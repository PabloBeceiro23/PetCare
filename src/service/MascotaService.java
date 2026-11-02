package service;

import dao.MascotaDAO;
import entity.Mascota;

import java.util.ArrayList;

public class MascotaService {
    MascotaDAO mascotaDAO = new MascotaDAO();

    public boolean registrarMascota(Mascota mascota) {
//        mascotaDAO = new MascotaDAO();

        return mascotaDAO.registrarMascota(mascota);
    }

    public void consultarMascotaChip(Mascota mascota){
//        mascotaDAO = new MascotaDAO();

        Mascota consultaMascota = mascotaDAO.consultarMascotaChip(mascota);

        if(consultaMascota != null){
            System.out.println(consultaMascota);
        } else {
            System.out.println("No existe la mascota con el microchip " + mascota.getChip());
        }
    }

    // se recorre el arraylist devuelto por el dao y se printa cada mascota
    public void listarMascotas(Mascota mascota) {

        ArrayList<Mascota> consultaMascotas = mascotaDAO.listarMascotas();

        for(Mascota m: consultaMascotas) {
            System.out.println(m);
        }
    }

    // se llama al dao que devuelve un boolean si modifica con exito o no
    public void modificarMascota(Mascota mascota) {
        boolean estaModificado = mascotaDAO.modificarMascota(mascota);

        if(estaModificado){
            System.out.println("Mascota Modificada");
        } else {
            System.out.println("La mascota con el id "+ mascota.getId() + " no existe");
        }
    }

    public void eliminarMascota(Mascota mascota) {
        boolean estaEliminado = mascotaDAO.eliminarMascota(mascota);

        if(estaEliminado){
            System.out.println("Mascota Eliminada");
        } else {
            System.out.println("No existe una mascota con el id "+ mascota.getId());
        }
    }
}
