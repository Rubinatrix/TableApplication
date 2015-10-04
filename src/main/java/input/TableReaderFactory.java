package input;

import settings.DataSourceType;

import java.util.Map;

public class TableReaderFactory {
    public static TableReader getTableReader(Map<String, Object> settings){
        DataSourceType dataSourceType = (DataSourceType) settings.get("datasourcetype");
        if (dataSourceType == DataSourceType.FILE) {
            return new FileTableReader((String) settings.get("datasource"));
        } else {
            return new ConsoleTableReader();
        }
    }
}
