package io.cucumber.skeleton;

public class Calculator {
    private Integer[] args;
    private String op;

    public Calculator() {
        this.args = new Integer[]{null, null};
        this.op = "+";
    }

    public void push(int integer) {
        if(args[0] == null) {
            args[0] = integer;
        }
        else {
            args[1] = integer;
        }
    }

    public void push(String op) {
        this.op = op;
    }

    public int value() {
        switch(op) {
            case "+":
                return args[0] + args[1];
            case "-":
                return args[0] - args[1];
            default:
                return 0;
        }
    }
}
