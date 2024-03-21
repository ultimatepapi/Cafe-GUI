package Proiect;

class MilkyBubbleTea implements BubbleTea {
    private String flavor;
    private boolean cuSoia;
    private double pret;

    public MilkyBubbleTea(String aroma, boolean soia) {
        this.flavor = aroma;
        this.cuSoia = soia;
        this.pret = pret();
    }

    @Override
    public String desc() {
        return "Bubble tea cu lapte, " + flavor + (cuSoia ? " si lapte de soia" : "");
    }

    @Override
    public double getPret() {
        return pret;
    }

    private double pret() {
        return 20;
    }
}