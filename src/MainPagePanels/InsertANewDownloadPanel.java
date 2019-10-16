package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

class InsertANewDownloadPanel extends JFrame {

    private JTextField URLTextField;
    private JCheckBox queueOrNot;
    private JComboBox<String> queues;
    private JButton okButton;
    Download download;

    InsertANewDownloadPanel() throws HeadlessException {

        /*
      Panel that asks for the url and if the user
      wants it to be inserted in a queue
     */
        JLabel URLIcon = new JLabel(Icons.LINK);
        URLTextField = new JTextField();
        queueOrNot = new JCheckBox("Queue");
        queues = new JComboBox<>();
        queues.setEnabled(false);
        queues.setBackground(Color.white);
        JSeparator separator = new JSeparator();
        okButton = new JButton("Ok");

        Listener listener = new Listener();
        queueOrNot.addActionListener(listener);
        queues.addActionListener(listener);
        okButton.addActionListener(listener);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(URLIcon)
                                                        .addGap(5)
                                                        .addComponent(URLTextField, -2, 200, Short.MAX_VALUE)
                                        )
                                        .addComponent(separator)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(queueOrNot)
                                                        .addGap(5)
                                                        .addComponent(queues, -2, 200, Short.MAX_VALUE)
                                        )
                                        .addComponent(okButton, GroupLayout.Alignment.TRAILING)
                        )
                        .addContainerGap()

        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(URLIcon)
                                        .addGap(1)
                                        .addComponent(URLTextField, -2, 20, -2)
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separator)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(queueOrNot)
                                        .addGap(1)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGap(3)
                                                        .addComponent(queues, -2, 20, -2)
                                        )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)
                        .addContainerGap()
        );

        add(panel);
        setSize(400, 140);
        setResizable(false);
        setLocationRelativeTo(MainFrame.frame);
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == queueOrNot) {
                if (queueOrNot.isSelected()) {
                    queues.setEnabled(true);
                    for (ItemPanel q : MainPanel.queuesPanel.panels) {
                        queues.addItem(q.getName_());
                    }
                } else {
                    queues.setEnabled(false);
                }
            } else if (e.getSource() == okButton) {
                URL verifiedUrl = verifyUrl(URLTextField.getText());
                boolean flag = false;
                if (verifiedUrl != null) {
                    for (int i = 0; i < MainPanel.toolbarPanel.getSettingsPanel().getRestrictedURLs().getModel().size(); i++) {
                        if (verifiedUrl.toString().contains(MainPanel.toolbarPanel.getSettingsPanel().getRestrictedURLs().getModel().get(i))) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        JOptionPane.showMessageDialog(MainFrame.frame, "URL is filtered!", "Warning", JOptionPane.INFORMATION_MESSAGE
                                , Icons.CANCEL_TOOLBAR);
                    } else {
                        if (queueOrNot.isSelected()) {
                            for (ItemPanel q : MainPanel.queuesPanel.panels) {
                                if (q.getName_().equals(queues.getSelectedItem())) {
                                    ((QueueItem) q).getQueueBase().addTolist(verifiedUrl.toString());
                                    if(!(((QueueItem) q).getTimer().equals("null") && ((QueueItem) q).getStartAt().equals("null"))) {
                                        ((QueueItem) q).setOnline(true);
                                    }
                                }
                            }
                            setVisible(false);
                        } else {
                            if (MainPanel.toolbarPanel.getSettingsPanel().checkBox.isSelected()
                                    || ((int) MainPanel.toolbarPanel.getSettingsPanel().limitSpinner.getValue() < (MainPanel.processingPanel.panels.size()))) {
                                MainPanel.processingPanel.insertDownload(new InProcessItemPanel(new Download(verifiedUrl)));
                                MainPanel.toolbarPanel.getSort().setSelectedItem("By Name");
                                MainPanel.toolbarPanel.getSort().setSelectedItem("By Date");
                                MainPanel.processingPanel.validate();
                            } else {
                                if (((int) MainPanel.toolbarPanel.getSettingsPanel().limitSpinner.getValue()) > (MainPanel.processingPanel.panels.size())) {
                                    MainPanel.processingPanel.insertDownload(new InProcessItemPanel(new Download(verifiedUrl)));
                                    MainPanel.toolbarPanel.getSort().setSelectedItem("By Name");
                                    MainPanel.toolbarPanel.getSort().setSelectedItem("By Date");
                                    MainPanel.processingPanel.validate();
                                } else {
                                    JOptionPane.showMessageDialog(MainFrame.frame, "You Have Reached Your Download Limit!",
                                            "Warning", JOptionPane.INFORMATION_MESSAGE
                                            , Icons.CANCEL_TOOLBAR);
                                    new DownloadWaiting(new Download(verifiedUrl));
                                }
                            }
                            setVisible(false);
                            URLTextField.setText(""); // reset add text field
                        }
                    }
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.frame,
                                "Invalid Download URL", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
            }
        }
        private URL verifyUrl(String url) {

            // Only allow HTTP URLs.
            if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
                return null;

            // Verify format of URL.
            URL verifiedUrl;
            try {
                verifiedUrl = new URL(url);
            } catch (Exception e) {
                return null;
            }

            // Make sure URL specifies a file.
            if (verifiedUrl.getFile().length() < 2)
                return null;

            return verifiedUrl;
        }

    }
}
