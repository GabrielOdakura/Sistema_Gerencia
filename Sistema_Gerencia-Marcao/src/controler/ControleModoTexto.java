package controler;

import model.identificadores.Login;
import view.ModoTexto;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class ControleModoTexto {

    private LinkedList<Login> usuarios = new LinkedList<>();
    private Scanner input = new Scanner(System.in);

    public void start() {
        boolean sair = false;
        int opcaoEscolhida = -1;
        boolean loginEfetuado = false;
        while(!sair){
            try {
                if (usuarios.isEmpty()) { //Caso a lista de usuários for vazia ela entra nessa branch
                    ModoTexto.imprimirMenuSemUsuarios();
                    opcaoEscolhida = Integer.parseInt(input.nextLine());
                    if(opcaoEscolhida == 1){//Cadastra o usuário
                        ModoTexto.imprimirCadastrarUsuarioNome();
                        String usuario = input.nextLine();
                        ModoTexto.imprimirCadastrarUsuarioSenha();
                        String senha = input.nextLine();
                        usuarios.add(new Login(usuario,senha));
                    }else if(opcaoEscolhida == 2){//Por enquanto só volta pro menu
                        ModoTexto.imprimirVoltandoAoMenu();
                    }else if(opcaoEscolhida == 0){//Sai do programa
                        sair = true;
                    }else throw new InputMismatchException();
                }else {
                    //Pega as informacoes do usuário para o processo de login
                    ModoTexto.imprimirMenuLoginUsuario();
                    String usuario = input.nextLine();
                    ModoTexto.imprimirMenuSenhaUsuario();
                    String senha = input.nextLine();

                    //Verifica pra cada usuário se ele esta cadastrado e se o login foi feito corretamente
                    for (Login login : usuarios) {
                        if (login.verificarLogin(usuario, senha)) {
                            loginEfetuado = true;
                            break;
                        }
                    }

                    if(loginEfetuado){
                        loginRealizado();
                    }else{
                        throw new InputMismatchException();
                    }
                }
            }catch(InputMismatchException | NumberFormatException e){
                ModoTexto.imprimirMensagemErro();
            }
        }
        sairDoPrograma();
    }

    private void loginRealizado() {
        boolean sair = false;
        while(!sair) {
            try {
                ModoTexto.imprimirMenuLoginRealizado();
                int opcaoDesejada = Integer.parseInt(input.nextLine());
                if(opcaoDesejada == 1){
                    ModoTexto.imprimirWIP();
                }else if(opcaoDesejada == 2){
                    ModoTexto.imprimirWIP();
                }else if(opcaoDesejada == 3){
                    ModoTexto.imprimirWIP();
                }else if(opcaoDesejada == 9){
                    ModoTexto.imprimirVoltandoAoMenu();
                    sair = true;
                }else if(opcaoDesejada == 0){
                    System.exit(0);
                }else throw new InputMismatchException();
            }catch(InputMismatchException | NumberFormatException e){
                ModoTexto.imprimirMensagemErro();
            }
        }
    }

    private void sairDoPrograma(){
        input.close();
        System.exit(0);
    }
}
