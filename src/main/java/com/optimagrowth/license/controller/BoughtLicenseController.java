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
            @PathParam("id") Long id, @PathParam("owner_id") Long owner_id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        if (id == null || owner_id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ofNullable(this.service.createLicense(locale, id, owner_id));
    }

    @GetMapping
    public ResponseEntity<LocalBoughtLicense> read(
            @PathParam("id") Long id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ofNullable(this.service.readBoughtLicense(locale, id));
    }

    @PutMapping
    public ResponseEntity<LocalBoughtLicense> update(
            @PathParam("id") Long id, @PathParam("new_owner_id") Long new_owner_id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        if (id == null || new_owner_id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ofNullable(this.service.updateBoughtLicense(locale, id, new_owner_id));
    }

    @DeleteMapping
    public ResponseEntity<LocalBoughtLicense> deactivate(
            @PathParam("id") Long id,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ofNullable(this.service.deactivateBoughtLicense(locale, id));
    }
}
