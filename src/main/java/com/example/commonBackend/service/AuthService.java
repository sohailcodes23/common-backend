package com.example.commonBackend.service;

import com.example.commonBackend.config.security.JwtUtils;
import com.example.commonBackend.dto.AuthResponse;
import com.example.commonBackend.dto.LoginDto;
import com.example.commonBackend.entity.Customer;
import com.example.commonBackend.entity.UserDetail;
import com.example.commonBackend.exceptions.ResourceNotFoundException;
import com.example.commonBackend.repository.CustomerRepository;
import com.example.commonBackend.repository.UserDetailRepository;
import com.example.commonBackend.util.MessageUtil;
import com.example.commonBackend.util.Role;
import com.example.commonBackend.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthResponse login(LoginDto loginDto) {
        UserDetail userDetail = userDetailRepository.findByUsernameAndStatus(loginDto.getUsername(), Status.ACTIVE.getValue())
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginDto.getPassword(), userDetail.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        Long userId = null;
        Object data = null;

        if (userDetail.getRole().equalsIgnoreCase(Role.CUSTOMER.getValue())) {
//            data = iamObject.getCustomers().get(0);
//            userId = iamObject.getCustomers().get(0).getId();
        }
        String token = jwtUtils.createToken(userId, Role.CUSTOMER.getValue());
        return new AuthResponse(token, data);
    }

    public void registerCustomer(LoginDto loginDto) {

        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(loginDto.getUsername());
        userDetail.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        userDetail.setRole(Role.CUSTOMER.getValue());
        userDetail.setStatus(Status.ACTIVE.getValue());
        userDetailRepository.save(userDetail);

        Customer customer = new Customer();
//        customer.setIamObject(iamObject);
        customerRepository.save(customer);
    }
}
