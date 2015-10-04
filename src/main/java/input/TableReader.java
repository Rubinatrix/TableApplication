package input;

import logic.tabledata.Cell;
import logic.tabledata.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public abstract class TableReader {

    protected Table readTableFromReader(Reader reader) throws DataReadingException{

        BufferedReader bufferedReader = new BufferedReader(reader);

        Table newTable = new Table();

        int x = 0;
        int y = 0;
        String[] dimensions;

        try {
            dimensions = bufferedReader.readLine().split("   ");
        } catch (IOException e) {
            throw new DataReadingException("Buffer reading error", e);
        }

        if (dimensions.length != 2) {
            throw new DataReadingException("Illegal dimensions line format");
        }

        try {
            x = Integer.parseInt(dimensions[0]);
            y = Integer.parseInt(dimensions[1]);
        } catch (NumberFormatException e) {
            throw new DataReadingException("Illegal dimensions line format", e);
        }

        newTable.setX(x);
        newTable.setY(y);

        Cell[][] cells = new Cell[x][y];
        for (int i = 0; i < x; i++) {
            try {
                String[] line = bufferedReader.readLine().split("   ");
                for (int j = 0; j < y; j++) {
                    cells[i][j] = new Cell(line[j], newTable);
                }
            } catch (Exception e) {
                throw new DataReadingException("Illegal table line #" + (i + 1) + " format", e);
            }
        }
        newTable.setCells(cells);

        return newTable;
    }

    public abstract Table readTable() throws DataReadingException;

}
