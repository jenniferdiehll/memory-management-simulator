package com.os.memorymanagementsimulator;

import static java.util.Objects.isNull;

import lombok.Getter;

@Getter
public class VirtualMemory {

    static final int SIZE = 1000;
    Pages[] pages;

    public VirtualMemory() {
        pages = new Pages[SIZE / Pages.SIZE];
    }

    public void addPage(Pages newPage) {
        for (int i = 0; i < pages.length; i++) {
            if (isNull(pages[i])) {
                pages[i] = newPage;
                break;
            }
        }
        System.out.println("VIRTUAL");
        for (int i = 0; i < pages.length; i++) {
            if (isNull(pages[i])){
                System.out.println(i + " - memoria virtual vazia");
            }else{
                System.out.println(i + " - " + pages[i].id);
            }
        }
    }

}
