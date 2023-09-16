package com.prasad.customerservice.config;


import com.prasad.customerservice.model.Customer;
import com.prasad.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetailService implements UserDetailsService
{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> user=customerRepository.findByEmail(email);
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
