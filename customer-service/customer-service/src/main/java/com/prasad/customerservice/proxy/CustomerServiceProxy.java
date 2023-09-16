package com.prasad.customerservice.proxy;

import com.prasad.customerservice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;


@Component
public class CustomerServiceProxy implements ICustomerService
{

    Logger logger= LoggerFactory.getLogger(CustomerServiceProxy.class);

    @Autowired
    private ICustomerService customerService;

    @Override
    public Customer addCustomer(Customer customer) {
        logger.info("Entry into addCustomer method");
        Customer newCustomer= customerService.addCustomer(customer);
        logger.info("Exit from addCustomer method");
        return newCustomer;
    }

    @Override
    public Customer getCustomer(Long id) throws AccountNotFoundException {
        logger.info("Entry into getCustomer method");
        Customer customer = customerService.getCustomer(id);
        logger.info("Exit from getCustomer method");
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        logger.info("Entry into getAllCustomer method");
        List<Customer> allCustomer = customerService.getAllCustomer();
        logger.info("Exit from getAllCustomer method");
        return allCustomer;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws AccountNotFoundException {
        logger.info("Entry into updateCustomer method");
        Customer updatedCustomer = customerService.updateCustomer(customer);
        logger.info("Exit from updateCustomer method");
        return updatedCustomer;
    }

    @Override
    public boolean deleteCustomer(Long id) throws AccountNotFoundException {
        logger.info("Entry into deleteCustomer method");
        boolean status = customerService.deleteCustomer(id);
        logger.info("Exit from deleteCustomer method");
        return status;
    }
}
