package input;

import logic.tabledata.Table;

import java.io.FileReader;
import java.io.IOException;

public class FileTableReader extends TableReader {
    final private String fileName;

    public FileTableReader(String fileName) {
        this.fileName = fileName;
    }

    public Table readTable() throws DataReadingException{
        try (FileReader fileReader = new FileReader(fileName)) {
            Table table = readTableFromReader(fileReader);
            return table;
        } catch (IOException e) {
            throw new DataReadingException("Unable to open file " + fileName, e);
        } catch (DataReadingException e) {
            throw e;
        }
    }
}
