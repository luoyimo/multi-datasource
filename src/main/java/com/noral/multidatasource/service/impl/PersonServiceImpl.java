package com.noral.multidatasource.service.impl;

import com.noral.multidatasource.entity.Person;
import com.noral.multidatasource.mapper.master.MasterPersonMapper;
import com.noral.multidatasource.mapper.slave.SlavePersonMapper;
import com.noral.multidatasource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SlavePersonMapper slavePersonMapper;

    @Autowired
    private MasterPersonMapper masterPersonMapper;

    @Override
    public Person getPerson() {
        Person person = masterPersonMapper.selectByPrimaryKey(1);
        return person;
    }


    @Override
    public Person getPersonSlave() {
        Person person = slavePersonMapper.selectByPrimaryKey(2);
        return person;
    }

    @Override
    @Transactional(transactionManager = "xatx", rollbackFor = Exception.class)
    public int insertTo() throws Exception {
        Person person = new Person();
        person.setId(3);
        person.setName("一致性");
        slavePersonMapper.insert(person);
        if (true) {
            throw new Exception("一致性问题");
        }
        masterPersonMapper.insert(person);
        return 0;

    }


}
