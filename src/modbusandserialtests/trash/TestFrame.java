package modbusandserialtests.trash;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
 
public class TestFrame extends JFrame {
 
    private int i = 0;
 
    public TestFrame() {
        super("Тестовое окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
 
        final DefaultListModel listModel = new DefaultListModel();
 
        for (i = 0; i < 25; i++) {
            listModel.addElement("Элемент списка " + i);
        }
 
        final JList list = new JList(listModel);
        list.setSelectedIndex(0);
        list.setFocusable(false);
        mainPanel.add(new JScrollPane(list), BorderLayout.CENTER);
 
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 0));
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
 
        JButton addButton = new JButton("Добавить");
        addButton.setFocusable(false);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String element = "Элемент списка " + i++;
                listModel.addElement(element);
                int index = listModel.size() - 1;
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        });
        buttonsPanel.add(addButton);
 
        final JButton removeButton = new JButton("Удалить");
        removeButton.setFocusable(false);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.remove(list.getSelectedIndex());
            }
        });
        buttonsPanel.add(removeButton);
 
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() >= 0) {
                    removeButton.setEnabled(true);
                } else {
                    removeButton.setEnabled(false);
                }
            }
        });
 
        getContentPane().add(mainPanel);
 
        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new TestFrame();
            }
        });
    }
}