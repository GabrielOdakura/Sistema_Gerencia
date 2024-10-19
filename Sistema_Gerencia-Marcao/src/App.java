

import controler.ControleModoTexto;
import model.ConectorDB;
import view.ModoGrafico.FramePrincipal;


import javax.swing.*;


public class App {
	public static void main(String[] args) {

		FramePrincipal table = new FramePrincipal();

		JButton[] botoes = table.getBotoes();

		JButton botao = botoes[0];
		JButton bsair = botoes[1];

		botao.addActionListener(e ->{
			table.getPainelPrincipal().setLayer(table.getPainelLogin(), JLayeredPane.DEFAULT_LAYER);
			table.getPainelPrincipal().setLayer(table.getPainelPrograma(), JLayeredPane.PALETTE_LAYER);
			table.getPainelPrincipal().revalidate();
			table.getPainelPrincipal().repaint();

		});

		ConectorDB conector = new ConectorDB();
		conector.getConnection();
		table.TabelaFuncionario(conector.TabelaFuncionarios());
		table.TabelaEmpresa(conector.TabelaEmpresa());
		table.TabelaEstoque(conector.TabelaEstoque());
		table.TabelaLogin(conector.TabelaLogin());
		table.TabelaPedido(conector.TabelaPedido());
		table.TabelaProduto(conector.TabelaProduto());


		bsair.addActionListener(e -> System.exit(0));

		ControleModoTexto start = new ControleModoTexto();
		start.start();
		System.exit(0);

// VERSAO ANTERIOR APP
//		FramePrincipal teste = new FramePrincipal();
//
//		JButton[] botoes = teste.getBotoes();
//
//		JButton botao = botoes[0];
//		JButton bsair = botoes[1];
//
//		botao.addActionListener(e ->{
//			teste.getPainelPrincipal().setLayer(teste.getPainelLogin(), JLayeredPane.DEFAULT_LAYER);
//			teste.getPainelPrincipal().setLayer(teste.getPainelPrograma(), JLayeredPane.PALETTE_LAYER);
//			teste.getPainelPrincipal().revalidate();
//			teste.getPainelPrincipal().repaint();
//
//		});
//
//		ConectorDB conector = new ConectorDB();
//		conector.getConnection();
//		teste.testeTabela(conector.testeTabela());
//
//		bsair.addActionListener(e -> System.exit(0));
//
//		ControleModoTexto start = new ControleModoTexto();
//		start.start();
//		System.exit(0);

	}
}



