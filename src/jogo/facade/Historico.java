/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.facade;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author Lucas
 */
public class Historico {
    String historico = "historico.txt";
    void salvarResultado(String jogador1, String jogador2, String resultado) throws IOException {       
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String format = dateFormat.format(d);
        File arquivo = new File(historico);
        System.out.println(arquivo.getAbsolutePath());
        FileWriter arq;
                arq = new FileWriter(arquivo, true);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(format +","+jogador1+" X "+jogador2+" Resultado: "+resultado+"\n");
        arq.close();
    }
    
    String lerResultado(){
        String saida = "";
        String temp = "";
        try{
            FileReader reader = new FileReader(historico);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while((temp = bufferedReader.readLine()) != null) {
                saida += temp+"\n";
            }
        }catch(FileNotFoundException e){
            saida = "";
        }catch(IOException e){
            saida = "";
        }
        
        return saida;
    }

    void deletaResultados() throws IOException{
        File arquivo = new File(historico);
        FileWriter arq;
        arq = new FileWriter(arquivo);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("");
        arq.close();
    }
}
