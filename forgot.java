import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class forgot extends Stage 
{
    
    VBox y = new VBox();
     String ans="";
     String ques="";
     String email="";
    
     forgot()
    {

	GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label u1= new Label("Enter your Email");
        grid.add(u1, 0, 1);
        TextField eadd = new TextField();
	grid.add(eadd, 1, 1);
        
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btn);
        
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
         btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
             { 
                email = eadd.getText();
                int access = compareEmail(email);
                ques=returnSecretQuestion(email);
               if(access==1)
                {
                    //new squestion();
                    compareAnswer();
                }
                
                {
                  actiontarget.setFill(Color.FIREBRICK);
                  actiontarget.setText("E-mail is incorrect");   
                 }
            }
        });
        
        this.setScene(new Scene(grid, 600, 600));
        this.show();
        
    }  
     
      public void compareAnswer()
    {

	GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label u1= new Label(ques);
        grid.add(u1, 0, 2);
        TextField sanw = new TextField();
        grid.add(sanw, 1, 2);
      
                    
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btn);
        
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
         btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent e) 
             { 
                ans=sanw.getText();;
                int access1 = compareAns(ans, ques);
                
                if(access1 == 1)
                        {
                           welcome();
                        }

                        else if(access1 == 0)
                         {
                         actiontarget.setFill(Color.FIREBRICK);
                         actiontarget.setText("Answer is incorrect");   
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
    
    
 
    public static int compareEmail(String email)
    {
	Connection con = getSQLConnection();
	boolean mail = false;
	try
	{
		Statement s = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next() && mail == false)
		{
			if(email.equalsIgnoreCase(null))
                        {
                            return 0;   
                        }    
                        String emailid = rows.getString("email");
			if(email.equalsIgnoreCase(emailid))
			{					
                             mail = true;
			}
		}
	}
	catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
	        System.exit(0);
		return 2;
	}
	if(mail == true)
	{
            return 1;
        }
	else
	{
            return 0;
	}
		
    }
 
 
    public static String returnSecretQuestion(String email)
    {
	Connection con = getSQLConnection();
	
	try
	{
		String Squestion="";
                Statement s = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next() )
		{
			  String emailid = rows.getString("email");
			if(email.equalsIgnoreCase(emailid))
			{					
                                     Squestion=rows.getString("sques");
                                     
			}
                }
                return Squestion;
	}
	catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
	        System.exit(0);
		return "Hello";
	}
    }
  
    public static int compareAns(String ans, String ques)
    {
	Connection con = getSQLConnection();
	 boolean sans = false;
	try
	{
		Statement s = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next() && sans == false)
		{
			if(ans.equalsIgnoreCase(null))
                        {
                            return 0;   
                        }    
                        
                        String quest= rows.getString("sques");
                        
                        if(quest.equalsIgnoreCase(ques))
			{
                            String answ = rows.getString("sanw");
                            System.out.println(answ);
                            System.out.println(ans);
                            if(answ.equalsIgnoreCase(ans))
                            {					
                                     sans = true;
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
	if(sans == true)
	{
            return 1;
        }
	else
	{
            return 0;
	}
    }
    
    public void welcome()
{

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    
    Label u1= new Label("Welcome");
    grid.add(u1, 0, 1);
    Label u2= new Label(printUsername(email));
    grid.add(u2, 0, 3);
    Label u3= new Label(printPassword(email));
    grid.add(u3, 0, 5);
	
   
    this.setScene(new Scene(grid, 600, 600));
    this.show();
}
    
    public static String printUsername(String email)
    {
	Connection con = getSQLConnection();
	try
	{
		String username="";
                Statement s = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next())
		{   
                      String emailid = rows.getString("email");
			if(email.equalsIgnoreCase(emailid))
			{
                            username = rows.getString("uname");
                        }
                        
                       //String pass = rows.getString("pass");
                }
                return username;
	}
	catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
	        System.exit(0);
                return "Hello";
	}
    }
 
  public static String printPassword(String email)
    {
	Connection con = getSQLConnection();
	try
	{
		String password="";
                Statement s = con.createStatement();
		String select = "SELECT * FROM login;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next())
		{   
                      String emailid = rows.getString("email");
			if(email.equalsIgnoreCase(emailid))
			{
                            password = rows.getString("pass");
                        }
                        //String pass = rows.getString("pass");
                }
                return password;
	}
	catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
	        System.exit(0);
                return "Hello";
	}
    }
}



		
    

