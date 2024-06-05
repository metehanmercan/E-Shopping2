package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.ProductService;
import com.example.eShopping2.business.request.CreateProductRequest;
import com.example.eShopping2.dataAccess.CategoryRepository;
import com.example.eShopping2.dataAccess.ProductRepository;
import com.example.eShopping2.entity.Category;
import com.example.eShopping2.entity.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@AllArgsConstructor

public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void add(CreateProductRequest createProductRequest) throws IOException {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setStockQuantity(createProductRequest.getStockQuantity());
        product.setColour(createProductRequest.getColour());
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
}
