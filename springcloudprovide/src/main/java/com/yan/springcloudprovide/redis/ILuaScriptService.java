package com.yan.springcloudprovide.redis;

import java.util.List;
import java.util.Map;

/**
 * @author: zhongjie
 * date: 2018/7/18 0018
 * time: 17:37
 * description:处理redis lua脚本
 */
public interface ILuaScriptService {

    public void eval(String luaKey,List<String> keys,List<String> args);

    public String eval2string(String luaKey,List<String> keys,List<String> args);

    public Map<String,String> eval2map(String luaKey,List<String> keys,List<String> args);

    public <T> T eval2bean(String luaKey,List<String> keys,List<String> args,Class<T> clz);

    public <T> List<T> eval2list(String luaKey,List<String> keys,List<String> args,Class<T> clz);

}
