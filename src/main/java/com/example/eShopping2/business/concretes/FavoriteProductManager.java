package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.FavoriteProductService;
import com.example.eShopping2.business.request.CreateFavoriteProductRequest;
import com.example.eShopping2.business.request.UpdateFavoriteProductRequest;
import com.example.eShopping2.business.response.GetAllFavoriteProductResponse;
import com.example.eShopping2.dataAccess.FavoriteProductRepository;
import com.example.eShopping2.dataAccess.ProductRepository;
import com.example.eShopping2.dataAccess.UserRepository;
import com.example.eShopping2.entity.FavoriteProduct;
import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FavoriteProductManager implements FavoriteProductService {
    private FavoriteProductRepository favoriteProductRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    @Override
    public void add(CreateFavoriteProductRequest createFavoriteProduct) {
        FavoriteProduct  favoriteProduct=new FavoriteProduct();
        Product product=this.productRepository.getById(createFavoriteProduct.getProductId());
        favoriteProduct.setProduct(product);
        User user=this.userRepository.getById(createFavoriteProduct.getUserId());
        favoriteProduct.setUser(user);
        this.favoriteProductRepository.save(favoriteProduct);
    }

    @Override
    public void update(UpdateFavoriteProductRequest updateFavoriteProductRequest) {
        FavoriteProduct favoriteProduct=this.favoriteProductRepository.getById(updateFavoriteProductRequest.getId());
        User user=this.userRepository.getById(updateFavoriteProductRequest.getUserId());
        favoriteProduct.setUser(user);
        Product product=this.productRepository.getById(updateFavoriteProductRequest.getProductId());
        favoriteProduct.setProduct(product);
        this.favoriteProductRepository.save(favoriteProduct);
    }

    @Override
    public void delete(int id) {
        this.favoriteProductRepository.deleteById(id);
    }

    @Override
    public List<GetAllFavoriteProductResponse> getAll() {
     List<  FavoriteProduct>  favoriteProducts=this.favoriteProductRepository.findAll();
     List<GetAllFavoriteProductResponse> getAllFavoriteProductResponses=new ArrayList<>();
        for (FavoriteProduct favoriteProduct:favoriteProducts) {
            GetAllFavoriteProductResponse getAllFavoriteProductResponse=new GetAllFavoriteProductResponse();
            getAllFavoriteProductResponse.setId(favoriteProduct.getId());
            getAllFavoriteProductResponse.setProductId(favoriteProduct.getProduct().getId());
            getAllFavoriteProductResponse.setUserId(favoriteProduct.getUser().getId());
            getAllFavoriteProductResponses.add(getAllFavoriteProductResponse);
        }
        return getAllFavoriteProductResponses;
    }

    @Override
    public List<GetAllFavoriteProductResponse> getUserId(int userId) {
        List<FavoriteProduct> favoriteProducts=this.favoriteProductRepository.findFavoriteProductsByUserId(userId);
        List<GetAllFavoriteProductResponse> getAllFavoriteProductResponses=new ArrayList<>();
        for (FavoriteProduct favoriteProduct:favoriteProducts) {
            GetAllFavoriteProductResponse getAllFavoriteProductResponse=new GetAllFavoriteProductResponse();
            getAllFavoriteProductResponse.setId(favoriteProduct.getId());
            getAllFavoriteProductResponse.setUserId(favoriteProduct.getUser().getId());
            getAllFavoriteProductResponse.setProductId(favoriteProduct.getProduct().getId());
            getAllFavoriteProductResponses.add(getAllFavoriteProductResponse);
        }
        return getAllFavoriteProductResponses;
    }
}
