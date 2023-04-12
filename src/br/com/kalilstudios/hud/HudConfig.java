package br.com.kalilstudios.hud;

import br.com.kalilstudios.janela.RenderElement;
import br.com.kalilstudios.janela.RenderPreConfigs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static br.com.kalilstudios.main.Main.janela;
import static br.com.kalilstudios.main.Main.jogador;
import static br.com.kalilstudios.utils.Const.*;
import static java.awt.Color.*;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que desenha a Hud Config
 */
public class HudConfig implements RenderElement {

	// adiciona o EventoConfiguracao a janela
	public HudConfig() {
		janela.addMouseListener(new EventoConfiguracao());
	}
	
	// possiveis a configuracao de grafico
	private final String[] possibleConfigs = { "HIGH", "MEDIUM", "LOW" };
	
	// retorna o level que esse elemento sera renderizado
	@Override
	public Integer getRenderLevel() {
		return TOP;
	}

	// renderiza o Hud Config
	@Override
	public void render(Graphics g) {
		drawGraphicsConfig(g);
		drawBuyLot(g);
	}

	// determina se o elemento e visivel ou não
	@Override
	public boolean isVisible() {
		return true;
	}

	// desenha o Buy Lot
	private void drawBuyLot(Graphics g) {
		
		// desenha a string
		g.setFont(defautFont25);
		g.setColor(DARK_GRAY);
		g.drawString("x1         x10       x100     x1000", 338, 496);
	
		// desenha os circulos 
		for (int i = 0; i < 4; i++) {
			g.setColor(LIGHT_GRAY);
			g.fillArc(340 + 100 * i, 455, 20, 20, 0, 360);
			
			// verifica a configuracao atual
			if (jogador.config.buylot == (int) Math.pow(10, i)) {
				g.setColor(BLACK);
				g.fillArc(343 + 100 * i, 458, 14, 14, 0, 360);
			}
		}
	}
	
	// desenha as configuracoes graficas
	private void drawGraphicsConfig(Graphics g) {
		
		// desenha a string do graficos
		g.setColor(BLACK);
		g.setFont(defautFont15);
		g.drawString("Configuraçoes graficas", 5, 515);
		g.drawString("Alta", 35, 545);
		g.drawString("Medio", 35, 590);
		g.drawString("Baixo", 35, 635);
		
		// desenha os circulos da configuracao graficas
		for (int i = 0; i < 3; i++) {
			g.setColor(LIGHT_GRAY);
			g.fillArc(5, 530 + 45 * i, 20, 20, 0, 360);
			
			// verifica a configuracao atual
			if (possibleConfigs[i].equals(jogador.config.graphicsQuality)) {
				g.setColor(BLACK);
				g.fillArc(8, 533 + 45 * i, 14, 14, 0, 360);
			}
		}
	}
	
	private class EventoConfiguracao implements MouseListener {
	
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			graphicsEvent(e, x, y);
			buyLotEvent(e, x, y);
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

		// evento disparado ao clicar para alterar o grafico
		private void graphicsEvent(MouseEvent e, int x, int y) {
			
			// verifica qual o botao foi pressionado
			for (int i = 0; i < possibleConfigs.length; i++) {
				if (x > 10 && x < 33 && y > 558 + 45 * i && y < 581 + 45 * i && e.getButton() == 1) {
					
					// se a nova configuracao
					jogador.config.graphicsQuality = possibleConfigs[i];
					janela.renderGraphics.hints = RenderPreConfigs.getConfig(jogador.config.graphicsQuality);
					
					// pede para a jenela renderizar mais um novo quadro
					janela.renderGraphics.novoQuadro++;
					break;
				}
			}
		}

		// evento disparado ao clicar para alterar o buylot
		private void buyLotEvent(MouseEvent e, int x, int y) {
			
			// verifica qual botao foi pressionado
			for (int i = 0; i < 4; i++) {
				if (x > 346 + 100 * i && x < 369 + 100 * i && y > 485 && y < 506 && e.getButton() == 1) {
					
					// seta a nova configuracao buylot
					jogador.config.buylot = (int) Math.pow(10, i);
					
					// pede para a jenela renderizar um 30 novos quadros
					janela.renderGraphics.novoQuadro++;
				}
			}
		}
	}
}
