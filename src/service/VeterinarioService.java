package service;

import dao.VeterinarioDAO;
import entity.Veterinario;

public class VeterinarioService {
    VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

    public  void modificarEspecialidad(Veterinario veterinario) {
        boolean estaModificado = veterinarioDAO.modificarEspecialidad(veterinario);
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
        veterinarioDAO.buscarVeterinarioNumColegiado(veterinario);
    }

    public void listarVeterinarios() {
        veterinarioDAO.listarVeterinarios();
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
