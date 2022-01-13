import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Budget {

    private ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

    public Budget(String customer) {
        readCSV(customer);
    }

    private void readCSV(String customer) {
        String filePath = "src/doc//budget-list.csv";
        BufferedReader reader = null;
        String row = "";

        try {
            reader = new BufferedReader(new FileReader(filePath));
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(";");
                ArrayList<String> data_list = new ArrayList<>();
                if (data[0].toLowerCase().contains(customer.toLowerCase()) || customer.length() < 1 || data[0].equals("Cliente")) {
                    for (int i = 0; i < data.length; i++) {
                        data_list.add(data[i]);
                    }
                    info.add(data_list);
                }
            }
            info.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<ArrayList<String>> getInfo() {
        return info;
    }

}
