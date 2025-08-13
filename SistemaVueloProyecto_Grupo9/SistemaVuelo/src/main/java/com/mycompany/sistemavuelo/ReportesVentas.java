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
public class ReportesVentas {
    
    private int reservasActivas;          // Cantidad de reservas vigentes 
    private double gananciasTotales;      // Suma total de ingresos 
    private int vuelosCancelados;         // Contador de cancelaciones 
    private double promedioVenta;         // Promedio de montos por venta 
    private int clientesAtendidos;        // Total de clientes
    private String vueloMasVendido;       // ID del vuelo con mas ventas 
    private double costoPromedioBoleto;   // Precio promedio por boleto
    
    private Administracion administracion;
    
    public ReportesVentas (Administracion administracion){
        this.administracion=administracion;
    }
    
    public void mostrarDatosVuelos(){
        int totalVuelos = administracion.getContador();
        
        if (totalVuelos==0){
            JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "====Reporte de Ventas===\n"
                + "Reservas activas: "+totalVuelos+"\n"
                        + "Vuelos creados: "+administracion.getVuelosCreados()+"\n"
                                + "Vuelos Modificados: "+administracion.getVuelosModificados()+"\n"
                                        + "Vuelos eliminados: "+administracion.getVuelosEliminados());
        
    }
}
