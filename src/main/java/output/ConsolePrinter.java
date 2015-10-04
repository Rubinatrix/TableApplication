package output;

import logic.tabledata.Table;

public class ConsolePrinter {

    public void printTableInput(Table table) {
        System.out.println();
        for (int i = 0; i < table.getX(); i++) {
            for (int j = 0; j < table.getY(); j++) {
                System.out.print(table.getCells()[i][j].getInput());
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void printTableOutput(Table table) {
        System.out.println();
        for (int i = 0; i < table.getX(); i++) {
            for (int j = 0; j < table.getY(); j++) {
                System.out.print(table.getCells()[i][j].getOutput());
                System.out.print("\t");
            }
            System.out.println();
        }
    }

}
