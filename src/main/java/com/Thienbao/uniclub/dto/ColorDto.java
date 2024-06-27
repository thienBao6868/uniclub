package com.Thienbao.uniclub.dto;

import lombok.Data;

import java.util.List;

@Data
public class ColorDto {
    private int id;
    private String name;
    private List<String> images;
}
