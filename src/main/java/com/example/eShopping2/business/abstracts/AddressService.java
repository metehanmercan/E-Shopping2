package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateAddressRequest;
import com.example.eShopping2.business.request.UpdateAddressRequest;
import com.example.eShopping2.business.response.GetAllAddressResponse;
import java.util.List;

public interface AddressService {
    void add(CreateAddressRequest createAddressRequest);
    void  update(UpdateAddressRequest updateAddressRequest);

    void delete(int id);

    List<GetAllAddressResponse> addressUser(int userId);
}
