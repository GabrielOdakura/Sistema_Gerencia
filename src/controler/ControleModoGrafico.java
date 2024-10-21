package controler;

import model.identificadores.Funcionario;
import model.identificadores.Login;
import model.identificadores.Pedido;
import model.identificadores.Produto;
import view.ModoGrafico.FramePrincipal;
import view.ModoGrafico.TelaLogin;
import view.ModoGrafico.TelaPrograma;
import model.Modelos;

import javax.swing.*;

public class ControleModoGrafico extends Modelos {
    private TelaLogin telaLogin;
    private TelaPrograma telaPrograma;
    private Modelos model;
    private FramePrincipal frame;

    public ControleModoGrafico(){
        model = new Modelos();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new FramePrincipal();

                telaLogin = frame.getPainelLogin();
                telaPrograma = frame.getPainelPrograma();

                setarBotoesPainelLogin();
                setarBotoesPainelPrograma();
                criarTabelas();
            }
        });
        

    }



    private void setarBotoesPainelLogin() {
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
                for(Login login : model.getUsuarios()) {
                    if (login.verificarLogin(usuario, senha)) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        naoEncontrado = false;
                        if(!frame.getPainelLogin().getStatusCheckBoxLembrar()) frame.getPainelLogin().limparCaixasLogin();
                        frame.getPainelPrincipal().setLayer(frame.getPainelLogin(), JLayeredPane.DEFAULT_LAYER);
                        frame.getPainelPrincipal().setLayer(frame.getPainelPrograma(), JLayeredPane.PALETTE_LAYER);
                        frame.getPainelPrincipal().revalidate();
                        frame.getPainelPrincipal().repaint();
                    }
                }
                if(naoEncontrado) JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonSair.addActionListener(e -> System.exit(0));



    }

    private void setarBotoesPainelPrograma(){
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

        buttonVoltar.addActionListener(e -> {//mexer aqui -------------------------------
            // Mensagem de feedback ao usuário
            JOptionPane.showMessageDialog(null, "Voltando ao menu principal...");
            frame.getPainelPrincipal().setLayer(frame.getPainelPrograma(), JLayeredPane.DEFAULT_LAYER);
            frame.getPainelPrincipal().setLayer(frame.getPainelLogin(), JLayeredPane.PALETTE_LAYER);
            frame.getPainelPrincipal().revalidate();
            frame.getPainelPrincipal().repaint();
        });

        // Listener para o botão "Sair"
        buttonSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja sair?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);  // Fecha o programa
            }
        });

        buttonCadastrarFuncionario.addActionListener(e -> {
            // Exibe diálogos para capturar as informações do funcionário
            String nomeFuncionario = JOptionPane.showInputDialog(null, "Digite o nome do funcionário:");
            String cargoFuncionario = JOptionPane.showInputDialog(null, "Digite o cargo do funcionário:");
            String idFuncionario = JOptionPane.showInputDialog(null, "Digite o ID do funcionário:");

            // Verifica se todos os campos foram preenchidos corretamente
            if (nomeFuncionario != null && cargoFuncionario != null && idFuncionario != null &&
                    !nomeFuncionario.trim().isEmpty() && !cargoFuncionario.trim().isEmpty() && !idFuncionario.trim().isEmpty()) {

                // Cria um novo objeto Funcionario e cadastra as informações
                Funcionario funcionario = new Funcionario();
                funcionario.cadastrarFuncionario(nomeFuncionario, cargoFuncionario, "DataAtual", idFuncionario);
                model.addFuncionario(funcionario);

                // Exibe mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
            } else {
                // Exibe mensagem de erro caso algum campo não esteja preenchido
                JOptionPane.showMessageDialog(null, "Dados inválidos. Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarFuncionario.addActionListener(e -> {
            String idFuncionario = JOptionPane.showInputDialog(null, "Digite o ID do funcionário a ser alterado:");

            if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {

                for(Funcionario funcionario : model.getFuncionarios()){
                    if(funcionario.getIdFuncionario().equals(idFuncionario)){
                        // Solicita novos dados para alteração
                        String novoCargo = JOptionPane.showInputDialog(null, "Digite o novo cargo do funcionário:");

                        if (novoCargo != null && !novoCargo.trim().isEmpty()) {
                            funcionario.setCargo(novoCargo);
                            funcionario.alterarFuncionario();

                            JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Dados inválidos. Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "ID do funcionário não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonDesligarFuncionario.addActionListener(e -> {
            String idFuncionario = JOptionPane.showInputDialog(null, "Digite o ID do funcionário a ser desligado:");

            if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {
                for(Funcionario funcionario : model.getFuncionarios()) {
                    if(funcionario.getIdFuncionario().equals(idFuncionario)){
                        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente desligar o funcionário ID: "
                                + idFuncionario + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            funcionario.desligarFuncionario();
                            JOptionPane.showMessageDialog(null, "Funcionário desligado com sucesso!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "ID do funcionário não pode ser vazio.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAdicionarProduto.addActionListener(e -> {
            String nomeProduto = JOptionPane.showInputDialog(null, "Digite o nome do produto:");
            String idProduto = JOptionPane.showInputDialog(null, "Digite o ID do produto:");
            String precoProdutoStr = JOptionPane.showInputDialog(null, "Digite o preço do produto:");
            String categoriaProduto = JOptionPane.showInputDialog(null, "Digite a categoria do produto:");
            String descricaoProduto = JOptionPane.showInputDialog(null, "Digite a descrição do produto:");

            // Validação e conversão do preço
            try {
                double precoProduto = Double.parseDouble(precoProdutoStr);

                if (!nomeProduto.trim().isEmpty() && !idProduto.trim().isEmpty() && precoProduto >= 0
                        && !categoriaProduto.trim().isEmpty() && !descricaoProduto.trim().isEmpty()) {

                    // Cria e adiciona o produto
                    Produto produto = new Produto();
                    produto.adicionarProduto(nomeProduto, idProduto, precoProduto, categoriaProduto, descricaoProduto, 0);
                    model.addProduto(produto);

                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarEstoque.addActionListener(e -> {
            String idProduto = JOptionPane.showInputDialog(null, "Digite o ID do produto para alterar o estoque:");
            String quantidadeStr = JOptionPane.showInputDialog(null, "Digite a quantidade a ser removida do estoque:");

            try {
                int quantidade = Integer.parseInt(quantidadeStr);

                for(Produto produto : model.getProdutos()){
                    if(idProduto.equals(produto.getId())){
                        produto.alterarEstoque(quantidade);
                        JOptionPane.showMessageDialog(null, "Estoque alterado com sucesso!");
                    }
                }
                if (quantidade >= 0 && !idProduto.trim().isEmpty()) {
                    // Aqui você pode buscar o produto pelo ID e alterar o estoque
                    Produto produto = new Produto(); // Exemplo de instanciamento
                    produto.alterarEstoque(quantidade);


                } else {
                    JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonAlterarPreco.addActionListener(e -> {

        });

        buttonVerificarPedido.addActionListener(e -> {
            String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja verificar:");
            boolean resultadoDisponibilidade = false, naoEncontrado = true;

            if (nomeProduto != null && !nomeProduto.trim().isEmpty()) {
                for(Pedido pedido : model.getPedidos()){
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
                model.addPedido(novoPedido);
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
                for(Pedido pedido : model.getPedidos()){
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
                for(Pedido pedido : model.getPedidos()){
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

    private void criarTabelas() {
        frame.TabelaFuncionario(model.pegarTabelaFuncionarios());
        frame.TabelaEmpresa(model.pegarTabelaEmpresas());
        frame.TabelaEstoque(model.pegarTabelaEstoque());
        frame.TabelaLogin(model.pegarTabelaLogins());
        frame.TabelaPedido(model.pegarTabelaPedidos());
        frame.TabelaProduto(model.pegarTabelaProdutos());
    }
}
