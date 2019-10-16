package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TabsPanel extends JPanel {
    /**
     * this class will create a panel
     * for tabs which will be put in the
     * left side of panel and handle its
     * listeners and ...
     */
    private JLabel labelForProcessingPanel;
    private JLabel labelForCompletedPanel;
    private JLabel labelForQueuesPanel;
    private JLabel completedPanelSettings;
    private JLabel queuesPanelSettings;
    private JPanel processingPanel;
    private JPanel completedPanel;
    private JPanel queuesPanel;
    private QueueSettingsPanel queueSettingsPanel = new QueueSettingsPanel();

    static JButton updateLanguage_FA;
    static JButton updateLanguage_EN;

    /**
     * Constructor for making instances for fields
     * and run GUI
     */
    TabsPanel() {
        // Making instances for the main panel
        JLabel labelLogo = new JLabel(Icons.LOGO);
        labelForProcessingPanel = new JLabel("Processing",
                Icons.PROCESSING_TABS, SwingConstants.LEFT);
        labelForProcessingPanel.setForeground(new Color(0,0,0));
        labelForCompletedPanel = new JLabel("Completed",
                Icons.COMPLETED_TABS, SwingConstants.LEFT);
        labelForCompletedPanel.setForeground(new Color(0,0,0));
        labelForQueuesPanel = new JLabel("Queues",
               Icons.QUEUES_TABS, SwingConstants.LEFT);
        labelForQueuesPanel.setForeground(new Color(0,0,0));
        completedPanelSettings = new JLabel(Icons.SETTINGS_TABS);
        completedPanelSettings.setToolTipText("Completed Downloads Settings");
        queuesPanelSettings = new JLabel(Icons.SETTINGS_TABS);
        queuesPanelSettings.setToolTipText("Queues Settings");
        processingPanel = new JPanel();
        completedPanel = new JPanel();
        queuesPanel = new JPanel();

        // first values for the tabs
        processingPanel.setEnabled(false);
        completedPanel.setEnabled(true);
        queuesPanel.setEnabled(false);

        processingPanel.setBackground(new Color(255,214,23));
        queuesPanel.setBackground(new Color(255,214,23));
        completedPanel.setBackground(Color.BLACK);
        labelForCompletedPanel.setForeground(new Color(255,255,255));
        labelForCompletedPanel.setIcon(Icons.COMPLETED_TABS_CLICKED);
        completedPanelSettings.setIcon(Icons.SETTINGS_TABS_CLICKED);

        ListenerForMouse listenerForMouse = new ListenerForMouse();
        processingPanel.addMouseListener(listenerForMouse);
        completedPanel.addMouseListener(listenerForMouse);
        queuesPanel.addMouseListener(listenerForMouse);
        completedPanelSettings.addMouseListener(listenerForMouse);
        queuesPanelSettings.addMouseListener(listenerForMouse);
        labelLogo.addMouseListener(listenerForMouse);

        makeProcessingPanelWork(processingPanel);
        makeCompletedPanelWork(completedPanel);
        makeQueuesPanelWork(queuesPanel);

        updateLanguage_FA = new JButton();
        updateLanguage_FA.addActionListener(e -> {
            labelForProcessingPanel.setText(Language.FARSI.PROCESSING.toString());
            labelForCompletedPanel.setText(Language.FARSI.COMPLETED.toString());
            labelForQueuesPanel.setText(Language.FARSI.QUEUES.toString());
        });

        updateLanguage_EN = new JButton();
        updateLanguage_EN.addActionListener(e -> {
            labelForProcessingPanel.setText(Language.ENGLISH.PROCESSING.toString());
            labelForCompletedPanel.setText(Language.ENGLISH.COMPLETED.toString());
            labelForQueuesPanel.setText(Language.ENGLISH.QUEUES.toString());
        });

        // now for the main panel
        setBackground(new Color(255, 214, 23));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        // -1 for default size, -2 for preferred size
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(4,4,4)
                .addComponent(labelLogo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,200,Short.MAX_VALUE)
                .addComponent(processingPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                .addComponent(completedPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                .addComponent(queuesPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelLogo,GroupLayout.PREFERRED_SIZE,180,GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(processingPanel,-2,-1,-2)
                    .addComponent(completedPanel,-2,-1,-2)
                    .addComponent(queuesPanel,-2,-1,-2)
                    .addContainerGap(100,Short.MAX_VALUE)
        );
    }

    /**
     * handle processing tab
     * @param panel which is tabs panel
     */

    private void makeProcessingPanelWork(JPanel panel){
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelForProcessingPanel,-1,-1,Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelForProcessingPanel,-1,-1,Short.MAX_VALUE)
                .addContainerGap()
        );
    }

    /**
     * handle completed tab
     * @param panel which is tabs panel
     */

    private void makeCompletedPanelWork(JPanel panel){
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelForCompletedPanel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,20,Short.MAX_VALUE)
                        .addComponent(completedPanelSettings)
                        .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(labelForCompletedPanel)
                                .addComponent(completedPanelSettings)
                        )
                        .addContainerGap()
        );
    }

    /**
     * handle queues tab
     * @param panel which is tabs panel
     */

    private void makeQueuesPanelWork(JPanel panel){
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelForQueuesPanel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,20,Short.MAX_VALUE)
                        .addComponent(queuesPanelSettings)
                        .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(labelForQueuesPanel)
                                .addComponent(queuesPanelSettings)
                        )
                        .addContainerGap()
        );
    }

    /**
     * private class for handling mouse actions...
     */

    private class ListenerForMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==processingPanel){
                MainPanel.processingPanel.setVisible(true);
                MainPanel.completedPanel.setVisible(false);
                MainPanel.queuesPanel.setVisible(false);
                MainFrame.frame.validate();

            } else if (e.getSource()==completedPanel){
                MainPanel.processingPanel.setVisible(false);
                MainPanel.completedPanel.setVisible(true);
                MainPanel.queuesPanel.setVisible(false);
                MainFrame.frame.validate();
            } else if (e.getSource()==queuesPanel){
                MainPanel.processingPanel.setVisible(false);
                MainPanel.completedPanel.setVisible(false);
                MainPanel.queuesPanel.setVisible(true);
                MainFrame.frame.validate();
            } else if (e.getSource() == completedPanelSettings){
                ToolbarPanel.frameForSettingsPanel.setLocationRelativeTo(MainFrame.frame);
                ToolbarPanel.frameForSettingsPanel.setVisible(true);
            } else if (e.getSource() == queuesPanelSettings) {
                queueSettingsPanel = new QueueSettingsPanel();
                queueSettingsPanel.setLocationRelativeTo(MainFrame.frame);
                queueSettingsPanel.setVisible(true);
            }
            validate();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==processingPanel){
                processingPanel.setBackground(Color.BLACK);
                completedPanel.setBackground(new Color(255,214,23));
                queuesPanel.setBackground(new Color(255,214,23));
                processingPanel.setEnabled(true);
                completedPanel.setEnabled(false);
                queuesPanel.setEnabled(false);
                labelForProcessingPanel.setForeground(new Color(255,255,255));

            } else if (e.getSource()==completedPanel){
                processingPanel.setBackground(new Color(255,214,23));
                completedPanel.setBackground(Color.BLACK);
                queuesPanel.setBackground(new Color(255,214,23));
                processingPanel.setEnabled(false);
                completedPanel.setEnabled(true);
                queuesPanel.setEnabled(false);
                labelForCompletedPanel.setForeground(new Color(255,255,255));
            } else if (e.getSource()==queuesPanel){
                processingPanel.setBackground(new Color(255,214,23));
                completedPanel.setBackground(new Color(255,214,23));
                queuesPanel.setBackground(Color.BLACK);
                processingPanel.setEnabled(false);
                completedPanel.setEnabled(false);
                queuesPanel.setEnabled(true);
                labelForQueuesPanel.setForeground(new Color(255,255,255));
            }
            if(processingPanel.isEnabled()){
                labelForProcessingPanel.setForeground(new Color(255,255,255));
                labelForProcessingPanel.setIcon(Icons.PROCESSING_TABS_CLICKED);
            } else {
                labelForProcessingPanel.setForeground(new Color(0,0,0));
                labelForProcessingPanel.setIcon(Icons.PROCESSING_TABS);
            }
            if(completedPanel.isEnabled()){
                labelForCompletedPanel.setForeground(new Color(255,255,255));
                labelForCompletedPanel.setIcon(Icons.COMPLETED_TABS_CLICKED);
                completedPanelSettings.setIcon(Icons.SETTINGS_TABS_CLICKED);
            } else {
                labelForCompletedPanel.setForeground(new Color(0,0,0));
                labelForCompletedPanel.setIcon(Icons.COMPLETED_TABS);
                completedPanelSettings.setIcon(Icons.SETTINGS_TABS);
            }
            if(queuesPanel.isEnabled()){
                labelForQueuesPanel.setForeground(new Color(255,255,255));
                labelForQueuesPanel.setIcon(Icons.QUEUES_TABS_CLICKED);
                queuesPanelSettings.setIcon(Icons.SETTINGS_TABS_CLICKED);
            } else {
                labelForQueuesPanel.setForeground(new Color(0,0,0));
                labelForQueuesPanel.setIcon(Icons.QUEUES_TABS);
                queuesPanelSettings.setIcon(Icons.SETTINGS_TABS);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getSource()==processingPanel){
                MainPanel.processingPanel.repaint();
            } else if (e.getSource()==completedPanel){
                MainPanel.completedPanel.repaint();
            } else if (e.getSource()==queuesPanel){
                MainPanel.queuesPanel.repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==processingPanel){
                if(!processingPanel.isEnabled())
                processingPanel.setBackground(new Color(130,130,130));
            } else if (e.getSource()==completedPanel){
                if(!completedPanel.isEnabled())
                completedPanel.setBackground(new Color(130,130,130));
            } else if (e.getSource()==queuesPanel){
                if(!queuesPanel.isEnabled())
                queuesPanel.setBackground(new Color(130,130,130));

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==processingPanel){
                if(!processingPanel.isEnabled())
                processingPanel.setBackground(new Color(255,214,23));
            } else if (e.getSource()==completedPanel){
                if(!completedPanel.isEnabled())
                completedPanel.setBackground(new Color(255,214,23));
            } else if (e.getSource()==queuesPanel){
                if(!queuesPanel.isEnabled())
                queuesPanel.setBackground(new Color(255,214,23));
            }
        }
    }

    /**
     * getter methods.
     */

    public JPanel getProcessingPanel() {
        return processingPanel;
    }

    public JPanel getCompletedPanel() {
        return completedPanel;
    }

    public JPanel getQueuesPanel() {
        return queuesPanel;
    }

    public QueueSettingsPanel getQueueSettingsPanel() {
        return queueSettingsPanel;
    }

}
