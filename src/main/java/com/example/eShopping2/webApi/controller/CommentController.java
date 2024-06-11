package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.CommentService;
import com.example.eShopping2.business.request.CreateCommentRequest;
import com.example.eShopping2.business.request.UpdateCommentRequest;
import com.example.eShopping2.business.response.GetAllCommentResponse;
import com.example.eShopping2.business.response.GetByIdCommentResponse;
import com.example.eShopping2.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    @PostMapping("/save")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void  add(@RequestBody @Valid CreateCommentRequest createCommentRequest){
        this.commentService.add(createCommentRequest);
    }
    @PutMapping("/{id}")
    public void update(UpdateCommentRequest updateCommentRequest){
        this.commentService.update(updateCommentRequest);
    }
    @GetMapping("/getAll")
    public List<GetAllCommentResponse> getAll(){
        return this.commentService.getAll();
    }
    @GetMapping("/{id}")
    public GetByIdCommentResponse getById(@PathVariable int id){
        return this.commentService.geyById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.commentService.delete(id);
    }

    @GetMapping("/commentsProduct")
    public List<GetAllCommentResponse> commentsProduct(int productId){
    return this.commentService.commentsProduct(productId);
    }
}
