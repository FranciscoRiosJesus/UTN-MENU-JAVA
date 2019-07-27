package Modelo;


public class AlumnosMaterias {
    int legajo;
    int id_materia;
    int id_profesor;

    public AlumnosMaterias() {
    }

    public AlumnosMaterias(int legajo, int id_materia, int id_profesor) {
        this.legajo = legajo;
        this.id_materia = id_materia;
        this.id_profesor = id_profesor;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }
    
    
}
