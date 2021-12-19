package proyecto;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class PROYECTO {

    public static void main(String[] args) throws InterruptedException {
        listaAlarmas listaalarmas = new listaAlarmas();
        do {
            String menu = JOptionPane.showInputDialog("¿Que desea hacer?"
                    + "\n 1. Añadir alarma"
                    + "\n 2. Imprimir alarmas"
                    + "\n 3. Editar alarma"
                    + "\n 4. Eliminar alarma"
                    + "\n 5. Salir del programa");
            int numMenu = Integer.parseInt(menu);
            switch (numMenu) {
                case 1://añadir
                    listaalarmas.add();
                    break;
                case 2://mostrar
                    listaalarmas.printAlarmas();
                    break;
                case 3://editar
                    String editar = JOptionPane.showInputDialog("¿Que alarma quiere editar?\n Inserte el nombre de la alarma");
                    listaalarmas.editarAlarma(editar);
                    break;
                case 4://eliminar
                    String borrar = JOptionPane.showInputDialog("¿Que alarma quiere borrar?\n Inserte el nombre de la alarma");
                    listaalarmas.borrar(borrar);
                    break;
                case 5://salir
                    System.out.println("Gracias por utilizar");
                    //Se termina el codigo
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ingrese una respuesta correcta");
                    break;
            }
        } while (0 == 0);
    }
}
//Codigo a usar a futuro
        /*DateTimeFormatter h = DateTimeFormatter.ofPattern("HH:");
        DateTimeFormatter m = DateTimeFormatter.ofPattern("mm");
        Hora hora = new Hora();
        hora.setHora(h.format(LocalDateTime.now()));
        hora.setMinutos(m.format(LocalDateTime.now()));
        hora.getHora();
        hora.getMinutos();
        Thread.sleep(120000);
        System.out.println("Son las " + hora.getHora() + hora.getMinutos());*/
