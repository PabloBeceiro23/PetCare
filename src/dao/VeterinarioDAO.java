package dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entity.Mascota;
import entity.Veterinario;
import util.ConexionDb4o;

import java.util.ArrayList;

public class VeterinarioDAO {
    ConexionDb4o conexionBD;


    int calcularID(ObjectContainer bd) {

        ObjectSet<Veterinario> todosVeterinarios = bd.queryByExample(new Veterinario());

        int maxId = 0;

        for (Veterinario p : todosVeterinarios) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    // se busca si existe algun veterinario con el numero de colegiado introducido. si no existe se registra el veterinario
    public boolean registrarVeterinario(Veterinario veterinario) {
        conexionBD = new ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();

        Veterinario veterinarioPlantilla = new Veterinario();
        veterinarioPlantilla.setNumColegiado(veterinario.getNumColegiado());

        ObjectSet<Veterinario> consultaVeterinario = oc.queryByExample(veterinarioPlantilla);

        if (consultaVeterinario.isEmpty()) {
            veterinario.setId(calcularID(oc));
            oc.store(veterinario);
            oc.commit();
            oc.close();
            return true;
        } else {
            oc.close();
            return false;
        }
    }

    // se busca un veterinario por numero de colegiado. si existe se devuelve
    public Veterinario buscarVeterinarioNumColegiado(Veterinario veterinario) {
        conexionBD = new ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();

        ObjectSet<Veterinario> consultaVeterinario = oc.queryByExample(veterinario);

        Veterinario encontrado = null;
        if (consultaVeterinario.hasNext()) {
           encontrado = consultaVeterinario.next();
        }

        oc.close();
        return encontrado;
    }

    // devuelve la lista de veterinarios, vacio si no hay ninguno
    public ArrayList<Veterinario> listarVeterinarios() {
        conexionBD = new ConexionDb4o();
        ObjectContainer oc = conexionBD.abrirConexion();

        ObjectSet<Veterinario> consultaVeterinario = oc.queryByExample(new Veterinario());
        ArrayList<Veterinario> listaVeterinarios = new ArrayList<>(consultaVeterinario);

        oc.close();
        return listaVeterinarios;
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
