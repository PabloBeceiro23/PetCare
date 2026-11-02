package main;

import entity.Mascota;
import entity.Veterinario;
import service.MascotaService;
import service.VeterinarioService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static MascotaService mascotaService = new MascotaService();
    static VeterinarioService veterinarioService = new VeterinarioService();

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
                    } else if (opcionMenuMascotas == 2){
                        buscarMascotaMicroChip();
                    } else if(opcionMenuMascotas == 3){
                        listarMascotas();
                    } else if(opcionMenuMascotas == 4){
                        modificarMascota();
                    } else if(opcionMenuMascotas == 5){
                        eliminarMascota();
                    }

                } else if (opcionMenu == 2) {
                    System.out.println("1. Registrar veterinario\n" +
                            "2. Buscar por numero de colegiado\n" +
                            "3. Listar veterinarios\n" +
                            "4. Modificar especialidad\n" +
                            "5. Eliminar veterinario:");

                    int opcionMenuVeterinario = 0;

                    do{
                        try {opcionMenuVeterinario = teclado.nextInt();
                            teclado.nextLine();
                        }
                        catch(InputMismatchException e){
                            System.out.println("Introduce un valor valido");
                            teclado.nextLine();
                        }
                    } while(opcionMenuVeterinario==0);

                    if(opcionMenuVeterinario==1){
                        registrarVeterinario();
                    } else if (opcionMenuVeterinario == 2) {
                        buscarVeterinarioNumColegiado();
                    } else if (opcionMenuVeterinario == 3) {
                        listarVeterinarios();
                    } else if (opcionMenuVeterinario == 4) {
                        modificarEspecialidad();
                    } else if (opcionMenuVeterinario == 5) {
                        eliminarVeterinario();
                    }
                }

            }while (opcionMenu < 1 || opcionMenu > 3);
        }while (opcionMenu !=3);


    }

    private static void eliminarVeterinario() {
        int id = 0;

        do {
            try {
                System.out.println("Introduce el id del veterinario que quieres eliminar ");
                id = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor valido");
                teclado.nextLine();
            }
        } while (id == 0);

        Veterinario veterinario = new Veterinario();
        veterinario.setId(id);

        veterinarioService.eliminarVeterinario(veterinario);
    }

    private static void modificarEspecialidad() {
        int id = 0;

        do {
            try {
                System.out.println("Introduce el ID del veterinario al que quieres modificar la especialidad");
                id = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor valido");
                teclado.nextLine();
            }
        } while (id == 0);

        System.out.println("Introduce la especialidad");
        String especialidad = teclado.nextLine();

        Veterinario veterinario = new Veterinario();
        veterinario.setId(id);
        veterinario.setEspecialidad(especialidad);

        veterinarioService.modificarEspecialidad(veterinario);

    }

    private static void listarVeterinarios() {
        veterinarioService.listarVeterinarios();
    }

    private static void buscarVeterinarioNumColegiado() {
        Veterinario veterinario = new Veterinario();

        System.out.println("Introduce numero de colegiado");
        veterinario.setNumColegiado(teclado.nextLine());

        veterinarioService.buscarVeterinarioNumColegiado(veterinario);
    }

    private static void registrarVeterinario() {
        Veterinario veterinario = new Veterinario();

        System.out.println("Introduce nombre del veterinario: ");
        veterinario.setNombre(teclado.nextLine());

        System.out.println("Introduce el numero de colegiado");
        veterinario.setNumColegiado(teclado.nextLine());

        System.out.println("Introduce especialidad");
        veterinario.setEspecialidad(teclado.nextLine());

        veterinarioService.registrarVeterinario(veterinario);
    }

    private static void eliminarMascota() {
        Mascota mascota = new Mascota();
        int id = 0;

        do {
            try {
                System.out.println("Introduce el id de la mascota");
                id = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor valido");
                teclado.nextLine();
            }
        } while (id == 0);

        mascota.setId(id);

        mascotaService.eliminarMascota(mascota);
    }

    private static void modificarMascota() {
        int id = 0;
        int edad = 0;

        try {
            System.out.println("Introduce el id de la mascota que quieres modificar: ");
            id = teclado.nextInt();
            teclado.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Introduce un valor valido");
            teclado.nextLine();
        }

        System.out.println("Introduce el nombre de la mascota");
        String nombre = teclado.nextLine();

        do {
            try {
                System.out.println("Introduce la edad de la mascota");
                edad = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor valido");
                teclado.nextLine();
            }
        } while (edad == 0);

        Mascota mascota = new Mascota();
        mascota.setId(id);
        mascota.setNombre(nombre);
        mascota.setEdad(edad);

        mascotaService.modificarMascota(mascota);

    }

    private static void listarMascotas() {
        Mascota mascota = new Mascota();

        mascotaService.listarMascotas(mascota);
    }

    // se pide el chip a buscar y se pasa al service para que llame al dao
    private static void buscarMascotaMicroChip() {
        int nChip = 0;
        Mascota mascota = new Mascota();


       do {
           try {
               System.out.println("Introduce el numero del chip de la mascota");
               nChip = teclado.nextInt();
               teclado.nextLine();
           } catch (InputMismatchException e) {
               System.out.println("Introduce un valor valido");
               teclado.nextLine();
           }
       }while (nChip == 0);

       mascota.setChip(nChip);

        mascotaService.consultarMascotaChip(mascota);
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
                teclado.nextLine();
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
