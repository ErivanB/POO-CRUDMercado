package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class CentralInformacoesController {

    // Inicializa a lista de produtos,gerente,cliente,almoxarife, venda && caixa eletronico.
    private List<Gerente> gerentes = new ArrayList<Gerente>();
    private List<Produto> produtos = new ArrayList<Produto>();
    private List<Almoxarife> almoxarifes = new ArrayList<Almoxarife>();
    private  List<CaixaEletronico> caixaEletronicos = new ArrayList<CaixaEletronico>();
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private List<Venda> vendas = new ArrayList<Venda>();

    public CentralInformacoesController() {

    }
    //Get e & Set das litas.
    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Almoxarife> getAlmoxarifes() {
        return almoxarifes;
    }

    public void setAlmoxarifes(List<Almoxarife> almoxarifes) {
        this.almoxarifes = almoxarifes;
    }

    public List<CaixaEletronico> getCaixaEletronicos() {
        return caixaEletronicos;
    }

    public void setCaixaEletronicos(List<CaixaEletronico> caixaEletronicos) {
        this.caixaEletronicos = caixaEletronicos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Venda> getVendas() {
        return vendas;
    }


}
