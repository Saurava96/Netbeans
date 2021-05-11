/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.S3Connect.S3Project;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class Configuration {

    
    @Bean
    public AmazonS3 S3Builder(){
        
        AWSCredentials credentials = new BasicAWSCredentials("AKIA6OSGLTSYDSTVANVP"
                , "8MKuNjwc305OYfUengWzNTNv9+WSJLY6QNkMFhi4");
        
        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).build();
        
        return client;
        
    }
    
    
}
