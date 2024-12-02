package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import gte.br.gte3.Model.Categoria;
import gte.br.gte3.Model.Disciplina;
import gte.br.gte3.Model.Tarefa;
import gte.br.gte3.Services.TarefaService;
import gte.br.gte3.Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.*;

public class ListaControler implements Initializable {

    @FXML
    private TableColumn<Tarefa, String> colTitulo;

    @FXML
    private TableColumn<Tarefa, Categoria> colCategoria;

    @FXML
    private TableColumn<Tarefa, Date> colDataInicio;

    @FXML
    private TableColumn<Tarefa, Date> colDataVencimento;

    @FXML
    private TableColumn<Tarefa, String> colDescricao;

    @FXML
    private TableColumn<Tarefa, Disciplina> colDisciplina;

    @FXML
    private TableColumn<Tarefa, String> colStatus;

    @FXML
    private Button atualizar;

    @FXML
    private Button logout;

    @FXML
    private TableView<Tarefa> tableview;

    TarefaService tabelsService = new TarefaService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarTarefas();
    }

    @FXML
    void clickLogot(ActionEvent event) {
      HelloApplication.mudaeTela20("cadastro");
    }


    @FXML
    void clickAtualizar(ActionEvent event) {
        atualizarTabelaTarefas();
    }

    public void mostrarTarefas() {
        tableview.setItems(FXCollections.observableArrayList());
        ObservableList<Tarefa> list = getTarefas();
        tableview.setItems(list);

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colDataInicio.setCellValueFactory(new PropertyValueFactory<>("DataInicio"));
        colDataVencimento.setCellValueFactory(new PropertyValueFactory<>("DataVencimento"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
    }

    public ObservableList<Tarefa> getTarefas() {
        ObservableList<Tarefa> consults = FXCollections.observableArrayList();
        consults.addAll(TarefaService.listaTarefa());
        return consults;
    }

    public void atualizarTabelaTarefas() {
        List<Tarefa> listaDeTarefas = carregarTarefasDoBanco();

        if (listaDeTarefas != null) {
            ObservableList<Tarefa> tarefasObservaveis = FXCollections.observableArrayList(listaDeTarefas);
            tableview.setItems(tarefasObservaveis);
        } else {
            // A lista de tarefas é null, trate conforme necessário
            System.out.println("A lista de tarefas é null");
            // Você pode lançar uma exceção ou realizar outra ação apropriada
        }
    }

    private List<Tarefa> carregarTarefasDoBanco() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Tarefa> query = session.createQuery("FROM Tarefa", Tarefa.class);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @FXML
    void clickCategorizacao(ActionEvent event) {
        HelloApplication.mudaeTela3("categorizacao");
    }

    @FXML
    void clickEditar(ActionEvent event) {
        // Lógica para editar tarefa
    }

    @FXML
    void clickExcluir(ActionEvent event) {
        Tarefa tarefaSelecionada = tableview.getSelectionModel().getSelectedItem();

        if (tarefaSelecionada != null) {
            // Cria um diálogo de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja excluir a tarefa?");
            alert.setContentText("Esta ação não pode ser desfeita.");

            // Adiciona botões de sim e não
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Obtém a resposta do usuário
            Optional<ButtonType> result = alert.showAndWait();

            // Se o usuário clicou em "Sim", exclui a tarefa
            if (result.isPresent() && result.get() == ButtonType.YES) {
                excluirTarefa(tarefaSelecionada);
            }
        } else {
            // Se nenhuma tarefa estiver selecionada, exibe um aviso
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhuma tarefa selecionada");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma tarefa para excluir.");
            alert.showAndWait();
        }
    }

    private void excluirTarefa(Tarefa tarefa) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(tarefa);
            session.getTransaction().commit();
            atualizarTabelaTarefas();
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao excluir tarefa", "Ocorreu um erro ao excluir a tarefa.");
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }


    @FXML
    void getData(MouseEvent event) {
        // Lógica para obter dados ao clicar na tabela
    }

    @FXML
    void clickadd(ActionEvent event) {
        HelloApplication.mudaeTela5("adicionar");
        atualizarTabelaTarefas();
    }
}
