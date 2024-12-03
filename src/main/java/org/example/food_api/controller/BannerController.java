package org.example.food_api.controller;

import org.example.food_api.models.Banner;
import org.example.food_api.models.Banner;
import org.example.food_api.models.Banner;
import org.example.food_api.repository.BannerRepository;
import org.example.food_api.services.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banner")

public class BannerController {
    @Autowired
    BannerService bannerService;
    public static Logger logger = LoggerFactory.getLogger(BannerController.class);
    @Autowired
    private BannerRepository bannerRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Banner>> listAllBanner(){
        return bannerService.listAllBanner();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBanner(@PathVariable("id") long id) {
        return bannerService.deleteBanner(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllBanner() {
        try {
            bannerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Banner> updateBanner(@RequestBody Banner banner) {
        return bannerService.updateBanner(banner);
    }
    @PostMapping("/create")
    public void createBanner(@RequestBody Banner banner) {
        bannerService.save(banner);
    }
}
