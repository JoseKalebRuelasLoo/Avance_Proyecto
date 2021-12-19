/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author jose kaleb
 */
public class Nodo {

    private String dato = null;
    private Nodo siguiente;
    private String Nombre;
    private String Hora;

    //constructores
    public Nodo() {//default
        this.siguiente = null;
    }

    public Nodo(String dato) {//default
        this.dato = dato;
        this.siguiente = null;
    }

    //get y set 
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        this.Hora = hora;
    }

    /*dosis todavia no implementado
    private boolean Confirmacion;

    public boolean getConfirmacion() {
        return Confirmacion;
    }

    public void setConfirmacion(boolean confirmacion) {
        this.Confirmacion = confirmacion;
    }*/

}

/* public void printAlarmas() {
        a = "";
        alarmas(inicio);
        JOptionPane.showMessageDialog(null,a);
    }

    private void alarmas(Nodo actual) {
        //WHILE
        if (actual != null) {
            String b = null;
            b = "La alarma de " + actual.getDato() + " sonara a las: " + actual.getHora()+"\n";
            a = a+b;
            alarmas(actual.getSiguiente());
        }
    }*/