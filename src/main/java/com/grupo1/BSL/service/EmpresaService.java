package com.grupo1.BSL.service;

import com.grupo1.BSL.models.Empresa;
import com.grupo1.BSL.repository.EmpresaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRespository empresaRespository;

    public List<Empresa> getAll(){
        return (List<Empresa>) empresaRespository.findAll();
    }

    public Empresa getById(int id){
        return empresaRespository.findById(id).get();
    }

    public Empresa save(Empresa empresa){
        return empresaRespository.save(empresa);
    }

    public Empresa update(Empresa empresa){
        return empresaRespository.save(empresa);
    }

    public boolean delete(int id){
        empresaRespository.deleteById(id);

        if(empresaRespository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

}
