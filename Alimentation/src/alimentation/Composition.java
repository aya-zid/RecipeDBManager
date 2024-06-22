package alimentation;

public class Composition {
	private int RefComposition;
	private int QuantiteComposition;
	private Recette RefRecette;
	private Ingredient RefIngredient;
	public Composition(int a, int b, Recette c, Ingredient d)
	{
		RefComposition=a;
		QuantiteComposition=b;
		RefRecette=c;
		RefIngredient=d;
	}
	public int getRefComposition() {
		return RefComposition;
	}
	public void setRefComposition(int refComposition) {
		RefComposition = refComposition;
	}
	public int getQuantiteComposition() {
		return QuantiteComposition;
	}
	public void setQuantiteComposition(int quantiteComposition) {
		QuantiteComposition = quantiteComposition;
	}
	public Ingredient getRefIngredient() {
		return RefIngredient;
	}
	public void setRefIngredient(Ingredient refIngredient) {
		RefIngredient = refIngredient;
	}
	public Recette getRefRecette() {
		return RefRecette;
	}
	public void setRefRecette(Recette refRecette) {
		RefRecette = refRecette;
	}
}
