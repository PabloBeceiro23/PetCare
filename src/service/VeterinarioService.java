package service;

import dao.VeterinarioDAO;
import entity.Veterinario;

import java.util.ArrayList;

public class VeterinarioService {
    VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

    public  void modificarEspecialidad(Veterinario veterinario) {
        boolean estaModificado = veterinarioDAO.modificarEspecialidad(veterinario);

        if (estaModificado) {
            System.out.println("Especialidad modificada");
        } else {
            System.out.println("No existe un veterinario con id " + veterinario.getId());
        }
    }

    public void registrarVeterinario(Veterinario veterinario) {
        boolean estaRegistrado = veterinarioDAO.registrarVeterinario(veterinario);

        if(estaRegistrado){
            System.out.println("Veterinario registrado");
        }else {
            System.out.println("Veterinario no registrado");
        }
    }


    public void buscarVeterinarioNumColegiado(Veterinario veterinario) {
        Veterinario encontrado = veterinarioDAO.buscarVeterinarioNumColegiado(veterinario);

        if (encontrado != null) {
            System.out.println(encontrado);
        } else {
            System.out.println("Veterinario no encontrado");
        }
    }

    public void listarVeterinarios() {
        ArrayList<Veterinario> lista = veterinarioDAO.listarVeterinarios();

        if (lista.isEmpty()) {
            System.out.println("No hay veterinarios registrados en la base de datos");
        } else {
            for (Veterinario v : lista) {
                System.out.println(v);
            }
        }
    }

    public void eliminarVeterinario(Veterinario veterinario) {
        boolean estaEliminado = veterinarioDAO.eliminarVeterinario(veterinario);

        if(estaEliminado){
            System.out.println("Veterinario eliminado");
        } else  {
            System.out.println("No existe un veterinario con id " + veterinario.getId());
        }
    }
}
