
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;

public class Rompecabezas {

	List<String> numeros = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","");
	JButton[][] botones = new JButton[4][4];
	
	JPanel panel_4;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rompecabezas window = new Rompecabezas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Rompecabezas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 128), 5));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 128));
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 128), 5));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 128));
		frame.getContentPane().add(panel_2, BorderLayout.EAST);
		panel_2.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 128), 5));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 128));
		frame.getContentPane().add(panel_3, BorderLayout.SOUTH);
		panel_3.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 128), 5));

		panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(4, 4, 0, 0));

		desordenar();
		generarTablero();
		
		JButton btnNewButton_16 = new JButton("Reiniciar");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarTablero();
				desordenar();
				generarTablero();
			}
		});
		panel_3.add(btnNewButton_16);
	}
	public void desordenar() {
		Collections.shuffle(numeros);
	}
	
	public void generarTablero()
	{
		int cont = 0;
		for(int i = 0; i < botones.length; i++) {
			for(int j = 0; j < botones.length; j++) {
				botones[i][j] = new JButton(numeros.get(cont));
				botones[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 56));
				botones[i][j].setBackground(Color.white);
				
				panel_4.add(botones[i][j]);		
				botones[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton botonPresionado = (JButton) e.getSource();;
				        
				        if(!botonPresionado.getText().equals("")) {
				        	mover(botonPresionado);
				        	comprobarGaandor();
				        }
				    }
				});
				cont++;
			}
		}
		repintar();
	}
	public void borrarTablero() {
		for(int i = 0; i < botones.length; i++) {
			for(int j = 0; j < botones.length; j++) {
				panel_4.remove(botones[i][j]);			
			}
		}
		repintar();
	}
	public void mover(JButton btn){
	    for(int i = 0; i < botones.length; i++) {
	        for(int j = 0; j < botones.length; j++) {
	            if(botones[i][j].getText().equals("")) {

	                if(j-1 > -1) {
	                    if(botones[i][j-1].getText().equals(btn.getText())) {
	                        botones[i][j].setText(btn.getText());
	                        btn.setText("");
	                    }
	                }
	                if(j+1 < 4) {
	                    if(botones[i][j+1].getText().equals(btn.getText())) {
	                        botones[i][j].setText(btn.getText());
	                        btn.setText("");
	                    }
	                }
	                if(i-1 > -1) {
	                    if(botones[i-1][j].getText().equals(btn.getText())) {
	                        botones[i][j].setText(btn.getText());
	                        btn.setText("");
	                    }
	                }
	                if(i+1 < 4) {
	                    if(botones[i+1][j].getText().equals(btn.getText())) {
	                        botones[i][j].setText(btn.getText());
	                        btn.setText("");
	                    }
	                }
	            }
	        }
	    }
	}
	public void comprobarGaandor() {
		int numero = 1;
		int ganador = 0;
		
		for(int i = 0; i<botones.length; i++) {
			for(int j = 0; j<botones.length; j++) {
				if(botones[i][j].getText().contains(""+numero+"")) {
					ganador++;
				}
				if(numero > 15) {
					numero = 15;
				}
				else {
					numero++;					
				}
			}
		}
		if(ganador == 15) {
			JOptionPane.showMessageDialog(null, "¡Rompecabezas completado!", "Ganaste",
			        JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void repintar(){
		panel_4.repaint();
		panel_4.revalidate();
	}
}
