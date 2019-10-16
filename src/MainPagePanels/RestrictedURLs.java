package MainPagePanels;

import javax.swing.*;
import java.awt.*;

public class RestrictedURLs extends JPanel {

    /**
     * contains a list which contains
     * filtered files and URLs.
     */

        private JList<String> list;
        private DefaultListModel<String> model;
        private JTextField textField;

        RestrictedURLs() {
            setLayout(new BorderLayout());
            model = new DefaultListModel<>();
            list = new JList<>(model);
            textField = new JTextField();
            JScrollPane pane = new JScrollPane(list);
            JButton addButton = new JButton("Add URL");
            JButton removeButton = new JButton("Remove URL");

            addButton.addActionListener(e -> model.add(0,textField.getText()));
            removeButton.addActionListener(e -> model.removeElement(list.getSelectedValue()));

            textField.setBackground(Color.white);
            textField.setForeground(Color.BLACK);
            list.setBackground(new Color(243,233,167));
            list.setForeground(Color.BLACK);

            GroupLayout layout = new GroupLayout(this);
            setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(textField)
                            .addComponent(pane)
                            .addGroup(
                                    layout.createSequentialGroup()
                                            .addComponent(addButton,-1,-1,Short.MAX_VALUE)
                                            .addComponent(removeButton,-1,-1,Short.MAX_VALUE)
                            )
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addComponent(textField,30,-1,30)
                            .addComponent(pane)
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(addButton)
                                            .addComponent(removeButton)
                            )
            );

        }
    public DefaultListModel<String> getModel() {
        return model;
    }
}

