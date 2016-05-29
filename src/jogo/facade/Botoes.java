/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.facade;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Lucas
 */
public class Botoes {
    
    private JButton[][] jBotoes;
    
    public Botoes(int lin, int col){
        jBotoes = new JButton[lin][col];
    }
    
    JButton[][] criaBotoes(GridBagConstraints grid, JPanel component) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jBotoes[i][j] = new JButton();
                jBotoes[i][j].setSize(50, 50);
                jBotoes[i][j].setPreferredSize(new Dimension(50, 50));
                jBotoes[i][j].setText("");
                grid.ipadx = 50;
                grid.ipady = 50;
                grid.gridx = i;
                grid.gridy = j;
                component.add(jBotoes[i][j], grid);
            }
        }
        
        return jBotoes;
    }
    
    void liberarBotoes(){
        for (JButton[] botoes : jBotoes) {
            for (JButton botao : botoes) {
                botao.setEnabled(true);
            }
        }
    }

    void resetaBotoes() {
        for (JButton[] botoes : jBotoes) {
            for (JButton botao : botoes) {
                botao.setEnabled(true);
                botao.setText("");
            }
        }
    }

    void bloquearBotoes() {
        for (JButton[] botoes : jBotoes) {
            for (JButton botao : botoes) {
                botao.setEnabled(false);
            }
        }
    }
}
