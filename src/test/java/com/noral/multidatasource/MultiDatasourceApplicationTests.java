package com.noral.multidatasource;

import com.noral.multidatasource.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDatasourceApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    public void testForChangeDataSource() {
        System.out.println(personService.getPerson());
        System.out.println(personService.getPersonSlave());
        System.out.println(personService.getPersonMaster());
    }

}
