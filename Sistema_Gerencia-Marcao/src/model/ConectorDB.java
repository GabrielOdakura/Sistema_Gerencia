package model;

import model.identificadores.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;


public class ConectorDB {
    private Connection connection;
    public void getConnection() {
        String url = "jdbc:mysql://localhost:3306/Gestão-Produtos";
        String usuario = "root";
        String senha = "root";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Fazendo a conexão
            connection = DriverManager.getConnection(url, usuario, senha);

            System.out.println("Database conectada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro estabelecendo conexão com a database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySql não foi encontrado: " + e.getMessage());
        }
    }

    //Futuras Querries
    public ArrayList<Funcionario> SelectFuncionarios(){
        ArrayList<Funcionario> Funcionario_Dados = new ArrayList<>();
        String query = "Select * From Funcionarios";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Funcionario funcionario = new Funcionario();
                String Funcionarios_id = rs.getString("Funcionarios_id");
                String Funcionarios_nome = rs.getString("Funcionarios_nome");
                String Funcionarios_tempoAcesso = rs.getString("Funcionarios_tempoAcesso");
                String Funcionarios_cargo = rs.getString("Funcionarios_cargo");

                funcionario.cadastrarFuncionario(Funcionarios_nome,Funcionarios_cargo,Funcionarios_tempoAcesso,Funcionarios_id);
                Funcionario_Dados.add(funcionario);
                System.out.println(funcionario.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Funcionario_Dados;
    }

    public ArrayList<Login> SelectLogin(){
        ArrayList<Login> Login_Dados = new ArrayList<>();
        String query = "Select * From Login";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Login login;
                String Login_Usuario = rs.getString("Login_Usuario");
                String Login_Senha = rs.getString("Login_Senha");

                login = new Login(Login_Usuario,Login_Senha);
                Login_Dados.add(login);
                System.out.println(login.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Login_Dados;
    }

    public ArrayList<Produto> SelectProdutos(){
        ArrayList<Produto> Produto_Dados = new ArrayList<>();
        String query = "Select * From Produtos";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Produto produto = new Produto();
                String Produtos_id = rs.getString("Produtos_id");
                String Produtos_nome = rs.getString("Produtos_nome");
                String Produtos_preco = rs.getString("Produtos_preco");
                String Produtos_categoria = rs.getString("Produtos_categoria");
                String Produtos_descricao = rs.getString("Produtos_descricao");

                double preco = Double.parseDouble(Produtos_preco);

                produto.adicionarProduto(Produtos_nome,Produtos_id,preco,Produtos_categoria,Produtos_descricao);
                Produto_Dados.add(produto);
                System.out.println(produto.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Produto_Dados;
    }
    public ArrayList<Pedido> SelectPedidos(){
        ArrayList<Pedido> Pedido_Dados = new ArrayList<>();
        String query = "Select * From Pedidos";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Pedido pedido = new Pedido();
                String Pedidos_id = rs.getString("Pedidos_id");
                String Pedidos_nome = rs.getString("Pedidos_nome");
                String Pedidos_dataInicial = rs.getString("Pedidos_dataInicial");
                String Pedidos_dataEntrega = rs.getString("Pedidos_dataEntrega");
                String Pedidos_descricao = rs.getString("Pedidos_descricao");
                String Pedidos_Produtos_id = rs.getString("Produtos_Produtos_id");


                pedido.criarPedido(Pedidos_nome,Pedidos_id,Pedidos_dataInicial,Pedidos_dataEntrega,Pedidos_Produtos_id,Pedidos_descricao);
                Pedido_Dados.add(pedido);
                System.out.println(pedido.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Pedido_Dados;
    }

    public String describeTeste(){
        String query = "describe `gestão-produtos`.`login`;";
        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String field = rs.getString("Field");
                String type = rs.getString("Type");

                System.out.println("Field: " + field + ", Type: " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RetornoDescribe TabelaEmpresa(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `gestão-produtos`.`Empresa`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `gestão-produtos`.`Empresa`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new RetornoDescribe(vectorDados,vectorNomeColunas);
    }

    public RetornoDescribe TabelaEstoque(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `gestão-produtos`.`Estoque`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `gestão-produtos`.`Estoque`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new RetornoDescribe(vectorDados,vectorNomeColunas);
    }

    public RetornoDescribe TabelaFuncionarios(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `gestão-produtos`.`Funcionarios`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `gestão-produtos`.`Funcionarios`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new RetornoDescribe(vectorDados,vectorNomeColunas);
    }

    public RetornoDescribe TabelaLogin(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `gestão-produtos`.`Login`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `gestão-produtos`.`Login`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new RetornoDescribe(vectorDados,vectorNomeColunas);
    }

    public RetornoDescribe TabelaPedido(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `gestão-produtos`.`Pedidos`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `gestão-produtos`.`Pedidos`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new RetornoDescribe(vectorDados,vectorNomeColunas);
    }

    public RetornoDescribe TabelaProduto(){
        Vector<Vector<Object>> vectorDados = new Vector<>();
        Vector<String> vectorNomeColunas = new Vector<>();
        String query = "describe `gestão-produtos`.`Produtos`;";

        try{
            PreparedStatement stmt = connection.prepareCall(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<String> temp = new ArrayList<>();

            //pega dinamicamente todos os campos que serão escolhidos
            while (rs.next()) {
                temp.add(rs.getString("Field"));
            }

            vectorNomeColunas = new Vector<>(temp);

            query = "select * from `gestão-produtos`.`Produtos`;";
            stmt = connection.prepareCall(query);
            rs = stmt.executeQuery();

            // adiciona os dados de cada coluna 1 por 1 e passa pra prox coluna
            while(rs.next()){
                Vector<Object> vectorColuna = new Vector<>();
                for (String col: temp){
                    vectorColuna.add(rs.getObject(col));
                }
                vectorDados.add(vectorColuna);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new RetornoDescribe(vectorDados,vectorNomeColunas);
    }

   /*
   public static ArrayList<Empresa> InsertEmpresa(){
        String insertQuery = "Insert into `gestão-produtos`.`empresa`(`Empresa_id`,`Empresa_nome`) VALUES (\"1\",\"TechNova Solutions\"),(\"2\",\"GreenWave Industries\"),(\"3\",\"BlueSky Innovations\"),(\"4\",\"UrbanVista Architects\"),(\"5\",\"NextGen Dynamics\"),(\"6\",\"BrightFuture Investments\"),(\"7\",\"QuantumLeap Technologies\"),(\"8\",\"EcoSphere Enterprises\"),(\"9\",\"FutureLink Networks\"),(\"10\",\"Pinnacle Global\"),(\"11\",\"VistaCore Technologies\"),(\"12\",\"SilverLining Cloud Services\"),(\"13\",\"Zenith Financial Group\"),(\"14\",\"Harmony Wellness Solutions\"),(\"15\",\"AeroFlex Logistics\"),(\"16\",\"Solaris Energy Group\"),(\"17\",\"CrystalWave Technologies\"),(\"18\",\"NovaTerra Real Estate\"),(\"19\",\"PrimePath Consulting\"),(\"20\",\"InnovaTech Labs\");\n";

        try(Connection conn = ConectorDB.getConnection();
            PreparedStatement stmt = conn.prepareCall(insertQuery)){

            stmt.setString(1,"New Name");
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Affected Rows: "+ rowsAffected);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    */


    /*
    Insert into `gestão-produtos`.`empresa`(`Empresa_id`,`Empresa_nome`) VALUES ("1","TechNova Solutions"),("2","GreenWave Industries"),("3","BlueSky Innovations"),("4","UrbanVista Architects"),("5","NextGen Dynamics"),("6","BrightFuture Investments"),("7","QuantumLeap Technologies"),("8","EcoSphere Enterprises"),("9","FutureLink Networks"),("10","Pinnacle Global"),("11","VistaCore Technologies"),("12","SilverLining Cloud Services"),("13","Zenith Financial Group"),("14","Harmony Wellness Solutions"),("15","AeroFlex Logistics"),("16","Solaris Energy Group"),("17","CrystalWave Technologies"),("18","NovaTerra Real Estate"),("19","PrimePath Consulting"),("20","InnovaTech Labs");

    Insert into `gestão-produtos`.`funcionarios`(`Funcionarios_id`,`Funcionarios_nome`,`Funcionarios_tempoAcesso`,`Funcionarios_cargo`) VALUES (1,"Odakura",CURDATE(),"Gerente"),(2,"Breno",CURDATE(),"Gerente"),(3,"Bruno",CURDATE(),"Gerente"),(4,"Caio",CURDATE(),"Gerente"),(5,"Marques",CURDATE(),"Gerente");

    Insert into `gestão-produtos`.`login`(`Login_senha`,`Login_Usuario`,`Funcionarios_Funcionarios_id`) VALUES ('12345','Odakura','1'),('12345','Breno','2'),('12345','Bruno','3'),('12345','Caio','4'),('3us0ug4y','Marques','5');

    Insert into `gestão-produtos`.`produtos`(`Produtos_nome`,`Produtos_preco`,`Produtos_categoria`,`Produtos_descricao`,`Produtos_desconto`) VALUES ("Smartphone X100",1499.99,"Eletrônicos","Smartphone de última geração com câmera tripla e tela AMOLED de 6.5 polegadas.", 10),("Fone de Ouvido SonicBass",199.9,"Áudio","Fone de ouvido sem fio com cancelamento de ruído e bateria de longa duração.", 15),("Cafeteira UltraBrew",349.99,"Eletrodomésticos","Cafeteira automática com controle de temperatura e temporizador programável.", 20),("Notebook PowerFlex Pro",3999.0,"Informática","Notebook de alta performance com processador Intel i7 e 16GB de RAM.", 5),("Tênis FitMax",299.9,"Calçados","Tênis esportivo com tecnologia de amortecimento avançado e design moderno.", 25),("Relógio PulseTech",599.9,"Acessórios","Relógio inteligente com monitoramento de saúde e GPS integrado.", 10),("Garrafa Térmica CoolWave",79.9,"Utilidades Domésticas","Garrafa térmica de aço inoxidável com capacidade de 1 litro.", 30),("Câmera ProCapture 4K",2499.9,"Eletrônicos","Câmera profissional com gravação em 4K e lente intercambiável.", 12),("Batedeira PowerMix 300",399.9,"Eletrodomésticos","Batedeira planetária com 12 velocidades e tigela de 4 litros.", 18),("Mochila AdventurePro",249.99,"Esportes e Lazer","Mochila impermeável com capacidade de 35 litros, ideal para trilhas e viagens.", 10);

    Insert into `gestão-produtos`.`pedidos`(`Pedidos_dataInicial`,`Pedidos_dataEntrega`,`Pedidos_descricao`,`Empresa_Empresa_id`,`Produtos_Produtos_id`, `Funcionarios_Funcionarios_id`) VALUES (CURDATE(),CURDATE()+3,'Pedido.',16,5, 3),(CURDATE(),CURDATE()+3,'Pedido.',1,1, 3),(CURDATE(),CURDATE()+3,'Pedido.',6,5, 1),(CURDATE(),CURDATE()+3,'Pedido.',18,8, 5),(CURDATE(),CURDATE()+3,'Pedido.',3,4, 4),(CURDATE(),CURDATE()+3,'Pedido.',20,2, 1),(CURDATE(),CURDATE()+3,'Pedido.',14,3, 1),(CURDATE(),CURDATE()+3,'Pedido.',8,6, 1),(CURDATE(),CURDATE()+3,'Pedido.',4,1, 4),(CURDATE(),CURDATE()+3,'Pedido.',13,9, 4),(CURDATE(),CURDATE()+3,'Pedido.',13,1, 2),(CURDATE(),CURDATE()+3,'Pedido.',11,4, 5),(CURDATE(),CURDATE()+3,'Pedido.',6,8, 1),(CURDATE(),CURDATE()+3,'Pedido.',2,10, 4),(CURDATE(),CURDATE()+3,'Pedido.',13,2, 2);

    Insert into `gestão-produtos`.`estoque`(`estoque_total`,`estoque_disponivel`,`Produtos_Produtos_id`) VALUES (414,346,1),(151,148,2),(423,336,3),(111,106,4),(270,108,5),(494,76,6),(211,152,7),(292,114,8),(399,350,9),(411,401,10);
    */
}
