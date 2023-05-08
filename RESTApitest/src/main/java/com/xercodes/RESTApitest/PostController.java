package com.xercodes.RESTApitest;

import com.xercodes.RESTApitest.api.PostRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
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
        return ResponseEntity.ok("Posts Added Successfully:" + savedPosts.size());
    }


    @PutMapping(value = "/updatePost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") int id, @RequestBody Post post) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setUserId(post.getUserId());
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            List<Post> savedPosts = Collections.singletonList(postRepository.save(existingPost));
            return new ResponseEntity(savedPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletePost/{id}")
    public ResponseEntity deletePost(@PathVariable("id") int id) {
        Post existingPost2 = postRepository.findById(id).orElse(null);
        if(existingPost2!=null){
           postRepository.delete(existingPost2);
           return ResponseEntity.ok("Post:"+id+" deleted successfully");
        }else{
            return ResponseEntity.ok("Post:"+id+" deletion failed");
        }
    }

    @PostMapping("/upload/")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) throws IOException{
        String imagePath = postRepository.saveImage(file);
        if(imagePath!=null){
            return ResponseEntity.ok("Image Uploaded Successfully: "+imagePath);
        }else{
            return ResponseEntity.ok("Failed to upload image");
        }
    }


}
