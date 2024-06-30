package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.AgnosticBuyableLicense;
import com.optimagrowth.license.model.LocalBuyableLicense;
import com.optimagrowth.license.service.LicenseService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/v1/license/shop")
public class BuyableLicenseController {
    @Autowired
    private LicenseService service;

    @GetMapping
    public ResponseEntity<LocalBuyableLicense> view(
            @PathParam("id") long id,
            @RequestHeader(value="Accept-Language", required = false) Locale locale){
        return ResponseEntity.ofNullable(service.viewBuyableLicense(locale, id));
    }
}
