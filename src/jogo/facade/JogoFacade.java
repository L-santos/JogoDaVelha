/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.facade;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Lucas
 */
public class JogoFacade {

    private Tabuleiro tabuleiro;
    private Botoes botoesBuilder;
    private Historico historico;
    private JButton[][] jButtonBotoes;

    public JogoFacade() {
        botoesBuilder = new Botoes(3, 3);
        historico = new Historico();
    }

    //Cria os botoes, insere no panel
    public void getTabuleiro(Object acao, boolean ativo, String comando, JPanel panel) {
        GridBagConstraints grid = new GridBagConstraints();
        jButtonBotoes = botoesBuilder.criaBotoes(grid, panel);
        for (JButton[] botoes : jButtonBotoes) {
            for (JButton botao : botoes) {
                botao.setEnabled(ativo);
                botao.addActionListener((ActionListener) acao);
                botao.setActionCommand(comando);
            }
        }
    }

    public void iniciarPartida(String jogadorX, String jogadorY) {
        tabuleiro = new Tabuleiro();
        tabuleiro.setJogadores(jogadorX, jogadorY);
        botoesBuilder.resetaBotoes();
    }

    public void reiniciarPartida(String jogadorX, String jogadorY) {
        tabuleiro = new Tabuleiro();
        tabuleiro.setJogadores(jogadorX, jogadorY);
        botoesBuilder.resetaBotoes();
    }

    public void terminarPartida(String resultado) {
        try {
            historico.salvarResultado(tabuleiro.getJogadorX(), tabuleiro.getJogadorY(), resultado);
        } catch (IOException ex) {
            Logger.getLogger(JogoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        botoesBuilder.bloquearBotoes();
    }

    public String getHistorico() {
        String recordes = historico.lerResultado();
        if (recordes.equals("")) {
            recordes = "Sem recordes.";
        }
        return recordes;
    }

    public void removehistorico() {
        try {
            historico.deletaResultados();
        } catch (IOException ex) {
            Logger.getLogger(JogoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setJogada(JButton jButton) {
        String jogada = tabuleiro.getJogada();
        jButton.setText(jogada);
        jButton.setEnabled(false);
        String[][] botoes = getBotoes();
        tabuleiro.setTabuleiro(botoes);
        tabuleiro.verificaTabuleiro();
        tabuleiro.checaLinhas();
        tabuleiro.checaColunas();
        tabuleiro.checaDiagonais();
        tabuleiro.processaTurno();
    }
    
    public String getProximaJogada(){
        return tabuleiro.getJogada();
    }
    private String[][] getBotoes() {
        String[][] campos = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                campos[i][j] = jButtonBotoes[i][j].getText();
            }
        }
        return campos;
    }

    public String verificaResultado() {
        if (tabuleiro.temVencedor()) {
            return tabuleiro.getVencedor() + " Venceu!";
        } else if (tabuleiro.tabuleiroCompleto()) {
            return "empate";
        }

        return null;
    }

}
