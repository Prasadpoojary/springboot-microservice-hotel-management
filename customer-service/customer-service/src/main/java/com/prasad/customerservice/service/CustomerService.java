package com.prasad.customerservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.customerservice.dto.Notification;
import com.prasad.customerservice.exceptions.CustomerAlreadyExistException;
import com.prasad.customerservice.model.Customer;
import com.prasad.customerservice.proxy.ICustomerService;
import com.prasad.customerservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerService implements ICustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KafkaTemplate<String,Object> template;

    Logger logger=LoggerFactory.getLogger(CustomerService.class);

    public Customer addCustomer(Customer customer)
    {

        if(customerRepository.existsByEmail(customer.getEmail()))
        {
            CustomerAlreadyExistException customerAlreadyExistException = new CustomerAlreadyExistException(customer.getEmail() + " : Already registered");
            logger.error("Business Exception occurred : {}",customerAlreadyExistException);
            throw customerAlreadyExistException;
        }
        else
        {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            Customer newCustomer=customerRepository.save(customer);
//            Kafka welcome notification
            notify(newCustomer.getId(),"Welcome to our Hotel Reservation platform");
            logger.info("Customer registered : {}",newCustomer.getEmail());
            return newCustomer;
        }
    }

    public Customer getCustomer(Long id) throws AccountNotFoundException
    {
        return customerRepository.findById(id).stream().findAny().orElseThrow(AccountNotFoundException::new);
    }


    public List<Customer> getAllCustomer()
    {
        return customerRepository.findAll();
    }


    public Customer updateCustomer(Customer customer) throws AccountNotFoundException
    {
        if(customerRepository.existsById(customer.getId()))
        {
            try
            {
                return customerRepository.save(customer);
            }
            catch(Exception e)
            {
                logger.error("System exception while updating customer details : {}",e);
                return null;
            }
        }
        else
        {
            AccountNotFoundException customer_not_found = new AccountNotFoundException("Customer not found");
            logger.error("Business exception occurred : {}",customer_not_found);
            throw customer_not_found;
        }

    }


    public boolean deleteCustomer(Long id) throws AccountNotFoundException
    {
        if(customerRepository.existsById(id))
        {
            try
            {
                customerRepository.deleteById(id);
                return true;
            }
            catch(Exception e)
            {
                logger.error("System exception while deleting customer : {}",e);
                return false;
            }
        }
        else
        {
            AccountNotFoundException customer_not_found = new AccountNotFoundException("Customer not found");
            logger.error("Business exception occurred : {}",customer_not_found);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found", customer_not_found);
        }

    }

    private void notify(Long userId, String message)
    {
        logger.info("sending notification via Kafka");
        Notification notification=new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setTimestamp(new Date());

        try
        {
            ObjectMapper mapper=new ObjectMapper();
            CompletableFuture<SendResult<String,Object>> response= template.send("HotelNotificationTopic",mapper.writeValueAsString(notification));
            response.whenComplete((result,ex)->{
                if (ex == null) {
                    logger.info("Sent message with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    logger.info("Unable to send message due to : " + ex.getMessage());
                }
            });
        }
        catch(Exception e)
        {
            logger.error("Unable to push notification to Kafka");
            throw new RuntimeException(e.getMessage());
        }
    }
}
