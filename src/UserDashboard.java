import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JPanel {

    public UserDashboard(){
        setLayout(new BorderLayout());
        setBackground(Color.white);
        Font font = new Font("SansSerif",Font.PLAIN,27);

        JLabel title = new JLabel("MARINGO USER DASHBOARD");
        title.setFont(new Font("SansSerif",Font.BOLD,40));

        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(0,300));
        p1.setLayout(new FlowLayout(FlowLayout.CENTER,0,125));
        p1.add(title);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1,2,0,0));

        JButton btnStore = new JButton("STORE");
        btnStore.setFont(font);
        btnStore.setBackground(new Color(230,230,250));
        JButton btnGames = new JButton("GAMES");
        btnGames.setFont(font);
        btnGames.setBackground(new Color(230,230,250));
        p2.add(btnStore);p2.add(btnGames);


        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);

        btnStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserMain.usercardLayout.show(UserMain.mainUserPanel,"clubStorePanel");

            }
        });

        btnGames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserMain.usercardLayout.show(UserMain.mainUserPanel,"gamesPlayedDataPanel");

            }
        });

    }
}
