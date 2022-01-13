import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class budgetController {

    @FXML
    TextField input_client;
    @FXML
    TextField input_quantity;
    @FXML
    DatePicker input_date;
    @FXML
    Label final_price;
    @FXML
    Label label_error;
    @FXML
    Label label_bonus;

    int bonus = 0;

    public String calculate(int bonus) {
        int qtt = input_quantity.getText().length() < 1 ? 0 : Integer.parseInt(input_quantity.getText());
        DecimalFormat rule = new DecimalFormat("##0.00");
        return String.valueOf(rule.format((qtt * 4.5 - ( qtt * 4.5 * bonus/100)) + (qtt * 4.5 * 0.324)));
    }

    @FXML
    public void initialize() {
        label_error.setText("");
        label_error.setStyle("-fx-background-color: #ddd;");
        refresh();
    }

    @FXML
    public void refresh() {
        String quantity = input_quantity.getText();
        if (quantity.length() < 1) {
            input_quantity.setText("0");
        }
        if (!quantity.matches("\\d*")) {
            input_quantity.setText("0");
        }
        if (!input_client.getText().matches("[a-zA-Z\\s]+")) {
            input_client.setText("");
        }
        Bonus customers_with_bonus = new Bonus();
        customers_with_bonus.refreshBonus();
        bonus = customers_with_bonus.getBonus(input_client.getText());
        label_bonus.setText(String.valueOf(bonus) + "%");
        final_price.setText("R$ " + calculate(bonus) + (isInteger(calculate(bonus)) ? ",00" : ""));
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void open_home_page(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("home_page.fxml"));
        stage.setTitle("Energéticos Accelerator");
        Scene average = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene(average);
        stage.show();
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }

    public void register_budget(ActionEvent e) throws Exception {
        if( input_client.getText().length() < 2 ) {
            label_error.setText("Insira um cliente válido! [ Deve ter mais de 2 caracteres]");
            label_error.setStyle("-fx-background-color: #e99; -fx-text-fill: #eee;");
        } else if ( Double.parseDouble(input_quantity.getText()) < 1  ) {
            label_error.setText("Insira a quantidade de produtos a serem vendidos!");
            label_error.setStyle("-fx-background-color: #e99; -fx-text-fill: #eee;");
        } else if ( input_date.getValue() == null ) {
            label_error.setText("Insira uma data para o orçamento ser válido!");
            label_error.setStyle("-fx-background-color: #e99; -fx-text-fill: #eee;");
        } else {
            
            ArrayList<String> data_list = new ArrayList<>();
            data_list.add(input_client.getText().toLowerCase());
            data_list.add("R$ 4.50");
            data_list.add(input_quantity.getText());
            DecimalFormat rule = new DecimalFormat("##0.00");
            String taxes = "R$ " + String.valueOf(rule.format(Double.parseDouble(input_quantity.getText())*4.5d*0.324));
            data_list.add( taxes.replace(",", ".") );
            data_list.add( final_price.getText().replace(",", ".") );
            data_list.add(  String.valueOf(input_date.getValue()) );
            String row = String.join(";", data_list);

            String filePath = "src/doc//budget-list.csv";
            BufferedWriter writer = null;

            try {
                writer = new BufferedWriter(new FileWriter(filePath, true));
                writer.write(row);
                writer.newLine();
            } catch (Exception error) {
                error.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

            open_home_page(e);
        }
       
    }
    
}
