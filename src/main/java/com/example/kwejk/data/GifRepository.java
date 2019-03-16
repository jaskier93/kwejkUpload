package com.example.kwejk.data;

import com.example.kwejk.model.Gif;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GifRepository {

    public final static List<Gif> ALL_GIFS = Arrays.asList(

            new Gif("android-explosion", "mols", true, 1),
            new Gif("ben-and-mike", "mika", false, 2),
            new Gif("book-dominos", "nen", true, 1),
            new Gif("compiler-bot", "bot", false, 0),
            new Gif("cowboy-coder", "username", true, 0),
            new Gif("infinite-andrew", "username", true, 1)
    );

    public final static List<Gif> getFav() {
        List<Gif> FAV_GIFS = new ArrayList<>();
        for (int i = 0; i < ALL_GIFS.size(); i++) {
            if (ALL_GIFS.get(i).getFavorite() == true) {
                FAV_GIFS.add(ALL_GIFS.get(i));
            }
        }
        return FAV_GIFS;
    }

    // public final static List<Gif> FAV_GIFS = Arrays.asList();

    public static List<Gif> getAllGifs() {
        return ALL_GIFS;

    }

    public final static List<Gif> findGifByCategoryId(int id) {
        List<Gif> CAT_GIFS = new ArrayList<>();
        for (int i = 0; i < ALL_GIFS.size(); i++) {

            if (ALL_GIFS.get(i).getCategoryId() == id) {
                CAT_GIFS.add(ALL_GIFS.get(i));
            }
        }
        return CAT_GIFS;
    }


    public final static Gif searchGif(String q) {
        for (int i = 0; i < ALL_GIFS.size(); i++) {

            if (q.equals(ALL_GIFS.get(i).getName())) {
                return ALL_GIFS.get(i);
            }
        }
        return null;
    }
}
