package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.UserService;
import com.example.eShopping2.business.request.CreateUsersRequest;
import com.example.eShopping2.business.request.UpdateUsersRequest;
import com.example.eShopping2.business.response.GetAllUsersResponse;
import com.example.eShopping2.business.response.GetByIdUsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @GetMapping("/getAll")
    public List<GetAllUsersResponse> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/getKeyword")
    public List<GetAllUsersResponse> getKeyword(@RequestParam(required = false) String keyword) {
        return this.userService.getKeyword(keyword);
    }

    @GetMapping("/{id}")
    public GetByIdUsersResponse getById(@PathVariable int id) {
        return this.userService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateUsersRequest createUsersRequest) {
        this.userService.add(createUsersRequest);
    }

    @PutMapping("/update")
    public void update(UpdateUsersRequest updateUsersRequest) {
        this.userService.update(updateUsersRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id ){
        this.userService.delete(id);
    }

}
