package com.bootcamp.blackbriar.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonClient {
  private AmazonS3 s3Client;

  @Value("${amazonProperties.endpointUrl}")
  private String endpointUrl;

  @Value("${amazonProperties.bucketName}")
  private String bucketName;

  @Value("${amazonProperties.accessKey}")
  private String accessKey;

  @Value("${amazonProperties.secretKey}")
  private String secretKey;

  @PostConstruct
  private void initializeAmazon() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    this.s3Client = new AmazonS3Client(credentials);
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convertedFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convertedFile);

    fos.write(file.getBytes());
    fos.close();

    return convertedFile;
  }

  private String generateFileName(MultipartFile multiPart) {
    return new Date().getTime()
      + "-"
      + multiPart.getOriginalFilename().replace(" ", "_");
  }

  private void uploadFileTos3bucket(String fileName, File file) {
    s3Client
      .putObject(new PutObjectRequest(bucketName, fileName, file)
      .withCannedAcl(CannedAccessControlList.PublicRead));
  }

  public String uploadFile(MultipartFile multipartFile) {

    String fileUrl = "";
    try {
        File file = convertMultiPartToFile(multipartFile);
        String fileName = generateFileName(multipartFile);
        fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        uploadFileTos3bucket(fileName, file);
        file.delete();
    } catch (Exception e) {
       e.printStackTrace();
    }
    return fileUrl;
  }
}
