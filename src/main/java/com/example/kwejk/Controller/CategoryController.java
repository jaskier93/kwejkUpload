package com.example.kwejk.Controller;

import com.example.kwejk.data.CategoryRepository;

import com.example.kwejk.data.GifRepository;
import com.example.kwejk.model.Category;
import com.example.kwejk.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import sun.net.www.content.image.gif;

import java.util.ArrayList;
import java.util.List;

import static com.example.kwejk.data.GifRepository.findGifByCategoryId;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    GifRepository gifRepository;

    @RequestMapping("/categories")
    public String gifCategories(ModelMap modelMap) {
        List<Category> categories = categoryRepository.getCategoryList();
        modelMap.put("categories", categories);
        return "categories";
    }

    @RequestMapping("/category/{id}")
    public String getGifsByCategory(@PathVariable int id, ModelMap modelMap) {
        List<Gif> gifs = gifRepository.findGifByCategoryId(id);
        modelMap.put("gifs", gifs);
        modelMap.put("category", categoryRepository.getCategoryById(id));
        return "category";
    }

}
