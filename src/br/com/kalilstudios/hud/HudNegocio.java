package br.com.kalilstudios.hud;

import br.com.kalilstudios.janela.RenderElement;
import br.com.kalilstudios.jogador.Negocios;
import br.com.kalilstudios.main.Main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;

import static br.com.kalilstudios.main.Main.janela;
import static br.com.kalilstudios.main.Main.jogador;
import static br.com.kalilstudios.utils.Const.*;
import static br.com.kalilstudios.utils.Utils.*;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.LIGHT_GRAY;
import static java.lang.String.format;
import static java.math.BigInteger.ONE;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que desenha a Hud Negocio
 */
public class HudNegocio implements RenderElement {

	// imagem de uma seta verde para a esquerda
	private final Image arrowLeft = getImage("/br/com/kalilstudios/resource/arrow left.png", 70, 40);
	
	// imagem de uma seta verde para a direita
	private final Image arrowRigth = getImage("/br/com/kalilstudios/resource/arrow rigth.png", 70, 40);
	
	// imagem de 30 negocios diferentes
	private final Image[] negociosImage = getAllNegociosImage();
	
	// modifica a possicao da hud
	private int modX = -495 * jogador.config.column;
	
	// booleano para debugar os negocios
	private static final boolean debug = false;
	
	// adiciona o EventoMudarPaginaEComprarNegocios a janela
	public HudNegocio() {
		Main.janela.addMouseListener(new EventoMudarPaginaEComprarNegocios());
	}
	
	// retorna o level que esse elemento sera renderizado
	@Override
	public Integer getRenderLevel() {
		return BELOW;
	}

	// renderiza o Hud Negocio
	@Override
	public void render(Graphics g) {
		g.setColor(LIGHT_GRAY_DARKER);
		g.fillRect(0, 0, 1000, 500);
		drawColumn(g);
		drawArrows(g);
	}

	// determina se o elemento e visivel ou não
	@Override
	public boolean isVisible() {
		return true;
	}

	// desenha as colunas 
	private void drawColumn(Graphics g) {
		
		// obtem o dinheiro do jogador
		BigInteger dinheiro = jogador.getDinheiro();
		
		// for duplo com 2 variaveis que desenha as colunas
		for (int c = 0, i = 0; c < 6; c += ++i < 5 ? 0 : 1, i = i < 5 ? i : 0) {
			
			// verifica se a coluna e visivel caso seja e renderizado
			if (modX > -495 * (c + 1) && modX < (c <= 1 ? 1 : -495 * (c - 2))) {
				
				// obtem o negocio a ser renderizado
				Negocios n = jogador.negocios.get(i + 5 * c);
				
				// verifica se e possivel o negocio ou se o jogador ja o possui sera renderizado
				if (debug || n.getLevel().compareTo(ONE) > -1 || dinheiro.compareTo(n.getPreco()) > -1 || (i | c) == 0) {
					
					// simula o custo do negocio com base no jogador.userConfig.buylot
					BigInteger custoSimulado = n.simularCusto(jogador.config.buylot);
					
					// renderiza o negocio
					drawBackGroundImage(g, c, i);
					drawName(g, c, i, n, dinheiro, custoSimulado);
					drawLevelCustoGanho(g, c, i, n, custoSimulado);	
				}
			}
		}
	}

	// desenha o nome do negocio n caso tenha dinheiro desenha de GREEN_DARKER caso contrario DARK_GRAY
	private void drawName(Graphics g, int c, int i, Negocios n, BigInteger dinheiro, BigInteger custoSimulado) {
		g.setFont(defautFont30);
		g.setColor(dinheiro.compareTo(custoSimulado) > -1 ? GREEN_DARKER : DARK_GRAY);
		g.drawString(n.getName(), 100 + 495 * c + modX, 35 + 89 * i);
	}

	// desenha o retangulo atras da imagem e denha a imagem
	private void drawBackGroundImage(Graphics g, int c, int i) {
		g.setColor(LIGHT_GRAY);
		g.fillRoundRect(10 + 495 * c + modX, 10 + 89 * i, 485, 84, 10, 10);
		try {g.drawImage(negociosImage[i + 5 * c], 20 + 495 * c + modX, 15 + 89 * i, null);} catch (Exception e) {}
	}

	// desenha as Strings levelFormatado, CustoFormatado, Ganho e levelCusto
	private void drawLevelCustoGanho(Graphics g, int c, int i, Negocios n , BigInteger custoSimulado) {
		g.setFont(defautFont25);
		
		// gera as Strings 
		String levelFormatado = bigIntToString(n.getLevel());
		String CustoFormatado = bigIntToString(custoSimulado);
		String Ganho = format("Ganhos: %7s", bigIntToString(n.getGanho().multiply(jogador.getMultiPrestigio())));
		String levelCusto = format("Level: %7s   Custo: %7s", levelFormatado, CustoFormatado);
		
		// desenha as Strings
		g.drawString(levelCusto, 100 + 495 * c + modX, 60 + 89 * i);
		g.drawString(Ganho, 100 + 495 * c + modX, 88 + 89 * i);
	}

	// desenhas ambas as setas
	private void drawArrows(Graphics g) {
		g.drawImage(arrowLeft, 0, 455, null);
		g.drawImage(arrowRigth, 930, 455, null);
	}

	// retorna todas as 30 do negocios
	public Image[] getAllNegociosImage() {
		
		// array de Image que sera retornado
		Image[] images = new Image[30];
		
		// caminho padrao ate as imagems
		String path = "/br/com/kalilstudios/resource/images negocios/";
		
		// nome de cada imagem
		String[] names = { "barraca limonada.png", "pastelaria.png", "mercado.png", "shopping.png", "cinema.png",
				"lojas americanas.png", "starbucks.png", "mcdonalds.png", "burgerking.png", "outback.png",
				"rockstar.png", "ubisoft.png", "eagames.png", "epicgames.png", "steam.png", "disney.png", "netflix.png",
				"nintendo.png", "microsoft.png", "intel.png", "amd.png", "nvidia.png", "tesla.png", "volkswagen.png",
				"volvo.png", "toyota.png", "ferrari.png", "apple.png", "wallmart.png", "amazon.png" };

		// adiciona todas as imagems no array
		for (int i = 0; i < names.length; i++) {
			images[i] = getImage(path + names[i], 74, 74);
		}
		
		// retorna as imagens
		return images;
	}

	private class EventoMudarPaginaEComprarNegocios implements MouseListener {
		
		// MouseListener
		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			columnClickEvent(x, y, e);
			negocioUpgrade(x, y, e);
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

		Thread columnAnimation;
		private void columnClickEvent(int x, int y, MouseEvent e) {
			if (y > 485 && y < 486 + 40)
				if (x > 16 && x < 70) {
					if (jogador.config.column > 0 && e.getButton() == 1)
						jogador.config.column--;

				} else if (x > 946 && x < 70 + 930)
					if (jogador.config.column < 4 && e.getButton() == 1)
						jogador.config.column++;
			
			if(modX != -495 * jogador.config.column && columnAnimation == null) {
				columnAnimation = new Thread(() -> {
					while (modX != -495 * jogador.config.column) {
						modX = modX > -495 * jogador.config.column ? modX - 15 : (modX < -495 * jogador.config.column ? modX + 15 : modX);
						
						// pede para a janela renderizar mais um quadro
						janela.renderGraphics.novoQuadro++;
						
						sleep(10, MILLISECONDS);
					}
					columnAnimation = null;
				});
				columnAnimation.start();
			}
		}

		private void negocioUpgrade(int x, int y, MouseEvent e) {
			BigInteger dinheiro = jogador.getDinheiro();
			for (int k = 0, i = 0; k < 2; k += ++i < 5 ? 0 : 1, i = i < 5 ? i : 0) 
					if (y > 40 + 89 * i && y < 125 + 89 * i && x > 15 + 495 * k && x < 503 + 495 * k && e.getButton() == 1) {
						Negocios negocio = jogador.negocios.get(i + 5 * k + 5 * jogador.config.column);
						BigInteger custoSimulado = negocio.simularCusto(jogador.config.buylot);
						if (dinheiro.compareTo(custoSimulado) > -1) {
							jogador.setDinheiro(dinheiro.add(custoSimulado.negate()));
							negocio.setLevel(negocio.getLevel().add(BigInteger.valueOf(jogador.config.buylot)));
							k = 2;
							break;
						}
					}
		}
	}
}
