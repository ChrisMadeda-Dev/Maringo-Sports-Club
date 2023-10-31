import javax.swing.*;
import java.awt.*;

public class ClubStore extends JPanel {
    public ClubStore(){
        setLayout(null);
        setBackground(Color.white);

        String[][] data = {{"101", "Amit", "670000"}, {"102", "Jai", "780000"}, {"103", "Sachin", "700000"}};
        String[] columnNames = {"ID", "NAME", "SALARY"};
        JTable table = new JTable(data, columnNames);
        table.setBounds(500,200,600,500);

        add(table);

    }
}
