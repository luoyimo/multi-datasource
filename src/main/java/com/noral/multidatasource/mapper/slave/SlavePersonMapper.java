package com.noral.multidatasource.mapper.slave;

import com.noral.multidatasource.entity.Person;

public interface SlavePersonMapper {
    int insert(Person record);

    int insertSelective(Person record);


    Person selectByPrimaryKey(Integer id);
}