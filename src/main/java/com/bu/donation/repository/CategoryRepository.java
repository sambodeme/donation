package com.bu.donation.repository;


import com.bu.donation.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Modified Abdoul Aziz COMPAORE
 * @date 10/04/2020
 * @description  Repository of Categorie
 */

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
