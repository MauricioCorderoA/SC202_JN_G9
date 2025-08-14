/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

/**
 *
 * @author Maytan
 */
public class Administrador {
    
    //ATRIBUTOS
    private final String idAdministrador;
    private final String contrasenaAdmin;
    private String nombreAdmin;
    
    //CONSTRUCTOR
    public Administrador(String idAdministrador, String contrasenaAdmin) {
        this.idAdministrador = idAdministrador;
        this.contrasenaAdmin = contrasenaAdmin;
    }
    
    //ENCAPSULADORES
    public String getIdAdministrador() {
        return idAdministrador;
    }
    
    public String getNombreAdmin(){
        return nombreAdmin;
    }
    
    public void setNombreAdmin(String nombreAdmin){
        this.nombreAdmin=nombreAdmin;
    }

    public String getContrasenaAdmin() {
        return contrasenaAdmin;
    }
    
    //METODO PARA ENSEÑAR EL USUARIO AL INICIO DEL PROGRAMA
    public void MostrarDatosAdmin(){
        
        System.out.println("ID: "+idAdministrador);
        System.out.println("Contrasena: "+contrasenaAdmin);
        System.out.println("-- -- --- -- --");
    }

    boolean autenticar(String usuario, String clave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setNombre(String showInputDialog) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
