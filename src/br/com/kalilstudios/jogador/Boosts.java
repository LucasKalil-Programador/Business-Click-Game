package br.com.kalilstudios.jogador;

import java.awt.*;
import java.io.Serializable;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que representa os boosts que o usuario pode comprar
 *
 */
public class Boosts implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// nivel do boost auto click
	public byte autoClickLevel = 0;
	
	// representa a possicao atual do auto click
	public Point autoClickPosition = new Point(506, 506);
	
	// representa se o auto click esta ativo
	public boolean autoClickActive = false;
	
}
