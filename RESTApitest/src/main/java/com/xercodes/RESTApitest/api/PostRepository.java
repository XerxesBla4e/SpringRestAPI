package com.xercodes.RESTApitest.api;

import com.xercodes.RESTApitest.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface PostRepository extends CrudRepository<Post, Integer> {
    public default String saveImage(MultipartFile file) throws IOException {
        final String imagePath = "E:/give/uploads/"; //path
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();

        FileOutputStream outputStream = new FileOutputStream(imagePath+fileName);
       // Path path = Paths.get("uploads/" + fileName);
        outputStream.write(bytes);

        return imagePath+fileName;
    }


}
