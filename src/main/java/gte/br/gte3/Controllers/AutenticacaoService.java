package gte.br.gte3.Controllers;

public class AutenticacaoService {

    private static String usuarioLogado = null;


    public static boolean fazerLogin(String usuario, String senha) {

        usuarioLogado = usuario;
        return true;
    }

    public static void fazerLogout() {
        usuarioLogado = null;
    }

    // Método para verificar se o usuário está logado
    public static boolean estaLogado() {
        return usuarioLogado != null;
    }

    // Método para obter o nome do usuário logado
    public static String obterUsuarioLogado() {
        return usuarioLogado;
    }
}
