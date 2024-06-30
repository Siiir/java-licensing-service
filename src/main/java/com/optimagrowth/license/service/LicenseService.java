package com.optimagrowth.license.service;

import com.optimagrowth.license.model.AgnosticBoughtLicense;
import com.optimagrowth.license.model.AgnosticBuyableLicense;
import com.optimagrowth.license.model.LocalBoughtLicense;
import com.optimagrowth.license.model.LocalBuyableLicense;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class LicenseService {
    // FixMe: Make this state external. Not a part of microservice instance.
    final static Map<Long, AgnosticBuyableLicense> buyableLicenses = new HashMap<>();
    // FixMe: Make this state external. Not a part of microservice instance.
    final static Map<Long, AgnosticBoughtLicense> boughtLicenses = new HashMap<>();

    @Autowired
    MessageSource msgSrc;

    static {
        AgnosticBuyableLicense[] licensePrefabs = {
                new AgnosticBuyableLicense("partial_license.name", "partial_license.description"),
                new AgnosticBuyableLicense("full_license.name", "full_license.description")
        };
        for(var license : licensePrefabs){
            buyableLicenses.put(license.getId(), license);
        }
    }

    // CRUD-C: Creating
    public LocalBoughtLicense createLicense(Locale locale, long targetLicenseId, long futureOwnerId) {
        var targetLicense = buyableLicenses.get(targetLicenseId);
        if (targetLicense == null){
            return null;
        }
        @NonNull var boughtLicense = new AgnosticBoughtLicense(targetLicense, futureOwnerId);
        boughtLicenses.put(boughtLicense.getId(), boughtLicense);
        return boughtLicense.makeLocal(msgSrc, locale);
    }
    // CRUD-R: Reading
    public LocalBuyableLicense viewBuyableLicense(Locale locale, Long id) {
        AgnosticBuyableLicense agnosticLicense = buyableLicenses.get(id);
        if (agnosticLicense == null){
            return null;
        }
        return agnosticLicense.makeLocal(msgSrc, locale);
    }
    public LocalBoughtLicense readBoughtLicense(Locale locale, long licenseId) {
        AgnosticBoughtLicense agnosticLicense = boughtLicenses.get(licenseId);
        if (agnosticLicense == null){
            return null;
        }
        return agnosticLicense.makeLocal(msgSrc, locale);
    }
    // CRUD-U: Updating
    public LocalBoughtLicense updateBoughtLicense(Locale locale, long licenseId, long newOwnerId) {
        var targetLicense = boughtLicenses.get(licenseId);
        if (targetLicense == null){
            return null;
        }
        targetLicense.setOwnerId(newOwnerId);
        return targetLicense.makeLocal(msgSrc, locale);
    }
    // CRUD-D: Deactivating
    public LocalBoughtLicense deactivateBoughtLicense(Locale locale, long licenseId) {
        AgnosticBoughtLicense removed = boughtLicenses.remove(licenseId);
        if (removed == null){return null;}
        return removed.makeLocal(msgSrc, locale);
    }
}
