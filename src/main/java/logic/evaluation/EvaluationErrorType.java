package logic.evaluation;


public enum EvaluationErrorType {

    ILLEGAL_CELL_FORMAT ("#ErrorCellFormat"),
    ILLEGAL_OPERAND_FORMAT("#ErrorOperandFormat"),
    ILLEGAL_ARGUMENT_VALUE("#IllegalArgValue"),
    CIRCULAR_REFERENCE("#CircularRef"),
    UNKNOWN_ERROR("#UnknownErr");

    private String errorMessage;

    EvaluationErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
