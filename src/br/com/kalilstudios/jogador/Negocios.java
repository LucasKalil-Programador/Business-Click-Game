package br.com.kalilstudios.jogador;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static br.com.kalilstudios.utils.Const.TWO;
import static br.com.kalilstudios.utils.Utils.bigIntToString;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.String.format;
import static java.math.BigInteger.*;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * interface que representa um negocio no jogo
 *
 */

public interface Negocios extends Serializable {

	// metodo que modifica o level do negocio
	void setLevel(BigInteger level);

	/**
	 * @return Preco da melhoria
	 */
	BigInteger getPreco();

	/**
	 * @return level do negocio
	 */
	BigInteger getLevel();
 
	/**
	 * @return ganho do negocio
	 */
	BigInteger getGanho();

	/**
	 * @return nome do negocio
	 */
	String getName();
	
	/**
	 * @param range o quanto sera simulado
	 * 
	 * @return preco simulado
	 */
	default BigInteger simularCusto(int range) {
		// caso range for menor que 1 nao sera feito nada
		if(range <= 1) return getPreco();
		
		// representa o Custo do proximo nivel
		BigInteger proxCusto = getPreco();
		
		// Preco por level
		BigInteger precoUnico = proxCusto.divide(getLevel().add(ONE));
		
		// resultado final da simulacao
		BigInteger resultado = proxCusto;
		
		// for onde e simulado o custo
		for (int i = 1; i < range; i++) {
			proxCusto = proxCusto.add(precoUnico);
			resultado = resultado.add(proxCusto);
			
		}
		
		// retorna o resultado
		return resultado;
	}
	
	// retorna uma String com todos os detalhes do negocio
	default String toStr() {
		return format("Nome: [%20s] level: [%7s] Ganho: [%7s] Preco Melhoria: [%7s] ", getName(),
				bigIntToString(getLevel()), bigIntToString(getGanho()), bigIntToString(getPreco()));
	}

	// retorna uma lista que contem todos os negocios
	static List<Negocios> getAllNegocios() {
		return Arrays.asList(new Limonada(),
				new Pastel(),
				new Mercado(),
				new Shopping(),
				new Cinepolis(),
				new LojasAmericanas(),
				new StarBucks(),
				new McDonalds(),
				new BurgerKing(),
				new Outback(),
				new RockStar(),
				new Ubisoft(),
				new EA(),
				new EpicGames(),
				new Steam(),
				new Disney(),
				new Netflix(),
				new Nintendo(),
				new Microsoft(),
				new Intel(),
				new AMD(),
				new Nvidia(),
				new Tesla(),
				new Volkswagen(),
				new Volvo(),
				new Toyota(),
				new Ferrari(),
				new Apple(),
				new Wallmart(),
				new Armazon());
	}
}

class Limonada implements Negocios {

	private static final long serialVersionUID = 1L;

	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(3));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(TWO);
	}

	@Override
	public String getName() {
		return "Barraca de limonada";
	}
}

class Pastel implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;
	
	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(76));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(47));
	}

	@Override
	public String getName() {
		return "Pastel do carioca";
	}
	
}

class Mercado implements Negocios{

	private static final long serialVersionUID = 1L;

	BigInteger level = ZERO;
	
	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(1330));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(1126));
	}

	@Override
	public String getName() {
		return "Mercado";
	}
}

class Shopping implements Negocios {

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(25904));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(21980));
	}

	@Override
	public String getName() {
		return "Shopping Salvador";
	} 
}

class Cinepolis implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(122380));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(110380));
	}

	@Override
	public String getName() {
		return "Cinepolis";
	}	
}

class LojasAmericanas implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(591703));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(478013));
	}

	@Override
	public String getName() {
		return "Lojas Americas";
	}
}

class StarBucks implements Negocios {

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(1768234));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(1278900));
	}

	@Override
	public String getName() {
		return "StarBucks";
	}
}

class McDonalds implements Negocios{

	private static final long serialVersionUID = 1L;

	BigInteger level = ZERO;
	
	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(298175367));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(15623556));
	}

	@Override
	public String getName() {
		return "McDonalds";
	}	
}

class BurgerKing implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(792345678346L));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(MAX_VALUE));
	}

	@Override
	public String getName() {
		return "BurgerKing";
	}
}

class Outback implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(98000000000000L));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(780000000000L));
	}

	@Override
	public String getName() {
		return "Outback";
	}
}

class RockStar implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(560000000000000000L));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(60000000000000000L));
	}

	@Override
	public String getName() {
		return "RockStar Games";
	}
}

class Ubisoft implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(198).pow(9));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(578).pow(7));
	}

	@Override
	public String getName() {
		return "Ubisoft";
	}
}

class EA implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(980).multiply(TEN).pow(6));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(10).multiply(TEN).pow(11));
	}

	@Override
	public String getName() {
		return "EA Games";
	}	
}

class EpicGames implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(390).multiply(TEN.pow(24)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(173).multiply(TEN.pow(21)));
	}

	@Override
	public String getName() {
		return "Epic Games";
	}
}

class Steam implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(981).multiply(TEN.pow(29)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(97).multiply(TEN.pow(26)));
	}
	
	@Override
	public String getName() {
		return "Steam";
	}	
}

class Disney implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(379).multiply(TEN.pow(32)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(171).multiply(TEN.pow(29)));
	}

	@Override
	public String getName() {
		return "Disney";
	}	
}

class Netflix implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(100).multiply(TEN.pow(37)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(100).multiply(TEN.pow(33)));
	}

	@Override
	public String getName() {
		return "Netflix";
	}
}

class Nintendo implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(500).multiply(TEN.pow(40)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(100).multiply(TEN.pow(37)));
	}

	@Override
	public String getName() {
		return "Nintendo";
	}
}

class Microsoft implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(769).multiply(TEN.pow(43)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(6969).multiply(TEN.pow(39)));
	}

	@Override
	public String getName() {
		return "Microsoft";
	}
}

class Intel implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(123).multiply(TEN.pow(48)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(987).multiply(TEN.pow(43)));
	}

	@Override
	public String getName() {
		return "Intel";
	}	
}

class AMD implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(456).multiply(TEN.pow(52)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(761).multiply(TEN.pow(47)));
	}

	@Override
	public String getName() {
		return "AMD";
	}
}

class Nvidia implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(200).multiply(TEN.pow(55)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(300).multiply(TEN.pow(50)));
	}

	@Override
	public String getName() {
		return "Nvidia";
	}
}

class Tesla implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(500).multiply(TEN.pow(58)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(500).multiply(TEN.pow(54)));
	}

	@Override
	public String getName() {
		return "Tesla";
	}
}

class Volkswagen implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(179).multiply(TEN.pow(62)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(260).multiply(TEN.pow(56)));
	}

	@Override
	public String getName() {
		return "Volkswagen";
	}
}

class Volvo implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(101).multiply(TEN.pow(69)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(101).multiply(TEN.pow(63)));
	}

	@Override
	public String getName() {
		return "Volvo";
	}
}

class Toyota implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(420).multiply(TEN.pow(75)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(420).multiply(TEN.pow(69)));
	}

	@Override
	public String getName() {
		return "Toyota";
	}
}

class Ferrari implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(840).multiply(TEN.pow(84)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(720).multiply(TEN.pow(75)));
	}

	@Override
	public String getName() {
		return "Ferrari";
	}
}

class Apple implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(480).multiply(TEN.pow(91)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(1360).multiply(TEN.pow(80)));
	}

	@Override
	public String getName() {
		return "Apple";
	}	
}

class Wallmart implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(510).multiply(TEN.pow(96)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(490).multiply(TEN.pow(81)));
	}

	@Override
	public String getName() {
		return "Wallmart";
	}
}

class Armazon implements Negocios{

	private static final long serialVersionUID = 1L;
	
	BigInteger level = ZERO;

	@Override
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	@Override
	public BigInteger getPreco() {
		return level.add(ONE).multiply(valueOf(100).multiply(TEN.pow(101)));
	}

	@Override
	public BigInteger getLevel() {
		return level;
	}

	@Override
	public BigInteger getGanho() {
		return level.multiply(valueOf(345).multiply(TEN.pow(86)));
	}

	@Override
	public String getName() {
		return "Amazon";
	}
}

//Limonada       1
//Pastel         2
//Mercado        3
//Shoping        4
//Cinepolis      5
//Lojas americas 6
//StarBucks      7
//McDonalds      8
//BurgerKing     9
//OutBack        10
//Rockstar games 11
//Ubisoft        12
//EA             13
//Epic games     14
//Steam          15
//Disney         16
//Netflix        17
//Nintendo       18
//Microsoft      19
//Intel          20
//AMD            21
//Nvidia         22
//Tesla          23
//Volkswagen     24
//Volvo          25
//Toyota         26
//Ferrari        27
//Apple          28
//Wallmart       29
//Armazon        30






