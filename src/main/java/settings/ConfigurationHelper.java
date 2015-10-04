package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationHelper {

    public static Map<String, Object> getConfiguration(String fileName) throws ConfigurationException {

        Properties prop = new Properties();
        Map<String, Object> map = new HashMap<>();

        try (FileInputStream input = new FileInputStream(fileName)) {
            prop.load(input);
        } catch (IOException e) {
            throw new ConfigurationException("Unable to load properties from file 'config.properties'", e);
        }

        String stringDataSourceType = prop.getProperty("datasourcetype");
        String stringDataSource = prop.getProperty("datasource");

        if (stringDataSourceType == null) {
            throw new ConfigurationException("Data source type is missing in properties file");
        }

        DataSourceType enumDataSourceType;
        try {
            enumDataSourceType = DataSourceType.valueOf(stringDataSourceType);
        } catch (IllegalArgumentException e) {
            throw new ConfigurationException("Wrong data source type in properties file", e);
        }

        if (enumDataSourceType == DataSourceType.FILE) {
            if (stringDataSource == null) {
                throw new ConfigurationException("Data source filename is missing in properties file");
            }
        }

        map.put("datasourcetype", enumDataSourceType);
        map.put("datasource", stringDataSource);

        return map;
    }

}
