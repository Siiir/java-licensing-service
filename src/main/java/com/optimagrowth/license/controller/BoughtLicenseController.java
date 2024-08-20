package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.AgnosticBoughtLicense;
import com.optimagrowth.license.model.LocalBoughtLicense;
import com.optimagrowth.license.service.LicenseService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/v1/license")
public class BoughtLicenseController {
    @Autowired
    private LicenseService service;

    @PostMapping
    public ResponseEntity<LocalBoughtLicense> create(
            @RequestParam("base_id") Long buyable_license_id, @RequestParam("owner_id") Long owner_id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ofNullable(this.service.createLicense(locale, buyable_license_id, owner_id));
    }

    @GetMapping
    public ResponseEntity<LocalBoughtLicense> read(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ofNullable(this.service.readBoughtLicense(locale, id));
    }

    @PutMapping
    public ResponseEntity<LocalBoughtLicense> update(
            @RequestParam("id") Long id, @RequestParam("new_owner_id") Long new_owner_id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ofNullable(this.service.updateBoughtLicense(locale, id, new_owner_id));
    }

    @DeleteMapping
    public ResponseEntity<LocalBoughtLicense> deactivate(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ofNullable(this.service.deactivateBoughtLicense(locale, id));
    }
}
