import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeletedUsersData extends JPanel {
    public DeletedUsersData(){
        setLayout(null);
        setBackground(Color.white);

        JLabel lRegUserInfo = new JLabel("MARINGO DELETED USERS");
        lRegUserInfo.setFont(new Font("SansSerif",Font.BOLD,40));
        lRegUserInfo.setBounds(450,80,700,50);
        add(lRegUserInfo);

        JButton btnRestoreUser = new JButton("Restore User");
        btnRestoreUser.setBounds(520,750,500,50);
        btnRestoreUser.setBackground(Color.cyan);
        add(btnRestoreUser);

        JTable tableDeletedUser= new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();


        //Restore user Function
        btnRestoreUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String a = JOptionPane.showInputDialog(null,"Enter user ID");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    Statement statement = connection.createStatement();

                    //Connection to deleted_users table
                    Connection connectionDeleted = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    String insertToRegUsers = "INSERT INTO user_registration(userName,userHeight,userWeight,userGender,NextofKin,instituteName,currentAge," +
                            "subCounty,registerAs,specialNeed,charge,userRole)VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connectionDeleted.prepareStatement(insertToRegUsers);

                    if(a!=""){
                        String getInfoQuery = "SELECT * FROM deleted_users WHERE userId='"+a+"'";
                        ResultSet resultSet = statement.executeQuery(getInfoQuery);

                        while (resultSet.next()){
                            //preparedStatement.setString(1,resultSet.getString("userId"));
                            preparedStatement.setString(1,resultSet.getString("userName"));
                            preparedStatement.setString(2,resultSet.getString("userHeight"));
                            preparedStatement.setString(3,resultSet.getString("userWeight"));
                            preparedStatement.setString(4,resultSet.getString("userGender"));
                            preparedStatement.setString(5,resultSet.getString("NextofKin"));
                            preparedStatement.setString(6,resultSet.getString("InstituteName"));
                            preparedStatement.setString(7,resultSet.getString("CurrentAge"));
                            preparedStatement.setString(8,resultSet.getString("SubCounty"));
                            preparedStatement.setString(9,resultSet.getString("RegisterAs"));
                            preparedStatement.setString(10,resultSet.getString("SpecialNeed"));
                            preparedStatement.setString(11,resultSet.getString("charge"));
                            preparedStatement.setString(12,resultSet.getString("userRole"));

                            System.out.println(resultSet.getString("userName"));

                        }


                        int rowsAffected = preparedStatement.executeUpdate();
                        if(rowsAffected>0){
                            JOptionPane.showMessageDialog(null,"Restore Successes");
                        }else{
                            JOptionPane.showMessageDialog(null,"ERROR : Restore Failed");
                        }

                        //Delete user from deleted_users and add to user_registration
                        String deleteQuery="DELETE FROM deleted_users WHERE userId='"+a+"'";
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


        tableDeletedUser.setModel(tableModel);
        tableDeletedUser.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM deleted_users";
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

            }

            JScrollPane scrollPane = new JScrollPane(tableDeletedUser);
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


