package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion c = new Conexion();

    public List listar() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "select * from profesores";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setId_profesor(rs.getInt(1));
                p.setNombre_apellido(rs.getString(2));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List<Profesor> listarDeMateria(int idM) {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT profesores.id_profesor, profesores.nombre_apellido FROM profesores INNER JOIN profesores_materias ON profesores.id_profesor = profesores_materias.id_profesor WHERE profesores_materias.id_materia = " + idM;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setId_profesor(rs.getInt(1));
                p.setNombre_apellido(rs.getString(2));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void agregar(String nomApePro) {
        String sql = "insert into profesores(id_profesor, nombre_apellido)values(NULL, ?)";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nomApePro);
            if (!exist(nomApePro)) {
                ps.executeUpdate();
            }
        } catch (Exception e) {
        }
    }

    public void delete(int idProfe) {
        String sql = "delete from profesores where id_profesor=" + idProfe;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    private boolean exist(String nomApePro) {
        int aux = 0;
        String sql = "SELECT profesores.id_profesor FROM `profesores` WHERE profesores.nombre_apellido = ? ";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                aux=rs.getInt(1);
            }
        } catch (Exception e) {
        }
        if( aux != 0 ) {
            return true;
        } else {
            return false;
        }
    }

}
