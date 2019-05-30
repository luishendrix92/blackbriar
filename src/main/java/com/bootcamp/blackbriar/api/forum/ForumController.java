/**
 * User: Alexis M. Gutierrez Kinto
 * Date: 13/05/19
 */

package com.bootcamp.blackbriar.api.forum;

import com.bootcamp.blackbriar.business.forum.ForumService;
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
    @Qualifier("serviceForum")
    ForumService service;

    @PostMapping("/forum")
    public boolean addForum(@RequestBody @Valid ForumEntity forum){

        return service.create(forum);
    }

    @PutMapping("/forum/{id}")
    public boolean update(@PathVariable("id") long id, @RequestBody ForumEntity forum){
        return service.update(forum, id);
    }

    @DeleteMapping("/forum/{id}")
    public boolean delete(@PathVariable("id") long id){
        return service.delete(id);
    }

    @GetMapping("/forum")
    public List<ForumModel> get(){
        return service.get();
    }

}
