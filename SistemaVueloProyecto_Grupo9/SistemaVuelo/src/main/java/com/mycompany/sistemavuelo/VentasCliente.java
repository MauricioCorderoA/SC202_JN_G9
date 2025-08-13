/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

import javax.swing.JOptionPane;

/**
 *
 * @author micho
 */
public class VentasCliente {
    // Maneja las ventas realizadas por los clientes
 // Contiene los datos básicos de cada transaccion
    //atributos
    public String idVenta;       // Numero unico que identifica la venta
    public int cantidadBoletos;  // Cantidad de boletos comprados
    public double montoTotal;    // Monto de la transaccion
    public String estado;        // Estado de la venta: "activa", "cancelada", "completada"
    private Administracion administracion;
    private VentasCliente[] ventas;
    private int totalVentas;
    
    
    // metodos
    public VentasCliente (){   
    }
    
    public VentasCliente(Administracion administracion){
        this.administracion= administracion;
        this.ventas = new VentasCliente[100];
        this.totalVentas= 0;
    }
    
    //encapsuladores
    public String getEstado() {
        return estado;
    }

    public double getMontoTotal() {
        return montoTotal ;
    }
    
    public int getTotalVentas(){
        return totalVentas;
    }
    
    public VentasCliente[] getVentas(){
        return ventas;
    }
    
    //metodos
    
    public void Reservar(){
        if (totalVentas>=ventas.length){
            JOptionPane.showMessageDialog(null, "No se pueden registrar más ventas");
        return;
        }
        
        int numeroVuelo = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo que desea reservar"));
        Vuelo[] vuelos = administracion.getVuelos();
        int totalVuelos = administracion.getContador();
        
        for (int i = 0; i < totalVuelos; i++){
            if (vuelos[i].getNumeroVuelo() == numeroVuelo){
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de boletos a comprar"));
                
                if (cantidad <= vuelos[i].getAsientosTotales()){
                    
                    vuelos[i].setAsientosTotales(vuelos[i].getAsientosTotales()-cantidad);
                    
                    VentasCliente venta = new VentasCliente(null);
                    venta.idVenta= "V"+(totalVentas+1);
                    venta.cantidadBoletos = cantidad;
                    venta.montoTotal = cantidad*100.0;
                    venta.estado= "activa";
                    
                    ventas[totalVentas]=venta;
                    totalVentas++;
                    
                    JOptionPane.showMessageDialog(null, "Reservación exitosa para el vuelo "+numeroVuelo);
                    return;
                } else if (cantidad>=vuelos[i].getAsientosTotales()){
                    JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes asientos");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
        
    }
    
    public void CancelarReserva(){
        String id = JOptionPane.showInputDialog("ID de la venta");
    
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i].idVenta.equals(id) && ventas[i].estado.equals("activa")) {
                ventas[i].estado = "cancelada";
                
                int numeroVuelo = Integer.parseInt(JOptionPane.showInputDialog("ID del vuelo a cancelar"));
                
                Vuelo[] vuelos = administracion.getVuelos();
                int totalVuelos = administracion.getContador();
                
                for (int j = 0; j < totalVuelos; j++) {
                    if (vuelos[j].getNumeroVuelo() == numeroVuelo) {
                        vuelos[j].setColumnas(vuelos[j].getColumnas() + ventas[i].cantidadBoletos);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(null, "Reservación cancelada exitositamente");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Venta no encontrada o ya cancelada");
    }
    
}