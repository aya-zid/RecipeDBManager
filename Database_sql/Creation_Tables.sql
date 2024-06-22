-- Ensemble des rangements présents dans le logement
CREATE TABLE Rangement (RefRangement Char(5), 
                        NomRangement VarChar(100),
			CONSTRAINT PK_Rangement PRIMARY KEY (RefRangement) );

-- Catégories des ingrédients utilisés : fruits, légumes, fruits de mer, produit laitier ...
CREATE TABLE TypeIngredient (RefType Char(5),
                             NomType VarChar(50),
			     CONSTRAINT PK_TypeIngredient PRIMARY KEY (RefType));

-- Ingrédients pouvant être utilisé dans une recette
CREATE TABLE Ingredient (RefIngredient Char(5),
                         NomIngredient VarChar(50),
                         RefType Char(5) ,
			CONSTRAINT PK_Ingredient PRIMARY KEY (RefIngredient),
			CONSTRAINT FK_Ingredient_TypeIngredient FOREIGN KEY (RefType) REFERENCES TypeIngredient );

CREATE TABLE Recette (RefRecette Char(6),
                      NomRecette VarChar(50),
                      DescriptifRecette Text,
                      CaloriesRecette Numeric(4),
                      Difficulte Char(9) DEFAULT 'facile' CONSTRAINT CK_Recette_Difficulte CHECK (Lower(Difficulte)in('facile','moyen','difficile')),
                      TempsPreparation Numeric(4),
                      TempsCuisson Numeric(3),
                      NbPersonnes Numeric(2),
			CONSTRAINT PKey_Recette PRIMARY KEY (RefRecette));

CREATE TABLE Composition (RefComposition Numeric(10),
                          QuantiteComposition Numeric(12,2),
                          RefRecette Char(6),
                          RefIngredient Char(5),
			CONSTRAINT PK_Composition PRIMARY KEY (RefComposition),
			CONSTRAINT FK_Composition_Recette FOREIGN KEY (RefRecette) REFERENCES Recette,
			CONSTRAINT FK_Composition_Ingredient FOREIGN KEY (RefIngredient) REFERENCES Ingredient);

CREATE TABLE Produit (RefProduit Char(6),
                      DescriptifProduit Varchar(100),
                      DatePeremption Date,
                      QuantiteProduit Numeric(6),
                      PrixProduit Numeric(8,2),
                      RefRangement Char(5) References Rangement,
                      RefIngredient Char(5) References Ingredient,
			CONSTRAINT PK_Produit PRIMARY KEY (RefProduit),
			CONSTRAINT FK_Produit_Rangement FOREIGN KEY (RefRangement) REFERENCES Rangement,
			CONSTRAINT FK_Produit_Ingredient FOREIGN KEY (RefIngredient) REFERENCES Ingredient);