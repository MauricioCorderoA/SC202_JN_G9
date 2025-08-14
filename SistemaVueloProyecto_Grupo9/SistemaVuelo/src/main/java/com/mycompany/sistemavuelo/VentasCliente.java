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
    // Atributos principales de cada venta
    public String idVenta;       // Identificador unico para cada venta (formato V+numero)
    public int cantidadBoletos;  // Cantidad de boletos vendidos en esta transaccion
    public double montoTotal;    // Suma total del valor de la venta (cantidad * precio unitario)
    public String estado;        // Estado actual de la venta (activa, cancelada, completada)
    
    // Componentes del sistema de ventas
    private Administracion administracion;  // Referencia al sistema principal de administracion
    private VentasCliente[] ventas;         // Arreglo para almacenar todas las ventas realizadas
    private int totalVentas;                // Contador de ventas registradas en el sistema
    
    // Constructor vacio para inicializacion basica
    public VentasCliente (){   
    }
    
    // Constructor principal que recibe la administracion como parametro
    public VentasCliente(Administracion administracion){
        this.administracion = administracion;  // Establece la conexion con administracion
        this.ventas = new VentasCliente[100];  // Inicializa el arreglo con capacidad para 100 ventas
        this.totalVentas = 0;                  // Inicializa el contador de ventas en cero
    }
    
    // Metodos de acceso (getters) para los atributos privados
    
    // Obtiene el estado actual de una venta
    public String getEstado() {
        return estado;
    }

    // Obtiene el monto total de una venta
    public double getMontoTotal() {
        return montoTotal;
    }
    
    // Obtiene el numero total de ventas registradas
    public int getTotalVentas(){
        return totalVentas;
    }
    
    // Obtiene el arreglo completo de ventas
    public VentasCliente[] getVentas(){
        return ventas;
    }
    
    // Metodo para realizar una nueva reserva de vuelo
    public void Reservar(){
        // Verifica si hay espacio disponible para mas ventas
        if (totalVentas >= ventas.length){
            JOptionPane.showMessageDialog(null, "No se pueden registrar mas ventas");
            return;
        }
        
        // Solicita al usuario el numero de vuelo a reservar
        int numeroVuelo = Integer.parseInt(JOptionPane.showInputDialog("Numero de vuelo que desea reservar"));
        Vuelo[] vuelos = administracion.getVuelos();  // Obtiene la lista de vuelos disponibles
        int totalVuelos = administracion.getContador();  // Obtiene el total de vuelos existentes
        
        // Busca el vuelo solicitado en la lista de vuelos
        for (int i = 0; i < totalVuelos; i++){
            if (vuelos[i].getNumeroVuelo() == numeroVuelo){
                // Solicita la cantidad de boletos a reservar
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de boletos a comprar"));
                
                // Verifica si hay suficientes asientos disponibles
                if (cantidad <= vuelos[i].getAsientosTotales()){
                    // Actualiza la cantidad de asientos disponibles
                    vuelos[i].setAsientosTotales(vuelos[i].getAsientosTotales()-cantidad);
                    
                    // Crea un nuevo objeto de venta
                    VentasCliente venta = new VentasCliente(null);
                    venta.idVenta = "V"+(totalVentas+1);  // Genera un ID unico
                    venta.cantidadBoletos = cantidad;      // Asigna la cantidad de boletos
                    venta.montoTotal = cantidad*100.0;    // Calcula el total (precio fijo $100)
                    venta.estado = "activa";              // Establece estado inicial
                    
                    // Almacena la venta en el arreglo
                    ventas[totalVentas] = venta;
                    totalVentas++;  // Incrementa el contador de ventas
                    
                    // Muestra mensaje de confirmacion
                    JOptionPane.showMessageDialog(null, "Reservacion exitosa para el vuelo "+numeroVuelo);
                    generarFactura(venta.idVenta);  // Genera la factura automaticamente
                    return;
                } else if (cantidad >= vuelos[i].getAsientosTotales()){
                    // Muestra mensaje si no hay suficientes asientos
                    JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes asientos");
                    return;
                }
            }
        }
        // Muestra mensaje si el vuelo no existe
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
    }
    
    // Metodo para cancelar una reserva existente
    public void CancelarReserva(){
        // Solicita el ID de la venta a cancelar
        String id = JOptionPane.showInputDialog("ID de la venta");
    
        // Busca la venta en el arreglo de ventas
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i].idVenta.equals(id) && ventas[i].estado.equals("activa")) {
                // Cambia el estado de la venta cancelada
                ventas[i].estado = "cancelada";
                
                // Solicita el numero de vuelo para liberar los asientos
                int numeroVuelo = Integer.parseInt(JOptionPane.showInputDialog("ID del vuelo a cancelar"));
                
                Vuelo[] vuelos = administracion.getVuelos();  // Obtiene la lista de vuelos
                int totalVuelos = administracion.getContador();  // Obtiene el total de vuelos
                
                // Busca el vuelo para liberar los asientos
                for (int j = 0; j < totalVuelos; j++) {
                    if (vuelos[j].getNumeroVuelo() == numeroVuelo) {
                        // Devuelve los asientos al sistema
                        vuelos[j].setColumnas(vuelos[j].getColumnas() + ventas[i].cantidadBoletos);
                        break;
                    }
                }

                // Muestra mensaje de confirmacion
                JOptionPane.showMessageDialog(null, "Reservacion cancelada exitosamente");
                generarFactura(ventas[i].idVenta);  // Genera factura de cancelacion
                return;
            }
        }

        // Muestra mensaje si la venta no existe o ya esta cancelada
        JOptionPane.showMessageDialog(null, "Venta no encontrada o ya cancelada");
    }
    
    // Metodo para generar y mostrar una factura
    public void generarFactura(String idVenta) {
        // Busca la venta en el arreglo
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null && ventas[i].idVenta.equals(idVenta)) {
                // Construye el texto de la factura
                String factura = " Factura de Vuelo \n\n" +
                               "N° Factura: " + ventas[i].idVenta + "\n" +
                               "Estado: " + ventas[i].estado + "\n" +
                               "Cantidad de boletos: " + ventas[i].cantidadBoletos + "\n" +
                               "Precio unitario: $100.00\n" +
                               "Total pagado: $" + String.format("%.2f", ventas[i].montoTotal) + "\n\n" +
                               "Gracias por su compra";
                
                // Muestra la factura en un cuadro de dialogo
                JOptionPane.showMessageDialog(null, factura, "Factura de Venta", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // Muestra mensaje si no encuentra la venta
        JOptionPane.showMessageDialog(null, "No se encontro la venta especificada");
    }

    // Metodo para mostrar todas las facturas de un cliente
    public void mostrarFacturasCliente() {
        StringBuilder facturas = new StringBuilder(" Mis Facturas \n\n");
        boolean tieneFacturas = false;
        
        // Recorre todas las ventas para construir el historial
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null) {
                tieneFacturas = true;
                // Agrega los datos de cada factura al historial
                facturas.append("Factura ID: ").append(ventas[i].idVenta)
                       .append(" | Boletos: ").append(ventas[i].cantidadBoletos)
                       .append(" | Total: $").append(String.format("%.2f", ventas[i].montoTotal))
                       .append(" | Estado: ").append(ventas[i].estado)
                       .append("\n--------------------------------\n");
            }
        }
        
        // Muestra el historial o mensaje si no hay facturas
        JOptionPane.showMessageDialog(null, 
            tieneFacturas ? facturas.toString() : "No tienes facturas registradas",
            "Historial de Facturas", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}