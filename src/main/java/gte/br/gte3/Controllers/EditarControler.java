package gte.br.gte3.Controllers;

import gte.br.gte3.Controllers.ListaControler;
import gte.br.gte3.Model.Categoria;
import gte.br.gte3.Model.Disciplina;
import gte.br.gte3.Model.Tarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EditarControler {

    @FXML
    private TextField categoria;

    @FXML
    private TextField titulo;

    @FXML
    private TextField descricao;

    @FXML
    private TextField status;

    @FXML
    private TextField dataDeInicio;

    @FXML
    private TextField dataDeVencimento;

    @FXML
    private TextField disciplina;

    @FXML
    private ComboBox<String> cbxTarefa; // Agora a ComboBox contém nomes de tarefas, não objetos Tarefa

    private ListaControler listaControler; // Referência ao controlador da lista

    private Scanner scanner;
//
//    public void setListaControler(ListaControler listaControler) {
//        this.listaControler = listaControler;
//    }
//
//    public void setScanner(Scanner scanner) {
//        this.scanner = scanner;
//    }
//
//    @FXML
//    void initialize() {
//        // Inicialize o ComboBox com os nomes das tarefas da ListaControler
//        listaControler.getTarefas().forEach(tarefa -> cbxTarefa.getItems().add(tarefa.getTitulo()));
//    }
//
//    @FXML
//    void carregarDadosTarefa(ActionEvent event) {
//        System.out.print("Digite o nome da tarefa: ");
//        String nomeTarefaSelecionada = scanner.nextLine();
//
//        // Obtenha a tarefa correspondente pelo nome
//        Tarefa tarefaSelecionada = listaControler.getTarefas()
//                .stream()
//                .filter(tarefa -> tarefa.getTitulo().equals(nomeTarefaSelecionada))
//                .findFirst()
//                .orElse(null);
//
//        if (tarefaSelecionada != null) {
//            // Carregue os dados da tarefa nos campos correspondentes
//            categoria.setText(tarefaSelecionada.getCategoria().getNome());
//            titulo.setText(tarefaSelecionada.getTitulo());
//            descricao.setText(tarefaSelecionada.getDescricao());
//            status.setText(tarefaSelecionada.getStatus());
//            dataDeInicio.setText(tarefaSelecionada.getDataInicio().toString());
//            dataDeVencimento.setText(tarefaSelecionada.getDataVencimento().toString());
//            disciplina.setText(tarefaSelecionada.getDisciplina().getNome());
//        }
//    }
//
//    @FXML
//    void clickSalvar(ActionEvent event) {
//        System.out.print("Digite o nome da tarefa a ser editada: ");
//        String nomeTarefaSelecionada = scanner.nextLine();
//
//        // Obtenha a tarefa correspondente pelo nome
//        Tarefa tarefaSelecionada = listaControler.getTarefas()
//                .stream()
//                .filter(tarefa -> tarefa.getTitulo().equals(nomeTarefaSelecionada))
//                .findFirst()
//                .orElse(null);
//
//        if (tarefaSelecionada != null) {
//            // Atualize os dados da tarefa com base nos campos editados
//            System.out.print("Nova categoria: ");
//            tarefaSelecionada.setCategoria(new Categoria(scanner.nextLine()));
//
//            System.out.print("Novo título: ");
//            tarefaSelecionada.setTitulo(scanner.nextLine());
//
//            System.out.print("Nova descrição: ");
//            tarefaSelecionada.setDescricao(scanner.nextLine());
//
//            System.out.print("Novo status: ");
//            tarefaSelecionada.setStatus(scanner.nextLine());
//
//            System.out.print("Nova data de início (AAAA-MM-DD): ");
//            tarefaSelecionada.setDataInicio(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_DATE));
//
//            System.out.print("Nova data de vencimento (AAAA-MM-DD): ");
//            tarefaSelecionada.setDataVencimento(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_DATE));
//
//            System.out.print("Nova disciplina: ");
//            tarefaSelecionada.setDisciplina(new Disciplina(scanner.nextLine()));
//
//            // Salve as alterações no banco de dados
//            listaControler.getTabelsService().editarTarefa(tarefaSelecionada);
//
//            // Atualize a tabela na ListaControler após a edição
//            listaControler.atualizarTabelaTarefas();
//        }
//    }
}
