package com.hoainamtd.controller;

import com.hoainamtd.configuration.Translator;
import com.hoainamtd.dto.request.UserRequestDTO;
import com.hoainamtd.dto.response.ResponseData;
import com.hoainamtd.dto.response.ResponseError;
import com.hoainamtd.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping(value = "/")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Request add user {}", userRequestDTO.getFirstname());
        try {
            userService.addUser(userRequestDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Save fail!");
        }
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@Min(1) @PathVariable("userId") int id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Request update userId {}", id);
        try {
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update fail!");
        }
    }

    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatus(@PathVariable @Min(1) int userId, @Min(1) @RequestParam(required = false) boolean status) {
        log.info("Request change user status, userId = {}", userId);
        try {
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status changed");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "User status change fail!");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@Min(1) @PathVariable int userId) {
        log.info("Request delete user by userId = {}", userId);
        try {
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted successfully");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete fail!");
        }
    }

    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable int userId) {
        log.info("Request get user detail by userId {}", userId);
        try {
            return new ResponseData<>(
                    HttpStatus.OK.value(),
                    "User",
                    new UserRequestDTO("Hoai", "Nam", "phone", "email")
            );
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "User does not exist!");
        }

    }

    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        try {
            return new ResponseData<>(
                    HttpStatus.OK.value(),
                    "User",
                    List.of(new UserRequestDTO("Hoai", "Nam", "phone", "email"),
                            new UserRequestDTO("Huu", "Hao", "phone", "email")
                    )
            );
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "User does not exist!");
        }
    }
}
