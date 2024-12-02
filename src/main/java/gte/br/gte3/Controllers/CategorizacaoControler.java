package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import gte.br.gte3.Model.Categoria;
import gte.br.gte3.Model.Disciplina;
import gte.br.gte3.Model.Tarefa;
import gte.br.gte3.Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CategorizacaoControler {

    @FXML
    private TableView<Disciplina> TableDisci;

    @FXML
    private TableColumn<Disciplina, String> nomeDis;

    @FXML
    private TableView<Categoria> Tablecat;

    @FXML
    private TableColumn<Categoria, String> nomeCat;

    @FXML
    private Button atualizar;

    @FXML
    void initialize() {
        carregarDisciplinasETabelas();
        carregarCategoriasETabelas();
    }

    @FXML
    void clickAtualizar(ActionEvent event) {
        carregarDisciplinasETabelas();
        carregarCategoriasETabelas();
    }


    @FXML
    void ClickAdicionarCategoria(ActionEvent event) {
        HelloApplication.mudaeTela9("adicionarcat");
        carregarCategoriasETabelas();
    }




    private void excluirEntidade(Object entidade, String mensagemErro, boolean emUso) {
        if (emUso) {
            exibirAlertaErro(mensagemErro);
            return;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.remove(entidade);
                transaction.commit();
                refreshTableDisciplinas();
                carregarCategoriasETabelas();
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
                exibirAlertaErro("Erro ao excluir a entidade.");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao acessar o banco de dados.");
        }
    }



    private boolean isCategoriaEmUso(Categoria categoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Inicialize a coleção tarefas dentro da sessão
            categoria = session.load(Categoria.class, categoria.getId());

            List<Tarefa> tarefas = categoria.getTarefas();
            return tarefas != null && !tarefas.isEmpty();
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao verificar se a categoria está em uso.");
            return false;  // Ou ajuste conforme necessário
        }
    }

    private boolean isDisciplinaEmUso(Disciplina disciplina) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Inicialize a coleção tarefas dentro da sessão
            disciplina = session.load(Disciplina.class, disciplina.getId());

            List<Tarefa> tarefas = disciplina.getTarefas();
            return tarefas != null && !tarefas.isEmpty();
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao verificar se a categoria está em uso.");
            return false;  // Ou ajuste conforme necessário
        }
    }


    @FXML
    void clickAdicionarDisciplina(ActionEvent event) {
        HelloApplication.mudaeTela12("adicionardis");
        carregarCategoriasETabelas();
    }

    @FXML
    void clickEditarCategoria(ActionEvent event) {
      HelloApplication.mudaeTela17("editarcat");
    }

    @FXML
    void clickEditarDisciplina(ActionEvent event) {
     HelloApplication.mudaeTela16("editardis");
    }
    private void refreshTableDisciplinas() {
        TableDisci.getColumns().get(0).setVisible(false);
        TableDisci.getColumns().get(0).setVisible(true);
    }
    @FXML
    void ClickExcluirCategoria(ActionEvent event) {
        Categoria categoriaSelecionada = Tablecat.getSelectionModel().getSelectedItem();
        if (categoriaSelecionada != null) {
            boolean emUso = isCategoriaEmUso(categoriaSelecionada);
            String mensagemErro = emUso ?
                    "Erro: Não é possível excluir uma categoria associada a tarefas." :
                    "Erro ao excluir a entidade.";
            excluirEntidade(categoriaSelecionada, mensagemErro, emUso);
        } else {
            exibirAlertaSelecao();
        }
        carregarDisciplinasETabelas();
        carregarCategoriasETabelas();
    }

    @FXML
    void clickExcluirDisciplina(ActionEvent event) {
        Disciplina disciplinaSelecionada = TableDisci.getSelectionModel().getSelectedItem();
        if (disciplinaSelecionada != null) {
            boolean emUso = isDisciplinaEmUso(disciplinaSelecionada);
            String mensagemErro = emUso ?
                    "Erro: Não é possível excluir uma disciplina associada a tarefas." :
                    "Erro ao excluir a entidade.";
            excluirEntidade(disciplinaSelecionada, mensagemErro, emUso);
        } else {
            exibirAlertaSelecao();
        }
        carregarDisciplinasETabelas();
        carregarCategoriasETabelas();
    }



    private void exibirAlertaSelecao() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText("Selecione uma linha para excluir.");
        alert.showAndWait();
    }

    private ObservableList<Disciplina> carregarDisciplinas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Disciplina> query = session.createQuery("FROM Disciplina", Disciplina.class);
            List<Disciplina> disciplinas = query.list();
            return FXCollections.observableArrayList(disciplinas);
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao carregar disciplinas.");
            return FXCollections.observableArrayList(); // Retorna uma lista vazia em caso de erro
        }
    }

    @FXML
    void clickVoltar(ActionEvent event) {
   HelloApplication.mudaeTela15("lista");
    }

    private void carregarDisciplinasETabelas() {
        ObservableList<Disciplina> observableList = carregarDisciplinas();
        TableDisci.setItems(observableList);
        nomeDis.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }


    private void carregarCategoriasETabelas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Categoria> query = session.createQuery("FROM Categoria", Categoria.class);
            List<Categoria> categorias = query.list();
            ObservableList<Categoria> observableList = FXCollections.observableArrayList(categorias);
            Tablecat.setItems(observableList);
            nomeCat.setCellValueFactory(new PropertyValueFactory<>("nome")); // Substitua "nome" pelo atributo correto da sua classe Categoria
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao carregar categorias.");
        }
    }



    private void exibirAlertaErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}