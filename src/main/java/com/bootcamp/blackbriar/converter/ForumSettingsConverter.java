package com.bootcamp.blackbriar.converter;

import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import com.bootcamp.blackbriar.model.forum.ForumSettingsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("converterForumSettings")
public class ForumSettingsConverter {
    public List<ForumSettingsModel> convertList(List<ForumSettingsEntity> forumSettings){
        List<ForumSettingsModel> mforumSettings = new ArrayList<>();

        for(ForumSettingsEntity forum : forumSettings){
            mforumSettings.add(new ForumSettingsModel(forum));
        }
        return mforumSettings;
    }
}
