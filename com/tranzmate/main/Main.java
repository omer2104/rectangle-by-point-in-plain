package com.tranzmate.main;

import java.util.Vector;

import com.tranzmate.exercise.IRectangle;
import com.tranzmate.exercise.IRectanglesStore;
import com.tranzmate.exercise.Rectangle;
import com.tranzmate.exercise.Store;

/**
 * Main hardcoded test
 */
public class Main {
    public static void main(String[] args) {
        IRectanglesStore store = new Store();
        Vector<IRectangle> v = new Vector<>();

        /** TOP LEFT CHECK */
        v.add(new Rectangle(1, 5, 1, 5));
        v.add(new Rectangle(2, 5, 2, 5));
        v.add(new Rectangle(2, 5, 4, 5));
        v.add(new Rectangle(3, 5, 1, 5));
        v.add(new Rectangle(3, 5, 2, 5));
        v.add(new Rectangle(1, 7, 6, 7));
        v.add(new Rectangle(5, 6, 5, 6));
        v.add(new Rectangle(7, 9, 4, 8));
        
        store.initialize(new Rectangle(0, 100, 0, 100), v);
        
        IRectangle result = store.findRectangleAt(1, 2);
        System.out.println(result);
        
        result = store.findRectangleAt(7, 5);
        System.out.println(result);
        
        result = store.findRectangleAt(5, 7);
        System.out.println(result);
        
        result = store.findRectangleAt(6, 7);
        System.out.println(result);

        result = store.findRectangleAt(0, 0);
        System.out.println(result);
        
        /*
        RESULTS: 
        top: 1 bottom: 5 left: 1 right: 5
        top: 1 bottom: 7 left: 6 right: 7
        top: 7 bottom: 9 left: 4 right: 8
        top: 1 bottom: 7 left: 6 right: 7
        null
        */
        
        /** TOP RIGHT CHECK */
        v.add(new Rectangle(1, 5, 50, 55));
        v.add(new Rectangle(1, 5, 60, 70));
        v.add(new Rectangle(2, 5, 50, 75));
        v.add(new Rectangle(1, 10, 90, 100));
        v.add(new Rectangle(0, 6, 90, 95));
        v.add(new Rectangle(20, 24, 30, 40));
        v.add(new Rectangle(5, 6, 5, 6));
        v.add(new Rectangle(7, 9, 4, 8));
        
        store.initialize(new Rectangle(0, 100, 0, 100), v);
        
        result = store.findRectangleAt(60, 2);
        System.out.println(result);
        
        result = store.findRectangleAt(92, 5);
        System.out.println(result);
        
        result = store.findRectangleAt(95, 8);
        System.out.println(result);
        
        result = store.findRectangleAt(33, 22);
        System.out.println(result);

        result = store.findRectangleAt(100, 0);
        System.out.println(result);

        /*
        RESULTS
        top: 1 bottom: 5 left: 60 right: 70
        top: 0 bottom: 6 left: 90 right: 95
        top: 1 bottom: 10 left: 90 right: 100
        top: 20 bottom: 24 left: 30 right: 40
        null
        */


        /** BOTTOM LEFT CHECK */
        v.add(new Rectangle(51, 55, 1, 5));
        v.add(new Rectangle(52, 55, 2, 5));
        v.add(new Rectangle(52, 55, 4, 5));
        v.add(new Rectangle(53, 55, 1, 5));
        v.add(new Rectangle(53, 55, 2, 5));
        v.add(new Rectangle(71, 77, 6, 7));
        v.add(new Rectangle(75, 76, 5, 6));
        v.add(new Rectangle(77, 79, 4, 8));
        
        store.initialize(new Rectangle(0, 100, 0, 100), v);
        
        result = store.findRectangleAt(4, 52);
        System.out.println(result);
        
        result = store.findRectangleAt(7, 55);
        System.out.println(result);
        
        result = store.findRectangleAt(6, 75);
        System.out.println(result);
        
        result = store.findRectangleAt(6, 78);
        System.out.println(result);

        result = store.findRectangleAt(0, 100);
        System.out.println(result);
        
        /*
        RESULTS: 
        top: 51 bottom: 55 left: 1 right: 5
        null
        top: 71 bottom: 77 left: 6 right: 7
        top: 77 bottom: 79 left: 4 right: 8
        null
        */ 
    }
}