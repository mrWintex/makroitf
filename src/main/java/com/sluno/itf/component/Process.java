package com.sluno.itf.component;

import com.sluno.itf.model.ResultData;
import com.sluno.itf.model.StoreData;
import com.sluno.itf.model.StoreRawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Process {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @KafkaListener(topics = "#{@topicUtils.firstTopic}", groupId = "#{@topicUtils.firstTopic}.group", properties = {JsonDeserializer.VALUE_DEFAULT_TYPE+"com.sluno.itf.model.StoreRawData"})
    @SendTo("#{@topicUtils.secondTopic}")
    public StoreData ListenToNewEvents(StoreRawData message){
        return new StoreData(
            message.getStoreNo(), message.getStoreName(), message.getPhone(), message.getEmail(), message.getOrderEmail()
        );
    }

    @KafkaListener(topics = "#{@topicUtils.secondTopic}", groupId = "#{@topicUtils.secondTopic}", properties = {JsonDeserializer.VALUE_DEFAULT_TYPE+"=com.sluno.itf.model.StoreData"})
    @SendTo("#{@topicUtils.thirdTopic}")
    public ResultData ListenToValidatedEvents(StoreData message){
        try{
            jdbcTemplate.update(
                    "INSERT INTO makro_stores (makro_store_no, store_name, store_phone, contact_email, order_email) VALUES (:storeNo, :storeName, :storePhone, :contactEmail, :orderEmail)" +
                            "ON CONFLICT ON CONSTRAINT makro_stores_makro_store_no_key DO UPDATE SET store_name = :storeName, store_phone = :storePhone, contact_email = :contactEmail, order_email = :orderEmail WHERE makro_stores.makro_store_no = :storeNo;",
                    Map.of("storeNo", message.getStoreNo(), "storeName", message.getStoreName(), "storePhone", message.getPhone(), "contactEmail", message.getEmail(), "orderEmail", message.getOrderEmail()));
        } catch(Exception e){
            return new ResultData(message.getStoreNo(), "ERROR", e.getMessage());
        }
        return new ResultData(message.getStoreNo(), "OK", "Database updated!");
    }
}
