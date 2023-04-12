package br.com.kalilstudios.janela;

import java.awt.*;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que representa um elemento grafico renderizavel
 *
 */
public interface RenderElement {
	
	// metodo que contem o level quanto maior o level mais acima e renderizado
	Integer getRenderLevel();
	
	// metodo chamado todo quadro para ser renderizado
	void render(Graphics g);
	
	// metodo que decide se o elemento sera renderizado ou nao
	boolean isVisible();
}
