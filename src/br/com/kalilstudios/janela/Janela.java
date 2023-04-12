package br.com.kalilstudios.janela;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static br.com.kalilstudios.main.Main.janela;
import static br.com.kalilstudios.main.Main.saveJogador;
import static br.com.kalilstudios.utils.Const.RESOLUTION;
import static br.com.kalilstudios.utils.Utils.getImage;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * 
 * @author Lucas Guimaraes Kalil
 * 
 * @version 1.0
 * 
 * classe que representa a janela principal
 *
 */
public class Janela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// icone que aparece na janela
	public final Image dinheiroicon = getImage("/br/com/kalilstudios/resource/dinheiroicon.png",256, 256);
	
	// inicia o panel que renderiza o jogo
	public RenderGraphics renderGraphics = new RenderGraphics();

	// inicia o JFrame
	public Janela() {
		
		// muda o Look and Feel para um mais bonito
		changeLookAndFeel();
		
		// configura JFrame
		config();
	}

	// configura JFrame
	private void config() {
	
		// realiza todas as configuracoes do JFraem
		setLayout(null);
		setSize(RESOLUTION);
		setResizable(false);
		setIconImage(dinheiroicon);
		setMinimumSize(new Dimension(1264 / 2, 681 / 2));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Business Click Game     Criador: Lucas Guimaraes Kalil     Verçao: 1.5");
		
		// adiciona o GameCloseEvent na janela
		addWindowListener(new GameCloseEvent());

		setContentPane(renderGraphics);
	}

	// metodo que muda o Look and Feel para um mais bonito
	public static void changeLookAndFeel() {
		try {
			
			// pega todos os LookAndFeel
			for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
				
				// caso o Look And Feel Nimbus o setLookAndFeel(Nimbus);
				if ("Nimbus".equals(info.getName())) {
					setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// dispara um evento padrao
	public void dispatchEvent() {
		dispatchEvent(new MouseEvent(janela, 0, 0, 0, 0, 0, 0, false));
	}

	private class GameCloseEvent implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			
		}

		// metodo lençado clica para ser fechada
		public void windowClosing(WindowEvent e) {
			// salva o jogador
			saveJogador();

			// finaliza o programa
			System.exit(0);
		}
	
		@Override
		public void windowClosed(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}
	}
}
