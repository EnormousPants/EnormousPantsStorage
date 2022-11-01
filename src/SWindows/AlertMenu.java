/*
 * Created by JFormDesigner on Sun Oct 30 17:49:38 HKT 2022
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
public class AlertMenu extends JFrame {
    public AlertMenu() {
        initComponents();
    }

    private void okButtonMouseClicked(MouseEvent e) {
        this.dispose();
    }

    private void initComponents() {
        DefaultTableModel dtm ;
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle("\u8fc7\u671f\u8b66\u544a");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 549, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 247, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //---- label1 ----
                label1.setText("\u8b66\u544a\uff1a\u4ee5\u4e0b\u5546\u54c1\u5373\u5c06\u6216\u5df2\u7ecf\u8fc7\u671f\uff01");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
                contentPanel.add(label1, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== scrollPane1 ========
                {
                    String[] head ={"id","货物名称","录入日期","剩余保质期(天)"};
                    dtm = new DefaultTableModel();
                    table1 = new JTable(dtm);
                    for (int i=0;i<head.length;i++){
                        dtm.addColumn(head[i]);
                    }
                    List<Goods> list = GoodsDao.Alert_L();
                    for(int i =0;i<list.size();i++){
                        Goods goods=list.get(i);
                        dtm.addRow(new Object[]{goods.getId(),goods.getName(),goods.getDate(),goods.getQGP()});
                    }
                    scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
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
        contentPane.add(dialogPane, BorderLayout.CENTER);
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
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
