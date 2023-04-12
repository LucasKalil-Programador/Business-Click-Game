package br.com.kalilstudios.jogador;

import java.io.Serializable;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que representa a Config do usuario
 *
 */
public class Configs implements Serializable {

	// numero do implements Serializable usado para serializar o objeto
	private static final long serialVersionUID = 1L;

	// representa a compra em grande escala {x1, x10, x100, x1000}
	public int buylot = 1;
	// representa a coluna que o jogador se encontra
	public int column = 0;

	// represent a qualidade grafica do jogador
	public String graphicsQuality = "HIGH";
}
