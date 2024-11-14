package database;

import model.Livro; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/biblioteca";
    private String jdbcUsername = "root";  // Ajuste o usuário
    private String jdbcPassword = "12345";  // Ajuste a senha

    private static final String INSERT_LIVROS_SQL = "INSERT INTO livros (nome, autor, editora, anodepubli, numdepag, edicao) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LIVROS_BY_ID = "SELECT id, nome, autor, editora, anodepubli, numdepag, edicao FROM livros WHERE id = ?";
    private static final String SELECT_ALL_LIVROS = "SELECT * FROM livros";
    private static final String DELETE_LIVROS_SQL = "DELETE FROM livros WHERE id = ?";
    private static final String UPDATE_LIVROS_SQL = "UPDATE livros SET nome=?, autor=?, editora=?, anodepubli=?, numdepag=?, edicao=? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Métodos CRUD

    public void insertLivro(Livro livro) throws SQLException {
        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIVROS_SQL)) {
            preparedStatement.setString(1, livro.getNome());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getEditora());
            preparedStatement.setString(4, livro.getAnodepubli());
            preparedStatement.setString(5, livro.getNumdepag());
            preparedStatement.setString(6, livro.getEdicao());

            preparedStatement.executeUpdate();
        }
    }

    public Livro selectLivro(int id) {
        Livro livro = null;
        try (Connection connection = getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIVROS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String autor = rs.getString("autor");
                String editora = rs.getString("editora");
                String numdepubli = rs.getString("numdepubli");
                String numdepag = rs.getString("numdepag");
                String edicao = rs.getString("edicao");


                livro = new Livro(nome, autor, editora, numdepubli, numdepag, edicao);
                livro.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    public List<Livro> selectAllLivros() {
        List<Livro> livros = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIVROS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String autor = rs.getString("autor");
                String editora = rs.getString("editora");
                String numdepubli = rs.getString("numdepubli");
                String numdepag = rs.getString("numdepag");
                String edicao = rs.getString("edicao");

                Livro livro = new Livro(nome, autor, editora, numdepubli, numdepag, edicao);
                livro.setId(id);
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public boolean updateLivros(Livro livro) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LIVROS_SQL)) {
            statement.setString(1, livro.getNome());
            statement.setString(2, livro.getAutor());
            statement.setString(4, livro.getEditora());
            statement.setString(6, livro.getAnodepubli());
            statement.setString(5, livro.getNumdepag());
            statement.setString(3, livro.getEdicao());
            statement.setInt(7, livro.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated; 
    }

    public boolean deleteLivro(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LIVROS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}