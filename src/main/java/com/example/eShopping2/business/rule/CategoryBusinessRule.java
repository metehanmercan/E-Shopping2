package com.example.eShopping2.business.rule;

import com.example.eShopping2.business.exception.BusinessException;
import com.example.eShopping2.dataAccess.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryBusinessRule {
    private CategoryRepository categoryRepository;
    public void checkIfExistsId(int id) {
        if (this.categoryRepository.existsById(id)) {
            throw new BusinessException("id don't found");
        }
    }
}
