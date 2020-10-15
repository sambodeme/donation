package com.bu.donation.service.implement;


import com.bu.donation.entity.Audit;
import com.bu.donation.entity.Donation;
import com.bu.donation.model.Dto;
import com.bu.donation.repository.AuditRepository;
import com.bu.donation.repository.DonationRepository;
import com.bu.donation.service.AuditService;
import com.bu.donation.service.DonationService;
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
@Service("donationService")

public class DonationServiceImpl implements DonationService {

    private static final Logger logger = LogManager.getLogger(DonationServiceImpl.class);


    private final DonationRepository donationRepository;
    private final ModelMapper modelMapper ;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository){
        this.donationRepository = donationRepository;
        ModelMapper modelMapper1 = new ModelMapper();

        modelMapper1.addMappings(new PropertyMap<Dto, Donation>() {
            @Override
            protected void configure() {
                map().setIdDonation(source.getFirstIntField());
                map().setAmount(source.getFirstBigDecimalField());
                map().setDate(source.getFirstDateField());
                map().setIddonor(source.getSecondIntField());
                map().setIdpayment(source.getThirdIntField());
            }
        });
        modelMapper1.addMappings(new PropertyMap<Donation, Dto>() {
            @Override
            protected void configure() {
                map().setFirstIntField(source.getIdDonation());
                map().setFirstBigDecimalField(source.getAmount());
                map().setFirstDateField(source.getDate());
                map().setSecondIntField(source.getIddonor());
                map().setThirdIntField(source.getIdpayment());
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
    public Donation saveOrUpdate(Donation u) {
        Donation donation = null;
        try{
            if(u!=null) {
                donation = donationRepository.save(u);
            } else {
                logger.debug("saveOrUpdate() in DonationServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdate() of DonationServiceImpl:");
            logger.debug(exception);
        }
        return donation;
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
                Donation donation = donationRepository.save(modelMapper.map(u, Donation.class));
                dto = modelMapper.map(donation, Dto.class);
            } else {
                logger.debug("saveOrUpdateDto() in DonationServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdateDto() of DonationServiceImpl:");
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
    public List<Donation> saveAll(List<Donation> listOfObject) {
        List<Donation> ret;
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                ret = donationRepository.saveAll(listOfObject);
                if (ret!=null){
                    return ret;
                }
            } else {
                logger.debug("saveAll() in DonationServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAll() of DonationServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Donation::toString).collect(Collectors.joining("||")));
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
                donationRepository.saveAll(listOfObject.stream().map(mgnDto -> modelMapper.map(mgnDto, Donation.class)).collect(Collectors.toList()));
            } else {
                logger.debug("saveAllDto() in DonationServiceImpl received a null or a zero size input parameter");
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
                donationRepository.deleteById(id);
            } else {
                logger.debug("deleteById() in DonationServiceImpl received a null input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in deleteById() of DonationServiceImpl:");
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
    public List<Donation> findAll(Pageable pageable) {
        List<Donation> list;
        try{
            list = donationRepository.findAll(pageable).getContent();
            if (list!=null){
                return list;
            }
        }catch(Exception exception){
            logger.debug("Exception in findAll() of DonationServiceImpl:");
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
        List<Donation> list;
        try{
            list = donationRepository.findAll(pageable).getContent();
            if (list!=null){
                Type listType = new TypeToken<List<Dto>>() {}.getType();
                return modelMapper.map(list, listType);
            }
        }catch(Exception exception){
            logger.debug("Exception in findAllToDto() of DonationServiceImpl:");
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
        return this.donationRepository;
    }

}
