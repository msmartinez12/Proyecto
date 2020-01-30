package trabajofinal;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TrabajoFinal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;

        System.out.printf("%20s%n", "OPCIONES");
        System.out.println("1)Generar una cuenta nueva");
        System.out.println("2)Realizar un deposito en una cuenta existente");
        System.out.println("3)Realizar un retiro en una cuneta existente");
        System.out.println("4)Revisar el certificado de saldos de una cuenta existente ");
        System.out.println("5)Revisar el reporte de saldos de todos los clientes");
        System.out.print("Ingrese el numero de la accion que desea realizar: ");
        opcion = sc.nextInt();
        System.out.println("");
        sc.nextLine();
        try {
            switch (opcion) {
                case 1:

                    System.out.println("Usted desea crear una nueva cuenta");

                    System.out.print("Ingrese sus dos nombres por favor: ");
                    String nombres = sc.nextLine();
                    System.out.print("Ingrese su primero apellido por favor: ");
                    String apellido = sc.nextLine();
                    System.out.print("Ingrese su numero de cedula por favor: ");
                    String cedula = sc.nextLine();

                    File archivo1 = new File("cuentas/" + cedula + ".txt");

                    if (!archivo1.exists()) {
                        archivo1.createNewFile();
                        PrintWriter pw1 = new PrintWriter(archivo1);
                        pw1.println("saldo|1000");
                        pw1.close();

                        FileWriter archivo2 = new FileWriter("clientes.txt", true);
                        PrintWriter pw2 = new PrintWriter(archivo2);
                        pw2.println(nombres + "|" + apellido + "|" + cedula);
                        pw2.close();

                    } else {
                        System.out.println("Usted ya posee una cuenta de ahorros");
                    }

                    break;

                case 2:
                    System.out.println("Usted desea realizar un deposito en su cuenta");

                    System.out.print("Ingrese su numero de cedula por favor: ");
                    String cedulaComprobar = sc.nextLine();

                    File archivo3 = new File("cuentas/" + cedulaComprobar + ".txt");
                    FileWriter archivo4 = new FileWriter("cuentas/" + cedulaComprobar + ".txt", true);

                    if (!archivo3.exists()) {
                        System.out.println("Usted no posee una ceunta de ahorros");
                    } else {
                        System.out.print("Ingrse la cantidad de dinero que va a depostitar: ");
                        String abonar = sc.nextLine();
                        PrintWriter pw3 = new PrintWriter(archivo4);
                        pw3.println("deposito" + "|" + abonar);
                        pw3.close();
                    }
                    break;

                case 3:
                    System.out.println("Usted desea realizar un retiro en su cuenta");
                    System.out.print("Ingrese su numero de cedula por favor: ");
                    String cedulaComprobar2 = sc.nextLine();

                    File archivo5 = new File("cuentas/" + cedulaComprobar2 + ".txt");
                    FileWriter archivo6 = new FileWriter("cuentas/" + cedulaComprobar2 + ".txt", true);
                    Scanner entrada1 = new Scanner(archivo5);
                    if (!archivo5.exists()) {
                        System.out.println("Usted no posee una ceunta de ahorros");
                    } else {
                        System.out.print("Ingrse la cantidad de dinero que va a retirar: ");
                        String retirar = sc.nextLine();
                        String[] lista = new String[2];
                        int saldo = 0;
                        while (entrada1.hasNextLine()) {
                            lista = entrada1.nextLine().split("|");
                            if (lista[0].equals("saldo")) {
                                saldo = Integer.parseInt(lista[1]);
                            }
                        }
                        if (Integer.parseInt(retirar) <= saldo) {
                            PrintWriter pw4 = new PrintWriter(archivo6);
                            pw4.println("retiro" + "|" + retirar);
                            pw4.close();
                        } else {
                            System.out.println("Saldo unsuficiente para realizar la transaccion");
                        }

                    }
                    break;

                case 4:
                    System.out.print("Ingrese su numero de cedula: ");
                    String cedulaComprobar3 = sc.nextLine();
                    //String[] lista3 = new String[2];
                    File archivo10 = new File("cuentas/" + cedulaComprobar3 + ".txt");
                    File archivo11 = new File("certificaciones/" + "certificado_"+ cedulaComprobar3 + ".txt");

                    if (!archivo10.exists()) {
                        System.out.println("Usted no posee una cuenta de ahorros");
                    } else {
                        archivo11.createNewFile();
                        PrintWriter pw3 = new PrintWriter(archivo11);
                        Scanner entrada3 = new Scanner(archivo10);
                        int saldoFinal0 = 0,
                                aporteFinal0 = 0,
                                retiroFinal0 = 0;
                        while (entrada3.hasNextLine()) {

                            String[] lista3 = new String[2];
                            lista3 = entrada3.nextLine().split("\\|");
                            if (lista3[0].equals("saldo")) {
                                saldoFinal0 = Integer.parseInt(lista3[1]);
                            } else {
                                if (lista3[0].equals("deposito")) {
                                    aporteFinal0 = aporteFinal0 + Integer.parseInt(lista3[1]);
                                } else {
                                    retiroFinal0 = retiroFinal0 + Integer.parseInt(lista3[1]);
                                }
                            }

                        }
                        
                        saldoFinal0 = saldoFinal0 + aporteFinal0 - retiroFinal0;
                        
                        pw3.println("Certificado de Saldos");
                        pw3.println("dentificacion del cliente: " + cedulaComprobar3);
                        pw3.println("Saldo a la fecha: " + saldoFinal0);
                        pw3.close();
                        
                    }

                    break;

                case 5:
                    int saldoFinal = 0,
                     aporteFinal = 0,
                     retiroFinal = 0;

                    File dir = new File("cuentas/");
                    String clientes[] = dir.list();
                    for (int i = 0; i < clientes.length; i++) {
                        File archivo7 = new File("cuentas/" + clientes[i]);
                        Scanner entrada2 = new Scanner(archivo7);
                        saldoFinal = 0;
                        aporteFinal = 0;
                        retiroFinal = 0;
                        while (entrada2.hasNextLine()) {
                            String[] lista2 = new String[2];
                            lista2 = entrada2.nextLine().split("\\|");
                            if (lista2[0].equals("saldo")) {
                                saldoFinal = Integer.parseInt(lista2[1]);
                            } else {
                                if (lista2[0].equals("deposito")) {
                                    aporteFinal = aporteFinal + Integer.parseInt(lista2[1]);
                                } else {
                                    retiroFinal = retiroFinal + Integer.parseInt(lista2[1]);
                                }

                            }

                        }

                        saldoFinal = saldoFinal + aporteFinal - retiroFinal;

                        FileWriter archivo8 = new FileWriter("saldos_clientes.txt", true);
                        PrintWriter pw5 = new PrintWriter(archivo8);
                        pw5.println(clientes[i].substring(0, (clientes[i].length() - 4)) + "|" + saldoFinal);

                        pw5.close();
                    }

                    File archivo9 = new File("saldos_clientes.txt");
                    Scanner entrada3 = new Scanner(archivo9);
                    String lista3[] = new String[2];
                    while (entrada3.hasNextLine()) {
                        lista3 = entrada3.nextLine().split("\\|");
                        System.out.println(lista3[0] + "|" + lista3[1]);
                    }

                    break;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

}
