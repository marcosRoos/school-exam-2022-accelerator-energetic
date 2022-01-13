import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class customerController {


    @FXML
    Label label_client_name;
    @FXML
    Label label_qtt;
    @FXML 
    Label label_price_total;
    @FXML
    CategoryAxis table_x;
    @FXML
    NumberAxis table_y;
    @FXML
    LineChart<CategoryAxis,NumberAxis> chart;
    @FXML
    Label label_bonus;

    XYChart.Series<CategoryAxis, NumberAxis> series = new XYChart.Series<CategoryAxis, NumberAxis>();
    

    private int year = LocalDate.now().getYear();

    public void open_home_page(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load( getClass().getResource("home_page.fxml") );
        stage.setTitle("Energéticos Accelerator");
        Scene average = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene( average );
        stage.show();
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

    public void setCustomerName(String name) {
        label_client_name.setText(name);
    }

    public void setQuantity(String qtt) {
        label_qtt.setText(qtt);
    }

    public void setTotalPrice(String price) {
        label_price_total.setText(price);
    }

    public void load_data() {
        series.setName(label_client_name.getText());
        Budget data = new Budget( label_client_name.getText() );
        ArrayList<ArrayList<String>> allBudgets = data.getInfo();
        for( int month = 1; month <= 12; month++ ) {
            double total_budget_month = 0;
            for( int i = 0; i < allBudgets.size(); i++ ) {
                LocalDate date = LocalDate.parse(allBudgets.get(i).get(5));
                if( date.getYear() == year && date.getMonthValue() == month ) {
                    String value = allBudgets.get(i).get(4).replace("R$ ", "");
                    total_budget_month += Double.parseDouble( value );
                }
            }
            series.getData().add(new XYChart.Data(new DateFormatSymbols().getMonths()[month-1] , total_budget_month));
        }
        chart.getData().add(series);
        Bonus customers_with_bonus = new Bonus();
        customers_with_bonus.refreshBonus();
        int bonus = customers_with_bonus.getBonus(label_client_name.getText());
        label_bonus.setText(String.valueOf(bonus) + "%");
    }

    
}
