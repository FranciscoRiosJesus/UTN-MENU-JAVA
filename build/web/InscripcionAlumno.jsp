<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TP menu - Rios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
    </head>
    <body>
    <center>
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light p-0">
                <ul class="nav nav-tabs border-0">
                    <div><img src="img/utn.png" height="42" width="42"/></div>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" aria-haspopup="true" aria-expanded="false">Alumno</a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="AltaAlumno">
                                </form>
                            </li>
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="BajaAlumno">
                                </form>
                            </li>
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="InscripcionAlumno">
                                </form>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" aria-haspopup="true" aria-expanded="false">Materia</a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="AltaMateria">
                                </form>
                            </li>
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="BajaMateria">
                                </form>
                            </li>
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="listarAlumnosPorMateria">
                                </form>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" aria-haspopup="true" aria-expanded="false">Profesor</a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="AltaProfesores">
                                </form>
                            </li>
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto"type="submit" name="accion" value="BajaProfesores">
                                </form>
                            </li>
                            <li class="dropdown-item">
                                <form action="Controlador" method="POST">
                                    <input class="dropdown-item-input mx-auto" type="submit" name="accion" value="AsignarMateriaAProfesor">
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </header>

        <section class="wrapper">
            <section class="main">
                <div>
                    <center>
                        <h3>Inscripcion Alumno a Materia</h3>
                        <form action="Controlador" method="POST">
                            <div class="form-group">
                                <label>Alumno:</label>
                                <select class="form-control" name="alumno">
                                    <option selected>Alumno</option>
                                    <c:forEach var="dato" items="${listaAlumnos}">
                                        <option>${dato.getId()}. ${dato.getNom()} ${dato.getApe()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br>

                            <div class="form-group">
                                <label>Materia:</label><br>
                                <select class="form-control" name="materia">
                                    <option selected>Materia</option>
                                    <c:forEach var="dato" items="${listaMaterias}">
                                        <option>${dato.getId_materia()}. ${dato.getNombre()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br>

                            <input class="dropdown-item-input" style="width: 80%;" type="submit" name="accion" value="Seguir">
                        </form>
                    </center>
                </div>
            </section>
            <aside>

            </aside>
        </section>
        <footer>
            <p> Francisco Rios - TP menu de INSPT-UTN - (C)</p>
        </footer>
    </center>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
