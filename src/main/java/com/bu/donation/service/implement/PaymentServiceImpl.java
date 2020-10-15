package com.bu.donation.service.implement;


import com.bu.donation.entity.Payment;
import com.bu.donation.model.Dto;
import com.bu.donation.repository.PaymentRepository;
import com.bu.donation.service.PaymentService;
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
@Service("paymentService")

public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LogManager.getLogger(PaymentServiceImpl.class);


    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper ;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
        ModelMapper modelMapper1 = new ModelMapper();

        modelMapper1.addMappings(new PropertyMap<Dto, Payment>() {
            @Override
            protected void configure() {
                map().setIdPayment(source.getFirstIntField());
                map().setName(source.getFirstStringField());
            }
        });
        modelMapper1.addMappings(new PropertyMap<Payment, Dto>() {
            @Override
            protected void configure() {
                map().setFirstIntField(source.getIdPayment());
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
    public Payment saveOrUpdate(Payment u) {
        Payment payment = null;
        try{
            if(u!=null) {
                payment = paymentRepository.save(u);
            } else {
                logger.debug("saveOrUpdate() in PaymentServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdate() of PaymentServiceImpl:");
            logger.debug(exception);
        }
        return payment;
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
                Payment payment = paymentRepository.save(modelMapper.map(u, Payment.class));
                dto = modelMapper.map(payment, Dto.class);
            } else {
                logger.debug("saveOrUpdateDto() in PaymentServiceImpl received a null input parameter");
            }
        }catch (Exception exception){
            logger.debug("Exception in saveOrUpdateDto() of PaymentServiceImpl:");
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
    public List<Payment> saveAll(List<Payment> listOfObject) {
        List<Payment> ret;
        try{
            if((listOfObject!=null)&&(listOfObject.size()>0)){
                ret = paymentRepository.saveAll(listOfObject);
                if (ret!=null){
                    return ret;
                }
            } else {
                logger.debug("saveAll() in PaymentServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAll() of PaymentServiceImpl:");
            logger.debug(exception);
            logger.debug("Input list: ");
            logger.debug(listOfObject.stream().map(Payment::toString).collect(Collectors.joining("||")));
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
                paymentRepository.saveAll(listOfObject.stream().map(mgnDto -> modelMapper.map(mgnDto, Payment.class)).collect(Collectors.toList()));
            } else {
                logger.debug("saveAllDto() in PaymentServiceImpl received a null or a zero size input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in saveAllDto() of PaymentServiceImpl:");
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
                paymentRepository.deleteById(id);
            } else {
                logger.debug("deleteById() in PaymentServiceImpl received a null input parameter");
            }
        }catch(Exception exception){
            logger.debug("Exception in deleteById() of PaymentServiceImpl:");
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
    public List<Payment> findAll(Pageable pageable) {
        List<Payment> list;
        try{
            list = paymentRepository.findAll(pageable).getContent();
            if (list!=null){
                return list;
            }
        }catch(Exception exception){
            logger.debug("Exception in findAll() of PaymentServiceImpl:");
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
        List<Payment> list;
        try{
            list = paymentRepository.findAll(pageable).getContent();
            if (list!=null){
                Type listType = new TypeToken<List<Dto>>() {}.getType();
                return modelMapper.map(list, listType);
            }
        }catch(Exception exception){
            logger.debug("Exception in findAllToDto() of PaymentServiceImpl:");
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
        return this.paymentRepository;
    }

}
