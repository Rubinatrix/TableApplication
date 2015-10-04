package input;

public class DataReadingException extends Exception{
    public DataReadingException(String message) {
        super(message);
    }
    public DataReadingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
