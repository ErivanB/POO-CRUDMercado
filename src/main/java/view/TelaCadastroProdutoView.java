package view;

import controller.ExcecoesController;
import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroProdutoView extends JFrame {

    private JTextField nomeField;
    private JTextField unidadeField;
    private JTextField valorCompraField;
    private JTextField valorVendaField;
    private JTextField quantidadeEstoqueField;
    private JButton cadastrarButton;
    private JButton cancelarButton;

    private ProdutoController controller;

    public TelaCadastroProdutoView(ProdutoController controller) {
        this.controller = controller;
        setTitle("Cadastro de Produto");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initialize();
    }

    private void initialize() {
        nomeField = new JTextField(20);
        unidadeField = new JTextField(20);
        valorCompraField = new JTextField(20);
        valorVendaField = new JTextField(20);
        quantidadeEstoqueField = new JTextField(20);

        cadastrarButton = new JButton("Cadastrar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5)); // 6 linhas, 2 colunas

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Unidade:"));
        panel.add(unidadeField);
        panel.add(new JLabel("Valor Compra:"));
        panel.add(valorCompraField);
        panel.add(new JLabel("Valor Venda:"));
        panel.add(valorVendaField);
        panel.add(new JLabel("Quantidade Estoque:"));
        panel.add(quantidadeEstoqueField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(cancelarButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Coleta os dados e cria um produto
                    Produto novoProduto = new Produto(nomeField.getText(), unidadeField.getText(), Double.parseDouble(valorCompraField.getText()), Double.parseDouble(valorVendaField.getText()), Integer.parseInt(quantidadeEstoqueField.getText()));

                    // Verifica se o produto é válido
                    if (controller.isValidaProduto(novoProduto)) {
                        int response = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            controller.cadastrarProduto(novoProduto);
                            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Cadastro Confirmado", JOptionPane.INFORMATION_MESSAGE);
                            dispose(); // Fecha a janela
                        } else {
                            JOptionPane.showMessageDialog(null, "Cadastro cancelado.", "Cadastro Cancelado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (ExcecoesController.CampoEmBrancoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Campo em Branco", JOptionPane.ERROR_MESSAGE);
                } catch (ExcecoesController.ValorInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Valor Inválido", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor de compra, venda ou estoque inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar o cadastro?", "Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }
}
