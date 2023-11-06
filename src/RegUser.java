import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegUser extends JPanel {
    public RegUser(){



        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        JFormattedTextField dateField = new JFormattedTextField(dateFormat);


        setLayout(null);
        setBackground(Color.white);

        Font font = new Font("SansSerif",Font.PLAIN,18);

        JLabel lUname = new JLabel("USER NAME:");
        lUname.setFont(font);
        lUname.setBounds(500,100,200,30);
        JTextField tfUname= new JTextField();
        tfUname.setBounds(700,100,300,30);
        add(lUname);add(tfUname);

        JLabel lUnextofkin = new JLabel("NEXT OF KIN:");
        lUnextofkin.setFont(font);
        lUnextofkin.setBounds(500,140,200,30);
        JTextField tfUnextofkin= new JTextField();
        tfUnextofkin.setBounds(700,140,300,30);
        add(lUnextofkin);add(tfUnextofkin);

        JLabel lUtel = new JLabel("TELEPHONE NUM:");
        lUtel.setFont(font);
        lUtel.setBounds(500,180,200,30);
        JTextField tfUtel= new JTextField();
        tfUtel.setBounds(700,180,300,30);
        add(lUtel);add(tfUtel);

        JLabel lUgender = new JLabel("GENDER");
        lUgender.setFont(font);
        lUgender.setBounds(500,220,200,30);
        String []genderChoices = {"Male", "Female"};
        JComboBox <String> CbUgender= new JComboBox<>(genderChoices);
        CbUgender.setBounds(700,220,300,30);
        add(lUgender);add(CbUgender);

        JLabel lUweight = new JLabel("WEIGHT:");
        lUweight.setFont(font);
        lUweight.setBounds(500,260,200,30);
        JTextField tfUweight= new JTextField();
        tfUweight.setBounds(700,260,300,30);
        add(lUweight);add(tfUweight);

        JLabel lUheight = new JLabel("HEIGHT:");
        lUheight.setFont(font);
        lUheight.setBounds(500,300,200,30);
        JTextField tfUheight= new JTextField();
        tfUheight.setBounds(700,300,300,30);
        add(lUheight);add(tfUheight);

        JLabel lUdateofbirth = new JLabel("DATE OF BIRTH:");
        lUdateofbirth.setFont(font);
        lUdateofbirth.setBounds(500,340,200,30);
        JTextField tfUdateofbirth= new JTextField();
        tfUdateofbirth.setBounds(700,340,300,30);
        add(lUdateofbirth);add(tfUdateofbirth);

        JLabel lUage = new JLabel("CURRENT AGE:");
        lUage.setFont(font);
        lUage.setBounds(500,380,200,30);
        JTextField tfUage= new JTextField();
        tfUage.setBounds(700,380,300,30);
        add(lUage);add(tfUage);


        JLabel lUinstitute = new JLabel("INSTITUTE NAME:");
        lUinstitute.setFont(font);
        lUinstitute.setBounds(500,420,200,30);
        JTextField tfUinstitute= new JTextField();
        tfUinstitute.setBounds(700,420,300,30);
        add(lUinstitute);add(tfUinstitute);

        JLabel lUsubcounty = new JLabel("SUB COUNTY");
        lUsubcounty.setFont(font);
        lUsubcounty.setBounds(500,460,200,30);
        String []subcounties = {"Sofia","Sikijua","Kawagare","Kariako"};
        JComboBox <String> CbUsubcounty= new JComboBox<>(subcounties);
        CbUsubcounty.setBounds(700,460,300,30);
        add(lUsubcounty);add(CbUsubcounty);

        JLabel lUspecialneed = new JLabel("SPECIAL NEED:");
        lUspecialneed.setFont(font);
        lUspecialneed.setBounds(500,500,200,30);
        JTextField tfUspecialneed= new JTextField();
        tfUspecialneed.setBounds(700,500,300,30);
        add(lUspecialneed);add(tfUspecialneed);

        JLabel lUregas = new JLabel("REGISTER US");
        lUregas.setFont(font);
        lUregas.setBounds(500,540,200,30);
        String []regChoices = {"Individual", "Group"};
        JComboBox <String> CbUregas= new JComboBox<>(regChoices);
        CbUregas.setBounds(700,540,300,30);
        add(lUregas);add(CbUregas);

        JButton btnRegUser = new JButton("REGISTER USER");
        btnRegUser.setBounds(550,600,400,50);
        add(btnRegUser);

        btnRegUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {



                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    Statement statement = connection.createStatement();

                    String insertToSql= "INSERT INTO user_registration(userName,userHeight,userWeight,userGender,NextofKin,InstituteName, CurrentAge,SubCounty,RegisterAs,SpecialNeed,charge)" +
                            "VALUE(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertToSql);

                    //Get specific inputs



                    if(tfUname.getText()!="" && tfUinstitute.getText() != "" && tfUtel.getText()!=""){

                        preparedStatement.setString(1,tfUname.getText());
                        preparedStatement.setInt(2,Integer.valueOf(tfUheight.getText()));
                        preparedStatement.setInt(3,Integer.valueOf(tfUweight.getText()));
                        preparedStatement.setString(4,String.valueOf(CbUgender.getSelectedItem()));
                        preparedStatement.setString(5,tfUnextofkin.getText());
                        preparedStatement.setString(6,tfUinstitute.getText());
                        preparedStatement.setInt(7,Integer.valueOf(tfUage.getText()));
                        preparedStatement.setString(8,String.valueOf(CbUsubcounty.getSelectedItem()));
                        preparedStatement.setString(9,String.valueOf(CbUregas.getSelectedItem()));
                        preparedStatement.setString(10,tfUspecialneed.getText());


                        String regAsChoice = (String) CbUregas.getSelectedItem();

                        if(regAsChoice == "Individual"){
                            JOptionPane.showMessageDialog(null,"Your registering as Individual, Amount 1000 will be Debited");
                            String a = JOptionPane.showInputDialog(null,"Enter Mpesa Number");
                            int b = Integer.valueOf(a);
                            if(a.length()>=8){
                                JOptionPane.showMessageDialog(null,"Amount " + b + " has been Debited");
                                preparedStatement.setString(11,"1000");
                            }else{
                                JOptionPane.showMessageDialog(null,"Invalid Mpesa Number");
                            }
                        }

                        if(regAsChoice =="Group"){
                            String a = JOptionPane.showInputDialog(null,"Enter Group code");
                            int b = Integer.valueOf(a);
                            if(a!=""){
                                JOptionPane.showMessageDialog(null,"Group has been Verified Welcome");
                                preparedStatement.setString(11,"500");
                            }

                        }
                    }

                    preparedStatement.executeUpdate();
                    connection.close();
                    JOptionPane.showMessageDialog(null,"User is sucessfully Registered");

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


    }
}
