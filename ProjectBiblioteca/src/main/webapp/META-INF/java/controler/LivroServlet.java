package controler;

import database.DAO; 
import model.Livro;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/livro")
public class LivroServlet extends HttpServlet {
    private DAO dao;

    public void init() {
        dao = new DAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertLivro(request, response);
                    break;
                case "delete":
                    deleteAluno(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateLivro(request, response);
                    break;
                default:
                    listLivros(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listLivros(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Livro> listLivros = dao.selectAllLivros();
        request.setAttribute("listLivros", listLivros);
        request.getRequestDispatcher("listarLivros.html").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("adicionarLivros.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Livro existingLivro = dao.selectLivro(id);
        request.setAttribute("livro", existingLivro);
        request.getRequestDispatcher("adicionarLivros.jsp").forward(request, response);
    }

    private void insertLivro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String nome = request.getParameter("nome");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        String anodepubli = request.getParameter("anodepubli");
        String numdepag = request.getParameter("numdepag");
        String edicao = request.getParameter("edicao");
        Livro novolivro = new Livro(nome, autor, editora, anodepubli, numdepag, edicao);
     
        dao.insertLivro(novolivro);
        response.sendRedirect("aluno?action=list");
    }

    private void updateLivro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        String anodepubli = request.getParameter("anodepubli");
        String numdepag = request.getParameter("numdepag");
        String edicao = request.getParameter("edicao");
        Livro livro = new Livro(nome, autor, editora, anodepubli, numdepag, edicao);
        livro.setId(id);
        dao.updateLivros(livro);
        response.sendRedirect("aluno?action=list");
    }

    private void deleteAluno(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteLivro(id);
        response.sendRedirect("aluno?action=list");
    }
}