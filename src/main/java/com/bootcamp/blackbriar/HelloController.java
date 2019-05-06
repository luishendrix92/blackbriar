package com.bootcamp.blackbriar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
  @GetMapping("/")
  public List<String> index() {
    List<String> things = new ArrayList<>();

    things.add("Hola");
    things.add("Mundo");
    things.add("Cruel");

    return things;
  }
}
