package com.yan.springcloudprovide.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author: QXY
 * @classDescribe:
 * @createTime: 2019/1/23
 * @version: 1.0
 */
@Data
@ToString
@Document (indexName = "ecommerce", type = "product")
public class ProductEntity {
    private Long id;
    private Integer age;
    private String name;
    private String desc;
    private Double price;
    private String title;
}
