package com.noral.multidatasource.configuration;

/**
 * @Author hu
 * @Description:
 * @Date Create In 10:24 2018/11/29 0029
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> LOCAL = new ThreadLocal<String>();
    public static final String MASTER = "master";
    public static final String SLAVE = "slave";

    public static ThreadLocal<String> getLocal() {
        return LOCAL;
    }

    public static void setMaster() {
        LOCAL.set(MASTER);
    }

    public static void setSlave() {
        LOCAL.set(SLAVE);
    }


    public static String getMaterOrSlave() {
        return LOCAL.get();
    }

    public static void clear() {
        LOCAL.remove();
    }
}
