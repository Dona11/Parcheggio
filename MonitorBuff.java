/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcheggio;

/**
 *
 * @author Utente
 */
public class MonitorBuff {
    
    private int[] buffer;
    private int conta;
    private int metti;
    private int togli;
    private final int DIM = 10;
    
    public MonitorBuff(){
    
        buffer = new int[DIM];
        conta = 0;
        metti = 0;
        togli = 0;
    }
    
    public synchronized void ingresso(int dato) throws InterruptedException{
    
        while(conta == DIM){
        
            this.wait();
        }
        
        buffer[metti] = dato;
        metti = (metti + 1) % DIM;
        
        conta++;
        this.notify();
    }
    
    public synchronized void uscita() throws InterruptedException{
    
        while(conta == DIM){
        
            this.wait();
        }
        
        int dato;
        dato = buffer[togli];
        togli = (togli + 1) % DIM;
        
        
        conta--;
        this.notify();
    }
}
