/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadsire;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author jacop
 */
public class GameFrame extends JFrame implements ActionListener{
    private static int numFrame = 0;
    private JButton btnEsci;
    private JButton btnFine;
    private int[][] mat;
    public GameFrame(String titolo, int[][] mat) {
        super(titolo);
        numFrame++;
        this.mat = mat;
        setSize(300, 300);
        setResizable(false);
        createFrame();
        associaAscoltatori();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnFine){
            salvaDati();
        }else if(e.getSource() == btnEsci){
            gestisciEsci();
        }
    }

    private void associaAscoltatori() {
        btnEsci.addActionListener(this);
        btnFine.addActionListener(this);
    }

    private void createFrame() {
        JPanel pnlNord = new JPanel();
        
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        
        
        JPanel pnlSud = new JPanel(new GridLayout(1,2));
        btnEsci = new JButton("Esci");
        btnFine = new JButton("Fine");
        pnlSud.add(btnEsci);
        pnlSud.add(btnFine);
        add(pnlSud, BorderLayout.SOUTH);
    }

    private void salvaDati() {

    }

    private void gestisciEsci() {
        dispose();
    }
    
}
