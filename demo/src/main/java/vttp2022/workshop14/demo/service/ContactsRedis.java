package vttp2022.workshop14.demo.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp2022.workshop14.demo.model.Contact;



@Service
public class ContactsRedis implements ContactsRepo {
    private static final Logger logger = LoggerFactory.getLogger(ContactsRedis.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(final Contact ctc) {
        redisTemplate.opsForList().rightPush(ctc.getId(), ctc.getName());
        redisTemplate.opsForList().rightPush(ctc.getId(), ctc.getEmail());
        redisTemplate.opsForList().rightPush(ctc.getId(), ctc.getPhoneNumber());
    }

    @Override
    public Contact findById(final String contactId) {
        String name = (String) redisTemplate.opsForList().index(contactId, 0);
        String email = (String) redisTemplate.opsForList().index(contactId, 1);
        Integer phoneNumber = (Integer) redisTemplate.opsForList().index(contactId, 2);
        logger.info(">>> name " + name);
        logger.info(">>> email " + email);
        logger.info(">>> phoneNumber " + phoneNumber);
        
        Contact ct = new Contact();
        ct.setId(contactId);
        ct.setName(name);
        ct.setEmail(email);
        ct.setPhoneNumber(phoneNumber.intValue());

        return ct;
    }

      }
