package com.bu.donation.service.implement;


import com.bu.donation.entity.Category;
import com.bu.donation.model.Dto;
import com.bu.donation.repository.CategoryRepository;
import com.bu.donation.service.CategoryService;
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
@Service("categoryService")

public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);


    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper ;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        ModelMapper modelMapper1 = new ModelMapper();

        modelMapper1.addMappings(new PropertyMap<Dto, Category>() {
            @Override
            protected void configure() {
                map().setIdCategory(source.getFirstIntField());
                map().setName(source.getFirstStringField());
            }
        });
        modelMapper1.addMappings(new PropertyMap<Category, Dto>() {
            @Override
            protected void configure() {
                map().setFirstIntField(source.getIdCategory());
                map().setFirstStringField(source.getName());
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
    public Category saveOrUpdate(Category u) {
        Category category = null;
        try{
            if(u!=null) {
                category = categoryRepository.save(u);
            } else {
                logger.debug("saveOrUpdate() in CategoryServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdate() of CategoryServiceImpl:");
            logger.debug(exception);
        }
        return category;
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
                Category category = categoryRepository.save(modelMapper.map(u, Category.class));
                dto = modelMapper.map(category, Dto.class);
            } else {
                logger.debug("saveOrUpdateDto() in CategoryServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdateDto() of CategoryServiceImpl:");
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
    public List<Category> saveAll(List<Category> listOfObject) {
        List<Category> ret;
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                ret = categoryRepository.saveAll(listOfObject);
                if (ret!=null){
                    return ret;
                }
            } else {
                logger.debug("saveAll() in CategoryServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAll() of CategoryServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Category::toString).collect(Collectors.joining("||")));
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
                categoryRepository.saveAll(listOfObject.stream().map(mgnDto -> modelMapper.map(mgnDto, Category.class)).collect(Collectors.toList()));
            } else {
                logger.debug("saveAllDto() in CategoryServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAllDto() of AuditServiceImpl:");
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
                categoryRepository.deleteById(id);
            } else {
                logger.debug("deleteById() in CategoryServiceImpl received a null input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in deleteById() of AuditServiceImpl:");
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
    public List<Category> findAll(Pageable pageable) {
        List<Category> list;
        try{
            list = categoryRepository.findAll(pageable).getContent();
            if (list!=null){
                return list;
            }
        }catch(Exception exception){
            logger.debug("Exception in findAll() of AffectationServiceImpl:");
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
        List<Category> list;
        try{
            list = categoryRepository.findAll(pageable).getContent();
            if (list!=null){
                Type listType = new TypeToken<List<Dto>>() {}.getType();
                return modelMapper.map(list, listType);
            }
        }catch(Exception exception){
            logger.debug("Exception in findAllToDto() of CategoryServiceImpl:");
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
        return this.categoryRepository;
    }

}
