package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ParamInfo {
    private DetailRule rule;

    private List<ParamInfoDetail> set;

    private String images[];
}
