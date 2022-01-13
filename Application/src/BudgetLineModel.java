import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class BudgetLineModel {

    @FXML
    private SimpleStringProperty Column1, Column2, Column3, Column4, Column5, Column6;

    public BudgetLineModel(String client,
            String price, String quantity,
            String taxes, String total_price, String date) {
        this.Column1 = new SimpleStringProperty(client);
        this.Column2 = new SimpleStringProperty(price);
        this.Column3 = new SimpleStringProperty(quantity);
        this.Column4 = new SimpleStringProperty(taxes);
        this.Column5 = new SimpleStringProperty(total_price);
        this.Column6 = new SimpleStringProperty(date);
    }

    public String getColumn1() {
        return Column1.get();
    }

    public SimpleStringProperty column1Property() {
        return Column1;
    }

    public void setColumn1(String column1) {
        this.Column1.set(column1);
    }

    public String getColumn2() {
        return Column2.get();
    }

    public SimpleStringProperty column2Property() {
        return Column2;
    }

    public void setColumn2(String column2) {
        this.Column2.set(column2);
    }

    public String getColumn3() {
        return Column3.get();
    }

    public SimpleStringProperty column3Property() {
        return Column3;
    }

    public void setColumn3(String column3) {
        this.Column3.set(column3);
    }

    public String getColumn4() {
        return Column4.get();
    }

    public SimpleStringProperty column4Property() {
        return Column4;
    }

    public void setColumn4(String column4) {
        this.Column4.set(column4);
    }

    public String getColumn5() {
        return Column5.get();
    }

    public SimpleStringProperty column5Property() {
        return Column5;
    }

    public void setColumn5(String column5) {
        this.Column5.set(column5);
    }

    public String getColumn6() {
        return Column6.get();
    }

    public SimpleStringProperty column6Property() {
        return Column6;
    }

    public void setColumn6(String column6) {
        this.Column6.set(column6);
    }

}