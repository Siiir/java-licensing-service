package com.optimagrowth.license.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Getter
@Setter
public class BuyableLicense {
    // Fields
    protected final long id;
    @lombok.NonNull
    protected String name;
    @lombok.NonNull
    protected String description;

    @Override
    public boolean equals(Object obj){
        if (obj instanceof BuyableLicense other){
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