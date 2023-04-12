package br.com.kalilstudios.janela;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static java.lang.System.nanoTime;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.1
 * 
 * classe que controla o Frame Rate do jogo
 */
public class FPSControl extends Sleep{

	// tempo que demorou a execulssao da task
	private long currentTime;

	// ultilizo byte inves de int ou long devido esse numero nunca passar de 30
	// total de fps por 1 segundo
	private byte countFPS = 60;
	
	// contagem de fps temporaria
	private byte fps = 0;

	// estado atual define se o codigo é execultado ou nao
	private boolean state;

	// task que e execultada de tempos em tempos
	private final Runnable task;
	
	LocalTime time = LocalTime.now();
	
	// threads usadas
	// threadRun execulta o loop
	Thread threadRun;

	// inicia o loop so existe 1 loop ao mesmo tempo e isso e garantido pelo synchronized
	private synchronized void initLoop() {
		while (state) {
			// define o currentTime
			currentTime = nanoTime();
			
			// caso se tenha se passado mais de 1 segundo atualiza o countFPS
			if(time.until(LocalTime.now(), SECONDS) >= 1) {
				time = LocalTime.now();
				countFPS = fps;
				fps = 0;
			}
			
			// execulta o comando passando no construtor FPSControl(Runnable task)
			task.run();
			
			// incrementa 1 ao fps
			fps++;
			
			// define o tempo que ficara em espera
			exactSleep(delay60fps - (nanoTime() - currentTime));
		}
	}

	// contrutor que define a task a ser execultada
	public FPSControl(Runnable task) {
		this.task = task;
	}

	// inicia as threads
	public void start() {
		threadRun = new Thread(this::initLoop);
		this.state = true;
		threadRun.start();
	}

	// muda state para false
	// oque faz o loop das threads pararem
	public void stop() {
		state = false;
	}

	// retorna o state atual
	public boolean getState() {
		return state;
	}

	// retorna o CountFPS
	public byte getCountFPS() {
		return countFPS;
	}

	// retorna fps: x
	@Override
	public String toString() {
		return "FPS: " + countFPS;
	}
}

abstract class Sleep {
	
	// delay para 60fps
	public static final int delay60fps = (int) (1000.0 / 60.0 * 1000000.0);
	
	// pausa a thread exatamente no timeInNano
	public static void exactSleep(long time, TimeUnit tUnit) {
		
		// converte o time em nano segundos
		exactSleep(tUnit.toNanos(time));
	}

	// pausa a thread exatamente no timeInNano
	public static void exactSleep(long timeInNano) {
		
		// verifica se a timeInNano e menor que 0
		if (timeInNano > 0) {
			
			// adiciona o nanoTime timeInNano
			timeInNano += nanoTime();
			
			// diferença entre o tempo atual eo timeInNano
			long dif;

			// loop que fica sendo execultado ate o tempo chegar ao fin
			while ((dif = timeInNano - nanoTime()) > 0) {
				
				// caso a diferença for maior que 2.5 a thread e sleep
				if (dif >= 2500000) {
					sleep(NANOSECONDS.toMillis(dif / 2));
				}
			}
		}
	}

	// caso os parametros estejam fora dos limites a pausa sera de 0
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis < 0 ? 0 : millis);
		} catch (InterruptedException e) {
		}
	}
}