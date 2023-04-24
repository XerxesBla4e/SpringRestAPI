package com.xercodes.RESTApitest.api;

import com.xercodes.RESTApitest.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Integer> {

}
