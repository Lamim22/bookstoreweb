<%-- 
    Document   : cabecalho
    Created on : 25 de abr de 2022, 13:50:47
    Author     : KGe
--%>

<!-- Inicio cabecalho-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="jumbotron"><h1>BookStoreWeb</h1></div>

<h2>Bem vindo! <c:out value="${user.fullname}"/></h2>

<p>
    <a href="<%=request.getContextPath()%>/bstore/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>Adicionar novo Livro</a>

        <a href="<%=request.getContextPath()%>/bstore/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Lista todos Livros</a>
</p>

<!-- Fim cabecalho-->