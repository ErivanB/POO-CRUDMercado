package view;

import controller.ClienteController;
import controller.ExcecoesController;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroClienteView extends JFrame {

    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField enderecoField;
    private JCheckBox receberPromocoesCheckBox;
    private JButton cadastrarButton;
    private JButton cancelarButton;

    public ClienteController controller = new ClienteController();

    public TelaCadastroClienteView(ClienteController controller) {
        this.controller = controller;
        setTitle("Cadastro de Cliente");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initialize();
    }

    private void initialize() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Seção de informações do cliente
        JLabel infoLabel = new JLabel("Informações do Cliente");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(infoLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // Campo Nome
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Nome:"), gbc);
        nomeField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(nomeField, gbc);

        // Campo CPF
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("CPF:"), gbc);
        cpfField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(cpfField, gbc);

        // Campo Email
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("E-mail:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Campo Endereço
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Endereço (opcional):"), gbc);
        enderecoField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(enderecoField, gbc);

        // Seção de Preferências
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel prefLabel = new JLabel("Preferências");
        prefLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(prefLabel, gbc);

        // Checkbox Promoções
        receberPromocoesCheckBox = new JCheckBox("Desejo receber promoções");
        gbc.gridy++;
        gbc.gridwidth = 2;
        mainPanel.add(receberPromocoesCheckBox, gbc);

        // Botões de Ação
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cadastrarButton = new JButton("Cadastrar");
        cancelarButton = new JButton("Cancelar");
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(cancelarButton);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        // Listener do botão cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente novoCliente = new Cliente(nomeField.getText(), cpfField.getText(), emailField.getText(), enderecoField.getText());
                    if (controller.cadastrarCliente(novoCliente)) {
                        int response = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            controller.cadastrarCliente(novoCliente);
                            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.", "Cadastro Confirmado", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cadastro cancelado.", "Cadastro Cancelado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (ExcecoesController.CampoEmBrancoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Campo em Branco", JOptionPane.ERROR_MESSAGE);
                } catch (ExcecoesController.CadastroInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Cadastro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    dispose();
                }
            }
        });

        // Listener do botão cancelar
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

    public String getNome() {
        return nomeField.getText();
    }

    public String getCpf() {
        return cpfField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getEndereco() {
        return enderecoField.getText();
    }

    public boolean isReceberPromocoes() {
        return receberPromocoesCheckBox.isSelected();
    }
}
