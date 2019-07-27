package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion c = new Conexion();

    public List listar() {
        List<Persona> lista = new ArrayList<>();
        String sql = "select * from alumnos";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setApe(rs.getString(3));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public Persona listarId(int id) {
        String sql = "select * from alumnos where Id=" + id;
        Persona pe = new Persona();
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pe.setId(rs.getInt(1));
                pe.setNom(rs.getString(2));
                pe.setApe(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return pe;
    }

    public void agregar(Persona p) {
        String sql = "insert into alumnos(legajo, nombre, apellido)values(NULL, ?, ?)";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getApe());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void update(Persona p) {
        String sql = "update alumnos set Nombres=?,Dni=? where Id=?";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getApe());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void delete(int id) {
        String sql = "delete from alumnos where legajo=" + id;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public Persona buscar(int id) {
        String sql = "select * from alumnos where Id=" + id;
        Persona pe = new Persona();
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pe.setId(rs.getInt(1));
                pe.setNom(rs.getString(2));
                pe.setApe(rs.getString(3));
            }
        } catch (Exception e) {
        }
        return pe;
    }

    public List<Persona> listarPorMateria(int idMate) {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT alumnos.legajo, alumnos.nombre, alumnos.apellido FROM alumnos INNER JOIN alumnos_materias ON alumnos.legajo = alumnos_materias.legajo WHERE alumnos_materias.id_materia = " + idMate;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setApe(rs.getString(3));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }
}
