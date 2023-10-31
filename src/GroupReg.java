import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupReg extends JPanel {
    public GroupReg(){
        add(new JLabel("Register Group"));
        setBackground(Color.white);
        setLayout(null);

        Font font = new Font("SansSerif",Font.PLAIN,22);

        JLabel lGrpName = new JLabel("GROUP NAME");
        lGrpName.setFont(font);
        lGrpName.setBounds(500,200,200,40);
        JTextField tfGrpName = new JTextField(30);
        tfGrpName.setBounds(700,200,300,40);
        add(lGrpName); add(tfGrpName);

        JLabel lGrpCode = new JLabel("GROUP CODE");
        lGrpCode.setFont(font);
        lGrpCode.setBounds(500,250,200,40);
        JTextField tfGrpCode = new JTextField(30);
        tfGrpCode.setBounds(700,250,300,40);
        add(lGrpCode); add(tfGrpCode);


        JLabel lGrpNum = new JLabel("POPULATION");
        lGrpNum.setFont(font);
        lGrpNum.setBounds(500,300,200,40);
        JTextField tfGrpNum = new JTextField(30);
        tfGrpNum.setBounds(700,300,300,40);
        add(lGrpNum); add(tfGrpNum);


        JLabel lGrpType = new JLabel("GROUP TYPE");
        lGrpType.setFont(font);
        lGrpType.setBounds(500,350,200,40);
        String []grpTypeOpt = {"HIghSchool","University","Religious Group"};
        JComboBox <String> grpCb = new JComboBox<>(grpTypeOpt);
        grpCb.setBounds(700,350,300,40);
        add(lGrpType);add(grpCb);


        JLabel lCharge = new JLabel("CHARGE");
        lCharge.setFont(font);
        lCharge.setForeground(Color.gray);
        lCharge.setBounds(500,400,300,40);
        JTextField tfGrpCharge = new JTextField();
        tfGrpCharge.setEditable(false);
        tfGrpCharge.setBounds(700,400,300,40);
        add(lCharge);add(tfGrpCharge);


        JButton grpBtn = new JButton("Register Group");
        grpBtn.setBounds(600,550,300,50);
        add(grpBtn);


        grpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(tfGrpNum.getText()!=""){
                    int a = Integer.valueOf(tfGrpNum.getText())*500;
                    tfGrpCharge.setText(String.valueOf(a));
                    JOptionPane.showMessageDialog(null,"Sh " + a + " Will be debited");
                    int mpesaNum= Integer.valueOf(JOptionPane.showInputDialog("Enter Mpesa NUmber"));
                    if(mpesaNum >=10000000){
                        JOptionPane.showMessageDialog(null," Amount " + a + " Will be debited from " + mpesaNum + " Mpesa Account");
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid Mpesa Number");
                    }
                }
            }
        });




    }
}
