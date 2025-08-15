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
    public int[] filaAsiento;
    public int[] columnaAsiento;
    
    
    // Constructores
    public VentasCliente(){   
    }
    
    public VentasCliente(Administracion administracion){
        this.administracion = administracion;
        this.ventas = new VentasCliente[100];
        this.totalVentas = 0;
    }
    
    // Encapsuladores
    
    public int[] getFilaAsiento(){
        return filaAsiento;
    }
    
    public int[] getColumnaAsiento(){
        return columnaAsiento;
    }
    
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
                vuelos[i].mostrarAsientosDisponibles();
                int fila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fila del asiento"));
                int columna = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la columna del asiento"));
                
                boolean vendido = vuelos[i].venderAsiento(fila, columna);
                
                if (vendido ==true ){
                    
                    VentasCliente venta = new VentasCliente(administracion);
                    venta.idVenta = "V" + (totalVentas + 1);
                    venta.cantidadBoletos = 1;
                    venta.montoTotal = vuelos[i].getPrecioTicket();
                    venta.estado = true;
                    venta.numeroVuelo = numeroVuelo; 
                    venta.precioTicketV=vuelos[i].getPrecioTicket();
                    
                    venta.filaAsiento=new int[1];
                    venta.columnaAsiento=new int[1];
                    venta.filaAsiento[0]=fila;
                    venta.columnaAsiento[0]=columna;
                    
                    ventas[totalVentas] = venta;
                    totalVentas++;
                    
                    generarFactura(venta.idVenta);
                    
                    
                    return;
                } else  {
                    JOptionPane.showMessageDialog(null, "No se pudo vender el vuelo seleccionado");
                    return;
                }
                   
            }
        }
        JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
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
                        
                        
                        for (int k = 0; k<ventas[i].filaAsiento.length; k++){
                            int fila = ventas[i].filaAsiento[k];
                            int columna = ventas[i].columnaAsiento[k];
                            vuelos[j].liberarAsiento(fila, columna);
                        }
                        
                        
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

                String asientosComprados = "";
                for (int k = 0; k < ventas[i].filaAsiento.length; k++) {
                    asientosComprados += "[F" + ventas[i].filaAsiento[k] + ", C" + ventas[i].columnaAsiento[k] + "] ";
                }
                
                String factura = " Factura Vuelo \n\n" +
                               "N° Factura: " + ventas[i].idVenta + "\n" +
                               "N° Vuelo: " + ventas[i].numeroVuelo + "\n" +  // Mostramos el numero de vuelo
                               "Estado: " + ventas[i].estado + "\n" +
                               "Cantidad de boletos: " + ventas[i].cantidadBoletos + "\n" +
                               "Monto pagado $: " + ventas[i].precioTicketV+ "\n"+
                               ", Asiento comprado: "+asientosComprados +"\n\n" +
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
                
                String asientosComprados = "";
                for (int k = 0; k < ventas[i].filaAsiento.length; k++) {
                    asientosComprados += "[F" + ventas[i].filaAsiento[k] + ", C" + ventas[i].columnaAsiento[k] + "] ";
                }
                
                facturas+="Factura ID: " + ventas[i].idVenta+
                    " | Vuelo: " + ventas[i].numeroVuelo +
                 " | Boletos: " + ventas[i].cantidadBoletos + 
                  "| Total $:  "+ String.format("%.2f", ventas[i].montoTotal)+
                               "| Asiento comprado: "+asientosComprados +"" +
                   "| Estado: "+ventas[i].estado + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, facturas);
    }
    
}