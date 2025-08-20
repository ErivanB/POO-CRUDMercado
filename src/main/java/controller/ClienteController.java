package controller;

import Persistencia.Persistencia;
import model.Cliente;
import view.TelaCadastroClienteView;

import javax.swing.*;
import java.util.List;

public class ClienteController {



    private TelaCadastroClienteView telaCadastroClienteView;


    public boolean cadastrarCliente(Cliente cliente) throws Exception {
        Persistencia persistencia = new Persistencia();
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Cliente> listaClientes = centralInformacoes.getClientes();

        if (isValidaCliente(cliente)) {
            listaClientes.add(cliente);
            centralInformacoes.setClientes(listaClientes);
            persistencia.salvarCentralInformacoes(centralInformacoes);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.", "Cadastro Confirmado", JOptionPane.INFORMATION_MESSAGE);
            // Fecha a tela
            telaCadastroClienteView.dispose();
            return true;
        } else {
            throw new ExcecoesController.CadastroInvalidoException("Cadastro inválido!");
        }

    }

    private boolean isValidaCliente(Cliente cliente) {

        // Extraindo dados do cliente
        String nome = cliente.getNome();
        String CPF = cliente.getCpf();
        String email = cliente.getEmail();
        String endereco = cliente.getEndereco();



        // Validando os dados
        boolean nomeValido = isLimitarCaracteres(nome, 64);
        boolean CPFValido = isLimitarCaracteres(CPF, 32);
        boolean emailValido = isLimitarCaracteres(email, 32);
        boolean enderecoValido = isLimitarCaracteres(endereco, 128);



        // Verifica se todos os dados são válidos.
        if (nome.isBlank() || CPF.isBlank() || email.isBlank() ||endereco.isBlank()) {
            throw new ExcecoesController.CampoEmBrancoException("Campo em branco em um ou mais campos obrigatórios.");
        }

        if (!email.isBlank() && !emailValido) {
            throw new ExcecoesController.EmailInvalidoException("E-mail inválido.");
        }

        if (!CPF.isBlank() && !CPFValido) {
            throw new ExcecoesController.NisPisInvalidoException("Número CPF inválido.");
        }
        if (!CPF.isBlank() && CPF.length() != 11) {
            throw new ExcecoesController.LimiteCarcterException("Número CPF inválido, verifique se você digitou 11 caracteres.");
        }
        // Se todas as validações passam, retorna verdadeiro.
        return true;

    }

    public Cliente buscarClientePorCPF(String cpf) throws Exception {
        // Recupera a lista de clientes armazenada
        Persistencia persistencia = new Persistencia();
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Cliente> listaClientes = centralInformacoes.getClientes();

        // Verifica se a lista de clientes é nula ou vazia
        if (listaClientes == null || listaClientes.isEmpty()) {
            throw new ExcecoesController.ListaVaziaException("Nenhum cliente cadastrado até o momento.");
        }

        // Percorre a lista de clientes para encontrar o CPF correspondente
        for (Cliente cliente : listaClientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente; // Retorna o cliente encontrado
            }
        }

        // Caso o cliente não seja encontrado, retorna null ou lança uma exceção personalizada
        throw new ExcecoesController.CadastroInvalidoException("Cliente com o CPF informado não foi encontrado.");
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
