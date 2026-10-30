package service;

import dao.MascotaDAO;
import entity.Mascota;

public class MascotaService {
    MascotaDAO mascotaDAO;

    public boolean registrarMascota(Mascota mascota) {
        mascotaDAO = new MascotaDAO();

        return mascotaDAO.registrarMascota(mascota);
    }
}
