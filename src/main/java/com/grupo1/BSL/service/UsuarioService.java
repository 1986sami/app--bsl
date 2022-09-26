package com.grupo1.BSL.service;

import com.grupo1.BSL.models.Empleado;
import com.grupo1.BSL.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Empleado> getAll(){
        return (List<Empleado>) usuarioRepository.findAll();
    }

    public Empleado getById(int id){
        return usuarioRepository.findById(id).get();
    }

    public Empleado save(Empleado empleado){
        return usuarioRepository.save(empleado);
    }

    public boolean delete(int id){
        usuarioRepository.deleteById(id);

        if (usuarioRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    public Empleado update(Empleado empleado){
        return usuarioRepository.save(empleado);
    }

    public ArrayList<Empleado> getByEmpresa(Integer id){
        return usuarioRepository.findByEmpresa(id);
    }

}
