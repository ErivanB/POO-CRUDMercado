package controller;

import Persistencia.Persistencia;
import model.Almoxarife;
import model.Gerente;
import view.TelaAtualizaValorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AlmoxarifeController {

    private Gerente gerente;
    private TelaAtualizaValorView tela;
    private ProdutoController produtoController;
    private CentralInformacoesController centralInformacoes;
    private Persistencia persistencia;

    public  AlmoxarifeController(){

    }

    public AlmoxarifeController(TelaAtualizaValorView tela, ProdutoController produtoController) {
        this.tela = tela;
        this.produtoController = produtoController;

        // Adiciona listener para atualizar o valor do produto
        this.tela.adicionarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //erro pesado não to conseguindo fazer, monitor não consegue ajudar.
            }
        });
    }

    public void cadastrarAlmoxarife(Almoxarife novoAlmoxarife)throws Exception {

        Persistencia persistencia = new Persistencia();
        // Recupera a lista existente
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Almoxarife> listaAlmoxarife = centralInformacoes.getAlmoxarifes();


        if (listaAlmoxarife == null) {
            listaAlmoxarife = new ArrayList<>();
        }

        if (isValidaAlmoxarife(novoAlmoxarife)) {
            listaAlmoxarife.add(novoAlmoxarife);
            centralInformacoes.setAlmoxarifes(listaAlmoxarife);
            persistencia.salvarCentralInformacoes(centralInformacoes);
            throw new ExcecoesController.CadastroValidoException("Cadastro realizado com sucesso.");
        } else {
            throw new ExcecoesController.CadastroInvalidoException("Cadastro inválido!!!");
        }
    }

    public boolean isValidaAlmoxarife(Almoxarife almoxarife) {

        // Extraindo dados do gerente
        String nome = almoxarife.getNome();
        String login = almoxarife.getLogin();
        String senha = almoxarife.getSenha();
        String email = almoxarife.getEmail();
        String nisPis = almoxarife.getNisPis();

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
        List<Almoxarife> listaAlmoxarife = centralInformacoes.getAlmoxarifes();

        // Verifica se os campos de login e senha não são nulos ou em branco
        if (login == null || login.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("O campo de login está em branco.");
        }
        if (senha == null || senha.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("O campo de senha está em branco.");
        }

        // Verifica se a lista de gerentes é nula ou vazia
        if (centralInformacoes.getAlmoxarifes() == null || centralInformacoes.getAlmoxarifes().isEmpty()) {
            throw new ExcecoesController.ListaVaziaException("Nenhum almoxarife cadastrado até o momento.");
        }

        // Percorre a lista de gerentes para autenticar
        for (Almoxarife almoxarife : centralInformacoes.getAlmoxarifes()) {
            if (almoxarife.getLogin().equals(login) && almoxarife.getSenha().equals(senha)) {
                // Autenticação concluída com sucesso
                return true;
            }
        }

        // Login ou senha incorretos
        return false;
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
}