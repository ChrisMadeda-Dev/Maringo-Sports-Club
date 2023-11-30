import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Login extends JFrame {
    private String currentUserStatus;
    public Login(){


        setTitle("Maringo Sport Club");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setSize(600,500);
        p1.setLayout(null);
        p1.setBackground(Color.white);

        JLabel lName = new JLabel("ENTER NAME");
        JTextField tfName = new JTextField();
        lName.setBounds(170,150,100,40);
        tfName.setBounds(280,150,350,40);
        p1.add(lName);p1.add(tfName);

        JLabel lPin = new JLabel("ENTER PIN");
        JTextField tfPin = new JTextField();
        lPin.setBounds(170,200,100,40);
        tfPin.setBounds(280,200,350,40);
        p1.add(lPin);p1.add(tfPin);

        JLabel lRole = new JLabel("ROLE");
        lRole.setBounds(170,250,100,40);
        String [] roleOpt = {"User","Admin"};
        JComboBox <String> cbRole = new JComboBox<>(roleOpt);
        cbRole.setBounds(280,250,350,40);
        add(lRole);add(cbRole);
        
        JButton btn = new JButton("LogIn");
        btn.setBounds(200,330,410,50);
        btn.setBackground( new Color(230,230,250));
        p1.add(btn);

        JButton btnCreateAccount = new JButton("create account");
        btnCreateAccount.setBounds(240,390,320,25);
        p1.add(btnCreateAccount);


        add(p1);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(tfName.getText()!=""){
                    boolean isPassCorrect = false;

                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                        Statement statement = connection.createStatement();
                        String query = "SELECT *  FROM user_login WHERE userName='"+String.valueOf(tfName.getText())+"' AND passWord = '"+String.valueOf(tfPin.getText())+"'";
                        ResultSet resultSet = statement.executeQuery(query);

                        if(resultSet.next()){

                            if(cbRole.getSelectedItem()=="Admin"){
                                int a = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter Admin Passcode"));
                                if(a==1234){
                                    JOptionPane.showMessageDialog(null,"Login Successful || Admin");

                                    Main m = new Main();
                                    m.setVisible(true);
                                    dispose();


                                }else{
                                    JOptionPane.showMessageDialog(null,"Incorrect: Try Again");
                                }
                            }else{
                              JOptionPane.showMessageDialog(null,"Login Successful || User");

                              //Show userPanel
                              UserMain userMain = new UserMain();
                              userMain.setVisible(true);
                              dispose();

                            }

                        }else{
                            JOptionPane.showMessageDialog(null,"Incorrect Details : Try again");
                        }


                        resultSet.close();
                        statement.close();
                        connection.close();

                    }catch (Exception e){
                      e.printStackTrace();
                    }


                }else{
                    JOptionPane.showMessageDialog(null,"Login Error");
                }
            }
        });

        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateNewAccount createNewAccount = new CreateNewAccount();
                createNewAccount.setVisible(true);
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Login l = new Login();
            l.setVisible(true);
        });
    }
}
