package com.grupo1.BSL.controller;


import com.grupo1.BSL.models.Empleado;
import com.grupo1.BSL.models.Empresa;
import com.grupo1.BSL.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//controller usuario


@RestController
public class UsuarioController {

    @Autowired      //referenciando un servicio para hacer uso de ese.
    UsuarioService usuarioService;

    @GetMapping("/users")
    public List<Empleado> consultarTodos(){
        return  usuarioService.getAll();
    }

    @PostMapping("/users")
    public  Empleado crearUsuario (@RequestBody Empleado empleado){      //@RequestBody enviar un empleado o usuario ala tabla
        return usuarioService.save(empleado);
    }

    @DeleteMapping ("/user/{id}")
    public boolean eliminarUsuario(@PathVariable("id") int id) {
        return usuarioService.delete(id);
    }

    @PatchMapping ("/user/{id}")
    public Empleado editarUsuario(@RequestBody Empleado empleado){
        return usuarioService.update(empleado);
    }

    @GetMapping  ("/user/{id}")
    public Empleado obtenerPorId(@PathVariable("id") int id) {
        return usuarioService.getById(id);
    }
 }
