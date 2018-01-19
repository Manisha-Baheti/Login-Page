import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login extends Application 
{

    String pass="";
    String loginuser="";
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Real World User");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        Button fgt = new Button("Forgot Password");
        Button neu = new Button("New user");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(neu);
        hbBtn.getChildren().add(fgt);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
            { 
               loginuser = userTextField.getText();
               pass = pwBox.getText();
               int access = compareLogin(loginuser, pass);
                
               if(access == 1)
                 {
                  new welcome(loginuser, pass);
                 }
                
                if(access == 0)
                 {
                  actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("Sign-in details are incorrect");   
                 }
               
                 
            }
        }); 
       
        
       fgt.setOnAction(new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent e) 
            {
                new forgot();
            }
        });
        
        neu.setOnAction(new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent e) 
            {
                new SecondStage();
                
            }
        });
        
        Scene scene = new Scene(grid, 500, 475);
        primaryStage.setScene(scene);
        primaryStage.show();
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
	
    public static int compareLogin(String userlog, String userpass)
    {
	Connection con = getSQLConnection();
	boolean login = false;
	try
	{
		Statement s = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next() && login == false)
		{
			if(userlog.equalsIgnoreCase(null)|| userpass.equalsIgnoreCase(null))
                        {
                            return 0;   
                        }    
                        String uname = rows.getString("uname");
			if(uname.equalsIgnoreCase(userlog))
			{
				String pass =rows.getString("pass");
				if(pass.equals(userpass))
				{					
                                     login = true;
				}
			}
		}
	}
	catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
	        System.exit(0);
		return 2;
	}
	if(login == true)
	{
		
                return 1;
                
	}
	else
	{
		return 0;
	}
		
    }
}
