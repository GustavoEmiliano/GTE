package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import gte.br.gte3.Model.Categoria;
import gte.br.gte3.Model.Disciplina;
import gte.br.gte3.Model.Tarefa;
import gte.br.gte3.Util.HibernateUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdicionarControler implements Initializable {

    @FXML
    private TextField titulo;

    @FXML
    private TextField descricao;

    @FXML
    private TextField status;

    @FXML
    private DatePicker dataInicioPicker;

    @FXML
    private DatePicker dataVencimentoPicker;

    @FXML
    private ComboBox<Categoria> cbxCategoria;

    @FXML
    private ComboBox<Disciplina> cbxDisciplina;

    @FXML
    void clickVoltar(ActionEvent event) {
     HelloApplication.mudaeTela23("lista");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Teste");
        List<Categoria> categorias = buscarCategoriasNoBancoDeDados();
        System.out.println("Categorias encontradas: " + categorias.size());
        categorias.forEach(c -> System.out.println("Categoria na ComboBox: " + c));
        cbxCategoria.getItems().addAll(categorias);

        List<Disciplina> disciplinas = buscarDisciplinasNoBancoDeDados();
        System.out.println("Disciplinas encontradas: " + disciplinas.size());
        disciplinas.forEach(d -> System.out.println("Disciplina na ComboBox: " + d));
        cbxDisciplina.getItems().addAll(disciplinas);
    }


    @FXML
    void clickAdicionar(ActionEvent event) {
        String Titulo = titulo.getText();
        String Descricao = descricao.getText();
        String Status = status.getText();

        LocalDate dataInicio = dataInicioPicker.getValue();
        LocalDate dataVencimento = dataVencimentoPicker.getValue();

        Categoria categoriaSelecionada = cbxCategoria.getValue();
        Disciplina disciplinaSelecionada = cbxDisciplina.getValue();

        if (categoriaSelecionada != null && disciplinaSelecionada != null && dataInicio != null && dataVencimento != null) {
            // Verifica se a data de vencimento é anterior à data de início
            if (dataVencimento.isBefore(dataInicio)) {
                exibirAlertaErro("Erro ao adicionar tarefa", "A data de vencimento não pode ser anterior à data de início.");
                return;  // Não adiciona a tarefa e sai do método
            }

            Tarefa t = new Tarefa(Titulo, Descricao, Status, dataInicio, dataVencimento, disciplinaSelecionada, categoriaSelecionada);

            try (Session s = HibernateUtil.getSessionFactory().openSession()) {
                s.beginTransaction();
                s.persist(t);
                s.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
                exibirAlertaErro("Erro ao adicionar tarefa", "Ocorreu um erro ao adicionar a tarefa.");
            }

            limparCampos(); // Limpar os campos após adicionar a tarefa
            limparAlertaErro(); // Limpar o alerta de erro

            HelloApplication.mudartela2("lista");
        } else {
            exibirAlertaErro("Campos obrigatórios não preenchidos", "Por favor, preencha todos os campos obrigatórios.");
        }
    }


    private List<Categoria> buscarCategoriasNoBancoDeDados() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Categoria> query = session.createQuery("FROM Categoria", Categoria.class);
            List<Categoria> categorias = query.list();
            categorias.forEach(c -> System.out.println("Categoria: " + c));
            return categorias;
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    private List<Disciplina> buscarDisciplinasNoBancoDeDados() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Disciplina> query = session.createQuery("FROM Disciplina", Disciplina.class);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }


    private void limparCampos() {
        titulo.clear();
        descricao.clear();
        status.clear();
        dataInicioPicker.setValue(null);
        dataVencimentoPicker.setValue(null);
        cbxCategoria.setValue(null);
        cbxDisciplina.setValue(null);
    }

    private void limparAlertaErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("");
    }

}
