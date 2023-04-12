package br.com.kalilstudios.hud;

import br.com.kalilstudios.janela.RenderElement;
import br.com.kalilstudios.jogador.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;

import static br.com.kalilstudios.main.Main.*;
import static br.com.kalilstudios.utils.Const.*;
import static br.com.kalilstudios.utils.Utils.bigIntToString;
import static java.awt.Color.LIGHT_GRAY;
import static java.awt.Color.YELLOW;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que desenha a Hud Prestigio
 */
public class HudPrestigio implements RenderElement {

	// adiciona o EventoPrestigio a janela
	public HudPrestigio() {
		janela.addMouseListener(new EventoPrestigio());
	}
	
	// representa se e da para comprar o prestigio
	private boolean canBuy = false;

	// retorna o level que esse elemento sera renderizado
	@Override
	public Integer getRenderLevel() {
		return TOP;
	}

	// renderiza o HudPrestigio
	@Override
	public void render(Graphics g) {
		g.setColor(ORANGE_DARKER);
		g.fillRoundRect(1005, 225, 254, 185, 40, 40);

		g.setColor(ORANGE_BRIGHTER);
		g.fillRoundRect(1010, 355, 244, 50, 40, 40);

		g.setColor(jogador.getDinheiro().compareTo(jogador.getCustoPrestigio()) > -1 ? YELLOW : LIGHT_GRAY);
		g.setFont(defautFont40);
		g.drawString("Prestigio", 1045, 265);

		g.setFont(defautFont15);
		g.drawString("Ao usar o prestigio você reinicia", 1015, 300);
		g.drawString("seu progresso em troca de 2x do", 1014, 320);
		g.drawString("do seu ganho em negocios e click", 1011, 340);

		g.setFont(defautFont30);
		g.drawString(String.format("Custo: %7s", bigIntToString(jogador.getCustoPrestigio())), 1031, 390);
	}

	// determina se o elemento e visivel ou não
	@Override
	public boolean isVisible() {
		if (!canBuy)
			canBuy = jogador.getDinheiro().compareTo(jogador.getCustoPrestigio()) > -1;
		return canBuy;
	}

	private class EventoPrestigio implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			resetGameEvent(e, x, y);
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	
		// evento disparado ao clicar no Prestigio
		private void resetGameEvent(MouseEvent e, int x, int y) {
			
			// verifica se o click e valido
			if (x > 1017 && x < 1262 && y > 385 && y < 435 && e.getButton() == 1
			&& jogador.getDinheiro().compareTo(jogador.getCustoPrestigio()) > -1) {
				
				// cria a string mensagem
				StringBuilder sb = new StringBuilder();
				sb.append("Isso ira reiniciar seu progresso\r\n");
				sb.append("seu multiplicador atual é ");
				sb.append(bigIntToString(jogador.getMultiPrestigio().multiply(BigInteger.valueOf(100))));
				sb.append("%\r\n");
				sb.append("e passara a ser ");
				sb.append(bigIntToString(jogador.getMultiPrestigio().multiply(TWO).multiply(BigInteger.valueOf(100))));
				sb.append("%");
				
				// cria uma nova thread para mostrar a mensagem
				new Thread(() -> {
					if(JOptionPane.showConfirmDialog(janela, sb, "Tem certeza?", YES_NO_OPTION) == 0) {
						
						// cria um novo jogador
						Jogador novoJogador = new Jogador(jogador.getMultiPrestigio().multiply(TWO));
						
						// adiciona o achievements ao novo jogador
						novoJogador.achievements = jogador.achievements;
						
						// adiciona a config ao novo jogador
						novoJogador.config = jogador.config;
						
						// adiciona o boost ao novo jogador
						novoJogador.boost = jogador.boost;
						
						// define column para 0
						novoJogador.config.column = 0;

						// atribui o novoJogador ao jogador
						jogador = novoJogador;
						
						// define canBuy para false
						canBuy = false;
						
						// salva o novo jogador
						saveJogador();
						
						// dispara um evento que reseta a coluna
						janela.dispatchEvent();
					}
				}).start();
			}
		}
	}
}
