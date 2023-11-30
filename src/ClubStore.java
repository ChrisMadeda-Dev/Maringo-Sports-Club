import jdk.jfr.Experimental;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class ClubStore extends JPanel {

    static void updateMyItemsData(String itemName, String itemQuantity){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM MaringoSportsClub.items_data WHERE itemName='"+itemName+"'";
            ResultSet resultSet = statement.executeQuery(query);

            int purchasedQuantity = Integer.parseInt(itemQuantity);
            int itemsCurrentQuantity=0;
            int itemsNewQuantity=0;
            int calcSoldItems=0;
            int itemsSoldDb=0;
            int itemsSoldNewValue=0;

            while (resultSet.next()){
                itemsSoldDb = Integer.parseInt(resultSet.getString("itemsSold"));
                itemsCurrentQuantity = Integer.parseInt(resultSet.getString("itemQuantity"));
            }


            String updateSoldQuery = "UPDATE items_data SET itemsSold = ? WHERE itemName = ? ";
            String updateQuantityQuery = "UPDATE items_data SET itemQuantity = ? WHERE itemName = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSoldQuery);
            PreparedStatement updateQuantityStatement = connection.prepareStatement(updateQuantityQuery);

            calcSoldItems =itemsSoldDb + Integer.parseInt(itemQuantity);
            itemsSoldNewValue =calcSoldItems;

            itemsNewQuantity = itemsCurrentQuantity - purchasedQuantity;

            //Updates the Sold
            preparedStatement.setString(1,String.valueOf(itemsSoldNewValue));
            preparedStatement.setString(2,String.valueOf(itemName));

            //updates the Quantity
            updateQuantityStatement.setString(1,String.valueOf(itemsNewQuantity));
            updateQuantityStatement.setString(2,itemName);

            //Executes update
            preparedStatement.executeUpdate();
            int rowAffected = updateQuantityStatement.executeUpdate();

            if(rowAffected>0){
                //JOptionPane.showMessageDialog(null,"Item details updated");
                System.out.println("update for "+ itemName + " successful");
            }else{
                //JOptionPane.showMessageDialog(null,"ERROR: Items not updated");
                System.out.println("update for "+ itemName + " FAILED");

            }
            preparedStatement.close();
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ClubStore(){
        setLayout(null);
        setBackground(Color.white);

        Font font = new Font("SansSerif", Font.PLAIN,22);

        JLabel clubStoreTitle = new JLabel("MARINGO CLUB STORE");
        clubStoreTitle.setBounds(550,50,400,50);
        clubStoreTitle.setFont(new Font("SansSerif",Font.BOLD,30));
        add(clubStoreTitle);

        //Sets up the table
        String[][] data = {{"Bloomer","250"},{"Games Short","750"},{"Hockey Sticks","2000"},{"Socks","350"},{"Sports Shoe","1000-4000"},{"Track Suit","1000"},{"T-Shirts","800"},{"Wrapper","450"}};
        String[] columnNames = {"ITEM NAME", "PRICE"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);
        table.setCellSelectionEnabled(false);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(300,200,900,210);

        add(sp);

        String [] clubStoreItems= {"Bloomer - 250","Games Shorts - 750","Hockey Sticks - 2000", "Socks - 350",
        "Sports Shoe - 1000 -> 4000", "Track Suit - 1000", "T-Shirts -  800", "Wrapper - 450"
        };
        JComboBox <String> cbClubStore= new JComboBox<>(clubStoreItems);
        JLabel lChoose = new JLabel("SELECT ITEM : ");
        lChoose.setBounds(400,500,200,50);
        lChoose.setFont(font);
        cbClubStore.setBounds(600,500,450,50);
        //add(lChoose);add(cbClubStore);


        //Add checkboxes to allow user select items
        JCheckBox cbBloomer = new JCheckBox("Bloomer");
        cbBloomer.setBounds(300,500,150,40);
        add(cbBloomer);

        JCheckBox cbShorts = new JCheckBox("Game Shorts");
        cbShorts.setBounds(500,500,150,40);
        add(cbShorts);

        JCheckBox cbHockeySticks = new JCheckBox("Hockey Sticks");
        cbHockeySticks.setBounds(700,500,150,40);
        add(cbHockeySticks);

        JCheckBox cbSocks = new JCheckBox("Socks");
        cbSocks.setBounds(880,500,150,40);
        add(cbSocks);

        JCheckBox cbSportsShoe = new JCheckBox("Sports Shoe");
        cbSportsShoe.setBounds(1070,500,130,40);
        add(cbSportsShoe);

        JCheckBox cbTrackSuit = new JCheckBox("Track Suit");
        cbTrackSuit.setBounds(300,550,150,40);
        add(cbTrackSuit);

        JCheckBox cbTShirt= new JCheckBox("T-Shirts");
        cbTShirt.setBounds(500,550,150,40);
        add(cbTShirt);

        JCheckBox cbWrapper = new JCheckBox("Wrapper");
        cbWrapper.setBounds(700,550,150,40);
        add(cbWrapper);

        //Label for displaying the total
        JTextField tfTotalPrice = new JTextField("TOTAL PRICE");
        tfTotalPrice.setBounds(520,650,450,50);
        tfTotalPrice.setEditable(false);
        add(tfTotalPrice);

        //Button : Initiates the functions

        JButton btnClubStore = new JButton("PURCHASE");
        btnClubStore.setBounds(520,710,450,50);
        btnClubStore.setBackground(Color.CYAN);
        add(btnClubStore);


        btnClubStore.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    Statement statement = connection.createStatement();

                    ArrayList<String> itemsPurchased = new ArrayList<>();
                    HashMap<String,Integer> itemsQuantity = new HashMap<>();
                    HashMap<String,Integer> itemsPrice= new HashMap<>();

                    int totalPrice = 0;
                    ArrayList <String> itemArray= new ArrayList<>();

                    if(cbBloomer.isSelected()){
                        itemArray.add("Bloomer");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Bloomer"));
                        totalPrice += 250 * quantity;
                        itemsPurchased.add("Bloomer");
                        itemsQuantity.put("Bloomer",quantity);
                        itemsPrice.put("Bloomer",250*quantity);
                    }

                    if(cbShorts.isSelected()){

                        itemArray.add("Games Shorts");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Games shorts"));
                        totalPrice += 750 * quantity;
                        itemsPurchased.add("Games Shorts");
                        itemsQuantity.put("Games Shorts",quantity);
                        itemsPrice.put("Games Shorts",750*quantity);

                    }

                    if(cbHockeySticks.isSelected()){
                        itemArray.add("Hockey Stick");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Hockey Sticks"));
                        totalPrice += 2000 * quantity;
                        itemsPurchased.add("Hockey Stick");
                        itemsQuantity.put("Hockey Stick",quantity);
                        itemsPrice.put("Hockey Stick",2000 * quantity);

                    }
                    if(cbSocks.isSelected()){
                        itemArray.add("Socks");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Socks"));
                        totalPrice += 350 * quantity;
                        itemsPurchased.add("Socks");
                        itemsQuantity.put("Socks",quantity);
                        itemsPrice.put("Socks",350*quantity);
                    }

                    if(cbSportsShoe.isSelected()){
                        int price=Integer.valueOf(JOptionPane.showInputDialog(null,"Enter price of shoe"));
                        itemArray.add("Sport Shoe");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Sports Shoe"));
                        totalPrice += price * quantity;
                        itemsPurchased.add("Sports Shoe");
                        itemsQuantity.put(" Sports Shoe",quantity);
                        itemsPrice.put("Sports Shoe",price*quantity);
                    }

                    if(cbTrackSuit.isSelected()){
                        itemArray.add("Track Suit");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Track Suit"));
                        totalPrice += 1000 * quantity;
                        itemsPurchased.add("Track Suit");
                        itemsQuantity.put("Track Suit",quantity);
                        itemsPrice.put("Track Suit",1000*quantity);
                    }

                    if(cbTShirt.isSelected()){
                        itemArray.add("T-Shirt");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of T-Shirts"));
                        totalPrice += 800 * quantity;
                        itemsPurchased.add("T-Shirts");
                        itemsQuantity.put("T-Shirts",quantity);
                        itemsPrice.put("T-Shirts",800*quantity);
                    }

                    if(cbWrapper.isSelected()){
                        itemArray.add("Wrapper");
                        int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Wrapper"));
                        totalPrice += 450 * quantity;
                        itemsPurchased.add("Wrapper");
                        itemsQuantity.put("Wrapper",quantity);
                        itemsPrice.put("Wrapper",450*quantity);
                    }

                    String a = String.valueOf(totalPrice);
                    tfTotalPrice.setText(a);

                    String  mpesaNum = String.valueOf(JOptionPane.showInputDialog(null,"Enter Mpesa Number"));

                    if(mpesaNum.length()==10 ){
                        double discountAmount = 0.0 ;
                        String insertToSql= "INSERT INTO purchased_items(itemName,itemQuantity,itemPrice)"+"VALUE(?,?,?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertToSql);

                        for (String i : itemsPurchased){

                            if(itemsQuantity.containsKey(i)){
                                preparedStatement.setString(1,i);
                                preparedStatement.setInt(2,itemsQuantity.get(i));
                                preparedStatement.setInt(3,itemsPrice.get(i));
                            }

                            //Updates data on the items_data Table
                            updateMyItemsData(i,String.valueOf(itemsQuantity.get(i)));
                            //Updates the purchase Table
                            preparedStatement.executeUpdate(); //Updates Database
                        }

                        //Exists outside the for loop
                        if(totalPrice>0){
                            if(totalPrice<10000){
                                JOptionPane.showMessageDialog(null,"Amount " + a + " Will be debited ");
                            }else{
                                discountAmount = (0.05 * totalPrice);
                                double newPrice = totalPrice - discountAmount ;
                                JOptionPane.showMessageDialog(null,"You have received a Discount of " + discountAmount + " Amount " + newPrice + " Will be Debited" );
                                JOptionPane.showMessageDialog(null,"Transaction complete");
                            }
                        }


                        preparedStatement.close();
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid Mpesa Number");
                    }




                    connection.close();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
