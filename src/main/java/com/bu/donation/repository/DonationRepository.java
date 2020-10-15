package com.bu.donation.repository;


import com.bu.donation.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Modified Abdoul Aziz COMPAORE
 * @date 10/04/2020
 * @description  Repository of Donation
 */

public interface DonationRepository extends JpaRepository<Donation,Integer> {
}
