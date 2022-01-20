package com.sbrf.reboot.generics;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository<T extends BankProduct> {

    private Map<Long, T> productList = new HashMap<>();

    public void addProduct(Long productId, T product) {
        productList.put(productId, product);
    }

    public Long getClientIdByProductId(Long productId) {
        if (productList.containsKey(productId))
            return productList.get(productId).getClientId();
        else
            throw new RuntimeException("Несуществующий productId");
    }


}
