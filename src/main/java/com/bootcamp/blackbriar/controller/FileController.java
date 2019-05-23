package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.AmazonClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/files")
public class FileController {
  @Autowired
  private AmazonClient amazonClient;

  @PostMapping
  public String uploadFileToS3(@RequestPart(value = "file") MultipartFile file) {
    return amazonClient.uploadFile(file);
  }
}
