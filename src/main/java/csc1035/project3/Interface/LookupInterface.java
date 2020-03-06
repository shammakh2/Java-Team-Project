package csc1035.project3.Interface;

import csc1035.project3.insert.Table_Initializer;

import java.util.List;

public interface LookupInterface {

    /* */
    public List<Table_Initializer> start();

     /* Searches the Stock table for items.*/
    public List<Table_Initializer> get_data(String type, String term);
}
