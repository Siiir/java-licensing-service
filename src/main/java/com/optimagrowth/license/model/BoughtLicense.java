package com.optimagrowth.license.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BoughtLicense {
    // Fields
    protected final long id;
    @lombok.NonNull
    protected BuyableLicense baseLicense;
    protected long ownerId;

    @Override
    public boolean equals(Object obj){
        if (obj instanceof BoughtLicense other){
            return this.id == other.id;
        }else {
            return false;
        }
    }
    @Override
    public int hashCode(){
        return (int) this.id;
    }
}