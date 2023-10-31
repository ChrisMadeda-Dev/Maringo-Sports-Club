import javax.swing.*;
import java.awt.*;

public class DashBoard extends JPanel {
    public DashBoard(){
        add(new JLabel("DashBoard"));
        setBackground(Color.white);
        setLayout(new BorderLayout(10,0));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JLabel title = new JLabel("MARINGO  DASHBOARD");

        Font font = new Font("SansSerif",Font.BOLD,30);
        title.setFont(font);

        p1.setPreferredSize(new Dimension(0,200));
        p2.setLayout(new GridLayout(2,3,0,0));

        p1.setLayout(new FlowLayout(FlowLayout.CENTER,10,70));
        p1.add(title);


        JButton btn = new JButton("Btn1");
        JButton btn1 = new JButton("Btn2");
        JButton btn2 = new JButton("Btn3");
        JButton btn3 = new JButton("Btn3");
        JButton btn4 = new JButton("Btn3");
        JButton btn5 = new JButton("Btn3");

        p2.add(btn);p2.add(btn1);p2.add(btn2);
        p2.add(btn3);p2.add(btn4);p2.add(btn5);

        p1.setBackground(Color.white);
        p2.setBackground(Color.white);

        add(p1, BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
    }


}
