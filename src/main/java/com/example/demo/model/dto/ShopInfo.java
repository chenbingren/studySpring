package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author cbr
 */
@Data
public class ShopInfo {
    private String name;
    private String shopLogo;
    private Integer cFans;
    private Integer cSells;
    private Integer cGoods;

    private List<DetailScore> score;

    private List<DetailService> services;
}
