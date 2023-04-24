package com.xercodes.RESTApitest;

import com.xercodes.RESTApitest.api.PostRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//interacts with other application
@RestController
@RequestMapping("/posts")//all requests sent should be handled by this controller
public class PostController {


    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/{id}")
    //returning ResponseEntity returns process code as well(200-ok)
    public ResponseEntity getSinglePost(@PathVariable("id") int id) {
        Optional<Post> res = postRepository.findById(id);
        if (res.isPresent()) {
            Post post = res.get();
            return new ResponseEntity(post, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    //returning ResponseEntity returns process code as well(200-ok)
    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @PostMapping(value = "/newPost", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    //input for this method is json hence the consumes parameter value
    public Post addNewPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PostMapping(value = "/newPosts", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addNewPosts(@RequestBody List<Post> posts) {
        List<Post> savedPosts = (List<Post>) postRepository.saveAll(posts);
        return ResponseEntity.ok("Posts Added Successfully:"+savedPosts.size());
    }

}
