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
    // Contiene los datos basicos de cada transaccion
    
    // Atributos
    public String idVenta;       // Numero unico que identifica la venta
    public int cantidadBoletos;  // Cantidad de boletos comprados
    public double montoTotal;    // Monto de la transaccion
    public String estado;        // Estado de la venta: "activa", "cancelada", "completada"
    public int numeroVuelo;      // Numero de vuelo asociado a la venta 
    private Administracion administracion;
    private VentasCliente[] ventas;
    private int totalVentas;
    
    // Constructores
    public VentasCliente(){   
    }
    
    public VentasCliente(Administracion administracion){
        this.administracion = administracion;
        this.ventas = new VentasCliente[100];
        this.totalVentas = 0;
    }
    
    // Encapsuladores
    public String getEstado() {
        return estado;
    }

    public double getMontoTotal() {
        return montoTotal;
    }
    
    public int getTotalVentas(){
        return totalVentas;
    }
    
    public VentasCliente[] getVentas(){
        return ventas;
    }
    
    public int getNumeroVuelo() {
        return numeroVuelo;
    }
    
    // Metodos de negocio
    
    public void Reservar(){
        if (totalVentas >= ventas.length){
            JOptionPane.showMessageDialog(null, "No se pueden registrar mas ventas");
            return;
        }
        
        int numeroVuelo = Integer.parseInt(JOptionPane.showInputDialog("Numero de vuelo que desea reservar"));
        Vuelo[] vuelos = administracion.getVuelos();
        int totalVuelos = administracion.getContador();
        
        for (int i = 0; i < totalVuelos; i++){
            if (vuelos[i].getNumeroVuelo() == numeroVuelo){
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de boletos a comprar"));
                
                if (cantidad <= vuelos[i].getAsientosTotales()){
                    vuelos[i].setAsientosTotales(vuelos[i].getAsientosTotales() - cantidad);
                    
                    VentasCliente venta = new VentasCliente(null);
                    venta.idVenta = "V" + (totalVentas + 1);
                    venta.cantidadBoletos = cantidad;
                    venta.montoTotal = cantidad * 100.0;
                    venta.estado = "activa";
                    venta.numeroVuelo = numeroVuelo;  // Almacenamos el numero de vuelo
                    
                    ventas[totalVentas] = venta;
                    totalVentas++;
                    
                    JOptionPane.showMessageDialog(null, "Reservacion exitosa para el vuelo " + numeroVuelo);
                    generarFactura(venta.idVenta);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes asientos");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
    }
    
    public void CancelarReserva(){
        String id = JOptionPane.showInputDialog("ID de la venta a cancelar");
    
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null && ventas[i].idVenta.equals(id) && ventas[i].estado.equals("activa")) {
                ventas[i].estado = "cancelada";
                
                // Obtenemos el numero de vuelo directamente de la venta
                int numeroVuelo = ventas[i].numeroVuelo;
                
                Vuelo[] vuelos = administracion.getVuelos();
                int totalVuelos = administracion.getContador();
                
                for (int j = 0; j < totalVuelos; j++) {
                    if (vuelos[j].getNumeroVuelo() == numeroVuelo) {
                        vuelos[j].setAsientosTotales(vuelos[j].getAsientosTotales() + ventas[i].cantidadBoletos);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(null, "Reservacion cancelada exitosamente");
                generarFactura(ventas[i].idVenta);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Venta no encontrada o ya cancelada");
    }
    
    public void generarFactura(String idVenta) {
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null && ventas[i].idVenta.equals(idVenta)) {
                String factura = " Factura Vuelo \n\n" +
                               "N° Factura: " + ventas[i].idVenta + "\n" +
                               "N° Vuelo: " + ventas[i].numeroVuelo + "\n" +  // Mostramos el numero de vuelo
                               "Estado: " + ventas[i].estado + "\n" +
                               "Cantidad de boletos: " + ventas[i].cantidadBoletos + "\n" +
                               "Precio unitario: $100.00\n" +
                               "Total pagado: $" + String.format("%.2f", ventas[i].montoTotal) + "\n\n" +
                               "Gracias por su compra";
                
                JOptionPane.showMessageDialog(null, factura, "Factura de Venta", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro la venta especificada");
    }

    public void mostrarFacturasCliente() {
        StringBuilder facturas = new StringBuilder(" Mis Facturas \n\n");
        boolean tieneFacturas = false;
        
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null) {
                tieneFacturas = true;
                facturas.append("Factura ID: ").append(ventas[i].idVenta)
                       .append(" | Vuelo: ").append(ventas[i].numeroVuelo)  // Mostramos el vuelo
                       .append(" | Boletos: ").append(ventas[i].cantidadBoletos)
                       .append(" | Total: $").append(String.format("%.2f", ventas[i].montoTotal))
                       .append(" | Estado: ").append(ventas[i].estado)
                       .append("\n--------------------------------\n");
            }
        }
        
        JOptionPane.showMessageDialog(null, 
            tieneFacturas ? facturas.toString() : "No tienes facturas registradas",
            "Historial de Facturas", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Metodos no implementados (generados automaticamente)
    void reservar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void cancelarReserva() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}