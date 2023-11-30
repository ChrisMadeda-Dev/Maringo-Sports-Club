import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ReturnGameItem extends JPanel {
    public ReturnGameItem (){
        setLayout(null);
        setBackground(Color.white);

        Font font = new Font("SansSerif",Font.PLAIN,18);

        JLabel lCaptainName = new JLabel("CAPTAIN NAME");
        lCaptainName.setFont(font);
        lCaptainName.setBounds(470,200,200,40);
        JTextField tfCaptainName = new JTextField();
        tfCaptainName.setBounds(700,200,400,40);
        add(lCaptainName);add(tfCaptainName);

        JLabel lCaptainId = new JLabel("CAPTAIN ID");
        lCaptainId.setFont(font);
        lCaptainId.setBounds(470,250,200,40);
        JTextField tfCaptainId = new JTextField();
        tfCaptainId.setBounds(700,250,400,40);
        add(lCaptainId);add(tfCaptainId);


        JLabel lItemName = new JLabel("ITEM NAME");
        lItemName.setFont(font);
        lItemName.setBounds(470,300,200,40);
        JTextField tfItemName = new JTextField();
        tfItemName.setBounds(700,300,400,40);
        add(lItemName);add(tfItemName);

        JLabel lItemId = new JLabel("ITEM ID");
        lItemId.setFont(font);
        lItemId.setBounds(470,350,200,40);
        JTextField tfItemId = new JTextField();
        tfItemId.setBounds(700,350,400,40);
        tfItemId.setEditable(false);
        add(lItemId);add(tfItemId);

        JLabel lItemQuantity = new JLabel("ITEM QUANTITY");
        lItemQuantity.setFont(font);
        lItemQuantity.setBounds(470,400,200,40);
        JTextField tfItemQuantity = new JTextField();
        tfItemQuantity.setBounds(700,400,400,40);
        add(lItemQuantity);add(tfItemQuantity);

        JLabel lItemStatus = new JLabel("ITEM STATUS");
        lItemStatus.setFont(font);
        lItemStatus.setBounds(470,450,200,40);
        String [] statusOptions = {"Okay", "Damaged","Lost"};
        JComboBox <String> cbItemStatus = new JComboBox<>(statusOptions);
        cbItemStatus.setBounds(700,450,400,40);
        add(lItemStatus);add(cbItemStatus);


        JButton btnReturnItem= new JButton("Return Item");
        btnReturnItem.setBounds(570,560,400,50);
        btnReturnItem.setBackground(Color.cyan);
        add(btnReturnItem);


        btnReturnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    //Statement statement = connection.createStatement();

                    String insertToSql = "INSERT INTO Returned_Game_Items(itemName,itemQuantity,itemStatus,surchargedFee,captainName,captainId)"+"VALUE(?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertToSql);

                    if(!tfCaptainId.getText().isEmpty()&&!tfItemQuantity.getText().isEmpty()&&!tfItemQuantity.getText().isEmpty() ){
                        preparedStatement.setString(1,tfItemName.getText());
                        preparedStatement.setString(2,tfItemQuantity.getText());
                        preparedStatement.setString(5,tfCaptainName.getText());
                        preparedStatement.setString(6,tfCaptainId.getText());

                        if(cbItemStatus.getSelectedItem()=="Okay"){
                            preparedStatement.setString(3,"Okay");
                            preparedStatement.setString(4,"0");
                        }else{
                            int a = Integer.parseInt(JOptionPane.showInputDialog(null,"Input Item Price"));
                            double surchargeFee= a + (0.1 * a);
                            JOptionPane.showMessageDialog(null,"ksh " + surchargeFee + " will be surcharged on " + tfCaptainName.getText());
                            preparedStatement.setString(3,String.valueOf(cbItemStatus.getSelectedItem()));
                            preparedStatement.setString(4,String.valueOf(surchargeFee));
                        }

                        JOptionPane.showMessageDialog(null,"Record has been Made");
                        preparedStatement.executeUpdate();

                        tfItemName.setText("");
                        tfItemQuantity.setText("");
                        tfCaptainName.setText("");
                        tfCaptainId.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: Empty text field");
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}
