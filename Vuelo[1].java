package com.mycompany.proyectofinal;

public class Vuelo {
    private String nombreDelVuelo;
    private int filas;
    private int numeroVuelo;
    private String destino;
    private int asientosDisp;
    private int asientosTotales;
    private int[][] asientoDisponibles;

    // Constructor
    public Vuelo(int numeroVuelo, String destino, int asientosDisp, int filas, String nombreDelVuelo) {
        this.nombreDelVuelo = nombreDelVuelo;
        this.numeroVuelo = numeroVuelo;
        this.destino = destino;
        this.asientosDisp = asientosDisp;
        this.filas = filas;
        this.asientosTotales = filas * asientosDisp;
        this.asientoDisponibles = new int[filas][asientosDisp]; // matriz de disponibilidad
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

    public int getAsientosDisp() {
        return asientosDisp;
    }

    public void setAsientosDisp(int asientosDisp) {
        this.asientosDisp = asientosDisp;
        this.asientoDisponibles = new int[filas][asientosDisp];
        this.asientosTotales = filas * asientosDisp;
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }

    public int[][] getAsientoDisponibles() {
        return asientoDisponibles;
    }
    public int getFilas() {
    return filas;
}

public void setFilas(int filas) {
    this.filas = filas;
    // Update seat matrix and total count after changing filas
    this.asientoDisponibles = new int[filas][asientosDisp];
    this.asientosTotales = filas * asientosDisp;
}


    public void setAsientoDisponibles(int[][] asientoDisponibles) {
        this.asientoDisponibles = asientoDisponibles;
    }
}

