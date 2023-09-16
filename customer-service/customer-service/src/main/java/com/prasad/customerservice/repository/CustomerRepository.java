package com.prasad.customerservice.repository;

import com.prasad.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>
{
    boolean existsByEmail(String email);
    public Optional<Customer> findByEmail(String email);
}
