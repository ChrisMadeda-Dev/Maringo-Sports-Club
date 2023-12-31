import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class GroupReg extends JPanel {
    public GroupReg(){
        add(new JLabel("Register Group"));
        setBackground(Color.white);
        setLayout(null);

        Font font = new Font("SansSerif",Font.PLAIN,22);

        JLabel lGrpName = new JLabel("GROUP NAME");
        lGrpName.setFont(font);
        lGrpName.setBounds(500,200,200,40);
        JTextField tfGrpName = new JTextField(30);
        tfGrpName.setBounds(700,200,350,40);
        add(lGrpName); add(tfGrpName);

        JLabel lGrpCode = new JLabel("GROUP CODE");
        lGrpCode.setFont(font);
        lGrpCode.setBounds(500,250,200,40);
        JTextField tfGrpCode = new JTextField(30);
        tfGrpCode.setBounds(700,250,350,40);
        //add(lGrpCode); add(tfGrpCode);
        //Not in use


        JLabel lGrpNum = new JLabel("POPULATION");
        lGrpNum.setFont(font);
        lGrpNum.setBounds(500,250,200,40);
        JTextField tfGrpNum = new JTextField(30);
        tfGrpNum.setBounds(700,250,350,40);
        add(lGrpNum); add(tfGrpNum);


        JLabel lGrpType = new JLabel("GROUP TYPE");
        lGrpType.setFont(font);
        lGrpType.setBounds(500,300,200,40);
        String []grpTypeOpt = {"HIghSchool","University","Religious Group"};
        JComboBox <String> grpCb = new JComboBox<>(grpTypeOpt);
        grpCb.setBounds(700,300,350,40);
        add(lGrpType);add(grpCb);


        JLabel lCharge = new JLabel("CHARGE");
        lCharge.setFont(font);
        lCharge.setForeground(Color.gray);
        lCharge.setBounds(500,350,200,40);
        JTextField tfGrpCharge = new JTextField();
        tfGrpCharge.setEditable(false);
        tfGrpCharge.setBounds(700,350,350,40);
        add(lCharge);add(tfGrpCharge);


        JButton grpBtn = new JButton("Register Group");
        grpBtn.setBounds(600,450,350,50);
        grpBtn.setBackground(Color.cyan);
        add(grpBtn);


        grpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    Statement statement = connection.createStatement();

                    String insertToSql = "INSERT INTO group_registration(groupName,population,charge,groupType)VALUE(?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertToSql);

                    preparedStatement.setString(1,tfGrpName.getText());
                    preparedStatement.setInt(2,Integer.valueOf(tfGrpNum.getText()));
                    preparedStatement.setString(4,String.valueOf(grpCb.getSelectedItem()));

                    if(tfGrpNum.getText()!=""){
                        int a = Integer.valueOf(tfGrpNum.getText())*500;
                        tfGrpCharge.setText(String.valueOf(a));
                        JOptionPane.showMessageDialog(null,"Sh " + a + " Will be debited");
                        String mpesaNum= JOptionPane.showInputDialog("Enter Mpesa NUmber");
                        if(mpesaNum.length()==10){
                            JOptionPane.showMessageDialog(null," Amount " + a + " Will be debited from " + mpesaNum + " Mpesa Account");
                            preparedStatement.setString(3,String.valueOf(a));
                            preparedStatement.executeUpdate();

                        }else{
                            JOptionPane.showMessageDialog(null,"Invalid Mpesa Number");
                        }
                    }

                    tfGrpName.setText("");
                    tfGrpNum.setText("");
                    tfGrpCharge.setText("");
                    tfGrpCode.setText("");

                    connection.close();
                    preparedStatement.close();
                    JOptionPane.showMessageDialog(null,"Group has been Registered");

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });




    }
}
