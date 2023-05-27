package com.cjm.biblioteca.controladores;

import com.cjm.biblioteca.entidades.Editorial;
import com.cjm.biblioteca.excepciones.BibliotecaException;
import com.cjm.biblioteca.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    public String registrar(){
        return "editorial_form";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, ModelMap modelo){

        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("exito","El libro fue cargado correctamente");
        } catch (BibliotecaException e) {
            modelo.put("error", e.getMessage());
            return "editorial_form";
        }
        return "index.html";

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_list.html";
    }
}
