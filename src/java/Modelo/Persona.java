
package Modelo;

public class Persona {
    int id;
    String nom;
    String ape;

    public Persona() {
    }

    public Persona(int id, String nom, String dni) {
        this.id = id;
        this.nom = nom;
        this.ape = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String dni) {
        this.ape = dni;
    }
    
     
}
