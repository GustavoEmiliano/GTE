package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import gte.br.gte3.Model.Disciplina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import gte.br.gte3.Util.HibernateUtil;

public class AdicionarDisControler {

    @FXML
    private TextField nome;

    @FXML
    private Button voltar;

    @FXML
    void clickVoltar(ActionEvent event) {
      HelloApplication.mudaeTela22("categorizacao");
    }

    @FXML
    void clickSalvardIS(ActionEvent event) {
        // Obtenha o nome da disciplina do TextField
        String nomeDisciplina = nome.getText();

        // Verifique se o nome não está vazio
        if (nomeDisciplina.isEmpty()) {
            // Trate o caso em que o nome está vazio (você pode exibir uma mensagem de erro)
            System.out.println("O nome da disciplina não pode ser vazio.");
            return;
        }

        // Crie uma instância da Disciplina com o nome fornecido
        Disciplina novaDisciplina = new Disciplina(nomeDisciplina);

        // Agora você pode salvar a nova disciplina usando o Hibernate
        salvarDisciplina(novaDisciplina);

        // Limpe o TextField após salvar a disciplina (se desejar)
        nome.clear();

        HelloApplication.mudaeTela14("categorizacao");
    }

    // Este método usa o Hibernate para salvar a disciplina no banco de dados
    private void salvarDisciplina(Disciplina disciplina) {
        // Obtém a SessionFactory do HibernateUtil
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Obtém uma nova sessão do Hibernate
        try (Session session = sessionFactory.openSession()) {
            // Inicia uma transação
            session.beginTransaction();

            // Salva a disciplina no banco de dados
            session.save(disciplina);

            // Comita a transação
            session.getTransaction().commit();
        } catch (Exception e) {
            // Se ocorrer um erro, faça rollback da transação
            e.printStackTrace();
        }
    }
}
