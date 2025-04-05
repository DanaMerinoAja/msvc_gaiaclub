package org.soygaia.msvc.gaiaclub.models.dtos;

import org.soygaia.msvc.gaiaclub.models.beans.Measure;

import java.util.List;

public class ProductoDTO {
    private Long id;//Precio de venta
    private String slug;
    private String name;
    private Integer rating;
    private Long reviews;
    private String availability;
    private Measure measureShop;
    private Long prdId;
    private List<String> images;
}
