package model.identificadores;

import java.time.LocalDateTime;
import java.util.Random;

public class InformacaoLogin { // NAO USAR NAO USAR NAO USAR NAO USAR NAO USAR NAO USAR NAO USAR NAO USAR NAO USAR
    private String nome = "";
    private String senha = "";
    private String chaveRecuperacao = "";


    public InformacaoLogin(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        gerarChaveRecuperacao();
        System.out.println(chaveRecuperacao);
    }

    private boolean gerarChaveRecuperacao (){
        boolean funcionou = false;
        try {
            LocalDateTime dataAtual = LocalDateTime.now();
            String horario = dataAtual.toString().substring(11, 19);
            int tempoConvertido = (Integer.parseInt(horario.substring(0, 2)) * 3600) +
                    (Integer.parseInt(horario.substring(3, 5)) * 60) + Integer.parseInt(horario.substring(6, 8));
            Random random = new Random(tempoConvertido);
            int[] numeros = new int[3];
            char[] letras = new char[3];
            if (tempoConvertido % 2 == 0) {
                for (int i = 0; i < numeros.length; i ++){
                    numeros[i] = random.nextInt(0,9);
                    letras[i] = (char) random.nextInt(65, 90);
                }
            } else {
                for (int i = 0; i < numeros.length; i ++){
                    numeros[i] = random.nextInt(0,9);
                    letras[i] = (char) random.nextInt(97, 122);
                }
            }
            chaveRecuperacao += "" + letras[0] + letras[1] + numeros[0] + numeros[1] + numeros[2] + letras[2];
        }catch (Exception e){
            System.out.println("Houve um problema ao gerar a chave, tente o cadastro novamente!");
            funcionou = false;
        }
        return funcionou;
    }
}

