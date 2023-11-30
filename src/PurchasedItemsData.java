import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.jar.JarEntry;

public class PurchasedItemsData extends JPanel {
    public PurchasedItemsData (){
        setLayout(null);
        setBackground(Color.white);

        Font font = new Font("SansSerif",Font.PLAIN,18);

        JLabel lPurchase = new JLabel("MARINGO PURCHASED ITEMS");
        lPurchase.setFont(new Font("SansSerif",Font.BOLD,40));
        lPurchase.setBounds(420,70,700,50);
        add(lPurchase);

        //Displays the total income
        JLabel lTotalIncome = new JLabel("TOTAL INCOME");
        lTotalIncome.setBounds(500,735,200,30);
        lTotalIncome.setFont(font);
        lTotalIncome.setForeground(Color.gray);
        JTextField tfTotalIncome = new JTextField();
        tfTotalIncome.setBounds(700,735,300,30);
        tfTotalIncome.setEditable(false);
        add(lTotalIncome);add(tfTotalIncome);

        JButton btnUpdateItem = new JButton("Update Items");
        btnUpdateItem.setBounds(450,800,300,40);
        btnUpdateItem.setBackground(Color.cyan);
        add(btnUpdateItem);

        JButton btnDeleteItems =  new JButton("Delete Items");
        btnDeleteItems.setBounds(750,800,300,40);
        btnDeleteItems.setBackground(Color.cyan);
        add(btnDeleteItems);

        //Button functions

        btnDeleteItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String a = JOptionPane.showInputDialog(null,"Enter purchase ID OR '0' ( to Delete ALL )");
                    String b = JOptionPane.showInputDialog(null,"WARNING : Your about to delete item' "+a+"' Details :: Enter Passcode");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");

                    String deleteQuery="";

                    if(Integer.parseInt(b)==4321){

                       if(Integer.parseInt(a)==0){
                           int opt = JOptionPane.showConfirmDialog(null,"ALERT !!: DELETING ALL ITEMS","CONFIRM",JOptionPane.YES_NO_OPTION);
                           if(opt==JOptionPane.YES_OPTION){
                               deleteQuery = "DELETE FROM purchased_items";
                           }else{
                               return;
                           }
                       }else{
                           deleteQuery = "DELETE FROM purchased_items WHERE purchaseId='"+String.valueOf(a)+"'";
                       }

                        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                        int rowsAffected = preparedStatement.executeUpdate();

                        if(rowsAffected>0){
                            JOptionPane.showMessageDialog(null,"Delete complete");
                        }else{
                            JOptionPane.showMessageDialog(null,"ERROR : NO item deleted");
                        }

                        preparedStatement.close();
                    }else{
                        JOptionPane.showMessageDialog(null,"Wrong Passcode");
                    }

                    connection.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //Shows the update Items Panel
        btnUpdateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"updateItemsPanel");
            }
        });

        //Setting up the table
        JTable purchasedItemsTable= new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Purchase ID");
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Item Quantity");
        tableModel.addColumn("Item Price");

        purchasedItemsTable.setModel(tableModel);
        purchasedItemsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            String query ="SELECT * from MaringoSportsClub.purchased_items";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            int totalIncome = 0;

            while(resultSet.next()){
                String purchaseId = resultSet.getString("purchaseId");
                String itemName = resultSet.getString("itemName");
                String itemQuantity = resultSet.getString("itemQuantity");
                String itemPrice = resultSet.getString("itemPrice");

                //Calculates the total Income
                totalIncome = totalIncome + Integer.parseInt(itemPrice);
                tfTotalIncome.setText(String.valueOf(totalIncome));

                tableModel.addRow(new Object[]{purchaseId,itemName,itemQuantity,itemPrice});
            }

            JScrollPane scrollPane = new JScrollPane(purchasedItemsTable);
            scrollPane.setBounds(155,200,1200,500);
            add(scrollPane);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
