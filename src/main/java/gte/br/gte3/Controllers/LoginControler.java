package gte.br.gte3.Controllers;

import gte.br.gte3.HelloApplication;
import gte.br.gte3.Util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import gte.br.gte3.Model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginControler {

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private TextField nome;

    @FXML
    private TextField sobrenome;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private void cadastrarUsuario(String username, String password, String email, String nome, String sobrenome) {

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Long userId = (Long) session.save(usuario); // Obtém o ID do usuário recém-criado
            usuario.setId(userId);
            session.persist(usuario);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickLogin(ActionEvent event) {
        String usernameText = username.getText();
        String passwordText = password.getText();
        String confirmPasswordText = confirmPassword.getText();
        String emailText = email.getText();
        String nomeText = nome.getText();
        String sobrenomeText = sobrenome.getText();

        if (usernameText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty() || emailText.isEmpty() || nomeText.isEmpty() || sobrenomeText.isEmpty()) {
            mostrarErro("Erro: Preencha todos os campos.");
            return;
        }

        if (!passwordText.equals(confirmPasswordText)) {
            mostrarErro("Erro: As senhas são diferentes.");
            return;
        }

        if (!isValidEmail(emailText)) {
            mostrarErro("Erro: Email inválido.");
            return;
        }

        cadastrarUsuario(usernameText, passwordText, emailText, nomeText, sobrenomeText);
        HelloApplication.mudaeTela19("cadastro");
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}



