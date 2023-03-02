/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadsire;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.SecondaryLoop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jacop
 */
public class ThreadGame extends Thread implements ActionListener{
    private int threadNum;
    private long start;
    public int[][] v;
    private JFrame f;
    private JButton btnEsci;
    private JButton btnFine;
    private JTextField txtRisultato;
    private Timer timer;
    private int seconds;
    private JLabel textTimer;
    private static final Random r = new Random();
    private Utente user;
    
    private JPanel pnlCenter;


    public ThreadGame(int threadNum){
        this.threadNum = threadNum;
        timer = new Timer(1000, this);
        textTimer = new JLabel("0:00");
        v = new int [10][3];
    }
    
    @Override
    public void run(){
        for (int i = 0; i < v.length; i++) {
            v[i][0] = r.nextInt(1, 12);
            v[i][1] = r.nextInt(1, 12);
            v[i][2] = v[i][0] * v[i][1];
        }
        
        btnEsci = new JButton("Esci");
        btnFine = new JButton("Fine");
        txtRisultato = new JTextField();
        f = new JFrame("Finestra thread" + threadNum);
        f.setSize(300, 300);
        f.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int horizontalFrame = screenSize.width/ 300;
        int verticalFrame = screenSize.height / 300;
        int x = 300*((threadNum > horizontalFrame?threadNum%horizontalFrame: threadNum)-1);
        int y = 300*((threadNum > horizontalFrame?threadNum/horizontalFrame: 0));
        f.setLocation(x, y);
        showTitle();
        showUserInit();
        /*
        try {
            createFrame();
        } catch (FontFormatException ex) {
        } catch (IOException ex) {
        }*/
        associaAscoltatori();
        f.setVisible(true);
        System.out.println("fine");
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnFine){
            salvaDati();
        }else if(e.getSource() == btnEsci){
            gestisciEsci();
        }else if(e.getSource() == timer){
            seconds++;
            int minutes = seconds / 60;
            int remainingSeconds = seconds % 60;
            textTimer.setText(minutes + ":" + (remainingSeconds < 10 ? "0" : "") + remainingSeconds);
        }else if(e.getSource() == txtRisultato){
            gestisciRisultato();
        }
    }

    private void associaAscoltatori() {
        btnEsci.addActionListener(this);
        btnFine.addActionListener(this);
        txtRisultato.addActionListener(this);
    }

    public void showTitle(){
        JPanel pnlNord = new JPanel();
        JLabel lblTitolo = new JLabel("TABELLINE THREAD " + threadNum);
        lblTitolo.setHorizontalAlignment(JLabel.CENTER);
        lblTitolo.setFont(
                new Font(Font.SANS_SERIF,
                        Font.BOLD, 18));
        lblTitolo.setForeground(Color.WHITE);
        pnlNord.setBackground(Color.BLACK);
        pnlNord.add(lblTitolo);
        f.add(pnlNord, BorderLayout.NORTH);
        f.setVisible(true);
    }
    
    public void showUserInit(){
        
        
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JButton btnConferma = new JButton("conferma");
        btnConferma.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        pnlCenter = new JPanel();
        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
        
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel pnlField = new JPanel(new GridBagLayout());
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx = 1.0;
        pnlField.add(lblName, gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx = 2.0;
        pnlField.add(txtName, gbc);
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        pnlCenter.add(pnlField);
        pnlCenter.add(btnConferma);
        
        
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().compareTo("") != 0) {
                    user = new Utente(txtName.getText());
                    try {
                        createFrame();
                    } catch (FontFormatException ex) {
                        Logger.getLogger(ThreadGame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
        });
        
        
        f.add(pnlCenter, BorderLayout.CENTER);
        f.setVisible(true);
    }
    
    private void createFrame() throws FontFormatException, IOException {
        
        timer.start();
        start = Instant.now().toEpochMilli();
        
        f.remove(pnlCenter);
        pnlCenter = new JPanel(new GridBagLayout());
        String[] columnNames = {""};
        Object[][] data = new Object[10][1];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = v[i][0]+" * " + v[i][1];
        }
        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Centra il contenuto della tabella
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Imposta la larghezza della colonna 0
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        
        
        
        textTimer.setHorizontalAlignment(JLabel.CENTER);
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("DS-DIGIT.TTF")).deriveFont(40f);
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.registerFont(customFont);
       /* textTimer.setFont(
                new Font(Font.SANS_SERIF,
                        Font.BOLD, 18));*/
        textTimer.setFont(customFont);
        JPanel firstRow = new JPanel(new GridLayout(1,2));
        firstRow.add(scrollPane);
        firstRow.add(textTimer);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        pnlCenter.add(firstRow, gbc);
        
        
        JPanel secondRow = new JPanel(new GridLayout(1,1));
        secondRow.add(txtRisultato);
        
        pnlCenter.add(secondRow);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.1;
        pnlCenter.add(secondRow, gbc);
        f.add(pnlCenter, BorderLayout.CENTER);
        
        JPanel pnlSud = new JPanel(new GridLayout(1,2));
        
        pnlSud.add(btnEsci);
        pnlSud.add(btnFine);
        f.add(pnlSud, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    private void salvaDati() {

    }

    private void gestisciEsci() {
        f.dispose();
    }

    private void gestisciRisultato() {
        boolean isCorretto;
        for (int i = 0; i < v.length; i++) {
            
        }
    }
    
        
}
