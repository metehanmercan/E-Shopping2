package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.CommentService;
import com.example.eShopping2.business.request.CreateCommentRequest;
import com.example.eShopping2.dataAccess.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private CommentRepository commentRepository;


    @Override
    public void add(CreateCommentRequest createCommentRequest) {

    }
}
