package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.UserService;
import com.example.eShopping2.business.request.CreateUsersRequest;
import com.example.eShopping2.business.request.UpdateUsersRequest;
import com.example.eShopping2.business.response.GetAllUsersResponse;
import com.example.eShopping2.business.response.GetByIdUsersResponse;
import com.example.eShopping2.business.rule.UserBusinessRule;
import com.example.eShopping2.dataAccess.CartRepository;
import com.example.eShopping2.dataAccess.UserRepository;
import com.example.eShopping2.entity.Cart;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserRepository userRepository;
    private UserBusinessRule userBusinessRule;
    private CartRepository cartRepository;

    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = this.userRepository.findAll();
        List<GetAllUsersResponse> getAllUsersResponses = new ArrayList<>();

        for (User user : users) {
            GetAllUsersResponse getAllUsersResponseItem = new GetAllUsersResponse();
            getAllUsersResponseItem.setId(user.getId());
            getAllUsersResponseItem.setName(user.getName());
            getAllUsersResponseItem.setEmail(user.getEmail());
            getAllUsersResponseItem.setPhone(user.getPhone());
            getAllUsersResponseItem.setRole(user.getRole());
            getAllUsersResponseItem.setPassword(user.getPassword());
            getAllUsersResponseItem.setFullName(user.getFullName());
            getAllUsersResponses.add(getAllUsersResponseItem);
        }
        return getAllUsersResponses;
    }
    @Override
    public List<GetAllUsersResponse> getKeyword(String keyword) {
        if (keyword != null) {
            List<User> users = this.userRepository.findByNameContaining(keyword);
            List<GetAllUsersResponse> getAllUsersResponses = new ArrayList<>();

            for (User user : users) {
                GetAllUsersResponse getAllUsersResponseItem = new GetAllUsersResponse();
                getAllUsersResponseItem.setId(user.getId());
                getAllUsersResponseItem.setName(user.getName());
                getAllUsersResponseItem.setEmail(user.getEmail());
                getAllUsersResponseItem.setPhone(user.getPhone());
                getAllUsersResponseItem.setRole(user.getRole());
                getAllUsersResponseItem.setPassword(user.getPassword());
                getAllUsersResponseItem.setFullName(user.getFullName());
                getAllUsersResponses.add(getAllUsersResponseItem);
            }
            return getAllUsersResponses;
        } else {
            List<User> users = this.userRepository.findAll();
            List<GetAllUsersResponse> getAllUsersResponses = new ArrayList<>();

            for (User user : users) {
                GetAllUsersResponse getAllUsersResponseItem = new GetAllUsersResponse();
                getAllUsersResponseItem.setId(user.getId());
                getAllUsersResponseItem.setName(user.getName());
                getAllUsersResponseItem.setEmail(user.getEmail());
                getAllUsersResponseItem.setPhone(user.getPhone());
                getAllUsersResponseItem.setRole(user.getRole());
                getAllUsersResponseItem.setPassword(user.getPassword());
                getAllUsersResponseItem.setFullName(user.getFullName());
                getAllUsersResponses.add(getAllUsersResponseItem);
            }
            return getAllUsersResponses;
        }
    }


    @Override
    public GetByIdUsersResponse getById(int id) {
        this.userBusinessRule.checkIfExistsId(id);
        User user = this.userRepository.findById(id).orElseThrow();
        GetByIdUsersResponse getByIdUsersResponse = new GetByIdUsersResponse();
        getByIdUsersResponse.setId(user.getId());
        getByIdUsersResponse.setName(user.getName());
        getByIdUsersResponse.setEmail(user.getEmail());
        getByIdUsersResponse.setPhone(user.getPhone());
        getByIdUsersResponse.setRole(user.getRole());
        getByIdUsersResponse.setPassword(user.getPassword());
        getByIdUsersResponse.setFullName(user.getFullName());

        return getByIdUsersResponse;
    }

    @Override
    public void update(UpdateUsersRequest updateUsersRequest) {


        User user = userRepository.findById(updateUsersRequest.getId()).orElseThrow();
        user.setEmail(user.getEmail());
        user.setPassword(updateUsersRequest.getPassword());
        user.setPhone(updateUsersRequest.getPhone());
        userRepository.save(user);
    }
    @Override
    public void add(CreateUsersRequest createUsersRequest) {
        this.userBusinessRule.checkIfExistsName(createUsersRequest.getName());
        this.userBusinessRule.checkIfExistsEmail(createUsersRequest.getEmail());
        User user = new User();
        user.setName(createUsersRequest.getName());
        user.setEmail(createUsersRequest.getEmail());
        user.setFullName(createUsersRequest.getFullName());
        user.setPassword(createUsersRequest.getPassword());
        user.setRole(createUsersRequest.getRole());
        user.setPhone(createUsersRequest.getPhone());
        this.userRepository.save(user);
        // Kullanıcı için yeni bir sepet oluştur
        Cart cart=new Cart();
        cart.setUser(user);
        cart.setTotalPrice(BigDecimal.ZERO); // başlangıçta FİYAT SIFIR
        this.cartRepository.save(cart);

    }
    @Override
    public void delete(int id) {
        this.userBusinessRule.checkIfExistsId(id);
        this.userRepository.deleteById(id);
    }
}
