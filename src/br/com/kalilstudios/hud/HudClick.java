package br.com.kalilstudios.hud;

import br.com.kalilstudios.janela.RenderElement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;

import static br.com.kalilstudios.main.Main.janela;
import static br.com.kalilstudios.main.Main.jogador;
import static br.com.kalilstudios.utils.Const.*;
import static br.com.kalilstudios.utils.Utils.bigIntToString;
import static br.com.kalilstudios.utils.Utils.getImage;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;
import static java.lang.String.format;
import static java.math.BigInteger.ONE;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que desenha a Hud de Click
 */
public class HudClick implements RenderElement {

		// imagem do dinheiro onde pode ser clickado para ganhar dinheiro
		private final Image dinheiro = getImage("/br/com/kalilstudios/resource/dinheiro.png", 394, 191);
		
		// imagem de uma seta verde que representa possibilidade de upGrade
		private final Image upGrade = getImage("/br/com/kalilstudios/resource/upgrade.png", 60, 60);

		// imagem de uma seta cinza que representa nao tem possibilidade de upGrade
		private final Image upGradeFail = getImage("/br/com/kalilstudios/resource/upgradefail.png", 60, 60);

	
	// adiciona o EventoGanhoClick a janela
	public HudClick() {
		janela.addMouseListener(new EventoGanhoClick());
	}
	
	// retorna o level que esse elemento sera renderizado
	@Override
	public Integer getRenderLevel() {
		return TOP;
	}

	// renderiza o Hud Click
	@Override
	public void render(Graphics g) {
		drawMoneyButton(g);
		drawUpGradeClick(g);
	}

	// determina se o elemento e visivel ou não
	@Override
	public boolean isVisible() {
		return true;
	}

	// renderiza o botao para upGrade
	private void drawUpGradeClick(Graphics g) {
		
		// obtem o custo de melhoria
		BigInteger custo = jogador.getPrecoMelhoria();
		
		// determina se pode comprar ou nao 
		boolean canBuy = jogador.getDinheiro().compareTo(custo) > -1;

		// desenha a cor de fundo do botao upGrade
		g.setColor(LIGHT_GRAY);
		g.fillRoundRect(1005, 420, 254, 80, 30, 30);

		// caso for possivel comprar o upGrade sera denhado 
		// uma seta verde e com as Strings em verde 
		// caso contrario
		// uma seta cinza e com as Strings em verde cinza
		g.setColor(canBuy ? GREEN_DARKER : DARK_GRAY);
		g.drawImage(canBuy ? upGrade : upGradeFail, 1010, 432, null);
		
		// desenha o level e o custo no botao
		g.setFont(defautFont25);
		g.drawString(format("Level: %s", bigIntToString(jogador.getLevel().add(ONE))), 1070, 455);
		g.drawString(format("Custo: %s", bigIntToString(custo)), 1070, 485);
	}

	// renderiza a imagem do dinheiro
	private void drawMoneyButton(Graphics g) {
		g.drawImage(dinheiro, 935, 496, null);
	}

	private class EventoGanhoClick implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			clickEvent(e, x, y);
			upgradeEvent(e, x, y);
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

		// evento disparado ao clickar na imagem do dinheiro
		private void clickEvent(MouseEvent e, int x, int y) {
			
			// verifica as coordenadas se estivem dentro do esperado realiza a acao
			if (x > 1016 && x < 1263 && y > 537 && y < 708 && e.getButton() == 1) {
				jogador.setDinheiro(jogador.getDinheiro().add(jogador.getGanhoPorClick()));
				
				// adiciona 1 ao click count do achievements
				jogador.achievements.clickCount++;
			}
		}

		// evento disparado ao clicar no botao upGrade
		private void upgradeEvent(MouseEvent e,int x, int y) {
			
			// verifica as coordenadas se estivem dentro do esperado realiza a acao
			if (x > 1011 && x < 1267 && y > 449 && y < 531 && e.getButton() == 1) {
				
				// obtem quantos levels serao comprados
				int buylot = jogador.config.buylot;
				
				// tenta comprar a quantidade de levels do buylot caso nao tenha dinheiro para o loop
				for (int i = 0; i < buylot && jogador.getDinheiro().compareTo(jogador.getPrecoMelhoria()) > -1; i++) {
					jogador.setDinheiro(jogador.getDinheiro().add(jogador.getPrecoMelhoria().negate()));
					jogador.setLevel(jogador.getLevel().add(ONE));
				}
			}
		}
	}
}
