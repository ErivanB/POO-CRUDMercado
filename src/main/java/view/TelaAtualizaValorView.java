package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaAtualizaValorView extends JFrame {
    private JTextField campoNomeProduto;
    private JTextField campoValorVenda;
    private JButton botaoAtualizar;

    public TelaAtualizaValorView() {
        setTitle("Atualizar Valor de Venda");
        setSize(400, 150); // Ajustado o tamanho para melhor se adequar ao layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento ao redor dos componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Componentes se expandem horizontalmente
        gbc.anchor = GridBagConstraints.WEST;

        // Adiciona o rótulo e campo de nome do produto
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome do Produto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Ocupa a última coluna
        campoNomeProduto = new JTextField(20);
        add(campoNomeProduto, gbc);

        // Adiciona o rótulo e campo de valor de venda
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reseta o gridwidth
        add(new JLabel("Novo Valor de Venda:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        campoValorVenda = new JTextField(20);
        add(campoValorVenda, gbc);

        // Adiciona o botão Atualizar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // O botão ocupa as duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza o botão
        botaoAtualizar = new JButton("Atualizar Valor");
        add(botaoAtualizar, gbc);

        setLocationRelativeTo(null); // Centraliza a tela na tela principal
    }

    public String getNomeProduto() {
        return campoNomeProduto.getText();
    }

    public double getValorVenda() {
        try {
            return Double.parseDouble(campoValorVenda.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor válido para venda.", "Valor Inválido", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void adicionarListener(ActionListener listener) {
        botaoAtualizar.addActionListener(listener);
    }
}
