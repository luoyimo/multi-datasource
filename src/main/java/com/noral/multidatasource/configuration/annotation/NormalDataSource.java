package com.noral.multidatasource.configuration.annotation;

import java.lang.annotation.*;

/**
 * @Author hu
 * @Description:
 * @Date Create In 11:22 2019/1/28 0028
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface NormalDataSource {
}
