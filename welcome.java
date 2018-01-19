import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class welcome extends Stage {
    
    VBox y = new VBox();

welcome(String user, String pass)
{

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    
    Label u1= new Label("Welcome");
    grid.add(u1, 0, 1);
    Label u2= new Label(user);
    grid.add(u2, 0, 3);
    Label u3= new Label(pass);
    grid.add(u3, 0, 5);
	
   
    this.setScene(new Scene(grid, 600, 600));
    this.show();
} 
}


	
		
    

