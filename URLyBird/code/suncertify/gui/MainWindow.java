package suncertify.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import suncertify.gui.controller.ClientMainWindowController;
import suncertify.gui.controller.MainWindowController;
import suncertify.gui.controller.StandaloneMainWindowController;

public class MainWindow extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * MainWindowController bound to this window. There must always be a
     * controller set for a window.
     */
    private MainWindowController controller;
    private boolean standalone;
    private JTable mainTable;
    private JTextField hotelNameSearchWidget;
    private JTextField locationSearchWidget;
    private JLabel statusLabel;
    private JButton showAllButton;
    private JButton bookButton;
    private JButton clearButton;
    private JButton searchButton;
    private Menu editMenu;

    public MainWindow(boolean standalone) {
        this.standalone = standalone;
        createAndShow();
        if (standalone) {
            controller = new StandaloneMainWindowController(this);
        } else {
            controller = new ClientMainWindowController(this);
        }
    }

    public void createAndShow() {
        createGUI();
        // call confirmation dialog when user clicks 'X' button on the top
        // window bar.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.confirmAndClose();
            }
        });
        pack();
        setVisible(true);
    }

    private void createGUI() {
        setTitle("URLy Bird Booking Tool");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // setMinimumSize(new Dimension(500, 500));
        Container mainContentPane = getContentPane();
        mainContentPane.setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT);
        splitPane.add(createSearchPane(), JSplitPane.LEFT);
        splitPane.add(createTablePane(), JSplitPane.RIGHT);
        mainContentPane.add(splitPane, BorderLayout.CENTER);

        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory
                .createLoweredBevelBorder());
        statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel, BorderLayout.WEST);
        mainContentPane.add(statusBar, BorderLayout.SOUTH);

        setMenuBar(createMenu());
    }

    private Component createTablePane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory
                .createTitledBorder("Search Results"));
        mainTable = new JTable();
        mainTable.setFillsViewportHeight(true);
        JScrollPane tablePanel = new JScrollPane(mainTable);
        panel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,
                BoxLayout.LINE_AXIS));
        showAllButton = new JButton("Show All Records");
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showAllRecords();
            }
        });
        buttonPanel.add(showAllButton);
        buttonPanel.add(Box.createHorizontalGlue());
        bookButton = new JButton("Book Selected");
        buttonPanel.add(bookButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private Component createSearchPane() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory
                .createTitledBorder("Search Criteria"));
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        fieldsPanel.add(new JLabel("Hotel Name"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        hotelNameSearchWidget = new JTextField(10);
        fieldsPanel.add(hotelNameSearchWidget, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        fieldsPanel.add(new JLabel("City"), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        locationSearchWidget = new JTextField(10);
        fieldsPanel.add(locationSearchWidget, c);
        panel.add(fieldsPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,
                BoxLayout.LINE_AXIS));
        buttonsPanel.add(Box.createHorizontalGlue());
        clearButton = new JButton("Clear");
        buttonsPanel.add(clearButton);
        buttonsPanel.add(Box
                .createRigidArea(new Dimension(5, 0)));
        searchButton = new JButton("Search");
        buttonsPanel.add(searchButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        panel.setMinimumSize(new Dimension(170, 100));
        return panel;
    }

    private MenuBar createMenu() {
        MenuBar bar = new MenuBar();
        bar.add(createFileMenu());
        bar.add(createEditMenu());
        return bar;
    }

    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem connectItem = new MenuItem("Connect to server");
        connectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.displayConnectionDialog();
            }
        });
        fileMenu.add(connectItem);
        if (standalone) {
            connectItem.setEnabled(false);
        }
        MenuItem closeItem = new MenuItem("Close");
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.confirmAndClose();
            }
        });
        fileMenu.add(closeItem);
        return fileMenu;
    }

    private Menu createEditMenu() {
        editMenu = new Menu("Edit");
        MenuItem copyItem = new MenuItem("Copy");
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editMenu.add(copyItem);
        return editMenu;
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public void setConnected(boolean enabled) {
        mainTable.setEnabled(enabled);
        locationSearchWidget.setEnabled(enabled);
        hotelNameSearchWidget.setEnabled(enabled);
        clearButton.setEnabled(enabled);
        searchButton.setEnabled(enabled);
        showAllButton.setEnabled(enabled);
        bookButton.setEnabled(enabled);
        editMenu.setEnabled(enabled);
    }

    public JTable getMainTable() {
        return mainTable;
    }
}
