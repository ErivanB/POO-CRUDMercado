package view;

import controller.*;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaFuncoesGerenteView extends JFrame {

    // Declaração dos botões
    private JButton btnCadastrarAlmoxarifado;
    private JButton btnCadastrarCaixaEletronico;
    private JButton btnCadastrarCliente;
    private JButton btnCadastrarProduto;
    private JButton btnEditarProduto;
    private JButton btnGerarCupomDesconto;
    private JButton btnGerarRelatorioMensal;
    private JButton btnListarProdutos;
    private JButton btnRegistrarValorUnitario;

    public TelaFuncoesGerenteView() {
        // Configurações da janela
        setTitle("Funções do Gerente");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Definir o layout da janela como BorderLayout
        setLayout(new BorderLayout());

        // Painel para os botões (no canto esquerdo)
        JPanel panelBotoes = new JPanel();
        // 9 linhas, 1 coluna, espaçamento de 10px
        panelBotoes.setLayout(new GridLayout(9, 1, 10, 10));

        // Inicializando os botões
        btnCadastrarAlmoxarifado = new JButton("Cadastrar Almoxarifado");
        btnCadastrarCaixaEletronico = new JButton("Cadastrar Caixa Eletrônico");
        btnCadastrarCliente = new JButton("Cadastrar Cliente");
        btnCadastrarProduto = new JButton("Cadastrar Produto");
        btnEditarProduto = new JButton("Editar Produto");
        btnGerarCupomDesconto = new JButton("Gerar Cupom de Desconto");
        btnGerarRelatorioMensal = new JButton("Gerar Relatório Mensal");
        btnListarProdutos = new JButton("Listar Produtos");
        btnRegistrarValorUnitario = new JButton("Registrar Valor Unitário");

        // Adicionando os botões ao painel
        panelBotoes.add(btnCadastrarAlmoxarifado);
        panelBotoes.add(btnCadastrarCaixaEletronico);
        panelBotoes.add(btnCadastrarCliente);
        panelBotoes.add(btnCadastrarProduto);
        panelBotoes.add(btnEditarProduto);
        panelBotoes.add(btnGerarCupomDesconto);
        panelBotoes.add(btnGerarRelatorioMensal);
        panelBotoes.add(btnListarProdutos);
        panelBotoes.add(btnRegistrarValorUnitario);

        // Adicionando o painel com os botões ao canto esquerdo
        add(panelBotoes, BorderLayout.WEST);

        // Adicionando a lógica dos botões
        adicionarListeners();

        // Exibe a janela
        setVisible(true);
    }

    // Método para adicionar listeners aos botões
    private void adicionarListeners() {
        btnCadastrarAlmoxarifado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAlmoxarifado();
            }
        });

        btnCadastrarCaixaEletronico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCaixaEletronico();
            }
        });

        btnCadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        btnCadastrarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    cadastrarProduto();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        btnEditarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });

        btnGerarCupomDesconto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarCupomDesconto();
            }
        });

        btnGerarRelatorioMensal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorioMensal();
            }
        });

        btnEditarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });

        btnRegistrarValorUnitario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarValorUnitario();
            }
        });
    }

    // Metodos para as funçoes dos botoes
    private void cadastrarAlmoxarifado() {
        JOptionPane.showMessageDialog(this, "Cadastrar Almoxarifado");
        // Lógica para cadastrar almoxarifado
        AlmoxarifeController almoxarifeController = new AlmoxarifeController();
        TelaCadastroAlmoxarifeView cadastroAlmoxarifeView = new TelaCadastroAlmoxarifeView(almoxarifeController);
        cadastroAlmoxarifeView.setVisible(true);

    }

    private void cadastrarCaixaEletronico() {
        JOptionPane.showMessageDialog(this, "Cadastrar Caixa Eletrônico");
        // Lógica para cadastrar caixa eletrônico
        CaixaEletronicoController caixaEletronicoController = new CaixaEletronicoController();
        TelaCadastroCaixaEletronicoView caixaEletronicoView = new TelaCadastroCaixaEletronicoView(caixaEletronicoController);
        caixaEletronicoView.setVisible(true);
    }

    private void cadastrarCliente() {
        JOptionPane.showMessageDialog(this, "Cadastrar Cliente");
        // Lógica para cadastrar cliente
        ClienteController clienteController = new ClienteController();
        TelaCadastroClienteView cadastroClienteView = new TelaCadastroClienteView(clienteController);

        cadastroClienteView.setVisible(true);
    }

    private void cadastrarProduto()throws Exception {
        JOptionPane.showMessageDialog(this, "Cadastrar Produto");
        // Lógica para cadastrar produto
        ProdutoController produtoController = new ProdutoController();
        TelaProdutoView produtoView = new TelaProdutoView(produtoController);
        TelaCadastroProdutoView cadastroProdutoView = new TelaCadastroProdutoView(produtoController);
        cadastroProdutoView.setVisible(true);
    }

    private void editarProduto() {
        JOptionPane.showMessageDialog(this, "Editar Produto");
        // Lógica para editar produto

        ProdutoController produtoController = new ProdutoController();
        Produto produto = new Produto();
    try{
        TelaProdutoView produtoView = new TelaProdutoView(produtoController);
        produtoController.atualizarProduto(produto);
        produtoView.setVisible(true);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    }

    private void gerarCupomDesconto() {
        JOptionPane.showMessageDialog(this, "Gerar Cupom de Desconto");
        // Lógica para gerar cupom de desconto

        TelaCadastroCupomView telaCadastroCupomView = new TelaCadastroCupomView();
        telaCadastroCupomView.setVisible(true);
        CupomController cupomController = new CupomController();
        GerenteController gerenteController = new GerenteController();



    }

    private void gerarRelatorioMensal() {
        JOptionPane.showMessageDialog(this, "Gerar Relatório Mensal");
        // Lógica para gerar relatório mensal
    }

    private void listarProdutos() {
        JOptionPane.showMessageDialog(this, "Listar Produtos");
        // Lógica para listar produtos
        ProdutoController produtoController = new ProdutoController();

        try{
            TelaProdutoView produtoView = new TelaProdutoView(produtoController);
            produtoView.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void registrarValorUnitario() {
        JOptionPane.showMessageDialog(this, "Registrar Valor Unitário");
        // Lógica para registrar valor unitário
        TelaRegistrarValorUnitario telaRegistrarValorUnitario = new TelaRegistrarValorUnitario();
        telaRegistrarValorUnitario.setVisible(true);
    }

}

