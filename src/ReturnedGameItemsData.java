import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReturnedGameItemsData extends JPanel {
    public ReturnedGameItemsData (){
        setLayout(null);
        setBackground(Color.white);

        JLabel ltitle = new JLabel("MARINGO RETURNED ITEMS");
        ltitle.setFont(new Font("SansSerif",Font.BOLD,40));
        ltitle.setBounds(430,100,700,50);
        ltitle.setForeground(Color.DARK_GRAY);
        add(ltitle);

        JTable returnedItemsTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Item ID");
        tableModel.addColumn("Items Name");
        tableModel.addColumn("Item Quantity");
        tableModel.addColumn("Item Status");
        tableModel.addColumn("Surcharge Fee");

        returnedItemsTable.setModel(tableModel);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM MaringoSportsClub.Returned_Game_Items";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String itemId = resultSet.getString("itemId");
                String itemName = resultSet.getString("itemName");
                String itemQuantity = resultSet.getString("itemQuantity");
                String itemStatus = resultSet.getString("itemStatus");
                String surchargeFee = resultSet.getString("surchargedFee");

                tableModel.addRow(new Object[]{itemId,itemName,itemQuantity,itemStatus,surchargeFee});
            }

            JScrollPane scrollPane = new JScrollPane(returnedItemsTable);
            scrollPane.setBounds(160,200,1200,600);
            add(scrollPane);

            resultSet.close();
            statement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
