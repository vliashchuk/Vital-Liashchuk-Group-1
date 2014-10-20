package com.epam.jmp.tasks.memorymanagment;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ivan_Spresov
 * Date: 3/3/14
 */
public class MemoryEaterFixed {
    static final Logger logger = Logger.getLogger(MemoryEaterFixed.class);

    //Application stops with OutOfMemoryError error.
    //It creates lots of objects
    //but GC can not clean them because application holds references to all this objects.
    //In order to fix this memory leak we need to clean references after we don't need them.
    
    public static void main(String[] args) {
        List v = new ArrayList();
        while (true) {
            byte b[] = new byte[1048576];
            v.add(b);
            
            //Do some work with b;
            
            //After handling refernced objcet we need to clean reference
            v.remove(b);
            
            //Or we can clean references periodically
            //if(v.size()>100){
            //	v.clear();
            //}
            
            Runtime rt = Runtime.getRuntime();

            System.out.println("free memory: " + rt.freeMemory());
        }
    }
}
