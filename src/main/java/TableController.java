import logic.evaluation.Evaluator;
import logic.tabledata.Table;
import output.ConsolePrinter;

public class TableController {
    private Evaluator evaluator;
    private ConsolePrinter dataPrinter;

    public TableController() {
        this.evaluator = new Evaluator();
        this.dataPrinter = new ConsolePrinter();
    }

    public TableController(Evaluator evaluator, ConsolePrinter dataPrinter) {
        this.evaluator = evaluator;
        this.dataPrinter = dataPrinter;
    }

    public void onReceivingTableData(Table table){
        evaluator.evaluateTable(table);
        dataPrinter.printTableInput(table);
        dataPrinter.printTableOutput(table);
    }
}
