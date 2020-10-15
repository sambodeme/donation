package com.bu.donation.repository;


import com.bu.donation.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Modified Abdoul Aziz COMPAORE
 * @date 10/04/2020
 * @description  Repository of Donor
 */

public interface DonorRepository extends JpaRepository<Donor,Integer> {
}
