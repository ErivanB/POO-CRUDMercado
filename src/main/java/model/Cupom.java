package model;

import controller.CupomController;

public class Cupom {


    private String codigo;
    private double percentualDesconto;

    public String getCodigo() {

        return codigo;
    }

    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    public double getPercentualDesconto() {

        return percentualDesconto;
    }

    public void setPercentualDesconto(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }



    public Cupom(String codigo, double percentualDesconto) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
    }

    public Cupom() {

    }

}

