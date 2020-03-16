package com.example.demo.model.dto;

import lombok.Data;

/**
 * @author cbr
 * 用于商品详情信息存储
 */
@Data
public class ItemInfo {
    private String title;
    private String desc;
    private String price;
    private String oldPrice;
    private String discountDesc;
    private String highNowPrice;
}
