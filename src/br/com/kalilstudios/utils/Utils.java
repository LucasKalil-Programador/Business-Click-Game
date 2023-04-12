package br.com.kalilstudios.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static java.awt.Image.SCALE_SMOOTH;
import static java.awt.Transparency.TRANSLUCENT;
import static java.lang.String.format;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.valueOf;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * classe contem metodos uteis durante toda a execulsao do jogo
 *
 */
public abstract class Utils {
	/**
	 * metodo usado para ter uma string com a data e o horario
	 * 
	 * @return String data e horario atual
	 */
	public static String getDateTimeString() {
		return now().format(ofPattern("dd/MM/yyyy HH:mm:ss - "));
	}

	/**
	 * metodo sleep com TimeUnit 
	 * exemplo sleep(10, TimeUnit.Seconds) ira darsleep de 10 segundos
	 * 
	 * @param t tempo que se passara 
	 * @param unit unidade de tempo de t
	 */
	public static void sleep(long t, TimeUnit unit) {
		if (t > 0)
			try {
				Thread.sleep(unit.toMillis(t));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	/**
	 * metodo padrao usado para Carregar as imagens do jogo ultiliza BufferedImage
	 * 
	 * @param path   o local onde a imagem se encontra
	 * @param width  � a largura final da imagem
	 * @param height � a altura final da imagem
	 * 
	 * @return retorna a Image que foi requerida
	 *
	 */
	public static BufferedImage getImage(String path, int width, int height) {
		try {
			// carrega a imagem
			Image rawImage = ImageIO.read(Utils.class.getResource(path));

			// cria o BufferedImage
			BufferedImage Image;

			// se o tamanho da imagem seja dirente do nessesario
			if (rawImage.getHeight(null) != height || rawImage.getWidth(null) != width) {
				
				// exibe uma mensagem de erro
				System.err.println("tamanho da imagem esta errado tamanho original " + rawImage.getHeight(null) + " " + rawImage.getWidth(null));

				
				// redimensiona a rawImage para o tamanho correto
				rawImage = rawImage.getScaledInstance(width, height, SCALE_SMOOTH);

				// cria um BufferedImage
				Image = new BufferedImage(width, height, TRANSLUCENT);

				// desenha a rawImage no BufferedImage
				Image.createGraphics().drawImage(rawImage, 0, 0, null);
			} else {
				// caso o tamanho esteja correto
				Image = (BufferedImage) rawImage;
			}

			// retorna o BufferedImage
			return Image;
		} catch (Exception e) {
			
			// exibe o erro
			e.printStackTrace();
		}
		return null;
	}

	// array que contem os complementos para o numero exemplo 1000 sera 1.0K
	private static final String[] STRARRAY = { "K", "M", "B", "T", "q", "Q", "s", "S", "O", "N", "D", "AA", "AB", "AC",
			"AD", "AE", "AF", "AG", "AH", "AI", "AJ", "AK", "AL", "AM", "AN", "AO", "AP", "AQ", "AR", "AS", "AT", "AU",
			"AV", "AW", "AX", "AY", "AZ", "BA", "BB", "BC", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BK", "BL", "BM",
			"BN", "BO", "BP", "BQ", "BR", "BS", "BT", "BU", "BV", "BW", "BX", "BY", "BZ", "CA", "CB", "CC", "CD", "CE",
			"CF", "CG", "CH", "CI", "CJ", "CK", "CL", "CM", "CN", "CO", "CP", "CQ", "CR", "CS", "CT", "CU", "CV", "CW",
			"CX", "CY", "CZ", "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH", "DI", "DJ", "DK", "DL", "DM", "DN", "DO",
			"DP", "DQ", "DR", "DS", "DT", "DU", "DV", "DW", "DX", "DY", "DZ", "EA", "EB", "EC", "ED", "EE", "EF", "EG",
			"EH", "EI", "EJ", "EK", "EL", "EM", "EN", "EO", "EP", "EQ", "ER", "ES", "ET", "EU", "EV", "EW", "EX", "EY",
			"EZ", "FA", "FB", "FC", "FD", "FE", "FF", "FG", "FH", "FI", "FJ", "FK", "FL", "FM", "FN", "FO", "FP", "FQ",
			"FR", "FS", "FT", "FU", "FV", "FW", "FX", "FY", "FZ", "GA", "GB", "GC", "GD", "GE", "GF", "GG", "GH", "GI",
			"GJ", "GK", "GL", "GM", "GN", "GO", "GP", "GQ", "GR", "GS", "GT", "GU", "GV", "GW", "GX", "GY", "GZ", "HA",
			"HB", "HC", "HD", "HE", "HF", "HG", "HH", "HI", "HJ", "HK", "HL", "HM", "HN", "HO", "HP", "HQ", "HR", "HS",
			"HT", "HU", "HV", "HW", "HX", "HY", "HZ", "IA", "IB", "IC", "ID", "IE", "IF", "IG", "IH", "II", "IJ", "IK",
			"IL", "IM", "IN", "IO", "IP", "IQ", "IR", "IS", "IT", "IU", "IV", "IW", "IX", "IY", "IZ", "JA", "JB", "JC",
			"JD", "JE", "JF", "JG", "JH", "JI", "JJ", "JK", "JL", "JM", "JN", "JO", "JP", "JQ", "JR", "JS", "JT", "JU",
			"JV", "JW", "JX", "JY", "JZ", "KA", "KB", "KC", "KD", "KE", "KF", "KG", "KH", "KI", "KJ", "KK", "KL", "KM",
			"KN", "KO", "KP", "KQ", "KR", "KS", "KT", "KU", "KV", "KW", "KX", "KY", "KZ", "LA", "LB", "LC", "LD", "LE",
			"LF", "LG", "LH", "LI", "LJ", "LK", "LL", "LM", "LN", "LO", "LP", "LQ", "LR", "LS", "LT", "LU", "LV", "LW",
			"LX", "LY", "LZ", "MA", "MB", "MC", "MD", "ME", "MF", "MG", "MH", "MI", "MJ", "MK", "ML", "MM", "MN", "MO",
			"MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NB", "NC", "ND", "NE", "NF", "NG",
			"NH", "NI", "NJ", "NK", "NL", "NM", "NN", "NO", "NP", "NQ", "NR", "NS", "NT", "NU", "NV", "NW", "NX", "NY",
			"NZ", "OA", "OB", "OC", "OD", "OE", "OF", "OG", "OH", "OI", "OJ", "OK", "OL", "OM", "ON", "OO", "OP", "OQ",
			"OR", "OS", "OT", "OU", "OV", "OW", "OX", "OY", "OZ", "PA", "PB", "PC", "PD", "PE", "PF", "PG", "PH", "PI",
			"PJ", "PK", "PL", "PM", "PN", "PO", "PP", "PQ", "PR", "PS", "PT", "PU", "PV", "PW", "PX", "PY", "PZ", "QA",
			"QB", "QC", "QD", "QE", "QF", "QG", "QH", "QI", "QJ", "QK", "QL", "QM", "QN", "QO", "QP", "QQ", "QR", "QS",
			"QT", "QU", "QV", "QW", "QX", "QY", "QZ", "RA", "RB", "RC", "RD", "RE", "RF", "RG", "RH", "RI", "RJ", "RK",
			"RL", "RM", "RN", "RO", "RP", "RQ", "RR", "RS", "RT", "RU", "RV", "RW", "RX", "RY", "RZ", "SA", "SB", "SC",
			"SD", "SE", "SF", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SP", "SQ", "SR", "SS", "ST", "SU",
			"SV", "SW", "SX", "SY", "SZ", "TA", "TB", "TC", "TD", "TE", "TF", "TG", "TH", "TI", "TJ", "TK", "TL", "TM",
			"TN", "TO", "TP", "TQ", "TR", "TS", "TT", "TU", "TV", "TW", "TX", "TY", "TZ", "UA", "UB", "UC", "UD", "UE",
			"UF", "UG", "UH", "UI", "UJ", "UK", "UL", "UM", "UN", "UO", "UP", "UQ", "UR", "US", "UT", "UU", "UV", "UW",
			"UX", "UY", "UZ", "VA", "VB", "VC", "VD", "VE", "VF", "VG", "VH", "VI", "VJ", "VK", "VL", "VM", "VN", "VO",
			"VP", "VQ", "VR", "VS", "VT", "VU", "VV", "VW", "VX", "VY", "VZ", "WA", "WB", "WC", "WD", "WE", "WF", "WG",
			"WH", "WI", "WJ", "WK", "WL", "WM", "WN", "WO", "WP", "WQ", "WR", "WS", "WT", "WU", "WV", "WW", "WX", "WY",
			"WZ", "XA", "XB", "XC", "XD", "XE", "XF", "XG", "XH", "XI", "XJ", "XK", "XL", "XM", "XN", "XO", "XP", "XQ",
			"XR", "XS", "XT", "XU", "XV", "XW", "XX", "XY", "XZ", "YA", "YB", "YC", "YD", "YE", "YF", "YG", "YH", "YI",
			"YJ", "YK", "YL", "YM", "YN", "YO", "YP", "YQ", "YR", "YS", "YT", "YU", "YV", "YW", "YX", "YY", "YZ", "ZA",
			"ZB", "ZC", "ZD", "ZE", "ZF", "ZG", "ZH", "ZI", "ZJ", "ZK", "ZL", "ZM", "ZN", "ZO", "ZP", "ZQ", "ZR", "ZS",
			"ZT", "ZU", "ZV", "ZW", "ZX", "ZY", "ZZ", "?" };
	
	// valor que replesenta 1000 em BigInteger usado no metodo THOUSAND
	public static final BigInteger THOUSAND = valueOf(1000);
	
	/** metodo usado para converter um numero extremamente em uma string
	 * exemplo 1.500.000.000.000.000.000 sera convertido para 1.5s
	 * 
	 * @param bigInt numero a ser convertido para um forma simplificada
	 * 
	 * @return String que contem a forma simplificada
	 */
	public static String bigIntToString(BigInteger bigInt) {
		
		// tamanho do numero a cada 3 numero sera 1 ou seja 1.000.000 size = 2
		short size = (short) ((bigInt.toString().length() - 1) / 3);
		
		// caso o size seja 0 retorna o propio valor sem nenhama modifica�ao
		if (size == 0) return bigInt.toString();

		// converte o size para BigInteger
		BigInteger sizeBI = THOUSAND.pow(size);
		// calcula as casas decimais
		BigInteger[] B = { bigInt.divide(sizeBI), bigInt.mod(sizeBI).divide(sizeBI.divide(TEN)).abs() };

		// formata e retorna a String que representa bigInt
		return format("%s.%s%s", B[0], B[1], STRARRAY[--size < 687 ? size : 687]);
	}
}
