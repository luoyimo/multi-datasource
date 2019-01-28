package com.noral.multidatasource;

import com.noral.multidatasource.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(classes = MultiDatasourceApplication.class)
public class MultiDatasourceApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    public void testForChangeDataSource() throws Exception {


        personService.insertTo();


        StopWatch watch1 = new StopWatch();
        watch1.start();
        for (int i = 0; i < 10000; i++) {

            personService.insertTo1();
        }
        watch1.stop();
        System.err.println("xa take ----->" + watch1);


        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < 10000; i++) {

            personService.insertTo2();
        }
        watch.stop();
        System.out.println("normal  take ----> " + watch);


    }


}
