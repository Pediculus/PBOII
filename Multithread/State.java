/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.state;

/**
 *
 * @author HAK_PHENG
 */
public class State {

    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Jalan di thread :  " + Thread.currentThread().getName());
            });
        thread.setName("Slide 1");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}