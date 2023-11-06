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
    public Login(){


        setTitle("Maringo Sport Club");
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setSize(600,500);
        p1.setLayout(null);

        JLabel lName = new JLabel("ENTER NAME");
        JTextField tfName = new JTextField();
        lName.setBounds(100,200,100,50);
        tfName.setBounds(210,200,300,50);
        p1.add(lName);p1.add(tfName);

        JLabel lPin = new JLabel("ENTER PIN");
        JTextField tfPin = new JTextField();
        lPin.setBounds(100,260,100,50);
        tfPin.setBounds(210,260,300,50);
        p1.add(lPin);p1.add(tfPin);


        JButton btn = new JButton("LogIn");
        btn.setBounds(100,350,410,40);
        p1.add(btn);


        add(p1);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(tfName.getText()!=""){
                    boolean isPassCorrect = false;

                    try{
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                        Statement statement = connection.createStatement();

                        String query = "SELECT *  FROM user_login WHERE userName='"+String.valueOf(tfName.getText())+"' AND passWord = '"+String.valueOf(tfPin.getText())+"'";
                        ResultSet resultSet = statement.executeQuery(query);

                        System.out.println(tfName.getText());
                        System.out.println(tfPin.getText());

                        if(resultSet.next()){
                            JOptionPane.showMessageDialog(null,"Logged in");
                            Main m = new Main();
                            m.setVisible(true);
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Try again");
                        }


                        resultSet.close();
                        connection.close();


                    }catch (Exception e){
                      e.printStackTrace();
                    }


                }
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
