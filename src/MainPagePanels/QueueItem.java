package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

class QueueItem extends ItemPanel {

    /**
     * subclass of ItemPanel and used for inserting
     * queue panel
     */

    private JLabel dateLabel;
    private JLabel statusLabel;
    private String startAt;
    private String timer;
    private QueueBase queueBase;
    private JButton startButton;

    private static int flag=0;

    QueueItem(String name, String startAt, String timer) {
        super(new JLabel(Icons.QUEUE), new JLabel(Icons.REMOVE_ITEM), new JLabel(Icons.REMOVE_ITEM));
        setName_(name);
        dateLabel = new JLabel(getDate());
        statusLabel = new JLabel();
        startButton = new JButton("Start");
        startButton.setBackground(new Color(239,178,63));
        startButton.setVisible(false);

        this.startAt=startAt;
        this.timer=timer;

        if(timer.equals("null") && !startAt.equals("null")) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            if(simpleDateFormat.format(date).compareTo(startAt)>0){
                statusLabel.setText("Status: Expired");
            } else {
                statusLabel.setText("");
            }
        }

        if(startAt.equals("null") && !timer.equals("null")) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            if(simpleDateFormat.format(date).compareTo(RunQueue.getProperTimer(timer,getDate()))>0){
                statusLabel.setText("Status: Expired");
            } else {
                statusLabel.setText("");
            }
        }

        queueBase = new QueueBase();

        getNameLabel().setText(name);
        getRemoveFileButton().addMouseListener(new MouseListenerForProcessItem());

        startButton.addActionListener(e -> setOnline(true));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(getImageLabel())
                        .addGap(15)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getNameLabel())
                                        .addComponent(dateLabel)
                                        .addComponent(statusLabel)

                        )
                        .addGap(20,40,Short.MAX_VALUE)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(getRemoveFileButton())
                                        .addComponent(startButton)
                        )
                        .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getImageLabel())
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGap(5)
                                                        .addComponent(getNameLabel())
                                                        .addComponent(dateLabel)
                                                        .addComponent(statusLabel)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)

                                        )
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGap(5)
                                                        .addComponent(getRemoveFileButton())
                                                        .addGap(10)
                                                        .addComponent(startButton)
                                        )
                        )

                        .addGap(20)
        );
    }
    private class MouseListenerForProcessItem implements java.awt.event.MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource()==getRemoveFileButton()) {
                QueueItem.this.setVisible(false);
                MainPanel.queuesPanel.getPanels().remove(QueueItem.this);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource()==getRemoveFileButton()) {
                getRemoveFileButton().setIcon(Icons.REMOVE_ITEM_CLICKED);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource()==getRemoveFileButton()) {
                getRemoveFileButton().setIcon(Icons.REMOVE_ITEM);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public String getStartAt() {
        return startAt;
    }

    public String getTimer() {
        return timer;
    }


    public void setOnline(boolean b) {
        if(b) {
            if (flag == 0) {
                new RunQueue(this);
                flag = 1;
            }
        } else {
            flag=0;
        }
    }

    public QueueBase getQueueBase() {
        return queueBase;
    }


    public JButton getStartButton() {
        return startButton;
    }

    public JLabel getDateLabel() { return dateLabel; }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}

