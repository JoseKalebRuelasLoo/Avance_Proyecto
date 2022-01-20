package proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class listaAlarmas extends Thread {

    Scanner input = new Scanner(System.in);
    DateTimeFormatter h = DateTimeFormatter.ofPattern("HH:mm");
    private Nodo inicio;
    private Nodo fin;
    private String a;
    private int tama;

    public listaAlarmas() {
        inicio = null;
        fin = null;
        tama = 0;
        try {
            comprobar();
        } catch (IOException ex) {
            Logger.getLogger(listaAlarmas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comprobar() throws IOException {
        File archivo = new File(System.getProperty("user.dir") + "\\src\\Recursos\\configuracion.txt");
        if (archivo.exists()) {
            if (archivo.length() == 0) {
                inicio = null;
                fin = null;
                tama = 0;
            } else {
                BufferedReader bf;
                try {
                    bf = new BufferedReader(new FileReader(archivo));
                    String a = bf.readLine();
                    Nodo nuevo = new Nodo(a);
                    inicio = nuevo;
                    fin = nuevo;
                    inicio.setNombre(a);
                    inicio.setHora(bf.readLine());
                    tama = 1;
                    llenarDatosDoc(bf);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(listaAlarmas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            PrintWriter writer;
            writer = new PrintWriter(System.getProperty("user.dir") + "\\src\\Recursos\\configuracion.txt");
            writer.close();
            inicio = null;
            fin = null;
            tama = 0;
        }
    }

    private void llenarDatosDoc(BufferedReader bf) throws FileNotFoundException, IOException {
        String Nombre = bf.readLine();
        if (Nombre != null) {
            String Hora;
            tama++;
            Nodo nuevo = new Nodo(Nombre);
            fin.setNombre(Nombre);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            if ((Hora = bf.readLine()) != null) {
                fin.setHora(Hora);
            }
            llenarDatosDoc(bf);
        }
    }

    public void add() {
        String nombre = JOptionPane.showInputDialog("Nombre de la alarma.");
        if (nombre != null && nombre.compareTo("") != 0) {
            Nodo nuevo = new Nodo(nombre);
            //HAY NODOS EN LA LISTA?
            if (inicio == null) { //LISTA VACIA
                inicio = nuevo;//conectamos el primer nodo a la lista
                fin = nuevo;
                inicio.setNombre(nombre);
                int a = 0;
                do {
                    String hora = JOptionPane.showInputDialog("Ingrese la Hora. \n Formato de 24 horas porfavor.");
                    if (hora != null && hora.compareTo("") != 0 && hora.length() <= 2 && (Integer.parseInt(hora) <= 24)) {
                        if (hora.length() == 1) {
                            hora = "0" + hora;
                        }
                        int b = 0;
                        do {
                            String minutos = JOptionPane.showInputDialog("Ingrese los minutos.");
                            if (minutos != null && minutos.compareTo("") != 0 && minutos.length() <= 2 && (Integer.parseInt(minutos) <= 60)) {
                                hora = hora + ":" + minutos;
                                inicio.setHora(hora);
                                a = 1;
                                b = 1;
                            } else {
                                JOptionPane.showMessageDialog(null, "Por favor ingrese los dato de manera correcta.");
                            }
                        } while (b == 0);
                        //inicio.setHora(hora);
                        a = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor escriba una hora correcta.");
                    }
                    inicio.setHora(hora);
                } while (a == 0);
            } else { //LISTA CON NODOS
                fin.setNombre(nombre);
                fin.setSiguiente(nuevo);
                fin = nuevo;
                int a = 0;
                do {
                    String hora = JOptionPane.showInputDialog("Ingrese la Hora. \n Formato de 24 horas porfavor.");
                    if (hora != null && hora.compareTo("") != 0 && hora.length() <= 2 && (Integer.parseInt(hora) <= 24)) {
                        if (hora.length() == 1) {
                            hora = "0" + hora;
                        }
                        int b = 0;
                        do {
                            String minutos = JOptionPane.showInputDialog("Ingrese los minutos.");
                            if (minutos != null && minutos.compareTo("") != 0 && minutos.length() <= 2 && (Integer.parseInt(minutos) <= 60)) {
                                if (minutos.length() == 1) {
                                    minutos = "0" + minutos;
                                }
                                hora = hora + ":" + minutos;
                                a = 1;
                                b = 1;
                            } else {
                                JOptionPane.showMessageDialog(null, "Por favor ingrese los dato de manera correcta.");
                            }
                        } while (b == 0);
                        a = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor escriba una hora correcta.");
                    }
                    fin.setHora(hora);
                } while (a == 0);
            }
            tama++;
            JOptionPane.showMessageDialog(null, "Alarma añadida");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor escriba un nombre valido");
            add();
        }
    }

    public void printAlarmas() {
        if (tama != 0) {
            a = "";
            alarmas(inicio);
            JOptionPane.showMessageDialog(null, a);
        } else {
            JOptionPane.showMessageDialog(null, "No existen alarmas.");
        }
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

    public void borrar() {
        String nombre = JOptionPane.showInputDialog("¿Que alarma quiere borrar?\n Inserte el nombre de la alarma.");
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
            JOptionPane.showMessageDialog(null, "No se encontro la alarma.");
        }
        JOptionPane.showMessageDialog(null, "Alarma eliminada.");
    }

    public void editarAlarma() {
        String nombre = JOptionPane.showInputDialog("¿Que alarma quiere editar?\n Inserte el nombre de la alarma.");
        editar(nombre, inicio);
    }

    private void editar(String nombre, Nodo actual) {// O(N)
        //VALIDAR
        if (actual != null) {
            if (actual.getDato().equals(nombre)) {
                int opc = JOptionPane.showOptionDialog(null, "¿Que es lo que desea editar?",
                        "Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new Object[]{"Nombre", "Hora", "Cancelar"}, "Nombre");
                if (opc == 0) {
                    int a = 0;
                    do {
                        String nombreNu = JOptionPane.showInputDialog("Nombre de la alarma\n" + actual.getDato() + "\n¿Que nombre le quiere poner a su alarma?");
                        if (nombreNu != null && nombreNu.compareTo("") != 0) {
                            actual.setDato(nombreNu);
                            a = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor escriba un nombre");
                        }
                    } while (a == 0);
                    JOptionPane.showMessageDialog(null, "Alarma editada.\n Nuevo nombre: \n" + actual.getDato());
                } else if (opc == 1) {
                    int a = 0;
                    do {
                        String hora = JOptionPane.showInputDialog("Hora de la alarma actual\n" + actual.getHora() + "\nIngrese la nueva hora en formato de 24 horas porfavor.");
                        if (hora != null && hora.compareTo("") != 0 && hora.length() <= 2 && (Integer.parseInt(hora) <= 24)) {
                            if (hora.length() == 1) {
                                hora = "0" + hora;
                            }
                            int b = 0;
                            do {
                                String minutos = JOptionPane.showInputDialog("Ingrese los minutos.");
                                if (minutos != null && minutos.compareTo("") != 0 && minutos.length() <= 2 && (Integer.parseInt(minutos) <= 60)) {
                                    if (minutos.length() == 1) {
                                        minutos = "0" + minutos;
                                    }
                                    hora = hora + ":" + minutos;
                                    a = 1;
                                    b = 1;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Por favor ingrese los dato de manera correcta.");
                                }
                            } while (b == 0);
                            a = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor escriba una hora correcta.");
                        }
                        fin.setHora(hora);
                    } while (a == 0);
                    JOptionPane.showMessageDialog(null, "Alarma editada.\n Nueva hora: \n" + actual.getHora());

                } else if (opc == 2) {
                    System.out.print("");
                }
            } else {
                editar(nombre, actual.getSiguiente());
            }
        } else if (actual == null) {
            JOptionPane.showMessageDialog(null, "No se encontro la alarma.");
        }
    }

    public void run() {
        sonar(inicio);
    }

    private void sonar(Nodo actual) {
        if (actual != null) {
            if (actual.getHora().equals(h.format(LocalDateTime.now()))) {
                JOptionPane.showMessageDialog(null, actual.getNombre() + " " + actual.getHora());
            }
            sonar(actual.getSiguiente());
        }
        try {
            Thread.sleep(60000);
            sonar(inicio);
        } catch (InterruptedException ex) {
        }
    }

    public void cerrar() throws FileNotFoundException {
        JOptionPane.showMessageDialog(null, "Gracias por utilizar.");
        PrintWriter writer2 = new PrintWriter(System.getProperty("user.dir") + "\\src\\Recursos\\configuracion.txt");
        guardarDatos(inicio, writer2);
        System.exit(0);
    }

    private void guardarDatos(Nodo actual, PrintWriter writer) {// O(N)
        if (actual != null) {
            try {
                writer.println(actual.getDato());
                writer.println(actual.getHora());
            } catch (Exception e) {
                e.printStackTrace();
            }
            guardarDatos(actual.getSiguiente(), writer);
        }
        writer.close();
    }
}
