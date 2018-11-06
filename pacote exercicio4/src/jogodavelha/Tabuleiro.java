
package jogodavelha;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Graphics;
public class Tabuleiro implements ActionListener
{
	private JFrame janela;
	private GridLayout layout;
	private JButton[][] celulas;
	private ImageIcon xis;
	private ImageIcon zero;
	private String atual;
	private boolean fimDeJogo=false;
	private int posX;
	private int posY;
	public Tabuleiro()
	{
		setScreen();
	}
	public void setScreen()
	{
		janela = new JFrame("");//cria uma nova janela.
		janela.setLocation(posX,posY);//coloca-a na posição x,y; Esta é 0,0 na primeira rodada,
									  //mas depois guarda a posição final usada pelo usuário
									  //caso ele mova a janela.
		xis = new ImageIcon("src\\Imagem\\xis.jpg");//carrega o ícone
		zero= new ImageIcon("src\\Imagem\\zero.jpg");
		atual="";
		atual+="X";
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		layout = new GridLayout(3,3);
									//O gerenciador de layout das janelas as colocara em colunas e linhas
		janela.setTitle("Tic Tac Toe Game. Jogador: "+atual);//título da janela.
		janela.setLayout(layout);
		setButtons();
		for(int i =0;i< 3; i++)
		{
			for(int j=0;j<3;j++)
			{
				janela.add(celulas[i][j]);
			}
		}
		janela.setSize(550,550);
		janela.setVisible(true);//atualiza o desenho da janela.
		fimDeJogo=false;
	}
	public void setButtons()
	{
		celulas = new JButton[3][3];
		for(int i =0;i< 3; i++)
		{
			for(int j=0;j<3;j++)
			{
				//Instancia os botões.
				celulas[i][j] = new JButton("");
				celulas[i][j].addActionListener(this);
			}
		}
	}
	public void resetGame()
	{
		for(int i =0;i< 3; i++)
		{
			for(int j=0;j<3;j++)
			{
				/*
				 *Reseta-se o Status dos botões que representam as células.
				 */
				celulas[i][j].setIcon(null);
				celulas[i][j].addActionListener(this);
				celulas[i][j].revalidate();
			}
		}
		fimDeJogo=false;
	}
	public void actionPerformed(ActionEvent e)
	{
		/*
		 *Este método é chamado automaticamente pelo gerenciador de eventos.
		 *Verificará quem (o que) chamou o evento; como são todos botões,
		 *o qual chamou o evento terá seu ícone alterado pela imagem que
		 *representa a jogada atual (X OU 0).
		 */
		for(int i =0;i< 3; i++)
		{
			for(int j=0;j<3;j++)
			{
				if(e.getSource().equals(celulas[i][j]))
				{
					if(atual.compareToIgnoreCase("X")==0){
						celulas[i][j].setIcon(xis);
						celulas[i][j].removeActionListener(this);//para nao ser mais alterada, caso
					}else{
						celulas[i][j].setIcon(zero);
					    celulas[i][j].removeActionListener(this);//para nao ser mais alterada, caso
						//clicada.
					}
					String vencedor="";
					//Verifica se teve algum ganhador.
					if((vencedor=calcLinha())!=null)
					{
						JOptionPane.showMessageDialog(null,"O vencedor é "+vencedor);
						fimDeJogo=true;
					//	break;
					}
					vencedor="";
					if((vencedor=calcColuna())!=null)
					{
						JOptionPane.showMessageDialog(null,"O vencedor é "+vencedor);
						fimDeJogo=true;
					//	break;
					}
					vencedor="";
					if((vencedor=calcDiagonais())!=null)
					{
						JOptionPane.showMessageDialog(null,"O vencedor é "+vencedor);
						fimDeJogo=true;
					//	break;
					}
					if( (empate()) && (vencedor==null))
					{
						JOptionPane.showMessageDialog(null,"EMPATE!");
						fimDeJogo=true;
					}
				}
				if(fimDeJogo)
				{
					newGame();
					break;
				}
			}
		}
		proximaJogada();
	}
	public void proximaJogada()
	{
		/*
		 *Indica quem será o próximo a jogar.
		 */
		if(atual.compareToIgnoreCase("X")==0)
		{
			atual="";
			atual+="O";
		}
		else
		{
			atual="";
			atual+="X";
		}
		janela.setTitle("PancaVelha. Jogador: "+atual);
	}
	public boolean compCell(int c11,int c12,int c21, int c22)
	{
		/*
		 *Compara duas células.
		 */
		if( (celulas[c11][c12].getIcon()!=null) && (celulas[c21][c22].getIcon()!=null) )
		{
			if(celulas[c11][c12].getIcon().equals(celulas[c21][c22].getIcon()))
			{
				return true;
			}
		}
		return false;
	}
	public String calcLinha()
	{
		/*
		 *Calcula a força bruta se alguma linha foi preenchida.
		 */
		if( (compCell(0,0,0,1)) && (compCell(0,1,0,2)) )
		{
			if(celulas[0][0].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		if(	(compCell(1,0,1,1)) && (compCell(1,1,1,2)) )
		{
			if(celulas[1][0].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		if	( (compCell(2,0,2,1)) && (compCell(2,1,2,2)) )
		{
			if(celulas[2][0].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		return null;
	}
	public String calcColuna()
	{
		/*
		 *Calcula a força bruta se alguma coluna foi preenchida.
		 */
		if( (compCell(0,0,1,0)) && (compCell(1,0,2,0)) )
		{
			if(celulas[0][0].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		if(	(compCell(0,1,1,1)) && (compCell(1,1,2,1)) )
		{
			if(celulas[0][1].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		if	( (compCell(0,2,1,2)) && (compCell(1,2,2,2)) )
		{
			if(celulas[0][2].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		return null;
	}
	public String calcDiagonais()
	{
		/*
		 *Calcula a força bruta se as diagonais completam um jogo.
		 */
		if( (compCell(0,0,1,1)) && (compCell(1,1,2,2)) )
		{
			if(celulas[0][0].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		if( (compCell(0,2,1,1)) && (compCell(1,1,2,0)) )
		{
			if(celulas[0][2].getIcon().equals(xis))
				return "x";
			else
				return "0";
		}
		return null;
	}
	public boolean empate()
	{
		/*
		 *Este método será chamado se não houver ganhador (verificado nos métodos anteriores).
		 *Verifica todas células, vendo se existe alguma vazia.
		 */
		boolean tmp=true;
		for(int i =0;i< 3; i++)
		{
			for(int j=0;j<3;j++)
			{
				if(celulas[i][j].getIcon()==null)
					tmp=false;//se tiver alguma que nao foi marcada, ainda nao ocorreu empate.
			}
		}
		return tmp;
	}
	public boolean acabou()
	{
		return fimDeJogo;//verifica se o jogo acabou. Nao utilizado...
	}
	public void newGame()
	{
		/*
		 *Exibe um painel de opção. Cada botao tem um numero, começando do 0. Como o "YES" é o primeiro (esquerda para direita)
		 *seu número é 0. Este número é retornado assim que o botão é pressionado, então se o retorno for 0, um novo jogo começa.
		 */
		if(JOptionPane.showConfirmDialog(null, "Novo jogo?", "Novo", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null)==0)
		{
				posX=janela.getX();//guarda a posição da janela, para que outra seja criada na mesma.
				posY=janela.getY();
				janela.setVisible(false);
				setScreen();
		}
		else
			System.exit(0);
	}
	
}

