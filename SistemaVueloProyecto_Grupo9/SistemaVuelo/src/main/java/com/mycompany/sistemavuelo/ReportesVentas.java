/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

import javax.swing.JOptionPane;

//Clase para generar reportes estadísticos de ventas
 
public class ReportesVentas {
    
    //Atributos
    private int reservasActivas;          // Numero de reservas actualmente activas
    private double gananciasTotales;      // Suma acumulada de todas las ventas
    private int vuelosCancelados;         // Total de vuelos cancelados
    private double promedioVenta;         // Valor promedio por transaccion
    private int clientesAtendidos;        // Cantidad total de clientes atendidos
    private String vueloMasVendido;       // Identificador del vuelo con mas ventas
    private double costoPromedioBoleto;   // Precio promedio por boleto vendido
    
    // Conexion con el sistema principal
    private Administracion administracion;
    
    //Constructor
    public ReportesVentas(Administracion administracion){
        this.administracion = administracion;
    }
    
    //Metodos de Reportes
    
    // Muestra un resumen general de los vuelos registrados
     
    public void mostrarDatosVuelos(){
        int totalVuelos = administracion.getContador();
        
        // Validacion si no hay vuelos
        if (totalVuelos == 0){
            JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
            return;
        }
        
        // Genera el reporte de Ventas
        JOptionPane.showMessageDialog(null, 
            " Reporte de Ventas \n"
            + "Reservas activas: " + totalVuelos + "\n"
            + "Vuelos creados: " + administracion.getVuelosCreados() + "\n"
            + "Vuelos Modificados: " + administracion.getVuelosModificados() + "\n"
            + "Vuelos eliminados: " + administracion.getVuelosEliminados());
    }
    
    // Identifica y muestra el vuelo con mayor cantidad de boletos vendidos
    
    public void mostrarVueloMasVendido() {
        Vuelo[] vuelos = administracion.getVuelos();
        int totalVuelos = administracion.getContador();
        
        // Validacion si no hay vuelos
        if (totalVuelos == 0) {
            JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
            return;
        }
        
        // Variables para calculo de estadisticas
        String idVueloMasVendido = "";
        int maxVentas = 0;
        int totalBoletosVendidos = 0;
        double totalGanancias = 0.0;
        String origenDestino = "";
        
        // Procesa cada vuelo para encontrar el mas vendido
        for (int i = 0; i < totalVuelos; i++) {
            Vuelo vueloActual = vuelos[i];
            if (vueloActual != null) {
                // Calcula boletos vendidos (asientos iniciales - disponibles)
                int boletosVendidos = vueloActual.getAsientosIniciales() - vueloActual.getAsientosTotales();
                
                // Actualiza estadisticas si encuentra nuevo maximo
                if (boletosVendidos > maxVentas) {
                    maxVentas = boletosVendidos;
                    idVueloMasVendido = String.valueOf(vueloActual.getNumeroVuelo());
                    totalBoletosVendidos = boletosVendidos;
                    totalGanancias = boletosVendidos * vueloActual.getPrecio();
                    origenDestino = vueloActual.getOrigen() + " a " + vueloActual.getDestino();
                }
            }
        }
        
        // Muestra resultados si hay ventas registradas
        if (maxVentas > 0) {
            String mensaje = " Vuelo mas vendido \n\n" +
                           "Número de Vuelo: " + idVueloMasVendido + "\n" +
                           "Ruta: " + origenDestino + "\n" +
                           "Boletos Vendidos: " + totalBoletosVendidos + "\n" +
                           "Ganancias Generadas: $" + String.format("%.2f", totalGanancias);
            
            JOptionPane.showMessageDialog(null, mensaje, 
                "Reporte de Vuelo Más Vendido", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                "No se encontraron ventas registradas para ningún vuelo");
        }
    }
}