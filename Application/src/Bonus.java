import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bonus {

    private ArrayList<String> info = new ArrayList<>();

    public Bonus() {
        loadBonuses();
    }

    public void loadBonuses() {
        String filePath = "src/doc//customer-bonus-list.txt";
        BufferedReader reader = null;
        String readed = "";
        try {
            reader = new BufferedReader(new FileReader(filePath));
            readed += reader.readLine();
            String[] data = readed.split(";");
            for (int i = 0; i < data.length; i++) {
                info.add(data[i]);
            }
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

    private void insertNewCustomer(String newCustomer) {
        String filePath = "src/doc//customer-bonus-list.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.append(newCustomer + ";");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFile() {
        String filePath = "src/doc//customer-bonus-list.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.write("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> findAllCustomers() {
        Budget allBudgets = new Budget("");
        ArrayList<ArrayList<String>> table = allBudgets.getInfo();
        ArrayList<String> customers = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            if (!customers.contains(table.get(i).get(0))) {
                customers.add(table.get(i).get(0));
            }
        }
        return customers;
    }

    public void refreshBonus() {
        ArrayList<String> customers = findAllCustomers();
        clearFile();
        for (int i = 0; i < customers.size(); i++) {
            Budget allBudgets = new Budget(customers.get(i));
            double total_spent = 0;
            for (int j = 0; j < allBudgets.getInfo().size(); j++) {
                if (LocalDate.parse(allBudgets.getInfo().get(j).get(5)).getMonth() == LocalDate.now().getMonth()) {
                    total_spent += Double.parseDouble(allBudgets.getInfo().get(j).get(4).replace("R$ ", ""));
                }
            }

            if (total_spent > 10000) {
                insertNewCustomer(customers.get(i));
            }
        }
        loadBonuses();
    }

    public int getBonus(String search) {
        return info.contains(search) ? 15 : 0;
    }

}
