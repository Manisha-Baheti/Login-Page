import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

//import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class SecondStage extends Stage {
VBox y = new VBox();

SecondStage(){

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    
        Label u1= new Label("First Name:");
        grid.add(u1, 0, 1);
        TextField fname = new TextField();
        grid.add(fname, 1, 1);
    
        Label u2 = new Label("Last Name:");
        grid.add(u2, 0, 2);
        TextField lname = new TextField();
        grid.add(lname, 1, 2);
    
        Label u3= new Label("Date Of Birth:");
        grid.add(u3, 0, 3);
        
	DatePicker date = new DatePicker();
	grid.add(date, 1, 3);
	
	Label u4= new Label("Email:");
	grid.add(u4, 0, 4);
	TextField eadd = new TextField();
	grid.add(eadd, 1, 4);
	
	Label u5= new Label("User Name:");
	grid.add(u5, 0, 5);
	TextField uname = new TextField();
	grid.add(uname, 1, 5);
	
	Label u6= new Label("Password:");
	grid.add(u6, 0, 6);
        PasswordField pwBox = new PasswordField();
	grid.add(pwBox, 1, 6);
        
        Label u7= new Label("Confirm Password:");
	grid.add(u7, 0, 7);
        PasswordField pwBox1 = new PasswordField();
	grid.add(pwBox1, 1, 7);

        Label u8= new Label("Secret Question:");
	grid.add(u8, 0, 8);
	TextField sadd = new TextField();
	grid.add(sadd, 1, 8);
	
	Label u9= new Label("Secret Answer");
	grid.add(u9, 0, 9);
	TextField sanw = new TextField();
	grid.add(sanw, 1, 9);
        
	Button submit = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(submit);
        grid.add(hbBtn, 1, 10);
        
	Label u10= new Label("All fields are compulsory");
        u10.setTextFill(Color.FIREBRICK);
	grid.add(u10, 1, 0);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 12);
        
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
               
                
               if(fname.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter your first name"); 
                }
                
                else if(lname.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter your last name"); 
                }
                
                else if((((TextField)date.getEditor()).getText()).isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter Date of Birth"); 
                }
                
                else if(pwBox.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter your password"); 
                }
                
                else if(pwBox1.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please confirm your your password"); 
                }
                
                else if(!pwBox.getText().equalsIgnoreCase(pwBox1.getText()))
                {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Passwords Not Matching, please re-enter");
                }
               else if(eadd.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter your e-mail id"); 
                }
                
               else if(sadd.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter your secret question"); 
                }
                 
               else if(sanw.getText().isEmpty()) 
                {
                    actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Please enter your secret answer"); 
                }
               else
               {
               String loginuser = uname.getText();
               String pass = pwBox.getText();
               String first = fname.getText();
               String last = lname.getText();
               String dob = (((TextField)date.getEditor()).getText());
               String email=eadd.getText();
               String sques = sadd.getText();
               String sans = sanw.getText();
               if(!compareUname(loginuser))
               {
                   insertData(loginuser, pass, first, last, dob, email, sques, sans );
                   actiontarget.setFill(Color.FIREBRICK);
                   actiontarget.setText("New user created successfully."); 
           
               new welcome(loginuser, pass);
               }
               else
               {
                   actiontarget.setFill(Color.FIREBRICK);
               actiontarget.setText("User name already in use ");
               }
               
              }
               
            }
            
        });
        
        
        
    this.setScene(new Scene(grid, 600, 600));
    this.show();
}  


protected static Connection getSQLConnection()
    {
	Connection con = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/login";
		String user = "root";
		String password = "qwerty11";
		con = DriverManager.getConnection(url,user,password);
	} 
	catch(ClassNotFoundException e2)
	{
		e2.getException();
		System.out.println(e2.getMessage()+" failed");
		System.exit(0);
	}
	catch (SQLException e1) 
	{
		
		System.out.println(e1.getMessage());
		System.exit(0);
	}
	return con;
    }

public static boolean compareUname(String s)
{
    Connection con = getSQLConnection();
    boolean login =false;
    try
	{
		Statement v = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = v.executeQuery(select);
		while(rows.next() && login == false)
		{
                    String uname = rows.getString("uname");
			if(uname.equalsIgnoreCase(s))
			{
				login=true;
			}
                }
        }
    catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
	        System.exit(0);
		return login;
	}
    return login;
}

 public static void insertData(String userlog, String userpass, String fname, String lname, String dob, String email, String sques, String sanw)
    {
	Connection con = getSQLConnection();
	try
	{
		/**Statement s = con.createStatement();
		String select = "Insert into login(uname, pass, fname, lname, dob, email, sques, sanw) values(userlog, userpass, fname, lname, dob, email, sques, sanw)";
		s.execute(select);**/
                PreparedStatement pst;
                String query = "Insert into login(uname, pass, fname, lname, dob, email, sques, sanw) values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(query);
                
                pst.setString(1, userlog);
                pst.setString(2, userpass);
                pst.setString(3, fname);
                pst.setString(4, lname);
                pst.setString(5, dob);
                pst.setString(6, email);
                pst.setString(7, sques);
                pst.setString(8, sanw);
                
                pst.execute();
                pst.close();
                
	}
	catch(SQLException e)
	{
		System.out.println("You fucked up");
                System.out.println(e.getErrorCode());
	        System.exit(0);
		
	}
}
}

