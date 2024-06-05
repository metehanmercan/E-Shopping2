package com.example.eShopping2.business.rule;


import com.example.eShopping2.business.exception.BusinessException;
import com.example.eShopping2.dataAccess.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRule {
    private UserRepository userRepository;

    public void checkIfExistsName(String name) {
        if (this.userRepository.existsByName(name)) {
            throw new BusinessException("user name already exists");
        }
    }
        public void checkIfExistsEmail(String email) {
        if (this.userRepository.existsByEmail(email)) {
            throw new BusinessException("email name already exists");
        }
    }
    public void checkIfExistsEmail1(String email) {
        if (this.userRepository.existsByEmail(email)==false) {
            throw new BusinessException("email don't found");
        }
    }

    public void checkIfExistsId(int id) {
        if (this.userRepository.existsById(id)==false) {
          throw new BusinessException("id don't found");
        }
    }

}
