import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;



public class Main extends JFrame {

    private JPanel mainPanel;
    private  CardLayout cardLayout;

    public Main(){
        setTitle("Maringo Sports Club");
        setSize(1500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(getContentPane().getWidth(),50));

        JMenu dashBoardMenu = new JMenu("DashBoard");
        JMenu regMenu = new JMenu("Register");
        JMenu gamesMenu = new JMenu("Games");
        JMenu clubStoreMenu = new JMenu("Store");
        JMenu infoMenu = new JMenu("Info");
        JMenu recordsMenu = new JMenu("Records");

        JMenuItem regUser = new JMenuItem("New User");
        JMenuItem regGroup = new JMenuItem("New Group");
        regMenu.add(regUser);regMenu.add(regGroup);

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem returnGameItemsMI= new JMenuItem("Return Items");
        gamesMenu.add(newGameMenuItem);gamesMenu.add(returnGameItemsMI);

        JMenuItem infoRegUserDataItem = new JMenuItem("Registered Users");
        JMenuItem gamesPlayedMI = new JMenuItem("Games Played");
        infoMenu.add(infoRegUserDataItem);infoMenu.add(gamesPlayedMI);

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
        UpdateItems updateItemsPanel = new UpdateItems();


        mainPanel.add(dashBoardPanel,"dashBoardPanel");
        mainPanel.add(groupRegPanel,"groupRegPanel");
        mainPanel.add(userRegPanel,"userRegPanel");
        mainPanel.add(clubStorePanel,"clubStorePanel");
        mainPanel.add(newgame,"newGamePanel");
        mainPanel.add(returnGameItemPanel,"returnGameItemPanel");
        mainPanel.add(regUsersData,"regUserDataPanel");
        mainPanel.add(updateItemsPanel,"updateItemsPanel");


        add(mainPanel,BorderLayout.CENTER);
        setVisible(true);

        dashBoardMenu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Handle the menu click here
                    cardLayout.show(mainPanel,"dashBoardPanel");
                }
            }
        });

        clubStoreMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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