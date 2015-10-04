package logic.tabledata;

public class Cell {

    private Table table;
    private String input;
    private String output;
    private boolean evaluated;

    //indicates if evaluating process in this cell has started, but not finished yet
    private boolean processing = false;

    public Cell(String input, Table table) {
        this.table = table;
        this.input = input;
        this.evaluated = false;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public Table getTable() {
        return table;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

}
