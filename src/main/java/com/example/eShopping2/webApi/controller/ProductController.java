package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.ProductService;
import com.example.eShopping2.business.request.CreateProductRequest;
import com.example.eShopping2.business.request.UpdateProductRequest;
import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.business.response.GetByIdProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @PostMapping(value = "/add",consumes ="multipart/form-data")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestParam("name") String name,
                    @RequestParam("description") String description,
                    @RequestParam("price") BigDecimal price,
                    @RequestParam("imageFile") MultipartFile imageFile,
                    @RequestParam("stockQuantity") int stockQuantity,
                    @RequestParam("colour") String colour,
                    @RequestParam("brandId") int brandId,
                    @RequestParam("categoryId") int categoryId) {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                name, description, price, imageFile, stockQuantity, colour, categoryId,brandId);
        try {
            productService.add(createProductRequest);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving product image", e);
        }
    }
    @GetMapping("/getAll")
    public List<GetAllProductResponse> getAll(){
        return this.productService.getAll();
    }
    @PutMapping(value = "/update",consumes ="multipart/form-data")
    public void update(
                        @RequestParam("id") int id,
                        @RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("price") BigDecimal price,
                       @RequestParam("imageFile") MultipartFile imageFile,
                       @RequestParam("stockQuantity") int stockQuantity,
                       @RequestParam("colour") String colour,
                        @RequestParam("brandId") int brandId,
                       @RequestParam("categoryId") int categoryId) throws IOException {
       UpdateProductRequest updateProductRequest = new UpdateProductRequest(id,
                name, description, price, imageFile, stockQuantity, colour, categoryId,brandId);
        productService.update(updateProductRequest);
    }
    @GetMapping("/{id}")
    public GetByIdProductResponse getById(@PathVariable int id){
        return this.productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(int id){
        this.productService.delete(id);
    }

    @GetMapping("/getKeyword")
    public List<GetAllProductResponse> keyword(String keyword){
        return this.productService.getKeyword(keyword);
    }
}

