/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemavuelo;

import javax.swing.JOptionPane;
// Link del GitHub https://github.com/MauricioCorderoA/SC202_JN_G9.git
/**
 *
 * @author Maytan
 */
public class SistemaVuelo {
    
    
    private static final Administrador ADMIN = new Administrador("Admin", "1234A");
    private static final Cliente CLIENTE = new Cliente("cliente", "9876C");
    
    public static void main(String[] args) {
        Datos();
        iniciarSistema();
    }
    
    private static void iniciarSistema() {
        Administracion administracion = new Administracion();
        VentasCliente ventasclientes = new VentasCliente(administracion);
        ReportesVentas reportesventas = new ReportesVentas(administracion, ventasclientes);
        
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

                if (tipoUsuario == 0 && id.equals(ADMIN.getIdAdministrador()) && password.equals(ADMIN.getContrasenaAdmin())) {
                    if (ADMIN.getNombreAdmin() == null){
                        ADMIN.setNombreAdmin(JOptionPane.showInputDialog("Ingrese el nombre que desea registrar"));
                    }
                    menuAdministrador(administracion, reportesventas, ventasclientes);
                    accesoConcedido = true;
                } 
                else if (tipoUsuario == 1 && id.equals(CLIENTE.getIdCliente()) && password.equals(CLIENTE.getContrasenaCliente())) {
                    if (CLIENTE.getNombreCliente() == null && CLIENTE.getPasaporte() == null){
                        CLIENTE.setNombreCliente(JOptionPane.showInputDialog("Ingrese el nombre que desea registrar"));
                        CLIENTE.setPasaporte(JOptionPane.showInputDialog("Ingrese su pasaporte"));
                    }
                    menuCliente(ventasclientes, administracion);
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
                "Cambiar sesion", 
                JOptionPane.YES_NO_OPTION);
            
            if (continuar != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Gracias por usar nuestro sistema\nSesion finalizada");
                return;
            }
        }
    }
    
    private static void Datos() {
        System.out.println("Recuerde que los datos de sus usuarios son los siguientes");
        System.out.println("Datos de administrador");
        ADMIN.MostrarDatosAdmin();
        System.out.println("Datos de cliente");
        CLIENTE.MostrarDatosCliente();
    }
    
    private static void menuAdministrador(Administracion administracion, ReportesVentas reportesventas, VentasCliente ventasclientes) {
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
                "Menu de Administrador", 
                "Administración", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                OPCIONES, 
                OPCIONES[0]
            );

            switch (opcion) {
                case 0 -> administracion.crearVuelo();
                case 1 -> administracion.ModificarVuelo();
                case 2 -> administracion.EliminarVuelo();
                case 3 -> administracion.VuelosRegistrados();
                case 4 -> VentasDelSistema(reportesventas, ventasclientes);
                case 5, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menu principal");
                    return;
                }
            }
        }
    }
    
    private static void VentasDelSistema(ReportesVentas reportesventas, VentasCliente ventasclientes) {
        final String[] OPCIONES = {
            "Mostrar acciones realizadas", 
            "Mostrar ganancias", 
            "Salir"
        };
        
        while (true) {
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
                case 0 -> reportesventas.mostrarDatosVuelos();
                case 1 -> reportesventas.mostrarganancias();
                case 2, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menu de administrador");
                    return;
                }
            }
        }
    }

    private static void menuCliente(VentasCliente ventasclientes, Administracion administracion) {
        final String[] OPCIONES = {
            "Ver vuelos disponibles", 
            "Hacer reservacion", 
            "Cancelar reservacion",
            "Ver mis facturas",  // Texto actualizado
            "Salir"
        };

        while (true) {
            int opcion = JOptionPane.showOptionDialog(
                null, 
                "Menu de Cliente", 
                "Cliente", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                OPCIONES, 
                OPCIONES[0]
            );

            switch (opcion) {
                case 0 -> administracion.VuelosRegistrados();
                case 1 -> ventasclientes.Reservar();
                case 2 -> ventasclientes.CancelarReserva();
                case 3 -> ventasclientes.mostrarFacturasCliente();  // Nueva funcionalidad
                case 4, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menu principal");
                    return;
                }
            }
        }
    }
    
}