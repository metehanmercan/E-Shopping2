package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.AddressService;
import com.example.eShopping2.business.request.CreateAddressRequest;
import com.example.eShopping2.business.request.UpdateAddressRequest;
import com.example.eShopping2.business.response.GetAllAddressResponse;
import com.example.eShopping2.dataAccess.AddressRepository;
import com.example.eShopping2.dataAccess.UserRepository;
import com.example.eShopping2.entity.Address;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressManager implements AddressService {

    private AddressRepository addressRepository;
    private UserRepository userRepository;
    @Override
    public void add(CreateAddressRequest createAddressRequest) {
        Address address=new Address();
        address.setCity(createAddressRequest.getCity());
        address.setStreet(createAddressRequest.getStreet());
        address.setNeighborhood(createAddressRequest.getNeighborhood());
        User user=userRepository.getById(createAddressRequest.getUserId());
        address.setUser(user);
        this.addressRepository.save(address);
    }

    @Override
    public void update(UpdateAddressRequest updateAddressRequest) {
        Address address=this.addressRepository.getById(updateAddressRequest.getId());
        address.setNeighborhood(updateAddressRequest.getNeighborhood());
        address.setStreet(updateAddressRequest.getStreet());
        address.setCity(updateAddressRequest.getCity());
        User user=userRepository.getById(updateAddressRequest.getUserId());
        address.setUser(user);
        this.addressRepository.save(address);

    }

    @Override
    public void delete(int id) {
        this.addressRepository.deleteById(id);
    }

    @Override
    public List<GetAllAddressResponse> addressUser(int userId) {
        List<Address> addresses=this.addressRepository.findAddressByUserId(userId);
       List<GetAllAddressResponse > getAllAddressResponse=new ArrayList<>();
        for (Address address:addresses) {
            GetAllAddressResponse getAllAddressResponse1=new GetAllAddressResponse();
            getAllAddressResponse1.setCity(address.getCity());
            getAllAddressResponse1.setNeighborhood(address.getNeighborhood());
            getAllAddressResponse1.setStreet(address.getStreet());
            getAllAddressResponse1.setId(address.getId());
            getAllAddressResponse1.setUserId(address.getUser().getId());
            getAllAddressResponse.add(getAllAddressResponse1);
        }
        return getAllAddressResponse;
    }
}
