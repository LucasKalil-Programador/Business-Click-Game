package br.com.kalilstudios.utils;

import java.awt.*;
import java.io.File;
import java.math.BigInteger;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;
import static java.awt.Font.DIALOG;
import static java.io.File.separator;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * classe contem constantes ultilizadas no jogo
 *
 */
public abstract class Const {
	
	// dimensoes padroes
	public static final Dimension RESOLUTIONPANEL = new Dimension(1264, 681);
	public static final Dimension RESOLUTION = new Dimension(1280, 720);
	
	// fontes usadas para escrever Strings
	public static final Font defautFont50 = new Font(DIALOG, BOLD, 50);
	public static final Font defautFont40 = new Font(DIALOG, BOLD, 40);
	public static final Font defautFont30 = new Font(DIALOG, BOLD, 30);
	public static final Font defautFont25 = new Font(DIALOG, BOLD, 25);
	public static final Font defautFont15 = new Font(DIALOG, BOLD, 15);
	public static final Font defautFont12 = new Font(DIALOG, BOLD, 12);
	
	// cores extras usadas para desenhar o jogo
	public static final Color ORANGE_BRIGHTER = new Color(255, 140, 0);
	public static final Color LIGHT_GRAY_DARKER = LIGHT_GRAY.darker();
	public static final Color BLUE_BRIGHTER = new Color(90, 90, 255);
	public static final Color ORANGE_DARKER = ORANGE.darker();
	public static final Color GREEN_DARKER = GREEN.darker();
	
	// inteiros usados pelo RenderGraphics.getRenderLevel
	public static final Integer TOP   = 10;
	public static final Integer HALF  =  5;
	public static final Integer BELOW =  0;
	
	// local dos arquivos para carregar e salvar o save do jogo
	public static final File DOCUMENTS = new File(System.getProperty("user.home"));
	public static final File KALILGAMES = new File(DOCUMENTS + separator + "Kalil Studios");
	public static final File JOGADORSAVE = new File(KALILGAMES + separator + "Business Click Game Save.bin");

	// bigInteger 
	public static final BigInteger TWO = BigInteger.valueOf(2);
}

