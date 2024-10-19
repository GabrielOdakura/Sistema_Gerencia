package controler;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import model.ConectorDB;
import model.identificadores.Funcionario;
import model.identificadores.Login;
import model.identificadores.Pedido;
import model.identificadores.Produto;
import view.ModoGrafico.TelaLogin;
import view.ModoGrafico.TelaPrograma;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ControleModoGrafico {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Login> usuarios = new ArrayList<>();
    private JFrame frame;
    private TelaLogin telaLogin;
    private TelaPrograma telaPrograma;
    private ConectorDB conector = new ConectorDB();

    public ControleModoGrafico(){
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("TabbedPane.background", new Color(74, 143, 211));
            UIManager.put("Panel.background", new Color(124, 173, 223));

        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Sistema de Gerenciamento de Inventário");
                TelaLogin telaLoginInstancia = new TelaLogin();
                TelaPrograma telaProgramaInstancia = new TelaPrograma();
                frame.setContentPane(telaLoginInstancia.renderizarPainelLogin(frame));
                telaLoginInstancia.configurarAcoes(frame, telaLoginInstancia, telaProgramaInstancia);
                telaProgramaInstancia.renderizarPainelPrograma(frame, telaLoginInstancia, telaProgramaInstancia);

                telaLogin = telaLoginInstancia;
                telaPrograma = telaProgramaInstancia;

                frame.setSize(700, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                setarBotoes();
                atualizarDB();
            }
        });
        

    }

    private void setarBotoes() {
        JButton buttonEntrar = telaLogin.getButtonEntrar();
        JButton buttonSair = telaLogin.getButtonSair();

        buttonEntrar.addActionListener(e -> {
            JTextField textUsuario = telaLogin.getTextUsuario();
            JPasswordField textSenha = telaLogin.getTextSenha();
            String usuario = textUsuario.getText().trim();
            String senha = new String(textSenha.getPassword()).trim();
            boolean naoEncontrado = true;

            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                for(Login login : usuarios) {
                    if (login.verificarLogin(usuario, senha)) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        naoEncontrado = false;
                        frame.getContentPane().removeAll();
                        telaPrograma.renderizarPainelPrograma(frame, telaLogin, telaPrograma);
                        telaPrograma.gambiarra(frame);
                        setarBotoes2();
                    }
                }
                if(naoEncontrado) JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonSair.addActionListener(e -> System.exit(0));



    }

    private void setarBotoes2(){
        JButton buttonVoltar = telaPrograma.getButtonVoltar();
        JButton buttonSair = telaPrograma.getButtonSair();
        JButton buttonCadastrarFuncionario = telaPrograma.getButtonCadastrarFuncionario();
        JButton buttonAlterarFuncionario = telaPrograma.getButtonAlterarFuncionario();
        JButton buttonDesligarFuncionario = telaPrograma.getButtonDesligarFuncionario();
        JButton buttonAdicionarProduto = telaPrograma.getButtonAdicionarProduto();
        JButton buttonAlterarEstoque = telaPrograma.getButtonAlterarEstoque();
        JButton buttonAlterarPreco = telaPrograma.getButtonAlterarPreco();
        JButton buttonVerificarPedido = telaPrograma.getButtonVerificarPedido();
        JButton buttonRealizarPedido = telaPrograma.getButtonRealizarPedido();
        JButton buttonEncerrarPedido = telaPrograma.getButtonEncerrarPedido();
        JButton buttonAlterarPedidos = telaPrograma.getButtonAlterarPedidos();

        buttonVoltar.addActionListener(e -> {
            // Mensagem de feedback ao usuário
            JOptionPane.showMessageDialog(frame, "Voltando ao menu principal...");

            // Remove todos os componentes da tela atual
            frame.getContentPane().removeAll();

            // Renderiza a tela de login novamente
//            JPanel painelLogin = login.renderizarPainelLogin(frame);
//            frame.setContentPane(painelLogin);
//            login.configurarAcoes(frame, login, telaPrograma); // Configura as ações da tela de login

            // Ajusta o tamanho, a posição e o comportamento da janela
            frame.setSize(700, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Revalida e repinta o frame para garantir que a nova tela seja exibida corretamente
            frame.revalidate();
            frame.repaint();
        });

        // Listener para o botão "Sair"
        buttonSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Tem certeza de que deseja sair?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose(); // Fecha a janela atual
                System.exit(0);  // Fecha o programa
            }
        });

        buttonCadastrarFuncionario.addActionListener(e -> {
            // Exibe diálogos para capturar as informações do funcionário
            String nomeFuncionario = JOptionPane.showInputDialog(frame, "Digite o nome do funcionário:");
            String cargoFuncionario = JOptionPane.showInputDialog(frame, "Digite o cargo do funcionário:");
            String idFuncionario = JOptionPane.showInputDialog(frame, "Digite o ID do funcionário:");

            // Verifica se todos os campos foram preenchidos corretamente
            if (nomeFuncionario != null && cargoFuncionario != null && idFuncionario != null &&
                    !nomeFuncionario.trim().isEmpty() && !cargoFuncionario.trim().isEmpty() && !idFuncionario.trim().isEmpty()) {

                // Cria um novo objeto Funcionario e cadastra as informações
                Funcionario funcionario = new Funcionario();
                funcionario.cadastrarFuncionario(nomeFuncionario, cargoFuncionario, "DataAtual", idFuncionario);
                funcionarios.add(funcionario);

                // Exibe mensagem de sucesso
                JOptionPane.showMessageDialog(frame, "Funcionário cadastrado com sucesso!");
            } else {
                // Exibe mensagem de erro caso algum campo não esteja preenchido
                JOptionPane.showMessageDialog(frame, "Dados inválidos. Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarFuncionario.addActionListener(e -> {
            String idFuncionario = JOptionPane.showInputDialog(frame, "Digite o ID do funcionário a ser alterado:");

            if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {

                for(Funcionario funcionario : funcionarios){
                    if(funcionario.getIdFuncionario().equals(idFuncionario)){
                        // Solicita novos dados para alteração
                        String novoCargo = JOptionPane.showInputDialog(frame, "Digite o novo cargo do funcionário:");

                        if (novoCargo != null && !novoCargo.trim().isEmpty()) {
                            funcionario.setCargo(novoCargo);
                            funcionario.alterarFuncionario();

                            JOptionPane.showMessageDialog(frame, "Funcionário alterado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Dados inválidos. Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "ID do funcionário não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonDesligarFuncionario.addActionListener(e -> {
            String idFuncionario = JOptionPane.showInputDialog(frame, "Digite o ID do funcionário a ser desligado:");

            if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {
                for(Funcionario funcionario : funcionarios) {
                    if(funcionario.getIdFuncionario().equals(idFuncionario)){
                        int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente desligar o funcionário ID: "
                                + idFuncionario + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            funcionario.desligarFuncionario();
                            JOptionPane.showMessageDialog(frame, "Funcionário desligado com sucesso!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "ID do funcionário não pode ser vazio.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAdicionarProduto.addActionListener(e -> {
            String nomeProduto = JOptionPane.showInputDialog(frame, "Digite o nome do produto:");
            String idProduto = JOptionPane.showInputDialog(frame, "Digite o ID do produto:");
            String precoProdutoStr = JOptionPane.showInputDialog(frame, "Digite o preço do produto:");
            String categoriaProduto = JOptionPane.showInputDialog(frame, "Digite a categoria do produto:");
            String descricaoProduto = JOptionPane.showInputDialog(frame, "Digite a descrição do produto:");
            String descontoProdutoStr = JOptionPane.showInputDialog(frame, "Digite o desconto do produto:");
            // Validação e conversão do preço
            try {
                double precoProduto = Double.parseDouble(precoProdutoStr);
                double descontoProduto = Double.parseDouble(descontoProdutoStr);
                if (!nomeProduto.trim().isEmpty() && !idProduto.trim().isEmpty() && precoProduto >= 0
                        && !categoriaProduto.trim().isEmpty() && !descricaoProduto.trim().isEmpty()) {

                    // Cria e adiciona o produto
                    Produto produto = new Produto();
                    produto.adicionarProduto(nomeProduto, idProduto, precoProduto, categoriaProduto, descricaoProduto,descontoProduto);
                    produtos.add(produto);

                    JOptionPane.showMessageDialog(frame, "Produto adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarEstoque.addActionListener(e -> {
            String idProduto = JOptionPane.showInputDialog(frame, "Digite o ID do produto para alterar o estoque:");
            String quantidadeStr = JOptionPane.showInputDialog(frame, "Digite a quantidade a ser removida do estoque:");

            try {
                int quantidade = Integer.parseInt(quantidadeStr);

                for(Produto produto : produtos){
                    if(idProduto.equals(produto.getId())){
                        produto.alterarEstoque(quantidade);
                        JOptionPane.showMessageDialog(frame, "Estoque alterado com sucesso!");
                    }
                }
                if (quantidade >= 0 && !idProduto.trim().isEmpty()) {
                    // Aqui você pode buscar o produto pelo ID e alterar o estoque
                    Produto produto = new Produto(); // Exemplo de instanciamento
                    produto.alterarEstoque(quantidade);


                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarPreco.addActionListener(e -> {

        });

        buttonVerificarPedido.addActionListener(e -> {
            String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja verificar:");
            boolean resultadoDisponibilidade = false, naoEncontrado = true;

            if (nomeProduto != null && !nomeProduto.trim().isEmpty()) {
                for(Pedido pedido : pedidos){
                    if(nomeProduto.equals(pedido.getNomePedido())){
                        resultadoDisponibilidade = pedido.verificarDisponibilidade();
                        naoEncontrado = false;
                    }
                }

                if(naoEncontrado){
                    JOptionPane.showMessageDialog(null, "Pedido Não Encontrado",
                            "Disponibilidade", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    // Exibir o resultado da verificação
                    JOptionPane.showMessageDialog(null, resultadoDisponibilidade,
                            "Disponibilidade", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum nome de produto foi informado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonRealizarPedido.addActionListener(e -> {
            String nomePedido = JOptionPane.showInputDialog("Digite o nome do pedido:");
            String idPedido = JOptionPane.showInputDialog("Digite o ID do pedido:");
            String dataPedido = JOptionPane.showInputDialog("Digite a data do pedido:");
            String dataEntrega = JOptionPane.showInputDialog("Digite a data de entrega:");
            String descricao = JOptionPane.showInputDialog("Digite a descrição do pedido:");

            if (nomePedido != null && idPedido != null && dataPedido != null && dataEntrega != null && descricao != null &&
                    !nomePedido.trim().isEmpty() && !idPedido.trim().isEmpty() && !dataPedido.trim().isEmpty() &&
                    !dataEntrega.trim().isEmpty() && !descricao.trim().isEmpty()) {
                Pedido novoPedido = new Pedido();
                novoPedido.criarPedido(nomePedido, idPedido, dataPedido, dataEntrega, "produtos", descricao);
                pedidos.add(novoPedido);
                JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonEncerrarPedido.addActionListener(e ->{
            String IdPedido = JOptionPane.showInputDialog("Digite o id do pedido a ser encerrado:");
            if (IdPedido != null && !IdPedido.trim().isEmpty()) {
                boolean naoEncontrado = true;
                for(Pedido pedido : pedidos){
                    if(pedido.getIdPedido().equals(IdPedido)){
                        JOptionPane.showMessageDialog(null, "Pedido encerrado com sucesso!",
                                "Encerrar Pedido", JOptionPane.INFORMATION_MESSAGE);
                        naoEncontrado = false;
                    }
                }
                if(naoEncontrado) {
                    JOptionPane.showMessageDialog(null, "Pedido não encontrado!",
                            "Encerrar Pedido", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarPedidos.addActionListener(e -> {
            String IdPedido = JOptionPane.showInputDialog("Digite o id do pedido a ser alterado:");
            String novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição do pedido:");

            if (IdPedido != null && novaDescricao != null && !IdPedido.trim().isEmpty()
                    && !novaDescricao.trim().isEmpty()) {
                for(Pedido pedido : pedidos){
                    if(pedido.getIdPedido().equals(IdPedido)){
                        pedido.setDescricao(novaDescricao);
                        pedido.alterarPedido();
                    }
                }

                JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!",
                        "Alterar Pedido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void atualizarDB(){
        conector.getConnection();
        funcionarios = conector.SelectFuncionarios();
        pedidos = conector.SelectPedidos();
        produtos = conector.SelectProdutos();
        usuarios = conector.SelectLogin();
    }

}
