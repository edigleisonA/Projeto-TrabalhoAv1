
package maquinacacaniquel;

import java.util.Random;
import javax.swing.JLabel;

public class ThreadMaquina extends Thread {
	private JLabel n;
	private int op;
	
	/**
	 * Construtor
	 * @param n é a label que mostrará o número a ser sorteado
	 * @param op significa qual label iniciará a contagem aleatória após o botão ser acionado 
	 */
	public ThreadMaquina(JLabel n, int op) {
		this.n = n;
		this.op = op;
	}
	
	/**
	 * método run que efetua achamada do método executa sem a passagem de parâmetros
	 */
	
	@Override
	public void run() {
		executa();
	}
	
	/**
	 * método executa que contém um switch case para analisar op e repassar os parâmetros
	 * necessários para o método numero
	 */
	
	public void executa() {
		switch(op) {
		case 0: 
			numero(op);
			break;
		case 1:
			numero(op);
			break;
		case 2:
			numero(op);
			break;
		}
	}
	
	/**
	 * Método número que fara o sorteio aleatório dos números  
	 * @param op recebe o número da opção do switch case 
	 * a fim de estipular um limite no for
	 */
	
	public void numero(int op) {
		int numero;
		String num;
		int valor = 150;
		Random random = new Random();
                numero = (op+1) * valor; 
		
		for(int i = 1; i < valor; i++) {
			numero = random.nextInt(7);
			num = ""+numero;
			n.setText(num);
		}
                
	}
        
}

