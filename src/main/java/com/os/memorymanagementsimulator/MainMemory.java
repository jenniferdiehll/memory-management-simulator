package com.os.memorymanagementsimulator;

import static com.os.memorymanagementsimulator.Utils.Output.ANSI_GREEN;
import static com.os.memorymanagementsimulator.Utils.Output.ANSI_RESET;
import static com.os.memorymanagementsimulator.Utils.Output.ANSI_VERMELHO;
import static com.os.memorymanagementsimulator.Utils.Output.ANSI_YELLOW;
import static java.util.Objects.isNull;

import java.time.OffsetDateTime;

import com.os.memorymanagementsimulator.Utils.Output;

import lombok.Getter;

@Getter
public class MainMemory {

    static final int SIZE = 64;
    Pages[] pages;
    Output color;

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
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ MAIN MEMORY ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        for (int i = 0; i < pages.length; i++) {
            if (isNull(pages[i])) {
                System.out.print(" " + i);
                System.out.print(ANSI_VERMELHO + " - NP  " + ANSI_RESET);
            } else {
                System.out.format(" " + i);
                System.out.print(ANSI_GREEN + " - P:" + pages[i].id + "  " + ANSI_RESET);
            }
        }
    }

    private void printPagesReplaced(final String s) {
        System.out.println();
        System.out.print(s);
        System.out.print(ANSI_GREEN + " ✔" + ANSI_RESET);
        System.out.println();
        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println();
    }

    private void printReplacementHeader() {
        System.out.println();
        System.out.print("┌─────────────────────────────────── Page replacement required ───────────────────────────────────┐" + "\n");
        System.out.print(ANSI_YELLOW + "  Replacement started" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + " 🔄" + ANSI_RESET);
    }
}
