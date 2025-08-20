package controller;

import Persistencia.Persistencia;
import model.Cliente;
import model.Gerente;
import model.Produto;
import view.TelaAtualizaValorView;
import view.TelaCadastroCupomView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


//Controlador que gerencia a interação entre o Gerente, a tela e o repositório de produtos.


public class GerenteController {

    private Gerente gerente;
    private TelaAtualizaValorView tela;
    private ProdutoController produtoController;
    private CentralInformacoesController centralInformacoes;
    private Persistencia persistencia;



    // Construtor para inicializar o controlador com uma tela e um repositório de produtos
    // Tela gráfica onde o gerente interage
    // Repositório onde os produtos são armazenado
    public GerenteController(TelaAtualizaValorView tela, ProdutoController produtoController) {
        this.tela = tela;
        this.produtoController = produtoController;

        // Adiciona listener para atualizar o valor do produto
        this.tela.adicionarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                atualizarValorProduto();
            }
        });
    }

    public GerenteController() {

    }

    // Metodo para cadastrar gerentes em uma lista de objetos Gerente.
    public void cadastrarGerente(Gerente novoGerente) throws Exception {

        Persistencia persistencia = new Persistencia();
        // Recupera a lista existente
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Gerente> listaGerentes = centralInformacoes.getGerentes();

        if (listaGerentes == null) {
            listaGerentes = new ArrayList<>();
        }

        if (isValidaGerente(novoGerente)) {
            listaGerentes.add(novoGerente);
            centralInformacoes.setGerentes(listaGerentes);
            persistencia.salvarCentralInformacoes(centralInformacoes);
            throw new ExcecoesController.CadastroValidoException("Cadastro realizado com sucesso.");
        } else {
            throw new ExcecoesController.CadastroInvalidoException("Cadastro inválido!!!");
        }
    }

    public boolean isValidaGerente(Gerente gerente) {

        // Extraindo dados do gerente
        String nome = gerente.getNome();
        String login = gerente.getLogin();
        String senha = gerente.getSenha();
        String email = gerente.getEmail();
        String nisPis = gerente.getNisPis();

        // Validando os dados
        boolean nomeValido = isLimitarCaracteres(nome, 64);
        boolean loginValido = isLimitarCaracteres(login, 32);
        boolean senhaValida = isLimitarCaracteres(senha, 128);
        boolean emailValido = isLimitarCaracteres(email, 32);
        boolean nisPisValido = isLimitarCaracteres(nisPis, 11);

        // Verifica se todos os dados são válidos.
        if (nome.isBlank() || login.isBlank() || senha.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("Campo em branco em um ou mais campos obrigatórios.");
        }

        if (senha.length() < 8) {
            throw new ExcecoesController.SenhaInvalidaException("Senha deve ter no mínimo 8 caracteres.");
        }

        if (!email.isBlank() && !emailValido) {
            throw new ExcecoesController.EmailInvalidoException("E-mail inválido.");
        }

        if (!nisPis.isBlank() && !nisPisValido) {
            throw new ExcecoesController.NisPisInvalidoException("Número NIS/PIS inválido.");
        }
        if (!nisPis.isBlank() && nisPis.length() != 11) {
            throw new ExcecoesController.LimiteCarcterException("Número NIS/PIS inválido, verifique se você digitou 11 caracteres.");
        }
        // Se todas as validações passam, retorna verdadeiro.
        return true;

    }

    public boolean isAutenticacao(String login, String senha) throws Exception {


        Persistencia persistencia = new Persistencia();
        // Recupera a lista existente
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Gerente> listaGerentes = centralInformacoes.getGerentes();

        // Verifica se os campos de login e senha não são nulos ou em branco
        if (login == null || login.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("O campo de login está em branco.");
        }
        if (senha == null || senha.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("O campo de senha está em branco.");
        }

        // Verifica se a lista de gerentes é nula ou vazia
        if (centralInformacoes.getGerentes() == null || centralInformacoes.getGerentes().isEmpty()) {
            throw new ExcecoesController.ListaVaziaException("Nenhum gerente cadastrado até o momento.");
        }

        // Percorre a lista de gerentes para autenticar
        for (Gerente gerente : centralInformacoes.getGerentes()) {
            if (gerente.getLogin().equals(login) && gerente.getSenha().equals(senha)) {
                // Autenticação concluída com sucesso
                return true;
            }
        }

        // Login ou senha incorretos
        return false;
    }


    public boolean isValidaProduto(Produto produto) {

        // Extraindo dados do produto
        String codigo = produto.getCodigo();
        String nome = produto.getNome();
        String unidade = produto.getUnidade();
        double valorCompra = produto.getValorCompra();
        double valorVenda = produto.getValorVenda();
        double valorUnitario = produto.getValorUnitario();
        int quantidadeNoEstoque = produto.getQuantidadeEstoque();

        // Verificando se o código não é nulo e tem um comprimento válido
        if (codigo == null || codigo.trim().isEmpty() || codigo.length() < 5) {
            throw new ExcecoesController.CampoEmBrancoException("Campo 'código' está vazio ou contém um valor inválido.");
        }

        // Verificando se o nome do produto não é nulo e não está vazio
        if (nome == null || nome.trim().isEmpty()) {
            throw new ExcecoesController.CampoEmBrancoException("Campo 'nome' está vazio.");
        }

        // Verificando se a unidade não é nula e não está vazia
        if (unidade == null || unidade.trim().isEmpty()) {
            throw new ExcecoesController.CampoEmBrancoException("Campo 'unidade' está vazio ou contém um valor inválido.");

        }

        // Verificando se os valores financeiros são positivos
        if (valorCompra < 0 || valorVenda < 0 || valorUnitario < 0) {
            throw new ExcecoesController.CampoEmBrancoException("Campo de 'valores' está vazio ou contém um valor inválido.");

        }

        // Verificando se o valor de venda é maior que o valor de compra
        if (valorVenda <= valorCompra) {
            throw new ExcecoesController.ValorDeVendaErradoException("Campo 'unidade' está vazio ou contém um valor inválido.");

        }

        // Verificando se a quantidade no estoque é não negativa.
        if (quantidadeNoEstoque < 0) {
            return false;
        }

        // Se todas as verificações passarem, o produto é válido
        return true;
    }


    //Cadastra um novo produto.Dados do produto.
    public void cadastrarProduto(Produto novoProduto) throws Exception {

        Persistencia persistencia = new Persistencia();
        // Recupera a lista existente
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Produto> listaProduto = centralInformacoes.getProdutos();

        if (listaProduto == null) {
            listaProduto = new ArrayList<>();
        }

        if (isValidaProduto(novoProduto)) {
            listaProduto.add(novoProduto);
            centralInformacoes.setProdutos(listaProduto);
            persistencia.salvarCentralInformacoes(centralInformacoes);
            throw new ExcecoesController.CadastroValidoException("Cadastro realizado com sucesso.");
        } else {
            throw new ExcecoesController.CadastroInvalidoException("Cadastro inválido!!!");
        }
    }

    //limitador de caracteres.
    private boolean isLimitarCaracteres(String texto, int limiteMaximo) {

        if (texto == null || texto.isBlank()) {
            return false;
        }
        if (texto.length() > limiteMaximo) {

            return false;
        }
        return true;
    }

    //Atualiza o valor de um produto a partir da interface gráfica.
    private void atualizarValorProduto() {
        String nomeProduto = tela.getNomeProduto();
        double novoValor = tela.getValorVenda();


    try{
        Produto produto = produtoController.buscarPorNome(nomeProduto);

        if (produto != null) {
            produto.setValorUnitario(novoValor);
            JOptionPane.showMessageDialog(tela, "Valor atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(tela, "Produto não encontrado.");
        }

    } catch (Exception e) {
        throw new RuntimeException(e);
        }

    }

    public void enviaEmailDesconto(CupomController cupom, TelaCadastroCupomView telaCadastroCupomView)throws Exception{

        // Recupera a lista existente
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Cliente> listaCliente = centralInformacoes.getClientes();

        for (Cliente cliente : listaCliente) {
            if(!cliente.getEmail().isBlank()){

                cupom.enviarEmail(cliente.getEmail(), cupom, telaCadastroCupomView);
            }
        }


    }
}

