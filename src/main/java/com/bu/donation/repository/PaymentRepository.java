package com.bu.donation.repository;


import com.bu.donation.entity.Audit;
import com.bu.donation.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Modified Abdoul Aziz COMPAORE
 * @date 10/04/2020
 * @description  Repository of Payment
 */

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
