package com.bootcamp.blackbriar.controller;

import com.bootcamp.blackbriar.service.forum.ForumService;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ForumController {

    @Autowired
    @Qualifier("service")
    ForumService service;

    @PostMapping("/foro")
    public boolean addForum(@RequestBody @Valid ForumEntity forum){

        return service.create(forum);
    }

    @PutMapping("/foro/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody ForumEntity forum){
        return service.update(forum, id);
    }

    @DeleteMapping("/foro/{id}")
    public boolean delete(@PathVariable("id") long id){
        return service.delete(id);
    }

    @GetMapping("/foro")
    public List<ForumModel> get(){
        return service.get();
    }

}
