package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame {
    private JFrame jFrame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu findMenu;
    private JMenu helpMenu;
    private JMenuItem exitItem;
    private JMenuItem historyItem;
    private JMenuItem saveAllItem;
    private JMenuItem addIconItem;
    private JMenuItem removeIconItem;
    private JMenuItem renameIconItem;
    private JMenuItem editIconItem;
    private JMenuItem exportIconItem;
    private JMenuItem aboutIconItem;
    private JToolBar toolBar;
    private JButton findButton;
    private JButton addIconButton;
    private JButton removeIconButton;
    private JButton editIconButton;
    private JButton renameIconButton;
    private JButton historyButton;
    private JButton exportButton;
    private JButton aboutButton;
    private JPanel centerPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextArea meaningTextArea;
    private JTextField findingTextField;
    private JList<String> iconList;

    public MainFrame() {
        initComponents();
    }

    public void initComponents() {
        jFrame = new JFrame("Dictionary");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(100, 100, 1200, 650);
        jFrame.setResizable(false);
        jFrame.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        findMenu = new JMenu("Find");
        helpMenu = new JMenu("Help");

        exitItem = new JMenuItem("Exit");
        exitItem.setPreferredSize(new Dimension(80,20));
        exitItem.setIcon(new ImageIcon(getClass().getResource("/Images/logout.png")));
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        historyItem = new JMenuItem("History");
        historyItem.setPreferredSize(new Dimension(80,20));
        historyItem.setIcon(new ImageIcon(getClass().getResource("/Images/history.png")));

        saveAllItem = new JMenuItem("Save all");
        saveAllItem.setPreferredSize(new Dimension(120,20));
        saveAllItem.setIcon(new ImageIcon(getClass().getResource("/Images/save.png")));

        addIconItem = new JMenuItem("Add Icon");
        addIconItem.setPreferredSize(new Dimension(120, 20));
        addIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/add.png")));

        removeIconItem = new JMenuItem("Remove Icon");
        removeIconItem.setPreferredSize(new Dimension(120, 20));
        removeIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/delete.png")));

        renameIconItem = new JMenuItem("Rename Icon");
        renameIconItem.setPreferredSize(new Dimension(120, 20));
        renameIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/document.png")));

        exportIconItem = new JMenuItem("Export Icon");
        exportIconItem.setPreferredSize(new Dimension(120, 20));
        exportIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/share.png")));

        aboutIconItem = new JMenuItem("About");
        aboutIconItem.setPreferredSize(new Dimension(120, 20));
        aboutIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
        aboutIconItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dictionary Ver 1.0 \nCopyright(C) 2019 \nCao Minh Duc","About Dictionary" ,JOptionPane.INFORMATION_MESSAGE);
            }
        });

        fileMenu.add(historyItem);
        fileMenu.add(saveAllItem);
        fileMenu.add(exitItem);

        editMenu.add(addIconItem);
        editMenu.add(removeIconItem);
        editMenu.add(renameIconItem);
        editMenu.add(exportIconItem);

        helpMenu.add(aboutIconItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(findMenu);
        menuBar.add(helpMenu);

        toolBar = new JToolBar();
        findButton = new JButton(new ImageIcon(getClass().getResource("/Images/search (1).png")));
        findButton.setPreferredSize(new Dimension(40, 40));
        findButton.setToolTipText("Find Icon");

        addIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/add (1).png")));
        addIconButton.setPreferredSize(new Dimension(40, 40));
        addIconButton.setToolTipText("Add Icon");

        removeIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/delete (1).png")));
        removeIconButton.setPreferredSize(new Dimension(40, 40));
        removeIconButton.setToolTipText("Remove Icon");

        editIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/edit (1).png")));
        editIconButton.setPreferredSize(new Dimension(40, 40));
        editIconButton.setToolTipText("Edit Icon");

        renameIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/document (1).png")));
        renameIconButton.setPreferredSize(new Dimension(40, 40));
        renameIconButton.setToolTipText("Rename Icon");

        historyButton = new JButton(new ImageIcon(getClass().getResource("/Images/history (1).png")));
        historyButton.setPreferredSize(new Dimension(40, 40));
        historyButton.setToolTipText("History");

        exportButton = new JButton(new ImageIcon(getClass().getResource("/Images/share (1).png")));
        exportButton.setPreferredSize(new Dimension(40, 40));
        exportButton.setToolTipText("Export Icon");

        aboutButton = new JButton(new ImageIcon(getClass().getResource("/Images/question (1).png")));
        aboutButton.setPreferredSize(new Dimension(40, 40));
        aboutButton.setToolTipText("About");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dictionary Ver 1.0 \nCopyright(C) 2019 \nCao Minh Duc","About Dictionary" ,JOptionPane.INFORMATION_MESSAGE);
            }
        });

        toolBar.add(findButton);
        toolBar.add(addIconButton);
        toolBar.add(removeIconButton);
        toolBar.add(editIconButton);
        toolBar.add(renameIconButton);
        toolBar.add(historyButton);
        toolBar.add(exportButton);
        toolBar.add(aboutButton);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));

        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(0, 5));

        findingTextField = new JTextField();
        findingTextField.setPreferredSize(new Dimension(45, 35));
        findingTextField.setMaximumSize(new Dimension(45, 35));
        leftPanel.add(findingTextField, BorderLayout.PAGE_START);

        iconList = new JList<>();
        iconList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        iconList.setPreferredSize(new Dimension(100, 100));
        leftPanel.add(iconList, BorderLayout.CENTER);

        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(1,1, 25, 0));

        meaningTextArea = new JTextArea();
        meaningTextArea.setPreferredSize(new Dimension(800, 500));

        rightPanel.add(meaningTextArea);

        centerPanel.add(leftPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(15, 5)));
        centerPanel.add(rightPanel);

        jFrame.setJMenuBar(menuBar);
        jFrame.getContentPane().add(toolBar, BorderLayout.PAGE_START);
        jFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

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

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame windows = new MainFrame();
                windows.jFrame.setVisible(true);
            }
        });
    }
}
