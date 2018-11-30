package com.yan.springcloudprovide.redis;

import com.alibaba.fastjson.TypeReference;
import com.yan.springcloudprovide.redis.impl.GetDataCallBack;
import com.yan.springcloudprovide.redis.impl.PipelineCallBackWithResult;
import com.yan.springcloudprovide.redis.impl.PipelineCallBackWithoutResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public interface IRedisClient {
    String set(String bizkey,String nameSpace,String value,int time,int dbIndex);

    String set(String bizkey,String nameSpace,String value,String nxxx,String expx,long time,int dbIndex);

    String get(String bizkey,String nameSpace,GetDataCallBack<String> gbs,int dbIndex);

    Boolean exists(String bizkey,String nameSpace,int dbIndex);

    String type(String bizkey,String nameSpace,int dbIndex);

    Long expire(String bizkey,String nameSpace,int seconds,int dbIndex);

    Long expireAt(String bizkey,String nameSpace,long unixTime,int dbIndex);

    Long ttl(String bizkey,String nameSpace,int dbIndex);

    Boolean setbit(String bizkey,String nameSpace,long offset,boolean value,int dbIndex);

    Boolean getbit(String bizkey,String nameSpace,long offset,int dbIndex);

    Long setrange(String bizkey,String nameSpace,long offset,String value,int dbIndex);

    String getrange(String bizkey,String nameSpace,long startOffset,long endOffset,int dbIndex);

    String getSet(String bizkey,String nameSpace,String value,int dbIndex);

    Long setnx(String bizkey,String nameSpace,String value,int dbIndex);

    String setex(String bizkey,String nameSpace,int seconds,String value,int dbIndex);

    Long decrBy(String bizkey,String nameSpace,long integer,int dbIndex);

    Long decr(String bizkey,String nameSpace,int dbIndex);

    Long incrBy(String bizkey,String nameSpace,long integer,int dbIndex);

    Long incr(String bizkey,String nameSpace,int dbIndex);

    String substr(String bizkey,String nameSpace,int start,int end,int dbIndex);

    Long hset(String bizkey,String nameSpace,String field,String value,int dbIndex);

    String hget(String bizkey,String nameSpace,String field,GetDataCallBack<String> gbs,int dbIndex);

    Long hsetnx(String bizkey,String nameSpace,String field,String value,int dbIndex);

    String hmset(String bizkey,String nameSpace,Map<String,String> hash,int dbIndex);

    List<String> hmget(String bizkey,String nameSpace,int dbIndex,String... fields);

    Long hincrBy(String bizkey,String nameSpace,String field,long value,int dbIndex);

    Boolean hexists(String bizkey,String nameSpace,String field,int dbIndex);

    Long hdel(String bizkey,String nameSpace,int dbIndex,String... field);

    Long hlen(String bizkey,String nameSpace,int dbIndex);

    Set<String> hkeys(String bizkey,String nameSpace,int dbIndex);

    List<String> hvals(String bizkey,String nameSpace,int dbIndex);

    Map<String, String> hgetAll(String bizkey,String nameSpace,int dbIndex);

    Long llen(String bizkey,String nameSpace,int dbIndex);

    List<String> lrange(String bizkey,String nameSpace,long start,long end,int dbIndex);

    String ltrim(String bizkey,String nameSpace,long start,long end,int dbIndex);

    String lindex(String bizkey,String nameSpace,long index,int dbIndex);

    String lset(String bizkey,String nameSpace,long index,String value,int dbIndex);

    Long lrem(String bizkey,String nameSpace,long count,String value,int dbIndex);

    String lpop(String bizkey,String nameSpace,int dbIndex);

    String rpop(String bizkey,String nameSpace,int dbIndex);

    Long sadd(String bizkey,String nameSpace,int dbIndex,String... member);

    Set<String> smembers(String bizkey,String nameSpace,int dbIndex);

    Long srem(String bizkey,String nameSpace,int dbIndex,String... member);

    String spop(String bizkey,String nameSpace,int dbIndex);

    Long scard(String bizkey,String nameSpace,int dbIndex);

    Boolean sismember(String bizkey,String nameSpace,String member,int dbIndex);

    String srandmember(String bizkey,String nameSpace,int dbIndex);

    List<String> srandmember(String bizkey,String nameSpace,int count,int dbIndex);

    Long zadd(String bizkey,String nameSpace,double score,String member,int dbIndex);

    Set<String> zrange(String bizkey,String nameSpace,long start,long end,int dbIndex);

    Long zrem(String bizkey,String nameSpace,int dbIndex,String... member);

    Double zincrby(String bizkey,String nameSpace,double score,String member,int dbIndex);

    Long zrank(String bizkey,String nameSpace,String member,int dbIndex);

    Long zrevrank(String bizkey,String nameSpace,String member,int dbIndex);

    Set<String> zrevrange(String bizkey,String nameSpace,long start,long end,int dbIndex);

    Set<Tuple> zrangeWithScores(String bizkey,String nameSpace,long start,long end,int dbIndex);

    Set<Tuple> zrevrangeWithScores(String bizkey,String nameSpace,long start,long end,int dbIndex);

    Long zcard(String bizkey,String nameSpace,int dbIndex);

    Double zscore(String bizkey,String nameSpace,String member,int dbIndex);

    List<String> sort(String bizkey,String nameSpace,int dbIndex);

    List<String> sort(String bizkey,String nameSpace,SortingParams sortingParameters,int dbIndex);

    Long zcount(String bizkey,String nameSpace,double min,double max,int dbIndex);

    Set<String> zrangeByScore(String bizkey,String nameSpace,double min,double max,int dbIndex);

    Set<String> zrevrangeByScore(String bizkey,String nameSpace,double max,double min,int dbIndex);

    Set<String> zrangeByScore(String bizkey,String nameSpace,double min,double max,int offset,int count,int dbIndex);

    Set<String> zrevrangeByScore(String bizkey,String nameSpace,double max,double min,int offset,int count,int dbIndex);

    Long zremrangeByRank(String bizkey,String nameSpace,long start,long end,int dbIndex);

    Long zremrangeByScore(String bizkey,String nameSpace,double start,double end,int dbIndex);

    Set<Tuple> zrangeByScoreWithScores(String bizkey,String nameSpace,double min,double max,int dbIndex);

    Set<Tuple> zrevrangeByScoreWithScores(String bizkey,String nameSpace,double max,double min,int dbIndex);

    Set<Tuple> zrangeByScoreWithScores(String bizkey,String nameSpace,double min,double max,int offset,int count,int dbIndex);

    Set<Tuple> zrevrangeByScoreWithScores(String bizkey,String nameSpace,double max,double min,int offset,int count,int dbIndex);

    Long linsert(String bizkey,String nameSpace,BinaryClient.LIST_POSITION where,String pivot,String value,int dbIndex);

    Long del(String bizkey,String nameSpace,int dbIndex);

    Long lpush(String bizkey,String nameSpace,int dbIndex,String... fields);

    Long rpush(String bizkey,String nameSpace,int dbIndex,String... fields);

    Boolean setbit(String bizkey,String nameSpace,long offset,String value,int dbIndex);

    Long strlen(String bizkey,String nameSpace,int dbIndex);

    String echo(String string,int dbIndex);

    Long bitcount(String bizkey,String nameSpace,int dbIndex);

    Long bitcount(String bizkey,String nameSpace,long start,long end,int dbIndex);

    <T> String set(String bizkey,String nameSpace,T value,int time,int dbIndex);

    <T> String setex(String bizkey,String nameSpace,int time,T value,int dbIndex);

    <T> T get(String bizkey,String nameSpace,Class<T> value,GetDataCallBack<T> gbs,int dbIndex);

    <T> T get(String bizkey,String nameSpace,TypeReference<T> type,GetDataCallBack<T> gbs,int dbIndex);

    String mset(Map<String,String> bizkeyValues,String nameSpace,int dbIndex);

    List<String> mget(String[] bizkeys,String nameSpace,int dbIndex);

    <T> Long hsetObject(String bizkey,String nameSpace,String field,T value,int dbIndex);

    <T> T hgetObject(String bizkey,String nameSpace,String field,Class<T> value,GetDataCallBack<T> gbs,int dbIndex);

    <T> T hgetObject(String bizkey,String nameSpace,String field,TypeReference<T> type,GetDataCallBack<T> gbs,int dbIndex);

    void hdelObject(String bizkey,String nameSpace,int dbIndex,String... field);

    <T> Map<String, T> hgetAllObjects(String bizkey,String nameSpace,TypeReference<T> type,GetDataCallBack<T> gbs,int dbIndex);

    <T> Map<String, T> hgetAllObjects(String bizkey,String nameSpace,Class<T> value,GetDataCallBack<T> gbs,int dbIndex);

    Long del(String[] bizkeys,String nameSpace,int dbIndex);

    <T> List<T> hvalsObject(String bizkey,String nameSpace,TypeReference<T> type,int dbIndex);

    <T> List<T> hvalsObject(String bizkey,String nameSpace,Class<T> type,int dbIndex);

    List<Map.Entry<String, String>> hscan(String bizkey,String nameSpace,String match,int dbIndex);

    Set<String> sscan(String bizkey,String nameSpace,String match,int dbIndex);

    Set<String> scan(String nameSpace,String match,int dbIndex);

    Object patternDel(String pattern,String nameSpace,int dbIndex);

    Object eval(String script,List<String> bizkeys,String nameSpace,List<String> args,int dbIndex);

    Object eval(String script,int keyCount,List<String> params,String nameSpace,int dbIndex);

    Set<String> keys(String pattern,String nameSpace,int dbIndex);

    String rename(String oldKey,String newKey,String nameSpace,int dbIndex);

    <K, V> Map<K, V> pipelineWithResult(int dbIndex,PipelineCallBackWithResult<K,V> pipelineCallBack);

    void pipelineWithoutResult(int dbIndex,PipelineCallBackWithoutResult pipelineCallBack);

    Jedis getJedis();

    Long getTime();

    String scriptLoad(String script);

    Boolean scriptExists(String scriptSha);

    Object evalsha(String sha1,List<String> keys,List<String> args);

    Object evalsha(String sha1,int keyCount,String... params);
}
