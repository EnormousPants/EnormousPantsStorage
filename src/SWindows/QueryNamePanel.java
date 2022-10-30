/*
 * Created by JFormDesigner on Sun Oct 30 18:53:46 HKT 2022
 */

package SWindows;

import Dao.GoodsDao;
import entity.Goods;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author B20040628梅从尧
 */
public class QueryNamePanel extends JFrame {
    public static String Name= null;
    DefaultTableModel dtm;
    public QueryNamePanel() {
        initComponents();
    }

    private void okButtonMouseClicked(MouseEvent e) {
        this.dispose();
    }

    private void button1MouseClicked(MouseEvent e) {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
        Name = (String) comboBox1.getSelectedItem();
        List<Goods> list = GoodsDao.QueryByName(Name);
        for(int i =0;i<list.size();i++){
            Goods goods=list.get(i);
            dtm.addRow(new Object[]{goods.getId(),goods.getName(),goods.getDate(),goods.getQGP()});
        }
    }
    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        textField1 = new JTextField();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle("\u8d27\u7269\u5217\u8868-\u6309\u540d\u79f0\u67e5\u8be2");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 75, 135, 85, 250, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {51, 247, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //---- textField1 ----
                textField1.setText("\u8d27\u7269\u7c7b\u522b");
                contentPanel.add(textField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(comboBox1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
                //---- comboBox1 ----
                comboBox1.addItem("卫尤辣条");
                comboBox1.addItem("梅森午餐肉");
                comboBox1.addItem("老干爹");
                comboBox1.addItem("可爱多多");
                comboBox1.addItem("三金水饺");
                //---- button1 ----
                button1.setText("\u67e5\u8be2");
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                contentPanel.add(button1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== scrollPane1 ========
                {
                    String[] head ={"id","货物名称","录入日期","剩余保质期"};
                    dtm = new DefaultTableModel();
                    table1 = new JTable(dtm);
                    for (int i=0;i<head.length;i++){
                        dtm.addColumn(head[i]);
                    }

                        scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1, new GridBagConstraints(0, 1, 5, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
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
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
