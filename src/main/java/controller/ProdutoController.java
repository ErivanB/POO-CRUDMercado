package controller;

import Persistencia.Persistencia;
import model.Produto;
import view.TelaProdutoDetailView;
import view.TelaProdutoView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private TelaProdutoView produtoView;
    private List<Produto> produtos;
    private Persistencia persistencia;


    public ProdutoController(){

    }

    public ProdutoController(TelaProdutoView produtoView) throws Exception {
        this.produtoView = produtoView;
        this.persistencia = new Persistencia();
        carregarProdutos();
        this.produtoView.setProdutos(produtos);
        this.produtoView.addDetalhesButtonListener(e -> showProdutoDetails());
        this.produtoView.addSearchButtonListener(e -> buscarProdutos());
        this.produtoView.addRefreshButtonListener(e -> atualizarProdutos());
    }

    private void carregarProdutos() {
        try {
            produtos = recuperarListaProdutos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            produtos = new ArrayList<>();
        }
    }



    private List<Produto> recuperarListaProdutos() throws Exception {
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Produto> listaProdutos = centralInformacoes.getProdutos();

        if (listaProdutos == null) {
            listaProdutos = new ArrayList<>();
        }
        return listaProdutos;
    }

    public Produto buscarPorNome(String nome)throws Exception{

        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Produto> listaProdutos = centralInformacoes.getProdutos();

        for (Produto produto : listaProdutos){
            if (produto.getNome().equals(nome)){
                return produto;
            }else{
                throw new ExcecoesController.CampoEmBrancoException("Nunhum produto foi encontradio ");
            }
        }

            return null;
    }

    private void salvarListaProdutos(List<Produto> listaProdutos) throws Exception {
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        centralInformacoes.setProdutos(listaProdutos);
        persistencia.salvarCentralInformacoes(centralInformacoes);
    }

    public boolean cadastrarProduto(Produto novoProduto) {
        try {
            if (isValidaProduto(novoProduto)) {
                List<Produto> listaProdutos = recuperarListaProdutos();
                listaProdutos.add(novoProduto);
                salvarListaProdutos(listaProdutos);

                produtos.add(novoProduto);
                produtoView.setProdutos(produtos);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                if (produtoView != null) {
                    produtoView.dispose();
                }
                return true;
            } else {
                throw new ExcecoesController.CadastroInvalidoException("Cadastro inválido!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean isValidaProduto(Produto produto) {
        String nome = produto.getNome();
        String unidade = produto.getUnidade();
        double valorCompra = produto.getValorCompra();
        double valorVenda = produto.getValorVenda();
        int quantidadeEstoque = produto.getQuantidadeEstoque();

        boolean nomeValido = isLimitarCaracteres(nome, 64);
        boolean unidadeValido = isLimitarCaracteres(unidade, 32);

        if (nome.isBlank() || unidade.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("Campo em branco em um ou mais campos obrigatórios.");
        }

        if (!nomeValido) {
            throw new ExcecoesController.LimiteCarcterException("Nome do produto excede o limite de 64 caracteres.");
        }

        if (!unidadeValido) {
            throw new ExcecoesController.LimiteCarcterException("Unidade do produto excede o limite de 32 caracteres.");
        }

        if (valorCompra <= 0) {
            throw new ExcecoesController.ValorInvalidoException("O valor de compra deve ser maior que zero.");
        }

        if (valorVenda < valorCompra) {
            throw new ExcecoesController.ValorInvalidoException("O valor de venda deve ser maior ou igual ao valor de compra.");
        }

        if (quantidadeEstoque < 0) {
            throw new ExcecoesController.QuantidadeInvalidaException("A quantidade em estoque não pode ser negativa.");
        }

        return true;
    }

    public void atualizarProduto(Produto produto) {
        try {
            List<Produto> listaProdutos = recuperarListaProdutos();
            int index = listaProdutos.indexOf(produto);
            if (index != -1) {
                listaProdutos.set(index, produto);
                salvarListaProdutos(listaProdutos);

                produtos = listaProdutos;
                produtoView.setProdutos(produtos);
            } else {
                throw new ExcecoesController.ProdutoNaoEncontradoException("Produto não encontrado para atualização.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirProduto(Produto produto) {
        try {
            List<Produto> listaProdutos = recuperarListaProdutos();
            if (listaProdutos.remove(produto)) {
                salvarListaProdutos(listaProdutos);

                produtos = listaProdutos;
                produtoView.setProdutos(produtos);
            } else {
                throw new ExcecoesController.ProdutoNaoEncontradoException("Produto não encontrado para remoção.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarProdutos() {
        try {
            String nome = produtoView.getSearchText();
            List<Produto> produtosEncontrados = buscaPorNomeGeraLista(nome);
            produtoView.setProdutos(produtosEncontrados);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Produto> buscaPorNomeGeraLista(String nome) throws Exception {
        List<Produto> listaProdutos = recuperarListaProdutos();
        List<Produto> produtosEncontrados = new ArrayList<>();

        for (Produto produto : listaProdutos) {
            if (produto.getNome().toLowerCase().contains(nome.toLowerCase())) {
                produtosEncontrados.add(produto);
            }
        }

        if (produtosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(produtoView, "Nenhum produto encontrado com o nome: " + nome);
        }

        return produtosEncontrados;
    }

    public void atualizarProdutos() {
        try {
            carregarProdutos();
            produtoView.setProdutos(produtos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a lista de produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showProdutoDetails() {
        int selectedRow = produtoView.getSelectedRow();
        if (selectedRow >= 0) {
            Produto selectedProduto = produtos.get(selectedRow);
            TelaProdutoDetailView detailView = new TelaProdutoDetailView(this);
            detailView.setProduto(selectedProduto);
            detailView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(produtoView, "Por favor, selecione um produto.");
        }
    }

    private boolean isLimitarCaracteres(String texto, int limiteMaximo) {
        return texto != null && !texto.isBlank() && texto.length() <= limiteMaximo;
    }
}
