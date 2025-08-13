/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

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
    private int[][] asientoDisponibles;

    // Constructores
    public Vuelo(){   
    }
    
    public Vuelo(int numeroVuelo, String destino, int columnas, int filas, String nombreDelVuelo) {
        this.nombreDelVuelo = nombreDelVuelo;
        this.numeroVuelo = numeroVuelo;
        this.destino = destino;
        this.columnas = columnas;
        this.filas = filas;
        CalcularAsientos();
   
    }
    
    //metodo
    
    public void CalcularAsientos(){
        this.asientosTotales=this.filas * this.columnas;
        this.asientoDisponibles = new int[this.filas][this.columnas];
    }

    // Getters y Setters
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

    public int[][] getAsientoDisponibles() {
        return asientoDisponibles;
    }
    
     public int getFilas() {
    return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
        CalcularAsientos();
    }

    public void setAsientoDisponibles(int[][] asientoDisponibles) {
        this.asientoDisponibles = asientoDisponibles;
    }

  


}
