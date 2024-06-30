package com.optimagrowth.license.model;

import lombok.NonNull;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public final class AgnosticBuyableLicense extends BuyableLicense{
    // Associated static values
    private static final AtomicLong OLD_ID = new AtomicLong(0);
    private static final long MAX_ID = Long.MAX_VALUE;

    public AgnosticBuyableLicense(@NonNull String name, @NonNull String description) {
        super(generateNextId(), name, description);
    }

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
    public LocalBuyableLicense makeLocal(MessageSource msgSrc, Locale locale){
        return new LocalBuyableLicense(this.id, msgSrc.getMessage(this.name, null, locale), msgSrc.getMessage(this.description, null, locale));
    }
}