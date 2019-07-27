package Modelo;

//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnosMateriasDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion c = new Conexion();

    public void agregar(int idAlu, int idMat, int idPro) {
        String sql = "insert into alumnos_materias(legajo, id_materia, id_profesor)values(" + idAlu + ", " + idMat + ", " + idPro + ")";
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    //25/05/19  SEGUNDA CORRELATIVA EJ: Ingles I y Ingles III
    public void agregarConCorrela(int idAlum, int idMat, int idProf) {
        List<AlumnosMaterias> alMatIs = new ArrayList<>();
        alMatIs = null;
        alMatIs = this.listar(idAlum, idMat);
        if (alMatIs.isEmpty()) {
            List<Materia> listCorre = new ArrayList<>();
            List<Materia> listAdelanteCoree = new ArrayList<>();
            listCorre = this.listarCorrelativas(idMat);
            listAdelanteCoree = this.listarCorrelativasAdelante(idMat);
            if (listCorre.isEmpty()) {
                if (listAdelanteCoree.isEmpty()) {
                    this.agregar(idAlum, idMat, idProf);
                }
            } else {
                int flag = 0;
                int esta = 0;
                for (int i = 0; i < listCorre.size(); i++) {
                    int id_corre = listCorre.get(i).getId_materia();
                    esta = this.estaAnotadoEnMateria(idAlum, id_corre);
                    if (esta == 1) {
                        flag = 1;
                    }
                    //Segunda Coree 26/5/19  FUNCIONA
                    List<Materia> listCorre2 = new ArrayList<>();
                    listCorre2 = this.listarCorrelativas(id_corre);
                    if (!listCorre2.isEmpty()) {
                        for (i = 0; i < listCorre2.size(); i++) {
                            int id_corre2 = listCorre.get(i).getId_materia();
                            esta = this.estaAnotadoEnMateria(idAlum, id_corre2);
                            if (esta == 1) {
                                flag = 1;
                            }
                        }
                    }
                }

                if (!listAdelanteCoree.isEmpty()) {
                    for (int i = 0; i < listAdelanteCoree.size(); i++) {
                        int id_corre = listAdelanteCoree.get(i).getId_materia();
                        esta = this.estaAnotadoEnMateria(idAlum, id_corre);
                        if (esta == 1) {
                            flag = 1;
                        }
                        //Segunda CorreAdela 26/5/19  FUNCIONA
                        List<Materia> listAdelanteCoree2 = new ArrayList<>();
                        listAdelanteCoree2 = this.listarCorrelativasAdelante(id_corre);
                        if (!listAdelanteCoree2.isEmpty()) {
                            for (i = 0; i < listAdelanteCoree2.size(); i++) {
                                int id_corre2 = listAdelanteCoree2.get(i).getId_materia();
                                esta = this.estaAnotadoEnMateria(idAlum, id_corre2);
                                if (esta == 1) {
                                    flag = 1;
                                }
                            }
                        }
                    }
                }

                if (flag == 0) {
                    this.agregar(idAlum, idMat, idProf);
                } else {
                    //NO SE INSCRIBIO, NO PUEDE ESTAR INSCRIPTO EN LA CORRELATIVA 
                }
            }
        } else {
            //ya esta inscripto en esa materia
        }
    }

    public List listar(int idAlum, int idMat) {
        List<AlumnosMaterias> lista = new ArrayList<>();
        String sql = "select legajo, id_materia from alumnos_materias where legajo=" + idAlum + " and id_materia=" + idMat;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AlumnosMaterias p = new AlumnosMaterias();
                p.setLegajo(rs.getInt(1));
                p.setId_materia(rs.getInt(2));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    private List<Materia> listarCorrelativas(int idMat) {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT materias.id_materia, materias.nombre FROM materias INNER JOIN correlativas ON materias.id_materia = correlativas.id_matcorrelativa WHERE correlativas.id_materia = " + idMat;
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

    private List<Materia> listarCorrelativasAdelante(int idMat) {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT materias.id_materia, materias.nombre FROM materias INNER JOIN correlativas ON materias.id_materia = correlativas.id_materia WHERE correlativas.id_matcorrelativa = " + idMat;
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

    private int estaAnotadoEnMateria(int idAlum, int id_corre) {
        List<AlumnosMaterias> lista = new ArrayList<>();
        String sql = "select legajo, id_materia from alumnos_materias where legajo=" + idAlum + " and id_materia=" + id_corre;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AlumnosMaterias p = new AlumnosMaterias();
                p.setLegajo(rs.getInt(1));
                p.setId_materia(rs.getInt(2));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        if (lista.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    public void delete(int idMatDel, int idA) {
        String sql = "delete from alumnos_materias where legajo=" + idA + "and id_materia=" + idMatDel;
        try {
            con = c.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
