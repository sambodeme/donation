package com.bu.donation.repository;


import com.bu.donation.entity.Audit;
import com.bu.donation.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Modified Abdoul Aziz COMPAORE
 * @date 10/04/2020
 * @description  Repository of Expense
 */

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
}
