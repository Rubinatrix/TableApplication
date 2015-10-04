package logic.tabledata;

public class Table {

    private int x;
    private int y;
    private Cell[][] cells;

    // get table cell by it's name according to
    // cell_reference ::= [A-Za-z][0-9] --
    public Cell getCellByName(String name) {
        if (name.length() < 2) {
            return null;
        } else if ((name.charAt(0) >= 65) && (name.charAt(0) <= 87) && (name.substring(1).matches("\\d+"))) {
            int y = name.charAt(0) - 65;
            int x = Integer.parseInt(name.substring(1)) - 1;
            return cells[x][y];
        } else {
            return null;
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
