package com.os.memorymanagementsimulator;

import java.time.OffsetDateTime;

public class Starter {

    public static void main(String[] args) throws InterruptedException {

        MemoryManagementUnit mmu = new MemoryManagementUnit();

        for (int i = 0; i < 10 ; i++) {
//            Thread t = new Thread(() -> {
                mmu.addPage(new Pages(OffsetDateTime.now()));
//            });
//            Thread.sleep(2000);
//            t.start();
        }
    }
}
