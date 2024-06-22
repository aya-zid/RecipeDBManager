package alimentation;
import java.util.Date;
public class Produit {
	private String RefProduit;
	private String DescriptifProduit;
	private Date DatePremption;
	private int QuantiteProduit;
	private int PrixProduit;
	private Rangement RefRangement;
	private Ingredient RefIngredient;
	
	public Produit(String a, String b, Date c, int d, int e, Rangement f, Ingredient g)
	{
		setRefProduit(a);
		setDescriptifProduit(b);
		setDatePremption(c);
		setQuantiteProduit(d);
		setPrixProduit(e);
		setRefRangement(f);
		setRefIngredient(g);		
	}

	public String getRefProduit() {
		return RefProduit;
	}

	public void setRefProduit(String refProduit) {
		RefProduit = refProduit;
	}

	public String getDescriptifProduit() {
		return DescriptifProduit;
	}

	public void setDescriptifProduit(String descriptifProduit) {
		DescriptifProduit = descriptifProduit;
	}

	public Date getDatePremption() {
		return DatePremption;
	}

	public void setDatePremption(Date datePremption) {
		DatePremption = datePremption;
	}

	public int getQuantiteProduit() {
		return QuantiteProduit;
	}

	public void setQuantiteProduit(int quantiteProduit) {
		QuantiteProduit = quantiteProduit;
	}

	public int getPrixProduit() {
		return PrixProduit;
	}

	public void setPrixProduit(int prixProduit) {
		PrixProduit = prixProduit;
	}

	public Rangement getRefRangement() {
		return RefRangement;
	}

	public void setRefRangement(Rangement refRangement) {
		RefRangement = refRangement;
	}

	public Ingredient getRefIngredient() {
		return RefIngredient;
	}

	public void setRefIngredient(Ingredient refIngredient) {
		RefIngredient = refIngredient;
	}

}
