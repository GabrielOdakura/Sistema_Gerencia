package view.ModoGrafico;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.identificadores.Login;


    public class TelaLogin extends JPanel {
        private JTextField textUsuario;
        private JPasswordField textSenha;
        private JButton buttonEntrar, buttonSair;
        private JCheckBox checkBoxLembrar;
        private JLabel linkEsqueceuSenha;
        private TelaLogin telaLogin;
        private TelaPrograma telaPrograma;

        public TelaLogin(){
            renderizarPainelLoginTeste();
        }

        public void renderizarPainelLoginTeste(){
            this.setLayout(new BorderLayout());

            JLabel logo = new JLabel(new ImageIcon("logoEmpresa.png"), SwingConstants.CENTER);
            logo.setForeground(Color.WHITE);
            logo.setBorder(new EmptyBorder(0, 0, 50, 0));

            JLabel nomeCompania = new JLabel("Sistema de Gerenciamento de Inventário", SwingConstants.CENTER);
            nomeCompania.setFont(new Font("Arial", Font.BOLD, 18));
            nomeCompania.setForeground(Color.BLACK);

            JPanel panel = new GradientPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.fill = GridBagConstraints.HORIZONTAL;

            adicionarComponentesLogin(panel, constraints, logo, nomeCompania);

            this.add(panel, BorderLayout.CENTER);

        }

        public JPanel renderizarPainelLogin(){
            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());


            JLabel logo = new JLabel(new ImageIcon("logoEmpresa.png"), SwingConstants.CENTER);
            logo.setForeground(Color.WHITE);
            logo.setBorder(new EmptyBorder(0, 0, 50, 0));

            JLabel nomeCompania = new JLabel("Sistema de Gerenciamento de Inventário", SwingConstants.CENTER);
            nomeCompania.setFont(new Font("Arial", Font.BOLD, 18));
            nomeCompania.setForeground(Color.BLACK);

            JPanel panel = new GradientPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.fill = GridBagConstraints.HORIZONTAL;

            adicionarComponentesLogin(panel, constraints, logo, nomeCompania);

            painel.add(panel, BorderLayout.CENTER);

            return painel;
        }

        public JPanel renderizarPainelLogin(JFrame frame) {

            JPanel painel = new JPanel();
            painel.setLayout(new BorderLayout());

            JMenuBar menuBar = criarMenuBar();
            frame.setJMenuBar(menuBar);

            JLabel logo = new JLabel(new ImageIcon("logoEmpresa.png"), SwingConstants.CENTER);
            logo.setForeground(Color.WHITE);
            logo.setBorder(new EmptyBorder(0, 0, 50, 0));

            JLabel nomeCompania = new JLabel("Sistema de Gerenciamento de Inventário", SwingConstants.CENTER);
            nomeCompania.setFont(new Font("Arial", Font.BOLD, 18));
            nomeCompania.setForeground(Color.BLACK);

            JPanel panel = new GradientPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.fill = GridBagConstraints.HORIZONTAL;

            adicionarComponentesLogin(panel, constraints, logo, nomeCompania);

            painel.add(panel, BorderLayout.CENTER);
            return painel;
        }

        private void adicionarComponentesLogin(JPanel panel, GridBagConstraints constraints, JLabel logo, JLabel nomeCompania) {
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            panel.add(logo, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 2;
            panel.add(nomeCompania, constraints);
            constraints.gridwidth = 1;

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("Usuário:"), constraints);
            constraints.gridx = 1;
            textUsuario = new JTextField(20);
            panel.add(textUsuario, constraints);
            textUsuario.putClientProperty("JComponent.roundRect", true);

            constraints.gridx = 0;
            constraints.gridy = 3;
            panel.add(new JLabel("Senha:"), constraints);
            constraints.gridx = 1;
            textSenha = new JPasswordField(20);
            panel.add(textSenha, constraints);
            textSenha.putClientProperty("JComponent.roundRect", true);

            constraints.gridx = 0;
            constraints.gridy = 4;
            checkBoxLembrar = new JCheckBox("Lembrar_me");
            panel.add(checkBoxLembrar, constraints);

            constraints.gridx = 1;
            constraints.gridy = 4;
            linkEsqueceuSenha = new JLabel("Esqueceu a senha?");
            linkEsqueceuSenha.setForeground(Color.BLUE.darker());  // Cor do link
            linkEsqueceuSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Cursor de mão
            linkEsqueceuSenha.setFont(new Font("Arial", Font.PLAIN, 12));
            linkEsqueceuSenha.setHorizontalAlignment(SwingConstants.LEFT);
            linkEsqueceuSenha.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));  // ajustar borda
            panel.add(linkEsqueceuSenha, constraints);

            constraints.gridx = 0;
            constraints.gridy = 5;
            buttonEntrar = new JButton("Entrar");
            panel.add(buttonEntrar, constraints);
            buttonEntrar.putClientProperty("JComponent.roundRect", true);
            buttonEntrar.setContentAreaFilled(true);            // Garante que a área de conteúdo seja preenchida
            buttonEntrar.setFocusPainted(false);                // Remove a borda de foco
            buttonEntrar.setBorderPainted(false);               // Remove a borda padrão
            buttonEntrar.setBackground(new Color(45, 117, 200)); //azul escuro

            constraints.gridx = 1;
            buttonSair = new JButton("Sair");
            panel.add(buttonSair, constraints);
            buttonSair.putClientProperty("JComponent.roundRect", true);
            buttonSair.putClientProperty("JButton.arc", 200);
            buttonSair.setContentAreaFilled(true);              // Garante que a área de conteúdo seja preenchida
            buttonSair.setFocusPainted(false);                  // Remove a borda de foco
            buttonSair.setBorderPainted(false);                 // Remove a borda padrão
            buttonSair.setBackground(new Color(139, 181, 229)); // azul claro

            add(panel);
        }

        public void configurarAcoes(JFrame frame, TelaLogin telaLogin, TelaPrograma telaPrograma) {
            this.telaLogin = this;
            this.telaPrograma = telaPrograma;
            /*
            buttonEntrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String usuario = textUsuario.getText().trim();
                    String senha = new String(textSenha.getPassword()).trim();

                    if (usuario.isEmpty() || senha.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    } else if (login.verificarLogin(usuario, senha)) {
                        JOptionPane.showMessageDialog(null, "Login realizado com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        frame.getContentPane().removeAll();
                        telaPrograma.renderizarPainelPrograma(frame, telaLogin, telaPrograma);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!",
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            buttonSair.addActionListener(e -> System.exit(0));*/

            // Listener para o link "Esqueceu a senha?"
            linkEsqueceuSenha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Redefinir senha não está implementado.",
                            "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    linkEsqueceuSenha.setText("<html><u>Esqueceu a senha?</u></html>"); // Adiciona o sublinhado quando o mouse passa por cima
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    linkEsqueceuSenha.setText("Esqueceu a senha?"); // Remove o sublinhado quando o mouse sai
                }
            });
        }
        // Configura os eventos dos itens de menu
        private void configurarEventosMenu(JMenuItem novoUsuario, JMenuItem options, JMenuItem ajuda) {
            // Listener para o menu Arquivo
            novoUsuario.addActionListener(e ->
                    JOptionPane.showMessageDialog(null, "O botão Novo Usuário não está implementado.",
                            "Informação", JOptionPane.INFORMATION_MESSAGE));

            // Listener para o menu Configurações
            options.addActionListener(e ->
                    JOptionPane.showMessageDialog(null, "O botão Opções não está implementado.",
                            "Informação", JOptionPane.INFORMATION_MESSAGE));

            // Listener para o menu Ajuda
            ajuda.addActionListener(e ->
                    JOptionPane.showMessageDialog(null, "Sistema de Gerenciamento de Inventário\n" +
                            "Versão 1.0", "Sobre", JOptionPane.INFORMATION_MESSAGE));
        }

        public JMenuBar criarMenuBar() {
            JMenuBar menuBar = new JMenuBar();

            // Criando menus principais
            JMenu menuArquivo = new JMenu("Arquivo");
            JMenu menuConfig = new JMenu("Configurações");
            JMenu menuAjuda = new JMenu("Ajuda");

            // Adicionando os menus à barra
            menuBar.add(menuArquivo);
            menuBar.add(menuConfig);
            menuBar.add(menuAjuda);

            // Criando itens de menu
            JMenuItem novoUsuario = new JMenuItem("Novo Usuario");
            JMenuItem options = new JMenuItem("Opções");
            JMenuItem ajuda = new JMenuItem("Sobre");

            menuArquivo.add(novoUsuario);
            menuConfig.add(options);
            menuAjuda.add(ajuda);

            configurarEventosMenu(novoUsuario, options, ajuda);

            return menuBar;
        }

        //getters and setters


        public TelaLogin getTelaLogin() {
            return telaLogin;
        }

        public void setTelaLogin(TelaLogin telaLogin) {
            this.telaLogin = telaLogin;
        }

        public TelaPrograma getTelaPrograma() {
            return telaPrograma;
        }

        public void setTelaPrograma(TelaPrograma telaPrograma) {
            this.telaPrograma = telaPrograma;
        }

        public JButton getButtonEntrar() {
            return buttonEntrar;
        }

        public void setButtonEntrar(JButton buttonEntrar) {
            this.buttonEntrar = buttonEntrar;
        }

        public JButton getButtonSair() {
            return buttonSair;
        }

        public void setButtonSair(JButton buttonSair) {
            this.buttonSair = buttonSair;
        }

        public JTextField getTextUsuario() {
            return textUsuario;
        }

        public void setTextUsuario(JTextField textUsuario) {
            this.textUsuario = textUsuario;
        }

        public JPasswordField getTextSenha() {
            return textSenha;
        }

        public void setTextSenha(JPasswordField textSenha) {
            this.textSenha = textSenha;
        }

        // Painel personalizado para aplicar o fundo degradê
        private class GradientPanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                GradientPaint gp = new GradientPaint(0, 0, new Color(107, 159, 223), 0, height, new Color(236, 236, 236));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        }
    }

