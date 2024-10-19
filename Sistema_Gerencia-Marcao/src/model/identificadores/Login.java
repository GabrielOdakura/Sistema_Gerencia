package model.identificadores;

import model.interfaces.ILogin;

public class Login implements ILogin {
	private String usuario = "";
	private String senha = "";
	private String idUsuario = "";

	public Login(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public Login(String usuario, String senha, String idUsuario) {
		this.usuario = usuario;
		this.senha = senha;
		this.idUsuario = idUsuario;
	}

	@Override
	public boolean verificarLogin(String usuario, String senha) {
		return this.usuario.equals(usuario) && this.senha.equals(senha);
	}

	public String toString(){
		return "[Usuario: " + usuario + " | Senha: " + senha + " | ID Usuário: " + idUsuario + "]";
	}

	
	
}
