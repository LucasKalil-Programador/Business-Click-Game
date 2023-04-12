package br.com.kalilstudios.jogador;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.kalilstudios.utils.Utils.THOUSAND;
import static br.com.kalilstudios.utils.Utils.bigIntToString;
import static java.lang.String.format;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que representa 1 jogador
 *
 */
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;

	// lista que contem todos os negocios
	public List<Negocios> negocios = Negocios.getAllNegocios();

	// configuracoes do usuario
	public Configs config = new Configs();
	
	// representa os boosts do usuario
	public Boosts boost = new Boosts();
	
	// representa as conquistas do jogo
	public Achievements achievements = new Achievements();

	// multiplicador prestigio do jogador
	private BigInteger multiPrestigio = ONE;

	// dinheiro que o jogador tem
	private BigInteger dinheiro = ZERO;

	// level do jogador
	private BigInteger level = ONE;

	// ultima vez que o jogador foi salvo
	public LocalDateTime lastSaveTime = LocalDateTime.now();

	// construtor que gera um jogador quando ele usa o prestigio
	public Jogador(BigInteger b) {
		this.multiPrestigio = b;
	}

	// construtor padrao de um novo jogador
	public Jogador() {
		super();
	}

	// calcula o custo do prestigio
	public BigInteger getCustoPrestigio() {
		return THOUSAND.pow((int) (Math.log(multiPrestigio.longValueExact()) / Math.log(2) + 4));
	}

	// retorna o multiPrestigio
	public BigInteger getMultiPrestigio() {
		return multiPrestigio;
	}

	// retorna o ganho por segundo multiplicado pelo multiPrestigio
	public BigInteger getGanhoPorSegundo() {
		BigInteger b = ZERO;
		for (Negocios n : negocios)
			b = b.add(n.getGanho());
		return b.multiply(multiPrestigio);
	}

	// calcula o ganho por click
	public BigInteger getGanhoPorClick() {
		return level.pow(7).multiply(multiPrestigio);
	}

	// calcula o preco melhoria do level
	public BigInteger getPrecoMelhoria() {
		return level.add(ONE).pow(9);
	}

	// retorna o dinheiro que o jogador tem
	public BigInteger getDinheiro() {
		return dinheiro;
	}

	// modifica o dinheiro
	public synchronized void setDinheiro(BigInteger dinheiro) {
		this.dinheiro = dinheiro;
	}

	// retorna o level atual do jogador
	public BigInteger getLevel() {
		return level;
	}

	// modifica o level do jogador
	public synchronized void setLevel(BigInteger level) {
		this.level = level;
	}

	// retorna uma String com todas os detalhes do jogador
	@Override
	public String toString() {
		// cria o String Builder
		StringBuilder sb = new StringBuilder();

		// adiciona todos os detalhes sobre o jogador
		sb.append(format("Jogador  Dinheiro: [%11s] Level: [%11s] GPC: [%11s] GPS: [%11s]%n", bigIntToString(dinheiro),
				bigIntToString(level), bigIntToString(getGanhoPorClick()), bigIntToString(getGanhoPorSegundo())));

		// adiciona todos os detalhes sobre os negocios
		for (int i = 0; i < negocios.size(); i++)
			sb.append(format("%n%2d %s", i + 1, negocios.get(i).toStr()));

		// retorna o StringBuilder
		return sb.toString();
	}
}