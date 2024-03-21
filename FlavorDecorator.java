package Proiect;

import java.util.List;

class FlavorDecorator implements BubbleTea {
	private BubbleTea base;
	private List<String> top;
	private double pret;

	public FlavorDecorator(BubbleTea base, List<String> topping, double pret) {
		this.base = base;
		this.top = topping;
		this.pret = pret;
	}

	@Override
	public String desc() {
		String baseDesc = base.desc();
		if (!top.isEmpty()) {
			String topDesc = " cu toppingurile: " + String.join(", ", top);
			return baseDesc + topDesc;
		}
		return baseDesc;
	}

	@Override
	public double getPret() {
		return base.getPret() + pret;
	}

}