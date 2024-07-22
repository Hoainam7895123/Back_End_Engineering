package com.hoainamtd.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hoainamtd.dto.validator.EnumPattern;
import com.hoainamtd.dto.validator.EnumValue;
import com.hoainamtd.dto.validator.GenderSubset;
import com.hoainamtd.dto.validator.PhoneNumber;
import com.hoainamtd.util.Gender;
import com.hoainamtd.util.UserStatus;
import com.hoainamtd.util.UserType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserRequestDTO implements Serializable {
    @NotBlank(message = "firstName must be not blank")
    private String firstname;
    @NotNull(message = "lastName must be not null")
    private String lastname;
    @Email(message = "email invalid format")
    private String email;
    // @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @PhoneNumber
    private String phone;
    @NotNull(message = "date of birth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

    // @Pattern(regexp = "^ACTIVE|INACTIVE|NONE$", message = "status must be one in {ACTIVE, INACTIVE, NONE}")
    // private String userStatus;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE") // cách này có thể sử dụng với nhiều loại enum khác nhau
    private UserStatus userStatus;

    @GenderSubset(anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER})
    private Gender gender;

    @NotNull(message = "username must be not null")
    private String username;
    @NotNull(message = "password must be not null")
    private String password;

    @EnumValue(name = "type", enumClass = UserType.class)
    private String type;

    public UserRequestDTO(String firstname, String lastname, String email, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }
}
