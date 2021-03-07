package com.example.bakerymanagementapp.services.implementations;

import com.example.bakerymanagementapp.model.Admin;
import com.example.bakerymanagementapp.repository.AdminRepository;
import com.example.bakerymanagementapp.services.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);

        if (optionalAdmin.isEmpty()) {
            throw new UsernameNotFoundException("User not exists with username: " + username);
        }

        return new CustomUserDetails(optionalAdmin.get());
    }
}
