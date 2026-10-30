package dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entity.Mascota;
import util.ConexionDb4o;
import java.util.Random;

public class MascotaDAO {
    ConexionDb4o conexionBD;

    // se le asigna un id y un numero de chip valido
    public boolean registrarMascota(Mascota mascota) {
        conexionBD = new ConexionDb4o();

        ObjectContainer oc = conexionBD.abrirConexion();

        int idMascota = calcularID(oc);
        mascota.setId(idMascota);

        int chipMascota = calcularChip(oc);

        oc.store(mascota);
        oc.commit();
        oc.close();

        return true;
    }

    private int calcularID( ObjectContainer bd) {

        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());

        int maxId = 0;

        for (Mascota p : todasMascotas) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    // se calcula un numero aleatorio entre 1 y 10000 y se comprueba que no lo tenga ninguna mascota de la bd
    private int calcularChip(ObjectContainer bd) {
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());

        Random random = new Random();
        int numeroChipAleatorio = 0;
        boolean chipCorrecto = true;



        do {

            numeroChipAleatorio = random.nextInt(10000) + 1;

            for (Mascota p : todasMascotas) {
                if(p.getId() == numeroChipAleatorio) {
                    chipCorrecto = false;
                }
            }
        } while (!chipCorrecto);

        return numeroChipAleatorio;
    }
}
