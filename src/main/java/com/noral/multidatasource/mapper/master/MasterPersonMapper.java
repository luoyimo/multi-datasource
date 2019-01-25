package com.noral.multidatasource.mapper.master;

import com.noral.multidatasource.entity.Person;

public interface MasterPersonMapper {
    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);
}