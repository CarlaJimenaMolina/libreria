package com.cjm.biblioteca.servicios;

import com.cjm.biblioteca.entidades.Autor;
import com.cjm.biblioteca.excepciones.BibliotecaException;
import com.cjm.biblioteca.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor (String nombre) throws BibliotecaException {

        verificar(nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutores(){

        List<Autor> autores = new ArrayList<>();

        autores = autorRepositorio.findAll();

        return autores;
    }

    public void modificarAutor(String id, String nombre) throws BibliotecaException {

        verificar(nombre);

        Optional<Autor> autorOptional = autorRepositorio.findById(id);

        if (autorOptional.isPresent()){

            Autor autor = autorOptional.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }

    private void verificar(String nombre) throws BibliotecaException{
        if (nombre == null || nombre.isEmpty()){
            throw new BibliotecaException("El nombre no puede ser nulo ni estar vacío");
        }
    }
}
