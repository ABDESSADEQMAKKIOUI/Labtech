package com.example.LabTech.DTO;

import com.example.demo.DTO.enums.RoleDTO;
import lombok.Data;

@Data
public class UserDTO {

    private long id;
    private String username;
    private String password;
    private RoleDTO role;
}

