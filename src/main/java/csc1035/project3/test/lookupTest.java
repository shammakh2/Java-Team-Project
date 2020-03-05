package csc1035.project3.test;

import csc1035.project3.insert.Table_Initializer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class lookupTest {
    @Test
    @DisplayName("Test -> Get Lookup")
    void getLookup(){
        List<Table_Initializer> data = csc1035.project3.lookup.stockLookup.start();
        int expected_length = 1;
        int actual = data.size();
        assertEquals(expected_length, actual);
    }
}
