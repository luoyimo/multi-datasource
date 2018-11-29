package com.noral.multidatasource.service;

import com.noral.multidatasource.person.Person;

/**
 * @Author hu
 * @Description:
 * @Date Create In 18:32 2018/11/28 0028
 */
public interface PersonService {

    Person getPerson();

    Person getPersonSlave();

    Person getPersonMaster();
}
