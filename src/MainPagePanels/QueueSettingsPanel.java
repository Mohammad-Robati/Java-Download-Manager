package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

class QueueSettingsPanel extends JFrame {

    /**
     * All works around making a queue and scheduling downloads
     * the panel will be shown when the queue setting button in the tabs
     * panel gets clicked!
     */
    private JTextField queueName = new JTextField();
    private JCheckBox scheduleOrNot = new JCheckBox("Schedule");
    private JRadioButton startAtRadioButton = new JRadioButton("Start At");
    private JRadioButton timerRadioButton = new JRadioButton("Timer");
    private JSpinner startAtTimeSpinner;
    private JSpinner timerSpinner;
    private JButton okButton = new JButton("Add Queue / Ok");
    private JButton cancelButton = new JButton("Cancel");

    QueueSettingsPanel() {

        queueName.setMaximumSize(new Dimension(150,10));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(startAtRadioButton);
        buttonGroup.add(timerRadioButton);

        Listener listener = new Listener();
        startAtRadioButton.addActionListener(listener);
        timerRadioButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
        okButton.addActionListener(listener);
        scheduleOrNot.addActionListener(listener);

        timerRadioButton.setBackground(new Color(243,233,167));
        startAtRadioButton.setBackground(new Color(243,233,167));
        scheduleOrNot.setBackground(new Color(243,233,167));

        Date date1 = new Date();
        Date date2 = new Date(0,0,0,0,0,0);
        SpinnerDateModel sm1 = new SpinnerDateModel(date1, null, null,0);
        SpinnerDateModel sm2 = new SpinnerDateModel(date2, null, null,0);
        startAtTimeSpinner = new JSpinner(sm1);
        timerSpinner = new JSpinner(sm2);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(startAtTimeSpinner, "yyyy/MM/dd HH:mm:ss");
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(timerSpinner, "hh:mm:ss aa");
        startAtTimeSpinner.setEditor(de1);
        timerSpinner.setEditor(de2);

        startAtRadioButton.setEnabled(false);
        startAtTimeSpinner.setEnabled(false);
        timerRadioButton.setEnabled(false);
        timerSpinner.setEnabled(false);
        timerSpinner.setMaximumSize(new Dimension(200,10));
        startAtTimeSpinner.setMaximumSize(new Dimension(200,10));

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        JLabel queueNameLabel = new JLabel("Queue Name: ");
        JSeparator separator = new JSeparator();
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout.createSequentialGroup()
                                .addComponent(queueNameLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(queueName)
                        )
                        .addComponent(separator)
                        .addComponent(scheduleOrNot)
                        .addComponent(startAtRadioButton)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addGap(0,0,Short.MAX_VALUE)
                                        .addComponent(startAtTimeSpinner)
                        )
                        .addComponent(timerRadioButton)
                        .addGroup(
                                layout.createSequentialGroup()
                                .addGap(0,0,Short.MAX_VALUE)
                                        .addComponent(timerSpinner)
                        )
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addGap(0,0,Short.MAX_VALUE)
                                        .addComponent(cancelButton)
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
                                        .addComponent(queueNameLabel)
                                        .addComponent(queueName)
                        )
                        .addGap(15)
                        .addComponent(separator)
                        .addComponent(scheduleOrNot)
                        .addGap(10)
                .addComponent(startAtRadioButton)
                .addComponent(startAtTimeSpinner)
                .addComponent(timerRadioButton)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(0,0,Short.MAX_VALUE)
                                        .addComponent(timerSpinner)
                        )
                        .addGap(10)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(cancelButton)
                                        .addComponent(okButton)
                        )

                .addContainerGap()
        );
        panel.setBackground(new Color(243,233,167));
        add(panel);
        setSize(350,250);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("" +
                "src/Icons/icon.png"));
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==scheduleOrNot){
                if(scheduleOrNot.isSelected()) {
                    startAtRadioButton.setEnabled(true);
                    timerRadioButton.setEnabled(true);
                    timerRadioButton.setSelected(true);
                    timerSpinner.setEnabled(true);
                } else {
                    startAtRadioButton.setEnabled(false);
                    startAtTimeSpinner.setEnabled(false);
                    timerRadioButton.setEnabled(false);
                    timerSpinner.setEnabled(false);
                }
            }
            if(e.getSource()==startAtRadioButton || e.getSource()==timerRadioButton){
                if(startAtRadioButton.isSelected()){
                    timerSpinner.setEnabled(false);
                    startAtTimeSpinner.setEnabled(true);
                } else {
                    timerSpinner.setEnabled(true);
                    startAtTimeSpinner.setEnabled(false);
                }
            } else if (e.getSource()==cancelButton) {
                QueueSettingsPanel.this.setVisible(false);
            } else if (e.getSource()==okButton) {
                if(scheduleOrNot.isSelected()){
                    if(startAtRadioButton.isSelected()){
                        MainPanel.queuesPanel.insertDownload(new QueueItem(queueName.getText(),((JSpinner.DefaultEditor)startAtTimeSpinner.getEditor()).getTextField().getText(),
                                "null"));
                    } else if (timerRadioButton.isSelected()) {
                        MainPanel.queuesPanel.insertDownload(new QueueItem(queueName.getText(),"null",
                                ((JSpinner.DefaultEditor)timerSpinner.getEditor()).getTextField().getText()));
                    }
                } else {
                    QueueItem queueItem = new QueueItem(queueName.getText(),"null","null");
                    queueItem.getStartButton().setVisible(true);
                    MainPanel.queuesPanel.insertDownload(queueItem);
                }
                QueueSettingsPanel.this.setVisible(false);
                MainFrame.frame.validate();
            }
        }
    }
}
