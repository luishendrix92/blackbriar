package com.bootcamp.blackbriar.api.forum;

import com.bootcamp.blackbriar.business.forum.ForumSettingsService;
import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import com.bootcamp.blackbriar.model.forum.ForumSettingsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ForumSettingsController {

    @Autowired
    @Qualifier("serviceForumSettings")
    ForumSettingsService service;

    @PostMapping("/forumSettings")
    public boolean addForumSettings(@RequestBody @Valid ForumSettingsEntity forumSettings){

        return service.create(forumSettings);
    }

    @PutMapping("/forumSettings/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody ForumSettingsEntity forumSettings){
        return service.update(forumSettings, id);
    }

    @DeleteMapping("/forumSettings/{id}")
    public boolean delete(@PathVariable("id") long id){
        return service.delete(id);
    }

    @GetMapping("/forumSettings")
    public List<ForumSettingsModel> get(){
        return service.get();
    }
}
