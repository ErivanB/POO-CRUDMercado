import Persistencia.Persistencia;
import controller.*;
import model.Produto;
import view.*;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        // Inicializa os controladores
        GerenteController gerenteController = new GerenteController();
        AlmoxarifeController almoxarifeController = new AlmoxarifeController();
        CaixaEletronicoController caixaController = new CaixaEletronicoController();
        ClienteController clienteController = new ClienteController();
        TelaCadastroClienteView cadastroClienteView = new TelaCadastroClienteView(clienteController);
        ProdutoController produtoController = new ProdutoController();
        try {
            TelaProdutoView telaProdutoView = new TelaProdutoView(produtoController);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CentralInformacoesController centralInformacoes;

        try {
            Persistencia repositorio = new Persistencia();
            centralInformacoes = repositorio.recuperarCentralInformacoes();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Imprimindo os dados recuperados
        centralInformacoes.getGerentes().forEach(
                gerente -> System.out.println("Nome do GERENTE : " + gerente.getNome() + "\nLoguin do usuario: " + gerente.getLogin() + "\nSenha do usuario: " + gerente.getSenha())
        );

        // Start do programa;
        boolean stop = true;


        while (stop) {
            // Verifica se existe gerente cadastrado.
            if (centralInformacoes.getGerentes() == null || centralInformacoes.getGerentes().isEmpty()) {

                // Inicia tela de cadastro de gerente.
                inicializarTelaCadastroGerente(gerenteController);
                stop = false;

                // Caso já a lista já tenha um gerente cadastrado, inicia tela de autenticação.
            } else {

                // Tela de autenticação.

                inicializarTelaLoginOpcoes();


                stop = false;

            }
        }
        inicializarTelaCadastroAlmoxarife(almoxarifeController);
        inicializarTelaCadastroCaixaEletronico(caixaController);
        try {

            inicializarTelaCadastroProduto(centralInformacoes.getProdutos());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Métodos de inicialização para cada tela
    private static void inicializarTelaCadastroGerente(GerenteController gerenteController) {
        TelaCadastroGerenteView telaCadastroGerente = new TelaCadastroGerenteView(gerenteController);
        telaCadastroGerente.setVisible(true);
    }

    private static void inicializarTelaCadastroAlmoxarife(AlmoxarifeController almoxarifeController) {
        TelaCadastroAlmoxarifeView telaCadastroAlmoxarife = new TelaCadastroAlmoxarifeView(almoxarifeController);
        telaCadastroAlmoxarife.setVisible(false);
    }

    private static void inicializarTelaCadastroCaixaEletronico(CaixaEletronicoController caixaController) {
        TelaCadastroCaixaEletronicoView telaCadastroCaixa = new TelaCadastroCaixaEletronicoView(caixaController);
        telaCadastroCaixa.setVisible(false);
    }

    private static void inicializarTelaCadastroProduto(List<Produto> listaDeProdutosTeste) throws Exception {
       ProdutoController produtoController =new ProdutoController();
        TelaProdutoView produtoView = new TelaProdutoView(produtoController);
        produtoView.setVisible(false);
        ProdutoController produtoController2 = new ProdutoController();

        TelaCadastroProdutoView telaCadastroProduto = new TelaCadastroProdutoView(produtoController);
        telaCadastroProduto.setVisible(false);

        produtoView.setVisible(false);
    }
    private static void inicializarTelaAtualizaValor(GerenteController gerenteController, ProdutoController produtoController) throws Exception {
        TelaAtualizaValorView telaGerente = new TelaAtualizaValorView();
        try {
            GerenteController gerenteControllerComTela = new GerenteController(telaGerente, produtoController);
            telaGerente.setVisible(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Inicia tela de opções de Login
    private static void inicializarTelaLoginOpcoes() {

        TelaLoginOpcoesView loginOpcoesView = new TelaLoginOpcoesView();
        loginOpcoesView.setVisible(true);
    }
}
