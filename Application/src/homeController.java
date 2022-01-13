import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class homeController {

    @FXML
    TableView<BudgetLineModel> budget_table = new TableView<>();
    @FXML
    TableColumn<BudgetLineModel, String> Column1 = new TableColumn<>("Cliente");
    @FXML
    TableColumn<BudgetLineModel, String> Column2 = new TableColumn<>("Preço Un");
    @FXML
    TableColumn<BudgetLineModel, String> Column3 = new TableColumn<>("Quantidade");
    @FXML
    TableColumn<BudgetLineModel, String> Column4 = new TableColumn<>("Taxas");
    @FXML
    TableColumn<BudgetLineModel, String> Column5 = new TableColumn<>("Preço Total");
    @FXML
    TableColumn<BudgetLineModel, String> Column6 = new TableColumn<>("Data");
    @FXML
    TextField search_cliente;

    private Budget budget_list;

    @FXML
    public void initialize() {
        Column1.setCellValueFactory(new PropertyValueFactory<>("Column1"));
        Column2.setCellValueFactory(new PropertyValueFactory<>("Column2"));
        Column3.setCellValueFactory(new PropertyValueFactory<>("Column3"));
        Column4.setCellValueFactory(new PropertyValueFactory<>("Column4"));
        Column5.setCellValueFactory(new PropertyValueFactory<>("Column5"));
        Column6.setCellValueFactory(new PropertyValueFactory<>("Column6"));
        budget_list = new Budget(search_cliente.getText().toLowerCase());
        addInfos();
    }

    @FXML
    public void clickItem(MouseEvent event) throws Exception {
        if (event.getClickCount() == 2 && budget_table.getSelectionModel().getSelectedItem() != null) {
            BudgetLineModel info = budget_table.getSelectionModel().getSelectedItem();
            String price = info.getColumn5() + (isInteger(info.getColumn5()) ? ".00" : "");
            open_customer_page(info.getColumn1(), info.getColumn3(), price);
        }
    }

    public void refresh_info() {
        budget_list = new Budget(search_cliente.getText().toLowerCase());
        addInfos();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void addInfos() {
        ObservableList<BudgetLineModel> observableList = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> info = budget_list.getInfo();
        for (int i = 0; i < info.size(); i++) {
            String col1 = info.get(i).get(0);
            String col2 = info.get(i).get(1);
            String col3 = info.get(i).get(2);
            String col4 = info.get(i).get(3);
            String col5 = info.get(i).get(4);
            String col6 = info.get(i).get(5);

            BudgetLineModel row = new BudgetLineModel(col1, col2, col3, col4, col5, col6);
            observableList.add(row);
        }
        budget_table.setItems(observableList);
    }

    public void open_budget_page(ActionEvent e) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("budget_register_page.fxml"));
        stage.setTitle("Energéticos Accelerator");
        Scene average = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene(average);
        stage.show();
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }

    public void open_customer_page(String customer_name, String qtt, String total_price) throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_page.fxml"));
        Parent root = loader.load();
        customerController customer_controller = loader.getController();
        customer_controller.setCustomerName(customer_name);
        customer_controller.setQuantity(qtt);
        customer_controller.setTotalPrice(total_price);
        customer_controller.load_data();
        stage.setTitle("Energéticos Accelerator");
        Scene average = new Scene(root, 1200, 800);
        stage.setResizable(false);
        stage.setScene(average);
        stage.show();
        budget_table.getScene().getWindow().hide();
    }

}
