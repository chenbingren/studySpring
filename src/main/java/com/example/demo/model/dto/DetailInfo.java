package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DetailInfo {
    private String desc;

    private List<DetailImage> detailImage;

}
