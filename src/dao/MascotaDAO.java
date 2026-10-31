package dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entity.Mascota;
import util.ConexionDb4o;

import java.util.ArrayList;
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
        mascota.setChip(chipMascota);

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
        ObjectSet<Mascota> consulta = bd.queryByExample(new Mascota());
        ArrayList<Mascota> todasMascotas = new ArrayList<>(consulta);


        Random random = new Random();
        int numeroChipAleatorio = 0;
        boolean chipCorrecto = true;



        do {
            chipCorrecto = true;
            numeroChipAleatorio = random.nextInt(10000) + 1;

            for (Mascota p : todasMascotas) {
                if(p.getChip() == numeroChipAleatorio) {
                    chipCorrecto = false;
                    break;
                }
            }
        } while (!chipCorrecto);

        return numeroChipAleatorio;
    }

    // busca mascotas en la base de datos que tengan los mismos campos que la mascota que se pasa como parametro.
    // La mascota que se pasa como parametro solo tiene rellenado el chip por lo que va a buscar por chip

    public Mascota consultarMascotaChip(Mascota mascota) {
        conexionBD = new ConexionDb4o();

        ObjectContainer bd= conexionBD.abrirConexion();
        ObjectSet<Mascota> consultaMascotasChip = bd.queryByExample(mascota);



        Mascota mascotaADevolver = null;

        if(consultaMascotasChip.hasNext()){
            mascotaADevolver = consultaMascotasChip.next();
        }

        bd.close();

        return mascotaADevolver;
    }

    // se hace una consulta a la base de datos con una mascota vacia para que liste todas las mascotas. el resultado de esa consulta
    // la pasamos a un arraylist que luego se devuelve por la funcion
    public ArrayList<Mascota> listarMascotas() {
        Mascota mascotaPlantilla = new Mascota();

        conexionBD = new ConexionDb4o();

        ObjectContainer bd= conexionBD.abrirConexion();
        ObjectSet<Mascota> consultaMascotasChip = bd.queryByExample(new Mascota());

        ArrayList<Mascota>listaMascotas = new ArrayList<>();

        for(Mascota m: consultaMascotasChip){
            listaMascotas.add(m);
        }

        bd.close();

        return listaMascotas;

    }
}
