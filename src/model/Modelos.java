package model;
import model.identificadores.*;
import model.ConectorDB;
import java.util.ArrayList;

public class Modelos {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Login> usuarios = new ArrayList<>();
    private ConectorDB conector = new ConectorDB();

    public Modelos(){
        conector.getConnection();
        atualizarUsandoDB();
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Login> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Login> usuarios) {
        this.usuarios = usuarios;
    }

    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public void addUsuario(Login usuario) {
        usuarios.add(usuario);
    }

    public DadosTabela pegarTabelaFuncionarios(){
        return conector.TabelaFuncionarios();
    }
    public DadosTabela pegarTabelaEmpresas(){
        return conector.TabelaEmpresa();
    }
    public DadosTabela pegarTabelaEstoque(){
        return conector.TabelaEstoque();
    }
    public DadosTabela pegarTabelaLogins(){
        return conector.TabelaLogin();
    }
    public DadosTabela pegarTabelaPedidos(){
        return conector.TabelaPedido();
    }
    public DadosTabela pegarTabelaProdutos(){
        return conector.TabelaProduto();
    }

    private void atualizarUsandoDB(){
        funcionarios = conector.SelectFuncionarios();
        pedidos = conector.SelectPedidos();
        produtos = conector.SelectProdutos();
        usuarios = conector.SelectLogin();
    }

    public void inserirEmpresa(String empresaNome){
//        conector.inserirEmpresa();
    }

    public void inserirFuncionarios(String funcionarioNome, String funcionarioCargo){
        conector.inserirFuncionarios(funcionarios.size() + 1, funcionarioNome, funcionarioCargo);
    }

    public void inserirLogin(String senha, String usuario){
        conector.inserirLogin(senha,usuario, funcionarios.size() + 1);
    }

    public void inserirProdutos(String nomeProduto, Double precoProduto, String categoriaProduto, String descricaoProduto, int desconto){
        conector.inserirProdutos(nomeProduto,precoProduto,categoriaProduto,descricaoProduto,desconto);
    }

    public void inserirPedidos(String descricaoPedido, int empresaId, int produtoId, int funcionarioId){
        conector.inserirPedidos(descricaoPedido, empresaId, produtoId, funcionarioId);
    }

    public void inserirEstoque(int estoque, int produtoId){
        conector.inserirEstoque(estoque, produtoId);
    }

}
