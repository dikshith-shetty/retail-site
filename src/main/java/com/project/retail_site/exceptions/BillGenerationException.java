package com.project.retail_site.exceptions;

import java.util.List;

public class BillGenerationException extends RuntimeException {
    public BillGenerationException(Long userId, List<Long> products) {
        super("Unable to gererate bills for user: " + userId + " listed products: " + products.toString().substring(0,20)+"...");
    }
}