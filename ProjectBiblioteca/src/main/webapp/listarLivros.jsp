<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head><title>Lista de livros</title></head>
<body>
<h1>Lista de Alunos</h1>
<a href="livro?action=new">Novo livro</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>editora</th>
        <th>anodepubli</th>
        <th>numdepag</th>
        <th>edicao</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="w" items="${listLivros}">
        <tr>
            <td>${w.id}</td>
            <td>${w.nome}</td>
            <td>${w.editora}</td>
            <td>${w.anodepubli}</td>
            <td>${w.numdepag}</td>
            <td>${w.edicao}</td>
            
            <td>
                <a href="livro?action=edit&id=${w.id}">Editar</a>| 
                <a href="livro?action=delete&id=${w.id}">Deletar</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
