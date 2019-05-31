package com.bootcamp.blackbriar.business.forum;

import com.bootcamp.blackbriar.converter.ForumSettingsConverter;
import com.bootcamp.blackbriar.model.forum.ForumSettingsEntity;
import com.bootcamp.blackbriar.model.forum.ForumSettingsModel;
import com.bootcamp.blackbriar.repository.forum.ForumSettingsRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceForumSettings")
public class ForumSettingsService {

    @Autowired
    @Qualifier("repositoryForumSettings")
    private ForumSettingsRepository repository;

    @Autowired
    @Qualifier("converterForumSettings")
    private ForumSettingsConverter converter;


    private static final Log logger = LogFactory.getLog(ForumSettingsService.class);


    public boolean create(ForumSettingsEntity forumSettings){
        try{
            repository.save(forumSettings);
            //
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean update(ForumSettingsEntity forumSettings, long id){
        logger.info("... Actualizando Foro Settings ...");
        try{
            ForumSettingsEntity forumSettingsNew = repository.findById(id);
            if ( forumSettingsNew == null)
            {
                logger.info("No forum with id = " + id);
            }
            forumSettings.setId(id);
            repository.save(forumSettings);
            logger.info("... Foro Settings actualizado! ...");
            return true;
        }catch(Exception e){
            logger.error("... Error al actualizar foro settings ...");
            return false;
        }
    }

    public boolean delete(long id){
        logger.info("... Eliminando Foro Settings ...");
        try{
            ForumSettingsEntity forumSettings = repository.findById(id);
            repository.delete(forumSettings);
            logger.info("... Foro Settings elimado! ...");
            return true;
        }catch(Exception e){
            logger.error("... Error al eliminar foro settings...");
            return false;
        }
    }

    public List<ForumSettingsModel> get(){
        return converter.convertList(repository.findAll());
    }
}
