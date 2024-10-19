package model.identificadores;

import model.interfaces.IFuncionarios;

public class Funcionario implements IFuncionarios {

    private String nomeFuncionario = "";
    private String cargo = "";
    private String ultimoAcesso = "";
    private String idFuncionario = "";




    public void cadastrarFuncionario(String nomeFuncionario, String cargo, String ultimoAcesso, String idFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
        this.ultimoAcesso = ultimoAcesso;
        this.idFuncionario = idFuncionario;

    }

    public void alterarFuncionario() {

    }


    public void desligarFuncionario() {

    }


    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    @Override
    public String toString() {
        return "[Nome Funcionario: " + nomeFuncionario + " | Cargo: " + cargo + " | Ultimo acesso: " + ultimoAcesso + " | ID Funcionario: " + idFuncionario + "]";
    }
}
