package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.CategoryService;
import com.example.eShopping2.business.request.CreateCategoriesRequest;
import com.example.eShopping2.business.request.UpdateCategoriesRequest;
import com.example.eShopping2.business.response.GetAllCategoriesResponse;
import com.example.eShopping2.business.response.GetByIdCategoriesResponse;
import com.example.eShopping2.business.response.GetByIdUsersResponse;
import com.example.eShopping2.dataAccess.CategoryRepository;
import com.example.eShopping2.entity.Category;
import com.example.eShopping2.entity.User;
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
                GetAllCategoriesResponse getAllCategoriesResponse = convertToResponse(category);
                getAllCategoriesResponses.add(getAllCategoriesResponse);
            }
        }
        return getAllCategoriesResponses;
    }

    @Override
    public GetByIdCategoriesResponse geyById(int id) {

        Optional<Category> optionalCategory = this.categoryRepository.findById(id);

        return optionalCategory
                .map(this::convertToGetByIdResponse)
                .orElse(null);
    }

    private GetByIdCategoriesResponse convertToGetByIdResponse(Category category) {
        List<GetAllCategoriesResponse> subCategories = category.getSubCategories().stream()
                .map(this::convertToGetAllResponse)
                .collect(Collectors.toList());

        return new GetByIdCategoriesResponse(category.getId(), category.getName(), subCategories);
    }

    private GetAllCategoriesResponse convertToGetAllResponse(Category category) {
        List<GetAllCategoriesResponse> subCategories = category.getSubCategories().stream()
                .map(this::convertToGetAllResponse)
                .collect(Collectors.toList());

        return new GetAllCategoriesResponse(category.getName(), subCategories);
    }


    private GetAllCategoriesResponse convertToResponse(Category category) {
        GetAllCategoriesResponse response = new GetAllCategoriesResponse();
        response.setName(category.getName());

        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
            List<GetAllCategoriesResponse> subCategoryResponses = category.getSubCategories().stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
            response.setSubCategories(subCategoryResponses);
        }
        return response;
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
        Category category = categoryRepository.findByName(updateCategoriesRequest.getCurrentName());

        category.setName(updateCategoriesRequest.getNewName());
        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        this.categoryRepository.deleteById(id);
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
