package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.ProductService;
import com.example.eShopping2.business.request.CreateProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;

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
                    @RequestParam("categoryId") int categoryId) {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                name, description, price, imageFile, stockQuantity, colour, categoryId);
        try {
            productService.add(createProductRequest);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving product image", e);
        }
    }
}

