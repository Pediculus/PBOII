/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.daemon;

/**
 *
 * @author HAK_PHENG
 */
public class Daemon {

    public static void main(String[] args) {
        
        var thread = new Thread(()->{
            try {
                Thread.sleep(3000);
                System.out.println("Run Thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        
    }
}
