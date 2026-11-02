package dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entity.Mascota;
import entity.Veterinario;
import util.ConexionDb4o;

import java.util.ArrayList;

public class VeterinarioDAO {
    ConexionDb4o conexionBD;


    // se calcula el id del veterinario reutilizando el metodo creado en mascotadao. se comprueba que no existe el numero de colegiado
    // y si no existe se almacena en la base de datos
    public boolean registrarVeterinario(Veterinario veterinario) {

        conexionBD = new ConexionDb4o();

        ObjectContainer oc = conexionBD.abrirConexion();

        MascotaDAO mascotaDAO = new MascotaDAO();
        int idVeterinario = mascotaDAO.calcularID(oc);
        veterinario.setId(idVeterinario);

        // se crea un veterinario con el numero de colegiado introducido por el usuario para buscarlo en la base de datos a ver si existe
        // si existe no se guarda el veterinario
        Veterinario veterinarioPlantilla = new Veterinario();
        veterinarioPlantilla.setNumColegiado(veterinario.getNumColegiado());

        ObjectSet<Mascota> consultaVeterinario = oc.queryByExample(veterinarioPlantilla);

        if(consultaVeterinario.isEmpty()) {
            oc.store(veterinario);
            oc.commit();
            oc.close();
            return true;
        } else {
            return false;
        }
    }

    // se busca un veterinario por numero de colegiado. si existe se printa
    public void buscarVeterinarioNumColegiado(Veterinario veterinario) {
        conexionBD = new ConexionDb4o();

        ObjectContainer oc = conexionBD.abrirConexion();

        ObjectSet<Veterinario> consultaVeterinario = oc.queryByExample(veterinario);

        if(consultaVeterinario.hasNext()){
            Veterinario veterinarioPlantilla = new Veterinario();
            veterinarioPlantilla = consultaVeterinario.next();
            System.out.println(veterinarioPlantilla);
            oc.close();
        } else {
            System.out.println("Veterinario no encontrado");
            oc.close();
        }

    }

    public void listarVeterinarios() {
        Veterinario veterinario =  new Veterinario();

        conexionBD = new  ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();
        ObjectSet<Veterinario>  consultaVeterinario = oc.queryByExample(veterinario);
        ArrayList<Veterinario> listaVeterinarios = new ArrayList<>(consultaVeterinario);

        if(consultaVeterinario.isEmpty()) {
            System.out.println("No hay veterinarios registrados en la base de datos");
            oc.close();
        } else {
            for(Veterinario v: listaVeterinarios){
                System.out.println(v);
            }
            oc.close();
        }
    }

    // se busca un veterinario con el id que introdujo el usuario y se modifica la especialidad
    public boolean modificarEspecialidad(Veterinario veterinario) {
        conexionBD = new  ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();

        Veterinario veterinarioPlantilla = new Veterinario();

        veterinarioPlantilla.setId(veterinario.getId());

        ObjectSet<Veterinario> consultaVeterinario = oc.queryByExample(veterinarioPlantilla);

        if(consultaVeterinario.hasNext()) {
            veterinarioPlantilla = consultaVeterinario.next();
            veterinarioPlantilla.setEspecialidad(veterinario.getEspecialidad());
            oc.store(veterinarioPlantilla);
            oc.commit();
            oc.close();
            return true;
        } else {
            oc.close();
            return false;
        }

    }

    // se busca por id y si existe se elimina de la base de datos
    public boolean eliminarVeterinario(Veterinario veterinario) {
        conexionBD = new  ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();
        ObjectSet<Veterinario> consultaVeterinario = oc.queryByExample(veterinario);



        if(consultaVeterinario.hasNext()) {
            Veterinario veterinarioEliminar = consultaVeterinario.next();
            oc.delete(veterinarioEliminar);
            oc.commit();
            oc.close();
            return true;
        } else {
            oc.close();
            return false;
        }

    }
}
