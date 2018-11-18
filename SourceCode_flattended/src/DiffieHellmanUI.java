import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class DiffieHellmanUI implements ActionListener {

	 static JFrame frmD;
	 static JTextField primeTextField;
	  static JTextField PRtextField;
	  static JTextField sharedKeyTF;
	  JButton btnCalculate;
	  JButton btnShowPrivateKeys;
	  JButton btnShowPublicKeys;
	  static JTextField txtPrivateKeys1;
	  static JTextField txtPrivateKeys2;
	  static JTextField txtPublicKeys1;
	  static JTextField txtPublicKeys2;
	  static JTextField txtCskprime;
	  static JTextField tfCSK_PR;
	  static JTextField textFieldA;
	  static JTextField textFieldB;
	  static JTextField sharedKeyTFC;
	  JButton btnCrack;
	  JButton btnShowPrivateKeysC;
	  static JTextField txtPrivateKeysC1;
	  static JTextField txtPrivateKeysC2;
	  
	  JButton isPrime;
	  JButton isPrimitiveRoot;







	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					
				//	javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel");
					DiffieHellmanUI window = new DiffieHellmanUI();
					window.frmD.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DiffieHellmanUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmD = new JFrame();
		frmD.getContentPane().setBackground(Color.WHITE);
		frmD.setTitle("DiffieHellman");
		frmD.setBounds(100, 100, 515, 416);
		frmD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmD.getContentPane().setLayout(null);
		ButtonGroup group = new ButtonGroup();

		
		JPanel homePanel = new JPanel();
		homePanel.setBackground(Color.WHITE);
		homePanel.setBounds(0, 0, 499, 377);
		frmD.getContentPane().add(homePanel);
		homePanel.setLayout(null);
		
		JLabel lblWelcomeToDiffie = new JLabel("Welcome to Diffie Hellman Protocol");
		lblWelcomeToDiffie.setForeground(Color.RED);
		lblWelcomeToDiffie.setFont(new Font("Modern No. 20", Font.BOLD, 18));
		lblWelcomeToDiffie.setBounds(93, 43, 307, 20);
		homePanel.add(lblWelcomeToDiffie);
		
		JLabel lblNewLabel = new JLabel("*Please Choose Which Operation You Want to Use?");
		lblNewLabel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 14));
		lblNewLabel.setBounds(72, 103, 360, 16);
		homePanel.add(lblNewLabel);
		
		JRadioButton rdbtnCalculateSharedKey = new JRadioButton("Calculate a Shared Key");
		rdbtnCalculateSharedKey.setBackground(Color.WHITE);
		rdbtnCalculateSharedKey.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		rdbtnCalculateSharedKey.setBounds(168, 161, 185, 23);
		homePanel.add(rdbtnCalculateSharedKey);
		
		JRadioButton rdbtnCrackSharedKey = new JRadioButton("Crack a Shared Key");
		rdbtnCrackSharedKey.setBackground(Color.WHITE);
		rdbtnCrackSharedKey.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		rdbtnCrackSharedKey.setBounds(168, 202, 169, 23);
		homePanel.add(rdbtnCrackSharedKey);
		
		group.add(rdbtnCalculateSharedKey);
		group.add(rdbtnCrackSharedKey);
		
		
		JPanel CalculateSK = new JPanel();
		CalculateSK.setBackground(Color.WHITE);
		CalculateSK.setBounds(0, 1, 499, 376);
		frmD.getContentPane().add(CalculateSK);
		CalculateSK.setLayout(null);
		
		JLabel lblCalculateAShared = new JLabel("Calculate a Shared Key");
		lblCalculateAShared.setBounds(119, 20, 265, 20);
		lblCalculateAShared.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculateAShared.setForeground(Color.RED);
		lblCalculateAShared.setFont(new Font("Modern No. 20", Font.BOLD, 18));
		CalculateSK.add(lblCalculateAShared);
		
		JLabel lblPrimeNumber = new JLabel("Enter a Prime Number 'p'");
		lblPrimeNumber.setBounds(25, 56, 170, 55);
		lblPrimeNumber.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CalculateSK.add(lblPrimeNumber);
		
		JLabel lblPrimetiveRoot = new JLabel("Enter a Primitive Root 'a'");
		lblPrimetiveRoot.setBounds(25, 106, 170, 55);
		lblPrimetiveRoot.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CalculateSK.add(lblPrimetiveRoot);
		
		JLabel lblTheSharedKey = new JLabel("The Shared Key");
		lblTheSharedKey.setBounds(25, 188, 104, 55);
		lblTheSharedKey.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CalculateSK.add(lblTheSharedKey);
		
		primeTextField = new JTextField();
		primeTextField.setBounds(190, 70, 100, 26);
		CalculateSK.add(primeTextField);
		primeTextField.setColumns(10);
		
		
		
		PRtextField = new JTextField();
		PRtextField.setColumns(10);
		PRtextField.setBounds(190, 120, 100, 26);
		CalculateSK.add(PRtextField);
		
		isPrime = new JButton("Is it Prime?");
		isPrime.setBounds(295, 70, 170, 24);
		CalculateSK.add(isPrime);
		isPrime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!DiffieHellman.isPrime(new BigInteger(primeTextField.getText()))) {
					 errMsgPrime();
					 primeTextField.setText("");
					 PRtextField.setText("");
				}
				else
					NoerrMsg();
			}
		});
		
		
		isPrimitiveRoot = new JButton("Is it Primitive Root?");
		isPrimitiveRoot.setBounds(295, 120, 170, 24);
		CalculateSK.add(isPrimitiveRoot);
		isPrimitiveRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!DiffieHellman.isPrimitiveRoot(new BigInteger(PRtextField.getText()), new BigInteger(primeTextField.getText()))) {
					errMsgPrimitiveRoot();
					PRtextField.setText("");
				}
				else
					NoerrMsg();
			}
		});
		
		sharedKeyTF = new JTextField();
		sharedKeyTF.setEditable(false);
		sharedKeyTF.setColumns(10);
		sharedKeyTF.setBounds(190, 202, 170, 26);
		CalculateSK.add(sharedKeyTF);
	
		
		txtPrivateKeys1 = new JTextField();
		txtPrivateKeys1.setEditable(false);
		txtPrivateKeys1.setColumns(10);
		txtPrivateKeys1.setBounds(110, 275, 165, 26);
		CalculateSK.add(txtPrivateKeys1);
		
		txtPrivateKeys2 = new JTextField();
		txtPrivateKeys2.setEditable(false);
		txtPrivateKeys2.setColumns(10);
		txtPrivateKeys2.setBounds(110, 300, 165, 26);
		CalculateSK.add(txtPrivateKeys2);
		
		txtPublicKeys1 = new JTextField();
		txtPublicKeys1.setEditable(false);
		txtPublicKeys1.setColumns(10);
		txtPublicKeys1.setBounds(280, 275, 165, 26);
		CalculateSK.add(txtPublicKeys1);
		
		txtPublicKeys2 = new JTextField();
		txtPublicKeys2.setEditable(false);
		txtPublicKeys2.setColumns(10);
		txtPublicKeys2.setBounds(280, 300, 165, 26);
		CalculateSK.add(txtPublicKeys2);
		
		
		
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(190, 165, 170, 23);
		CalculateSK.add(btnCalculate);
		
		btnShowPrivateKeys = new JButton("Show the Private Keys");
		btnShowPrivateKeys.setBounds(110, 240, 165, 23);
		CalculateSK.add(btnShowPrivateKeys);
		btnShowPrivateKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiffieHellman.showPrivateKeys();
			}
		});
		
		btnShowPublicKeys = new JButton("Show the Public Keys");
		btnShowPublicKeys.setBounds(280, 240, 165, 23);
		CalculateSK.add(btnShowPublicKeys);
		btnShowPublicKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiffieHellman.showPublicKeys();
			}
		});
		
				
		
		JButton btnBack1 = new JButton("Back");
		btnBack1.setBounds(233, 335, 89, 23);
		CalculateSK.add(btnBack1);
		btnBack1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(true);	
				CalculateSK.setVisible(false);
				primeTextField.setText("");
				PRtextField.setText("");
				sharedKeyTF.setText("");
				txtPrivateKeys1.setText("");
				txtPrivateKeys2.setText("");
				txtPublicKeys1.setText("");
				txtPublicKeys2.setText("");
				
			}
		});
		btnCalculate.addActionListener(this);
		
		
		JPanel 	CrackSK = new JPanel();
		CrackSK.setBackground(Color.WHITE);
		CrackSK.setBounds(0, 1, 499, 376);
		frmD.getContentPane().add(CrackSK);
		CrackSK.setLayout(null);
		
		
		JLabel lblCrackASharedKey = new JLabel("Crack a Shared Key");
		lblCrackASharedKey.setBounds(175, 20, 265, 20);
		lblCrackASharedKey.setForeground(Color.RED);
		lblCrackASharedKey.setFont(new Font("Modern No. 20", Font.BOLD, 18));
		CrackSK.add(lblCrackASharedKey);
		
		
		
		JLabel lblEnterAPrime = new JLabel("Enter a Prime Number 'p'");
		lblEnterAPrime.setBounds(25, 56, 170, 55);
		lblEnterAPrime.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CrackSK.add(lblEnterAPrime);
		
		JLabel lblPrimitiveRootA = new JLabel("Enter a Primitive Root 'a'");
		lblPrimitiveRootA.setBounds(25, 88, 170, 55);
		lblPrimitiveRootA.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CrackSK.add(lblPrimitiveRootA);
		
		JLabel lblEnterPublicKeyA = new JLabel("Enter Public Key 'A'");
		lblEnterPublicKeyA.setBounds(25, 118, 170, 55);
		lblEnterPublicKeyA.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CrackSK.add(lblEnterPublicKeyA);
		
		JLabel lblEnterPublicKeyB = new JLabel("Enter Public Key 'B'");
		lblEnterPublicKeyB.setBounds(25, 148, 170, 55);
		lblEnterPublicKeyB.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CrackSK.add(lblEnterPublicKeyB);
		
		JLabel lblTheSharedKey_1 = new JLabel("The Shared Key is");
		lblTheSharedKey_1.setBounds(25, 208, 170, 55);
		lblTheSharedKey_1.setFont(new Font("Modern No. 20", Font.BOLD, 12));
		CrackSK.add(lblTheSharedKey_1);
		
		txtCskprime = new JTextField();
		txtCskprime.setBounds(190, 70, 130, 26);
		CrackSK.add(txtCskprime);
		txtCskprime.setColumns(10);
		
		isPrime = new JButton("Is it Prime?");
		isPrime.setBounds(330, 70, 150, 24);
		CrackSK.add(isPrime);
		isPrime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!DiffieHellman.isPrime(new BigInteger(txtCskprime.getText()))) {
					 errMsgPrime();
					 txtCskprime.setText("");
					 tfCSK_PR.setText("");
				}
				else
					NoerrMsg();
			}
		});
		
		
		tfCSK_PR = new JTextField();
		tfCSK_PR.setColumns(10);
		tfCSK_PR.setBounds(190, 100, 130, 26);
		CrackSK.add(tfCSK_PR);
		
		
		isPrimitiveRoot = new JButton("Is it Primitive Root?");
		isPrimitiveRoot.setBounds(330, 101, 150, 24);
		CrackSK.add(isPrimitiveRoot);
		isPrimitiveRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!DiffieHellman.isPrimitiveRoot(new BigInteger(tfCSK_PR.getText()), new BigInteger(txtCskprime.getText()))) {
					errMsgPrimitiveRoot();
					tfCSK_PR.setText("");
				}
				else
					NoerrMsg();
			}
		});
		
		textFieldA = new JTextField();
		textFieldA.setColumns(10);
		textFieldA.setBounds(190, 130, 130, 26);
		CrackSK.add(textFieldA);
		
		textFieldB = new JTextField();
		textFieldB.setColumns(10);
		textFieldB.setBounds(190, 160, 130, 26);
		CrackSK.add(textFieldB);
		
		
		sharedKeyTFC = new JTextField();
		sharedKeyTFC.setEditable(false);
		sharedKeyTFC.setColumns(10);
		sharedKeyTFC.setBounds(190, 220, 130, 26);
		CrackSK.add(sharedKeyTFC);
		
		txtPrivateKeysC1 = new JTextField();
		txtPrivateKeysC1.setEditable(false);
		txtPrivateKeysC1.setColumns(10);
		txtPrivateKeysC1.setBounds(110, 285, 165, 26);
		CrackSK.add(txtPrivateKeysC1);
		
		txtPrivateKeysC2 = new JTextField();
		txtPrivateKeysC2.setEditable(false);
		txtPrivateKeysC2.setColumns(10);
		txtPrivateKeysC2.setBounds(280, 285, 165, 26);
		CrackSK.add(txtPrivateKeysC2);
		
		
		
		btnCrack = new JButton("Crack");
		btnCrack.setBounds(185, 190, 170, 23);
		CrackSK.add(btnCrack);
		
		
		btnShowPrivateKeysC = new JButton("Show the Private Keys");
		btnShowPrivateKeysC.setBounds(110, 255, 335, 23);
		CrackSK.add(btnShowPrivateKeysC);
		btnShowPrivateKeysC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiffieHellman.showPrivateKeysC();
			}
		});
		
		
		 
		
		
		JButton btnBack2 = new JButton("Back");
		btnBack2.setBounds(233, 335, 89, 23);
		CrackSK.add(btnBack2);
		btnBack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.setVisible(true);	
				CrackSK.setVisible(false);
				txtCskprime.setText("");
				tfCSK_PR.setText("");
				textFieldA.setText("");
				textFieldB.setText("");
				sharedKeyTFC.setText("");
				txtPrivateKeysC1.setText("");
				txtPrivateKeysC2.setText("");
			}
		});
		btnCrack.addActionListener(this);

		JButton btnProcess = new JButton("Proceed");
		btnProcess.setFont(new Font("Sitka Subheading", Font.BOLD, 14));
		btnProcess.setBounds(101, 273, 299, 23);
		homePanel.add(btnProcess);
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCalculateSharedKey.isSelected()){
					CalculateSK.setVisible(true);
					homePanel.setVisible(false);
				}else if(rdbtnCrackSharedKey.isSelected()){
					CrackSK.setVisible(true);
					homePanel.setVisible(false);
				}
			}
		});
		
		
		homePanel.setVisible(true);
		CalculateSK.setVisible(false);
		CrackSK.setVisible(false);

	}
	
	public static void NoerrMsg(){
		JOptionPane.showMessageDialog(frmD, "It is A Valid Number :)");
	}
	
	public static void errMsgPrime(){
		JOptionPane.showMessageDialog(frmD, "Invalid Number!, this is not a PRIME Number .. Please Re-Enter a valid one.");
	}
	
	public static void errMsgPrimitiveRoot(){
		JOptionPane.showMessageDialog(frmD, "Invalid Number!, this is not a Primitive of 'p' .. Please Re-Enter a valid one.");
	}
	
	public static void errMsg(){
		JOptionPane.showMessageDialog(frmD, "ERROR: Invalid input!");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCalculate))
			try {
				if(!primeTextField.getText().isEmpty() && !PRtextField.getText().isEmpty())
				DiffieHellman.runComputeSK();
				else
					errMsg();
			} catch (NumberFormatException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		else if(e.getSource().equals(btnCrack))
			try {
				DiffieHellman.runCrackSK();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
	}
}
