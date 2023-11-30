import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMain extends JFrame {
    public static JPanel mainUserPanel;
    public static CardLayout usercardLayout;

    public UserMain(){
        setTitle("Moringa Sports Club");
        setLayout(new BorderLayout());
        setSize(1500,1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar userMenuBar = new JMenuBar();
        userMenuBar.setPreferredSize(new Dimension(getContentPane().getWidth(),50));

        JMenu dashboardMenu = new JMenu("Dashboard");
        JMenu storeMenu = new JMenu("Store");
        JMenu gamesMenu = new JMenu("Games");

        userMenuBar.add(dashboardMenu);
        userMenuBar.add(gamesMenu); userMenuBar.add(storeMenu);

        setJMenuBar(userMenuBar);

        usercardLayout = new CardLayout();
        mainUserPanel = new JPanel(usercardLayout);

        UserDashboard userDashboardPanel = new UserDashboard();
        ClubStore clubStorePanel = new ClubStore();
        NewGame newGamePanel = new NewGame();
        GamesPlayedData gamesPlayedDataPanel = new GamesPlayedData();

        mainUserPanel.add(userDashboardPanel,"userDashboardPanel");
        mainUserPanel.add(clubStorePanel,"clubStorePanel");
        mainUserPanel.add(gamesPlayedDataPanel,"gamesPlayedDataPanel");

        dashboardMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                usercardLayout.show(mainUserPanel,"userDashboardPanel");
            }
        });

        storeMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                usercardLayout.show(mainUserPanel,"clubStorePanel");
            }
        });

        gamesMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                usercardLayout.show(mainUserPanel,"gamesPlayedDataPanel");
            }
        });

        add(mainUserPanel,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserMain userMain = new UserMain();
                userMain.setVisible(true);
            }
        });
    }
}
