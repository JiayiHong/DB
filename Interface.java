
import javafx.application.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Interface extends Application{

	Scene scene1, scene2;

	public void start(Stage stage) {
		stage.setTitle("Welcome to my database.");

		Label label1 = new Label("Welcome to the first scene!");
		Button button1 = new Button("+");
		// Anonymous inner class
		button1.setOnAction(e -> stage.setScene(scene2));

		//Layout 1 - children are laid out in vertical column
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 200, 200);


		Label label2 = new Label("Welcome to the second scene!");
		Button button2 = new Button("-");
		button2.setOnAction(e -> stage.setScene(scene1));
		HBox layout2 = new HBox(20);
		layout2.getChildren().addAll(label2, button2);
		scene2 = new Scene(layout2, 300, 300);

		stage.setScene(scene1);
		stage.show();
	}
}
