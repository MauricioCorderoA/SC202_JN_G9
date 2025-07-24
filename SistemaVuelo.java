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
    
    private static final Administrador ADMIN = new Administrador("4dm1n", "1234A");
    private static final Cliente CLIENTE = new Cliente("cl13nt3", "9876C");

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
                case 0 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
                case 1 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
                case 2 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
                case 3 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
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
                case 0 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
                case 1 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
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
                case 0 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
                case 1, 2 -> JOptionPane.showMessageDialog(null, "Función en desarrollo");
                case 3, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Volviendo al menú principal");
                    return;
                }
            }
        }
    }
    
}
