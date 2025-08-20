package view;

import controller.ClienteController;
import controller.VendaController;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class TelaVendaView extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField cupomField;
    private JTextField enderecoField;
    private JTextArea produtosArea;
    private JLabel totalLabel;
    private ClienteController clienteController;
    private VendaController vendaController;

    public TelaVendaView(ClienteController clienteController, VendaController vendaController) {
        this.clienteController = clienteController;
        this.vendaController = vendaController;
        configurarTela();
    }

    private void configurarTela() {
        setTitle("Tela de Venda");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout(10, 10)); // Espaçamento entre os componentes

        // Painel para os dados do cliente
        JPanel painelCliente = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCliente.add(new JLabel("CPF:"), gbc);

        gbc.gridx = 1;
        cpfField = new JTextField(15);
        painelCliente.add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCliente.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        nomeField = new JTextField(15);
        painelCliente.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCliente.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        painelCliente.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCliente.add(new JLabel("Cupom:"), gbc);

        gbc.gridx = 1;
        cupomField = new JTextField(15);
        painelCliente.add(cupomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        painelCliente.add(new JLabel("Endereço:"), gbc);

        gbc.gridx = 1;
        enderecoField = new JTextField(15);
        painelCliente.add(enderecoField, gbc);

        add(painelCliente, BorderLayout.NORTH);

        // Área para exibição dos produtos
        produtosArea = new JTextArea(10, 30);
        produtosArea.setBorder(BorderFactory.createTitledBorder("Produtos Selecionados"));
        produtosArea.setLineWrap(true);
        produtosArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(produtosArea);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior para o total e botão de finalizar
        JPanel painelInferior = new JPanel(new BorderLayout(10, 10));

        totalLabel = new JLabel("Total: R$ 0,00", JLabel.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        painelInferior.add(totalLabel, BorderLayout.NORTH);

        JButton finalizarButton = new JButton("Finalizar Venda");
        finalizarButton.setPreferredSize(new Dimension(200, 40));
        finalizarButton.addActionListener(this::realizarVenda);
        painelInferior.add(finalizarButton, BorderLayout.SOUTH);

        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void realizarVenda(ActionEvent e) {
        String cpf = cpfField.getText();
        Cliente cliente;
        LocalDateTime dataHoraVenda = LocalDateTime.now();
        try {
            cliente = clienteController.buscarClientePorCPF(cpf);
            if (cliente == null) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String endereco = enderecoField.getText();
                cliente = new Cliente(cpf, nome, email, endereco);
                clienteController.cadastrarCliente(cliente);
            }
            // Realiza a venda (futuro: integrar com vendaController)
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar venda: " + ex.getMessage());
        }
    }
}
