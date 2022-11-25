package com.os.memorymanagementsimulator;

import java.time.OffsetDateTime;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pages {

    static int SIZE = 8;
    int bitValidator;
    public OffsetDateTime accessDate;
    int id;

    public Pages(final OffsetDateTime accessDate) {
        this.accessDate = accessDate;
        this.id = new Random().nextInt(1000);
    }

}
