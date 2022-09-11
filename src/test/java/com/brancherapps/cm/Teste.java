package com.brancherapps.cm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class Teste {

    @Test
    void testarSeIgualADois() {

        int a = 1 + 1;

        Assertions.assertEquals(2, a);
    }

    @Test
    void testarSeIgualATres() {

        int x = 2 + 10 - 9;

        Assertions.assertEquals(3, x);
    }
}
