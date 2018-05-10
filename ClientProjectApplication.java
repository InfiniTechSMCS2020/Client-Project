
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class ClientProjectApplication extends Application {
	private int count;
	private static int studentId;
	public static void main(String[] args){
		launch(args);
		
	}
	 
  
	
	public static void write(String s, File f) throws IOException{
		FileWriter fw = new FileWriter(f,true);
		fw.write(s);
		fw.close();
	}
	
	@Override
	public void start(Stage testStage) throws Exception {
		testStage.setTitle("Application");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		Scene scene= new Scene(grid,300,275);
		testStage.setScene(scene);
		Text label = new Text("Welcome to the Poolesville High School Counseling Department");
		grid.add(label,0,0);
		Text id = new Text("School ID:");
		grid.add(id,0,1);
		TextField idEnter = new TextField();
		grid.add(idEnter, 1, 1);
		Button signIn = new Button("Click Here");
		HBox buttonBox= new HBox(10);
		buttonBox.setAlignment(Pos.BOTTOM_CENTER);
		buttonBox.getChildren().add(signIn);
		grid.add(buttonBox, 1, 2);
		final Text actionText = new Text();
		grid.add(actionText,1,3);
		signIn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				studentId= Integer.valueOf(idEnter.getText());
				try{
					File f = new File("H:\\TestUno.txt");
					write("  Student ID:  " +studentId,f);
				}catch(IOException x){
					
				}
				
				System.out.println(studentId);
				actionText.setText("You entered an ID:  "+studentId);
				idEnter.setText("");
				/*String stringID = String.valueOf(studentId);
				Mailer mailer = new Mailer();
				mailer.send("InfiniTechSMCS2020@gmail.com","Infinity1238","jacobkiviat@gmail.com","This is a test", stringID);
				 */
				 String from= "InfiniTechSMCS2020@gmail.com";
				 String password="Infinity1238";
				 String to= "InfiniTechSMCS2020@gmail.com";
				Properties props = new Properties();    
		          props.put("mail.smtp.host", "smtp.gmail.com");    
		          props.put("mail.smtp.socketFactory.port", "465");    
		          props.put("mail.smtp.socketFactory.class",    
		                    "javax.net.ssl.SSLSocketFactory");    
		          props.put("mail.smtp.auth", "true");    
		          props.put("mail.smtp.port", "465");    
		          //get Session   
		          Session session = Session.getDefaultInstance(props,    
		           new javax.mail.Authenticator() {    
		           protected PasswordAuthentication getPasswordAuthentication() {    
		           return new PasswordAuthentication(from,password);  
		           }    
		          });  
				  //2) compose message     
				  try{  
				    MimeMessage message = new MimeMessage(session);  
				    message.setFrom(new InternetAddress(from));  
				    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
				    message.setSubject("This is a better test");  
				      
				    //3) create MimeBodyPart object and set your message text     
				    BodyPart messageBodyPart1 = new MimeBodyPart();  
				    messageBodyPart1.setText("Check the attached file");  
				      
				    //4) create new MimeBodyPart object and set DataHandler object to this object      
				    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
				  
				    String filename = "H:\\TestUno.txt";//change accordingly  
				    DataSource source = new FileDataSource(filename);  
				    messageBodyPart2.setDataHandler(new DataHandler(source));  
				    messageBodyPart2.setFileName(filename);  
				     
				     
				    //5) create  object and add MimeBodyPart objects to this object      
				    Multipart multipart = new MimeMultipart();  
				    multipart.addBodyPart(messageBodyPart1);  
				    multipart.addBodyPart(messageBodyPart2);  
				  
				    //6) set the  object to the message object  
				    message.setContent(multipart );  
				     
				    //7) send message  
				    Transport.send(message);  
				   
				   System.out.println("message sent....");  
				   }catch (MessagingException ex) {ex.printStackTrace();}  
				 }  
				  
			
			
			
		
		
		});
		testStage.show();
	}
		
		/*
		Button button = new Button();
		button.setText("Press Here");
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				count++;
				System.out.println(count);
			}
		});
		StackPane root = new StackPane();
		root.getChildren().add(button);
		testStage.setScene(new Scene(root,300,250));
		*/
		
	}
	

