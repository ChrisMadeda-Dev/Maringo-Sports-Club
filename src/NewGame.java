import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class NewGame extends JPanel {
    public NewGame(){
        setLayout(null);
        Font font = new Font("SansSerif", Font.PLAIN,18);

        JLabel lGameType=  new JLabel("GAME TYPE");
        lGameType.setFont(font);
        String []gameTypes = {"Basket Ball"," FootBall"};
        JComboBox <String> cbGameType = new JComboBox<>(gameTypes);
        lGameType.setBounds(500,200,150,40);
        cbGameType.setBounds(700,200,350,40);
        add(lGameType);add(cbGameType);


        JLabel lGameCategory=  new JLabel("GAME CATEGORY");
        lGameCategory.setFont(font);
        String []gameCategory = {"Internal Game","External Game"};
        JComboBox <String> cbGameCategory = new JComboBox<>(gameCategory);
        lGameCategory.setBounds(500,250,200,40);
        cbGameCategory.setBounds(700,250,350,40);
        add(lGameCategory);add(cbGameCategory);

        JLabel lTeamName = new JLabel("TEAM NAME");
        JTextField tfTeamName = new JTextField();
        lTeamName.setBounds(500,300,150,40);
        lTeamName.setFont(font);
        tfTeamName.setBounds(700,300,350,40);
        add(lTeamName);add(tfTeamName);


        JLabel lTeamId = new JLabel("TEAM ID");
        JTextField tfTeamId = new JTextField();
        lTeamId.setBounds(500,350,150,40);
        lTeamId.setFont(font);
        tfTeamId.setBounds(700,350,350,40);
        add(lTeamId);add(tfTeamId);


        JLabel lTeamPopulation = new JLabel("TEAM POPULATION");
        JTextField tfTeamPopulation = new JTextField();
        lTeamPopulation.setBounds(500,400,150,40);
        lTeamPopulation.setFont(font);
        tfTeamPopulation.setBounds(700,400,350,40);
        add(lTeamPopulation);add(tfTeamPopulation);


        JLabel lPatronName = new JLabel("PATRON NAME");
        JTextField tfPatronName = new JTextField();
        lPatronName.setBounds(500,450,150,40);
        lPatronName.setFont(font);
        tfPatronName.setBounds(700,450,350,40);
        add(lPatronName);add(tfPatronName);

        JLabel lPatronId = new JLabel("PATRON ID");
        JTextField tfPatronId = new JTextField();
        lPatronId.setBounds(500,500,150,40);
        lPatronId.setFont(font);
        tfPatronId.setBounds(700,500,350,40);
        add(lPatronId);add(tfPatronId);

        JLabel lPatronCommission = new JLabel("COMMISSION");
        JTextField tfPatronCommission = new JTextField();
        lPatronCommission.setBounds(500,550,150,40);
        lPatronCommission.setFont(font);
        lPatronCommission.setForeground(Color.gray);
        tfPatronCommission.setBounds(700,550,350,40);
        tfPatronCommission.setEditable(false);
        add(lPatronCommission);add(tfPatronCommission);

        JLabel lTeamFee = new JLabel("TEAM FEE");
        JTextField tfTeamFee = new JTextField();
        tfTeamFee.setEditable(false);
        lTeamFee.setBounds(500,600,150,40);
        lTeamFee.setFont(font);
        lTeamFee.setForeground(Color.gray);
        tfTeamFee.setBounds(700,600,350,40);
        add(lTeamFee);add(tfTeamFee);


        JButton btnNewGame = new JButton("Set New Game");
        btnNewGame.setBounds(580,700,400,50);
        btnNewGame.setBackground(Color.CYAN);
        add(btnNewGame);

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
                    Statement statement = connection.createStatement();
                    String insertToSql = "INSERT INTO games_played(gameType,gameCategory,teamName,teamId,teamPopulation,patronName,patronId,patronCommission,teamFee)" +
                            "VALUE(?,?,?,?,?,?,?,?,?) ";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertToSql);

                    if(tfPatronId.getText()!="" && tfTeamId.getText()!=""){

                        System.out.println(cbGameCategory.getSelectedItem());
                        System.out.println(cbGameType.getSelectedItem());

                        preparedStatement.setString(1,String.valueOf(cbGameType.getSelectedItem()));
                        preparedStatement.setString(2,String.valueOf(cbGameCategory.getSelectedItem()));
                        preparedStatement.setString(3,tfTeamName.getText());
                        preparedStatement.setString(4,tfTeamId.getText());
                        preparedStatement.setString(5,tfTeamPopulation.getText());
                        preparedStatement.setString(6,tfPatronName.getText());
                        preparedStatement.setString(7,tfPatronId.getText());


                        if(String.valueOf(cbGameCategory.getSelectedItem())=="External Game"){
                            int teamFee = Integer.valueOf(tfTeamPopulation.getText())*500;
                            double b = 0.2 * teamFee;

                            System.out.println(teamFee);

                            preparedStatement.setDouble(8,b);
                            preparedStatement.setInt(9,teamFee);
                            JOptionPane.showMessageDialog(null,"Amount"+teamFee+" will be debited");
                        }else{
                            preparedStatement.setDouble(8,0.0);
                            preparedStatement.setInt(9,0);
                        }

                    }

                    preparedStatement.executeUpdate();
                    connection.close();
                    JOptionPane.showMessageDialog(null,"New Game is Added");


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
}
