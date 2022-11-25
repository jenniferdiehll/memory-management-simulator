package com.os.memorymanagementsimulator;

import static java.util.Objects.isNull;

import java.time.OffsetDateTime;

import com.os.memorymanagementsimulator.Utils.Colors;

import lombok.Getter;

@Getter
public class MainMemory {

    static final int SIZE = 64;
    Pages[] pages;
    Colors color;


    public MainMemory() {
        pages = new Pages[SIZE / Pages.SIZE];
    }

    public void addPage(Pages newPage) {
        System.out.println(newPage.accessDate);
        boolean shouldReplace = false;
        for (int i = 0; i < pages.length; i++) {
            if (isNull(pages[i])) {
                pages[i] = newPage;
                shouldReplace = true;
                break;
            }
        }
        if (!shouldReplace) {
            replacePage(newPage);
        }
        printMemory();
    }

    //LRU
    private void replacePage(Pages newPage) {
        int indexReplace = 0;
        printReplacementHeader();
        for (int i = 1; i < pages.length; i++) {
            if (pages[i].accessDate.isBefore(pages[indexReplace].accessDate)) {
                indexReplace = i;
            }
        }
        printPagesReplaced("  Page " + pages[indexReplace].id + " has been replaced by page " + newPage.id);
        pages[indexReplace] = newPage;
    }

    public void setPageAcessDate(OffsetDateTime accessDate, int id) {
        for (final Pages page : pages) {
            if (page.id == id) {
                page.setAccessDate(accessDate);
                break;
            }
        }
    }

    private void printMemory() {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%43s", "MAIN MEMORY");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 0; i < pages.length; i++) {
            if (isNull(pages[i])) {
                System.out.printf("%5s", i);
            } else {
                System.out.format("%5s", i);
                System.out.format("%5s", pages[i].id);
            }
        }
    }

    private void printPagesReplaced(final String s) {
        System.out.println();
        System.out.print(s);
        System.out.print(Colors.ANSI_GREEN + " ✔" + Colors.ANSI_RESET);
        System.out.println();
        System.out.println("└────────────────────────────────────────────────────────┘");
        System.out.println();
    }

    private void printReplacementHeader() {
        System.out.println();
        System.out.print("┌───────────── Page replacement required ────────────────┐" + "\n");
        System.out.print(Colors.ANSI_YELLOW + "  Replacement started" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_YELLOW + " 🔄" + Colors.ANSI_RESET);
    }
}
