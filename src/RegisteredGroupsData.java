import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisteredGroupsData extends JPanel {
    public RegisteredGroupsData (){
        setLayout(null);
        setBackground(Color.white);

        JLabel ltitle = new JLabel("MARINGO REGISTERED GROUPS");
        ltitle.setFont(new Font("SansSerif",Font.BOLD,40));
        ltitle.setBounds(420,100,750,50);
        //ltitle.setForeground(Color.DARK_GRAY);
        add(ltitle);

        JTable groupsTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Group Id");
        tableModel.addColumn("Group Name");
        //tableModel.addColumn("Group Code");
        tableModel.addColumn("Group Type");
        tableModel.addColumn("Group Population");
        tableModel.addColumn("Charge Paid");

        groupsTable.setModel(tableModel);
        groupsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MaringoSportsClub","root","#jonam.81");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM group_registration";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String groupId=resultSet.getString("groupId");
                String groupName= resultSet.getString("groupName");
                //String groupCode = resultSet.getString("groupCode");
                String groupType = resultSet.getString("groupType");
                String groupPopulation = resultSet.getString("population");
                String charge = resultSet.getString("charge");

                tableModel.addRow(new Object[]{groupId,groupName,groupType,groupPopulation,charge});
            }

            JScrollPane scrollPane = new JScrollPane(groupsTable);
            scrollPane.setBounds(170,200,1200,600);
            add(scrollPane);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
