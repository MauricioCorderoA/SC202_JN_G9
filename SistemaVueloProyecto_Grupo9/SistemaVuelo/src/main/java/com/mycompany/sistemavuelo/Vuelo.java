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
    private int columnas;
    private int numeroVuelo;
    private String destino;
    private int asientosTotales;
    private int asientosDisponibles;
    private int TicketsComprados;
    private double precioTicket;
    
    private boolean [][] asientos;

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
        this.asientosTotales=filas*columnas;
        this.asientosDisponibles= this.asientosTotales;
        this.TicketsComprados= 0;
        
        this.asientos = new boolean [filas][columnas];
        for (int i = 0 ; i<filas ; i++){
            for (int j=0;j<columnas; j++){
                asientos [i][j]=false;
            }
        }
   
    }
    
    //metodo
    
    
    public void mostrarAsientosDisponibles(){
        String info = "Aientos disponibles (F = filas, C = columnas):\n";
        for (int i = 0 ; i<filas ; i++){
            for (int j = 0; j<columnas; j++){
                if (asientos[i][j]==true){
                    info += "[X]"; //el asiento esta vendido
                }else {
                    info+="[O]" ; //el asiento esta libre
                }
            }
            info +="\n";
        }
        JOptionPane.showMessageDialog(null, info);
    }
    
    public boolean venderAsiento(int fila, int columna){
        if (fila < 1|| fila > filas || columna <1|| columna>columnas){
            JOptionPane.showMessageDialog(null, "Asiento invalidado");
            return false;
        }
        
        if (asientos[fila-1][columna-1]==true){
            JOptionPane.showMessageDialog(null, "Asiento ya vendido");
            return false;
        } else {
            asientos[fila-1][columna - 1]=true;
            asientosDisponibles --;
            TicketsComprados++;
            JOptionPane.showMessageDialog(null, "Asiento vendido correctamente");
            return true;
        }
     
    }
    public boolean liberarAsiento(int fila, int columna){
        if (fila < 1|| fila > filas || columna <1|| columna>columnas){
            return false; //invalido
        }if (!asientos[fila-1][columna-1]){
            return false; //ya esta libre
        }
        
        asientos[fila-1][columna-1]=false;
        asientosDisponibles++;
        TicketsComprados--;
        return true;
        
    }
    
    public void recalcularAsientos(){
        this.asientosTotales=filas*columnas;
        this.asientosDisponibles=asientosTotales-TicketsComprados;
        
        boolean[][]nuevosAsientos = new boolean[filas][columnas];
        int limiteFilas = filas;
        if(asientos!=null && asientos.length<filas){
            limiteFilas = asientos.length;
            
        }
        
        int limiteColumnas = columnas;
        if(asientos!=null&&asientos[0].length<columnas){
            limiteColumnas = asientos[0].length;
        }
        
        for (int i = 0 ; i<limiteColumnas;i++){
            for (int j=0; j<limiteColumnas;j++){
                nuevosAsientos[i][j]=asientos[i][j];
            }
        }
        
        this.asientos=nuevosAsientos;
        
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
        recalcularAsientos();
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
        recalcularAsientos();
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
