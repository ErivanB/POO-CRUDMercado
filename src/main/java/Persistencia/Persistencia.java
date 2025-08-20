package Persistencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.CentralInformacoesController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Persistencia {

    private CentralInformacoesController centralInformacoes;
    private File arquivoRepositorioJson;
    private final static String ArquivoRepositorioJson = "repositorio.json";

    public Persistencia() throws IOException {

        this.arquivoRepositorioJson = new File(ArquivoRepositorioJson);
        // Cria um novo arquivo vazio, de acordo com o nome passado no construtor,
        // se ele ainda não existir
        this.arquivoRepositorioJson.createNewFile();
    }

    public void salvarCentralInformacoes(CentralInformacoesController persistencia) throws Exception {
        // Classe q provê leitura e escrita de arquivos JSON.
        ObjectMapper mapper = new ObjectMapper();
        //String json = mapper.writer().writeValueAsString(persistencia);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(persistencia);
        // Mostrar diferenca entre o metodo writer e writerWithDefaultPrettyPrinter.
        // da classe ObjectMapper
        System.out.println("########## Salvando Informações ##########");
        // Classe para gravar textos em arquivo
        FileWriter file = new FileWriter(arquivoRepositorioJson, StandardCharsets.UTF_8);
        file.write(json);
        System.out.println("########## Informações Foram Salvas ##########");
        System.out.println("********** Repositorio.json Foi Atualizado **********");
        file.close();
    }

    public CentralInformacoesController recuperarCentralInformacoes() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("********** Recuperando Informações **********");

        if (this.arquivoRepositorioJson.length() > 0) {
            this.centralInformacoes = objectMapper.readValue(this.arquivoRepositorioJson, CentralInformacoesController.class);
        } else {
            this.centralInformacoes = new CentralInformacoesController();
        }
        return this.centralInformacoes;
    }
}
