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
}
