package br.com.kalilstudios.hud;

import br.com.kalilstudios.janela.RenderElement;
import br.com.kalilstudios.jogador.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static br.com.kalilstudios.main.Main.janela;
import static br.com.kalilstudios.main.Main.jogador;
import static br.com.kalilstudios.utils.Const.*;
import static br.com.kalilstudios.utils.Utils.bigIntToString;
import static java.awt.Color.*;
import static java.lang.String.format;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que desenha a Hud
 */
public class Hud implements RenderElement {
	
	// adiciona o EventoResetGame a janela
	public Hud() {
		janela.addMouseListener(new EventoResetGame());
	}

	// retorna o level que esse elemento sera renderizado
	@Override
	public Integer getRenderLevel() {
		return HALF;
	}
	
	// renderiza o Hud
	@Override
	public void render(Graphics g) {
		drawHud(g);
		drawStringBox(g);
		drawStrings(g);
		drawResetGame(g);
	}

	// determina se o elemento e visivel ou não
	@Override
	public boolean isVisible() {
		return true;
	}

	/** 
	* desenha as Strings do jogador
	* Dinheiro 
	* level 
	* GPC
	* GPS
	*/
	private void drawStrings(Graphics g) {
		
		// define cor e tamanho da String
		g.setColor(WHITE);
		g.setFont(defautFont50);
		
		// desenha o Dinheiro do jogador
		g.drawString(format("$%s", bigIntToString(jogador.getDinheiro())), 1020, 53);

		// define tamanho da String
		g.setFont(defautFont30);
		
		// desenha o level, Ganho Por Click e o Ganho Por Segundo
		g.drawString(format("level: %s", bigIntToString(jogador.getLevel())), 1020, 106);
		g.drawString(format("GPC: %s", bigIntToString(jogador.getGanhoPorClick())), 1020, 156);
		g.drawString(format("GPS: %s", bigIntToString(jogador.getGanhoPorSegundo())), 1020, 206);
	}

	// desenha os retangulos das Strings
	private void drawStringBox(Graphics g) {
		g.setColor(LIGHT_GRAY);
		g.fillRoundRect(1005, 5, 254, 60, 40, 40);
		for (int i = 0; i < 3; i++)
			g.fillRoundRect(1005, 75 + 50 * i, 254, 40, 30, 30);

		g.setColor(LIGHT_GRAY_DARKER);
		g.fillRoundRect(1010, 10, 244, 50, 40, 40);
		for (int i = 0; i < 3; i++)
			g.fillRoundRect(1010, 80 + 50 * i, 244, 30, 30, 30);
	}

	// desenha 2 retangulos do HUD
	private void drawHud(Graphics g) {
		g.setColor(DARK_GRAY);
		g.fillRect(1000, 0, 264, 681);
		g.fillRect(0, 500, 1000, 181);
	}
	
	// desenha a String "Reset game"
	private void drawResetGame(Graphics g) {
		g.setFont(defautFont12);
		g.setColor(LIGHT_GRAY_DARKER);
		g.drawString("Reset game", 132, 677);
	}

	private class EventoResetGame implements MouseListener{
	
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// obtem as coordenadas X Y do click
			int x = e.getX();
			int y = e.getY();
			ResetEvent(e, x, y);
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

		// evento disparado ao clickar no Reset Game
		private void ResetEvent(MouseEvent e, int x, int y) {
			
			// verifica se o click e valido e realiza a acao
			if (y > 697 && y < 715 && x > 140 && x < 208 && e.getButton() == 1) {
				
				// obtem a resposta do usuario
				int option = JOptionPane.showConfirmDialog(janela,
						"\r\nIncluindo o seu prestigio e seus boosts\r\nNão sera possivel recuperar o save", "reiniciar o jogo?",
						YES_NO_OPTION);
				
				// caso a resposta seja sim reinicia o jogo
				// e dispara o evento para que a coluna visivel seja a primeira
				if (option == YES_OPTION) {
					jogador = new Jogador();
					janela.dispatchEvent();
					
					// pede para a janela renderizar um novo quadro
					janela.renderGraphics.novoQuadro++;
				}
			}
		}
	}
}
