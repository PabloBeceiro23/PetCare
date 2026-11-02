package dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entity.Mascota;
import util.ConexionDb4o;
import java.util.ArrayList;


public class MascotaDAO {
    ConexionDb4o conexionBD;

    // se le asigna un id y se comprueba que el chip no exista
    public boolean registrarMascota(Mascota mascota) {
        conexionBD = new ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();


            Mascota plantilla = new Mascota();
            plantilla.setChip(mascota.getChip());

            ObjectSet<Mascota> existente = oc.queryByExample(plantilla);

            if (existente.hasNext()) {
                oc.close();
                return false;   // chip repetido
            }

            mascota.setId(calcularID(oc));
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
