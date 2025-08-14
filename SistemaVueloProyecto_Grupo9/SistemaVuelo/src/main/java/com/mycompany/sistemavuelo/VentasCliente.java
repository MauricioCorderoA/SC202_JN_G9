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
    public boolean estado;        // Estado de la venta: "activa", "cancelada", "completada"
    public int numeroVuelo;      // Numero de vuelo asociado a la venta 
    private Administracion administracion;
    private VentasCliente[] ventas;
    public int totalVentas;
    public double precioTicketV;
    
    // Constructores
    public VentasCliente(){   
    }
    
    public VentasCliente(Administracion administracion){
        this.administracion = administracion;
        this.ventas = new VentasCliente[100];
        this.totalVentas = 0;
    }
    
    // Encapsuladores
    public boolean getEstado() {
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
    
    public double getPrecioTicketV(){
        return precioTicketV;
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
                
                if (cantidad <= vuelos[i].getAsientosDisponibles()){
                    vuelos[i].setAsientosDisponibles(vuelos[i].getAsientosDisponibles() - cantidad);
                    vuelos[i].setTicketsComprados(vuelos[i].getTicketsComprados()+cantidad);
                    
                    VentasCliente venta = new VentasCliente(administracion);
                    venta.idVenta = "V" + (totalVentas + 1);
                    venta.cantidadBoletos = cantidad;
                    venta.montoTotal = cantidad* vuelos[i].getPrecioTicket();
                    venta.estado = true;
                    venta.numeroVuelo = numeroVuelo; 
                    venta.precioTicketV=vuelos[i].getPrecioTicket();
                    
                    ventas[totalVentas] = venta;
                    totalVentas++;
                    
                    JOptionPane.showMessageDialog(null, "Reservacion exitosa para el vuelo " + numeroVuelo);
                    generarFactura(venta.idVenta);
                    return;
                } else if (cantidad >= vuelos[i].getAsientosDisponibles()) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes asientos");
                    return;
                }else {
                    JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
                }
            }
        }
        
    }
    
    public void CancelarReserva(){
        String id = JOptionPane.showInputDialog("ID de la venta a cancelar");
    
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null && ventas[i].idVenta.equals(id) && ventas[i].estado == true) {
                ventas[i].estado = false;
                
                // Obtenemos el numero de vuelo directamente de la venta
                int numeroVuelo = ventas[i].numeroVuelo;
                Vuelo[] vuelos = administracion.getVuelos();
                int totalVuelos = administracion.getContador();
                
                boolean vueloEncontrado = false;
                
                for (int j = 0; j < totalVuelos; j++) {
                    if (vuelos[j].getNumeroVuelo() == numeroVuelo) {
                        vuelos[j].setAsientosDisponibles(vuelos[j].getAsientosDisponibles() + ventas[i].cantidadBoletos);
                        vuelos[j].setTicketsComprados(vuelos[j].getTicketsComprados()-ventas[j].cantidadBoletos);
                        vueloEncontrado=true;
                        break;
                    }
                }
                
                if (!vueloEncontrado){
                    JOptionPane.showMessageDialog(null, "El vuelo no fue encontrado");
                }

                JOptionPane.showMessageDialog(null, "Reservacion cancelada exitosamente");
                generarFactura(ventas[i].idVenta);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Venta no encontrada o ya se encuentra cancelada");
    }
    
    public void generarFactura(String idVenta) {
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null && ventas[i].idVenta.equals(idVenta)) {
                String factura = " Factura Vuelo \n\n" +
                               "N° Factura: " + ventas[i].idVenta + "\n" +
                               "N° Vuelo: " + ventas[i].numeroVuelo + "\n" +  // Mostramos el numero de vuelo
                               "Estado: " + ventas[i].estado + "\n" +
                               "Cantidad de boletos: " + ventas[i].cantidadBoletos + "\n" +
                               "Precio unitario: " + ventas[i].precioTicketV+ "\n"+
                               "Total pagado: $" + String.format("%.2f", ventas[i].montoTotal) + "\n\n" +
                               "Gracias por su compra";
                
                JOptionPane.showMessageDialog(null, factura, "Factura de Venta", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontro la venta especificada");
    }

    public void mostrarFacturasCliente() {
        if (totalVentas==0){
            JOptionPane.showMessageDialog(null, "No tienes facturas registradas");
        }
        String facturas = (" Mis Facturas \n\n");
        
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i] != null) {
                facturas+="Factura ID: " + ventas[i].idVenta+
                    " | Vuelo: " + ventas[i].numeroVuelo +
                 " | Boletos: " + ventas[i].cantidadBoletos + 
                  "| Total $:  "+ String.format("%.2f", ventas[i].montoTotal)+
                   "| Estado: "+ventas[i].estado + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, facturas);
    }
    
}