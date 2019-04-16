package com.example.kwejk.services;

import com.example.kwejk.data.GifRepository;
import com.example.kwejk.model.Gif;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
@Slf4j
public class GifService {

    private static Set<Gif> gifLinksDatabase = new TreeSet<>();

    public void save(String url, int category){
        if(willItBeFirstGif()){
         gifLinksDatabase.addAll(GifRepository.getAllGifs());
        }
        Gif gif = new Gif();
        gif.setCategoryId(category);
        String name = url.substring(url.length()-5, url.length()-1);
        gif.setName(name);
        gif.setDateOfUpload(LocalDateTime.now());
        gif.setUrl(url);
        log.info(gif.toString());
        gifLinksDatabase.add(gif);
    }

    private boolean willItBeFirstGif(){
        return gifLinksDatabase.size() < 1;
    }

    public Set<Gif> getGifLinksDatabase(){
        return ImmutableSet.copyOf(gifLinksDatabase);
    }
}
