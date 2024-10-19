package model.identificadores;

import java.util.ArrayList;
import java.util.Vector;

public class RetornoDescribe {
    public Vector<Vector<Object>> vectorDados;
    public Vector<String> vectorNomeColunas;

    public RetornoDescribe(Vector<Vector<Object>> vectorDados, Vector<String> vectorNomeColunas) {
        this.vectorDados = vectorDados;
        this.vectorNomeColunas = vectorNomeColunas;
    }

    @Override
    public String toString(){
        String stringRetorno = "";
        String nomeColunas = String.join(" | ", vectorNomeColunas) + "\n";
        String dados = "";
        for(Vector<Object> linha : vectorDados){
            ArrayList<String> dadosLinha = new ArrayList<>();
            for(Object celula : linha){
                dadosLinha.add(celula.toString());
            }
            dados += String.join(" | ", dadosLinha) + "\n";
        }

        stringRetorno = nomeColunas + dados;

        return stringRetorno;
    }
}
