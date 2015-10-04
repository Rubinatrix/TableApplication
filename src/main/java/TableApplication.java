import input.DataReadingException;
import input.TableReaderFactory;
import logic.tabledata.Table;
import settings.ConfigurationException;
import settings.ConfigurationHelper;

import java.util.Map;

public class TableApplication {

    public static void main(String[] args) throws ConfigurationException, DataReadingException {

        TableController controller = new TableController();
        Map settings = ConfigurationHelper.getConfiguration("config.properties");
        Table table = TableReaderFactory.getTableReader(settings).readTable();
        controller.onReceivingTableData(table);

    }
}
