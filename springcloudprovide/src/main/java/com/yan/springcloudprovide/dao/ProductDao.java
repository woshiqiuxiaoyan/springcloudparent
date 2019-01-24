package com.yan.springcloudprovide.dao;

import com.yan.springcloudprovide.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDao extends ElasticsearchRepository<ProductEntity, Long> {
}