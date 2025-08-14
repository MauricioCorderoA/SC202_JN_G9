/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Maytan
 */
public class Vuelo {
    
    
    
    private String nombreDelVuelo;
    private int filas;
    private int numeroVuelo;
    private String destino;
    private int columnas;
    private int asientosTotales;
    private int asientosDisponibles;
    private int TicketsComprados;
    private double precioTicket;

    // Constructores
    public Vuelo(){   
    }
    
    public Vuelo(int numeroVuelo, String destino, int columnas, int filas, String nombreDelVuelo, double precioTicket) {
        this.nombreDelVuelo = nombreDelVuelo;
        this.numeroVuelo = numeroVuelo;
        this.destino = destino;
        this.columnas = columnas;
        this.filas = filas;
        this.precioTicket = precioTicket;
        CalcularAsientos();
        this.asientosDisponibles= this.asientosTotales;
        this.TicketsComprados= 0;
   
    }
    
    //metodo
    
    public void CalcularAsientos(){
        this.asientosTotales=this.filas * this.columnas;
    }

    // Getters y Setters
    public double getPrecioTicket(){
        return precioTicket;
    }
    
    public void setPrecioTicket(double precioTicket){
        this.precioTicket=precioTicket;
    }
    
    public int getTicketsComprados(){
        return TicketsComprados;
    }
    
    public void setTicketsComprados(int TicketsComprados){
        this.TicketsComprados=TicketsComprados;
    }
    
    public String getNombreDelVuelo() {
        return nombreDelVuelo;
    }

    public void setNombreDelVuelo(String nombreDelVuelo) {
        this.nombreDelVuelo = nombreDelVuelo;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
        CalcularAsientos();
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }
    
    public void setAsientosTotales(int asientosTotales){
        this.asientosTotales = asientosTotales;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
    
    public void setAsientosDisponibles(int asientoDisponibles){
        this.asientosDisponibles=asientoDisponibles;
    }
    
     public int getFilas() {
    return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
        CalcularAsientos();
    }
    
    public String MostrarVuelo(){
        
        String info = "Vuelo registrado:\n";
        info +=
                "Id del vuelo: " + this.numeroVuelo + "\n" + 
                "Nombre del vuelo: " + this.nombreDelVuelo+ "\n"+ 
                "Destino: " + this.destino + "\n" + 
                "Asientos totales: " + this.asientosTotales+ "\n"+ 
                "Precio del ticket: " + this.precioTicket + "\n" + 
                "Tickets comprados: " + this.TicketsComprados+ "\n";
        
        return info;
    }

}
