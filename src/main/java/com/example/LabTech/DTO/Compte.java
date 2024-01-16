package com.example.LabTech.DTO;

import com.example.LabTech.DTO.enums.RoleDTO;
import lombok.Data;

@Data
public class Compte {

    private long id;
    private String username;
    private String password;
    private RoleDTO role;
}

