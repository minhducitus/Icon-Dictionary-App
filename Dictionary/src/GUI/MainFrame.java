package GUI;

import Business.WorldHandler;
import DTO.Dictionary;
import Data.TextDataHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

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
    private JMenuItem searchSwitch;
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
    private JScrollPane iconListScrollPane;
    private JList<String> iconList;
    private DefaultListModel<String> listIcon;
    private Dictionary dictionary;
    private List<String> list;
    private Set<String> historySet;
    private boolean searchByContent = false;

    public MainFrame() throws IOException {
        historySet = new HashSet<>();
        initComponents();
        dictionary = new Dictionary();
        listIcon = new DefaultListModel<>();

        dictionary = TextDataHelper.loadResource(listIcon);
        listIcon = WorldHandler.getModel(dictionary);
        WorldHandler.loadWordsToList(listIcon, iconList);

        list = new ArrayList<>();
        for (int i = 0; i < listIcon.getSize(); i++) {
            try {
                list.add(listIcon.getElementAt(i));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void initComponents() {
        jFrame = new JFrame("Dictionary");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(300, 200, 1100, 550);
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
        historyItem.addActionListener(this::historyButtonActionPerformed);

        saveAllItem = new JMenuItem("Save all");
        saveAllItem.setPreferredSize(new Dimension(120,20));
        saveAllItem.setIcon(new ImageIcon(getClass().getResource("/Images/save.png")));

        addIconItem = new JMenuItem("Add Icon");
        addIconItem.setPreferredSize(new Dimension(120, 20));
        addIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/add.png")));
        addIconItem.addActionListener(this::addButtonActionPerformed);

        removeIconItem = new JMenuItem("Remove Icon");
        removeIconItem.setPreferredSize(new Dimension(120, 20));
        removeIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/delete.png")));
        removeIconItem.addActionListener(this::removeButtonActionPerformed);

        renameIconItem = new JMenuItem("Rename Icon");
        renameIconItem.setPreferredSize(new Dimension(120, 20));
        renameIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/document.png")));
        renameIconItem.addActionListener(this::renameButtonActionPerformed);

        exportIconItem = new JMenuItem("Export Icon");
        exportIconItem.setPreferredSize(new Dimension(120, 20));
        exportIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/share.png")));
        exportIconItem.addActionListener(this::exportButtonActionPerformed);

        editIconItem = new JMenuItem("Edit Icon");
        editIconItem.setPreferredSize(new Dimension(120, 20));
        editIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/edit.png")));
        editIconItem.addActionListener(this::editButtonActionPerformed);

        aboutIconItem = new JMenuItem("About");
        aboutIconItem.setPreferredSize(new Dimension(120, 20));
        aboutIconItem.setIcon(new ImageIcon(getClass().getResource("/Images/question.png")));
        aboutIconItem.addActionListener(this::aboutButtonActionPerformed);

        searchSwitch = new JMenuItem("Search By Content");
        searchSwitch.setPreferredSize(new Dimension(170, 20));
        searchSwitch.setIcon(new ImageIcon(getClass().getResource("/Images/search.png")));
        searchSwitch.addActionListener(new ActionListener() {
            int clicked = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked++;
                if (clicked != 0 && clicked % 2 == 0) {
                    searchSwitch.setText("Search By Contents");
                    searchByContent = false;
                } else {
                    searchSwitch.setText("Search By Icon's Name");
                    searchByContent = true;
                }
            }
        });

        fileMenu.add(historyItem);
        fileMenu.add(saveAllItem);
        fileMenu.add(exitItem);

        editMenu.add(addIconItem);
        editMenu.add(removeIconItem);
        editMenu.add(renameIconItem);
        editMenu.add(editIconItem);
        editMenu.add(exportIconItem);

        helpMenu.add(aboutIconItem);

        findMenu.add(searchSwitch);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(findMenu);
        menuBar.add(helpMenu);

        toolBar = new JToolBar();
        findButton = new JButton(new ImageIcon(getClass().getResource("/Images/search (1).png")));
        findButton.setPreferredSize(new Dimension(40, 40));
        findButton.setToolTipText("Find Icon");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchByContent == true) {
                    findButton.setToolTipText("Find Icon By Meaning");
                    String meaning = JOptionPane.showInputDialog(null, "Please Input Meaning To Find");
                    for (int i = 0; i < list.size(); i++) {
                        if (meaning.equals(dictionary.getValue(list.get(i)))) {
                            iconList.setSelectedIndex(i);
                            iconList.ensureIndexIsVisible(iconList.getSelectedIndex());
                        }
                    }
                } else {
                    String icon = JOptionPane.showInputDialog(null, "Please Input Icon Name To Find");
                    for (int i = 0; i < list.size(); i++) {
                        if (icon.equals(list.get(i))) {
                            iconList.setSelectedIndex(i);
                            iconList.ensureIndexIsVisible(iconList.getSelectedIndex());
                        }
                    }
                }
            }
        });

        addIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/add (1).png")));
        addIconButton.setPreferredSize(new Dimension(40, 40));
        addIconButton.setToolTipText("Add Icon");
        addIconButton.addActionListener(this::addButtonActionPerformed);

        removeIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/delete (1).png")));
        removeIconButton.setPreferredSize(new Dimension(40, 40));
        removeIconButton.setToolTipText("Remove Icon");
        removeIconButton.addActionListener(this::removeButtonActionPerformed);

        editIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/edit (1).png")));
        editIconButton.setPreferredSize(new Dimension(40, 40));
        editIconButton.setToolTipText("Edit Icon");
        editIconButton.addActionListener(this::editButtonActionPerformed);

        renameIconButton = new JButton(new ImageIcon(getClass().getResource("/Images/document (1).png")));
        renameIconButton.setPreferredSize(new Dimension(40, 40));
        renameIconButton.setToolTipText("Rename Icon");
        renameIconButton.addActionListener(this::renameButtonActionPerformed);

        historyButton = new JButton(new ImageIcon(getClass().getResource("/Images/history (1).png")));
        historyButton.setPreferredSize(new Dimension(40, 40));
        historyButton.setToolTipText("History");
        historyButton.addActionListener(this::historyButtonActionPerformed);

        exportButton = new JButton(new ImageIcon(getClass().getResource("/Images/share (1).png")));
        exportButton.setPreferredSize(new Dimension(40, 40));
        exportButton.setToolTipText("Export Icon");
        exportButton.addActionListener(this::exportButtonActionPerformed);

        aboutButton = new JButton(new ImageIcon(getClass().getResource("/Images/question (1).png")));
        aboutButton.setPreferredSize(new Dimension(40, 40));
        aboutButton.setToolTipText("About");
        aboutButton.addActionListener(this::aboutButtonActionPerformed);

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

        iconListScrollPane = new JScrollPane(iconList);
        iconList = new JList<>();
        iconList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        iconListScrollPane.setViewportView(iconList);
        leftPanel.add(iconListScrollPane, BorderLayout.CENTER);

        iconList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String icon = "";
                int index = iconList.getSelectedIndex();
                if (index != -1) {
                    historySet.add(iconList.getSelectedValue());
                    icon = iconList.getModel().getElementAt(index);
                }
                meaningTextArea.setText(dictionary.getValue(icon));
            }
        });

        findingTextField = new JTextField();
        findingTextField.setPreferredSize(new Dimension(45, 35));
        findingTextField.setMaximumSize(new Dimension(45, 35));
        findingTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                iconList.clearSelection();
                filter((DefaultListModel) iconList.getModel());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                iconList.clearSelection();
                filter((DefaultListModel) iconList.getModel());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            private void filter(DefaultListModel model) {
                if (searchByContent == false) {
                    model =( DefaultListModel) createDefaultModel();
                    iconList.setModel(model);
                    String filter = findingTextField.getText();
                    if (filter.length() != 0) {
                        for (String s : list) {
                            if (!s.startsWith(filter)) {
                                if (model.contains(s)) {
                                    model.removeElement(s);
                                }
                            } else {
                                if (!model.contains(s)) {
                                    model.addElement(s);
                                }
                            }
                        }
                    }
                    iconList.setModel(model);
                } else if (searchByContent == true) {
                    String needle = findingTextField.getText();
                    for (int i = 0; i < list.size(); i++) {
                       if (needle.equals(dictionary.getValue(list.get(i)))) {
                            iconList.setSelectedIndex(i);
                            iconList.ensureIndexIsVisible(iconList.getSelectedIndex());
                       }
                   }
                }
            }
        });
        leftPanel.add(findingTextField, BorderLayout.PAGE_START);


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

    public void aboutButtonActionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Dictionary Ver 1.0 \nCopyright(C) 2019 \nCao Minh Duc","About Dictionary" ,JOptionPane.INFORMATION_MESSAGE);
    }

    public void removeButtonActionPerformed(ActionEvent event) {
        int index = iconList.getSelectedIndex();
        if (index != -1) {
            String needle = iconList.getSelectedValue();
            dictionary.delete(needle);
            list.remove(needle);
            DefaultListModel<String> newList = (DefaultListModel<String>) iconList.getModel();
            newList.remove(index);
            iconList.setModel(newList);
            JOptionPane.showMessageDialog(null, "Icon " + needle + " is deleted!");
        } else {
            JOptionPane.showMessageDialog(null, "Select Icon To Deleted");
        }
    }

    public void editButtonActionPerformed(ActionEvent event) {
        try {
            String needle = iconList.getSelectedValue();
            String meaning = dictionary.getValue(needle);
            List<String> dummyList = new ArrayList<>();
            Consumer<List<String>> consumer = c -> {
                String newMeaning  = c.get(0);
                dictionary.replace(needle, newMeaning);
                listIcon = WorldHandler.getModel(dictionary);
                WorldHandler.loadWordsToList(listIcon, iconList);
            };
            EditIconGUI edit = new EditIconGUI(consumer, needle, meaning);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Please Select Icon To Edit");
        }
    }

    public void renameButtonActionPerformed(ActionEvent event) {
        int index = iconList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please Select An Icon To Rename");
        } else {
            String iconName = JOptionPane.showInputDialog("Icon Old Name Is: " + iconList.getSelectedValue() + ", Please Input New Icon Name!");
            String meaning = dictionary.getValue(iconList.getSelectedValue());
            dictionary.delete(iconList.getSelectedValue());
            list.remove(iconList.getSelectedValue());
            list.add(iconName);
            dictionary.add(iconName, meaning);
            listIcon = WorldHandler.getModel(dictionary);
            WorldHandler.loadWordsToList(listIcon, iconList);
        }
    }

    public void addButtonActionPerformed(ActionEvent event) {
        Consumer<List<String>> consumer = s ->
        {
            list.add(s.get(0));
            dictionary.add(s.get(0), s.get(1));
            listIcon = WorldHandler.getModel(dictionary);
            WorldHandler.loadWordsToList(listIcon, iconList);
            iconList.updateUI();
        };
        AddIconGUI addIconGUI = new AddIconGUI(consumer);
    }

    int clicked = 0;
    public void historyButtonActionPerformed(ActionEvent event) {
        clicked++;
        if (clicked % 2 == 0 && clicked != 0) {
            DefaultListModel<String> model = (DefaultListModel<String>) createDefaultModel();
            iconList.setModel(model);
        } else {
            DefaultListModel<String> historyModel = new DefaultListModel<>();
            for (String icon : historySet) {
                historyModel.addElement(icon);
            }
            iconList.setModel(historyModel);
        }
    }

    public void exportButtonActionPerformed(ActionEvent event) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedIcon.txt", true));
            String iconName = iconList.getSelectedValue();
            String meaning = dictionary.getValue(iconName);
            String content = iconName + " " + meaning;
            writer.write(content);
            writer.write("\n");
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Select Icon To Export");
        }
    }

    public ListModel<String> createDefaultModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < list.size(); i++) {
            model.addElement(list.get(i));
        }
        return model;
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
                MainFrame windows = null;
                try {
                    windows = new MainFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                windows.jFrame.setVisible(true);
            }
        });
    }
}
