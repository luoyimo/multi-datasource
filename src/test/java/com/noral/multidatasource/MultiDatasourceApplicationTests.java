package com.noral.multidatasource;

import com.noral.multidatasource.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = MultiDatasourceApplication.class)
public class MultiDatasourceApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    public void testForChangeDataSource() throws Exception {
        System.out.println("master------>" + personService.getPerson());
        //Person{id=1, name='datasource1'}
        System.out.println("slave------>" + personService.getPersonSlave());

        personService.insertTo();
        //Person{id=2, name='datasource2'}
    }


}
