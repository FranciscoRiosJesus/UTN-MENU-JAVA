
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion c = new Conexion();

    public List listar() {
        List<Materia> lista = new ArrayList<>();
        String sql = "select * from materias";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Materia p = new Materia();
                p.setId_materia(rs.getInt(1));
                p.setNombre(rs.getString(2));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void agregar(String nomMat) {
        String sql = "insert into materias(id_materia, nombre)values(NULL, ?)";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nomMat);
            if( buscarMat(nomMat) == 0 ) {
                ps.executeUpdate();
            }
        } catch (Exception e) {
        }
    }

    public void delete(int idMatE) {
        String sql = "delete from materias where id_materia=" + idMatE;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void agregarProfesorAMat(int idMatA, int idProfA) {
        String sql = "insert into profesores_materias(profesores_materias.id_profesor, profesores_materias.id_materia, profesores_materias.llave)values(" + idProfA +", "+ idMatA +", NULL)";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void agregarCorre(int correla, String nomMat) {
        int idMat=this.buscarMat(nomMat);
        String sql = "insert into correlativas(llave, id_materia, id_matcorrelativa)values(NULL, "+idMat+ ", "+correla+")";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    private int buscarMat(String nomMat) {
        int idMat;
        idMat = 0;
        String sql = "select materias.id_materia from materias where materias.nombre='"+nomMat+"'";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idMat=rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idMat;
    }
}
