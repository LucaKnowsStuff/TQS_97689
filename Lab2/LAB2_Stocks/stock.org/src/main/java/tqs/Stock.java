package tqs;

public class Stock {
    private String label;
    private int quantaty;

    public Stock (String label , int quantaty) {
        this.label = label;
        this.quantaty = quantaty;
    }


    public String getLabel() {
        return this.label;
    }

    public  int getQuantaty() {
        return this.quantaty;
    }


    public void setLabel(String label) {
        this.label = label;
    }

    public void setQuantaty(int quantaty) {
        this.quantaty = quantaty;
    }


}
