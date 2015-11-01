package ISIS.Code.Panels;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import ISIS.Code.Class.PanelCurves;
import ISIS.Code.Matriz.Mapa;
import ISIS.Code.Matriz.ModeloMapa;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author ISIS
 */
public class PanelPausa extends javax.swing.JDialog {

	public PanelPausa(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	private void initComponents() {

		panelImage1 = new org.edisoncor.gui.panel.PanelImage();
		panelCurves2 = new PanelCurves();
		buttonRound1 = new org.edisoncor.gui.button.ButtonRound();
		buttonRound2 = new org.edisoncor.gui.button.ButtonRound();
		buttonRound3 = new org.edisoncor.gui.button.ButtonRound();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setPreferredSize(new java.awt.Dimension(300, 370));
		getContentPane().setLayout(null);

		panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/ISIS/Resource/Backgrounds/Pausa.png")));

		buttonRound1.setText("Continuar");
		buttonRound1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				System.out.println("continuar");
				dispose();
			}
		});

		buttonRound2.setText("Guardar");
		buttonRound2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				setGuardado(true);
				dispose();
			}
		});

		buttonRound3.setText("Salir");
		buttonRound3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonRound3ActionPerformed(evt);
				dispose();
			}
		});

		javax.swing.GroupLayout panelCurves2Layout = new javax.swing.GroupLayout(
				panelCurves2);
		panelCurves2.setLayout(panelCurves2Layout);
		panelCurves2.start = new Color(0, 0, 0, 200);

		panelCurves2Layout
				.setHorizontalGroup(panelCurves2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								panelCurves2Layout
										.createSequentialGroup()
										.addContainerGap(112, Short.MAX_VALUE)
										.addGroup(
												panelCurves2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																buttonRound3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																91,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonRound2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonRound1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(94, 94, 94)));
		panelCurves2Layout.setVerticalGroup(panelCurves2Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						panelCurves2Layout
								.createSequentialGroup()
								.addContainerGap(128, Short.MAX_VALUE)
								.addComponent(buttonRound1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(buttonRound2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(buttonRound3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(128, 128, 128)));

		javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(
				panelImage1);
		panelImage1.setLayout(panelImage1Layout);
		panelImage1Layout
				.setHorizontalGroup(panelImage1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								panelImage1Layout
										.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(
												panelCurves2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		panelImage1Layout
				.setVerticalGroup(panelImage1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								panelImage1Layout
										.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(
												panelCurves2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		getContentPane().add(panelImage1);
		panelImage1.setBounds(0, 0, 300, 370);

		pack();
	}

	private void buttonRound3ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	public static void main(String args[]) {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(PanelPausa.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(PanelPausa.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(PanelPausa.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(PanelPausa.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				PanelPausa dialog = new PanelPausa(new javax.swing.JFrame(),
						true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {

					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public static boolean isGuardado() {
		return guardado;
	}

	public static void setGuardado(boolean guardado) {
		PanelPausa.guardado = guardado;
	}

	private org.edisoncor.gui.button.ButtonRound buttonRound1;
	private org.edisoncor.gui.button.ButtonRound buttonRound2;
	private org.edisoncor.gui.button.ButtonRound buttonRound3;
	private PanelCurves panelCurves2;
	private org.edisoncor.gui.panel.PanelImage panelImage1;
	private static boolean guardado;

}
