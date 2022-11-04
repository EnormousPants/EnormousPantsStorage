/*
 * Created by JFormDesigner on Sun Oct 30 10:38:17 HKT 2022
 */

package SWindows;

import Dao.GoodsDao;
import util.DateCalc;
import util.QGPSet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author B20040628梅从尧
 */
public class UpdatePanel extends JFrame {
    public UpdatePanel() {
        initComponents();
    }

    private void okButtonMouseClicked(MouseEvent e) {
        GoodsDao dao = new GoodsDao();
        String id = textField1.getText();
        String name = (String) comboBox1.getSelectedItem();
        String date = textField2.getText();
        if(date==null||date.length()<=0||id==null||id.length()<=0){
            new NullPanel();
        }
        QGPSet qgpSet = new QGPSet();
        int mqgp= qgpSet.qgpSet(name);
        DateCalc dateCalc= new DateCalc();
        int qgp=dateCalc.Calc(date,mqgp);
        int flag=dao.updateBySingle(id,name,date,qgp);
        if(flag==1)
            new SuccessPanel();
        else new FailPanel();
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        textField1 = new JTextField();
        comboBox1 = new JComboBox();
        textField2 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle("\u8bf7\u8f93\u5165\u8d27\u7269\u4fe1\u606f");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {145, 145, 140, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {45, 40, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("id");
                contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("\u540d\u79f0");
                contentPanel.add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("\u5165\u5e93\u65e5\u671f(\u8bf7\u6309yyyy-MM-dd\u683c\u5f0f\u8f93\u5165)");
                contentPanel.add(label3, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 5, 0), 0, 0));
                contentPanel.add(textField1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
                contentPanel.add(comboBox1, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
                contentPanel.add(textField2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
                //---- comboBox1 ----
                comboBox1.addItem("卫尤辣条");
                comboBox1.addItem("梅森午餐肉");
                comboBox1.addItem("老干爹");
                comboBox1.addItem("可爱多多");
                comboBox1.addItem("三金水饺");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("\u786e\u5b9a");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
