package br.com.kalilstudios.hud;

import br.com.kalilstudios.janela.RenderElement;
import br.com.kalilstudios.utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

import static br.com.kalilstudios.main.Main.janela;
import static br.com.kalilstudios.main.Main.jogador;
import static br.com.kalilstudios.utils.Const.*;
import static br.com.kalilstudios.utils.Utils.getImage;
import static java.awt.Color.BLACK;
import static java.awt.Color.LIGHT_GRAY;
import static java.awt.event.MouseEvent.BUTTON1;
import static java.lang.String.format;
import static java.math.BigDecimal.TEN;
import static java.math.RoundingMode.DOWN;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que desenha a Hud do boost auto click
 */
public class HudBoostAutoClick implements RenderElement {

	// imagem de uma mira vermelha
	private final Image miraAtivaImage = getImage("/br/com/kalilstudios/resource/mira ativada.png", 80, 80);

	// imagem de uma mira cinza
	private final Image miraDesativadoImage = getImage("/br/com/kalilstudios/resource/mira desativada.png", 80, 80);

	double porcentagem = 0;

	// adiciona o AtivarDesativarEvent e EventoMoverMouse a janela
	public HudBoostAutoClick() {
		janela.addKeyListener(new EventoAtivarDesativar());
		janela.addMouseMotionListener(new EventoMoverMouse());

		// inicia o autoClick
		new ThreadAutoClick();
	}

	// retorna o level que esse elemento sera renderizado
	@Override
	public Integer getRenderLevel() {
		return TOP;
	}

	// renderiza o Hud Auto Click
	@Override
	public void render(Graphics g) {
		drawAutoClickStatus(g);
		drawTarger(g);
	}

	// determina se o elemento e visivel ou não
	@Override
	public boolean isVisible() {
		return true;
	}

	// desenha a imagem da mira
	private void drawTarger(Graphics g) {
		
		// so desenha a imagem caso o autoClickLevel seja maior que 0
		if (jogador.boost.autoClickLevel > 0) {
			int x = jogador.boost.autoClickPosition.x - 48;
			int y = jogador.boost.autoClickPosition.y - 72;
			
			// se o auto click estiver ativo desenha a mira vermelha caso contrario mira cinza
			g.drawImage(jogador.boost.autoClickActive ? miraAtivaImage : miraDesativadoImage, x, y, null);
		}
	}

	// desenha os status do auto click
	private void drawAutoClickStatus(Graphics g) {
		
		// atualiza a porcentagem
		porcentagem = getPorcentagem();

		// desenha a hud dos status
		g.setColor(LIGHT_GRAY);
		g.fillRoundRect(829, 505, 171, 171, 10, 10);
		g.setColor(BLUE_BRIGHTER);
		g.fillRoundRect(834, 671 - (int) (161 * porcentagem / 100), 161, (int) (161 * porcentagem / 100), 10, 10);
		g.setColor(BLACK);
		g.setFont(defautFont25);
		g.drawString("Auto Click", 853, 532);
		g.setFont(defautFont15);
		if (jogador.boost.autoClickLevel == 10) {
			g.drawString("Nivel: MAX", 885, 570);
		} else {
			g.drawString(format("Nivel: %2d", jogador.boost.autoClickLevel), 885, 570);
		}
		if (jogador.boost.autoClickLevel > 0) {
			g.drawString(format("delay: %3d", getDelay()), 878, 600);
		} else {
			g.drawString("delay: ???", 878, 600);
		}
		g.drawString(format("progresso: %3d%%", (int) Math.floor(porcentagem)), 860, 630);
		g.drawString("F8: on/off F7: reset", 850, 660);
	}

	// calcula o delay
	private int getDelay() {
		return 550 - jogador.boost.autoClickLevel * 50;
	}

	// calcula a porcentagem
	private int getPorcentagem() {
		
		// pega os valores click Count e auto Click Level
		long clickCount = jogador.achievements.clickCount;
		byte autoClickLevel = jogador.boost.autoClickLevel;

		// calcula o resultado da porcentagem
		BigDecimal resultado = BigDecimal.valueOf(clickCount).multiply(TEN.pow(2))
				.divide(BigDecimal.valueOf(1000 * Math.pow(autoClickLevel + 1, 2)), DOWN);

		// retorna o resultado
		return Math.min(resultado.intValueExact(), 100);
	}

	private class ThreadAutoClick extends Thread {

		public ThreadAutoClick() {
			start();
		}

		@Override
		public void run() {
			
			// loop do auto click
			while (true) {

				// dispara o evento caso o auto click esteja true e o nivel do auto click seja maior que 0
				if (jogador.boost.autoClickActive && jogador.boost.autoClickLevel > 0) {
					dispatchEvent();
				}

				// caso a porcemtagem seja 100% adiciona 1 ao autoClickLevel com limite de 10
				if (jogador.boost.autoClickLevel < 10 && porcentagem >= 100) {
					jogador.boost.autoClickLevel++;

					// pede para a janela renderizar mais um quadro
					janela.renderGraphics.novoQuadro++;
				}

				// espera o tempo do get Delay
				Utils.sleep(getDelay(), MILLISECONDS);
			}
		}

		// dispara um evento que ecoa todos os mouseListener
		private void dispatchEvent() {
			// obtem o x y do auto Click
			int x = jogador.boost.autoClickPosition.x;
			int y = jogador.boost.autoClickPosition.y;
			
			// cria o MouseEvent com os parametros
			MouseEvent defaultMouseEvent = new MouseEvent(janela, 0, 0, 0, x, y, 1, false, BUTTON1);

			// dispara o evento em todos os mouseListener
			for (MouseListener mouseListener : janela.getMouseListeners()) {
				mouseListener.mousePressed(defaultMouseEvent);
			}
		}
	}

	private class EventoAtivarDesativar implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		// evento disparado ao clicar F8 e F7
		// F8 ativa o auto click 
		// F7 reseta a possicao
		@Override
		public void keyPressed(KeyEvent e) {

			// verifica se o o level e maior que 0
			if (jogador.boost.autoClickLevel > 0) {
				
				// F8
				if (e.getKeyCode() == KeyEvent.VK_F8) {
					jogador.boost.autoClickActive = !jogador.boost.autoClickActive;
					janela.renderGraphics.novoQuadro++;
				}

				// F7
				if (e.getKeyCode() == KeyEvent.VK_F7) {
					jogador.boost.autoClickPosition = new Point(506, 506);
					jogador.boost.autoClickActive = false;
					janela.renderGraphics.novoQuadro++;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

	private class EventoMoverMouse implements MouseMotionListener {

		// evento disparado ao mover a mira
		@Override
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			// verifica se as coordenadas sao a da mira caso seja move a mira
			if (jogador.boost.autoClickLevel > 0 && x > jogador.boost.autoClickPosition.x - 40
					&& x < jogador.boost.autoClickPosition.x + 40 && y > jogador.boost.autoClickPosition.y - 40
					&& y < jogador.boost.autoClickPosition.y + 40) {
				jogador.boost.autoClickPosition.x = e.getX();
				jogador.boost.autoClickPosition.y = e.getY();
				janela.renderGraphics.novoQuadro = 2;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}
	}
}
