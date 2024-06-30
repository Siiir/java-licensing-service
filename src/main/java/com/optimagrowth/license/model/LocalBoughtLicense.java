package com.optimagrowth.license.model;

import lombok.ToString;

@ToString
public class LocalBoughtLicense extends BoughtLicense {
    LocalBoughtLicense(long id, LocalBuyableLicense baseLicense, long ownerId) {
        super(id, baseLicense, ownerId);
    }
}
