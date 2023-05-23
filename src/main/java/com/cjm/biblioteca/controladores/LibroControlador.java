package com.cjm.biblioteca.controladores;

import com.cjm.biblioteca.excepciones.BibliotecaException;
import com.cjm.biblioteca.servicios.AutorServicio;
import com.cjm.biblioteca.servicios.EditorialServicio;
import com.cjm.biblioteca.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    public String registrar(){
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                           @RequestParam(required = false) Integer ejemplares,
                           @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo){
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor,idEditorial );

            modelo.put("éxito","El libro fue cargado correctamente");
        } catch (BibliotecaException e) {
            modelo.put("error",e.getMessage());
            return "libro_form.html";
        }
        return "index.html";
    }

}
