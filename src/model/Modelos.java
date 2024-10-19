package model;
import model.identificadores.Funcionario;
import model.identificadores.Login;
import model.identificadores.Pedido;
import model.identificadores.Produto;
import model.ConectorDB;
import java.util.ArrayList;

public class Modelos {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Login> usuarios = new ArrayList<>();

    ConectorDB conector = new ConectorDB();

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

    private void atualizarUsandoDB(){
        conector.getConnection();
        funcionarios = conector.SelectFuncionarios();
        pedidos = conector.SelectPedidos();
        produtos = conector.SelectProdutos();
        usuarios = conector.SelectLogin();
    }

}
