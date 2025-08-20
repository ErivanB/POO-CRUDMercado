package model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Venda {
    private Cliente cliente;
    private LocalDateTime dataHora;
    private Cupom cupom;

    private List<Produto> produtos;

    public Venda(Cliente cliente, List<Produto> produtos, LocalDateTime dataHora, Cupom cupom) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataHora = dataHora;
        this.cupom = cupom;
    }

    public Venda(){

    }

    private Venda venda;

    public void registrarVenda() {
        // Lógica para registrar venda
    }

    public void emitirNota() {
        // Lógica para emitir nota
    }

    public double calcularValorTotal(Venda venda) {
        double total = produtos.stream().mapToDouble(Produto::getValorVenda).sum();
        if (cupom != null) {
            total -= total * (cupom.getPercentualDesconto() / 100);
        }
        return total;
    }

    public double calcularValorTotal(){
       double total = calcularValorTotal(venda);
       return total;
    }



}

