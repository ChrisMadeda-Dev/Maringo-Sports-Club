import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class UpdateItems extends JPanel {
    public UpdateItems(){
        setLayout(null);
        setBackground(Color.white);

        Font font = new Font("SansSerif",Font.PLAIN,18);

        JLabel lItems= new JLabel("CHOOSE ITEM");
        lItems.setFont(font);
        lItems.setBounds(480,300,200,40);
        String[]Items= {"Bloomer","Games Shorts","Hockey Sticks","Socks","Sports shoes","Track Suit","T-shirts","Wrapper"};
        JComboBox <String> cbItems = new JComboBox<>(Items);
        cbItems.setBounds(680,300,400,40);
        add(lItems);add(cbItems);

        JLabel lItemQuantity = new JLabel("ADD QUANTITY");
        lItemQuantity.setFont(font);
        lItemQuantity.setBounds(480,350,200,40);
        JTextField tfItemQuantity = new JTextField();
        tfItemQuantity.setBounds(680,350,400,40);
        add(lItemQuantity);add(tfItemQuantity);

        JButton btnUpdateItemRecord = new JButton("UPDATE");
        btnUpdateItemRecord.setBounds(590,420,400,50);
        btnUpdateItemRecord.setBackground(Color.CYAN);
        add(btnUpdateItemRecord);

        btnUpdateItemRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    String updateQuery = "UPDATE items_data SET itemQuantity=?, itemsSold= ? WHERE itemName='"+cbItems.getSelectedItem()+"' ";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                    preparedStatement.setString(1,tfItemQuantity.getText());
                    preparedStatement.setString(2,"0");

                    int rowsAffected = preparedStatement.executeUpdate();

                    if(rowsAffected>0){
                        JOptionPane.showMessageDialog(null,"Item Detail is Updated. Quantity:'"+tfItemQuantity.getText()+"'");
                    }else {
                        JOptionPane.showMessageDialog(null,"Error: Update failed !!");
                    }

                    tfItemQuantity.setText("");
                    preparedStatement.close();
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
