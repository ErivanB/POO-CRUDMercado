package view;

import controller.EntradaEstoqueController;
import model.Almoxarife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEntradaEstoqueView extends JFrame {
    private JTextField produtoField;
    private JTextField quantidadeField;
    private JTextField valorUnitarioField;
    private JButton registrarButton;
    private JButton cancelarButton;

    private EntradaEstoqueController controller;

    public TelaEntradaEstoqueView(Almoxarife almoxarife) {
        controller = new EntradaEstoqueController(almoxarife, this);

        setTitle("Registro de Entrada no Estoque");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Configurando o layout principal como GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicializando os campos de texto
        produtoField = new JTextField(20);
        quantidadeField = new JTextField(20);
        valorUnitarioField = new JTextField(20);

        // Botões
        registrarButton = new JButton("Registrar Entrada");
        cancelarButton = new JButton("Cancelar");

        // Configurando a grade do GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Produto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(produtoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Quantidade:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(quantidadeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Valor Unitário de Compra:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(valorUnitarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registrarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(cancelarButton, gbc);

        // Adicionando o painel ao frame
        add(panel);

        // Configurando ações dos botões
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.registrarEntrada();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Fecha a janela
                dispose();
            }
        });
    }

    public String getProduto() {

        return produtoField.getText();
    }

    public int getQuantidade() {

        return Integer.parseInt(quantidadeField.getText());
    }

    public double getValorUnitarioCompra() {

        return Double.parseDouble(valorUnitarioField.getText());
    }
}
