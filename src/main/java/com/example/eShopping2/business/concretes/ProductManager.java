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
    public void add(CreateProductRequest createProductRequest) throws IOException {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setStockQuantity(createProductRequest.getStockQuantity());
        product.setColour(createProductRequest.getColour());
        // brand ayarla
        Brand brand=brandRepository.findById(createProductRequest.getBrandId()).orElseThrow();
        product.setBrand(brand);
        // Kategoriyi ayarla
        Category category = categoryRepository.findById(createProductRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        // Resim dosyasını kaydet
        String imageUrl = saveImage(createProductRequest.getImageFile());
        product.setImageUrl(imageUrl);
        System.out.println();

        productRepository.save(product);
    }


    private String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        String uploadDir = System.getProperty("user.dir") + File.separator + "product-images" + File.separator;
        File uploadDirFile = new File(uploadDir);

        if (!uploadDirFile.exists()) {
            boolean created = uploadDirFile.mkdirs();
            if (!created) {
                throw new IOException("Failed to create directory: " + uploadDir);
            }
        }

        String fileName = System.currentTimeMillis() + "-" + imageFile.getOriginalFilename();
        File destinationFile = new File(uploadDir + fileName);

        try {
            imageFile.transferTo(destinationFile);
        } catch (IOException e) {
            throw new IOException("Error saving product image: " + e.getMessage());
        }

        return destinationFile.getAbsolutePath();
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
            getAllProductResponse.setImageUrl(product.getImageUrl());
            getAllProductResponse.setCategoryName(product.getCategory().getName());
            getAllProductResponse.setBrandName(product.getBrand().getName());
            getAllProductResponses.add(getAllProductResponse);
        }
        return getAllProductResponses;
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) throws IOException {
        Product product = this.productRepository.getById(updateProductRequest.getId());

        product.setColour(updateProductRequest.getColour());
        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setStockQuantity(updateProductRequest.getStockQuantity());
        product.setDescription(updateProductRequest.getDescription());

        // kategori ayarlama
        Category category = this.categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow();
        product.setCategory(category);
        // brand ayarlama
        Brand brand=this.brandRepository.findById(updateProductRequest.getBrandId()).orElseThrow();
       product.setBrand(brand);

        // Resim dosyasını güncelle
        if (updateProductRequest.getImageFile() != null && !updateProductRequest.getImageFile().isEmpty()) {
            String imageUrl = saveImage(updateProductRequest.getImageFile());
            product.setImageUrl(imageUrl);
        }
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
        getByIdProductResponse.setImageUrl(product.getImageUrl());
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
            getAllProductResponse.setImageUrl(product.getImageUrl());
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
