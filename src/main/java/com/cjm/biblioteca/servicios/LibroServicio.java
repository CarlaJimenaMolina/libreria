package com.cjm.biblioteca.servicios;

import com.cjm.biblioteca.entidades.Autor;
import com.cjm.biblioteca.entidades.Editorial;
import com.cjm.biblioteca.entidades.Libro;
import com.cjm.biblioteca.excepciones.BibliotecaException;
import com.cjm.biblioteca.repositorios.AutorRepositorio;
import com.cjm.biblioteca.repositorios.EditorialRepositorio;
import com.cjm.biblioteca.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws BibliotecaException{

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);

        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        libro.setAlta(new Date());

        libro.setAutor(autor);

        libro.setEditorial(editorial);

        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibros(){

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro (Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws BibliotecaException {

        this.validar(isbn, titulo, idAutor, idEditorial, ejemplares);

        Optional<Libro> libroOpcional = libroRepositorio.findById(isbn);

        if (libroOpcional.isPresent()){

            Libro libro = libroOpcional.get();

            libro.setTitulo(titulo);

            libro.setEjemplares(ejemplares);

            Optional<Autor> autorOptional = autorRepositorio.findById(idAutor);

            if (autorOptional.isPresent()){

                Autor autor = autorOptional.get();

                libro.setAutor(autor);
            }

            Optional<Editorial> editorialOptional = editorialRepositorio.findById(idEditorial);

            if (editorialOptional.isPresent()){

                Editorial editorial = editorialOptional.get();

                libro.setEditorial(editorial);
            }

            libroRepositorio.save(libro);

        }

    }

    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws BibliotecaException{
        if (isbn == null){
            throw new BibliotecaException("El ISBN no puede ser nulo");
        }
        if (titulo.isEmpty() || titulo == null){
            throw new BibliotecaException("El título no puede ser nulo ni estar vacío");
        }
        if (ejemplares == null){
            throw new BibliotecaException("La cantidad de ejemplares no puede ser nulo");
        }
        if (idAutor == null || idAutor.isEmpty()){
            throw new BibliotecaException("El ID de autor no puede ser nulo ni estar vacío");
        }
        if (idEditorial == null || idEditorial.isEmpty()){
            throw new BibliotecaException("El ID de la editorial no puede ser nulo ni estar vacío");
        }
    }

}
