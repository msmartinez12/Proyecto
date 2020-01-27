package proyectov1;

import java.util.Scanner;
import java.io.*;

public class ProyectoV1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su usuario: ");
        String usuario = sc.nextLine();
        int numAlumnos;
        int actividadesTotales;

        try {
            File archivo = new File(usuario + ".txt");
            //en el caso de que el usuario no exista se piden los datos iniciales
            if (!archivo.exists()) {
                archivo.createNewFile();

                System.out.print("Ingrese a cuantos alumnos les da clase: ");
                numAlumnos = sc.nextInt();
                System.out.print("Ingrese cuantas activiades desea promediar: ");
                actividadesTotales = sc.nextInt();

                String[] nombreActividades = new String[actividadesTotales];

                for (int i = 0; i < actividadesTotales; i++) {
                    if (i == 0) {
                        sc.nextLine();
                    }

                    System.out.print("Ingrese el nombre de la actividad " + (i + 1) + ": ");
                    nombreActividades[i] = sc.nextLine();

                }

                PrintWriter pw = new PrintWriter(archivo);

                String[] nombreAlumnos = new String[numAlumnos];
                double[][] notas = new double[numAlumnos][actividadesTotales];
                double suma = 0;
                for (int i = 0; i < numAlumnos; i++) {
                    System.out.println("");
                    System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
                    nombreAlumnos[i] = sc.nextLine();
                    for (int j = 0; j < actividadesTotales; j++) {
                        System.out.print("Nota de " + nombreAlumnos[i] + " en la actividad " + nombreActividades[j] + ": ");
                        notas[i][j] = sc.nextDouble();
                        sc.nextLine();
                        suma = suma + notas[i][j];
                        pw.print(nombreAlumnos[i] + ";" + nombreActividades[j] + ";" + notas[i][j] + ";");
                    }

                }
                pw.close();
                //en el caso de que el usuario exista se muestran los datos
            } else {
                System.out.println("Usted a ingresado");
                Scanner entrada = new Scanner(archivo);
                int c = 0; //la c sirve para contar el numero de lineas del documento;
                int p = 0; // la p cuneta el numero de personas
                String[] calificaciones = new String[3];
                String nombre = ""; //ayuda a comparar los nombres para verificar cuantas personas hay;

                while (entrada.hasNextLine()) {
                    c++;
                    calificaciones = entrada.nextLine().split(";");
                    if (nombre.equals(calificaciones[0])) {
                    } else {
                        nombre = calificaciones[0];
                        p++;
                    }
                }
                entrada.close();
                
                String[] nombreAlumnos = new String[p];
                String[] nombreActividades = new String[c / p];
                Double[] notas = new Double[p * nombreActividades.length];

                int z = 0; // z sirve de contador

                // se abre un nuevo scanner pues el anteiror quedo en la ultima linea
                Scanner entrada1 = new Scanner(archivo);

                while (z < c / p) {

                    calificaciones = entrada1.nextLine().split(";");
                    nombreActividades[z] = calificaciones[1];
                    z++;
                }
                
                entrada1.close();

                Scanner entrada2 = new Scanner(archivo);

                nombre = "";
                z = 0; //se vuelve a inicializar el contador para no abrir una nueva variable.

                while (z < p) {
                    calificaciones = entrada2.nextLine().split(";");
                    if (!nombre.equals(calificaciones[0])) {
                        nombre = calificaciones[0];
                        nombreAlumnos[z] = calificaciones[0];
                        z++;
                    }
                }
                z = 0;
                
                entrada2.close();
                
                Scanner entrada3 = new Scanner(archivo);

                double[] promedio = new double[p];
                double suma = 0;

                while (entrada3.hasNextLine()) {
                    calificaciones = entrada3.nextLine().split(";");
                    notas[z] = Double.parseDouble(calificaciones[2]);
                    z++;
                }
                
                entrada3.close();

                int k = 0;
                for (int i = 0; i < p; i++) {
                    suma = 0;
                    for (int j = 0; j < nombreActividades.length; j++) {
                        suma = suma + notas[k];
                        k++;
                    }
                    promedio[i] = suma/nombreActividades.length;
                }
                for (int i = 0; i < nombreActividades.length; i++) {
                    System.out.printf("%25s", nombreActividades[i]);
                }
                System.out.printf("%14s", "Promedio");

                System.out.println("");
                k = 0;

                for (int i = 0; i < p; i++) {
                    System.out.printf("%-10s", nombreAlumnos[i]);
                    for (int j = 0; j < nombreActividades.length; j++) {
                        System.out.printf("%15s          ", notas[k]);
                        k++;
                    }
                    System.out.printf("%.2f",promedio[i]);
                    System.out.println("");
                }

            }
        } catch (Exception e) {
            System.err.print("Error " + e.getLocalizedMessage());
        }

    }
}
