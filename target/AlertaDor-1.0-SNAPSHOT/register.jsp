<%-- 
    Document   : register
    Created on : 26/07/2016, 00:42:14
    Author     : Wensttay de Sousa Alencar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <meta name="Author" content="Wensttay de Sousa Alencar">
    </head>
    <body>
        <div class="container">
            <div style="position: absolute; float: left; top: 10px; left: 30px;">
                <a href="index" style="text-decoration: none;"><h1>< Voltar</h1></a>
            </div>
            <form class="form-horizontal" action="LoginControll" method="POST"
                  style="width: 300px; margin: 100px auto;">
                <h1 style="text-align: center; margin-bottom: 50px;">Cadastro</h1>

                <div class="form-group">
                    <label for="inputName">Name: </label><input  class="form-control" type="text" id="inputName" name="name">
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email: </label><input class="form-control" type="email" id="inputEmail" name="email">
                </div>
                <div class="form-group">
                    <label for="inputPassword">Password: </label> <input class="form-control" type="password" id="inputPassword" name="password">
                </div>

                <div class="form-group">
                    <input type="submit" class="form-control" value="Register">
                </div>
            </form>
        </div>
    </body>
</html>
