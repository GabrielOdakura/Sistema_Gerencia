package model.identificadores;

import model.interfaces.IPedidos;

public class Pedido  implements IPedidos {
    private String nomePedido = "";
    private String idPedido = "";
    private String dataPedido = "";
    private String dataEntrega = "";
    private String descricao = "";


    @Override
    public void criarPedido(String nomePedido, String idPedido, String dataPedido, String dataEntrega, String produtos, String descricao) {
        this.nomePedido = nomePedido;
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
    }


    @Override
    public boolean verificarDisponibilidade() {
        return false;
    }

    @Override
    public void encerrarPedido() {

    }

    @Override
    public void alterarPedido() {

    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "[" +
                "nomePedido: " + nomePedido + " | " +
                ", idPedido: " + idPedido + " | " +
                ", dataPedido: " + dataPedido + " | " +
                ", dataEntrega: " + dataEntrega + " | " +
                ", descricao: " + descricao + " | " +
                ']';
    }
}
