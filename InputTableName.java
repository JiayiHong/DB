import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.lang.*;
import javafx.scene.control.Button;
import javafx.event.*;
import java.util.*;


class InputTableName {

    public static void display(String title) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        // Get Table's name
        Label name = new Label("Table Name:");
        TextField inputName = new TextField ();
        HBox hbName = new HBox(10);
        hbName.getChildren().addAll(name, inputName);

        // Get Table's header
        Label header = new Label("Table Header:" + "\n" +
                        "(Use , to seperate columns)");
        TextField inputHeader = new TextField ();
        VBox vbHeader = new VBox(10);
        vbHeader.getChildren().addAll(header, inputHeader);

        Label key = new Label("Key Columns:");
        TextField inputKey = new TextField();
        VBox vbKey = new VBox(10);
        vbKey.getChildren().addAll(key, inputKey);

        // Get columns' types of the table
        Label type = new Label("Table type:" + "\n" +
                        "(Use , to seperate columns)");
        TextField inputType = new TextField ();
        Label details = new Label("Types include number, text, date and currency.");
        VBox vbType = new VBox(10);
        vbType.getChildren().addAll(type, inputType, details);


        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
        confirm.setOnAction(e -> {
            String tableName = inputName.getText();
            String getHeader = inputHeader.getText();
            String getKey = inputKey.getText();
            String getType = inputType.getText();
            String[] tableHeader = getHeader.split(",");
            String[] tableKey = getKey.split(",");
            String[] tableType = getType.split(",");
            if (tableHeader.length != tableType.length) 
                AlertBox.display("Error","Type in "+tableHeader.length+" types");
            else if (!checkKey(tableKey, tableHeader))
                AlertBox.display("Error","Key(s)' name(s) should be exactly the same with columns.");
            else if (!checkType(tableType))
                AlertBox.display("Error","Types include number, text, date and currency.");
            else if (!tableName.trim().isEmpty() && !getHeader.trim().isEmpty() &&
                    !getType.trim().isEmpty() ){
                Table newTable = new Table(tableName);
                newTable.setHeader(tableHeader);
                newTable.setType(tableType);
                newTable.setKey(getKeyNumber(tableKey, tableHeader));
                File createFile = new File(newTable);
                if (!createFile.writeToFile()) {
                    AlertBox.display("Alert","Save Table Failed!");
                };
            }
            else {
                AlertBox.display("Alert", "Please type in the empty boxes.");
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
        layout.getChildren().addAll(hbName, vbHeader, vbKey, vbType, buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setMargin(vbHeader, new Insets(20,0,10,0));
        layout.setMargin(vbType, new Insets(10,0,20,0));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    static boolean checkType(String... type) {
        
        for (String str : type) {
            System.out.println(str);
            if (!str.equals("number") && !str.equals("text") 
                && !str.equals("date") && !str.equals("currency"))
                return false;
        }
        return true;
    }

    static int[] getKeyNumber(String[] keys, String... header) {
        int[] number = new int[keys.length];
        int j = 0;
        for (String str : keys) {
            for (int i = 0; i < header.length; i++) {
                if (str.equals(header[i]))  {
                    number[j] = i;
                    j++;
                }
            }
        }
        return number;
    }

    static boolean checkKey(String[] keys, String... header) {
        for (String str : keys) {
            if (!Arrays.asList(header).contains(str))    return false;
        }
        return true;
    }

}