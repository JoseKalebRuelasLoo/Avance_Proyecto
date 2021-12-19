package proyecto;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class listaAlarmas {

    Scanner input = new Scanner(System.in);

    private Nodo inicio;
    private Nodo fin;
    private String a;
    private int tama;

    public listaAlarmas() {
        inicio = null;
        fin = null;
        tama = 0;
    }

    public void add() {
        String nombre = JOptionPane.showInputDialog("Nombre de la alarma");
        Nodo nuevo = new Nodo(nombre);
        //HAY NODOS EN LA LISTA?
        if (inicio == null) { //LISTA VACIA
            inicio = nuevo;//conectamos el primer nodo a la lista
            fin = nuevo;
            inicio.setNombre(nombre);
            String hora = JOptionPane.showInputDialog("¿A que hora es su alarma? Formato de 24 horas porfavor");
            inicio.setHora(hora);
        } else { //LISTA CON NODOS
            fin.setNombre(nombre);
            System.out.println("");
            fin.setSiguiente(nuevo);
            fin = nuevo;
            String hora = JOptionPane.showInputDialog("¿A que hora es su alarma? Formato de 24 horas porfavor");
            fin.setHora(hora);
        }
        tama++;
    }

    public void printAlarmas() {
        a = "";
        alarmas(inicio);
        JOptionPane.showMessageDialog(null, a);
    }

    private void alarmas(Nodo actual) {
        if (actual != null) {
            String b = "";
            b = "La alarma de " + actual.getDato() + " sonara a las: " + actual.getHora() + "\n";
            a = a + b;
            alarmas(actual.getSiguiente());
        }
    }

    public void clear() {
        inicio = null;
        fin = null;
        tama = 0;
    }

    public int length() { //O(1)
        return tama;
    }

    public void borrar(String nombre) {
        delete(nombre, inicio);
    }

    private void delete(String nombre, Nodo actual) {// O(N)
        //VALIDAR
        if (actual != null) {
            if (length() == 1 && inicio.getDato().equals(nombre)) {
                clear();
            } else if (inicio.getDato().equals(nombre)) {//BORRAR UN NODO AL INICIO
                inicio = inicio.getSiguiente();
                tama--;
            } else if (actual.getSiguiente().getDato().equals(nombre)) {//CUALQUIER OTRO CASO
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tama--;
            } else {
                delete(nombre, actual.getSiguiente());
            }
        } else if (actual == null) {
            JOptionPane.showMessageDialog(null, "No se encontro la alarma");
        }
        JOptionPane.showMessageDialog(null, "Alarma eliminada");
    }

    public void editarAlarma(String nombre) {
        editar(nombre, inicio);
    }

    private void editar(String nombre, Nodo actual) {// O(N)
        //VALIDAR
        if (actual != null) {
            if (actual.getDato().equals(nombre)) {
                int opc = JOptionPane.showOptionDialog(null, "¿Que es lo que desea editar?", "Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Nombre", "Hora", "Cancelar"}, "Nombre");
                if (opc == 0) {
                    String nombreNu = JOptionPane.showInputDialog("Nombre de la alarma");
                    actual.setNombre(nombreNu);
                    JOptionPane.showMessageDialog(null, "Alarma editada");
                } else if (opc == 1) {
                    String hora = JOptionPane.showInputDialog("¿A que hora es su alarma? Formato de 24 horas porfavor");
                    actual.setHora(hora);
                    JOptionPane.showMessageDialog(null, "Alarma editada");
                } else if (opc == 2) {
                        System.out.print("");
                }
            } else {
                editar(nombre, actual.getSiguiente());
            }
        } else if (actual == null) {
            JOptionPane.showMessageDialog(null, "No se encontro la alarma");
        }
    }

}
