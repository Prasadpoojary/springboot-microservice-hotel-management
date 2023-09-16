package com.prasad.customerservice.proxy;


import com.prasad.customerservice.exceptions.CustomerAlreadyExistException;
import com.prasad.customerservice.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface ICustomerService
{

    public Customer addCustomer(Customer customer);

    public Customer getCustomer(Long id) throws AccountNotFoundException;

    public List<Customer> getAllCustomer();

    public Customer updateCustomer(Customer customer) throws AccountNotFoundException;

    public boolean deleteCustomer(Long id) throws AccountNotFoundException;

}
