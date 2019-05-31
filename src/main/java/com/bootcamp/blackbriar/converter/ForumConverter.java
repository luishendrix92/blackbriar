package com.bootcamp.blackbriar.converter;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("converterForum")
public class ForumConverter {
    public List<ForumModel> convertList(List<ForumEntity> forums){
        List<ForumModel> mforums = new ArrayList<>();

        for(ForumEntity forum : forums){
            mforums.add(new ForumModel(forum));
        }
        return mforums;
    }
}
