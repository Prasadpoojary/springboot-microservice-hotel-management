package com.prasad.customerservice.controller;


import com.prasad.customerservice.dto.AuthRequest;
import com.prasad.customerservice.dto.Message;
import com.prasad.customerservice.enums.MessageStatus;
import com.prasad.customerservice.model.Customer;
import com.prasad.customerservice.proxy.CustomerServiceProxy;
import com.prasad.customerservice.service.CustomerService;
import com.prasad.customerservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{


    @Autowired
    private CustomerServiceProxy customerService;

    @Autowired
    public JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    ResponseEntity<Customer> register(@RequestBody Customer customer)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Message> authenticate(@RequestBody AuthRequest authRequest)
    {

        Authentication authentication=null;
        try
        {
            authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        if(authentication!=null && authentication.isAuthenticated())
        {
            String jwt=jwtService.generateToken(authRequest.getEmail());
            Message response=new Message(MessageStatus.SUCCESS,jwt);
            return ResponseEntity.ok(response);
        }

        Message response=new Message(MessageStatus.FAILURE,"Invalid username or password");
        return ResponseEntity.badRequest().body(response);

    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws AccountNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomer(id));
    }


    @GetMapping("/")
    ResponseEntity<List<Customer>> getAllCustomer()
    {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());
    }

    @PutMapping("/")
    ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws AccountNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteCustomer(@PathVariable Long id) throws AccountNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(id));
    }



}
