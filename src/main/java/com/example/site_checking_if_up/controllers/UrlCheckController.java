package com.example.site_checking_if_up.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INVALID_URL = "Invalid URL!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";

        try {
            URL urlObj = URI.create(url).toURL();
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory != 2 || responseCodeCategory != 3) {
                returnMessage = SITE_IS_DOWN;
            } else {
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INVALID_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }

}
