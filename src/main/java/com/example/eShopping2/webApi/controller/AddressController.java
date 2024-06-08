package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.AddressService;
import com.example.eShopping2.business.request.CreateAddressRequest;
import com.example.eShopping2.business.request.UpdateAddressRequest;
import com.example.eShopping2.business.response.GetAllAddressResponse;
import com.example.eShopping2.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private AddressService addressService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(CreateAddressRequest createAddressRequest){
        this.addressService.add(createAddressRequest);
    }
    @PutMapping("/update")
    public void update(UpdateAddressRequest updateAddressRequest){
        this.addressService.update(updateAddressRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.addressService.delete(id);
    }
    @GetMapping("/addresUser")
    public List<GetAllAddressResponse> addresUser(int userId){
        return this.addressService.addressUser(userId);
    }
}
