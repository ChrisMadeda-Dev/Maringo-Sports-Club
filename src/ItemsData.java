import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ItemsData extends JPanel {
    public ItemsData(){
        setLayout(null);
        setBackground(Color.white);

        JLabel ltitle = new JLabel("MARINGO STORE ITEMS");
        ltitle.setFont(new Font("SansSerif",Font.BOLD,40));
        ltitle.setBounds(460,100,700,50);
        ltitle.setForeground(Color.DARK_GRAY);
        add(ltitle);

        JButton btnUpdateItems = new JButton("Update Items");
        btnUpdateItems.setBounds(500,770,500,50);
        btnUpdateItems.setBackground(Color.cyan);
        add(btnUpdateItems);

        //Button Functions
        btnUpdateItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"updateItemsPanel");
            }
        });

        JTable itemsTable= new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Item ID");
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Item Price");
        tableModel.addColumn("Item Quantity");
        tableModel.addColumn("Items Sold");
        tableModel.addColumn("Items Status");

        itemsTable.setModel(tableModel);
        itemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM MaringoSportsClub.items_data";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String itemId = resultSet.getString("itemId");
                String itemName = resultSet.getString("itemName");
                String itemPrice = resultSet.getString("itemPrice");
                String itemQuantity = resultSet.getString("itemQuantity");
                String itemsSold = resultSet.getString("itemsSold");

                int percent = Integer.parseInt(itemsSold)/Integer.parseInt(itemQuantity);
                String itemStatus = "Okay";

                if(percent>0.7){
                    itemStatus = "ALERT !!";
                }
                tableModel.addRow(new Object[]{itemId,itemName,itemPrice,itemQuantity,itemsSold,itemStatus});
            }


            JScrollPane scrollPane = new JScrollPane(itemsTable);
            scrollPane.setBounds(150,200,1200,500);
            add(scrollPane);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
