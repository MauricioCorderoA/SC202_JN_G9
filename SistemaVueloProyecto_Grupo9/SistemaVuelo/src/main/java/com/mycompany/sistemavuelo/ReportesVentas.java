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
    
    private double gananciasTotales;      // Suma total de ingresos
    private String vueloMasVendido;       // ID del vuelo con mas ventas 
    
    private Administracion administracion;
    private VentasCliente ventascliente;
    
    public ReportesVentas (Administracion administracion, VentasCliente ventascliente){
        this.administracion=administracion;
        this.ventascliente= ventascliente;
    }
    
    public void mostrarDatosVuelos(){
        int totalVuelos = administracion.getContador();
        
        if (totalVuelos==0){
            JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
            return;
        }
        
        JOptionPane.showMessageDialog(null, " Reporte de Ventas \n"
                + "Reservas activas: "+totalVuelos+"\n"
                        + "Vuelos creados: "+administracion.getVuelosCreados()+"\n"
                                + "Vuelos Modificados: "+administracion.getVuelosModificados()+"\n"
                                        + "Vuelos eliminados: "+administracion.getVuelosEliminados());
        
    }
    
    public void mostrarganancias(){
        Vuelo[] vuelos = administracion.getVuelos();
        int totalVuelos=administracion.getContador();
        
        gananciasTotales = 0;
        int maxTicketsVendidos=0;
        Vuelo vueloMasVendido=null;
        
        String info = "===Reporte de las ganancias===\n\n";
        
        for (int i=0; i<totalVuelos; i++){
            Vuelo v = vuelos[i];
            
            double gananciasVuelo = v.getPrecioTicket()*v.getTicketsComprados();
            gananciasTotales+=gananciasVuelo;
            
            if (v.getTicketsComprados()>maxTicketsVendidos){
                maxTicketsVendidos = v.getTicketsComprados();
                vueloMasVendido=v;
            }
            
            info+="Vuelo: " + v.getNombreDelVuelo() +
                    " | Nº: " + v.getNumeroVuelo() +
                 " | Tickets vendidos: " + v.getTicketsComprados() + 
                  "| Ganancias: $"+String.format("%.2f", gananciasVuelo)+"\n";
            
        }
        
        info+="\nGanancias total de todos los vuelos: $" + String.format("%.2f", gananciasTotales);
        JOptionPane.showMessageDialog(null, info);
        
        if (vueloMasVendido!=null){
            JOptionPane.showMessageDialog(null, "===Vuelo con mas ventas===\n\n" +vueloMasVendido.MostrarVuelo(), "Vuelo mas vendido", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}