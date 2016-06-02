/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jRenderLove.app;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r= new Random();
        System.out.println((int)(r.nextDouble() * 2+1));
    }
    
}
