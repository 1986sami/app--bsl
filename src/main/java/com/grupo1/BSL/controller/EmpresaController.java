package com.grupo1.BSL.controller;

import com.grupo1.BSL.models.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.grupo1.BSL.service.EmpresaService;

import java.util.List;

@RestController
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/enterprises")
    public List<Empresa> getAll(){

        return empresaService.getAll();
    }

    @GetMapping("/enterprises/{id}")
    public Empresa getById(@PathVariable("id") int id){
        return empresaService.getById(id);
    }

    @PostMapping("/enterprises")
    public Empresa save(@RequestBody Empresa empresa){
        return empresaService.save(empresa);
    }

    @PatchMapping("/enterprises/{id}")
    public Empresa update(@RequestBody Empresa empresa){
        return empresaService.update(empresa);
    }

    @DeleteMapping("/enterprises/{id}")
    public boolean delete(@PathVariable int id){
        return empresaService.delete(id);
    }
}
