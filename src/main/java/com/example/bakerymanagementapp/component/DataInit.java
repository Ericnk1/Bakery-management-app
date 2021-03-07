package com.example.bakerymanagementapp.component;

import com.example.bakerymanagementapp.model.Admin;
import com.example.bakerymanagementapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {

    @Autowired
    AdminRepository adminRepository;

    @PostConstruct
    public void initData() {
        initAdmin();
    }

    private void initAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin@bakerytest.com");
        admin.setPassword("123456");

        if (adminRepository.findByUsername(admin.getUsername()).isEmpty()) {
            adminRepository.save(admin);
        }
    }
}
