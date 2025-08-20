package model;

// Classe que representa um produto.

import Persistencia.Persistencia;

public class Produto {

    private String codigo; // Codigo unico do produto
    private String nome; // Nome do produto
    private String unidade; // Unidade de medida do produto (ex.: unidade, litro)
    private double valorCompra; // Valor de compra do produto
    private double valorVenda; // Valor de venda do produto
    private int quantidadeEstoque; // Quantidade em estoque do produto
    private double valorUnitario; // Valor unitário de venda, se necessário
    private static int sequencial = 1; // Contador para gerar códigos sequenciais

     // Construtor para inicializar o produto.
    public Produto( String nome, String unidade, double valorCompra, double valorVenda, int quantidadeEstoque) {

        this.codigo = gerarCodigoProduto();
        this.nome = nome;
        this.unidade = unidade;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorUnitario = 0;

    }
    //construtor para venda unitaria.
    public Produto(String nome, double valorUnitario) {
        this.nome = nome;
        this.valorUnitario = getValorUnitario();
    }
    public Produto(){

    }

    // Getters e Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }


    // Gera um código único para um novo produto.
    public String gerarCodigoProduto() {
        // Gera um código sequencial formatado
        String codigo = String.format("%05d", sequencial++);
        return codigo;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", unidade='" + unidade + '\'' +
                ", valorCompra=" + valorCompra +
                ", valorVenda=" + valorVenda +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}