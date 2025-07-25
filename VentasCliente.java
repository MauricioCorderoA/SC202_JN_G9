// Maneja las ventas realizadas por los clientes
 // Contiene los datos básicos de cada transaccion

public class VentasCliente {
    private String idVenta;       // Numero unico que identifica la venta
    private String fechaVenta;   // Fecha en formato String (2026-11-20)
    private int cantidadBoletos;  // Cantidad de boletos comprados
    private double montoTotal;    // Monto de la transaccion 
    private String estado;        // Estado de la venta: "activa", "cancelada", "completada"
    private String metodoPago;    // Metodo de pago: "efectivo", "tarjeta", "transferencia"
    private String numeroVuelo;   // Número de vuelo asociado ("IDvuelo")
}

