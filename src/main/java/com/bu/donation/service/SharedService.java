package com.bu.donation.service;

import com.bu.donation.model.Dto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Abdoul Aziz COMPAORE
 * @date 14/10/2020
 * @description  Defines all service data access methods
 * needed for MgnMultiFields entities (i.e., Audit ...).
 */
public interface SharedService<T> {
    /**
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @param u
     * @return the object saved/updated in the database
     */
    T saveOrUpdate(T u);

    /**
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @param u
     * @return the object saved/updated in the database
     */
    Dto saveOrUpdateDto(Dto u);

    /**
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @param listOfObject to persist
     * @return listOfObject persisted
     */
    List<T> saveAll(List<T> listOfObject);

    /**
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @param listOfObject to persist

     */
    void saveAllDto(List<Dto> listOfObject);

    /**
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @description delete record with having id from database
     * @param id
     */
    void deleteById (Integer id);

    /**
     * @param pageable
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @return list of requested objects from database in specified order.
     */
    List<T> findAll(Pageable pageable);

    /**
     * @param pageable
     * @return list of requested records page in specified order.
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    List<Dto> findAllToDto(Pageable pageable);

    /**
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @return service repository
     */
    JpaRepository getServiceRepository();

    /**
     * @param pageSize
     * @param jpaRepository
     * @return total number of record pages available
     * @author Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @throws Exception
     */
    default int getTotalPages(Integer pageSize, JpaRepository<?,?> jpaRepository) {
        int ret ;
        if(jpaRepository==null){
            return 0;
        }
        ret = jpaRepository.findAll(PageRequest.of(0, pageSize)).getTotalPages();
        return ret;
    }
}

