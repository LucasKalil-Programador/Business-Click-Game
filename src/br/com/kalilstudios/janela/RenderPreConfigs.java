package br.com.kalilstudios.janela;

import java.awt.*;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que contem as configuracoes graficas
 *
 */
abstract public class RenderPreConfigs {
	// configuracoes com maior qualidade
	public static final RenderingHints HIGH = getHighQuality();
	
	// configuracoes com qualidade media
	public static final RenderingHints MEDIUM = getMediumQuality();
	
	// configuracoes com menor qualidade
	public static final RenderingHints LOW = getLowQuality();

	// gera a qualidade HIGH
	private static RenderingHints getHighQuality() {
		RenderingHints high = new RenderingHints(null);
		high.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		high.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		high.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		high.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		high.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		high.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		high.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		high.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		high.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		high.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, 150);
		return high;
	}
	
	// gera a qualidade MEDIUM
	private static RenderingHints getMediumQuality() {
		RenderingHints Medium = new RenderingHints(null);
		Medium.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		Medium.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		Medium.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Medium.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		Medium.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		Medium.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		Medium.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		Medium.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		Medium.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		Medium.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, 150);
		return Medium;
	}
	
	// gera a qualidade LOW
	private static RenderingHints getLowQuality() {
		RenderingHints Low = new RenderingHints(null);
		Low.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		Low.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		Low.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		Low.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		Low.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		Low.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		Low.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		Low.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		Low.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		Low.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, 150);
		return Low;
	}
	
	// retorna uma config grafica de acordo com a String config
	public static RenderingHints getConfig(String config) {
		if(config.equals("HIGH")) return HIGH;
		if(config.equals("LOW")) return LOW;
		return MEDIUM;
	}
}
