package model.interfaces;

public interface IPedidos {
    public void criarPedido(String nomePedido, String idPedido, String dataPedido, String dataEntrega, String produtos, String Empresa, String Funcionarios, String descricao);
    public boolean verificarDisponibilidade();


    public void encerrarPedido();
    public void alterarPedido();
}
