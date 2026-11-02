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

    int calcularID(ObjectContainer bd) {

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
    public int calcularChip(ObjectContainer bd) {

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

    // se crea una mascota vacia y se le asigna el id de la mascota que se quiere modificar. se busca esa mascota, si existe se apunta a la mascota
    // de la base de datos, se modifican los datos y se hace store y commit
    public boolean modificarMascota(Mascota mascota) {

        conexionBD = new ConexionDb4o();
        ObjectContainer bd= conexionBD.abrirConexion();

        Mascota  mascotaPlantilla = new Mascota();
        mascotaPlantilla.setId(mascota.getId());

        ObjectSet<Mascota> consultaMascotaPorId = bd.queryByExample(mascotaPlantilla);

        if(consultaMascotaPorId.hasNext()){
            mascotaPlantilla = consultaMascotaPorId.next();

            mascotaPlantilla.setNombre(mascota.getNombre());
            mascotaPlantilla.setEdad(mascota.getEdad());

            bd.store(mascotaPlantilla);
            bd.commit();
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }
    }

// se busca en la base de datos una mascota que tenga el mismo id que la que se pide al usuario. si existe se elimina
    public boolean eliminarMascota(Mascota mascota) {
        conexionBD = new ConexionDb4o();
        ObjectContainer bd= conexionBD.abrirConexion();

        ObjectSet<Mascota> consultaMascotaPorId = bd.queryByExample(mascota);

        if(consultaMascotaPorId.hasNext()){
            mascota = consultaMascotaPorId.next();

            bd.delete(mascota);
            bd.commit();
            bd.close();
            return true;
        } else {
            bd.close();
            return false;
        }


    }
}
