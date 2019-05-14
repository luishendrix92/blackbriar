/**
 * User: Alexis M. Gutierrez Kinto
 * Date: 10/05/19
 */

package com.bootcamp.blackbriar.converter;

import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("converter")
public class Converter {
    public List<ForumModel> convertList(List<ForumEntity> forums){
        List<ForumModel> mforums = new ArrayList<>();

        for(ForumEntity forum : forums){
            mforums.add(new ForumModel(forum));
        }
        return mforums;
    }
}
