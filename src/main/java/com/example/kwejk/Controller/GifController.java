package com.example.kwejk.Controller;

import com.example.kwejk.data.GifRepository;
import com.example.kwejk.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GifController {
    @Autowired
    GifRepository gifRepository;

    @RequestMapping("/")
    public String listGifs(@RequestParam(required = false) String q, ModelMap modelMap) {
        List<Gif> gifs = gifRepository.getAllGifs();
        if (q == null) {
            modelMap.put("gifs", gifs);
        } else {
            modelMap.put("gifs", gifRepository.searchGif(q));
        }
        return "home";
    }

    @RequestMapping("/favorites")
    public String gifFavourites(ModelMap modelMap) {
        List<Gif> favgif = gifRepository.getFav();
        modelMap.put("gifs", favgif);
        return "favorites";
    }

}
