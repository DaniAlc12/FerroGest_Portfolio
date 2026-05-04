package ferrogest.vista;

import ferrogest.controller.GestorFerroviario;
import ferrogest.exceptions.CapacidadExcedidaException;
import ferrogest.exceptions.MercanciaInvalidaException;
import ferrogest.models.TrenMercancias;
import ferrogest.models.TrenPasajeros;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorFerroviario gf = new GestorFerroviario();
        boolean salir = false;
        int opcion;

        do {
            System.out.println("===========================");
            System.out.println("       MENÚ PRINCIPAL      ");
            System.out.println("===========================");
            System.out.println("1. Registrar Tren de Pasajeros");
            System.out.println("2. Registrar Tren de Mercancias");
            System.out.println("3. Cargar Tren");
            System.out.println("4. Guardar Auditoria");
            System.out.println("5. Guardar Estado");
            System.out.println("6. Cargar Estado");
            System.out.println("7. Salir");
            System.out.print("Por favor, elige una opción: ");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("\n--> Has elegido Registrar un Tren de Pasajeros");
                        System.out.println("--> Introduce el id de tu tren");
                        String idTrenPas = scanner.next();
                        if(gf.isIdUnique(idTrenPas)){
                            System.out.println("-->Introduce la capacidad maxima del tren ");
                            double cargaMaximaPas = scanner.nextDouble();
                            System.out.println("--> Introduce el precio del billete");
                            double precioBilletePas = scanner.nextDouble();
                            System.out.println("--> Introduce el numero de vagones");
                            int numVagonesPas = scanner.nextInt();
                            TrenPasajeros trenP = new TrenPasajeros(idTrenPas,cargaMaximaPas,precioBilletePas,numVagonesPas);
                            gf.registrarTren(trenP);
                        }else{
                            System.out.println("[!] Error: El ID introducido ya pertenece a otro tren.");
                        }
                        break;
                    case 2:
                        System.out.println("\n--> Has elegido Registrar un Tren de Mercancias");
                        System.out.println("--> Introduce el id de tu tren");
                        String idTrenMer = scanner.next();
                        if(gf.isIdUnique(idTrenMer)){
                            System.out.println("-->Introduce la capacidad maxima del tren ");
                            double cargaMaximaMer = scanner.nextDouble();
                            boolean mercanciaValida = false;
                            while (!mercanciaValida) {
                                System.out.println("--> Introduce el tipo de mercancia (Peligrosa/Perecedera/Estandar):");
                                String tipoMercancia = scanner.next();
                                try {
                                    TrenMercancias trenM = new TrenMercancias(idTrenMer, cargaMaximaMer, tipoMercancia);
                                    gf.registrarTren(trenM);
                                    mercanciaValida = true;
                                    System.out.println("[*] Tren registrado con éxito.");
                                } catch (MercanciaInvalidaException e) {
                                    System.out.println("[!] Error: " + e.getMessage() + ". Inténtalo de nuevo.");
                                }
                            }
                        }else{
                            System.out.println("[!] Error: El ID introducido ya pertenece a otro tren.");
                        }
                        break;
                    case 3:
                        System.out.println("\n--> Has elegido Cargar Tren");
                        System.out.println();
                        System.out.println("--> Introduce el id de tu tren");
                        String idTren = scanner.next();
                        if(!gf.isIdUnique(idTren)){
                            try{
                                System.out.println("-->Cuanta cantidad quieres cargar en el tren?");
                                double cantidadCarga = scanner.nextDouble();
                                gf.procesarCarga(idTren,cantidadCarga);
                            }catch(CapacidadExcedidaException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case 4:
                        System.out.println("\n--> Has elegido Guardar Auditoria");
                        System.out.println();
                        try{
                            gf.guardarAuditoriaTexto("auditoriaTrenes.txt");
                        } catch (IOException e) {
                            System.out.println("[!] Error de archivo: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("\n--> Has elegido Guardar Estado");
                        System.out.println();
                        try{
                            gf.guardarEstadoSistema("auditoriaTrenes.dat");
                        } catch (IOException e) {
                            System.out.println("[!] Error de archivo: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("\n--> Has elegido Cargar Estado");
                        System.out.println();
                        try{
                            gf.cargarEstadoSistema("auditoriaTrenes.dat");
                        } catch (IOException | ClassNotFoundException e) {
                            System.out.println("[!] Error de archivo: " + e.getMessage());
                        }
                        break;
                    case 7:
                        System.out.println("\n--> Saliendo del programa... ¡Hasta pronto!");
                        salir = true;
                        break;
                    default:
                        System.out.println("\n[!] Error: Opción no válida. Introduce un número del 1 al 6.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[!] Error de entrada: Debes introducir un número entero.\n");
                scanner.next();
            }
        } while (!salir);

        scanner.close();
    }
}