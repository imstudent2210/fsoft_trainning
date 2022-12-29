package com.example.library.service.impl;

import com.example.library.dto.AdminDTO;
import com.example.library.model.Admin;
import com.example.library.repository.AdminRepository;
import com.example.library.repository.RoleRepository;
import com.example.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {
//    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin findByUsername(String userName) {
        return adminRepository.findByUserName(userName);
    }

    @Override
    public Admin save(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setUserName(adminDTO.getUserName());
        admin.setPassWord(adminDTO.getPassWord());
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}
