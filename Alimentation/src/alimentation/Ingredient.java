package alimentation;

public class Ingredient {
private String RefIngredient;
private String NomIngredient;
private TypeIngredient RefType;
public Ingredient(String ref, String nom,TypeIngredient ti)
{
	RefIngredient=ref;
	NomIngredient=nom;
	RefType=ti;
}
public String getRefIngredient() {
	return RefIngredient;
}
public void setRefIngredient(String refIngredient) {
	RefIngredient = refIngredient;
}
public String getNomIngredient() {
	return NomIngredient;
}
public void setNomIngredient(String nomIngredient) {
	NomIngredient = nomIngredient;
}
public TypeIngredient getRefType() {
	return RefType;
}
public void setRefType(TypeIngredient tyingr) {
	this.RefType = tyingr;
}


}
