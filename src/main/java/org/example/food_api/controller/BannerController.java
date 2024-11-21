package org.example.food_api.controller;

import org.example.food_api.models.Banner;
import org.example.food_api.services.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banner")

public class BannerController {
    @Autowired
    BannerService bannerService;
    public static Logger logger = LoggerFactory.getLogger(BannerController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Banner>> listAllBanner(){
        return bannerService.listAllBanner();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Banner findBanner(@PathVariable("id") long id) {
        return bannerService.findBanner(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Banner findBannnerById(@RequestParam("id") long id) {
        return bannerService.findBanner(id);
    }
}
