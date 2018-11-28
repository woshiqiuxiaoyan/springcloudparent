package com.yan.springcloudprovide.mapper;

import com.yan.springcloudprovide.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper {
    User query(@Param("id") String id);
}
