package com.grupo1.BSL.models;

import javax.persistence.*;

@Entity
@Table(name = "Empleado")
public class Empleado{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String nombre;
    private String correo;
    @ManyToOne
    @JoinColumn(name ="empresa_id")
    private Empresa empresa;
    private String rol;
    private String password;
    private boolean estado;


    public Empleado() {
    }

    public Empleado(String nombre, String correo, Empresa empresa, String rol, String password, boolean estado) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
        this.password = password;
        this.estado = estado;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEmpresa(){
        return empresa;
    }
    public void setEmpresa(Empresa empresa){
        this.empresa =empresa;

    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}