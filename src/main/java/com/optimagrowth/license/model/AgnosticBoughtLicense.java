package com.optimagrowth.license.model;

import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public class AgnosticBoughtLicense extends BoughtLicense{
    // Associated static values
    private static final AtomicLong OLD_ID = new AtomicLong(0);
    private static final long MAX_ID = Long.MAX_VALUE;

    // Associated functions
    private static long generateNextId() {
        for (;;){
            long oldId = OLD_ID.get();
            if (oldId == MAX_ID) {
                throw new RuntimeException("The new ID would overflow upon generation." +
                        " Help: You might want to enforce `low id consumption` policy," +
                        " so as to protect the id-generating method from being used to often." +
                        " You could also change the generation logic to allow more ids or implement id recycling.");
            }
            long newId = oldId + 1;
            long witnessedId = OLD_ID.compareAndExchange(oldId, newId);
            if (witnessedId == oldId){
                return newId;
            }
        }
    }
    // Instance methods
    // CRUD-C
    public AgnosticBoughtLicense(AgnosticBuyableLicense baseLicense, long ownerId){
        super(generateNextId(), baseLicense, ownerId);
    }
    // CRUD-R: Getters
    @Override
    public AgnosticBuyableLicense getBaseLicense(){
        return (AgnosticBuyableLicense) this.baseLicense;
    }
    // CRUD-R: Converters
    public LocalBoughtLicense makeLocal(MessageSource msgSrc, Locale locale){
        return new LocalBoughtLicense(this.id, this.getBaseLicense().makeLocal(msgSrc, locale), this.ownerId);
    }
    // CRUD-U: Setters
    @Override
    public void setBaseLicense(BuyableLicense license){
        if (license instanceof AgnosticBuyableLicense agnosticLicense){
            this.baseLicense = agnosticLicense;
        }else{
            throw new IllegalArgumentException("argument should be of class %s".formatted(AgnosticBuyableLicense.class));
        }
    }
}