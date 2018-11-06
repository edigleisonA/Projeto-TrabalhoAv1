package jogodamemoria;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Memoria extends JFrame{
    
    private static final long serialVersionUID = 1L;

    int pontos = 100; 

    Random RandomNumber = new Random(); 
    int Aleatorio[] = new int [16];
    int PosicaodovetorAleatorio[] = new int [16];

    private  JToolBar BarraFerramenta = new JToolBar();

    private  JButton ButtonNovoJogo = new JButton("Novo Jogo");
    private  JButton[] Escolha = new JButton[16];
    private  Icon[] imgs = new Icon[8];
    private  Icon Icone = new ImageIcon("src\\images\\default\\card_blank");
   
    
    

    private  JPanel Panel = new JPanel();
    private  JPanel Barra_de_Status = new JPanel();

    private  GridLayout Layout_do_Jogo = new GridLayout(4,4); 
    private  Font Fonte = new Font("Lucida Console", Font.BOLD, 36);

    private  JLabel Pontuacao_do_Jogador = new JLabel("Pontos: 100");

    public Memoria() {
        
        super("Jogo da Memória");
        
        BarraFerramenta.add(ButtonNovoJogo);
        add(BarraFerramenta, BorderLayout.NORTH);
               
            for (int i=0; i<16; ++i){
                Escolha[i] = new JButton();
                Panel.add(Escolha[i]);
                Escolha[i].setFont(Fonte);
                Escolha[i].setVisible(true);
            }
        
        Panel.setLayout(Layout_do_Jogo);
        add(Panel, BorderLayout.CENTER);
        Barra_de_Status.add(Pontuacao_do_Jogador);
        add(Barra_de_Status, BorderLayout.SOUTH);
        
        Eventos_JogoDaMemoria Handler = new Eventos_JogoDaMemoria();
        for (int i=0; i<16; ++i){
            Escolha[i].addActionListener(Handler);
        }
        ButtonNovoJogo.addActionListener(Handler);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);     
        this.setSize(500,500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
       
        for(int i=0; i<imgs.length; i++){ 
           imgs[i] = new ImageIcon("src\\images\\cartas\\"+i+".png");
        }
    }
    

    

    
    private class Eventos_JogoDaMemoria implements ActionListener{
        int Cont_Acertos,Primeiro_Click,Segundo_Click;
        int Numero_Click, posi, cont, pontos_Anterior;
        boolean Novo_Jogo = true;
        boolean Re_Iniciar = false;
        public void actionPerformed(ActionEvent event){                       
            if (event.getSource() == ButtonNovoJogo){
                Novo_Jogo = true;
                Re_Iniciar = false;
            }
            if (Novo_Jogo == true){ 
                Cont_Acertos = 0;
                pontos_Anterior = pontos;
                pontos = 100;
                Numero_Click = 0;
                posi = 0; cont = 16;
                Primeiro_Click = 0;
                Segundo_Click = 0;
                for (int i=0; i<16; ++i){
                    Escolha[i].setEnabled(true);
                }
                if (Re_Iniciar == false){

                    for (int i=0; i<16; ++i){
                        PosicaodovetorAleatorio[i] = i;
                    }
                    for (int i=0; i<8; ++i){

                        for (int j=0; j<2; ++j){
                            posi = RandomNumber.nextInt(cont);
                            Aleatorio[PosicaodovetorAleatorio[posi]] = i;

                            if (posi < cont){
                                for (int q=(posi+1); q<(cont); ++q){
                                    PosicaodovetorAleatorio[q-1] = PosicaodovetorAleatorio[q];
                                }
                            } cont--;
                        }
                    }
                }
                Novo_Jogo = false;
            }
            for (int i=0; i<16; ++i){
                if (event.getSource() == Escolha[i]){
                    Escolha[i].setIcon(imgs[Aleatorio[i]]);  //novo no seu código
                    Escolha[i].setEnabled(false);
                    Escolha[i].setVisible(true);
                    Numero_Click++;
                    if (Numero_Click == 1) Primeiro_Click = i;
                    if (Numero_Click == 2){
                        Segundo_Click = i;
                        ///////////////Clicks_não_conseguidos///////////////
                        if (Aleatorio[Primeiro_Click] != Aleatorio[Segundo_Click]){                                                       
                            pontos-=2;
                            JOptionPane.showMessageDialog(Memoria.this, "Errado");
                            Escolha[Primeiro_Click].setIcon(null); //novo no seu código
                            Escolha[Segundo_Click].setIcon(null); //novo no seu código
                            Escolha[Primeiro_Click].setEnabled(true);
                            Escolha[Segundo_Click].setEnabled(true);                    
                        }  else {
                            Cont_Acertos++;
                            pontos+=10;
                        }
                        Numero_Click = 0;
                    }
                }
            }
            if (Cont_Acertos == 8){
                Cont_Acertos = 0;
                if (pontos > pontos_Anterior) {
                }
            }
            if (pontos < 0) pontos = 0;
            Pontuacao_do_Jogador.setText("Pontos: " + pontos);

        }
    }
   
    public static void main(String [] args){

    Memoria memoria = new Memoria();
    
    }  

    
}