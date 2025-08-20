package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TelaCadastroCupomView extends JFrame {

    private JTextField campoCodigo;
    private JTextField campoPercentual;
    private JButton botaoGerarCodigo;
    private JButton botaoCadastrar;

    public TelaCadastroCupomView() {
        setTitle("Cadastro de Cupom de Desconto");
        setSize(600, 300);  // Aumentando o tamanho da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel de campos, gastei tempo demias nisso.
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;  // Faz o campo expandir no eixo X
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Campo Código
        JLabel labelCodigo = new JLabel("Código do Cupom:");
        painelCampos.add(labelCodigo, gbc);

        // Tamnjo não esta bom
        gbc.gridx = 1;
        campoCodigo = new JTextField();
        campoCodigo.setEditable(false);
        campoCodigo.setPreferredSize(new Dimension(250, 30));
        painelCampos.add(campoCodigo, gbc);

        // Botão Gerar Código
        gbc.gridx = 2;
        botaoGerarCodigo = new JButton("Gerar Código");
        botaoGerarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoCodigo.setText(gerarCodigoCupom());
            }
        });
        painelCampos.add(botaoGerarCodigo, gbc);

        // Campo Percentual de Desconto
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel labelPercentual = new JLabel("Percentual de Desconto:");
        painelCampos.add(labelPercentual, gbc);


        // Tamnho esta fora do padrão não sei o motivo, mas quando aparece na tela parece estar menor.
        gbc.gridx = 1;
        campoPercentual = new JTextField();
        campoPercentual.setPreferredSize(new Dimension(250, 30));
        painelCampos.add(campoPercentual, gbc);

        // Adiciona painelCampos ao painelPrincipal
        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // Painel de botões (FlowLayout)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botaoCadastrar = new JButton("Cadastrar Cupom");
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCupom();
            }
        });
        painelBotoes.add(botaoCadastrar);

        // Adiciona painelBotoes ao painelPrincipal
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Adiciona o painel principal ao JFrame
        add(painelPrincipal);
        setVisible(true);
    }

    public JTextField getCampoCodigo() {
        return campoCodigo;
    }

    public JTextField getCampoPercentual() {
        return campoPercentual;
    }

    private String gerarCodigoCupom() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            codigo.append(chars.charAt(random.nextInt(chars.length())));
        }
        return codigo.toString();
    }

    private void cadastrarCupom() {
        String codigo = campoCodigo.getText();
        String percentualTexto = campoPercentual.getText();

        if (codigo.isEmpty() || percentualTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double percentual = Double.parseDouble(percentualTexto);
            if (percentual < 5 || percentual > 100) {
                JOptionPane.showMessageDialog(this, "Percentual inválido! Deve estar entre 5% e 80%.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lógica para enviar o cupom por e-mail e salvar no banco de dados
            JOptionPane.showMessageDialog(this, "Cupom cadastrado com sucesso!");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Percentual inválido! Deve ser um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
