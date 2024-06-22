package alimentation;

public class TypeIngredient {
private String RefType;
private String NomType;
public TypeIngredient(String ref, String nom)
{
	RefType=ref;
	NomType=nom;
}
public String getRefType() {
	return RefType;
}
public void setRefType(String refType) {
	RefType = refType;
}
public String getNomType() {
	return NomType;
}
public void setNomType(String nomType) {
	NomType = nomType;
}
}
