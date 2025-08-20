package view;

import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaProdutoDetailView extends JFrame {
    private JLabel codigoLabel;
    private JTextField nomeField;
    private JTextField unidadeField;
    private JTextField valorCompraField;
    private JTextField valorVendaField;
    private JTextField quantidadeEstoqueField;

    private JButton editarButton;
    private JButton excluirButton;

    private Produto produto;
    private ProdutoController produtoController;

    public TelaProdutoDetailView(ProdutoController produtoController) {
        this.produtoController = produtoController;

        setTitle("Detalhes do Produto");
        setSize(500, 300); // Tamanho aumentado para acomodar melhor os elementos
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configura o layout com GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Inicializa os campos e botões
        codigoLabel = new JLabel();
        nomeField = new JTextField(20);
        unidadeField = new JTextField(10);
        valorCompraField = new JTextField(10);
        valorVendaField = new JTextField(10);
        quantidadeEstoqueField = new JTextField(10);

        editarButton = new JButton("Editar");
        excluirButton = new JButton("Excluir");

        // Adiciona os componentes ao layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Código:"), gbc);

        gbc.gridx = 1;
        add(codigoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Unidade:"), gbc);

        gbc.gridx = 1;
        add(unidadeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Valor de Compra:"), gbc);

        gbc.gridx = 1;
        add(valorCompraField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Valor de Venda:"), gbc);

        gbc.gridx = 1;
        add(valorVendaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Quantidade em Estoque:"), gbc);

        gbc.gridx = 1;
        add(quantidadeEstoqueField, gbc);

        // Configura os botões
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editarButton);
        buttonPanel.add(excluirButton);
        add(buttonPanel, gbc);

        // Adiciona os listeners para os botões
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                editarProduto();
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                excluirProduto();
            }
        });
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        codigoLabel.setText(produto.getCodigo());
        nomeField.setText(produto.getNome());
        unidadeField.setText(produto.getUnidade());
        valorCompraField.setText(String.valueOf(produto.getValorCompra()));
        valorVendaField.setText(String.valueOf(produto.getValorVenda()));
        quantidadeEstoqueField.setText(String.valueOf(produto.getQuantidadeEstoque()));
        atualizarBotoes();
    }

    private void editarProduto() {
        if (produto != null) {
            try {
                produto.setNome(nomeField.getText());
                produto.setUnidade(unidadeField.getText());
                produto.setValorCompra(Double.parseDouble(valorCompraField.getText()));
                produto.setValorVenda(Double.parseDouble(valorVendaField.getText()));
                produto.setQuantidadeEstoque(Integer.parseInt(quantidadeEstoqueField.getText()));

                // Atualiza as informações no repositório
                produtoController.atualizarProduto(produto);
                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Dados inválidos. Verifique os valores inseridos.");
            }
        }
    }

    private void excluirProduto() {
        if (produto != null) {
            if (produto.getQuantidadeEstoque() > 0) {
                JOptionPane.showMessageDialog(this, "Não é possível excluir um produto com estoque.");
                return;
            }

            // Remove o produto do repositório
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                produtoController.excluirProduto(produto);
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso.");
                dispose();
            }
        }
    }

    private void atualizarBotoes() {
        // Desabilita o botão de excluir se o produto tem estoque
        excluirButton.setEnabled(produto.getQuantidadeEstoque() == 0);
    }

    public Produto getProduto() {
        return produto;
    }
}
