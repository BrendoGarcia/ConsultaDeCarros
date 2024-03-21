package tel;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JPanel;

public class carros {

	private JFrame frame;
	private JFrame pagina2;
	private JTextField cpf;
	private JPasswordField senha;
	private static final carros banco = null;
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;
	int verificar;
	String nomevendedor;
	String status;
	private JTextField campoplaca;
	private JTextField camponome;
	private JTextField campocpf;
	private JPasswordField camposenha;

	
	// inicando coneccaoa ao banco
	
	public void conectar() {
	    //Conectando ao banco de dados//
	  //tenho duas opções da banco de dados
	    String servidor = "jdbc:mysql://sql10.db4free.net:3306/projetinho";
	    String usuario = "brendofcghh";
	    String senha = "qwer1234";

	    try {

	      this.connection = DriverManager.getConnection(servidor, usuario, senha);
	      this.statement = this.connection.createStatement();
	    } catch (Exception e) {
	      System.out.println("Erro: " + e.getMessage());
	    }

	  }
	
	//fechando conecção ao banco
	
	public void fecharconeccao() throws SQLException {
	    //parametro para fechar o conecção do banco de dados//
	    this.connection.close();
	  }
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					carros window = new carros();
					window.frame.setVisible(true);
					window.pagina2.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public carros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		pagina2 = new JFrame();
		pagina2.setBounds(100, 100, 706, 576);
		pagina2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pagina2.getContentPane().setLayout(null);
		pagina2.setVisible(false);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 706, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cpf = new JTextField();
		cpf.setBounds(266, 215, 159, 20);
		frame.getContentPane().add(cpf);
		cpf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Bem vindo ao sistema");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 690, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Faça o Login");
		lblNewLabel_1.setBounds(302, 171, 91, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(236, 218, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SENHA");
		lblNewLabel_3.setBounds(225, 246, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		JButton botaoentrar = new JButton("ENTRAR");
		botaoentrar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				
				
				JButton botãoplaca = new JButton("Consultar");
				botãoplaca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						
						
						//cadastro usuario

						
						
						JLabel ResultadoConsulta = new JLabel("");
						ResultadoConsulta.setBounds(10, 83, 427, 443);
						frame.getContentPane().add(ResultadoConsulta);
						ResultadoConsulta.setVisible(false);
						
						JLabel resultadoplaca = new JLabel("");
						resultadoplaca.setHorizontalAlignment(SwingConstants.CENTER);
						resultadoplaca.setBounds(0, 190, 690, 14);
						frame.getContentPane().add(resultadoplaca);
						resultadoplaca.setVisible(false);
						
						JLabel resultadoplacaverde = new JLabel("");
						resultadoplacaverde.setHorizontalAlignment(SwingConstants.CENTER);
						resultadoplacaverde.setBounds(0, 190, 690, 14);
						frame.getContentPane().add(resultadoplacaverde);
						resultadoplacaverde.setVisible(false);
						
						
						String a = "Veiculo não encontrado";
						String b = "Veiculo encontrado";
						conectar();
						Statement s = null;
						//codigo consultar placa
						
						try {
							s = (Statement) connection.createStatement();
						      ResultSet r = null;
						      r = s.executeQuery("Select * from carro WHERE placa = " +"'"+ campoplaca.getText()+"'" );
						      
						
						      if (!r.isBeforeFirst() ) {
						    	  resultadoplaca.setText(a);
						    	 resultadoplaca.setForeground(Color.red);
						    	 resultadoplaca.setVisible(true);
						    	 resultadoplacaverde.setVisible(false);
						    	  
						      }
						      
						      
						      else {
						    	  
						    	  resultadoplaca.setDefaultLocale(null);
						    	 // 584sfm4
						    	  while (r.next()) {
						    		  
						    		  ResultadoConsulta.setText(r.getString("nomecarroid") + " | " + " \n "+"Nome do Dono: " + r.getString("NomeDono") + " | "+ " \n "+"CPF do Dono: " + r.getString("cpfDono"));
						    		  ResultadoConsulta.setVisible(true);
						    		 resultadoplacaverde.setText(b);
						    		 resultadoplacaverde.setForeground(Color.green);
						    		 resultadoplacaverde.setVisible(true);
						    		 resultadoplaca.setVisible(false);
						    	      }
						      }
						      
						      
						      
						      r.close();
						      s.close();
						      fecharconeccao();				   
						      } catch (SQLException e1) {
						      e1.printStackTrace();
						    }
						
					}
					
				});
				
				
				JButton botaoCadastroCarros = new JButton("Cadastrar Carro");
				
				
				//cadatro do usuario
			
				
				JButton Bottaoindocadastro = new JButton("Cadastrar Usuario");
				Bottaoindocadastro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

						frame.setVisible(false);
						pagina2.setVisible(true);
						
						JPanel painelCadatroUsuario = new JPanel();
						painelCadatroUsuario.setBounds(10, 11, 670, 515);
						pagina2.getContentPane().add(painelCadatroUsuario);
						painelCadatroUsuario.setLayout(null);
						painelCadatroUsuario.setVisible(true);
						
						JLabel textNome = new JLabel("NOME");
						textNome.setBounds(10, 88, 96, 14);
						painelCadatroUsuario.add(textNome);
						textNome.setVisible(true);
						
						JLabel TextoCPF = new JLabel("CPF");
						TextoCPF.setBounds(10, 117, 96, 14);
						painelCadatroUsuario.add(TextoCPF);
						TextoCPF.setVisible(true);
						
						JLabel TextoSenha = new JLabel("SENHA");
						TextoSenha.setBounds(10, 142, 96, 14);
						painelCadatroUsuario.add(TextoSenha);
						TextoSenha.setVisible(true);
						
						camponome = new JTextField();
						camponome.setBounds(59, 85, 160, 20);
						painelCadatroUsuario.add(camponome);
						camponome.setColumns(10);
						camponome.setVisible(true);
						
						campocpf = new JTextField();
						campocpf.setBounds(59, 114, 160, 20);
						painelCadatroUsuario.add(campocpf);
						campocpf.setColumns(10);
						campocpf.setVisible(true);
						
						//efetuar cadastro no banco//
						
						JButton enviarcadastro = new JButton("Cadastrar");
						enviarcadastro.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								conectar();
								Statement s = null;
								
								
								try {
										System.out.println(camponome.getText() +","+campocpf.getText()+","+camposenha.getText());
										System.out.println("INSERT INTO usuario (nome, cpf, senha) VALUES ("+camponome.getText() + "," +campocpf.getText()+","+camposenha.getText()+")");
										
								      String sql = ("INSERT INTO usuario (nome, cpf, senha) VALUES ("+ "'"+camponome.getText() +"'"+ ", " +"'"+ campocpf.getText()+"'"+", "+ "'"+camposenha.getText()+"'"+")");
								      PreparedStatement stmt = connection.prepareStatement(sql);
								      
								      stmt.execute(); //executa comando   
								      stmt.close(); 
								      fecharconeccao();				   
								      } catch (SQLException e1) {
								      e1.printStackTrace();
								    }
								
								
								
							}});
						
						
						
						enviarcadastro.setBounds(59, 192, 160, 23);
						painelCadatroUsuario.add(enviarcadastro);
						
						camposenha = new JPasswordField();
						camposenha.setBounds(59, 142, 160, 20);
						painelCadatroUsuario.add(camposenha);
						enviarcadastro.setVisible(true);
						
					}
					
				});	
				
				Bottaoindocadastro.setBounds(232, 481, 196, 23);
				frame.getContentPane().add(Bottaoindocadastro);
				Bottaoindocadastro.setVisible(false);
				
				botãoplaca.setBounds(482, 84, 89, 23);
				frame.getContentPane().add(botãoplaca);
				botãoplaca.setVisible(false);
				
				campoplaca = new JTextField();
		  		campoplaca.setBounds(225, 84, 200, 20);
		  		frame.getContentPane().add(campoplaca);
		  		campoplaca.setColumns(10);
		  		campoplaca.setVisible(false);
		  		
		  		JLabel consulplaca = new JLabel("Consultar Placa");
		  		consulplaca.setHorizontalAlignment(SwingConstants.CENTER);
		  		consulplaca.setBounds(225, 59, 200, 14);
		  		frame.getContentPane().add(consulplaca);
		  		consulplaca.setVisible(false);
				
				JLabel StatusLogin = new JLabel();
				StatusLogin.setHorizontalAlignment(SwingConstants.CENTER);
				StatusLogin.setBounds(-2, 326, 692, 14);
				frame.getContentPane().add(StatusLogin);
				conectar();
				Statement s = null;
				
				//System.out.println("teste" + cpf.getText() +":"+ senha.getText());
				
				
				try {
					s = (Statement) connection.createStatement();
				      ResultSet r = null;
				      r = s.executeQuery("Select * from usuario WHERE cpf = " + cpf.getText() + " and " + "senha = " + senha.getText() );
				      
				
				      if (!r.isBeforeFirst() ) {    
				    	  StatusLogin.setText("login invalido"); 
				    	  StatusLogin.setForeground(Color.red);;
				    	  
				      }
				      else {
				    	  botaoentrar.setVisible(false);
				    	  lblNewLabel_3.setVisible(false);
				    	  lblNewLabel_2.setVisible(false);
				    	  lblNewLabel_1.setVisible(false);
				    	  senha.setVisible(false);
				    	  cpf.setVisible(false);
				    	  StatusLogin.setText("Login Bem realizado");
				    	  StatusLogin.setForeground(Color.green);
				    	  campoplaca.setVisible(true);
				    	  consulplaca.setVisible(true);
				    	  botãoplaca.setVisible(true);
				    	  Bottaoindocadastro.setVisible(true);
				    	  
				    	  
				    	  
				      }
				      r.close();
				      s.close();
				      fecharconeccao();
				    } catch (SQLException e1) {
				      e1.printStackTrace();
				    }
				
				
				
			}
		});
		
		
		
		botaoentrar.setBounds(302, 274, 89, 23);
		frame.getContentPane().add(botaoentrar);
		
		senha = new JPasswordField();
		senha.setBounds(266, 244, 157, 20);
		frame.getContentPane().add(senha);
		
		//visualizar conteudo
		
		
		
		
		

		
		
		
		
			
	
	}
}
