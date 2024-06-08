package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.CategoryService;
import com.example.eShopping2.business.request.CreateCategoriesRequest;
import com.example.eShopping2.business.request.UpdateCategoriesRequest;
import com.example.eShopping2.business.response.GetAllCategoriesResponse;

import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.business.response.GetByIdCategoriesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {
   private CategoryService categoryService;

   @GetMapping("/getAll")
   public List<GetAllCategoriesResponse> getAll() {
      return this.categoryService.getAll();
   }

   @PostMapping("/add")
   @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateCategoriesRequest createCategoriesRequest){
       this.categoryService.add(createCategoriesRequest);
   }
   @PutMapping
   public void update(UpdateCategoriesRequest updateCategoriesRequest){
      this.categoryService.update(updateCategoriesRequest);
   }
   @DeleteMapping("/{id}")
   public void delete(@PathVariable int id){
      this.categoryService.delete(id);
   }

   @GetMapping("({id}")
   public GetByIdCategoriesResponse getById(int id){
      return this.categoryService.getById(id);
   }
   @GetMapping("/getKeyword")
   public List<GetAllCategoriesResponse>  keyWord(String name){
      return categoryService.getKeyword(name);
   }
   @GetMapping("/productsCategory")
   public List<GetAllProductResponse> productsCategory(String name){
      return this.categoryService.productsCategoryName(name);
   }
}
