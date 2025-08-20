package controller;

import model.Cliente;
import model.Cupom;
import model.Produto;
import model.Venda;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VendaController {

    public void registrarVenda(Map<String, Object> dados) {
        Cliente cliente = (Cliente) dados.get("cliente");
        List<Produto> produtosCarrinho = (List<Produto>) dados.get("produtos");
        LocalDateTime dataHoraVenda = LocalDateTime.now();
        Cupom cupom = (Cupom) dados.get("cupom");

        Venda venda = new Venda(cliente, produtosCarrinho, dataHoraVenda, cupom);
        venda.registrarVenda();
    }
    public VendaController(){

    }

    public void emitirNota(Venda venda) {

        venda.emitirNota();
    }


    private Venda venda;

    public void iniciarVenda(Cliente cliente, List<Produto> produtos,LocalDateTime dataHora ,Cupom cupom) {
        venda = new Venda(cliente, produtos, dataHora ,cupom);
    }

    public double finalizarVenda() {
        return venda.calcularValorTotal();
    }
}

