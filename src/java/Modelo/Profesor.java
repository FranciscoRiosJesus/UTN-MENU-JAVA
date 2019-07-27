package Modelo;

public class Profesor {
    int id_profesor;
    String nombre_apellido;

    public Profesor() {
    }

    public Profesor(int id_profesor, String nombre_apellido) {
        this.id_profesor = id_profesor;
        this.nombre_apellido = nombre_apellido;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre_apellido() {
        return nombre_apellido;
    }

    public void setNombre_apellido(String nombre_apellido) {
        this.nombre_apellido = nombre_apellido;
    }
    
}
