package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AddIconGUI {
    private JFrame jFrame;
    private JButton addButton;
    private JButton closeButton;
    private JLabel iconNameLabel;
    private JLabel meanLabel;
    private JTextField iconNameTextField;
    private JTextField meanTextField;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel mainPanel;

    public AddIconGUI(Consumer<List<String>> consumer) {
        jFrame = new JFrame("Add Icon");
        jFrame.setBounds(700, 350, 300, 170);
        jFrame.setVisible(true);

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 25));
        addButton.setIcon(new ImageIcon(getClass().getResource("/Images/add.png")));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (meanTextField.getText().length() == 0 && iconNameTextField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please Input All The Field");
                } else {
                    List<String> args = new ArrayList<>();
                    args.add(iconNameTextField.getText());
                    args.add(meanTextField.getText());
                    consumer.accept(args);
                    jFrame.setVisible(false);
                    jFrame.dispose();
                }
            }
        });

        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(100, 25));
        closeButton.setIcon(new ImageIcon(getClass().getResource("/Images/delete.png")));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                jFrame.dispose();
            }
        });

        iconNameLabel = new JLabel("Icon: ");
        iconNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconNameLabel.setPreferredSize(new Dimension(50, 25));

        meanLabel = new JLabel("Meaning: ");
        meanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        meanLabel.setPreferredSize(new Dimension(50, 25));

        iconNameTextField = new JTextField();
        iconNameTextField.setPreferredSize(new Dimension(200, 25));
        meanTextField = new JTextField();
        meanTextField.setPreferredSize(new Dimension(200, 25));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        bottomPanel.add(addButton);
        bottomPanel.add(closeButton);

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1, 25, 5));
        leftPanel.add(iconNameLabel);
        leftPanel.add(meanLabel);

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 25, 5));
        rightPanel.add(iconNameTextField);
        rightPanel.add(meanTextField);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));
        centerPanel.add(leftPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        centerPanel.add(rightPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(25, 25)));
        mainPanel.add(centerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(25, 10)));
        mainPanel.add(bottomPanel);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        jFrame.getContentPane().add(mainPanel);
    }
}
