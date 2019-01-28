package com.noral.multidatasource.service;


import com.noral.multidatasource.entity.Person;

/**
 * @Author hu
 * @Description:
 * @Date Create In 18:32 2018/11/28 0028
 */
public interface PersonService {

    Person getPerson();

    Person getPersonSlave();

    int insertTo() throws Exception;

    int insertTo2() throws Exception;


    int insertTo1() throws Exception;

}
