package com.noral.multidatasource.service.impl;

import com.noral.multidatasource.configuration.annotation.MasterDataSource;
import com.noral.multidatasource.configuration.annotation.SlaveDataSource;
import com.noral.multidatasource.person.Person;
import com.noral.multidatasource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author hu
 * @Description:
 * @Date Create In 18:33 2018/11/28 0028
 */
@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public  Person getPerson() {
        Person person = jdbcTemplate.queryForObject("select  * from person", new BeanPropertyRowMapper<>(Person.class));
        return person;
    }


    @Override
    @SlaveDataSource
    public Person getPersonSlave() {
        Person person = jdbcTemplate.queryForObject("select  * from person", new BeanPropertyRowMapper<>(Person.class));
        return person;
    }

    @Override
    @MasterDataSource
    @Transactional
    public Person getPersonMaster() {
        Person person = jdbcTemplate.queryForObject("select  * from person", new BeanPropertyRowMapper<>(Person.class));
        return person;
    }

}
