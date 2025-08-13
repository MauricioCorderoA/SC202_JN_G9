/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemavuelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Maytan
 */
public class SistemaVuelo {
    
    private static final Administrador ADMIN = new Administrador("Admin", "1234A");
    private static final Cliente CLIENTE = new Cliente("cliente", "9876C");
    private static final Vuelo[] vuelos = new Vuelo[10]; // máximo 10 vuelos
    private static int totalVuelos = 0;
    private static int totalVentas = 0;
    private static final VentasCliente[] ventas = new VentasCliente[20]; // máximo 20 ventas
    public static void main(String[] args) {
        
        Datos();
        iniciarSistema();
        
        
    }
    
    private static void iniciarSistema() {
        while (true) {
            int intentos = 0;
            boolean accesoConcedido = false;
            
            while (intentos < 3 && !accesoConcedido) {
                
                Object[] opciones = {"Administrador", "Cliente", "Salir"};
                int tipoUsuario = JOptionPane.showOptionDialog(
                    null, 
                    "Seleccione tipo de usuario:", 
                    "Inicio de Sesión",
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    opciones, 
                    opciones[0]
                );

                if (tipoUsuario == 2 || tipoUsuario == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Sistema cerrado");
                    return;
                }

                String id = JOptionPane.showInputDialog("Ingrese su ID:");
                String password = JOptionPane.showInputDialog("Ingrese su contraseña:");

                if (tipoUsuario == 0 && id.equals(ADMIN.getIdAdministrador())&& password.equals(ADMIN.getContrasenaAdmin())) {
                    if (ADMIN.getNombreAdmin()==null){
                        ADMIN.setNombreAdmin(JOptionPane.showInputDialog("Ingrese el nombre que desea registrar"));
                    }
                    menuAdministrador();
                    accesoConcedido = true;
                } 
                else if (tipoUsuario == 1 && id.equals(CLIENTE.getIdCliente())&& password.equals(CLIENTE.getContrasenaCliente())) {
                    if (CLIENTE.getNombreCliente()==null && CLIENTE.getPasaporte()==null){
                        CLIENTE.setNombreCliente(JOptionPane.showInputDialog("Ingrese el nombre que desea registrar"));
                        CLIENTE.setPasaporte(JOptionPane.showInputDialog("Ingrese su pasaporte"));
                    }
                    menuCliente();
                    accesoConcedido = true;
                } 
                else {
                    intentos++;
                    JOptionPane.showMessageDialog(null, 
                        "Credenciales incorrectas\nIntentos restantes: " + (3 - intentos));
                }    
            }

            if (intentos >= 3) {
                JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos\nSistema bloqueado");
                return;
            }

            int continuar = JOptionPane.showConfirmDialog(null, 
                "¿Desea cambiar de usuario?", 
                "Cambiar sesión", 
                JOptionPane.YES_NO_OPTION);
            
            if (continuar != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Gracias por usar nuestro sistema\nSesión finalizada");
                return;
            }
        }
    }
    
    private static void Datos(){
        
        System.out.println("Recuerde que los datos de sus usuarios son los siguientes");
        System.out.println("Datos de administrador");
        ADMIN.MostrarDatosAdmin();
        System.out.println("Datos de cliente");
        CLIENTE.MostrarDatosCliente();
        
    }
    
    private static void menuAdministrador() {
        final String[] OPCIONES = {
            "Crear vuelo", 
            "Modificar vuelo", 
            "Eliminar vuelo", 
            "Ver todos los vuelos", 
            "Reporte del sistema",
            "Salir"
        };

        while (true) {
            
            int opcion = JOptionPane.showOptionDialog(
                null, 
                "Menú de Administrador", 
                "Administración", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                OPCIONES, 
                OPCIONES[0]
            );

            switch (opcion) {
            case 0 -> crearVuelo();
            case 1 -> modificarVuelo();
            case 2 -> eliminarVuelo();
            case 3 -> verTodosLosVuelos();

                case 4 -> VentasDelSistema();
                case 5, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal");
                    return;
                }
            }
        }
    }
    
    private static void VentasDelSistema() {
        final String[] OPCIONES = {
            "Mostrar reservas", 
            "Mostrar ganancias", 
            "Salir"
        };
        
        while (true){
            int opcion = JOptionPane.showOptionDialog(
                null, 
                "Menú de Administrador", 
                "Reportes del sistema", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                OPCIONES, 
                OPCIONES[0]
            );
            
            switch (opcion) {
                case 0 -> mostrarReservas();
                case 1 -> mostrarGanancias();

                case 2, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menú de administrador");
                    return;
                }
            }
        }
    }

    private static void menuCliente() {
        final String[] OPCIONES = {
            "Ver vuelos disponibles", 
            "Hacer reservación", 
            "Cancelar reservación", 
            "Salir"
        };

        while (true) {
            int opcion = JOptionPane.showOptionDialog(
                null, 
                "Menú de Cliente", 
                "Cliente", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                OPCIONES, 
                OPCIONES[0]
            );

            switch (opcion) {
                case 0 -> verVuelosDisponibles();
                case 1 -> hacerReservacion();
                case 2 -> cancelarReservacion();
                case 3, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal");
                    return;
                }
            }
        }
    }
    
    private static void crearVuelo() {
    if (totalVuelos >= vuelos.length) {
        JOptionPane.showMessageDialog(null, "No se pueden registrar más vuelos");
        return;
    }

    String nombre = JOptionPane.showInputDialog("Nombre del vuelo:");
    String destino = JOptionPane.showInputDialog("Destino:");
    int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo:"));
    int filas = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de filas:"));
    int columnas = Integer.parseInt(JOptionPane.showInputDialog("Asientos por fila:"));

    vuelos[totalVuelos] = new Vuelo(numero, destino, columnas, filas, nombre);
    totalVuelos++;

    JOptionPane.showMessageDialog(null, "Vuelo creado exitosamente");
}

    private static void modificarVuelo() {
    int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo a modificar:"));
    
    for (int i = 0; i < totalVuelos; i++) {
        if (vuelos[i].getNumeroVuelo() == numero) {
            String nuevoDestino = JOptionPane.showInputDialog("Nuevo destino:");
            vuelos[i].setDestino(nuevoDestino);
            JOptionPane.showMessageDialog(null, "Vuelo modificado");
            return;
        }
    }

    JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
}

    private static void eliminarVuelo() {
    int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo a eliminar:"));
    
    for (int i = 0; i < totalVuelos; i++) {
        if (vuelos[i].getNumeroVuelo() == numero) {
            for (int j = i; j < totalVuelos - 1; j++) {
                vuelos[j] = vuelos[j + 1];
            }
            vuelos[totalVuelos - 1] = null;
            totalVuelos--;
            JOptionPane.showMessageDialog(null, "Vuelo eliminado");
            return;
        }
    }

    JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
}

    private static void verTodosLosVuelos() {
    if (totalVuelos == 0) {
        JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
        return;
    }

    String info = "Vuelos registrados:\n";
    for (int i = 0; i < totalVuelos; i++) {
        info += "Vuelo: " + vuelos[i].getNombreDelVuelo() +
                " | Nº: " + vuelos[i].getNumeroVuelo() +
                " | Destino: " + vuelos[i].getDestino() + "\n";
    }

    JOptionPane.showMessageDialog(null, info);
}

    private static void mostrarReservas() {
    JOptionPane.showMessageDialog(null, "Reservas activas: " + totalVentas);
}

    private static void mostrarGanancias() {
    double total = 0;

    for (int i = 0; i < totalVentas; i++) {
        if (ventas[i].getEstado().equals("activa")) {
            total += ventas[i].getMontoTotal();
        }
    }

    JOptionPane.showMessageDialog(null, "Ganancias totales: $" + total);
}

    private static void verVuelosDisponibles() {
    if (totalVuelos == 0) {
        JOptionPane.showMessageDialog(null, "No hay vuelos disponibles");
        return;
    }

    String info = "Vuelos disponibles:\n";
    for (int i = 0; i < totalVuelos; i++) {
        info += "Vuelo: " + vuelos[i].getNombreDelVuelo() +
                " | Nº: " + vuelos[i].getNumeroVuelo() +
                " | Destino: " + vuelos[i].getDestino() +
                " | Asientos: " + vuelos[i].getAsientosDisp() + "\n";
    }

    JOptionPane.showMessageDialog(null, info);
}

    private static void hacerReservacion() {
    if (totalVentas >= ventas.length) {
        JOptionPane.showMessageDialog(null, "No se pueden registrar más ventas");
        return;
    }

    int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo:"));
    for (int i = 0; i < totalVuelos; i++) {
        if (vuelos[i].getNumeroVuelo() == numero) {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de boletos:"));
            if (cantidad <= vuelos[i].getAsientosDisp()) {
                vuelos[i].setAsientosDisp(vuelos[i].getAsientosDisp() - cantidad);

                VentasCliente venta = new VentasCliente();
                venta.idVenta = "V" + (totalVentas + 1);
                venta.fechaVenta = "2025-08-12";
                venta.cantidadBoletos = cantidad;
                venta.montoTotal = cantidad * 100.0;
                venta.estado = "activa";
                venta.metodoPago = "efectivo";
                venta.numeroVuelo = String.valueOf(numero);

                ventas[totalVentas] = venta;
                totalVentas++;

                JOptionPane.showMessageDialog(null, "Reservación exitosa");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "No hay suficientes asientos");
                return;
            }
        }
    }

    JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
}

    private static void cancelarReservacion() {
    String id = JOptionPane.showInputDialog("ID de la venta:");
    
    for (int i = 0; i < totalVentas; i++) {
        if (ventas[i].idVenta.equals(id) && ventas[i].estado.equals("activa")) {
            ventas[i].estado = "cancelada";

            int numero = Integer.parseInt(ventas[i].numeroVuelo);
            for (int j = 0; j < totalVuelos; j++) {
                if (vuelos[j].getNumeroVuelo() == numero) {
                    vuelos[j].setAsientosDisp(vuelos[j].getAsientosDisp() + ventas[i].cantidadBoletos);
                    break;
                }
            }

            JOptionPane.showMessageDialog(null, "Reservación cancelada");
            return;
        }
    }

    JOptionPane.showMessageDialog(null, "Venta no encontrada o ya cancelada");
}

    
}
