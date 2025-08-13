/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemavuelo;

/**
 *
 * @author micho
 */
public class VentasCliente {
    // Maneja las ventas realizadas por los clientes
 // Contiene los datos básicos de cada transaccion
    
    public String idVenta;       // Numero unico que identifica la venta
    public String fechaVenta;   // Fecha en formato String (2026-11-20)
    public int cantidadBoletos;  // Cantidad de boletos comprados
    public double montoTotal;    // Monto de la transaccion
    public String estado;        // Estado de la venta: "activa", "cancelada", "completada"
    public String metodoPago;    // Metodo de pago: "efectivo", "tarjeta", "transferencia"
    public String numeroVuelo;   // Número de vuelo asociado ("IDvuelo")
    
public String getEstado() {
    return estado;
}

public double getMontoTotal() {
    return montoTotal ;

}
}