package model.interfaces;

public interface IFuncionarios {
    public void cadastrarFuncionario(String nomeFuncionario, String cargo, String ultimoAcesso, String idFuncionario);
    public void alterarFuncionario();
    public void desligarFuncionario();
}
