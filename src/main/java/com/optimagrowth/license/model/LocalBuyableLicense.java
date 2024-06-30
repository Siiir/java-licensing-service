package com.optimagrowth.license.model;

import lombok.ToString;

@ToString
public class LocalBuyableLicense extends BuyableLicense {
    LocalBuyableLicense(long id, String name, String description){
        super(id, name, description);
    }
}
