import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.lang.*;
import javafx.scene.control.Button;
import javafx.event.*;
import java.util.*;


class InputEditRow {

    public static void display(Table table) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Rows");
        window.setMinWidth(250);

        VBox layout = new VBox(10);

        Label instruction = new Label("Type in the record's row and column to Edit:");
        TextField input = new TextField();
        input.setPrefWidth(100);
        Label instruction1 = new Label("Type in the new value:");
        TextField value = new TextField();
        value.setPrefWidth(100);

        HBox bottomLayout = new HBox(20);
        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
        confirm.setOnAction(e -> {
            String rowinfo1 = input.getText();
            String[] toEdit = rowinfo1.split(",");
            String toupdate = value.getText();
            int[] position = new int[2];
            // System.out.println(table.getRecords().getRecordsNumber());
            for (int i = 0; i < 2; i++) {
                if (isInteger(toEdit[i])){
                    position[i] = Integer.parseInt(toEdit[i]);
                } 
                else {
                    AlertBox.display("Error", "Please type in corrent number.");
                    window.close();
                }
            }
            if (!table.updateRow(position[0], position[1], toupdate)) {
                AlertBox.display("Error", "Please type in valid value.");
                window.close();
            }
            window.close();
        });
        // System.out.println(table.getRecords().getRecordsNumber());
        cancel.setOnAction(e -> window.close());
        bottomLayout.getChildren().addAll(confirm, cancel);
        bottomLayout.setPadding(new Insets(20,0,0,0));

        layout.getChildren().addAll(instruction, input, instruction1, value, bottomLayout);
        layout.setPadding(new Insets(30,20,30,20));

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();

    }

    static boolean isInteger(String s) {
        if(s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if(Character.digit(s.charAt(i),10) < 0)
                return false;
        }
        return true;
    }

}