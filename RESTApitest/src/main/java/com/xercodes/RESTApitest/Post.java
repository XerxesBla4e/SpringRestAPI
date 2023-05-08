package com.xercodes.RESTApitest;
/*model same as domain*/

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
//help avoid write boilerplate code like creating constructor/getters/setters
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true )
@RequiredArgsConstructor
public class Post {
    @Id //automatically generate random unique ids for requests
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;
    private int userId;
    private String title;
    private String body;
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setBody(String body) {
        this.body = body;
    }
}
