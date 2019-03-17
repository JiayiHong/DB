
import javafx.application.*;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.VPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;
import java.io.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Slider;

public class Interface extends Application{

	Scene scene;
	Button createTable, refresh;
	Databases database = new Databases();
	TableView<String[]> table;
	Table operatingTable;

	public void start(Stage stage) {

		stage.setTitle("Welcome to my database.");

		VBox leftLayout = new VBox(10);
		leftLayout.setPadding(new Insets(10,10,10,10));
		leftLayout.prefWidthProperty().bind(stage.widthProperty().multiply(0.2));

		createTable = new Button("Create"+"\n"+"table");
		createTable.setOnAction(e -> {
			InputTableName.display("Creating a Table");
			refresh.fire();
		});
		leftLayout.getChildren().add(createTable);

		// Update all the tables;
		refresh = new Button("Refresh");
		refresh.setOnAction(e -> {
			database = new Databases();
			start(stage);
		});
		leftLayout.getChildren().add(refresh);
		Separator sepHor = new Separator();
		sepHor.setValignment(VPos.CENTER);
		leftLayout.getChildren().add(sepHor);
		Label label1 = new Label("Current Tables:");
		leftLayout.getChildren().add(label1);

		VBox rightLayout = new VBox(10);
		createTableButton(leftLayout, rightLayout);
		
		HBox layout = new HBox();
		layout.setPadding(new Insets(10,10,10,10));
		layout.getChildren().addAll(leftLayout, rightLayout);

		scene = new Scene(layout, 800, 600);

		stage.setScene(scene);
		stage.show();
	}

	void createTableButton(VBox leftLayout, VBox rightLayout) {
		for (Map.Entry<String, Table> entry : database.getTables().entrySet()) {
			String tablename = entry.getKey();
			Table currentTable = entry.getValue();
			Button button = new Button(tablename);
			button.setOnAction(e -> {
				rightLayout.getChildren().clear();
				Label tableNameLabel = new Label(tablename);
				rightLayout.getChildren().add(tableNameLabel);
				Label tableKey = new Label("Key: " + labelKey(currentTable));
				rightLayout.getChildren().add(tableKey);
				updateRightTable(currentTable);
				rightLayout.getChildren().add(table);
				rightLayout.getChildren().add(showOperatingButtons());
				operatingTable = currentTable;
			});
			leftLayout.getChildren().add(button);
		}
	}

	void updateRightTable(Table currentTable) {
		table = new TableView<>();
		table.setItems(getItems(currentTable.getRecords()));
		int i = 0;
		for (String headName : currentTable.getHeader()) {
			setTableColumn(headName, i);
			i++;
		}
	}


	void setTableColumn(String headName, int i) {
		TableColumn<String[], String> column = new TableColumn<>(headName);
		column.setMinWidth(200);
		// column.setCellValueFactory(new PropertyValueFactory<>(headName));
		column.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue()[i]));
		table.getColumns().add(column);
	}


	// Get the table's content
	ObservableList<String[]> getItems(Records tableRecords) {
		ObservableList<String[]> records = FXCollections.observableArrayList();
		for (int i = 0; i < tableRecords.getRecordsNumber(); i++) {
			records.add(tableRecords.getCertainRecord(i));
		}
		return records;
	}

	HBox showOperatingButtons() {
		HBox rightLowerLayout = new HBox(10);
		Button addRow = new Button("Add row");
		Button deleteRow = new Button("Delete row");
		Button editRow = new Button("Edit");
		addRow.setOnAction(e -> clickAddRow());
		deleteRow.setOnAction(e -> clickDeleteRow());
		editRow.setOnAction(e -> clickEdit());
		rightLowerLayout.getChildren().addAll(addRow, deleteRow, editRow);
		return rightLowerLayout;
	}

	void clickAddRow() {

		InputNewRow.display(operatingTable);
		updateFile();
	}

	void clickDeleteRow() {

		InputDeleteRow.display(operatingTable);
		updateFile();
	}

	void clickEdit() {
		
		InputEditRow.display(operatingTable);
		updateFile();
	}

	String labelKey(Table table) {

		StringBuilder key = new StringBuilder();
		for (int i : table.getKey()) {
			key.append(table.getHeader()[i]+" ");
		}
		return key.toString();
	}

	void updateFile() {
		File file = new File(operatingTable);
		if (! file.writeToFile())
			AlertBox.display("Error", "Error in updating the table.");
		else {
			refresh.fire();
		}
	}

}


