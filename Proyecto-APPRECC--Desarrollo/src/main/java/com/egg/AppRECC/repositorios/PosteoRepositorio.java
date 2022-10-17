/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.AppRECC.repositorios;

import com.egg.AppRECC.entidades.Posteo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PC
 */

@Repository
public interface PosteoRepositorio extends JpaRepository<Posteo, Long>{
     
    @Query("SELECT n FROM Posteo n WHERE n.titulo = :titulo")
    public Posteo buscarPorTitulo(@Param("titulo") String titulo);
    
    /* @Query("SELECT n FROM Posteo n WHERE n.borrado!=true ORDER BY n.fecha desc")
    public List<Posteo> listarposteos();
     */
}
