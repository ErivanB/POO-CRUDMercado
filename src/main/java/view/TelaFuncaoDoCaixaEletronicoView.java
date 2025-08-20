package view;

import controller.ClienteController;
import controller.VendaController;

import javax.swing.*;
import java.awt.*;

public class TelaFuncaoDoCaixaEletronicoView extends JFrame {

    private JButton botaoCadastrarCliente;
    private JButton botaoRealizarVenda;
    private JButton botaoCancelar;

    public TelaFuncaoDoCaixaEletronicoView() {
        setTitle("Funções do Caixa Eletrônico");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Painel para os botões à esquerda
        JPanel painelBotoesEsquerda = new JPanel();
        painelBotoesEsquerda.setLayout(new BoxLayout(painelBotoesEsquerda, BoxLayout.Y_AXIS));

        // Criação dos botões
        botaoCadastrarCliente = criarBotao("Cadastrar Cliente");
        botaoRealizarVenda = criarBotao("Realizar Venda");
        botaoCancelar = criarBotao("Cancelar");

        // Adiciona os botões "Cadastrar Cliente" e "Realizar Venda" ao painel esquerdo
        painelBotoesEsquerda.add(botaoCadastrarCliente);
        painelBotoesEsquerda.add(Box.createVerticalStrut(10)); // Espaçamento entre os botões
        painelBotoesEsquerda.add(botaoRealizarVenda);

        // Adiciona o painel de botões à esquerda
        painelPrincipal.add(painelBotoesEsquerda, BorderLayout.WEST);

        // Adiciona o botão "Cancelar" centralizado na parte inferior
        JPanel painelCancelar = new JPanel();
        painelCancelar.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelCancelar.add(botaoCancelar);
        painelPrincipal.add(painelCancelar, BorderLayout.SOUTH);

        // Adiciona o painel principal ao frame
        add(painelPrincipal);
        setVisible(true);

        // Configurações dos ActionListeners para cada botão
        botaoCadastrarCliente.addActionListener(e -> cadastrarCliente());
        botaoRealizarVenda.addActionListener(e -> realizarVenda());
        botaoCancelar.addActionListener(e -> cancelar());
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        // Tamanho não esta uniforme na tela, verificar isso amanhã.
        botao.setPreferredSize(new Dimension(180, 40));
        return botao;
    }

    private void cadastrarCliente() {
        JOptionPane.showMessageDialog(this, "Cadastrar Cliente");
        ClienteController controller = new ClienteController();
        TelaCadastroClienteView telaCadastroClienteView = new TelaCadastroClienteView(controller);
        telaCadastroClienteView.setVisible(true);
    }

    private void realizarVenda() {
        JOptionPane.showMessageDialog(this, "Realizar Venda");
        // Lógica para realizar venda

        ClienteController controller = new ClienteController();
        VendaController vendaController = new VendaController();
        TelaVendaView vendaView = new TelaVendaView(controller, vendaController);
        vendaView.setVisible(true);
    }

    private void cancelar() {
        JOptionPane.showMessageDialog(this, "Operação Cancelada");
        dispose();
    }

    // Métodos get para retornar os botões
    public JButton getBotaoCadastrarCliente() {
        return botaoCadastrarCliente;
    }

    public JButton getBotaoRealizarVenda() {
        return botaoRealizarVenda;
    }

    public JButton getBotaoCancelar() {
        return botaoCancelar;
    }
}
