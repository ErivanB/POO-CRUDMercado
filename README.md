Sistema de Gerenciamento de Supermercado (Versão 1.0)
Este projeto é um sistema de gerenciamento de supermercado desktop, desenvolvido em Java com a biblioteca Swing para a interface gráfica e persistência de dados em formato JSON.

Funcionalidades
O sistema oferece um conjunto de funcionalidades para gerenciar o estoque e as vendas de um supermercado de forma simples e intuitiva:

Cadastro de Produtos: Adicione, edite e remova produtos do inventário.

Controle de Estoque: Visualize o estoque atual de cada produto.

Gestão de Vendas: Registre vendas, dando baixa nos produtos do estoque.

Relatório de Vendas: Gere relatórios de vendas em formato PDF.

Tecnologias Utilizadas
Linguagem de Programação: Java

Interface Gráfica: Java Swing

Persistência de Dados: JSON (com a biblioteca Gson)

Geração de Relatórios: PDF (com a biblioteca iText ou Apache PDFBox)

Como Rodar o Projeto
Para executar o projeto, siga os passos abaixo:

Pré-requisitos: Certifique-se de ter o Java Development Kit (JDK) 8 ou superior instalado em sua máquina.

Clonar o Repositório:

Bash

git clone https://github.com/ErivanB/POO-CRUDMercado.git

Importar no IDE:

Abra sua IDE favorita (Eclipse, IntelliJ IDEA, NetBeans).

Importe o projeto como um projeto Maven ou Gradle (se houver, caso contrário, importe como um projeto Java simples).

Configurar Dependências:

Adicione as bibliotecas Gson e iText (ou a biblioteca de PDF que você escolheu) ao seu projeto. Se estiver usando Maven, as dependências já estarão no arquivo pom.xml.

Executar o Projeto:

Localize a classe principal (geralmente Main.java ou App.java) e execute o método main.

Estrutura do Projeto
A estrutura de diretórios do projeto é organizada da seguinte forma:

src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── seuprojeto/
│   │           ├── model/        (Classes de modelo - Produto, Venda)
│   │           ├── view/         (Classes de interface - Telas, Painéis)
│   │           ├── controller/   (Lógica de negócio e manipulação de dados)
│   │           └── data/         (Classes para leitura/escrita de JSON)
│   └── resources/  (Arquivos de recursos, como ícones ou dados iniciais)
└── test/
    └── java/
        └── com/
            └── seuprojeto/
                └── Teste.java
                
pom.xml (ou build.gradle)

Contribuições
Este projeto está em desenvolvimento contínuo. Sugestões e contribuições são bem-vindas! Se você encontrar um bug ou tiver uma ideia para uma nova funcionalidade, sinta-se à vontade para abrir uma issue ou um Pull Request.

Versão Atual
1.0

Autor: ERIVAN BARROS PEREIRA.
      
