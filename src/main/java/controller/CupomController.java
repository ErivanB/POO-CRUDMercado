package controller;

import Persistencia.Persistencia;
import model.Cliente;
import model.Cupom;
import view.TelaCadastroCupomView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class CupomController {

    public CupomController() {
    }

    public void gerarCupom(TelaCadastroCupomView telaCadastroCupomView) throws Exception {

        Persistencia persistencia = new Persistencia();
        // Recupera a lista de clientes
        CentralInformacoesController centralInformacoes = persistencia.recuperarCentralInformacoes();
        List<Cliente> listaClientes = centralInformacoes.getClientes();

        // Obtém os dados da tela
        String codigoCupom = telaCadastroCupomView.getCampoCodigo().getText();
        String percentualTexto = telaCadastroCupomView.getCampoPercentual().getText();

        if (codigoCupom.isEmpty() || percentualTexto.isEmpty()) {
            throw new Exception("Os campos não podem estar vazios!");
        }

        double percentual = Double.parseDouble(percentualTexto);

        // Cria o cupom
        CupomController novoCupom = new CupomController();
        novoCupom.gerarCupom(telaCadastroCupomView);

        // Envia o cupom para os clientes com e-mail válido
        for (Cliente cliente : listaClientes) {

            if (cliente.getEmail() != null && !cliente.getEmail().isEmpty()) {
                enviarEmail(cliente.getEmail(),novoCupom,telaCadastroCupomView);
            }
        }

        // Salva as informações atualizadas
        persistencia.salvarCentralInformacoes(centralInformacoes);
    }

    public void enviarEmail(String emailDestinatario, CupomController cupom,TelaCadastroCupomView telaCadastroCupomView) throws Exception {
        // Configuração do servidor de e-mail (SMTP)
        String host = "smtp.example.com";  // Altere para o seu host SMTP
        String usuario = "seuemail@example.com";  // Altere para seu e-mail
        String senha = "suasenha";  // Altere para sua senha

        // Configuração das propriedades para o envio de e-mail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        // Porta do servidor SMTP
        props.put("mail.smtp.port", "587");

        // Autenticação
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            // Criação da mensagem

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
            message.setSubject("Novo Cupom de Desconto!");
            message.setText("Olá, temos um novo cupom de desconto para você!\n" +
                    "Código do cupom: " + telaCadastroCupomView.getCampoPercentual() + "\n" +
                    "Desconto: " + telaCadastroCupomView.getCampoPercentual() + "%\n" +
                    "Aproveite!");

            // Envio da mensagem
            Transport.send(message);
            System.out.println("E-mail enviado para: " + emailDestinatario);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
