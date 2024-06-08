package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.CategoryService;
import com.example.eShopping2.business.request.CreateCategoriesRequest;
import com.example.eShopping2.business.request.UpdateCategoriesRequest;
import com.example.eShopping2.business.response.GetAllCategoriesResponse;
import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.business.response.GetByIdCategoriesResponse;
import com.example.eShopping2.dataAccess.CategoryRepository;
import com.example.eShopping2.entity.Category;

import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.ProductImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    private CategoryRepository categoryRepository;


    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<GetAllCategoriesResponse> getAllCategoriesResponses = new ArrayList<>();

        for (Category category : categories) {
            if (category.getParentCategory() == null) { // Sadece ana kategoriler için işlem yap
                GetAllCategoriesResponse getAllCategoriesResponse = convertToGetAllResponse(category);
                getAllCategoriesResponses.add(getAllCategoriesResponse);
            }
        }
        return getAllCategoriesResponses;
    }
    private GetAllCategoriesResponse convertToGetAllResponse(Category category) {
        List<GetAllCategoriesResponse> subCategories = category.getSubCategories().stream()
                .map(this::convertToGetAllResponse)
                .collect(Collectors.toList());

        return new GetAllCategoriesResponse(category.getId(), category.getName(), subCategories);
    }

    @Override
    public GetByIdCategoriesResponse getById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        return optionalCategory.map(this::convertToGetByIdResponse).orElse(null);
    }
    private GetByIdCategoriesResponse convertToGetByIdResponse(Category category) {
        List<GetAllCategoriesResponse> subCategories = category.getSubCategories().stream()
                .map(this::convertToGetAllResponse)
                .collect(Collectors.toList());

        return new GetByIdCategoriesResponse(category.getId(), category.getName(), subCategories);
    }



    @Override
    public void add(CreateCategoriesRequest createCategoriesRequest) {
        Category category = new Category();
        category.setName(createCategoriesRequest.getName());

        if (createCategoriesRequest.getParentCategoryName() != null && !createCategoriesRequest.getParentCategoryName().isEmpty()) {
            Category parentCategory = categoryRepository.findByName(createCategoriesRequest.getParentCategoryName());
            category.setParentCategory(parentCategory);

        }
        categoryRepository.save(category);
    }

    @Override
    public void update(UpdateCategoriesRequest updateCategoriesRequest) {
        // Mevcut kategoriyi bul
        Category category = categoryRepository.getById(updateCategoriesRequest.getId());

        category.setName(updateCategoriesRequest.getName());
        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<GetAllProductResponse> productsCategoryName(String name) {
        List<Product> products=this.categoryRepository.findProductsByCategoryName(name);
        List<GetAllProductResponse> getAllProductResponses=new ArrayList<>();
        for (Product product:products) {
            GetAllProductResponse getAllProductResponse=new GetAllProductResponse();
            getAllProductResponse.setCategoryName(product.getCategory().getName());
            getAllProductResponse.setBrandName(product.getBrand().getName());
            getAllProductResponse.setPrice(product.getPrice());
            getAllProductResponse.setColour(product.getColour());
            getAllProductResponse.setDescription(product.getDescription());
            getAllProductResponse.setStockQuantity(product.getStockQuantity());
            getAllProductResponse.setName(product.getName());
            List<String> imageUrls=new ArrayList<>();
            for (ProductImage productImage: product.getProductImages()) {
                imageUrls.add(productImage.getUrl());
            }
            getAllProductResponse.setImageUrl(imageUrls);
            getAllProductResponses.add(getAllProductResponse);
        }
        return getAllProductResponses;
    }

    @Override
    public List<GetAllCategoriesResponse>  getKeyword(String name) {
        List<Category> categories = this.categoryRepository.findByNameContaining(name);
        List<GetAllCategoriesResponse> getAllCategoriesResponses = new ArrayList<>();
        if (name != null) {

            for (Category category1:categories) {
                GetAllCategoriesResponse getAllCategoriesResponse=new GetAllCategoriesResponse();
                getAllCategoriesResponse.setName(category1.getName());
               getAllCategoriesResponses.add(getAllCategoriesResponse);
            }
        }
        return getAllCategoriesResponses;
    }
}
