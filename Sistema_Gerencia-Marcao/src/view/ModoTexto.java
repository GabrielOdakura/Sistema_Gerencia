package view;

public class ModoTexto {

    //Menu caso não exista nenhum usuário cadastrado para fazer login no sistema
    public static void imprimirMenuSemUsuarios(){
        System.out.println("""
                Nenhum usuário foi identificado!
                Gostaria de cadastrar um?
                1 - Sim
                2 - Não
                0 - Sair
                Digite a opção desejada:\s""");
    }

    //Imprime uma mensagem no console pedindo para o usuário colocar o nome de usuário
    public static void imprimirMenuLoginUsuario(){
        System.out.println("Digite o nome de Usuário para login: ");
    }

    //Imprime uma mensagem no console pedindo para o usuário colocar a senha de usuário
    public static void imprimirMenuSenhaUsuario(){
        System.out.println("Digite a senha do Usuário: ");
    }

    //Caso uma opção seja inválida, imprime uma mensagem de erro no console
    public static void imprimirMensagemErro(){
        System.out.println("Opcao Inválida, tente novamente!\n\n");
    }

    //Mensagem simples caso o usuario queira voltar um menu ao inves de fechar o telaPrograma
    public static void imprimirVoltandoAoMenu() {
        System.out.println("Voltando ao menu anterior!\n");
    }

    //Cas o login seja feito com sucesso esse será o próximo menu que o usuário verá
    public static void imprimirMenuLoginRealizado() {
        System.out.println("""
                
                1 - Gestão de Produtos
                2 - Gestão de Pedidos
                3 - Gestão de usuários
                9 - Sair do Usuário
                0 - Sair do Programa
                Digite a opção desejada: """);
    }

    //Metodo para apagar futuramente, serve para mostrar que a opcao ainda esta sendo programada
    public static void imprimirWIP() {
        System.out.println("WIP");
    }

    //Pede par a o usuário o nome que vai ser utilizado para o login
    public static void imprimirCadastrarUsuarioNome() {
        System.out.println("Digite o nome de Usuário a ser cadastrado: ");
    }

    //Pede par a o usuário a senha que vai ser utilizada para o login
    public static void imprimirCadastrarUsuarioSenha() {
        System.out.println("Digite a senha que será utilizada para o cadastro: ");
    }
}
