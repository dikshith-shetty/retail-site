package com.project.retail_site.controllers.request;

import java.util.List;

import lombok.Data;

@Data
public class BillRequest {
    private Long userId;
    private List<Long> productIds;
}
