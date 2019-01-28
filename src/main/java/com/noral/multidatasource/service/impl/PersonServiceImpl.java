package com.noral.multidatasource.service.impl;

import com.noral.multidatasource.configuration.DataSourceContextHolder;
import com.noral.multidatasource.configuration.annotation.NormalDataSource;
import com.noral.multidatasource.configuration.annotation.SlaveDataSource;
import com.noral.multidatasource.entity.Person;
import com.noral.multidatasource.mapper.SlavePersonMapper;
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

//    @Autowired
//    private MasterPersonMapper masterPersonMapper;

    @Override
    public Person getPerson() {
//        Person person = masterPersonMapper.selectByPrimaryKey(1);
//        return person;
        return null;
    }


    @Override
    public Person getPersonSlave() {
        Person person = slavePersonMapper.selectByPrimaryKey(2);
        return person;
    }

    @Override
    @Transactional(transactionManager = "xatx", rollbackFor = Exception.class)
    @SlaveDataSource
    public int insertTo() throws Exception {
        Person person = new Person();
//        person.setId(3);
        DataSourceContextHolder.setSlave();
        person.setName("一致性");
        slavePersonMapper.insert(person);
        boolean flag = true;
        if (flag) {
            throw new Exception("一致性问题");
        }
        DataSourceContextHolder.setMaster();
        slavePersonMapper.insert(person);
        return 0;

    }


    @Override
    @Transactional(transactionManager = "dataSourceTransactionManager", rollbackFor = Exception.class)
    @NormalDataSource
    public int insertTo2() throws Exception {
        Person person = new Person();
        person.setName("一致性");
        slavePersonMapper.insert(person);
        return 0;

    }

    @Override
    @Transactional(transactionManager = "xatx", rollbackFor = Exception.class)
    @SlaveDataSource
    public int insertTo1() throws Exception {
        Person person = new Person();
        person.setName("一致性");
        slavePersonMapper.insert(person);
        return 0;
    }
}
