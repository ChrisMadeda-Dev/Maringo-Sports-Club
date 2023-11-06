import javax.swing.*;
import java.awt.*;

public class UpdateItems extends JPanel {
    public UpdateItems(){
        setLayout(null);
        setBackground(Color.white);

        Font font = new Font("SansSerif",Font.PLAIN,18);

        JLabel lItems= new JLabel("CHOOSE ITEM");
        lItems.setFont(font);
        lItems.setBounds(480,300,200,40);
        String[]Items= {"Shoes","BOOts","shorts"};
        JComboBox <String> cbItems = new JComboBox<>(Items);
        cbItems.setBounds(680,300,400,40);
        add(lItems);add(cbItems);

        JLabel lItemQuantity = new JLabel("ADD QUANTITY");
        lItemQuantity.setFont(font);
        lItemQuantity.setBounds(480,350,200,40);
        JTextField tfItemQuantity = new JTextField();
        tfItemQuantity.setBounds(680,350,400,40);
        add(lItemQuantity);add(tfItemQuantity);

        JButton btnUpdateItemRecord = new JButton("UPDATE");
        btnUpdateItemRecord.setBounds(590,420,400,50);
        btnUpdateItemRecord.setBackground(Color.CYAN);
        add(btnUpdateItemRecord);

    }
}
