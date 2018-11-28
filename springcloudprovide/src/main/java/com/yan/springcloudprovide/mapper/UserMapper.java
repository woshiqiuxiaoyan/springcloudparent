package com.yan.springcloudprovide.mapper;

import com.yan.springcloudprovide.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User Sel(int id);
}
