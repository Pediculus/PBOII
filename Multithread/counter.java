/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.racecondition;

/**
 *
 * @author HAK_PHENG
 */
public class counter {
    private Long value = 0L;
    
    public synchronized void increment(){ //Menggunakan Synchronized untuk mencegah race condition
        value++;
    }
    
    public synchronized Long getValue(){
        return value;
    }
}
