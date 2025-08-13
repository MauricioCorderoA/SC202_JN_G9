package com.mycompany.sistemavuelo;
// Registra informacion clave para la gestion de vuelos

import javax.swing.JOptionPane;


public class Administracion {
    private int vuelosCreados=0;         // Total de vuelos registrados 
    private int vuelosModificados=0;     // Vuelos editados 
    private int vuelosEliminados=0;      // Vuelos eliminados 
    
    int contador = 0;
    Vuelo[] vuelos= new Vuelo[100];
    
    //encapsuladores
    public int getContador(){
        return contador;
    }
    
    public Vuelo[] getVuelos(){
        return vuelos;
    }
    
    public int getVuelosCreados(){
        return vuelosCreados;
    }
    
    public int getVuelosModificados(){
        return vuelosModificados;
    }
    
    public int getVuelosEliminados(){
        return vuelosEliminados;
    }
    
    //metodos
    public void crearVuelo(){
        String nombre = JOptionPane.showInputDialog("Nombre del vuelo:");
        String destino = JOptionPane.showInputDialog("Destino:");
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo:"));
        int filas = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de filas:"));
        int columnas = Integer.parseInt(JOptionPane.showInputDialog("Asientos por fila:"));

        vuelos[contador++] = new Vuelo(numero, destino, columnas, filas, nombre);
        JOptionPane.showMessageDialog(null, "Vuelo creado exitosamente");
        vuelosCreados++;
        
    }
    
    public void ModificarVuelo(){
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo a modificar:"));
    
        for (int i = 0; i < contador; i++) {
            if (vuelos[i].getNumeroVuelo() == numero) {
                String nuevoNombre = JOptionPane.showInputDialog("Anterior nombre "+vuelos[i].getNombreDelVuelo()+"\nIngrese el nuevo nombre");
                String nuevoDestino = JOptionPane.showInputDialog("Anterior destino "+vuelos[i].getDestino()+"\nIngrese el nuevo destino de su vuelo");
                int nuevoNumero = Integer.parseInt(JOptionPane.showInputDialog("Anterior numero del vuelo "+vuelos[i].getNumeroVuelo()+"\nIngrese el nuevo numero"));
                int nuevasFilas = Integer.parseInt(JOptionPane.showInputDialog("Anterior numero de filas "+vuelos[i].getFilas()+"\nIngrese la nueva cantidad de filas"));
                int nuevasColumnas = Integer.parseInt(JOptionPane.showInputDialog("Anterior numero de columnas "+vuelos[i].getColumnas()+"\nIngrese la nueva cantidad de asientos por fila"));
                vuelos[i]= new Vuelo(nuevoNumero, nuevoDestino, nuevasColumnas, nuevasFilas, nuevoNombre);
                JOptionPane.showMessageDialog(null, "Vuelo modificado correctamente");
                vuelosModificados++;
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
    }
    
    public void EliminarVuelo(){
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de vuelo a eliminar"));
        for (int i = 0; i < contador; i++) {
            if (vuelos[i].getNumeroVuelo() == numero) {
                for (int j = i; j < contador - 1; j++) {
                    vuelos[j] = vuelos[j + 1];
                }
                vuelos[contador - 1] = null;
                contador--;
                JOptionPane.showMessageDialog(null, "Vuelo eliminado");
                vuelosEliminados++;
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Vuelo no encontrado");
    }
    
    public void VuelosRegistrados(){
        if (contador == 0) {
        JOptionPane.showMessageDialog(null, "No hay vuelos registrados");
        return;
        }

        String info = "Vuelos registrados:\n";
        for (int i = 0; i < contador; i++) {
            info += "Vuelo: " + vuelos[i].getNombreDelVuelo() +
                    " | Nº: " + vuelos[i].getNumeroVuelo() +
                 " | Destino: " + vuelos[i].getDestino() + 
                  "| Asientos totales: "+vuelos[i].getAsientosTotales()+"\n";
        }

        JOptionPane.showMessageDialog(null, info);
    }
}


