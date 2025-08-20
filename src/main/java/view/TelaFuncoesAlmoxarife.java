package view;

import controller.ClienteController;
import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import java.awt.*;

public class TelaFuncoesAlmoxarife extends JFrame {

    private JButton botaoCadastrarCliente;
    private JButton botaoCadastrarProduto;
    private JButton botaoEditarProduto;
    private JButton botaoEntradaEstoque;
    private JButton botaoListarProdutos;

    public TelaFuncoesAlmoxarife() {
        setTitle("Funções do Almoxarife");
        setSize(500, 300);  // Aumentando o tamanho da tela para melhor visualização
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel para os botões à esquerda com GridBagLayout
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;  // Faz o botão expandir no eixo X
        gbc.gridx = 0;

        // Criação dos botões
        botaoCadastrarCliente = criarBotao("Cadastrar Cliente");
        botaoCadastrarProduto = criarBotao("Cadastrar Produto");
        botaoEditarProduto = criarBotao("Editar Produto");
        botaoEntradaEstoque = criarBotao("Entrada no Estoque");
        botaoListarProdutos = criarBotao("Listar Produtos");

        // Adiciona os botões ao painel com GridBagConstraints
        gbc.gridy = 0;
        painelBotoes.add(botaoCadastrarCliente, gbc);

        gbc.gridy = 1;
        painelBotoes.add(botaoCadastrarProduto, gbc);

        gbc.gridy = 2;
        painelBotoes.add(botaoEditarProduto, gbc);

        gbc.gridy = 3;
        painelBotoes.add(botaoEntradaEstoque, gbc);

        gbc.gridy = 4;
        painelBotoes.add(botaoListarProdutos, gbc);

        // Adiciona painel de botões à esquerda
        painelPrincipal.add(painelBotoes, BorderLayout.WEST);

        // Adiciona o painel principal ao frame
        add(painelPrincipal);
        setVisible(true);

        // Configurações dos ActionListeners para cada botão
        botaoCadastrarCliente.addActionListener(e -> cadastrarCliente());
        botaoCadastrarProduto.addActionListener(e -> {
            try {
                cadastrarProduto();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        botaoEditarProduto.addActionListener(e -> editarProduto());
        botaoEntradaEstoque.addActionListener(e -> entradaEstoque());
        botaoListarProdutos.addActionListener(e -> {
            try {
                listarProdutos();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(180, 40));  // Tamanho uniforme para todos os botões
        return botao;
    }

    private void cadastrarProduto() throws Exception {
        JOptionPane.showMessageDialog(this, "Cadastrar Produto");
        // Lógica para cadastrar produto
        ProdutoController produtoController = new ProdutoController();
        TelaCadastroProdutoView cadastroProdutoView = new TelaCadastroProdutoView(produtoController);
        cadastroProdutoView.setVisible(true);
    }

    private void editarProduto() {
        JOptionPane.showMessageDialog(this, "Editar Produto");
        // Lógica para editar produto
        ProdutoController produtoController = new ProdutoController();
        Produto produto = new Produto();
        try {
            TelaProdutoView produtoView = new TelaProdutoView(produtoController);
            produtoController.atualizarProduto(produto);
            produtoView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao editar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastrarCliente() {
        JOptionPane.showMessageDialog(this, "Cadastrar Cliente");
        // Lógica para cadastrar cliente
        ClienteController clienteController = new ClienteController();
        TelaCadastroClienteView cadastroClienteView = new TelaCadastroClienteView(clienteController);
        cadastroClienteView.setVisible(true);
    }

    private void entradaEstoque() {
        JOptionPane.showMessageDialog(this, "Entrada no Estoque");
        // Lógica para entrada no estoque
        ClienteController clienteController = new ClienteController();
        TelaCadastroClienteView cadastroClienteView = new TelaCadastroClienteView(clienteController);
        cadastroClienteView.setVisible(true);
    }

    private void listarProdutos()throws Exception {
        JOptionPane.showMessageDialog(this, "Listar Produtos");
        // Lógica para listar produtos
        ProdutoController produtoController = new ProdutoController();
        Produto produto = new Produto();
        try {
            TelaProdutoView produtoView = new TelaProdutoView(produtoController);
            produtoController.atualizarProduto(produto);
            produtoView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao editar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}
