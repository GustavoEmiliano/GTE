package gte.br.gte3;

import gte.br.gte3.Model.Categoria;
import gte.br.gte3.Model.Tarefa;
import gte.br.gte3.Model.Usuario;
import gte.br.gte3.Util.HibernateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stagePrincipal;
    private  static  Scene cadastroScene;
    private static Scene loginScene;
    private static Scene listaScene;
    private static Scene categorizacaoScene;
    private static Scene adicionarcatScene;
    private static Scene editarcatScene;

    private static Scene adicionardisScene;
    private static Scene editardisScene;

    private static Scene adicionarScene;



    private static Tarefa tarefaAtiva;

    @Override
    public void start(Stage stage) throws IOException {
        stagePrincipal = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cadastro.fxml"));
        Scene sceneCadastro = new Scene(fxmlLoader.load());

        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("lista.fxml"));
        Scene sceneLista = new Scene(fxmlLoader2.load());

        FXMLLoader fxmlLoader3 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene sceneLogin = new Scene(fxmlLoader3.load());

        FXMLLoader fxmlLoader4 = new FXMLLoader(HelloApplication.class.getResource("categorizacao.fxml"));
        Scene sceneCategorizacao = new Scene(fxmlLoader4.load());

        FXMLLoader fxmlLoader5 = new FXMLLoader(HelloApplication.class.getResource("adicionardis.fxml"));
        Scene sceneAdicionarDis = new Scene(fxmlLoader5.load());

        FXMLLoader fxmlLoader6 = new FXMLLoader(HelloApplication.class.getResource("editardis.fxml"));
        Scene sceneEditarDis = new Scene(fxmlLoader6.load());

        FXMLLoader fxmlLoader8 = new FXMLLoader(HelloApplication.class.getResource("adicionarcat.fxml"));
        Scene sceneAdicionarCat = new Scene(fxmlLoader8.load());

        FXMLLoader fxmlLoader9 = new FXMLLoader(HelloApplication.class.getResource("editarcat.fxml"));
        Scene sceneEditarCat = new Scene(fxmlLoader9.load());


        FXMLLoader fxmlLoader11 = new FXMLLoader(HelloApplication.class.getResource("adicionar.fxml"));
        Scene sceneAdicionar = new Scene(fxmlLoader11.load());



        editarcatScene = sceneEditarCat;
        adicionarcatScene = sceneAdicionarCat;

        editardisScene = sceneEditarDis;
        adicionardisScene = sceneAdicionarDis;
        categorizacaoScene = sceneCategorizacao;
        cadastroScene = sceneCadastro;
        loginScene = sceneLogin;
        listaScene = sceneLista;
        adicionarScene = sceneAdicionar;

        stage.setTitle("GERENCIADOR DE TAREFAS");
        stage.setScene(sceneCategorizacao);
        stage.setScene(sceneLogin);
        stage.setScene(sceneCadastro);

        stage.show();
    }

    public static void mudaeTela(String tela) {
        if (tela == "cadastro") {
            stagePrincipal.setScene(cadastroScene);
        }
        if (tela == "login") {
            stagePrincipal.setScene(loginScene);
        }
    }
    public static void mudaeTela(String tela, Tarefa tarefa) {
        tarefaAtiva = tarefa;
        if (tela == "cadastro") {
            stagePrincipal.setScene(cadastroScene);
        }
        if (tela == "login") {
            stagePrincipal.setScene(loginScene);
        }
    }
    public static void mudartela2(String tela) {
        if (tela == "cadastro") {
            stagePrincipal.setScene(cadastroScene);
        }
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
    }
        public static void mudaeTela3(String tela) {
            if (tela == "lista") {
                stagePrincipal.setScene(listaScene);
            }
            if (tela == "categorizacao") {
                stagePrincipal.setScene(categorizacaoScene);
            }
        }
            public static void mudaeTela4(String tela){
                if (tela == "categorizacao") {
                    stagePrincipal.setScene(categorizacaoScene);
                }
                if (tela == "lista"){
                    stagePrincipal.setScene(listaScene);
                }
    }

    public static void mudaeTela5(String tela) {
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
        if (tela == "adicionar") {
            stagePrincipal.setScene(adicionarScene);
        }
    }

    public static void mudaeTela6(String tela) {
        if (tela == "adcionar") {
            stagePrincipal.setScene(adicionarScene);
        }
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
    }

    public static void mudaeTela7(String tela) {
        if (tela == "login") {
            stagePrincipal.setScene(loginScene);
        }
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
    }

    public static void mudaeTela8(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "adicionardis") {
            stagePrincipal.setScene(adicionardisScene);
        }
    }

    public static void mudaeTela9(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "adicionarcat") {
            stagePrincipal.setScene(adicionarcatScene);
        }
    }

    public static void mudaeTela10(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "editarcat") {
            stagePrincipal.setScene(editarcatScene);
        }
    }

    public static void mudaeTela11(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "editardis") {
            stagePrincipal.setScene(editardisScene);
        }
    }


    public static void mudaeTela12(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "adicionardis") {
            stagePrincipal.setScene(adicionardisScene);
        }
    }

    public static void mudaeTela13(String tela) {
        if (tela == "adicionarcat") {
            stagePrincipal.setScene(adicionarcatScene);
        }
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
    }


    public static void mudaeTela14(String tela) {
        if (tela == "adicionarcdis") {
            stagePrincipal.setScene(adicionardisScene);
        }
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
    }

    public static void mudaeTela15(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
    }

    public static void mudaeTela16(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "editardis") {
            stagePrincipal.setScene(editardisScene);
        }
    }

    public static void mudaeTela17(String tela) {
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
        if (tela == "editarcat") {
            stagePrincipal.setScene(editarcatScene);
        }
    }

    public static void mudaeTela18(String tela) {
        if (tela == "editardis") {
            stagePrincipal.setScene(editardisScene);
        }
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
    }

    public static void mudaeTela19(String tela) {
        if (tela == "login") {
            stagePrincipal.setScene(loginScene);
        }
        if (tela == "cadastro") {
            stagePrincipal.setScene(cadastroScene);
        }
    }

    public static void mudaeTela20(String tela) {
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
        if (tela == "cadastro") {
            stagePrincipal.setScene(cadastroScene);
        }
    }

    public static void mudaeTela21(String tela) {
        if (tela == "adicionarcat") {
            stagePrincipal.setScene(adicionarcatScene);
        }
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
    }

    public static void mudaeTela22(String tela) {
        if (tela == "adicionardis") {
            stagePrincipal.setScene(adicionardisScene);
        }
        if (tela == "categorizacao") {
            stagePrincipal.setScene(categorizacaoScene);
        }
    }

    public static void mudaeTela23(String tela) {
        if (tela == "adicionar") {
            stagePrincipal.setScene(adicionarScene);
        }
        if (tela == "lista") {
            stagePrincipal.setScene(listaScene);
        }
    }


    public static void main(String[] args) {
        launch();

    }
}