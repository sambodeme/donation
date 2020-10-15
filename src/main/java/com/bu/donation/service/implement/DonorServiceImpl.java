package com.bu.donation.service.implement;


import com.bu.donation.entity.Donor;
import com.bu.donation.model.Dto;
import com.bu.donation.repository.DonorRepository;
import com.bu.donation.service.DonorService;
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
@Service("donorService")

public class DonorServiceImpl implements DonorService {

    private static final Logger logger = LogManager.getLogger(DonorServiceImpl.class);


    private final DonorRepository donorRepository;
    private final ModelMapper modelMapper ;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository){
        this.donorRepository = donorRepository;
        ModelMapper modelMapper1 = new ModelMapper();

        modelMapper1.addMappings(new PropertyMap<Dto, Donor>() {
            @Override
            protected void configure() {
                map().setIdDonor(source.getFirstIntField());
                map().setName(source.getFirstStringField());
                map().setAddress(source.getSecondStringField());
                map().setEmail(source.getThirdStringField());
                map().setPhone(source.getFourthStringField());
                map().setType(source.getFifthStringField());
            }
        });
        modelMapper1.addMappings(new PropertyMap<Donor, Dto>() {
            @Override
            protected void configure() {
                map().setFirstIntField(source.getIdDonor());
                map().setFirstStringField(source.getName());
                map().setSecondStringField(source.getAddress());
                map().setThirdStringField(source.getEmail());
                map().setFourthStringField(source.getPhone());
                map().setFifthStringField(source.getType());
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
    public Donor saveOrUpdate(Donor u) {
        Donor donor = null;
        try{
            if(u!=null) {
                donor = donorRepository.save(u);
            } else {
                logger.debug("saveOrUpdate() in DonorServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdate() of DonorServiceImpl:");
            logger.debug(exception);
        }
        return donor;
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
                Donor donor = donorRepository.save(modelMapper.map(u, Donor.class));
                dto = modelMapper.map(donor, Dto.class);
            } else {
                logger.debug("saveOrUpdateDto() in DonorServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdateDto() of DonorServiceImpl:");
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
    public List<Donor> saveAll(List<Donor> listOfObject) {
        List<Donor> ret;
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                ret = donorRepository.saveAll(listOfObject);
                if (ret!=null){
                    return ret;
                }
            } else {
                logger.debug("saveAll() in DonorServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAll() of DonorServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Donor::toString).collect(Collectors.joining("||")));
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
                donorRepository.saveAll(listOfObject.stream().map(mgnDto -> modelMapper.map(mgnDto, Donor.class)).collect(Collectors.toList()));
            } else {
                logger.debug("saveAllDto() in DonorServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAllDto() of DonorServiceImpl:");
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
                donorRepository.deleteById(id);
            } else {
                logger.debug("deleteById() in DonorServiceImpl received a null input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in deleteById() of DonorServiceImpl:");
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
    public List<Donor> findAll(Pageable pageable) {
        List<Donor> list;
        try{
            list = donorRepository.findAll(pageable).getContent();
            if (list!=null){
                return list;
            }
        }catch(Exception exception){
            logger.debug("Exception in findAll() of DonorServiceImpl:");
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
        List<Donor> list;
        try{
            list = donorRepository.findAll(pageable).getContent();
            if (list!=null){
                Type listType = new TypeToken<List<Dto>>() {}.getType();
                return modelMapper.map(list, listType);
            }
        }catch(Exception exception){
            logger.debug("Exception in findAllToDto() of DonorServiceImpl:");
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
        return this.donorRepository;
    }

}
