package view;

import controller.CaixaEletronicoController;
import controller.ExcecoesController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAutenticacaoCaixaEletronicoView extends JFrame {

    private JTextField loginField;
    private JPasswordField senhaField;
    private JButton autenticarButton;
    private JButton cancelarButton;

    CaixaEletronicoController controller;

    public TelaAutenticacaoCaixaEletronicoView(CaixaEletronicoController controller) {
        this.controller = controller;
        setTitle("Login de Caixa Eletronico");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initialize();
    }

    private void initialize() {
        loginField = new JTextField(20);
        senhaField = new JPasswordField(20);
        autenticarButton = new JButton("Entrar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(autenticarButton);
        panel.add(cancelarButton);

        add(panel);

        autenticarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(senhaField.getPassword());


                try {
                    boolean gerenteAutenticado = controller.isAutenticacao(login, senha);

                    if (gerenteAutenticado) {
                        JOptionPane.showMessageDialog(null, "Autenticado com sucesso!");
                        dispose();
                        TelaFuncaoDoCaixaEletronicoView telaFuncaoDoCaixaEletronicoView = new TelaFuncaoDoCaixaEletronicoView();
                        telaFuncaoDoCaixaEletronicoView.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciais inválidas!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (ExcecoesController.CampoEmBrancoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Login Gerente", JOptionPane.INFORMATION_MESSAGE);
                } catch (ExcecoesController.ListaVaziaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Login Gerente", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar?", "Cancelar Login", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    dispose();

                    TelaLoginOpcoesView telaLoginOpcoesView = new TelaLoginOpcoesView();
                    telaLoginOpcoesView.setVisible(true);
                }

            }
        });
    }
}
