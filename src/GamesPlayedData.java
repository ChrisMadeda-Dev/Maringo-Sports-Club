import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GamesPlayedData extends JPanel {
    public GamesPlayedData (){
        setLayout(null);
        setBackground(Color.white);

        JLabel ltitle = new JLabel("MARINGO GAMES PLAYED");
        ltitle.setFont(new Font("SansSerif",Font.BOLD,40));
        ltitle.setBounds(430,100,700,50);
        ltitle.setForeground(Color.DARK_GRAY);
        add(ltitle);

        JTable gamesPlayedTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("gameType");
        tableModel.addColumn("gameCategory");
        tableModel.addColumn("teamName");
        tableModel.addColumn("teamId");
        tableModel.addColumn("teamFee");
        tableModel.addColumn("teamPopulation");
        tableModel.addColumn("patronName");
        tableModel.addColumn("patronId");
        tableModel.addColumn("patronCommission");

        gamesPlayedTable.setModel(tableModel);
        gamesPlayedTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM games_played";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String gameType = resultSet.getString("gameType");
                String gameCategory = resultSet.getString("gameCategory");
                String teamName = resultSet.getString("teamName");
                String teamId= resultSet.getString("teamId");
                String teamFee = resultSet.getString("teamFee");
                String teamPopulation = resultSet.getString("teamPopulation");
                String patronName = resultSet.getString("patronName");
                String patronId = resultSet.getString("patronId");
                String patronCommission = resultSet.getString("patronCommission");

                tableModel.addRow(new Object[]{gameType,gameCategory,teamName,teamId,teamFee,teamPopulation,patronName,patronId,patronCommission});

            }

            JScrollPane scrollPane = new JScrollPane(gamesPlayedTable);
            scrollPane.setBounds(260,250,1000,500);
            add(scrollPane);

            connection.close();
            resultSet.close();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
