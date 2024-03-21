package Proiect;

class FruityBubbleTea implements BubbleTea {
    private String flavor;
    private double pret;

    public FruityBubbleTea(String flavor) {
        this.flavor = flavor;
        this.pret = pret();
    }

    @Override
    public String desc() {
        return "Bubble tea cu fructe, " + flavor;
    }

    @Override
    public double getPret() {
        return pret;
    }

    private double pret() {
        return 15;
    }
}