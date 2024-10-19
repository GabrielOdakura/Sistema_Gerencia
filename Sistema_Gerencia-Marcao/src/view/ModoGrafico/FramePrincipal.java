package view.ModoGrafico;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import model.ConectorDB;
import model.identificadores.RetornoDescribe;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

public class FramePrincipal {
    private JFrame frame;
    private TelaLogin painelLogin;
    private TelaPrograma painelPrograma;
    private JLayeredPane painelPrincipal;

    public FramePrincipal(){

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("TabbedPane.background", new Color(74, 143, 211));
            UIManager.put("Panel.background", new Color(124, 173, 223));

            UIManager.put("Table.rowHeight", 25); // Altura das linhas
            UIManager.put("Table.intercellSpacing", new Dimension(3, 3)); // Espaço entre células
            UIManager.put("Table.selectionInsets", new Insets(1, 1, 1, 1)); // Margens da seleção
            UIManager.put("Table.selectionArc", 10); // Arredondamento da seleção
            UIManager.put("Table.cellMargins", new Insets(5, 5, 5, 5));



        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Sistema de Gerenciamento de Inventário");


        JMenuBar menuBar = criarMenuBar();
        frame.setJMenuBar(menuBar);


        painelLogin = new TelaLogin();
        painelLogin.setBounds(0,0,700,600);


        painelPrograma = new TelaPrograma();
        painelPrograma.setBounds(0,0,700,600);


        painelPrincipal = new JLayeredPane();


        painelPrincipal.add(painelPrograma, JLayeredPane.DEFAULT_LAYER);
        painelPrincipal.setBounds(0,0,700,600);
        painelPrincipal.setVisible(true);
        painelPrincipal.add(painelLogin, JLayeredPane.PALETTE_LAYER);

        // Adiciona o listener para redimensionamento
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                ajustarComponentes(new JTable(), new JScrollPane());
            }
        });

        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(painelPrincipal, BorderLayout.CENTER);
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

    public void TabelaFuncionario(RetornoDescribe retorno) {
        // Obtenção do JTabbedPane e inicialização da tabela
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados, retorno.vectorNomeColunas);

        // Configurações gerais da tabela (renderização, rolagem e bordas)
        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem

        // Configurações de redimensionamento
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Colunas se ajustam automaticamente

        // Estilo de seleção e cursor
        tabela.setSelectionBackground(new Color(100, 149, 237)); // Cor de fundo na seleção
        tabela.setSelectionForeground(Color.BLACK); // Cor da fonte na seleção
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor muda para "mão"

        // Ajustes de fonte e layout de células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte da tabela
        tabela.setShowGrid(true); // Exibe grade
        tabela.setGridColor(Color.GRAY); // Cor da grade
        tabela.setShowHorizontalLines(true); // Linhas horizontais visíveis
        tabela.setShowVerticalLines(true); // Linhas verticais visíveis

        // Customização do JScrollPane (envolvendo a tabela)
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1)); // Borda do JScrollPane
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna do viewport
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Barra horizontal sob demanda
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra vertical sob demanda

        // Customização do cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do cabeçalho
        header.setBackground(Color.GRAY); // Cor de fundo do cabeçalho
        header.setForeground(Color.WHITE); // Cor da fonte do cabeçalho

        // Configuração do layout do painel que contém a tabela
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel

        // Adiciona a nova aba ao JTabbedPane
        tabbedPane.addTab("TabelaFuncionario", painelTeste);
        tabbedPane.revalidate(); // Revalida o layout do tabbedPane
        tabbedPane.repaint(); // Reaplica as configurações visuais

        // Renderização personalizada das células (alinhamento e cores alternadas)
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margens internas
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento central

                if (!isSelected) {
                    // Alterna a cor das linhas (cinza claro e branco)
                    label.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.LIGHT_GRAY);
                }
                return label;
            }
        });

        // Exibição de tooltips nas células ao passar o mouse
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString()); // Exibe o conteúdo como tooltip
                    }
                }
            }
        });
    }

    public void TabelaEmpresa(RetornoDescribe retorno) {
        // Obtenção do JTabbedPane e inicialização da tabela
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados, retorno.vectorNomeColunas);

        // Configurações gerais da tabela (renderização, rolagem e bordas)
        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem

        // Configurações de redimensionamento
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Colunas se ajustam automaticamente

        // Estilo de seleção e cursor
        tabela.setSelectionBackground(new Color(100, 149, 237)); // Cor de fundo na seleção
        tabela.setSelectionForeground(Color.BLACK); // Cor da fonte na seleção
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor muda para "mão"

        // Ajustes de fonte e layout de células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte da tabela
        tabela.setShowGrid(true); // Exibe grade
        tabela.setGridColor(Color.GRAY); // Cor da grade
        tabela.setShowHorizontalLines(true); // Linhas horizontais visíveis
        tabela.setShowVerticalLines(true); // Linhas verticais visíveis

        // Customização do JScrollPane (envolvendo a tabela)
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1)); // Borda do JScrollPane
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna do viewport
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Barra horizontal sob demanda
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra vertical sob demanda

        // Customização do cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do cabeçalho
        header.setBackground(Color.GRAY); // Cor de fundo do cabeçalho
        header.setForeground(Color.WHITE); // Cor da fonte do cabeçalho

        // Configuração do layout do painel que contém a tabela
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel

        // Adiciona a nova aba ao JTabbedPane
        tabbedPane.addTab("TabelaEmpresa", painelTeste);
        tabbedPane.revalidate(); // Revalida o layout do tabbedPane
        tabbedPane.repaint(); // Reaplica as configurações visuais

        // Renderização personalizada das células (alinhamento e cores alternadas)
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margens internas
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento central

                if (!isSelected) {
                    // Alterna a cor das linhas (cinza claro e branco)
                    label.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.LIGHT_GRAY);
                }
                return label;
            }
        });

        // Exibição de tooltips nas células ao passar o mouse
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString()); // Exibe o conteúdo como tooltip
                    }
                }
            }
        });
    }

    public void TabelaEstoque(RetornoDescribe retorno) {
        // Obtenção do JTabbedPane e inicialização da tabela
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados, retorno.vectorNomeColunas);

        // Configurações gerais da tabela (renderização, rolagem e bordas)
        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem

        // Configurações de redimensionamento
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Colunas se ajustam automaticamente

        // Estilo de seleção e cursor
        tabela.setSelectionBackground(new Color(100, 149, 237)); // Cor de fundo na seleção
        tabela.setSelectionForeground(Color.BLACK); // Cor da fonte na seleção
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor muda para "mão"

        // Ajustes de fonte e layout de células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte da tabela
        tabela.setShowGrid(true); // Exibe grade
        tabela.setGridColor(Color.GRAY); // Cor da grade
        tabela.setShowHorizontalLines(true); // Linhas horizontais visíveis
        tabela.setShowVerticalLines(true); // Linhas verticais visíveis

        // Customização do JScrollPane (envolvendo a tabela)
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1)); // Borda do JScrollPane
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna do viewport
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Barra horizontal sob demanda
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra vertical sob demanda

        // Customização do cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do cabeçalho
        header.setBackground(Color.GRAY); // Cor de fundo do cabeçalho
        header.setForeground(Color.WHITE); // Cor da fonte do cabeçalho

        // Configuração do layout do painel que contém a tabela
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel

        // Adiciona a nova aba ao JTabbedPane
        tabbedPane.addTab("TabelaEstoque", painelTeste);
        tabbedPane.revalidate(); // Revalida o layout do tabbedPane
        tabbedPane.repaint(); // Reaplica as configurações visuais

        // Renderização personalizada das células (alinhamento e cores alternadas)
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margens internas
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento central

                if (!isSelected) {
                    // Alterna a cor das linhas (cinza claro e branco)
                    label.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.LIGHT_GRAY);
                }
                return label;
            }
        });

        // Exibição de tooltips nas células ao passar o mouse
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString()); // Exibe o conteúdo como tooltip
                    }
                }
            }
        });
    }

    public void TabelaLogin(RetornoDescribe retorno) {
        // Obtenção do JTabbedPane e inicialização da tabela
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados, retorno.vectorNomeColunas);

        // Configurações gerais da tabela (renderização, rolagem e bordas)
        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem

        // Configurações de redimensionamento
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Colunas se ajustam automaticamente

        // Estilo de seleção e cursor
        tabela.setSelectionBackground(new Color(100, 149, 237)); // Cor de fundo na seleção
        tabela.setSelectionForeground(Color.BLACK); // Cor da fonte na seleção
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor muda para "mão"

        // Ajustes de fonte e layout de células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte da tabela
        tabela.setShowGrid(true); // Exibe grade
        tabela.setGridColor(Color.GRAY); // Cor da grade
        tabela.setShowHorizontalLines(true); // Linhas horizontais visíveis
        tabela.setShowVerticalLines(true); // Linhas verticais visíveis

        // Customização do JScrollPane (envolvendo a tabela)
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1)); // Borda do JScrollPane
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna do viewport
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Barra horizontal sob demanda
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra vertical sob demanda

        // Customização do cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do cabeçalho
        header.setBackground(Color.GRAY); // Cor de fundo do cabeçalho
        header.setForeground(Color.WHITE); // Cor da fonte do cabeçalho

        // Configuração do layout do painel que contém a tabela
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel

        // Adiciona a nova aba ao JTabbedPane
        tabbedPane.addTab("TabelaLogin", painelTeste);
        tabbedPane.revalidate(); // Revalida o layout do tabbedPane
        tabbedPane.repaint(); // Reaplica as configurações visuais

        // Renderização personalizada das células (alinhamento e cores alternadas)
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margens internas
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento central

                if (!isSelected) {
                    // Alterna a cor das linhas (cinza claro e branco)
                    label.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.LIGHT_GRAY);
                }
                return label;
            }
        });

        // Exibição de tooltips nas células ao passar o mouse
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString()); // Exibe o conteúdo como tooltip
                    }
                }
            }
        });
    }

    public void TabelaPedido(RetornoDescribe retorno) {
        // Obtenção do JTabbedPane e inicialização da tabela
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados, retorno.vectorNomeColunas);

        // Configurações gerais da tabela (renderização, rolagem e bordas)
        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem

        // Configurações de redimensionamento
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Colunas se ajustam automaticamente

        // Estilo de seleção e cursor
        tabela.setSelectionBackground(new Color(100, 149, 237)); // Cor de fundo na seleção
        tabela.setSelectionForeground(Color.BLACK); // Cor da fonte na seleção
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor muda para "mão"

        // Ajustes de fonte e layout de células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte da tabela
        tabela.setShowGrid(true); // Exibe grade
        tabela.setGridColor(Color.GRAY); // Cor da grade
        tabela.setShowHorizontalLines(true); // Linhas horizontais visíveis
        tabela.setShowVerticalLines(true); // Linhas verticais visíveis

        // Customização do JScrollPane (envolvendo a tabela)
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1)); // Borda do JScrollPane
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna do viewport
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Barra horizontal sob demanda
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra vertical sob demanda

        // Customização do cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do cabeçalho
        header.setBackground(Color.GRAY); // Cor de fundo do cabeçalho
        header.setForeground(Color.WHITE); // Cor da fonte do cabeçalho

        // Configuração do layout do painel que contém a tabela
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel

        // Adiciona a nova aba ao JTabbedPane
        tabbedPane.addTab("TabelaPedido", painelTeste);
        tabbedPane.revalidate(); // Revalida o layout do tabbedPane
        tabbedPane.repaint(); // Reaplica as configurações visuais

        // Renderização personalizada das células (alinhamento e cores alternadas)
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margens internas
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento central

                if (!isSelected) {
                    // Alterna a cor das linhas (cinza claro e branco)
                    label.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.LIGHT_GRAY);
                }
                return label;
            }
        });

        // Exibição de tooltips nas células ao passar o mouse
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString()); // Exibe o conteúdo como tooltip
                    }
                }
            }
        });
    }

    public void TabelaProduto(RetornoDescribe retorno) {
        // Obtenção do JTabbedPane e inicialização da tabela
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados, retorno.vectorNomeColunas);

        // Configurações gerais da tabela (renderização, rolagem e bordas)
        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem

        // Configurações de redimensionamento
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Colunas se ajustam automaticamente

        // Estilo de seleção e cursor
        tabela.setSelectionBackground(new Color(100, 149, 237)); // Cor de fundo na seleção
        tabela.setSelectionForeground(Color.BLACK); // Cor da fonte na seleção
        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor muda para "mão"

        // Ajustes de fonte e layout de células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte da tabela
        tabela.setShowGrid(true); // Exibe grade
        tabela.setGridColor(Color.GRAY); // Cor da grade
        tabela.setShowHorizontalLines(true); // Linhas horizontais visíveis
        tabela.setShowVerticalLines(true); // Linhas verticais visíveis

        // Customização do JScrollPane (envolvendo a tabela)
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1)); // Borda do JScrollPane
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem interna do viewport
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Barra horizontal sob demanda
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra vertical sob demanda

        // Customização do cabeçalho da tabela
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte do cabeçalho
        header.setBackground(Color.GRAY); // Cor de fundo do cabeçalho
        header.setForeground(Color.WHITE); // Cor da fonte do cabeçalho

        // Configuração do layout do painel que contém a tabela
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro do painel

        // Adiciona a nova aba ao JTabbedPane
        tabbedPane.addTab("TabelaProduto", painelTeste);
        tabbedPane.revalidate(); // Revalida o layout do tabbedPane
        tabbedPane.repaint(); // Reaplica as configurações visuais

        // Renderização personalizada das células (alinhamento e cores alternadas)
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Margens internas
                label.setHorizontalAlignment(SwingConstants.CENTER); // Alinhamento central

                if (!isSelected) {
                    // Alterna a cor das linhas (cinza claro e branco)
                    label.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.LIGHT_GRAY);
                }
                return label;
            }
        });

        // Exibição de tooltips nas células ao passar o mouse
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString()); // Exibe o conteúdo como tooltip
                    }
                }
            }
        });
    }

    private void ajustarComponentes(JTable tabela, JScrollPane scrollPane) {
        // Obtém o tamanho atual da janela
        Dimension tamanhoFrame = frame.getSize();

        // Define o tamanho dos painéis principais para preencher a janela
        painelPrincipal.setSize(tamanhoFrame);
        painelLogin.setSize(tamanhoFrame);
        painelPrograma.setSize(tamanhoFrame);

        // Ajusta dinamicamente a tabela conforme o número de linhas
        int alturaLinha = tabela.getRowHeight();
        int quantidadeLinhas = tabela.getRowCount();
        int alturaTotal = alturaLinha * quantidadeLinhas;

        // Define o tamanho preferido e mínimo do viewport do JScrollPane
        Dimension dimensaoTabela = new Dimension(tabela.getPreferredSize().width, alturaTotal);
        tabela.setPreferredScrollableViewportSize(dimensaoTabela);

        // Revalida e repinta o JScrollPane e a tabela para aplicar as alterações
        scrollPane.revalidate();
        scrollPane.repaint();

        // Revalida e repinta os painéis principais
        painelPrincipal.revalidate();
        painelPrincipal.repaint();

    }

    //getters
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPainelLogin() {
        return painelLogin;
    }

    public JPanel getPainelPrograma() {
        return painelPrograma;
    }

    public JLayeredPane getPainelPrincipal() {
        return painelPrincipal;
    }

    public JButton[] getBotoes(){
        return new JButton[]{
            painelLogin.getButtonEntrar(),painelLogin.getButtonSair()
        };
    }
}
