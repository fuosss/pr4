package pr3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable tableKlient, tableKniga, tableVydacha;
	private vydachaknigDAO vydachaDAO;
    
    public MainWindow() {
        super("Библиотека - Просмотр данных");
        
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        Database.testConnection();
        
        
        tabbedPane = new JTabbedPane();
         
        tableVydacha = new JTable();
         
        tabbedPane.addTab("Клиенты", createTablePanel(tableKlient));
        tabbedPane.addTab("Книги", createTablePanel(tableKniga));
        tabbedPane.addTab("Выдачи", createTablePanel(tableVydacha));
        
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Обновить данные");
        JButton exitButton = new JButton("Выход");
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllData();
                JOptionPane.showMessageDialog(null, "Данные обновлены!");
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(exitButton);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        loadAllData();
    }
    
    private JPanel createTablePanel(JTable table) {
        JPanel panel = new JPanel(new BorderLayout());
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadAllData() {
        loadKlienty();
        loadKnigi();
        loadVydachi();
    }
    
    private void loadKlienty() {
        try {
            klientDAO klientDAO = new klientDAO();
            List<klient> klienty = klientDAO.getAllKlient();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("ФИО");
            model.addColumn("Телефон");
            model.addColumn("Email");
            model.addColumn("Адрес");
            
            for (klient klient : klienty) {
                model.addRow(new Object[]{
                    klient.getIdKlienta(),
                    klient.getFio(),
                    klient.getNomerTelephona(),
                    klient.getEmail(),
                    klient.getAdresProzhivaniya()
                });
            }
            
            tableKlient.setModel(model);
        } catch (Exception e) {
            showError("Ошибка загрузки клиентов: " + e.getMessage());
        }
    }
    
    private void loadKnigi() {
        try {
            knigaDAO knigaDAO = new knigaDAO();
            List<kniga> knigi = knigaDAO.getAllKniga();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Название");
            model.addColumn("Автор");
            model.addColumn("Страниц");
            model.addColumn("Год издания");
            
            for (kniga kniga : knigi) {
                model.addRow(new Object[]{
                    kniga.getIdKnigi(),
                    kniga.getNazvanie(),
                    kniga.getAvtor(),
                    kniga.getKolvoStranic(),
                    kniga.getGodPublikacii()
                });
            }
            
            tableKniga.setModel(model);
        } catch (Exception e) {
            showError("Ошибка загрузки книг: " + e.getMessage());
        }
    }
    
    private void loadVydachi() {
        try {
        	vydachaknigDAO vydachaknig = new vydachaknigDAO();
            List<vydachaknig> vydachi = vydachaDAO.getAllVydacha();
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID выдачи");
            model.addColumn("ID клиента");
            model.addColumn("ID книги");
            model.addColumn("Дата выдачи");
            model.addColumn("Дата возврата");
            model.addColumn("Статус");
            
            for (vydachaknig vydacha : vydachi) {
                String status = vydacha.getDataVozvrata() == null ? "На руках" : "Возвращена";
                model.addRow(new Object[]{
                    vydacha.getIdVydachi(),
                    vydacha.getIdKlienta(),
                    vydacha.getIdKnigi(),
                    vydacha.getDataVydachi(),
                    vydacha.getDataVozvrata(),
                    status
                });
            }
            
            tableVydacha.setModel(model);
        } catch (Exception e) {
            showError("Ошибка загрузки выдач: " + e.getMessage());
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        try {
            Database.initDatabase();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Ошибка подключения к БД: " + e.getMessage() + 
                "\nПроверьте:\n1. Запущен ли MySQL\n2. Правильные ли настройки в Database.java", 
                "Ошибка БД", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
