import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

class InputTableName {



    public static void display(String title) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label name = new Label("Table Name:");
        TextField inputName = new TextField ();
        HBox hbName = new HBox(10);
        hbName.getChildren().addAll(name, inputName);

        Label header = new Label("Table Header:" + "\n" +
                        "(Use , to seperate columns)");
        TextField inputHeader = new TextField ();
        VBox vbHeader = new VBox(10);
        vbHeader.getChildren().addAll(header, inputHeader);

        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
        confirm.setOnAction(e -> {
            String tableName = inputName.getText();
            String getHeader = inputHeader.getText();
            String[] tableHeader = getHeader.split(",");
            if(tableName != null && getHeader != null && tableHeader != null){
                Table newTable = new Table(tableName);
                newTable.setHeader(tableHeader);
                File createFile = new File(newTable);
                if(createFile.writeToFile());
            }
            window.close();
        });
        cancel.setOnAction(e -> {
            window.close();
        });
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(hbName, vbHeader, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}