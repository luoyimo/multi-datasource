package com.noral.multidatasource.configuration.annotation;

import java.lang.annotation.*;

/**
 * @Author hu
 * @Description:
 * @Date Create In 10:47 2018/11/29 0029
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MasterDataSource {
}
