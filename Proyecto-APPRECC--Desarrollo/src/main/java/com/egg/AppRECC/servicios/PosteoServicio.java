/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.AppRECC.servicios;

import com.egg.AppRECC.entidades.Posteo;
import com.egg.AppRECC.excepciones.MiException;
import com.egg.AppRECC.repositorios.PosteoRepositorio;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import net.iharder.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PC
 */
@Service
public class PosteoServicio {
    
    @Autowired
    private PosteoRepositorio posteorepositorio;
    
    public void crearPosteo(String titulo, String cuerpo, MultipartFile imagen) throws MiException{
        validar(titulo, cuerpo);
        
        Posteo posteo = new Posteo();
        
        if (!imagen.isEmpty()) {
            try {
                posteo.setImagen(Base64.encodeBytes(imagen.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        posteo.setTitulo(titulo);
        posteo.setCuerpo(cuerpo);
        posteo.setFecha(LocalDate.now());
        
        posteorepositorio.save(posteo);
    }
    
    
    public List<Posteo> listarPosteos() {

        List<Posteo> posteo = new ArrayList();

        posteo = posteorepositorio.findAll();
        

        return posteo;
    }
    
    
    public Optional<Posteo> findById(Long id) {
        return posteorepositorio.findById(id);
    }
    
    
    private void validar( String titulo, String cuerpo) throws MiException {

        

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacio");
        }

        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("el cuerpo de la actividad no puede ser nulo o estar vacio");
        }

    }
}
