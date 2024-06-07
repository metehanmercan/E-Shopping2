package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateCommentRequest;

public interface CommentService {
    void add(CreateCommentRequest createCommentRequest);
}
