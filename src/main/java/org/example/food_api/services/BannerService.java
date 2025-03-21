package org.example.food_api.services;

import org.example.food_api.models.Banner;
import org.example.food_api.models.Banner;
import org.example.food_api.models.Banner;
import org.example.food_api.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service

public class BannerService {
    @Autowired
    BannerRepository bannerRepository;
    public ResponseEntity<List<Banner>> listAllBanner(){
        List<Banner> listBanner= bannerRepository.findAll();
        if(listBanner.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Banner>>(listBanner, HttpStatus.OK);
    }

    public Banner findBanner(@PathVariable("id") long id) {
        Banner contact= bannerRepository.findById(id);
        if(contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }
    public void save(Banner banner){
        bannerRepository.save(banner);
    }
    public ResponseEntity<Banner> updateBanner(Banner banner){
        Long bannerId = bannerRepository.findIdByBannerId(banner.getId());
        if (bannerId == null){
            String message = "banner id not found";
            System.out.println(message + bannerId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        bannerRepository.save(banner);
        return new ResponseEntity(banner, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteBanner(Long id){
        Long bannerId = bannerRepository.findIdByBannerId(id);
        if (bannerId == null){
            String message = "banner id not found";
            System.out.println(message + bannerId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        bannerRepository.deleteById(bannerId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
