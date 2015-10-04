package input;

import logic.tabledata.Table;

import java.io.*;

public class ConsoleTableReader extends TableReader{
    public Table readTable() throws DataReadingException{
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {
            Table table = readTableFromReader(inputStreamReader);
            return table;
        } catch (IOException e) {
            throw new DataReadingException("Unable to open/close console stream", e);
        } catch (DataReadingException e) {
            throw e;
        }
    }
}
