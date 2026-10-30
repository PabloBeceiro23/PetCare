package main;

import entity.Mascota;
import service.MascotaService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static MascotaService mascotaService;
static  Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        int opcionMenu = 0;
        int opcionMenuMascotas = 0;


        do {
            do {
                try {
                    System.out.println("========================================\n" +
                            "SISTEMA VETERINARIO PETCARE (BDOO)\n" +
                            "========================================\n" +
                            "1. Módulo de Mascotas\n" +
                            "2. Módulo de Veterinarios\n" +
                            "3. Salir\n" +
                            "========================================");
                    opcionMenu = teclado.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Introduce un valor valido");
                    teclado.nextLine();
                }

                if (opcionMenu == 1) {
                    System.out.println("1. Registrar mascota\n" +
                            "2. Buscar por microchip\n" +
                            "3. Listar mascotas\n" +
                            "4. Modificar datos\n" +
                            "5. Eliminar mascota");

                    do{
                        try {
                            opcionMenuMascotas = teclado.nextInt();
                            teclado.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Introduce un valor valido");
                            teclado.nextLine();
                        }
                    }while(opcionMenuMascotas==0);

                    if(opcionMenuMascotas==1){

                        altaMascota();
                    }

                } else if (opcionMenu == 2) {
                    System.out.println("1. Registrar veterinario\n" +
                            "2. Buscar por colegiado\n" +
                            "3. Listar veterinarios\n" +
                            "4. Modificar especialidad\n" +
                            "5. Eliminar veterinario:");
                }
            }while (opcionMenu < 1 || opcionMenu > 3);
        }while (opcionMenu !=3);


    }

    // pedimos los datos de la mascota y se lo pasamos al service para que haga las comprobaciones
    private static void altaMascota(){
        String nombreMascota = "";
        String especieMascota = "";
        int edadMascota = 0;

        System.out.println("Introduce nombre de la mascota");
        nombreMascota = teclado.nextLine();

        System.out.println("Introduce la especie");
        especieMascota = teclado.nextLine();

        do {
            try {
                System.out.println("Introduce edad de la mascota en meses");
                edadMascota = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Introduce un valor valido");
                teclado.nextLine();
            }
        } while (edadMascota == 0);

        Mascota mascota = new Mascota();
        mascota.setNombre(nombreMascota);
        mascota.setEspecie(especieMascota);
        mascota.setEdad(edadMascota);

        if(mascotaService.registrarMascota(mascota)){
            System.out.println("Mascota registrada correctamente");
        } else {
            System.out.println("Error al registrar la mascota");
        }
    }
}
