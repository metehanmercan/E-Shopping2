package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.CommentService;
import com.example.eShopping2.business.request.CreateCommentRequest;
import com.example.eShopping2.business.request.UpdateCommentRequest;
import com.example.eShopping2.business.response.GetAllCommentResponse;
import com.example.eShopping2.business.response.GetByIdCommentResponse;
import com.example.eShopping2.dataAccess.CommentRepository;
import com.example.eShopping2.dataAccess.ProductRepository;
import com.example.eShopping2.dataAccess.UserRepository;
import com.example.eShopping2.entity.Comment;
import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;


    @Override
    public void add(CreateCommentRequest createCommentRequest) {
        Comment comment = new Comment();
        comment.setContent(createCommentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        User user = this.userRepository.getById(createCommentRequest.getUserId());
        comment.setUser(user);
        Product product = this.productRepository.getById(createCommentRequest.getProductId());
        comment.setProduct(product);
        this.commentRepository.save(comment);
    }

    @Override
    public void update(UpdateCommentRequest updateCommentRequest) {
        Comment comment = this.commentRepository.getById(updateCommentRequest.getId());
        comment.setContent(updateCommentRequest.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        this.commentRepository.save(comment);
    }

    @Override
    public List<GetAllCommentResponse> getAll() {
        List<Comment> comments = this.commentRepository.findAll();
        List<GetAllCommentResponse> getAllCommentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            GetAllCommentResponse getAllCommentResponse = new GetAllCommentResponse();
            getAllCommentResponse.setId(comment.getId());
            getAllCommentResponse.setContent(comment.getContent());
            getAllCommentResponse.setCreated(comment.getCreatedAt());
            getAllCommentResponse.setProductId(comment.getId());
            getAllCommentResponse.setUserId(comment.getId());
            getAllCommentResponses.add(getAllCommentResponse);
        }
        return getAllCommentResponses;
    }

    @Override
    public GetByIdCommentResponse geyById(int id) {
        Comment comment = this.commentRepository.getById(id);
        GetByIdCommentResponse getByIdCommentResponse = new GetByIdCommentResponse();
        getByIdCommentResponse.setId(comment.getId());
        getByIdCommentResponse.setContent(comment.getContent());
        getByIdCommentResponse.setCreated(comment.getCreatedAt());
        getByIdCommentResponse.setUserId(comment.getUser().getId());
        getByIdCommentResponse.setProductId(comment.getProduct().getId());
        return getByIdCommentResponse;
    }

    @Override
    public void delete(int id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    public List<GetAllCommentResponse> commentsProduct(int productId) {
        List<Comment> comments = this.commentRepository.findCommentsByProductId(productId);
        List<GetAllCommentResponse> getAllCommentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            GetAllCommentResponse getAllCommentResponse = new GetAllCommentResponse();
            getAllCommentResponse.setId(comment.getId());
            getAllCommentResponse.setCreated(comment.getCreatedAt());
            getAllCommentResponse.setProductId(comment.getProduct().getId());
            getAllCommentResponse.setContent(comment.getContent());
            getAllCommentResponse.setUserId(comment.getUser().getId());
            getAllCommentResponses.add(getAllCommentResponse);
        }
        return getAllCommentResponses;
    }
}
