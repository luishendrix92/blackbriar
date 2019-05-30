package com.bootcamp.blackbriar.business.forum;

import com.bootcamp.blackbriar.converter.ForumConverter;
import com.bootcamp.blackbriar.model.forum.ForumEntity;
import com.bootcamp.blackbriar.model.forum.ForumModel;
import com.bootcamp.blackbriar.repository.forum.ForumRepository;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceForum")
public class ForumService {
    @Autowired
    @Qualifier("repositoryForum")
    private ForumRepository repository;

    @Autowired
    @Qualifier("converterForum")
    private ForumConverter converter;

    private static final Log logger = LogFactory.getLog(ForumService.class);

    public boolean create(ForumEntity forum){
        logger.info("... Creando Foro ...");
        try{
            repository.save(forum);


            logger.info("... Foro creado! ...");
            return true;
        }catch(Exception e){
            logger.error("... Error al crear foro");
            return false;
        }
    }

    public boolean update(ForumEntity forum, long id){
        logger.info("... Actualizando Foro ...");
        try{
            ForumEntity forumNew = repository.findById(id);
            if ( forumNew == null)
            {
                logger.info("No forum with id = " + id);
            }
            forum.setId(id);
            repository.save(forum);
            logger.info("... Foro actualizado! ...");
            return true;
        }catch(Exception e){
            logger.error("... Error al actualizar foro ...");
            return false;
        }
    }

    public boolean delete(long id){
        logger.info("... Eliminando Foro ...");
        try{
            ForumEntity forum = repository.findById(id);
            repository.delete(forum);
            logger.info("... Foro elimado! ...");
            return true;
        }catch(Exception e){
            logger.error("... Error al eliminar foro ...");
            return false;
        }
    }

    public List<ForumModel> get(){
        return converter.convertList(repository.findAll());
    }

//    public List<ForumModel> getFromTitle(String title){
//        return converter.convertList(repository.findByTitle(title));
//    }
}

