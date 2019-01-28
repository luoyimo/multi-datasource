package com.noral.multidatasource.configuration.aspect;

import com.noral.multidatasource.configuration.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Author hu
 * @Description:
 * @Date Create In 11:00 2018/11/29 0029
 */
@EnableAspectJAutoProxy
@Aspect
@Component
public class DataSourceChangeAop {

    private final String slaveAop = "@annotation(com.noral.multidatasource.configuration.annotation.SlaveDataSource)";

    private final String masterAop = "@annotation(com.noral.multidatasource.configuration.annotation.MasterDataSource)";

    private final String normalAop = "@annotation(com.noral.multidatasource.configuration.annotation.NormalDataSource)";

    @Before(slaveAop)
    public void changeToSlaveDataSource() {
        DataSourceContextHolder.setSlave();
    }


    @Before(masterAop)
    public void changeToMasterDataSource() {
        DataSourceContextHolder.setMaster();
    }


    @Before(normalAop)
    public void changeToNormalDataSource() {
        DataSourceContextHolder.setNormal();
    }

    @After("(" + slaveAop + "||" + masterAop + "||" + normalAop + ")")
    public void after() {
        DataSourceContextHolder.clear();
    }
}
