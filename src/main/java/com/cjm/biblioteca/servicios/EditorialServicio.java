package com.cjm.biblioteca.servicios;

import com.cjm.biblioteca.entidades.Editorial;
import com.cjm.biblioteca.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial (String nombre){
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales(){

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }

    public void modificarEditorial( String idEditorial, String nombreEditorial){

        Optional<Editorial> editorialOptional = editorialRepositorio.findById(idEditorial);

        if (editorialOptional.isPresent()){

            Editorial editorial = editorialOptional.get();

            editorial.setNombre(nombreEditorial);

            editorialRepositorio.save(editorial);

        }
    }
}
