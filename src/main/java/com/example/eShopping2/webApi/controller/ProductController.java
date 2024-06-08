package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.ProductService;
import com.example.eShopping2.business.request.CreateProductRequest;
import com.example.eShopping2.business.request.UpdateProductRequest;
import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.business.response.GetByIdProductResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateProductRequest createProductRequest)  {

    this.productService.add(createProductRequest);
    }
    @GetMapping("/getAll")
    public List<GetAllProductResponse> getAll(){
        return this.productService.getAll();
    }
    @PutMapping("/update")
    public void update(UpdateProductRequest updateProductRequest) {
        this.productService.update(updateProductRequest);
    }
    @GetMapping("/{id}")
    public GetByIdProductResponse getById(@PathVariable int id){
        return this.productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.productService.delete(id);
    }

    @GetMapping("/getKeyword")
    public List<GetAllProductResponse> keyword(String keyword){
        return this.productService.getKeyword(keyword);
    }
}

