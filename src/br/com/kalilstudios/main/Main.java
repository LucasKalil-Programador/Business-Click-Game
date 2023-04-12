package br.com.kalilstudios.main;

import br.com.kalilstudios.hud.*;
import br.com.kalilstudios.janela.Janela;
import br.com.kalilstudios.jogador.Jogador;
import br.com.kalilstudios.utils.Utils;

import java.io.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static br.com.kalilstudios.janela.RenderGraphics.addElement;
import static br.com.kalilstudios.utils.Const.*;
import static br.com.kalilstudios.utils.Utils.bigIntToString;
import static br.com.kalilstudios.utils.Utils.getDateTimeString;
import static java.time.temporal.ChronoUnit.SECONDS;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que inicia o jogo 
 *
 */
public class Main {
	
	// jogador central de todo o programa tudo roda ao redor dele
	public static Jogador jogador;
	
	// janela principal do jogo
	public static Janela janela;

	// main do programa a partir daqui tudo e iniciado
	public static void main(String[] args) {
		// inicia o jogador
		jogador = getJogador();
		
		// inicia a janela
		janela = new Janela();
		
		// thread que inicia a Hud do jogo
		startHud();
		
		// apos carregar o Hud o janela fica visivel
		janela.setVisible(true);
		
		// simula os ganhos calculado a partir do tempo fora do jogo
		simularGanhosAFK();
		
		// inicia o loop que Salva o jogo automaticamente 
		// e gera o dinheiro por segundo para o jogador
		new ThreadDeSaveDinheiro();
	}

	// inicia os componentes da hud
	private static void startHud() {
			// inicia as Huds
			Hud hud = new Hud();
			HudClick hudClick = new HudClick();
			HudConfig hudConfig = new HudConfig();
			HudNegocio hudNegocio = new HudNegocio();
			HudPrestigio hudPrestigio = new HudPrestigio();
			HudBoostAutoClick hudAutoClick = new HudBoostAutoClick();

			// inicia a Hud do jogo ou seja todas imagems
			addElement(hud, hudClick, hudConfig, hudNegocio, hudPrestigio, hudAutoClick);
		
			// pede para a janela renderizar um novo quadro
			janela.renderGraphics.novoQuadro++;
	}

	/**
	 * 
	 * @return Jogador pode ser um novo jogador ou o jogador carregado
	 * 
	 * caso seja possivel carregaro o jogador ele é usado caso o nao seja
	 * possivel carregar o jogador e gerado um novo jogador
	 * 
	 */
	public static Jogador getJogador() {
		// caso Documents exista no sistema rodado
		if (DOCUMENTS.exists()) {
			
			// inicia o ObjectInputStream que tentara carregara o jogador no arquivo JOGADORSAVE
			try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(JOGADORSAVE))) {
				
				// exibe uma mensagem de sucesso no console nao visivel normalmente
				System.out.println(getDateTimeString() + "Save carregado com sucesso!");
				
				// retorna esse jogador carregado
				return (Jogador) OIS.readObject();
			} catch (ClassNotFoundException | IOException e) {
				
				// exibe uma mensagem de erro
				System.err.println(getDateTimeString() + "Não foi possivel carregar o save!");
				
				// printa o erro que ocorreu
				e.printStackTrace();
				
				// cria a pasta usada para salvar o jogo 
				// caso a pasta ja exista nao fara nada
				KALILGAMES.mkdirs();
			}
		}

		// retorna um novo jogador possivelmente deu algum erro
		return new Jogador();
	}

	// metodo que salva o jogador
	public synchronized static void saveJogador() {
		// salva a data e hora do ultimo save alguns sistemas usam isso como base
		jogador.lastSaveTime = LocalDateTime.now();
		
		// caso o Documents exista
		if (DOCUMENTS.exists()) {
			
			// inicia o ObjectOutputStream que tentara salvar o jogador no arquivo JOGADORSAVE
			try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(JOGADORSAVE))) {
				
				// tenta excrever o jogador no arquivo
				OOS.writeObject(jogador);
				
				// exibe uma mensagem de sucesso caso consequiu salvar o jogador
				System.out.println(getDateTimeString() + "Save criado com sucesso!");
			} catch (IOException e) {
				
				// exibe uma mensagem de erro pois nao foi possivel salvar o jogador
				System.err.println(getDateTimeString() + "Nao foi possivel criar um Save!");
				
				// printa o erro que ocorreu
				e.printStackTrace();
				
				// cria a pasta usada para salvar o jogo 
				// caso a pasta ja exista nao fara nada
				KALILGAMES.mkdirs();
			}
		}
	}

	/**
	 *  metodo que simula o ganho do jogador pelo tempo ocioso
	 *  so é execultado uma vez ao iniciar o jogo
	 */
	public static void simularGanhosAFK() {
		// caso localDateTime seja diferente de null 
		if (jogador.lastSaveTime != null) {
		
			// calcula o tempo que se passou em segundos
			long segundos = jogador.lastSaveTime.until(LocalDateTime.now(), SECONDS);

			// calcula o ganho pelo tempo ocioso 
			// maximo sendo 10 horas a partir do maximo e considerado 10 horas ou 36000 segundos
			BigInteger ganhoAFK = jogador.getGanhoPorSegundo();
			ganhoAFK = ganhoAFK.multiply(BigInteger.valueOf(segundos > 36000 ? 36000 : segundos));
			
			// adiciona o dinheiro a conta do jogador
			jogador.setDinheiro(jogador.getDinheiro().add(ganhoAFK));

			// inicia o StringBuilder que sera exibido na notificacao
			StringBuilder tempoPassado = new StringBuilder("Se passaram ");
			
			//gera o tempo que sera exibido
			tempoPassado.append(segundos / 86400 + "D ");
			segundos %= 86400;
			tempoPassado.append(segundos / 3600 + "H ");
			segundos %= 3600;
			tempoPassado.append(segundos / 60 + "M ");
			segundos %= 60;
			tempoPassado.append(segundos % 60 + "S");
			
			// adiciona as informacaes finais
			tempoPassado.append("\r\nTempo maximo: 10 Horas \r\nOque rendeu ");
			tempoPassado.append(bigIntToString(ganhoAFK));
			
			// exibia a mensagem em uma tela separada
			showMessageDialog(janela, tempoPassado, "Bem vindo!", NO_OPTION);
		}
	}

	/**
	 * inicia o loop que ficara rodando ate o final do Jogo
	 * o loop salvara o jogador de tempos em tempos configurado por autoSaveDelay
	 * e adicionara ao jogador o dinheiro GanhoPorSegundo
	 */
	private static class ThreadDeSaveDinheiro extends Thread {
		
		// delay de autoSave em segundos
		private static final int autoSaveDelay = 10 * 60;
		
		public ThreadDeSaveDinheiro() {
			start();
		}
		
		@Override
		public void run() {
			// loop infinito
			while (true) {

				// tempo de 1 segundo de espera
				Utils.sleep(1, TimeUnit.SECONDS);

				// caso ja tenha se passado o tem do autoSaveDelay o jogador sera salvo
				if (jogador.lastSaveTime.until(LocalDateTime.now(), SECONDS) >= autoSaveDelay)
					saveJogador();

				// adiciona ao jogador o dinheiro do GanhoPorSegundo
				jogador.setDinheiro(jogador.getDinheiro().add(jogador.getGanhoPorSegundo()));
			}
		}
	}
}
