package controller;

import javax.swing.*;

public class ExcecoesController {

    public static class CadastroInvalidoException extends RuntimeException {
        public CadastroInvalidoException(String message) {
            super(message);
        }
    }

    public  static class CadastroValidoException extends RuntimeException {
        public CadastroValidoException(String message) {
            super(message);
        }
    }

    public static class CampoEmBrancoException extends RuntimeException {
        public CampoEmBrancoException(String message) {
            super(message);
        }
    }

    public static class LimiteCarcterException extends RuntimeException {
        public LimiteCarcterException(String message) {
            super(message);
        }
    }

    public static class ListaVaziaException extends RuntimeException {
        public ListaVaziaException(String message) {
            super(message);
        }
    }

    public static class ValorDeVendaErradoException extends RuntimeException {
        public ValorDeVendaErradoException(String message) {
            super(message);
        }
    }

    public static class ValorInvalidoException extends RuntimeException {
        public ValorInvalidoException(String message) {

            super(message);
        }
    }

    public static class SenhaInvalidaException extends RuntimeException {
        public SenhaInvalidaException(String message) {
            super(message);

        }
    }

    public static class EmailInvalidoException extends RuntimeException {
        public EmailInvalidoException(String message) {
            super(message);
        }
    }

    public static class NisPisInvalidoException extends RuntimeException {
        public NisPisInvalidoException(String message) {
            super(message);
        }
    }
    public  static class QuantidadeInvalidaException extends RuntimeException {
        public QuantidadeInvalidaException(String message) {
            super(message);
        }
    }

    public static class ProdutoNaoEncontradoException extends RuntimeException {
        public ProdutoNaoEncontradoException(String message) {
            super(message);
        }
    }
}
