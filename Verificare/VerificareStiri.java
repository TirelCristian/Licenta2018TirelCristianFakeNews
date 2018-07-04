package Verificare;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class VerificareStiri {
	
	int score = 0;
	int gasitdeja = 0;
	int adaugatdeja=0;
	
	private JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerificareStiri window = new VerificareStiri();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerificareStiri() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.BOLD, 13));
		frame.setBounds(500, 500, 750, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		textField = new JTextField();
		textField.setBounds(40, 135, 650, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblIntroducetiUrlulPentru = new JLabel("Introduceti URL-ul pentru verificare");
		lblIntroducetiUrlulPentru.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblIntroducetiUrlulPentru.setBounds(183, 85, 350, 35);
		frame.getContentPane().add(lblIntroducetiUrlulPentru);
		
		JButton btnVerificareCridibilitate = new JButton("Verificare Credibilitate URL");
		btnVerificareCridibilitate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnVerificareCridibilitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vvar = textField.getText();
				if (vvar.isEmpty())
					JOptionPane.showMessageDialog(frame, "Introduce-ti un site valid mai sus");
				
				if(vvar.indexOf(0)!='h') {
					List<String> Reconstruire = new ArrayList<>();
					Reconstruire = CautareSiSalvare.listare(vvar);
					vvar=Reconstruire.get(0);
				}
				
				final String var = vvar;

				System.out.println(URLverify.checkURLexistance(var));
				System.out.println(URLverify.checkURLsafety(var));
				
				if(!URLverify.checkURLexistance(var))
					JOptionPane.showMessageDialog(frame, "Site invalid");
				else JOptionPane.showMessageDialog(frame, "Site gasit");
				
				if(URLverify.checkURLexistance(var))
					if(!URLverify.checkURLsafety(var)) {
						JOptionPane.showMessageDialog(frame, "Site-ul nu este securizat");  
						score+=50;
					}
			
				if(URLverify.checkURLexistance(var)) {
					JButton btnNewButton = new JButton("Verificare Credibilitate Stire");
					btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							@SuppressWarnings("unused")
							List<String> ListaCale = new ArrayList<>();
							List<String> ListaSLDTLD = new ArrayList<>();
							List<String> SpargereRezultat = new ArrayList<>();
							
							String SLDTLD = TransformareURL.GetSLDTLD(var);
							String calea=TransformareURL.GetPath(var);
							
						    try {
								if(LucruCuFisier.cauta(SLDTLD, Paths.get("C:\\Users\\Cristian\\Desktop\\Java\\LicentaJava\\src\\Verificare\\FakeDetected.txt"))) {
									gasitdeja=1;
									score+=100;
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						    
							try {
								if(LucruCuFisier.cauta(SLDTLD, Paths.get("C:\\Users\\Cristian\\Desktop\\Java\\LicentaJava\\src\\Verificare\\RealDetected.txt"))) {
									gasitdeja=1;
									score-=100;
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							Calendar today = Calendar.getInstance();
							today.set(Calendar.HOUR_OF_DAY, 0);
							
							Date f = new Date(today.getTimeInMillis());
							Date d = new Date(URLverify.getLastModified(SLDTLD));

							int Diferenta = (int) Data.getDateDiff(f,d,TimeUnit.DAYS);
							if (Diferenta > 2)
								score+=30;
							
							System.out.println("Searching for: " + calea);
							ListaCale=CautareSiSalvare.listare(calea);
							ListaSLDTLD=CautareSiSalvare.listare(SLDTLD);
							
							System.out.println("Aici" + ListaSLDTLD);
							SpargereRezultat=TransformareURL.SpargereRezultat(SLDTLD);
							
							System.out.println(SpargereRezultat);
							
							ComparareRezultat.comparare(SpargereRezultat,ListaSLDTLD);
							
							if (score>30)
								JOptionPane.showMessageDialog(frame, "FAKE NEWS DETECTED !!!");
							else if (score < 0)
								JOptionPane.showMessageDialog(frame, "Real News");
							
							if (score == 0)
								JOptionPane.showMessageDialog(frame, "Caz Indecis, va rog sa raspundeti la urmatoarele intrebari: ");
								
							for(int i=0;i<3;i++) {
								String[] choices = { "Da", "Nu"};
								String[] intrebari = {"Este un site de satira ?", "Autorul este necunoscut ?", "Considerati ca acest site este fals ?"};
								String input = (String) JOptionPane.showInputDialog(null, intrebari[i],
						        "Alegeti varianta corecta", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
							if(input=="Da")
								score+=10;
							else score-=10;
							}
					
							if (score<=30 && score >0)
								JOptionPane.showMessageDialog(frame, "FAKE NEWS DETECTED !!!");
							else if (score <0 && score>=-30)
								JOptionPane.showMessageDialog(frame, "Real News");
								
								
								JLabel Imbunatatiti = new JLabel("Doriti sa adaugati URL-ul bazei noastre de date?");
								Imbunatatiti.setFont(new Font("Times New Roman", Font.PLAIN, 23));
								Imbunatatiti.setBounds(135, 300, 500, 35);
								frame.getContentPane().add(Imbunatatiti);
								
								JButton AdaugareURL = new JButton("Adaugare URL");
								AdaugareURL.setFont(new Font("Times New Roman", Font.PLAIN, 18));
								AdaugareURL.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {

										if(gasitdeja==1 || adaugatdeja==1) {
											JOptionPane.showMessageDialog(frame, "Site-ul este deja salvat!");
										}

										if (score > 0  && gasitdeja==0 && adaugatdeja==0) {
											LucruCuFisier.adauga(SLDTLD,"C:\\Users\\Cristian\\Desktop\\Java\\LicentaJava\\src\\Verificare\\FakeDetected.txt");
											adaugatdeja=1;
											System.out.println("Am adaugat " + SLDTLD + " " + gasitdeja);
										}
										
										if (score < 0 && gasitdeja==0 && adaugatdeja==0) {
											LucruCuFisier.adauga(SLDTLD,"C:\\Users\\Cristian\\Desktop\\Java\\LicentaJava\\src\\Verificare\\RealDetected.txt");
											adaugatdeja=1;
											System.out.println("Am adaugat " + SLDTLD);
										}
									}
								});
								AdaugareURL.setBounds(220, 350, 260, 40);
								frame.getContentPane().add(AdaugareURL);
							}
					});
					btnNewButton.setBounds(220, 250, 260, 40);
					frame.getContentPane().add(btnNewButton);
				}
			}
		});
		btnVerificareCridibilitate.setBounds(220, 200, 260, 40);
		frame.getContentPane().add(btnVerificareCridibilitate);

	}
}
