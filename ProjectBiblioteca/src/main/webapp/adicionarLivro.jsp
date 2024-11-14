<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adicionar Livro</title>
</head>
<body>
    <h2>Adicionar Novo Livro</h2>
    <form action="livro?action=${livro != null ? 'update' : 'insert'}" method="post">
        <input type="hidden" name="acao" value="adicionar">
        
        <label for="titulo">Nome:</label>
        <input type="text" name="nome" required><br><br>

        <label for="autor">Autor:</label>
        <input type="text" name="autor" required><br><br>

        <label for="genero">editora:</label>
        <input type="text" name="editora" required><br><br>

        <label for="ano">Ano de Publicação:</label>
        <input type="number" name="anodepubli" required><br><br>
        
        <label for="ano">Numero de Paginas:</label>
        <input type="number" name="numdepag" required><br><br>
        
        <label for="ano">edicao:</label>
        <input type="text" name="edicao" required><br><br>

        <button type="submit">Adicionar Livro</button>
    </form>

    <br>
    <a href="index.jsp">Voltar para a página inicial</a>
</body>
</html>
