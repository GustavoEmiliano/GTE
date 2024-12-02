package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import gte.br.gte3.Model.Disciplina;
import gte.br.gte3.Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EditarDisControler {

    @FXML
    private ComboBox<Disciplina> cbxdisciplina;

    @FXML
    private TextField NovoNome;

    @FXML
    void clickSave(ActionEvent event) {
        Disciplina disciplinaSelecionada = cbxdisciplina.getValue();
        String novoNome = NovoNome.getText();

        if (disciplinaSelecionada != null && !novoNome.isEmpty()) {
            atualizarNomeDisciplina(disciplinaSelecionada, novoNome);
        } else {
            exibirAlertaErro("Campos obrigatórios não preenchidos", "Por favor, selecione uma disciplina e digite o novo nome.");
        }
    }

    private void atualizarNomeDisciplina(Disciplina disciplina, String novoNome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Não é necessário carregar novamente a disciplina do banco de dados, pois você já tem a instância
            // que foi carregada pelo método carregarDisciplinas
            disciplina.setNome(novoNome);

            try {
                session.update(disciplina);
                transaction.commit();
                carregarDisciplinas();
                HelloApplication.mudaeTela18("categorizacao");
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
                exibirAlertaErro("Erro ao atualizar nome da disciplina", "Ocorreu um erro ao atualizar o nome da disciplina.");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao acessar o banco de dados", "Ocorreu um erro ao acessar o banco de dados.");
        }
    }


    @FXML
    void initialize() {
        carregarDisciplinas();
    }

    private void carregarDisciplinas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Disciplina> query = session.createQuery("FROM Disciplina", Disciplina.class);
            List<Disciplina> disciplinas = query.list();
            ObservableList<Disciplina> observableList = FXCollections.observableArrayList(disciplinas);
            cbxdisciplina.setItems(observableList);
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao carregar disciplinas", "Ocorreu um erro ao carregar disciplinas.");
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

