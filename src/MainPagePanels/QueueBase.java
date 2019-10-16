package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueueBase extends JFrame{

    /**
     * The main base which contains the
     * URLs of the queue...
     * SaveDownload class will save them in the
     * queueItem object
     */

    private JList<String> list = new JList<>();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JButton up = new JButton("Up");
    private JButton down = new JButton("Down");
    private JButton remove = new JButton("Remove");

    QueueBase() {

        list.setModel(model);

        up.setBackground(Color.BLACK);
        down.setBackground(Color.BLACK);
        remove.setBackground(Color.BLACK);
        up.setForeground(new Color(243,233,167));
        down.setForeground(new Color(243,233,167));
        remove.setForeground(new Color(243,233,167));

        Listener listener = new Listener();
        try {
            up.addActionListener(listener);
            down.addActionListener(listener);
            remove.addActionListener(listener);
        } catch (Exception ignored) {

        }

        JScrollPane scrollPane = new JScrollPane(list);

        JPanel panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(scrollPane,-2,250,Short.MAX_VALUE)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(up,-2,-2,Short.MAX_VALUE)
                        .addComponent(down,-2,-2,Short.MAX_VALUE)
                        .addComponent(remove,-2,-2,Short.MAX_VALUE)
                )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane,-2,-2,Short.MAX_VALUE)
                .addGroup(
                        layout.createSequentialGroup()
                        .addComponent(up,-2,-2,Short.MAX_VALUE)
                        .addComponent(down,-2,-2,Short.MAX_VALUE)
                        .addComponent(remove,-2,-2,Short.MAX_VALUE)
                )
        );

        setContentPane(panel);
        setSize(350,200);
        setLocationRelativeTo(MainFrame.frame);
    }

    public void addTolist(String str) {
        model.add(model.size(),str);
    }

    private void removeFromList() {
        model.removeElement(list.getSelectedValue());
    }

    private class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==up){
                String selectedItem = list.getSelectedValue();//get item value
                int itemIndex = list.getSelectedIndex();// get item index
                DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();// get list model

                if(itemIndex > 0){
                    model.remove(itemIndex);// remove selected item from the list
                    model.add(itemIndex - 1, selectedItem);// add the item to a new position in the list
                    list.setSelectedIndex(itemIndex - 1);// set selection to the new item
                }

            } else if (e.getSource()==down) {
                String selectedItem = list.getSelectedValue();
                int itemIndex = list.getSelectedIndex();
                DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();

                if( itemIndex < model.getSize() -1 ){
                    model.remove(itemIndex);
                    model.add(itemIndex + 1, selectedItem);
                    list.setSelectedIndex(itemIndex + 1);
                }
            } else if (e.getSource()==remove) {
                removeFromList();
            }
        }
    }
    public DefaultListModel<String> getModel() {
        return model;
    }
}
