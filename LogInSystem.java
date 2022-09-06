import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Base64;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;

public class LogInSystem implements ActionListener {

	private JFrame frame = new JFrame("Log In");
	private JPanel panel = new JPanel();
	private JLabel labelUsername = new JLabel("Username");
	private JLabel labelPassword = new JLabel("Password");
	private JLabel title = new JLabel("Log in");
	private JPasswordField usernameField = new JPasswordField(SwingConstants.CENTER);
	private JPasswordField passwordField = new JPasswordField();
	private JButton button = new JButton("Log in");
	private JButton button2 = new JButton("Haven't got a log in? Sign up!");
	
	
	LogInSystem(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setVisible(true);
		
		panel.setLayout(null);
	
		title.setBounds(70,25,300,100);
		title.setFont(new Font("italic", Font.PLAIN, 80));
		title.setForeground(new Color(255,255,255));
		panel.add(title, BorderLayout.CENTER);
		
		labelUsername.setBounds(20,150,300,40);
		labelUsername.setFont(new Font("italic", Font.PLAIN, 18));
		labelUsername.setForeground(new Color(255,255,255));
		panel.add(labelUsername, BorderLayout.CENTER);
		
		labelPassword.setBounds(20,200,300,40);
		labelPassword.setFont(new Font("italic", Font.PLAIN, 18));
		labelPassword.setForeground(new Color(255,255,255));
		panel.add(labelPassword, BorderLayout.CENTER);
		
		usernameField.setBounds(120,162,210,25);
		usernameField.setFont(new Font("italic", Font.ITALIC, 16 ));
		usernameField.setEchoChar((char)0);
		panel.add(usernameField);
		
		passwordField.setBounds(120,212,210,25);
		passwordField.setFont(new Font("italic", Font.ITALIC, 16 ));
		panel.add(passwordField);
		
		
		button.setBounds(20,275,100,25);
		button.setFont(new Font("italic", Font.BOLD, 13));
		button.setBackground(new Color(204,204,204));
		button.setForeground(new Color(255,255,255));
		button.addActionListener(this);
		panel.add(button);
		
		button2.setBounds(130,275,230,25);
		button2.setFont(new Font("italic", Font.BOLD, 13));
		button2.setBackground(new Color(204,204,204));
		button2.setForeground(new Color(255,255,255));
		button2.addActionListener(this);
		panel.add(button2);
		
		frame.add(panel, BorderLayout.CENTER);
	}
	
	public String saltedHash(String passwordInput, String salt) throws Exception {
			
			byte[] saltBytes = Base64.getDecoder().decode(salt);
			
			KeySpec spec = new PBEKeySpec(passwordInput.toCharArray(), saltBytes, 65536, 128);
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			
			byte[] hash = factory.generateSecret(spec).getEncoded();
			Base64.Encoder enc2 = Base64.getEncoder();
		
		return enc2.encodeToString(hash);
	}
	
	public String getSalt() {
		
		SecureRandom random = new SecureRandom();
		
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		Base64.Encoder enc1 = Base64.getEncoder();
		
	return enc1.encodeToString(salt);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//hashed password = saltedHash(password, salt from database
		String connectString = "jdbc:sqlserver://LAPTOP-CKUG71TD;Database=LogInSystem;IntegratedSecurity=false;trustServerCertificate=true;user=sa;password=Westlands01%";
		
			if(e.getSource() == button) {
				
				try {
					try(Connection connection = DriverManager.getConnection(connectString)){
							
						String sql = "SELECT * FROM [LogInSystem].[dbo].[User] WHERE Username = '"+usernameField.getText()+"'";
							
						Statement statement = connection.createStatement();
							
						ResultSet rs = statement.executeQuery(sql);
						
						while(rs.next()) {
							

							String inputPassword = saltedHash(passwordField.getText(), rs.getString("Salt"));
							
							if(inputPassword.equals(rs.getString("Password"))) {
								LoggedIn website = new LoggedIn();
								JOptionPane.showMessageDialog(frame, "You're in!");
							}else {
								JOptionPane.showMessageDialog(frame, "Invalid password or username, try again");
							}
							
						}
						connection.close();
					}
									
			
				}catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
			
		if(e.getSource() == button2) {
			SignUpSystem signUp = new SignUpSystem();	
		}
	}
}