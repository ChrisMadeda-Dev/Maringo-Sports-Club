import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateNewAccount extends JFrame {
    public CreateNewAccount (){
        setTitle("Maringo Sports Club");
        setLayout(null);
        setSize(700,600);
        //setBackground(Color.white);
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("SansSerif",Font.PLAIN,18);

        JLabel lName= new JLabel("NAME");
        lName.setBounds(100,180,100,40);
        lName.setFont(font);
        JTextField tfName = new JTextField();
        tfName.setBounds(200,180,400,40);
        add(lName);add(tfName);

        JLabel lPass= new JLabel("PASS KEY");
        lPass.setBounds(100,230,100,40);
        lPass.setFont(font);
        JTextField tfPass = new JTextField();
        tfPass.setBounds(200,230,400,40);
        add(lPass);add(tfPass);

        JButton btnCreateNewAccount = new JButton("CREATE ACCOUNT");
        btnCreateNewAccount.setBounds(190,300,350,40);
        btnCreateNewAccount.setBackground(new Color(230,230,250));
        add(btnCreateNewAccount);

        btnCreateNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    String insertToSql = "INSERT INTO user_login(userName,passWord)VALUE(?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertToSql);

                    if(!tfName.getText().isEmpty() && !tfPass.getText().isEmpty()){

                        int choice = JOptionPane.showConfirmDialog(null,"Confirm Your Details. Name : " +tfName.getText()+ " " +
                                "Pass key : " + tfPass.getText(),"Yes or No",JOptionPane.YES_NO_OPTION);

                        if(choice==JOptionPane.YES_OPTION){
                            preparedStatement.setString(1,tfName.getText());
                            preparedStatement.setString(2,tfPass.getText());

                            preparedStatement.executeUpdate();
                            Main mainFrame = new Main();
                            mainFrame.setVisible(true);
                            dispose();

                        }else{
                            JOptionPane.showMessageDialog(null,"Please Reenter your details");
                            tfName.setText("");
                            tfPass.setText("");
                        }


                        preparedStatement.close();
                        connection.close();
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: Cant create account");
                    }

                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Error: unable to connect to database");
                    JOptionPane.showMessageDialog(null,e);
                }

                JOptionPane.showMessageDialog(null,"End of code");
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateNewAccount createNewAccount = new CreateNewAccount();
                createNewAccount.setVisible(true);
            }
        });
    }
}
