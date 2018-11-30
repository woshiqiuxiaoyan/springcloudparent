package com.yan.common.constants;

/**
 * <p>类名:RedisConstants </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/30
 * 创建时间: 16:32
 */
public class RedisConstants {

    public static final String COLON =":" ;
    public static final String LUA_KEY = "LUA";

    public interface  Test{
        String testName="testname:";
        String testHsetName="hsetname";
    }

    public interface  NameSpace{
        String SpringCloudParent="SPRINGCLOUDPARENT";
    }

    public static final int TEST_DBINDEX = 3;
    public static final int DEFAULT_DBINDEX =4;
    public static final String DEFAULT_NAME_SPACE="COMYAN";


}
