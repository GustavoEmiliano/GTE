package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import gte.br.gte3.Model.Categoria;
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

public class EditarCatControler {

    @FXML
    private ComboBox<Categoria> cbxcategoria;

    @FXML
    private TextField NovoNome;

    @FXML
    void clickSave(ActionEvent event) {
        Categoria categoriaSelecionada = cbxcategoria.getValue();
        String novoNome = NovoNome.getText();

        if (categoriaSelecionada != null && !novoNome.isEmpty()) {
            atualizarNomeCategoria(categoriaSelecionada, novoNome);
        } else {
            exibirAlertaErro("Campos obrigatórios não preenchidos", "Por favor, selecione uma categoria e digite o novo nome.");
        }
    }

    private void atualizarNomeCategoria(Categoria categoria, String novoNome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Não é necessário carregar novamente a categoria do banco de dados, pois você já tem a instância
            // que foi carregada pelo método carregarCategorias
            categoria.setNome(novoNome);

            try {
                session.update(categoria);
                transaction.commit();
                carregarCategorias();
                HelloApplication.mudaeTela18("categorizacao");
            } catch (HibernateException e) {
                e.printStackTrace();
                transaction.rollback();
                exibirAlertaErro("Erro ao atualizar nome da categoria", "Ocorreu um erro ao atualizar o nome da categoria.");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao acessar o banco de dados", "Ocorreu um erro ao acessar o banco de dados.");
        }
    }

    @FXML
    void initialize() {
        carregarCategorias();
    }

    private void carregarCategorias() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Categoria> query = session.createQuery("FROM Categoria", Categoria.class);
            java.util.List<Categoria> categorias = query.list();
            ObservableList<Categoria> observableList = FXCollections.observableArrayList(categorias);
            cbxcategoria.setItems(observableList);
        } catch (HibernateException e) {
            e.printStackTrace();
            exibirAlertaErro("Erro ao carregar categorias", "Ocorreu um erro ao carregar categorias.");
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
