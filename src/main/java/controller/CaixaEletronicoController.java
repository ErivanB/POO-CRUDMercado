package controller;

import Persistencia.Persistencia;
import model.CaixaEletronico;

import java.util.ArrayList;
import java.util.List;

public class CaixaEletronicoController {

    public CaixaEletronicoController(){

    }
    public void cadastrarCaixaEletronico(CaixaEletronico novoCaixaEletronico)throws Exception {


        Persistencia persistencia = new Persistencia();
        // Recupera a lista existente
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<CaixaEletronico> listaCaixaEletronico = centralInformacoes.getCaixaEletronicos();

        if (listaCaixaEletronico == null) {
            listaCaixaEletronico = new ArrayList<>();
        }

        if (isValidaCaixaEletronico(novoCaixaEletronico)) {
            listaCaixaEletronico.add(novoCaixaEletronico);
            centralInformacoes.setCaixaEletronicos(listaCaixaEletronico);
            persistencia.salvarCentralInformacoes(centralInformacoes);
            throw new ExcecoesController.CadastroValidoException("Cadastro realizado com sucesso.");
        } else {
            throw new ExcecoesController.CadastroInvalidoException("Cadastro inválido!!!");
        }
    }

    public boolean isValidaCaixaEletronico(CaixaEletronico caixaEletronico) {

        // Extraindo dados do gerente
        String nome = caixaEletronico.getNome();
        String login = caixaEletronico.getLogin();
        String senha = caixaEletronico.getSenha();
        String email = caixaEletronico.getEmail();
        String nisPis = caixaEletronico.getNisPis();

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
        List<CaixaEletronico> listaCaixaEletronico = centralInformacoes.getCaixaEletronicos();

        // Verifica se os campos de login e senha não são nulos ou em branco
        if (login == null || login.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("O campo de login está em branco.");
        }
        if (senha == null || senha.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("O campo de senha está em branco.");
        }

        // Verifica se a lista de gerentes é nula ou vazia
        if (centralInformacoes.getCaixaEletronicos() == null || centralInformacoes.getCaixaEletronicos().isEmpty()) {
            throw new ExcecoesController.ListaVaziaException("Nenhum gerente cadastrado até o momento.");
        }

        // Percorre a lista de gerentes para autenticar
        for (CaixaEletronico caixaEletronico : centralInformacoes.getCaixaEletronicos()) {
            if (caixaEletronico.getLogin().equals(login) && caixaEletronico.getSenha().equals(senha)) {
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