package com.os.memorymanagementsimulator;

import static java.util.Objects.isNull;

import java.time.OffsetDateTime;

public class MemoryManagementUnit {

    VirtualMemory virtualMemory;
    MainMemory mainMemory;

    public MemoryManagementUnit() {
        virtualMemory = new VirtualMemory();
        mainMemory = new MainMemory();
    }

    synchronized void addPage(Pages newPage) {
        int lastPageIndex = virtualMemory.pages.length - 1;
        boolean isPagesFilled = isNull(virtualMemory.pages[lastPageIndex]);

        if (!isPagesFilled) {
            for (int i = 0; i < virtualMemory.pages.length; i++) {
                Pages page = virtualMemory.pages[i];
                if (page.id == newPage.id) {

                    System.out.println("Page was accessed.");

                    if (page.bitValidator == 0) {
                        mainMemory.addPage(newPage);
                        virtualMemory.addPage(newPage);
                    } else {
                        OffsetDateTime accessDate = OffsetDateTime.now();
                        page.setAccessDate(accessDate);
                        mainMemory.setPageAcessDate(accessDate, page.id);
                    }
                    break;
                }
            }
        } else {
            for (int i = 0; i < virtualMemory.pages.length; i++) {
                Pages page = virtualMemory.pages[i];
                if (isNull(page)) {
                    mainMemory.addPage(newPage);
                    virtualMemory.addPage(newPage);
                    break;
                }
            }
        }
    }

}
