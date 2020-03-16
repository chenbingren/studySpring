package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author cbr
 */
@Data
public class CommentInfo {
    private List images;
    private String content;
    private Long created;
    private String style;
    private CommentInfoUser user;

}
