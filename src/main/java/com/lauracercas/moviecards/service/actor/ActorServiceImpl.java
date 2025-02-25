package com.lauracercas.moviecards.service.actor;


import com.lauracercas.moviecards.model.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;


/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Service
public class ActorServiceImpl implements ActorService {

    //private final ActorJPA actorJPA;

    @Autowired
    RestTemplate template;
    String url = "https://moviecards-service-leiva.azurewebsites.net/actors";

    //public ActorServiceImpl(ActorJPA actorJPA) {
    //    this.actorJPA = actorJPA;
    //}

    @Override
    public List<Actor> getAllActors() {
        //return actorJPA.findAll();
        Actor[] actores = template.getForObject(url,
        Actor[].class);
        List<Actor> actoresList = Arrays.asList(actores);
        return actoresList;
    }

    @Override
    public Actor save(Actor actor) {
        //return actorJPA.save(actor);
        if (actor.getId() != null && actor.getId() > 0) {
            template.put(url, actor);
        } else {
            actor.setId(0);
            template.postForObject(url, actor, String.class);
        }
        return actor;
    }

    @Override
    public Actor getActorById(Integer actorId) {
        //return actorJPA.getById(actorId);
        Actor actor = template.getForObject(url+"/"+actorId, Actor.class);
        return actor;
    }
}
