package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.ProductService;
import com.example.eShopping2.business.request.CreateProductRequest;
import com.example.eShopping2.business.request.UpdateProductRequest;
import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.business.response.GetByIdProductResponse;
import com.example.eShopping2.dataAccess.BrandRepository;
import com.example.eShopping2.dataAccess.CategoryRepository;
import com.example.eShopping2.dataAccess.ProductRepository;
import com.example.eShopping2.entity.Brand;
import com.example.eShopping2.entity.Category;
import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;

    @Override
    public void add(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setStockQuantity(createProductRequest.getStockQuantity());
        product.setColour(createProductRequest.getColour());
        // brand ayarla
        Brand brand = brandRepository.findById(createProductRequest.getBrandId()).orElseThrow();
        product.setBrand(brand);
        // Kategoriyi ayarla
        Category category = categoryRepository.findById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        // image set et
        List<ProductImage> productImages = new ArrayList<>();
        for (String url : createProductRequest.getImageUrls()) {
            ProductImage productImage = new ProductImage();
            productImage.setUrl(url);
            productImage.setProduct(product);
            productImages.add(productImage);
        }
        product.setProductImages(productImages);

        productRepository.save(product);
    }


    @Override
    public List<GetAllProductResponse> getAll() {
        List<Product> products = this.productRepository.findAll();
        List<GetAllProductResponse> getAllProductResponses = new ArrayList<>();

        for (Product product : products) {
            GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
            getAllProductResponse.setColour(product.getColour());
            getAllProductResponse.setPrice(product.getPrice());
            getAllProductResponse.setName(product.getName());
            getAllProductResponse.setDescription(product.getDescription());
            getAllProductResponse.setStockQuantity(product.getStockQuantity());
            // image set et
            getAllProductResponse.setCategoryName(product.getCategory().getName());
            getAllProductResponse.setBrandName(product.getBrand().getName());
            getAllProductResponses.add(getAllProductResponse);
        }
        return getAllProductResponses;
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest)  {
        Product product = this.productRepository.getById(updateProductRequest.getId());

        product.setColour(updateProductRequest.getColour());
        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setStockQuantity(updateProductRequest.getStockQuantity());
        product.setDescription(updateProductRequest.getDescription());
        // İMAGE SET ET
        List<ProductImage> productImages = new ArrayList<>();
        for (String url : updateProductRequest.getImageUrls()) {
            ProductImage productImage = new ProductImage();
            productImage.setUrl(url);
            productImage.setProduct(product);
            productImages.add(productImage);
        }
        product.setProductImages(productImages);
        // kategori ayarlama
        Category category = this.categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow();
        product.setCategory(category);
        // brand ayarlama
        Brand brand = this.brandRepository.findById(updateProductRequest.getBrandId()).orElseThrow();
        product.setBrand(brand);

        productRepository.save(product);
    }

    @Override
    public GetByIdProductResponse getById(int id) {
        Product product = this.productRepository.findById(id).orElseThrow();
        GetByIdProductResponse getByIdProductResponse = new GetByIdProductResponse();
        getByIdProductResponse.setName(product.getName());
        getByIdProductResponse.setColour(product.getColour());
        getByIdProductResponse.setDescription(product.getDescription());
        getByIdProductResponse.setPrice(product.getPrice());
        // İMAGE SET ET
        List<String> imageUrls = new ArrayList<>();
        for (ProductImage productImage : product.getProductImages()) {
            imageUrls.add(productImage.getUrl());
        }
        getByIdProductResponse.setImageUrls(imageUrls);

        getByIdProductResponse.setStockQuantity(product.getStockQuantity());
        getByIdProductResponse.setId(product.getId());
        getByIdProductResponse.setBrandName(product.getBrand().getName());
        getByIdProductResponse.setCategoryName(product.getCategory().getName()); // alttaki iki satırla aynı işleme sahip mi araştır?
        // KATEGORİ AYARI
        // Category category = product.getCategory();
        //   getByIdProductResponse.setCategoryName(category.getName());

        return getByIdProductResponse;
    }

    @Override
    public void delete(int id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<GetAllProductResponse> getKeyword(String keyword) {
        List<Product> products = this.productRepository.findByNameContaining(keyword);
        List<GetAllProductResponse> getAllProductResponses = new ArrayList<>();

        for (Product product : products) {
            GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
            getAllProductResponse.setPrice(product.getPrice());
            getAllProductResponse.setColour(product.getColour());
            getAllProductResponse.setDescription(product.getDescription());
            getAllProductResponse.setName(product.getName());
            // image set et
            List<ProductImage> productImages=product.getProductImages();
            List<String> imageUrls =new ArrayList<>();
            for (ProductImage productImage:productImages) {
                imageUrls.add(productImage.getUrl());
            }
            getAllProductResponse.setImageUrl(imageUrls);

            getAllProductResponse.setStockQuantity(product.getStockQuantity());
            // KATEGORİ ATAMA
            // Category category = product.getCategory();
            getAllProductResponse.setCategoryName(product.getCategory().getName());
            getAllProductResponse.setBrandName(product.getBrand().getName());
            getAllProductResponses.add(getAllProductResponse);
        }
        return getAllProductResponses;
    }
}
