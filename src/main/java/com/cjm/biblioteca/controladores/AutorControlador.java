package com.cjm.biblioteca.controladores;

import com.cjm.biblioteca.excepciones.BibliotecaException;
import com.cjm.biblioteca.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar(){
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        try {
            autorServicio.crearAutor(nombre);
        } catch (BibliotecaException e) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, e);
            return "autor_form.html";
        }
        return "index.html";
    }
}