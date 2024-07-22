package com.hoainamtd.service;

import com.hoainamtd.dto.request.UserRequestDTO;
import com.hoainamtd.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public int addUser(UserRequestDTO userRequestDTO) {
        System.out.println("Save user to database");
        if (!userRequestDTO.getFirstname().equals("Nam")) {
            throw new ResourceNotFoundException("Nam khong ton tai");
        }
        return 0;
    }
}
