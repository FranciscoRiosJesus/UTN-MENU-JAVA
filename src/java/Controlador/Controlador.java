package Controlador;

import Modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    Persona p = new Persona();
    Materia m = new Materia();
    Profesor pr = new Profesor();

    MateriaDAO daoMat = new MateriaDAO();
    PersonaDAO daoPer = new PersonaDAO();
    ProfesorDAO daoPro = new ProfesorDAO();
    AlumnosMateriasDAO daoAluMat = new AlumnosMateriasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String accion = request.getParameter("accion");
        switch (accion) {
            //INDEX
            case "index":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            //student 
            case "Listar":
                //List<Persona> lista = daoPer.listar();
                //request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            //student saved
            case "SaveStudent":
                String nom = request.getParameter("txtNom");
                String ape = request.getParameter("txtApe");

                if (!isBlank(nom)) {
                    p.setNom(nom);
                    if (!isBlank(ape)) {
                        p.setApe(ape);
                        daoPer.agregar(p);
                    } else {
                        //alert! plis set a lastname
                    }
                } else {
                    //aler! plis set a name
                }

                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;

            /*case "Editar":
                int ide = Integer.parseInt(request.getParameter("id"));
                Persona res = daoPer.listarId(ide);
                request.setAttribute("dato", res);
                request.getRequestDispatcher("edit.jsp").forward(request, response);
                break;*/
 /*case "Actualizar":
                int id = Integer.parseInt(request.getParameter("id"));
                String nom1 = request.getParameter("txtNom");
                String dni1 = request.getParameter("txtDni");
                p.setId(id);
                p.setNom(nom1);
                p.setApe(dni1);
                daoPer.update(p);
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;*/
            // delete student    
            case "Delete":
                int idd = Integer.parseInt(request.getParameter("id"));
                daoPer.delete(idd);
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;

            case "AltaAlumno":
                request.getRequestDispatcher("AltaAlumno.jsp").forward(request, response);
                break;
            case "BajaAlumno":
                List<Persona> listaBaja = daoPer.listar();
                request.setAttribute("lista", listaBaja);
                request.getRequestDispatcher("BajaAlumno.jsp").forward(request, response);
                break;

            case "Buscar":
                int idBuscar = Integer.parseInt(request.getParameter("id"));
                Persona persona = daoPer.buscar(idBuscar);
                request.setAttribute("persona", persona);
                //request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;

            case "InscripcionAlumno":
                List<Persona> listaAlumnos = daoPer.listar();
                List<Materia> listaMaterias = daoMat.listar();
                request.setAttribute("listaAlumnos", listaAlumnos);
                request.setAttribute("listaMaterias", listaMaterias);
                request.getRequestDispatcher("InscripcionAlumno.jsp").forward(request, response);
                break;
            case "Seguir":
                String alu = request.getParameter("alumno");
                String mat = request.getParameter("materia");

                StringBuilder sb = new StringBuilder(mat);
                StringBuilder st = new StringBuilder(sb.substring(0, (sb.indexOf("."))));
                int idM = Integer.parseInt(st.toString());
                List<Profesor> listaProfesoresDeMateria = daoPro.listarDeMateria(idM);

                request.setAttribute("mat", mat);
                request.setAttribute("alu", alu);
                request.setAttribute("listaProfesores", listaProfesoresDeMateria);
                request.getRequestDispatcher("InscripcionAlumno2.jsp").forward(request, response);
                break;
            case "RegisterStudent":
                String alumn = request.getParameter("alu");
                StringBuilder sbAl = new StringBuilder(alumn);
                StringBuilder stAlu = new StringBuilder(sbAl.substring(0, (sbAl.indexOf("."))));
                int idAlum = Integer.parseInt(stAlu.toString());

                String materi = request.getParameter("mat");
                StringBuilder sbMa = new StringBuilder(materi);
                StringBuilder stMat = new StringBuilder(sbMa.substring(0, (sbMa.indexOf("."))));
                int idMat = Integer.parseInt(stMat.toString());

                String profeso = request.getParameter("profesor");
                StringBuilder sbPr = new StringBuilder(profeso);
                StringBuilder stPro = new StringBuilder(sbPr.substring(0, (sbPr.indexOf("."))));
                int idProf = Integer.parseInt(stPro.toString());

                //see if the student are registered in Subjects(materia)
                daoAluMat.agregarConCorrela(idAlum, idMat, idProf);//review, check out
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "AltaMateria":
                List<Materia> matLisA = daoMat.listar();
                request.setAttribute("listaMaterias", matLisA);
                request.getRequestDispatcher("AltaMateria.jsp").forward(request, response);
                break;
            case "SaveSubjects":
                String nomMat = request.getParameter("nomMat");

                if (!isBlank(nomMat)) {
                    daoMat.agregar(nomMat);
                    /*response.setContentType("text/html;charset=UTF-8");
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet NewServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1> " + nomMat + "</h1>");
                    out.println("</body>");
                    out.println("</html>");*/
                    //correlativa
                    String[] check = request.getParameterValues("checkbox");
                    if (check != null && check.equals("Correlativa")) {
                        String correla = request.getParameter("correlative");
                        if (!isBlank(correla)) {
                            StringBuilder sbMatG = new StringBuilder(correla);
                            StringBuilder stMaG = new StringBuilder(sbMatG.substring(0, (sbMatG.indexOf("."))));
                            daoMat.agregarCorre(Integer.parseInt(stMaG.toString()), nomMat);
                        }
                    }

                } else {
                    //Send a menssage in console, mat empy, plis 

                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "BajaMateria":
                List<Materia> matLis = daoMat.listar();
                request.setAttribute("lista", matLis);
                request.getRequestDispatcher("BajaMateria.jsp").forward(request, response);
                break;
            case "DeleteMateria":
                int idMatE = Integer.parseInt(request.getParameter("id"));
                daoMat.delete(idMatE);
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;

            case "listarAlumnosPorMateria":
                //List<Persona>listaAlumnosL = daoPer.listar();
                List<Materia> listaMateriasL = daoMat.listar();
                //request.setAttribute("listaAlumnos", listaAlumnosL);
                String strM = request.getParameter("materia");
                if (strM != null) {
                    StringBuilder sbMat = new StringBuilder(strM);
                    StringBuilder stMa = new StringBuilder(sbMat.substring(0, (sbMat.indexOf("."))));
                    int idMate = Integer.parseInt(stMa.toString());
                    List<Persona> listaAlumnosPorMateria = daoPer.listarPorMateria(idMate);
                    request.setAttribute("listaAlumnos", listaAlumnosPorMateria);
                }
                request.setAttribute("listaMaterias", listaMateriasL);
                request.getRequestDispatcher("ListarAlumnosPorMateria.jsp").forward(request, response);
                break;

            case "AltaProfesores":
                request.getRequestDispatcher("AltaProfesores.jsp").forward(request, response);
                break;
            case "SaveProfesor":
                String nomApePro = request.getParameter("nomApePro");
                if (!isBlank(nomApePro)) {
                    daoPro.agregar(nomApePro);
                }
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;

            case "BajaProfesores":
                List<Profesor> proLis = daoPro.listar();
                request.setAttribute("lista", proLis);
                request.getRequestDispatcher("BajaProfesores.jsp").forward(request, response);
                break;
            case "DeleteProfesor":
                int idProfe = Integer.parseInt(request.getParameter("id"));
                daoPro.delete(idProfe);
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;
                
            case "AsignarMateriaAProfesor":
                List<Persona> listaProfesorA = daoPro.listar();
                List<Materia> listaMateriasA = daoMat.listar();
                request.setAttribute("listaProfesor", listaProfesorA);
                request.setAttribute("listaMaterias", listaMateriasA);
                request.getRequestDispatcher("AsignarMateriaAProfesor.jsp").forward(request, response);
                break;
            case "Asignar":
                String materiA = request.getParameter("materia");
                String profesoA = request.getParameter("profesor");

                StringBuilder sbMaA = new StringBuilder(materiA);
                StringBuilder stMatA = new StringBuilder(sbMaA.substring(0, (sbMaA.indexOf("."))));
                int idMatA = Integer.parseInt(stMatA.toString());

                StringBuilder sbPrA = new StringBuilder(profesoA);
                StringBuilder stProA = new StringBuilder(sbPrA.substring(0, (sbPrA.indexOf("."))));
                int idProfA = Integer.parseInt(stProA.toString());

                daoMat.agregarProfesorAMat(idMatA, idProfA);
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;
                
            case "DeleteDeMateria":
                String matDelete = request.getParameter("materia");
                StringBuilder sbMaD = new StringBuilder(matDelete);
                StringBuilder stMatD = new StringBuilder(sbMaD.substring(0, (sbMaD.indexOf("."))));
                int idMatD = Integer.parseInt(stMatD.toString());
                int idA = Integer.parseInt(request.getParameter("idA"));
                daoAluMat.delete(idMatD, idA);
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

}
