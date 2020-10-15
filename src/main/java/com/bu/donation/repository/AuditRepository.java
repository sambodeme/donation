package com.bu.donation.repository;


import com.bu.donation.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Modified Abdoul Aziz COMPAORE
 * @date 10/04/2020
 * @description  Repository of Audit
 */

public interface AuditRepository extends JpaRepository<Audit,Integer> {
}
