package br.com.kalilstudios.jogador;

import java.io.Serializable;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que representa as conquistas do jogador
 *
 */
public class Achievements implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// representa o numero de clicks que o usuario ja deu
	public long clickCount = 0;

}
