package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Almoxarife;
import model.EntradaEstoque;
import view.TelaEntradaEstoqueView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntradaEstoqueController {
    private Almoxarife almoxarife;
    private TelaEntradaEstoqueView view;
    private List<EntradaEstoque> entradasEstoque;
    private final String FILE_PATH = "entradas_estoque.json";


    public EntradaEstoqueController(){

    }

    public EntradaEstoqueController(Almoxarife almoxarife, TelaEntradaEstoqueView view) {
        this.almoxarife = almoxarife;
        this.view = view;
        this.entradasEstoque = loadEntradasEstoque();
    }



    public void registrarEntrada() {
        String produto = view.getProduto();
        int quantidade = view.getQuantidade();
        double valorUnitario = view.getValorUnitarioCompra();

        EntradaEstoque entrada = new EntradaEstoque(produto, quantidade, valorUnitario, almoxarife);
        entradasEstoque.add(entrada);
        saveEntradasEstoque();

        JOptionPane.showMessageDialog(view, "Entrada registrada com sucesso!");
    }

    private List<EntradaEstoque> loadEntradasEstoque() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, EntradaEstoque.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void saveEntradasEstoque() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH), entradasEstoque);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

