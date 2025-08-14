/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

/**
 *
 * @author Maytan
 */
public class Cliente {
    
    //ATRIBUTOS
    private final String idCliente;
    private final String contrasenaCliente;
    private String nombreCliente;
    private String pasaporte;
    
    //CONSTRUCTOR
    public Cliente(String idCliente, String contrasenaCliente) {
        this.idCliente = idCliente;
        this.contrasenaCliente = contrasenaCliente;
    }
    
    //ENCAPSULADORES
    public String getIdCliente() {
        return idCliente;
    }
    
    public String getNombreCliente(){
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente){
        this.nombreCliente=nombreCliente;
    }

    public String getContrasenaCliente() {
        return contrasenaCliente;
    }
    
    public String getPasaporte(){
        return pasaporte;
    }
    
    public void setPasaporte(String pasaporte){
        this.pasaporte=pasaporte;
    }
    
    //METODO PARA ENSEÑAR EL USUARIO AL INICIO DEL PROGRAMA
    public void MostrarDatosCliente(){
        
        System.out.println("ID: "+idCliente);
        System.out.println("Contrasena: "+contrasenaCliente);
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
