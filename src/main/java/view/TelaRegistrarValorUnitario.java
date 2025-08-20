package view;

import javax.swing.*;
import java.awt.*;

public class TelaRegistrarValorUnitario extends JFrame {

    private JTextField campoNomeProduto;
    private JTextField campoValorUnitario;
    private JButton botaoConfirmar;

    public TelaRegistrarValorUnitario() {
        setTitle("Registrar Valor Unitário");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com GridBagLayout
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Campo Nome do Produto
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel labelNomeProduto = new JLabel("Nome do Produto:");
        painelPrincipal.add(labelNomeProduto, gbc);

        gbc.gridx = 1;
        campoNomeProduto = new JTextField();
        campoNomeProduto.setPreferredSize(new Dimension(200, 30));
        painelPrincipal.add(campoNomeProduto, gbc);

        // Campo Valor Unitário
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelValorUnitario = new JLabel("Valor Unitário:");
        painelPrincipal.add(labelValorUnitario, gbc);

        gbc.gridx = 1;
        campoValorUnitario = new JTextField();
        campoValorUnitario.setPreferredSize(new Dimension(200, 30));
        painelPrincipal.add(campoValorUnitario, gbc);

        // Botão Confirmar
        gbc.gridx = 1;
        gbc.gridy = 2;
        botaoConfirmar = new JButton("Confirmar");
        painelPrincipal.add(botaoConfirmar, gbc);

        // Adiciona o painel principal ao frame
        add(painelPrincipal);
        setVisible(true);
    }

    // Métodos para obter os valores dos campos
    public String getNomeProduto() {
        return campoNomeProduto.getText();
    }

    public double getNovoValor() throws NumberFormatException {
        return Double.parseDouble(campoValorUnitario.getText());
    }

}



