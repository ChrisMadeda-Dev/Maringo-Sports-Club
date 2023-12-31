import jdk.jfr.Experimental;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegUsersData extends JPanel {
    public RegUsersData(){
        setLayout(null);
        setBackground(Color.white);

        JLabel lRegUserInfo = new JLabel("MARINGO REGISTERED USERS");
        lRegUserInfo.setFont(new Font("SansSerif",Font.BOLD,40));
        lRegUserInfo.setBounds(450,80,700,50);
        add(lRegUserInfo);

        JButton btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.setBounds(520,750,500,50);
        btnDeleteUser.setBackground(Color.cyan);
        add(btnDeleteUser);

        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        btnDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String a = JOptionPane.showInputDialog(null,"Enter user ID");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    Statement statement = connection.createStatement();

                    //Connection to deleted_users table
                    Connection connectionDeleted = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    String insertToDeletedUsers = "INSERT INTO deleted_users(userId,userName,userHeight,userWeight,userGender,NextofKin,instituteName,currentAge," +
                            "subCounty,registerAs,specialNeed,charge,userRole)VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connectionDeleted.prepareStatement(insertToDeletedUsers);

                    if(a!=""){
                        String getInfoQuery = "SELECT * FROM user_registration WHERE userId='"+a+"'";
                        ResultSet resultSet = statement.executeQuery(getInfoQuery);

                        while (resultSet.next()){
                            preparedStatement.setString(1,resultSet.getString("userId"));
                            preparedStatement.setString(2,resultSet.getString("userName"));
                            preparedStatement.setString(3,resultSet.getString("userHeight"));
                            preparedStatement.setString(4,resultSet.getString("userWeight"));
                            preparedStatement.setString(5,resultSet.getString("userGender"));
                            preparedStatement.setString(6,resultSet.getString("NextofKin"));
                            preparedStatement.setString(7,resultSet.getString("InstituteName"));
                            preparedStatement.setString(8,resultSet.getString("CurrentAge"));
                            preparedStatement.setString(9,resultSet.getString("SubCounty"));
                            preparedStatement.setString(10,resultSet.getString("RegisterAs"));
                            preparedStatement.setString(11,resultSet.getString("SpecialNeed"));
                            preparedStatement.setString(12,resultSet.getString("charge"));
                            preparedStatement.setString(13,resultSet.getString("userRole"));

                            System.out.println(resultSet.getString("userName"));

                        }


                        int rowsAffected = preparedStatement.executeUpdate();
                        if(rowsAffected>0){
                            JOptionPane.showMessageDialog(null,"Delete Successes");
                        }else{
                            JOptionPane.showMessageDialog(null,"ERROR : Delete Failed");
                        }

                        //Delete user from table and add to deleted_users
                        String deleteQuery="DELETE FROM user_registration WHERE userId='"+a+"'";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(deleteQuery);
                        preparedStatement1.executeUpdate();//Execute Delete

                        preparedStatement.close();
                        statement.close();
                        resultSet.close();
                        connectionDeleted.close();
                        connection.close();

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        tableModel.addColumn("userID");
        tableModel.addColumn("userName");
        tableModel.addColumn("userRole");
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
                String userRole = resultSet.getString("userRole");
                String NextofKin = resultSet.getString("NextofKin");
                String InstituteName = resultSet.getString("instituteName");
                String CurrentAge = resultSet.getString("CurrentAge");
                String SubCounty = resultSet.getString("SubCounty");
                String RegisteredAs = resultSet.getString("RegisterAs");
                String SpecialNeed = resultSet.getString("SpecialNeed");
                String Charge = resultSet.getString("charge");

                tableModel.addRow(new Object[]{userID,userName,userRole,userHeight,userWeight,userGender,NextofKin,InstituteName
                ,CurrentAge,SubCounty,RegisteredAs,SpecialNeed,Charge});

                //tableModel.addRow();
            }

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(170,200,1200,500);

            add(scrollPane);

            connection.close();
            statement.close();
            resultSet.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
