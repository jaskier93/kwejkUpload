package com.example.kwejk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gif {

    private String name;
    private String username;
    private Boolean favorite;
    private int categoryId;
    private LocalDateTime dateOfUpload;
    private String url;

    public Gif(String name, String username, Boolean favorite, int categoryId) {
        this.name = name;
        this.username = username;
        this.favorite = favorite;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Gif{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", favorite=" + favorite +
                ", categoryId=" + categoryId +
                ", dateOfUpload=" + dateOfUpload +
                ", url='" + url + '\'' +
                '}';
    }
}


