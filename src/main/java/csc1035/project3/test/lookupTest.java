package csc1035.project3.test;

import csc1035.project3.tables.Table_Initializer;
import csc1035.project3.lookup.stockLookup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class lookupTest {
    @Test
    @DisplayName("Test -> Get Lookup")
    void getLookup(){
        stockLookup test_lookup = new stockLookup();
        List<Table_Initializer> data = test_lookup.start();
        int expected_length = 1;
        int actual = data.size();
        assertEquals(expected_length, actual);
    }
}
