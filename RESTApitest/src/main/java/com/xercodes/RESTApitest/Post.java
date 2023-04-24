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
    private final int userId;
    private final String title;
    private final String body;
}
