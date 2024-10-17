package com.entrega.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {
    private Integer id;
    private String title;
    private Integer year;
    private String genre;
}
