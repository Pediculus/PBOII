/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.racecondition;

/**
 *
 * @author HAK_PHENG
 */
public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        var penghitung = new counter();
        Runnable runnable = ()->{
            for (int i = 0; i < 1000000; i++){
                penghitung.increment();
            }
        };
        
    var thread1 = new Thread(runnable);
    var thread2 = new Thread(runnable);
    var thread3 = new Thread(runnable);
    
    thread1.start();
    thread2.start();
    thread3.start();
    
    thread1.join();
    thread2.join();
    thread3.join();
    
    System.out.println(penghitung.getValue());
    }
}