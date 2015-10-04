package logic.evaluation;

import logic.tabledata.Table;
import logic.tabledata.Cell;

import java.util.Stack;

public class Evaluator {

    public boolean evaluateCell(Cell cell){
        if (!cell.isEvaluated()) {
            if (!cell.isProcessing()) {
                cell.setProcessing(true);
                try {
                    cell.setOutput(evaluateCellValue(cell.getInput(), cell.getTable()));
                } catch (EvaluationException e) {
                    cell.setOutput(e.getMessage());
                }
                cell.setProcessing(false);
                cell.setEvaluated(true);
            } else {
                cell.setEvaluated(false);
            }
        }
        return cell.isEvaluated();
    }

    public void evaluateTable(Table table) {

        for (int i = 0; i < table.getX(); i++) {
            for (int j = 0; j < table.getY(); j++) {
                evaluateCell(table.getCells()[i][j]);
            }
        }

    }

    //check cell format according to
    //expression ::= '=' term {operation term}*
    //text ::= '\'' {printable_character}
    //and return evaluated value
    private String evaluateCellValue(String text, Table table) throws EvaluationException {
        if (text.length() == 0) {
            return text;
        } else if (text.charAt(0) == '=') {
            if (text.length() > 1) {
                TreeNode tree = BuildTree(text.substring(1));
                return evaluateTree(tree, table);
            } else {
                throw new EvaluationException(EvaluationErrorType.ILLEGAL_CELL_FORMAT.getErrorMessage());
            }
        } else if (text.charAt(0) == '\'') {
            return text.substring(1);
        } else if (EvaluationHelper.isNonnegativeNumber(text)) {
            return text;
        } else {
            throw new EvaluationException(EvaluationErrorType.ILLEGAL_CELL_FORMAT.getErrorMessage());
        }
    }

    // check operand format according to
    // term ::= cell_reference | nonnegative_number"
    // and return evaluated value
    private String evaluateOperandValue(String text, Table table) throws EvaluationException{
        if ((text.length() == 0) || (EvaluationHelper.isNonnegativeNumber(text))) {
            return text;
        } else {
            Cell cell = table.getCellByName(text);
            if (cell != null) {
                boolean isEvaluated = evaluateCell(cell);
                if (isEvaluated == true){
                    return cell.getOutput();
                } else {
                    throw new EvaluationException(EvaluationErrorType.CIRCULAR_REFERENCE.getErrorMessage());
                }
            } else {
                throw new EvaluationException(EvaluationErrorType.ILLEGAL_OPERAND_FORMAT.getErrorMessage());
            }
        }
    }

    // converting string to binary tree before evaluation
    private TreeNode BuildTree(String text) {
        int start = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (EvaluationHelper.isOperator(ch)) {
                TreeNode node = new TreeNode(text.substring(start, i), TreeValueType.OPERAND);
                start = i + 1;
                TreeNode operator = new TreeNode(String.valueOf(ch), TreeValueType.OPERATOR);
                if (stack.empty()) {
                    operator.setLeft(node);
                    stack.push(operator);
                } else {
                    TreeNode prevNode = stack.pop();
                    prevNode.setRight(node);
                    operator.setLeft(prevNode);
                    stack.push(operator);
                }
            } else if (i == text.length() - 1) {
                TreeNode node = new TreeNode(text.substring(start), TreeValueType.OPERAND);
                if (stack.empty()) {
                    stack.push(node);
                } else {
                    TreeNode prevNode = stack.pop();
                    prevNode.setRight(node);
                    stack.push(prevNode);
                }
            }
        }
        if (!stack.empty())
            return stack.pop();
        else
            return null;
    }

    // resolving binary tree in context of selected table
    private String evaluateTree(TreeNode tree, Table table) throws EvaluationException{
        switch (tree.getType()){
            case OPERATOR:
                return doOperation(evaluateTree(tree.getLeft(), table), evaluateTree(tree.getRight(), table), tree.getValue());
            case OPERAND:
                return evaluateOperandValue(tree.getValue(), table);
            default:
                throw new EvaluationException(EvaluationErrorType.UNKNOWN_ERROR.getErrorMessage());
        }
    }

    // evaluate result of applying selected operation to two arguments
    private String doOperation(String leftArg, String rightArg, String operation) throws EvaluationException{

        StringBuilder sb = new StringBuilder();

        int leftOperand = 0;
        int rightOperand = 0;

        if ((leftArg != null) && (EvaluationHelper.isNumber(leftArg))){
            leftOperand = Integer.parseInt(leftArg);
        } else {
            throw new EvaluationException(EvaluationErrorType.ILLEGAL_ARGUMENT_VALUE.getErrorMessage());
        }

        if ((rightArg != null) && (EvaluationHelper.isNumber(rightArg))){
            rightOperand = Integer.parseInt(rightArg);
        } else {
            throw new EvaluationException(EvaluationErrorType.ILLEGAL_ARGUMENT_VALUE.getErrorMessage());
        }

        if (operation.equals("+")) {
            sb.append(leftOperand + rightOperand);
        } else if (operation.equals("-")) {
            sb.append(leftOperand - rightOperand);
        } else if (operation.equals("/")) {
            sb.append(leftOperand / rightOperand);
        } else if (operation.equals("*")) {
            sb.append(leftOperand * rightOperand);
        }

        return sb.toString();

    }

}
