import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpSystem implements ActionListener{
	
	private JFrame frame = new JFrame("Sign up");
	private JPanel panel = new JPanel();
	private JLabel labelName = new JLabel("Name");
	private JLabel labelUsername = new JLabel("Username");
	private JLabel labelPassword = new JLabel("Password");
	private JLabel title = new JLabel("Sign up");
	private JPasswordField nameField = new JPasswordField();
	public static  JTextField usernameField = new JTextField();
	public static JPasswordField passwordField = new JPasswordField();
	private JButton button = new JButton("Register");
	
	
	
	SignUpSystem(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,400,400,400);
		frame.setVisible(true);
		
		panel.setLayout(null);
		title.setBounds(150,20,200,60);
		title.setFont(new Font("italic", Font.PLAIN, 30));
		title.setForeground(new Color(255,255,255));
		panel.add(title, BorderLayout.CENTER);
		
		labelName.setBounds(20,90,300,40);
		labelName.setFont(new Font("italic", Font.PLAIN, 18));
		labelName.setForeground(new Color(255,255,255));
		panel.add(labelName, BorderLayout.CENTER);
		
		labelUsername.setBounds(20,140,300,40);
		labelUsername.setFont(new Font("italic", Font.PLAIN, 18));
		labelUsername.setForeground(new Color(255,255,255));
		panel.add(labelUsername, BorderLayout.CENTER);
		
		labelPassword.setBounds(20,190,300,40);
		labelPassword.setFont(new Font("italic", Font.PLAIN, 18));
		labelPassword.setForeground(new Color(255,255,255));
		panel.add(labelPassword, BorderLayout.CENTER);
		
		nameField.setBounds(120,100,210,25);
		nameField.setFont(new Font("italic", Font.ITALIC, 16 ));
		nameField.setEchoChar((char)0);
		panel.add(nameField);
		
		usernameField.setBounds(120,150,210,25);
		usernameField.setFont(new Font("italic", Font.ITALIC, 16 ));
		panel.add(usernameField);
		
		passwordField.setBounds(120,200,210,25);
		passwordField.setFont(new Font("italic", Font.ITALIC, 16 ));
		panel.add(passwordField);
		
		button.setBounds(100,275,180,25);
		button.setFont(new Font("italic", Font.BOLD, 13));
		button.setBackground(new Color(204,204,204));
		button.setForeground(new Color(255,255,255));
		button.addActionListener(this);
		panel.add(button);
		
		frame.add(panel, BorderLayout.CENTER);
	}
	
	public static String saltedHash(String passwordInput, String salt) throws Exception {
		
		byte[] saltBytes = Base64.getDecoder().decode(salt);
		
		KeySpec spec = new PBEKeySpec(passwordInput.toCharArray(), saltBytes, 65536, 128);
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hash = factory.generateSecret(spec).getEncoded();
		Base64.Encoder enc2 = Base64.getEncoder();
	
	return enc2.encodeToString(hash);
	}
	
	public static String getSalt() {
		
		SecureRandom random = new SecureRandom();
		
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		Base64.Encoder enc1 = Base64.getEncoder();
		
	return enc1.encodeToString(salt);
	}


	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == button) {
			try {
				
					JDBCConnection connection = new JDBCConnection();
					
				}
				
			 catch (Exception e1) {
				
				e1.printStackTrace();
			
		}
				     
					
		
			
		}
	}
}
