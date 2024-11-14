package model;
public class Livro {
	private int id;
    private String nome;
    private String autor;
    private String editora;
    private String anodepubli;
    private String numdepag;
    private String edicao;
    
	public Livro(String nome, String autor, String editora, String anodepubli, String numdepag, String edicao) {
		super();
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
		this.anodepubli = anodepubli;
		this.numdepag = numdepag;
		this.edicao = edicao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getAnodepubli() {
		return anodepubli;
	}
	public void setAnodepubli(String anodepubli) {
		this.anodepubli = anodepubli;
	}
	public String getNumdepag() {
		return numdepag;
	}
	public void setNumdepag(String numdepag) {
		this.numdepag = numdepag;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
    
}
    

   