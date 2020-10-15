package com.bu.donation.service.implement;


import com.bu.donation.entity.Audit;
import com.bu.donation.model.Dto;
import com.bu.donation.repository.AuditRepository;
import com.bu.donation.service.AuditService;
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
@Service("auditService")

public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);


    private final AuditRepository auditRepository;
    private final ModelMapper modelMapper ;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository){
        this.auditRepository = auditRepository;
        ModelMapper modelMapper1 = new ModelMapper();

        modelMapper1.addMappings(new PropertyMap<Dto, Audit>() {
            @Override
            protected void configure() {
                map().setIdAudit(source.getFirstIntField());
                map().setAmount(source.getFirstBigDecimalField());
                map().setDate(source.getFirstDateField());
                map().setIddonor(source.getSecondIntField());
                map().setIdpayment(source.getThirdIntField());
                map().setUserDetails(source.getFirstStringField());
            }
        });
        modelMapper1.addMappings(new PropertyMap<Audit, Dto>() {
            @Override
            protected void configure() {
                map().setFirstIntField(source.getIdAudit());
                map().setFirstBigDecimalField(source.getAmount());
                map().setFirstDateField(source.getDate());
                map().setSecondIntField(source.getIddonor());
                map().setThirdIntField(source.getIdpayment());
                map().setFirstStringField(source.getUserDetails());
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
    public Audit saveOrUpdate(Audit u) {
        Audit audit = null;
        try{
            if(u!=null) {
                audit = auditRepository.save(u);
            } else {
                logger.debug("saveOrUpdate() in AuditServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdate() of AuditServiceImpl:");
            logger.debug(exception);
        }
        return audit;
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
                Audit audit = auditRepository.save(modelMapper.map(u, Audit.class));
                dto = modelMapper.map(audit, Dto.class);
            } else {
                logger.debug("saveOrUpdateDto() in AuditServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdateDto() of AuditServiceImpl:");
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
    public List<Audit> saveAll(List<Audit> listOfObject) {
        List<Audit> ret;
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                ret = auditRepository.saveAll(listOfObject);
                if (ret!=null){
                    return ret;
                }
            } else {
                logger.debug("saveAll() in AuditServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAll() of AuditServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Audit::toString).collect(Collectors.joining("||")));
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
                auditRepository.saveAll(listOfObject.stream().map(mgnDto -> modelMapper.map(mgnDto, Audit.class)).collect(Collectors.toList()));
            } else {
                logger.debug("saveAllDto() in AuditServiceImpl received a null or a zero size input parameter");
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
                auditRepository.deleteById(id);
            } else {
                logger.debug("deleteById() in AuditServiceImpl received a null input parameter");
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
    public List<Audit> findAll(Pageable pageable) {
        List<Audit> list;
        try{
            list = auditRepository.findAll(pageable).getContent();
            if (list!=null){
                return list;
            }
        }catch(Exception exception){
            logger.debug("Exception in findAll() of AuditServiceImpl:");
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
        List<Audit> list;
        try{
            list = auditRepository.findAll(pageable).getContent();
            if (list!=null){
                Type listType = new TypeToken<List<Dto>>() {}.getType();
                return modelMapper.map(list, listType);
            }
        }catch(Exception exception){
            logger.debug("Exception in findAllToDto() of AuditServiceImpl:");
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
        return this.auditRepository;
    }

}
