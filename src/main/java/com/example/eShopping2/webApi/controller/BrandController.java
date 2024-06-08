package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.BrandService;
import com.example.eShopping2.business.request.CreateBrandRequest;
import com.example.eShopping2.business.request.UpdateBrandRequest;
import com.example.eShopping2.business.response.GetAllBrandsResponse;
import com.example.eShopping2.business.response.GetAllProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }
    @GetMapping("/getAll")
    public List<GetAllBrandsResponse> getAll(){
        return brandService.getAll();
    }

    @PutMapping("/{id}")
    public void update(UpdateBrandRequest updateBrandRequest){
        this.brandService.update(updateBrandRequest);
    }

    @GetMapping("/getById")
    public GetAllBrandsResponse getById(int id){
        return this.brandService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.brandService.delete(id);
    }
    @GetMapping("/keyword")
    public List<GetAllBrandsResponse> getKeyword(String keyword){
        return this.brandService.getKeyword(keyword);
    }

    @GetMapping("/productsBrand")
    public List<GetAllProductResponse> productsBrand(String brandName){
        return this.brandService.productsBrand(brandName);
    }
}
