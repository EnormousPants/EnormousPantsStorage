/*
 * Created by JFormDesigner on Sat Oct 29 21:10:55 HKT 2022
 */

package SWindows;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author B20040628梅从尧
 */
public class TestMenu extends JFrame {

    public TestMenu() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        new AddPanel();
    }

    private void button2MouseClicked(MouseEvent e) {
        new DeletePanel();
    }

    private void button3MouseClicked(MouseEvent e) {
        new UpdatePanel();
    }

    private void button5MouseClicked(MouseEvent e) {
        new OutPanel();
    }

    private void button4MouseClicked(MouseEvent e) {
        String method = (String) comboBox1.getSelectedItem();
        switch (method) {
            case "录入顺序":
                new QueryAllPanel();
                break;
            case "入库日期":
                new QueryDatePanel();
                break;
            case "保质期":
                new QueryQGPPanel();
                break;
            case "货物名称":
                new QueryNamePanel();
                break;
            default:
                new FailPanel();
                break;
        }
    }

    private void button6MouseClicked(MouseEvent e) {
        this.dispose();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        button4 = new JButton();
        button3 = new JButton();
        button2 = new JButton();
        button5 = new JButton();
        buttonBar = new JPanel();
        button6 = new JButton();

        //======== this ========
        setTitle("\u5546\u5e97\u5b58\u8d27\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {245, 20, 125, 105, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {47, 50, 50, 50, 50, 45, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("\u6b22\u8fce\u4f7f\u7528\u5546\u5e97\u5b58\u8d27\u7ba1\u7406\u7cfb\u7edf\uff01");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
                contentPanel.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label2 ----
                label2.setText("\u8bf7\u9009\u62e9\u60f3\u8981\u4f7f\u7528\u7684\u529f\u80fd\uff1a");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));
                contentPanel.add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button1 ----
                button1.setText("\u5f55\u5165\u8d27\u7269\u4fe1\u606f");
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                contentPanel.add(button1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText("\u6309");
                contentPanel.add(label3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(comboBox1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                //---- comboBox1 ----
                comboBox1.addItem("录入顺序");
                comboBox1.addItem("入库日期");
                comboBox1.addItem("保质期");
                comboBox1.addItem("货物名称");
                //---- button4 ----
                button4.setText("\u67e5\u8be2");
                button4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button4MouseClicked(e);
                    }
                });
                contentPanel.add(button4, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- button3 ----
                button3.setText("\u66f4\u65b0\u8d27\u7269\u4fe1\u606f");
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button3MouseClicked(e);
                    }
                });
                contentPanel.add(button3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- button2 ----
                button2.setText("\u5220\u9664\u8d27\u7269\u4fe1\u606f");
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });
                contentPanel.add(button2, new GridBagConstraints(1, 3, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- button5 ----
                button5.setText("\u8fd1\u4fdd\u8d28\u671f\u8d27\u7269\u51fa\u5e93");
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button5MouseClicked(e);
                    }
                });
                contentPanel.add(button5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- button6 ----
                button6.setText("\u5b8c\u6210");
                button6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button6MouseClicked(e);
                    }
                });
                buttonBar.add(button6, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JLabel label3;
    private JComboBox comboBox1;
    private JButton button4;
    private JButton button3;
    private JButton button2;
    private JButton button5;
    private JPanel buttonBar;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
