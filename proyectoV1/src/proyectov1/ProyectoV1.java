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
            if (!archivo.exists()) {
                archivo.createNewFile();

                System.out.print("Ingrese a cuantos alumnos les da clase: ");
                numAlumnos = sc.nextInt();
                System.out.print("Ingrese cuantas activiades desea promediar: ");
                actividadesTotales = sc.nextInt();

                String[] nombreActividades = new String[actividadesTotales];

                for (int i = 0; i < actividadesTotales; i++) {
                    System.out.print("Ingrese el nombre de la actividad " + (i + 1) + ": ");
                    nombreActividades[i] = sc.next();

                }

                PrintWriter pw = new PrintWriter(archivo);

                String[] nombreAlumnos = new String[numAlumnos];
                int[][] notas = new int[numAlumnos][actividadesTotales];

                for (int i = 0; i < numAlumnos; i++) {
                    System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
                    nombreAlumnos[i] = sc.next();
                    for (int j = 0; j < actividadesTotales; j++) {
                        System.out.print("Nota de " + nombreAlumnos[i] + " en la actividad " + nombreActividades[j] + ": ");
                        notas[i][j] = sc.nextInt();

                        pw.println(nombreAlumnos[i] + ";" + nombreActividades[j] + ";" + notas[i][j]);
                    }

                }
                pw.close();
            } else {
                System.out.println("Usted a ingresado");
                Scanner entrada = new Scanner(archivo);

                String[] calificaciones = new String[3];
                while (entrada.hasNextLine()) {
                    calificaciones = entrada.nextLine().split(";");
                    
                    System.out.printf("%-5s%10s%5s\n", calificaciones[0],calificaciones[1],calificaciones[2]);
                }
            }
        } catch (Exception e) {
            System.out.print("Error " + e.getMessage());
        }

    }
}
