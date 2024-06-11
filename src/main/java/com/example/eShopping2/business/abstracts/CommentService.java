package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateCommentRequest;
import com.example.eShopping2.business.request.UpdateCommentRequest;
import com.example.eShopping2.business.response.GetAllCommentResponse;
import com.example.eShopping2.business.response.GetByIdCommentResponse;
import java.util.List;

public interface CommentService {
    void add(CreateCommentRequest createCommentRequest);

    void update(UpdateCommentRequest updateCommentRequest);

    List<GetAllCommentResponse> getAll();

    GetByIdCommentResponse geyById(int id);

    void delete(int id);

    // bir ürüne yapılan tüm yorumları getirme
    List<GetAllCommentResponse> commentsProduct(int productId);
}
