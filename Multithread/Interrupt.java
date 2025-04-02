/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interrupt;

/**
 *
 * @author HAK_PHENG
 */
public class Interrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () ->{
            for (int i = 0; i < 10; i++){
                //Manual Check
                if(Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1000);
                    } catch (InterruptedException ex){
                        return;
                }
            }
        };
            
        var thread = new Thread(runnable);
        
        System.out.println("Program sedang berjalan ... ");
        thread.start();
        thread.sleep(4000);
        thread.interrupt();
        System.out.println("Menunggu");
        thread.join();
        
        System.out.println("Program telah dijalankan");
    }
}
