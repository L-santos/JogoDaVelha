/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.facade;

/**
 *
 * @author Lucas
 */
class Tabuleiro {
    private String[][] tabuleiro;
    private String jogadorX;
    private String jogadorY;
    private String turno;
    private boolean completo;
    private String vencedor;
    
    public Tabuleiro(){
        tabuleiro = new String[3][3];
        completo = false;
        vencedor = " ";
    }

    void setJogadores(String jogadorX, String jogadorY) {
        this.jogadorX = jogadorX;
        this.jogadorY = jogadorY;
        turno = "X";
    }
    
    void setTabuleiro(String[][] botoes) {
        tabuleiro = botoes;
    }
    
    String getJogadorX(){
        return jogadorX;
    }
    
    String getJogadorY(){
        return jogadorY;
    }
    String getJogada() {
       
       return turno;
    }
     
    void processaTurno(){
        if(turno.equals("X")){
            turno = "O";
        }else if(turno.equals("O")){
            turno = "X";
        }
    }
    
    boolean tabuleiroCompleto(){
        return completo;
    }
    
    boolean temVencedor(){
        return !vencedor.equals(" ");
    }
    
    void verificaTabuleiro() {
        int count = 9;
        for (String[] strings : tabuleiro) {
            for (String string : strings) {
                if(string.equals("")){
                    count--;
                }
            }
        }
        
        if(count == 9){
            completo = true;
        }
    }

    String getVencedor() {
        if(vencedor.equals("X")){
            vencedor = jogadorX;
        }if(vencedor.equals("O")){
            vencedor = jogadorY;
        }
        
        return vencedor;
    }
    
    //Checa as linhas para um vencedor
    void checaColunas() {
        for (int linha = 0; linha < 3; linha++) {

            if (tabuleiro[linha][0].equals("X") && tabuleiro[linha][1].equals("X") && tabuleiro[linha][2].equals("X")) {
                   vencedor = "X";
            }
            if (tabuleiro[linha][0].equals("O") && tabuleiro[linha][1].equals("O") && tabuleiro[linha][2].equals("O")) {
                    vencedor = "O";
            }
        }
    }

    void checaLinhas() {
         for (int coluna = 0; coluna < 3; coluna++) {

            if (tabuleiro[0][coluna].equals("X") && tabuleiro[1][coluna].equals("X") && tabuleiro[2][coluna].equals("X")) {
                   vencedor = "X";
            }
            if (tabuleiro[0][coluna].equals("O") && tabuleiro[1][coluna].equals("O") && tabuleiro[2][coluna].equals("O")) {
                    vencedor = "O";
            }
        }       
    }

    void checaDiagonais() {
            if (tabuleiro[0][0].equals("X") && tabuleiro[1][1].equals("X") && tabuleiro[2][2].equals("X")) {
                   vencedor = "X";
            }
            if (tabuleiro[0][0].equals("O") && tabuleiro[1][1].equals("O") && tabuleiro[2][2].equals("O")) {
                   vencedor = "O";
            }
            if (tabuleiro[0][2].equals("X") && tabuleiro[1][1].equals("X") && tabuleiro[2][0].equals("X")) {
                   vencedor = "X";
            }
            if (tabuleiro[0][2].equals("O") && tabuleiro[1][1].equals("O") && tabuleiro[2][0].equals("O")) {
                   vencedor = "O";
            }
    }

}
