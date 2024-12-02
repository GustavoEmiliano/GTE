package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import gte.br.gte3.Model.Categoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import gte.br.gte3.Util.HibernateUtil;

public class AdicionarCatControler {

    @FXML
    private TextField nome;
    @FXML
    private Button voltar;

    @FXML
    void clickVoltar(ActionEvent event) {
   HelloApplication.mudaeTela21("categorizacao");
    }

    @FXML
    void clickSalvarCat(ActionEvent event) {
        // Obtenha o nome da categoria do TextField
        String nomeCategoria = nome.getText();

        // Verifique se o nome não está vazio
        if (nomeCategoria.isEmpty()) {
            // Trate o caso em que o nome está vazio (você pode exibir uma mensagem de erro)
            System.out.println("O nome da categoria não pode ser vazio.");
            return;
        }

        // Crie uma instância da Categoria com o nome fornecido
        Categoria novaCategoria = new Categoria(nomeCategoria);

        // Agora você pode salvar a nova categoria usando o Hibernate
        salvarCategoria(novaCategoria);

        // Limpe o TextField após salvar a categoria (se desejar)
        nome.clear();

        HelloApplication.mudaeTela13("categorizacao");
    }

    // Este método usa o Hibernate para salvar a categoria no banco de dados
    private void salvarCategoria(Categoria categoria) {
        // Obtém a SessionFactory do HibernateUtil
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Obtém uma nova sessão do Hibernate
        try (Session session = sessionFactory.openSession()) {
            // Inicia uma transação
            session.beginTransaction();

            // Salva a categoria no banco de dados
            session.save(categoria);

            // Comita a transação
            session.getTransaction().commit();
        } catch (Exception e) {
            // Se ocorrer um erro, faça rollback da transação
            e.printStackTrace();
        }
    }
}
