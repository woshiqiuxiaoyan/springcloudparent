package com.yan.springcloudservice.redis;

import java.util.List;
import java.util.Map;

/**
 * lua脚本编写
 */
public interface ILuaScriptService {

    void eval(String luaKey,List<String> keys,List<String> args);

    String eval2string(String luaKey,List<String> keys,List<String> args);

    Map<String,String> eval2map(String luaKey,List<String> keys,List<String> args);

    <T> T eval2bean(String luaKey,List<String> keys,List<String> args,Class<T> clz);

    <T> List<T> eval2list(String luaKey,List<String> keys,List<String> args,Class<T> clz);

}
