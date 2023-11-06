import jdk.jfr.Experimental;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegUsersData extends JPanel {
    public RegUsersData(){
        setLayout(null);
        setBackground(Color.white);

        JLabel lRegUserInfo = new JLabel("MARINGO REGISTERED USERS");
        lRegUserInfo.setFont(new Font("SansSerif",Font.BOLD,40));
        lRegUserInfo.setBounds(420,100,700,50);
        add(lRegUserInfo);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();


        tableModel.addColumn("userID");
        tableModel.addColumn("userName");
        tableModel.addColumn("userHeight");
        tableModel.addColumn("userWeight");
        tableModel.addColumn("userGender");
        tableModel.addColumn("NextofKin");
        tableModel.addColumn("InstituteName");
        tableModel.addColumn("CurrentAge");
        tableModel.addColumn("SubCounty");
        tableModel.addColumn("Registered As");
        tableModel.addColumn("Special Need");
        tableModel.addColumn("Charge");

        table.setModel(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //table.setBounds(300,300,1000,700);


        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user_registration";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String userID= resultSet.getString("userId");
                String userName= resultSet.getString("userName");
                String userHeight= resultSet.getString("userHeight");
                String userWeight= resultSet.getString("userWeight");
                String userGender= resultSet.getString("userGender");
                String NextofKin = resultSet.getString("NextofKin");
                String CurrentAge = resultSet.getString("CurrentAge");
                String SubCounty = resultSet.getString("SubCounty");
                String RegisteredAs = resultSet.getString("RegisterAs");
                String SpecialNeed = resultSet.getString("SpecialNeed");
                String Charge = resultSet.getString("charge");

                tableModel.addRow(new Object[]{userID,userName,userHeight,userWeight,userGender,NextofKin
                ,CurrentAge,SubCounty,RegisteredAs,SpecialNeed,Charge});

                //tableModel.addRow();
            }

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(250,250,1000,500);

            add(scrollPane);

            connection.close();
            statement.close();
            resultSet.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
