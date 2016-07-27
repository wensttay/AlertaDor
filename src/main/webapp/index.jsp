<%-- 
    Document   : index
    Created on : 26/07/2016, 02:37:25
    Author     : wensttay
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.edu.ifpb.bdnc.alertador.enums.DenunciationType"%>
<%@page import="br.edu.ifpb.bdnc.alertador.enums.SquealerType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Showing pixel and tile coordinates</title>
        <meta name="viewport" content="initial-scale=1.0">
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <style>
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }

            #map {
                height: 400px;
                width: 500px;
                border-radius: 10px;
                margin: 0 auto 50px;
            }

        </style>
    </head>
    <body>
        <div class="conatiner">
            <h1 style="text-align: center; margin-top: 50px; margin-bottom: 50px;">AlertaDor</h1>


             
            <div id="map">

            </div>
            
            <div class="form-group" style="width: 400px; margin: 10px auto; z-index: 2;" >
                <input id="pac-input" class="form-control" 
                       style="width: 200px;" type="text" placeholder="Pesquise um Lugar">
            </div> 

            <c:if test="${user == null}">
                <div style="width: 400px; margin: 0 auto 50px; ">
                    <a href="register"><div class="btn btn-default col-lg-6">Se Cadastrar</div></a>
                    <a href="login"><div class="btn btn-default col-lg-6">Efetuar Login</div></a>
                </div>
            </c:if>
            <c:if test="${user != null}">
                <form  class="form-horizontal" action="DenunciationControll" 
                       method="POST" style="width: 400px; margin: 0 auto ;">
                    <div class="form-group">
                        <label for="description">Descrição:</label>
                        <input class="form-control" type="text" id="description" name="description">
                    </div>

                    <div class="form-group">
                        <label for="denunciationType">Relatar um:</label>
                        <select class="form-control" id="denunciationType" name="denunciationType">
                            <c:forEach var="dt" items="${DenunciationType.values()}" >
                                <option value="${dt.getId()}">${dt.getTittle()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="denunciationType">Me identifico como:</label>
                        <select class="form-control" id="squealerType" name="squealerType">
                            <c:forEach var="st" items="${SquealerType.values()}" >
                                <option value="${st.getId()}">${st.getTittle()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="text" id="lat" name="lat" hidden>
                    <input type="text" id="lng" name="lng" hidden>
                    <div class="form-group">
                        <label>Modo Anonimo</label><input  class="form-control" type="checkbox" id="anonymous" name="anonymous" value="anonymous" >
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="submit" onclick="setCoordinateOnInputs();" id="posAtt" value="Registrar Ocorrencia">
                    </div>
                </form>
                <form class="form-horizontal" action="LogoutControll" method="POST" 
                      style="width: 400px; margin: 50px auto;">
                    <div class="form-group">
                        <input type="submit" class="form-control btn-danger" value="Deslogar">
                    </div>
                </form>
            </c:if>
            <script type="text/javascript" src="js/jquery-1.12.3.js"></script>
            <script type="text/javascript" src="js/maps.js"></script>
        </div>
    </body>
</html>
