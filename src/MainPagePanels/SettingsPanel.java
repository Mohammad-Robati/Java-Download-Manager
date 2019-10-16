package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsPanel extends JPanel{

    /**
     * Settings panel...
     * contains download limits
     * output direction
     * look and feel set
     * and restricted URLs
     */

    JCheckBox checkBox;
    JSpinner limitSpinner;
    private JRadioButton nimbusRadioButton;
    private JRadioButton windowsRadioButton;
    private JRadioButton defaultRadioButton;
    private JButton okButton;
    private JButton restrictedURLsButton;
    private LookAndFeelChanges lookAndFeelChanges;
    private RestrictedURLs restrictedURLs;
    private JFrame frameForRestrictedURLs;
    public static String currentPath = "src";

    SettingsPanel(){
        JLabel labelForDownloadLimit = new JLabel("Download Limit : ");
        JLabel labelForDownloadDirectory = new JLabel("Downloads Directory : ");
        JLabel labelForLookAndFeel = new JLabel("Set Look And Feel : ");
        JFileChooser fileChooser = new JFileChooser();
        JSeparator separator1 = new JSeparator();
        JSeparator separator2 = new JSeparator();
        okButton = new JButton("Ok");
        restrictedURLsButton = new JButton("Restricted URLs");
        lookAndFeelChanges = new LookAndFeelChanges();
        restrictedURLs = new RestrictedURLs();
        limitSpinner = new JSpinner();
        limitSpinner.setEnabled(false);

        checkBox = new JCheckBox("No Limit");
        checkBox.setSelected(true);

        ActionListener actionListener = new ActionListener();
        checkBox.addActionListener(actionListener);
        okButton.addActionListener(actionListener);
        restrictedURLsButton.addActionListener(actionListener);

        frameForRestrictedURLs = new JFrame("Restricted URLs");
        frameForRestrictedURLs.setContentPane(restrictedURLs);
        frameForRestrictedURLs.setSize(260, 200);
        frameForRestrictedURLs.setLocationRelativeTo(MainFrame.frame);

        JPanel radioButtonPanel = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();
        nimbusRadioButton = new JRadioButton("Nimbus");
        windowsRadioButton = new JRadioButton("Windows");
        defaultRadioButton = new JRadioButton("Default");
        buttonGroup.add(nimbusRadioButton);
        buttonGroup.add(windowsRadioButton);
        buttonGroup.add(defaultRadioButton);

        defaultRadioButton.setSelected(true);
        radioButtonPanel.add(nimbusRadioButton);
        radioButtonPanel.add(windowsRadioButton);
        radioButtonPanel.add(defaultRadioButton);
        nimbusRadioButton.addActionListener(actionListener);
        windowsRadioButton.addActionListener(actionListener);
        defaultRadioButton.addActionListener(actionListener);

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addActionListener(e -> currentPath = fileChooser.getSelectedFile().getPath());

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        limitSpinner.setMaximumSize(new Dimension(Short.MAX_VALUE,15));
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(labelForDownloadLimit)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(checkBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(limitSpinner)
                )
                        .addComponent(separator1)
                .addComponent(labelForDownloadDirectory)
                .addComponent(fileChooser)
                        .addComponent(separator2)
                .addComponent(labelForLookAndFeel)
                .addComponent(radioButtonPanel)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(restrictedURLsButton)
                                        .addGap(20,40,Short.MAX_VALUE)
                                        .addComponent(okButton)
                        )
                )
                .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                .addGap(5)
                                                        .addComponent(labelForDownloadLimit)
                                        )
                                        .addComponent(checkBox)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGap(3)
                                                        .addComponent(limitSpinner)
                                        )
                        )
                        .addGap(10)
                        .addComponent(separator1)
                        .addComponent(labelForDownloadDirectory)
                        .addComponent(fileChooser)
                        .addComponent(separator2)
                        .addComponent(labelForLookAndFeel)
                        .addComponent(radioButtonPanel)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(restrictedURLsButton)
                                        .addComponent(okButton, GroupLayout.Alignment.TRAILING)
                        )
                .addContainerGap()
        );
    }
    private class ActionListener implements java.awt.event.ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==okButton) {
                if(nimbusRadioButton.isSelected()){
                    lookAndFeelChanges.setLookAndFeel("Nimbus");
                } else if (windowsRadioButton.isSelected()) {
                    lookAndFeelChanges.setLookAndFeel("Windows");
                } else if (defaultRadioButton.isSelected()){
                    lookAndFeelChanges.setLookAndFeel("Metal");
                }
                ToolbarPanel.frameForSettingsPanel.setVisible(false);
                lookAndFeelChanges.update();
            } else if(e.getSource()==checkBox){
                if(checkBox.isSelected()){
                    limitSpinner.setEnabled(false);
                } else {
                    limitSpinner.setEnabled(true);
                }
            } else if (e.getSource()==restrictedURLsButton){
                frameForRestrictedURLs.setVisible(true);
            }
        }

        }
    public RestrictedURLs getRestrictedURLs() {
        return restrictedURLs;
    }

    public JRadioButton getNimbusRadioButton() {
        return nimbusRadioButton;
    }

    public JRadioButton getWindowsRadioButton() {
        return windowsRadioButton;
    }

    public JRadioButton getDefaultRadioButton() {
        return defaultRadioButton;
    }

    public JButton getOkButton() {
        return okButton;
    }

}
