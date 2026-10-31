package entity;

public class Veterinario {
    private String numColegiado;
    private String nombre;
    private int id;
    private String especialidad;

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "numColegiado='" + numColegiado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id=" + id +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
