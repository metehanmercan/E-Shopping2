package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.BrandService;
import com.example.eShopping2.business.request.CreateBrandRequest;
import com.example.eShopping2.business.request.UpdateBrandRequest;
import com.example.eShopping2.business.response.GetAllBrandsResponse;
import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.dataAccess.BrandRepository;
import com.example.eShopping2.entity.Brand;
import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());
        this.brandRepository.save(brand);
    }

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = this.brandRepository.findAll();
        List<GetAllBrandsResponse> getAllBrandsResponses = new ArrayList<>();
        for (Brand brand : brands) {
            GetAllBrandsResponse getAllBrandsResponse = new GetAllBrandsResponse();
            getAllBrandsResponse.setId(brand.getId());
            getAllBrandsResponse.setName(brand.getName());
            getAllBrandsResponses.add(getAllBrandsResponse);
        }
        return getAllBrandsResponses;
    }

    @Override
    public GetAllBrandsResponse getById(int id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow();
        GetAllBrandsResponse getAllBrandsResponse = new GetAllBrandsResponse();
        getAllBrandsResponse.setName(brand.getName());
        return getAllBrandsResponse;
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        Brand brand = this.brandRepository.getById(updateBrandRequest.getId());
        brand.setName(updateBrandRequest.getName());
        this.brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        this.brandRepository.deleteById(id);
    }

    @Override
    public List<GetAllProductResponse> productsBrand(String brandName) {
        List<Product> products = this.brandRepository.findProductsByBrandName(brandName);
        List<GetAllProductResponse> getAllProductResponses = new ArrayList<>();
        for (Product product : products) {
            GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
            getAllProductResponse.setName(product.getName());
            getAllProductResponse.setBrandName(product.getBrand().getName());
            getAllProductResponse.setPrice(product.getPrice());
            getAllProductResponse.setColour(product.getColour());
            getAllProductResponse.setDescription(product.getDescription());
            getAllProductResponse.setStockQuantity(product.getStockQuantity());
            getAllProductResponse.setCategoryName(product.getCategory().getName());
            List<String> imageUrls = new ArrayList<>();
            for (ProductImage productImage : product.getProductImages()) {
                imageUrls.add(productImage.getUrl());
            }
            getAllProductResponse.setImageUrl(imageUrls);

            getAllProductResponses.add(getAllProductResponse);
        }


        return getAllProductResponses;
    }

    @Override
    public List<GetAllProductResponse> productsBrand(int brandId) {
        List<Product> products = this.brandRepository.findProductsByBrandId(brandId);
        List<GetAllProductResponse> getAllProductResponses = new ArrayList<>();
        for (Product product : products) {
            GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
            getAllProductResponse.setName(product.getName());
            getAllProductResponse.setBrandName(product.getBrand().getName());
            getAllProductResponse.setPrice(product.getPrice());
            getAllProductResponse.setColour(product.getColour());
            getAllProductResponse.setDescription(product.getDescription());
            getAllProductResponse.setStockQuantity(product.getStockQuantity());
            getAllProductResponse.setCategoryName(product.getCategory().getName());
            List<String> imageUrls = new ArrayList<>();
            for (ProductImage productImage : product.getProductImages()) {
                imageUrls.add(productImage.getUrl());
            }
            getAllProductResponse.setImageUrl(imageUrls);

            getAllProductResponses.add(getAllProductResponse);
        }


        return getAllProductResponses;
    }

    @Override
    public List<GetAllBrandsResponse> getKeyword(String keyword) {
        List<Brand> brands = this.brandRepository.findByNameContaining(keyword);
        List<GetAllBrandsResponse> getAllBrandsResponses = new ArrayList<>();
        if (keyword != null) {
            for (Brand brand : brands) {
                GetAllBrandsResponse getAllBrandsResponse = new GetAllBrandsResponse();
                getAllBrandsResponse.setId(brand.getId());
                getAllBrandsResponse.setName(brand.getName());
                getAllBrandsResponses.add(getAllBrandsResponse);
            }
        }
        return getAllBrandsResponses;
    }
}
