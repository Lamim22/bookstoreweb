<%-- 
    Document   : UserForm
    Created on : 9 de mai de 2022, 09:31:32
    Author     : devsys-a
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro!</h1>
        <form action="<%=request.getContextPath()%>/bsuser/register" method="post">
            <table>
                <tr>   
                    <td>
                        <label for="userName"><strong>Nome:</strong></label>
                        <input type="text" name="userName" value="<c:out value='${PojoUser.fullname}'/>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="userEmail"><strong>Email:</strong></label>
                        <input type="text" name="userEmail" value="<c:out value='${PojoUser.email}'/>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="userPassword"><strong>Password:</strong></label>
                        <input type="password" name="userPassword" value="<c:out value='${PojoUser.password}'/>">
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
