import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;



public class Main extends JFrame {

    private String currentUser;
    public static JPanel mainPanel;
    public static CardLayout cardLayout;

    //Sets the current User
    public void setCurrentUser(String user){
        currentUser = user;
        JOptionPane.showMessageDialog(null,currentUser);
    }

    public Main(){

        setTitle("Maringo Sports Club");
        setSize(1500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getContentPane().getWidth(),50));
        Color lavenderColor = new Color(176,196,222);
        //menuBar.setBackground(lavenderColor);

        JMenu dashBoardMenu = new JMenu("DashBoard");
        JMenu regMenu = new JMenu("Register");
        JMenu gamesMenu = new JMenu("Games");
        JMenu clubStoreMenu = new JMenu("Store");
        JMenu infoMenu = new JMenu("Info");
        JMenu recordsMenu = new JMenu("Records");

        //sub Menu
        JMenu usersSubMenu= new JMenu("USERS");
        JMenu groupSubMenu = new JMenu("GROUPS");
        JMenu gamesSubMenu = new JMenu("GAMES");
        JMenu storeSubMenu = new JMenu("STORE");

        JMenuItem regUser = new JMenuItem("New User");
        JMenuItem regGroup = new JMenuItem("New Group");
        regMenu.add(regUser);regMenu.add(regGroup);

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem returnGameItemsMI= new JMenuItem("Return Items");
        gamesMenu.add(newGameMenuItem);gamesMenu.add(returnGameItemsMI);

        JMenuItem infoRegUserDataItem = new JMenuItem("Registered Users");
        JMenuItem infoRegGroupMI = new JMenuItem("Registered Groups");
        JMenuItem infoDeletedUsers = new JMenuItem("Deleted Users");
        JMenuItem gamesPlayedMI = new JMenuItem("Games Played");
        JMenuItem itemsDataMI = new JMenuItem("STORE ITEMS");
        JMenuItem purchasedItemMI= new JMenuItem("Purchased Items");
        JMenuItem returnedGameItemsMI = new JMenuItem("Returned Game Items");

        usersSubMenu.add(infoRegUserDataItem);
        usersSubMenu.add(infoDeletedUsers);

        groupSubMenu.add(infoRegGroupMI);

        gamesSubMenu.add(gamesPlayedMI);
        gamesSubMenu.add(returnedGameItemsMI);

        storeSubMenu.add(itemsDataMI);
        storeSubMenu.add(purchasedItemMI);

        infoMenu.add(usersSubMenu);
        infoMenu.add(groupSubMenu);
        infoMenu.add(gamesSubMenu);
        infoMenu.add(storeSubMenu);

        JMenuItem updateItemMI = new JMenuItem("Update Items");
        recordsMenu.add(updateItemMI);

        menuBar.add(dashBoardMenu);menuBar.add(regMenu);menuBar.add(gamesMenu);
        menuBar.add(clubStoreMenu);menuBar.add(infoMenu);menuBar.add(recordsMenu);

        setJMenuBar(menuBar);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        DashBoard dashBoardPanel = new DashBoard();
        GroupReg groupRegPanel = new GroupReg();
        RegUser userRegPanel = new RegUser();
        ClubStore clubStorePanel = new ClubStore();
        NewGame newgame = new NewGame();
        ReturnGameItem returnGameItemPanel = new ReturnGameItem();
        RegUsersData regUsersData = new RegUsersData();
        DeletedUsersData deletedUsersDataPanel = new DeletedUsersData();
        GamesPlayedData gamesPlayedDataPanel = new GamesPlayedData();
        RegisteredGroupsData registeredGroupsDataPanel = new RegisteredGroupsData();
        PurchasedItemsData purchasedItemsDataPanel = new PurchasedItemsData();
        ReturnedGameItemsData returnedGameItemsDataPanel = new ReturnedGameItemsData();
        UpdateItems updateItemsPanel = new UpdateItems();
        ItemsData itemsDataPanel = new ItemsData();


        mainPanel.add(dashBoardPanel,"dashBoardPanel");
        mainPanel.add(groupRegPanel,"groupRegPanel");
        mainPanel.add(userRegPanel,"userRegPanel");
        mainPanel.add(clubStorePanel,"clubStorePanel");
        mainPanel.add(newgame,"newGamePanel");
        mainPanel.add(returnGameItemPanel,"returnGameItemPanel");
        mainPanel.add(regUsersData,"regUserDataPanel");
        mainPanel.add(deletedUsersDataPanel,"deletedUsersDataPanel");
        mainPanel.add(registeredGroupsDataPanel,"regGroupsDataPanel");
        mainPanel.add(gamesPlayedDataPanel,"gamesPlayedDataPanel");
        mainPanel.add(purchasedItemsDataPanel,"purchasedItemsDataPanel");
        mainPanel.add(returnedGameItemsDataPanel,"returnGameItemsDataPanel");
        mainPanel.add(itemsDataPanel,"itemsDataPanel");
        mainPanel.add(updateItemsPanel,"updateItemsPanel");


        add(mainPanel,BorderLayout.CENTER);
        setVisible(true);

        dashBoardMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                cardLayout.show(mainPanel,"dashBoardPanel");
            }
        });

        clubStoreMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                cardLayout.show(mainPanel,"clubStorePanel");
            }
        });

        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"newGamePanel");
            }
        });

        returnGameItemsMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"returnGameItemPanel");
            }
        });


        regGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"groupRegPanel");
            }
        });

        regUser.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"userRegPanel");
            }
        }));

        infoRegUserDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"regUserDataPanel");
            }
        });

        infoDeletedUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"deletedUsersDataPanel");
            }
        });

        infoRegGroupMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"regGroupsDataPanel");
            }
        });

        gamesPlayedMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"gamesPlayedDataPanel");
            }
        });

        itemsDataMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"itemsDataPanel");
            }
        });

        purchasedItemMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"purchasedItemsDataPanel");
            }
        });

        returnedGameItemsMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"returnGameItemsDataPanel");
            }
        });

        updateItemMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(mainPanel,"updateItemsPanel");
            }
        });

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}