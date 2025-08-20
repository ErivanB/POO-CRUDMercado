package view;

import controller.CaixaEletronicoController;
import controller.ExcecoesController;
import model.CaixaEletronico;
import model.Gerente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TelaCadastroCaixaEletronicoView extends JFrame {

    private JTextField nomeField;
    private JTextField loginField;
    private JPasswordField senhaField;
    private JTextField emailField;
    private JTextField nisPisField;
    private JButton cadastrarButton;
    private JButton cancelarButton;
    private CaixaEletronicoController controller;

    public TelaCadastroCaixaEletronicoView(CaixaEletronicoController controller) {
        this.controller = controller;
        setTitle("Cadastro de Caixa Eletrônico");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initialize();
    }

    private void initialize() {
        nomeField = new JTextField(20);
        loginField = new JTextField(20);
        senhaField = new JPasswordField(20);
        emailField = new JTextField(20);
        nisPisField = new JTextField(20);

        cadastrarButton = new JButton("Cadastrar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(new JLabel("Email (opcional):"));
        panel.add(emailField);
        panel.add(new JLabel("NIS/PIS (opcional):"));
        panel.add(nisPisField);
        panel.add(cadastrarButton);
        panel.add(cancelarButton);

        add(panel);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Coleta os dados e cria um gerente.
                    CaixaEletronico novoCaixaEletronico = new CaixaEletronico(nomeField.getText(), loginField.getText(), new String(senhaField.getPassword()), emailField.getText(), nisPisField.getText());

                    // Verifica se o gerente é válido.
                    if (controller.isValidaCaixaEletronico(novoCaixaEletronico)) {

                        // Exibe mensagem de confirmação.
                        int response = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {

                            controller.cadastrarCaixaEletronico(novoCaixaEletronico);

                            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.", "Cadastro Confirmado", JOptionPane.INFORMATION_MESSAGE);
                            // Fecha a janela se confirmado.
                            dispose(); // Fecha a janela se confirmado.

                        } else {

                            JOptionPane.showMessageDialog(null, "Cadastro cancelado.", "Cadastro Cancelado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (ExcecoesController.CadastroInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Cadastro", JOptionPane.ERROR_MESSAGE);
                } catch (ExcecoesController.SenhaInvalidaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Senha", JOptionPane.ERROR_MESSAGE);
                } catch (ExcecoesController.CampoEmBrancoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Campo em Branco", JOptionPane.ERROR_MESSAGE);
                } catch (ExcecoesController.EmailInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Email", JOptionPane.INFORMATION_MESSAGE);
                } catch (ExcecoesController.NisPisInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro NIS/PIS", JOptionPane.INFORMATION_MESSAGE);
                } catch (ExcecoesController.LimiteCarcterException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro Limite", JOptionPane.ERROR_MESSAGE);
                } catch (ExcecoesController.CadastroValidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Salvo com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
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

