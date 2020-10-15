package com.bu.donation.service.implement;


import com.bu.donation.entity.Expense;
import com.bu.donation.model.Dto;
import com.bu.donation.repository.ExpenseRepository;
import com.bu.donation.service.ExpenseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Modified by Abdoul Aziz COMPAORE
 * @date 14/10/2020
 * @description implements all data access methods
 * needed for Audit.
 */
@Transactional("sdmsMainTransactionManager")
@Service("expenseService")

public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger logger = LogManager.getLogger(ExpenseServiceImpl.class);


    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper ;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
        ModelMapper modelMapper1 = new ModelMapper();

        modelMapper1.addMappings(new PropertyMap<Dto, Expense>() {
            @Override
            protected void configure() {
                map().setIdExpense(source.getFirstIntField());
                map().setAmount(source.getFirstBigDecimalField());
                map().setDate(source.getFirstDateField());
                map().setIdcategory(source.getSecondIntField());
                map().setComment(source.getFirstStringField());
            }
        });
        modelMapper1.addMappings(new PropertyMap<Expense, Dto>() {
            @Override
            protected void configure() {
                map().setFirstIntField(source.getIdExpense());
                map().setFirstBigDecimalField(source.getAmount());
                map().setFirstDateField(source.getDate());
                map().setSecondIntField(source.getIdcategory());
                map().setFirstStringField(source.getComment());
            }
        });

        this.modelMapper = modelMapper1;
    }

    /**
     * @param u
     * @return the object saved/updated in the database
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public Expense saveOrUpdate(Expense u) {
        Expense expense = null;
        try{
            if(u!=null) {
                expense = expenseRepository.save(u);
            } else {
                logger.debug("saveOrUpdate() in ExpenseServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdate() of ExpenseServiceImpl:");
            logger.debug(exception);
        }
        return expense;
    }

    /**
     * @param u
     * @return the object saved/updated in the database
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public Dto saveOrUpdateDto (Dto u) {
        Dto dto = null;
        try{
            if(u!=null) {
                Expense expense = expenseRepository.save(modelMapper.map(u, Expense.class));
                dto = modelMapper.map(expense, Dto.class);
            } else {
                logger.debug("saveOrUpdateDto() in ExpenseServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdateDto() of ExpenseServiceImpl:");
            logger.debug(exception);
        }
        return dto;
    }

    /**
     * @param listOfObject to persist
     * @return listOfObject persisted
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public List<Expense> saveAll(List<Expense> listOfObject) {
        List<Expense> ret;
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                ret = expenseRepository.saveAll(listOfObject);
                if (ret!=null){
                    return ret;
                }
            } else {
                logger.debug("saveAll() in ExpenseServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAll() of ExpenseServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Expense::toString).collect(Collectors.joining("||")));
        }
        return  Collections.emptyList();
    }

    /**
     * @param listOfObject to persist
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public void saveAllDto(List<Dto> listOfObject) {
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                expenseRepository.saveAll(listOfObject.stream().map(mgnDto -> modelMapper.map(mgnDto, Expense.class)).collect(Collectors.toList()));
            } else {
                logger.debug("saveAllDto() in ExpenseServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAllDto() of ExpenseServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Dto::toString).collect(Collectors.joining("||")));
        }
    }

    /**
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     * @description delete record with having id from database
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        try{
            if(id!=null){
                expenseRepository.deleteById(id);
            } else {
                logger.debug("deleteById() in ExpenseServiceImpl received a null input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in deleteById() of ExpenseServiceImpl:");
            logger.debug(exception);
        }
    }

    /**
     * @param pageable
     * @return list of requested objects from database in specified order.
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public List<Expense> findAll(Pageable pageable) {
        List<Expense> list;
        try{
            list = expenseRepository.findAll(pageable).getContent();
            if (list!=null){
                return list;
            }
        }catch(Exception exception){
            logger.debug("Exception in findAll() of ExpenseServiceImpl:");
            logger.debug(exception);
        }
        return Collections.emptyList();
    }

    /**
     * @param pageable
     * @return dto list of requested records page in specified order.
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public List<Dto> findAllToDto(Pageable pageable) {
        List<Expense> list;
        try{
            list = expenseRepository.findAll(pageable).getContent();
            if (list!=null){
                Type listType = new TypeToken<List<Dto>>() {}.getType();
                return modelMapper.map(list, listType);
            }
        }catch(Exception exception){
            logger.debug("Exception in findAllToDto() of ExpenseServiceImpl:");
            logger.debug(exception);
        }
        return Collections.emptyList();
    }

    /**
     * @return service repository
     * @Modified by Abdoul Aziz COMPAORE
     * @date 14/10/2020
     */
    @Override
    public JpaRepository getServiceRepository() {
        return this.expenseRepository;
    }

}
