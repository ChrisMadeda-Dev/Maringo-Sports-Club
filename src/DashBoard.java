import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoard extends JPanel {
    public DashBoard(){
        add(new JLabel("DashBoard"));
        setBackground(Color.white);
        setLayout(new BorderLayout(10,0));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JLabel title = new JLabel("MARINGO ADMIN DASHBOARD");
        title.setFont(new Font("SansSerif",Font.BOLD,30));

        Font font = new Font("SansSerif",Font.PLAIN,20);

        p1.setPreferredSize(new Dimension(0,200));
        p2.setLayout(new GridLayout(2,3,0,0));
        p2.setBackground(Color.white);

        p1.setLayout(new FlowLayout(FlowLayout.CENTER,10,70));
        p1.add(title);

        //Main mainFrame = new Main();

        JButton btnUsers = new JButton("REGISTERED USERS");
        btnUsers.setFont(font);
        btnUsers.setBackground( new Color(230,230,250));
        btnUsers.setFocusable(false);

        JButton btnRegisteredGroups = new JButton("REGISTERED GROUPS");
        btnRegisteredGroups.setFont(font);
        btnRegisteredGroups.setBackground( new Color(230,230,250));

        JButton btnGamesPlayed = new JButton("GAMES PLAYED");
        btnGamesPlayed.setFont(font);
        btnGamesPlayed.setBackground( new Color(230,230,250));

        JButton btnPurchasedItems = new JButton("PURCHASED ITEMS");
        btnPurchasedItems.setFont(font);
        btnPurchasedItems.setBackground(new Color(176,196,222));

        JButton btnStoreItems = new JButton("STORE ITEMS");
        btnStoreItems.setFont(font);
        btnStoreItems.setBackground( new Color(176,196,222));

        JButton btnClubStore = new JButton("CLUB STORE");
        btnClubStore.setFont(font);
        btnClubStore.setBackground( new Color(176,196,222));

        p2.add(btnUsers);p2.add(btnRegisteredGroups);p2.add(btnGamesPlayed);
        p2.add(btnPurchasedItems);p2.add(btnStoreItems);p2.add(btnClubStore);

        btnUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
             Main.cardLayout.show(Main.mainPanel,"regUserDataPanel");
            }
        });

        btnRegisteredGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"regGroupsDataPanel");
            }
        });

        btnGamesPlayed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"gamesPlayedDataPanel");
            }
        });

        btnPurchasedItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"purchasedItemsDataPanel");
            }
        });

        btnStoreItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"itemsDataPanel");
            }
        });

        btnClubStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.cardLayout.show(Main.mainPanel,"clubStorePanel");
            }
        });


        p1.setBackground(Color.white);
        p2.setBackground(Color.white);

        add(p1, BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
    }


}
