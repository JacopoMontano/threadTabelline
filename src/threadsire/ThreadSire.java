/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package threadsire;

import java.util.Scanner;

/**
 *
 * @author jacop
 */
public class ThreadSire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*int numThread;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("inserisci il numero di player:");
            numThread = scanner.nextInt();
        }while(numThread <= 0);
        ThreadGame[] thread = new ThreadGame[numThread];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new ThreadGame(i+1);
            thread[i].start();
            
        }*/
        ThreadGame game = new ThreadGame(1);
        ThreadGame game2 = new ThreadGame(2);
        game.start();
        game2.start();

    }
    
}
