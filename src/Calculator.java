public class Calculator {
    private int leftOperand , rightOperand;

    public Calculator(){}

    public Calculator(int leftOperand, int rightOperand){
        this.rightOperand = rightOperand;
        this.leftOperand = leftOperand;
    }
    public int add(){
        return leftOperand + rightOperand;
    }

    public int subtract(){
        return leftOperand - rightOperand;
    }
    public int multiplication(){
        return leftOperand * rightOperand;
    }
    public int division(){
        return leftOperand / rightOperand;
    }

    public int getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(int leftOperand) {
        this.leftOperand = leftOperand;
    }

    public int getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(int rightOperand) {
        this.rightOperand = rightOperand;
    }
}
