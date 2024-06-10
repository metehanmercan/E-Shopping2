package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.FavoriteProductService;
import com.example.eShopping2.business.request.CreateFavoriteProductRequest;
import com.example.eShopping2.business.request.UpdateFavoriteProductRequest;
import com.example.eShopping2.business.response.GetAllFavoriteProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/favoriteProduct")
public class FavoriteProductController {
    private FavoriteProductService favoriteProductService;
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(CreateFavoriteProductRequest createFavoriteProduct){
        this.favoriteProductService.add(createFavoriteProduct);
    }
    @PutMapping("/update")
    public void update(UpdateFavoriteProductRequest updateFavoriteProductRequest){
        this.favoriteProductService.update(updateFavoriteProductRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.favoriteProductService.delete(id);
    }
    @GetMapping("/getAll")
    public List<GetAllFavoriteProductResponse> getAll(){
        return this.favoriteProductService.getAll();
    }
    @GetMapping("/getUserId")
    public List<GetAllFavoriteProductResponse> getFavoriteUserId(int id){
        return this.favoriteProductService.getUserId(id);
    }
}
