/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo.programa;

import java.awt.BorderLayout;
import jogo.facade.JogoFacade;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lucas
 */
public class Jogo extends JFrame implements ActionListener{

    private static Jogo jogo;
    JogoFacade jogoFacade;
    
    //Singleton
    static Jogo criarJanela() {
        if(jogo == null){
            jogo = new Jogo();
        }else{
            System.out.println("Janela já existe");
        }
        return jogo;
    }
    
    
    private Jogo(){
        System.out.println("Janela Criada");
        jogoFacade = new JogoFacade();
        initComponents();
    }
    
    /*public static void main(String[] args) {   
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jogo().setVisible(true);
            }
        });    
    }*/
    
    private  void initComponents(){
        
        jPanelTabuleiro = new JPanel(new GridBagLayout());
        jPanelStatus = new JPanel();
        jPanelContainer = new JPanel();
        jLabelJogada = new JLabel("X");
        jMenuBar = new JMenuBar();
        jMenuPartida = new JMenu("Partida");
        jMenuOpcoes = new JMenu("Opções");
        jMenuItemReiniciar = new JMenuItem("Reiniciar");
        jMenuItemJogar = new JMenuItem("Iniciar");
        jMenuItemHistorico = new JMenuItem("Recordes");
        jMenuItemApagarHistorico = new JMenuItem("Remover Historico");
        jButtonJogadorX = new JButton("X");
        jButtonJogadorO = new JButton("O");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        
        jogoFacade.getTabuleiro(this, false, "tabuleiroClick", jPanelTabuleiro);
                
        jMenuItemJogar.addActionListener(this::iniciarAction);
        
        jMenuItemReiniciar.addActionListener(this::reinciarAction);
        jMenuItemReiniciar.setVisible(false);
        
        jMenuItemHistorico.addActionListener(this::historicoAction);
        jMenuItemApagarHistorico.addActionListener(this::removeHistorico);
                
        jMenuPartida.add(jMenuItemReiniciar);
        jMenuPartida.add(jMenuItemJogar);
        jMenuOpcoes.add(jMenuItemHistorico);
        jMenuOpcoes.add(jMenuItemApagarHistorico);
        jMenuBar.add(jMenuPartida);
        jMenuBar.add(jMenuOpcoes);
        setJMenuBar(jMenuBar);
        jPanelStatus.add(jLabelJogada);
        jPanelContainer.add(jPanelStatus, BorderLayout.CENTER);
        jPanelContainer.add(jPanelTabuleiro, BorderLayout.PAGE_END);
        jPanelContainer.revalidate();
        add(jPanelContainer);
        pack();
    }
   
    //Responsavel pelos clicks no tabuleiro
    @Override
    public void actionPerformed(ActionEvent e) {
      String comando = e.getActionCommand();
      if(comando.equals("tabuleiroClick")){
        JButton jButton = (JButton)e.getSource();
        jogoFacade.setJogada(jButton);
        String resultado = jogoFacade.verificaResultado();
        jLabelJogada.setText(jogoFacade.getProximaJogada());
        //checa se existe um vencedor
        if(resultado != null){
            //Salva no historico
            jogoFacade.terminarPartida(resultado);
            int resposta = JOptionPane.showConfirmDialog(this, resultado+"\nNova Partida?", "FIm de Jogo", JOptionPane.YES_NO_OPTION);
            if(resposta == JOptionPane.YES_OPTION){        
                iniciarAction(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }else if(resposta == JOptionPane.NO_OPTION){
                jMenuItemReiniciar.setVisible(false);
                jMenuItemJogar.setVisible(true);
            }
        }
      }
    }

    //Mostra Historico
    private void historicoAction(ActionEvent evt){
        String historico = jogoFacade.getHistorico();
        JOptionPane.showMessageDialog(this, historico);
    }
    
    //Apaga Historico
    private void removeHistorico(ActionEvent evt){
        jogoFacade.removehistorico();
        JOptionPane.showMessageDialog(this, "Lista de Recordes Excluida");
    }
    
    //Inicia/reinicia a partida
    private void iniciarAction(ActionEvent evt) {
        String jogadorX = JOptionPane.showInputDialog("Jogador X", "Jogador X");
        String jogadorY = JOptionPane.showInputDialog("Jogador O", "Jogador Y");
        jogoFacade.iniciarPartida(jogadorX, jogadorY);
        jLabelJogada.setText("X");
        jMenuItemReiniciar.setVisible(true);
        jMenuItemJogar.setVisible(false);
    }
    
    //Não usa isso, redundante
    private void reinciarAction(ActionEvent evt) {
        String jogadorX = JOptionPane.showInputDialog("Jogador X", "Jogador X");
        String jogadorY = JOptionPane.showInputDialog("Jogador O", "Jogador Y");
        jogoFacade.reiniciarPartida(jogadorX, jogadorY);
    }
    
    //Inicio declaração de variaveis
    //JFrame jFrameJanela;
    JPanel jPanelTabuleiro;
    JPanel jPanelStatus;
    JPanel jPanelContainer;
    JLabel jLabelJogada;
    JMenuBar jMenuBar;
    JMenu jMenuPartida;
    JMenu jMenuOpcoes;
    JMenuItem jMenuItemReiniciar;
    JMenuItem jMenuItemJogar;
    JMenuItem jMenuItemHistorico;
    JMenuItem jMenuItemApagarHistorico;
    JButton jButtonJogadorX;
    JButton jButtonJogadorO;
    //Fim declaração de variaveis
}
