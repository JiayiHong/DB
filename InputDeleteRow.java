import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.lang.*;
import javafx.scene.control.Button;
import javafx.event.*;
import java.util.*;


class InputDeleteRow {

    public static void display(Table table) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete Rows");
        window.setMinWidth(250);

        VBox layout = new VBox(10);
        Label instruction = new Label("Type in records' numbers to delete:" + "\n" +
                                    "(Use , to seperate multiple rows)");

        TextField input = new TextField();
        input.setPrefWidth(100);

        HBox bottomLayout = new HBox(20);
        Button confirm = new Button("Confirm");
        Button cancel = new Button("Cancel");
        confirm.setOnAction(e -> {
            String rowinfo = input.getText();
            String[] toDelete = rowinfo.split(",");
            int total = toDelete.length;
            int[] number = new int[total];
            // System.out.println(table.getRecords().getRecordsNumber());
            for (int i = 0; i < total; i++) {
                if (isInteger(toDelete[i])){
                    number[i] = Integer.parseInt(toDelete[i]);
                } 
                else {
                    AlertBox.display("Error", "Please type in interger.");
                    window.close();
                }
            }
            if (!table.deleteRow(sortIntArray(number))) {
                AlertBox.display("Error", "Please type in valid row numbers.");
                window.close();
            }
            table.printTable();
            window.close();
        });
        // System.out.println(table.getRecords().getRecordsNumber());
        cancel.setOnAction(e -> window.close());
        bottomLayout.getChildren().addAll(confirm, cancel);
        bottomLayout.setPadding(new Insets(20,0,0,0));

        layout.getChildren().addAll(instruction, input, bottomLayout);
        layout.setPadding(new Insets(30,20,30,20));

        Scene scene = new Scene(layout, 300, 200);
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

    static int[] sortIntArray(int... number) {
        int length = number.length;
        int[] sorted = new int[length];
        Arrays.sort(number);
        for (int i = length - 1; i >=0; i--) {
            sorted[length - i - 1] = number[i];
            System.out.println(number[i]);
        }
        return sorted;
    }
}
