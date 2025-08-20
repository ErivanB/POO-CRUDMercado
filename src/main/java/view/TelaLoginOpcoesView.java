package view;

import controller.AlmoxarifeController;
import controller.CaixaEletronicoController;
import controller.GerenteController;
import controller.VendaController;
import model.Almoxarife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginOpcoesView extends JFrame {

    private JCheckBox almoxarifeCheckBox;
    private JCheckBox caixaEletronicoCheckBox;
    private JCheckBox gerenteCheckBox;
    private JButton confirmarButton;
    private JButton cancelarButton;

    public TelaLoginOpcoesView() {
        setTitle("Tipo de Login");
        setSize(250, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        almoxarifeCheckBox = new JCheckBox("Almoxarife");
        caixaEletronicoCheckBox = new JCheckBox("Caixa Eletrônico");
        gerenteCheckBox = new JCheckBox("Gerente");

        confirmarButton = new JButton("Confirmar");
        cancelarButton = new JButton("Cancelar");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(almoxarifeCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(caixaEletronicoCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(gerenteCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(confirmarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(cancelarButton, gbc);

        almoxarifeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (almoxarifeCheckBox.isSelected()) {
                    caixaEletronicoCheckBox.setSelected(false);
                    gerenteCheckBox.setSelected(false);
                }
            }
        });

        caixaEletronicoCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (caixaEletronicoCheckBox.isSelected()) {
                    almoxarifeCheckBox.setSelected(false);
                    gerenteCheckBox.setSelected(false);
                }
            }
        });

        gerenteCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gerenteCheckBox.isSelected()) {
                    almoxarifeCheckBox.setSelected(false);
                    caixaEletronicoCheckBox.setSelected(false);
                }
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tipoLogin = getTipoLoginSelecionado();

                if (tipoLogin > -1 && tipoLogin < 4) {
                    // Chame a tela de login correspondente
                    switch (tipoLogin) {

                        case 0:
                            JOptionPane.showMessageDialog(null, "Abrindo tela de login para: Almoxarife");
                            break;

                        case 1:
                            JOptionPane.showMessageDialog(null, "Abrindo tela de login para: Caixa Eletronico");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "Abrindo tela de login para: Gerente");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Nenhuma caixa foi selecionada");
                            break;
                    }

                    // Aqui você pode chamar a tela correspondente, por exemplo:
                    if (tipoLogin == 0) {

                        AlmoxarifeController almoxarifeController = new AlmoxarifeController();

                        // abrir TelaAlmoxarife();
                        TelaAutenticacaoAlmoxarifeView telaAutenticacaoAlmoxarifeView = new TelaAutenticacaoAlmoxarifeView(almoxarifeController);
                        telaAutenticacaoAlmoxarifeView.setVisible(true);

                    } else if (tipoLogin == 1) {
                        // abrir TelaCaixaEletronico();
                        CaixaEletronicoController caixaEletronicoController = new CaixaEletronicoController();
                        TelaAutenticacaoCaixaEletronicoView telaAutenticacaoCaixaEletronicoView = new TelaAutenticacaoCaixaEletronicoView(caixaEletronicoController);
                        telaAutenticacaoCaixaEletronicoView.setVisible(true);
                    } else if (tipoLogin == 2) {
                        // abrir TelaGerente();

                        GerenteController gerenteController = new GerenteController();
                        TelaAutenticacaoGerenteView telaAutenticacaoGerenteView = new TelaAutenticacaoGerenteView(gerenteController);
                        telaAutenticacaoGerenteView.setVisible(true);
                    }
                    // Fechar a janela atual
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma opção de login.");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    // Metodo para verificar qual checkbox está selecionado
    // usa inteiros para verificar o tipo de seleção
    private int getTipoLoginSelecionado() {
        if (almoxarifeCheckBox.isSelected()) {
            return 0;
        } else if (caixaEletronicoCheckBox.isSelected()) {
            return 1;
        } else if (gerenteCheckBox.isSelected()) {
            return 2;
        }
        // Nenhuma opção selecionada
        return 4;
    }
}
