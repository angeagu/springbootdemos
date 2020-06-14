package com.example.springbootdemos.bbdd.mongo.config;

import com.example.springbootdemos.bbdd.mongo.model.Alumno;
import com.example.springbootdemos.bbdd.mongo.repository.ColegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class AlumnoCascadeSaveListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private ColegioRepository colegioRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if((source instanceof  Alumno) && ((Alumno)source).getColegio()!=null) {
            colegioRepository.save(((Alumno)source).getColegio());
        }
    }
}
