package br.com.kalilstudios.janela;

import br.com.kalilstudios.main.Main;

import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static br.com.kalilstudios.main.Main.jogador;
import static br.com.kalilstudios.utils.Const.RESOLUTIONPANEL;
import static br.com.kalilstudios.utils.Const.defautFont30;
import static java.awt.Color.BLACK;
import static java.math.BigInteger.ONE;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * panel onde o jogo e desenhado
 *
 */
public class RenderGraphics extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// RenderingHints que representa qualidade grafica 
	public RenderingHints hints = RenderPreConfigs.getConfig(jogador.config.graphicsQuality);
	
	// lista com todos os elementos a serem renderizados
	private static final List<RenderElement> RenderList = new LinkedList<RenderElement>();

	// controla a taxa de quadros chamando o paintImmediately() com certo intervalo
	private final FPSControl fpsControl = new FPSControl(() -> this.paintImmediately(0, 0, 1264,681));
	
	// dinheiro desde a ultima atualizacao
	private BigInteger dinheiroAntigo = ONE;
	
	// determina se um novo quadro tem que ser gerado
	public int novoQuadro = 0;
	
	// construtor que inicia o panel
	public RenderGraphics() {
		
		// inicia o controle de fps
		fpsControl.start();

		// configura o JPanel
		setSize(RESOLUTIONPANEL);
		setVisible(true);
		setLayout(null);
	}

	// pede para renderizar o jogo
	@Override
	public void paintImmediately(int x, int y, int w, int h) {
		
		// verifica se é nessesario renderizar novamente
		if (novoQuadro > 0 || !dinheiroAntigo.equals(jogador.getDinheiro())) {
			super.paintImmediately(x, y, w, h);
			
			// atualiza o dinheiro Antigo
			dinheiroAntigo = Main.jogador.getDinheiro();

			if (novoQuadro > 0) {
				
				// subtrai do novoQuadro
				novoQuadro--;
			}
		}
	}
	
	// renderiza o jogo
	@Override
	protected void paintComponent(Graphics g) {
		
			// cria um Graphics2D g2d a partir do Graphics g
			Graphics2D g2d = (Graphics2D) g;

			// altera a qualidade grafica do jogo
			g2d.setRenderingHints(hints);

			// renderiza todos os elementos da RenderList a menos que o isVisible seja falso
			RenderList.forEach(r -> { if (r.isVisible()) r.render(g2d);});

			// desenha o fps na tela
			drawFps(g2d);
	}
	
	// adiciona elementos no RenderList para serem renderizados
	public static void addElement(RenderElement... RE) {
		
		// adiciona todos os elementos passados na (RenderElement... RE)
		RenderList.addAll(Arrays.asList(RE));
		
		// organiza os elementos
		sortElements();
	}

	// remove um elemento do RenderList
	public static void removeElement(RenderElement RE) {
		RenderList.remove(RE);
	}

	// organiza os elementos da lista RenderList
	public static void sortElements() {
		// com base no getRenderLevel organiza o lista quanto 
		// maior, mais para frente o elemento estara na lista
		RenderList.sort((R1, R2) -> R1.getRenderLevel().compareTo(R2.getRenderLevel()));
	}

	// desenha a taxa de fps na tela
	private void drawFps(Graphics g) {
		g.setColor(BLACK);
		g.setFont(defautFont30);
		// desenha a String no panel
		g.drawString(fpsControl.toString(), 0, 679);
	}
}
