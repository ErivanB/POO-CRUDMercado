package model;

import java.time.LocalDateTime;

public class EntradaEstoque {
    private String produto;
    private int quantidade;
    private double valorUnitarioCompra;
    private LocalDateTime dataHoraEntrada;
    private Almoxarife almoxarife;

    public Almoxarife getAlmoxarife() {
        return almoxarife;
    }

    public void setAlmoxarife(Almoxarife almoxarife) {
        this.almoxarife = almoxarife;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public double getValorUnitarioCompra() {
        return valorUnitarioCompra;
    }

    public void setValorUnitarioCompra(double valorUnitarioCompra) {
        this.valorUnitarioCompra = valorUnitarioCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }



    public EntradaEstoque(String produto, int quantidade, double valorUnitarioCompra, Almoxarife almoxarife) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitarioCompra = valorUnitarioCompra;
        this.dataHoraEntrada = LocalDateTime.now();
        this.almoxarife = almoxarife;
    }

    public EntradaEstoque(){

    }

}

