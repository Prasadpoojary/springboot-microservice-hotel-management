package com.prasad.paymentservice.repository;

import com.prasad.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>
{

    List<Payment> findByReservationId(Long id);
}
