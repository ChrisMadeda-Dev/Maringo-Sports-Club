import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ClubStore extends JPanel {
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
                int totalPrice = 0;
                ArrayList <String> itemArray= new ArrayList<>();

                if(cbBloomer.isSelected()){
                    itemArray.add("Bloomer");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Bloomer"));
                    totalPrice += 250 * quantity;

                }

                if(cbShorts.isSelected()){

                    itemArray.add("Games Shorts");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Games shorts"));
                    totalPrice += 750 * quantity;
                }

                if(cbHockeySticks.isSelected()){
                    itemArray.add("Hockey Stick");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Hockey Sticks"));
                    totalPrice += 2000 * quantity;
                }
                if(cbSocks.isSelected()){
                    itemArray.add("Socks");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Socks"));
                    totalPrice += 350 * quantity;
                }

                if(cbSportsShoe.isSelected()){
                    int price=Integer.valueOf(JOptionPane.showInputDialog(null,"Enter price of shoe"));
                    itemArray.add("Sport Shoe");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Sports Shoe"));
                    totalPrice += price * quantity;
                }

                if(cbTrackSuit.isSelected()){
                    itemArray.add("Track Suit");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Track Suit"));
                    totalPrice += 1000 * quantity;
                }

                if(cbTShirt.isSelected()){
                    itemArray.add("TShirt");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of T-Shirts"));
                    totalPrice += 800 * quantity;
                }

                if(cbWrapper.isSelected()){
                    itemArray.add("Wrapper");
                    int quantity = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter quantity of Wrapper"));
                    totalPrice += 450 * quantity;
                }

                String a = String.valueOf(totalPrice);
                tfTotalPrice.setText(a);

               if(totalPrice>0){
                   if(totalPrice<10000){
                       JOptionPane.showMessageDialog(null,"Amount " + a + " Will be debited ");
                   }else{
                       double discountAmount = (0.05 * totalPrice);
                       double newPrice = totalPrice - discountAmount ;
                       JOptionPane.showMessageDialog(null,"You have received a Discount of " + discountAmount + " Amount " + newPrice + " Will be Debited" );
                   }
                   String  mpesaNum = String.valueOf(JOptionPane.showInputDialog(null,"Enter Mpesa Number"));
                   if(mpesaNum.length()>8){
                       JOptionPane.showMessageDialog(null,"Transaction complete");
                   }else{
                       JOptionPane.showMessageDialog(null,"Invalid Mpesa Number");
                   }
               }
            }
        });

    }
}
