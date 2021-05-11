/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.S3Connect.S3Project;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BucketCreation {

    String bucketName = "sauravanandbucket";

    @Autowired
    AmazonS3 s3client;
    
    @PostMapping(value = "/create")
    private String CreateBucket(){
        
     if(s3client.doesBucketExistV2(bucketName))
     {
         return "Bucket exists";
     }
     else
     {
         try{
             s3client.createBucket(bucketName);
             return "bucket created";
             
         }catch(AmazonS3Exception e){
             
             return "bucket cannot be created";
             
         }
     }
        
        
    }
    
    @PostMapping(value = "/upload")
    private String FileUpload(){
        
        try{
            
            s3client.putObject(bucketName, "bucketkey1", new File("C:/Users/Saurav/Pictures/Camera Roll/mq2.jpg"));
            return "object placed";
            
            
        }catch(AmazonS3Exception e){
            
            return "exception";
            
        }
        
    }
    
    @PostMapping(value = "/download")
    private String DownloadObject(){
        
        try{
            S3Object s = s3client.getObject(bucketName,"bucketkey1");
        S3ObjectInputStream stream = s.getObjectContent();
        FileOutputStream output = new FileOutputStream(new File("C:/Users/Saurav/Pictures/Camera Roll/mq22.jpg"));
        byte[] read_buf = new byte[1024];
        int read_len = 0;
        
        while ((read_len = stream.read(read_buf)) > 0) {
        output.write(read_buf, 0, read_len);
        }
        
        stream.close();
        output.close();
        return "success";
        
        
        }catch(FileNotFoundException e){
            return "exception";
        }catch(IOException e){
            return "exception";
        }
        
        
        
    }
    
}
