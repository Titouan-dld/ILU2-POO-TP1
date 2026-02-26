package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public static class Marche {
		private Etal[] etals;
		
		public Marche(int nombreEtal) {
			this.etals = new Etal[nombreEtal];
		}
		
		 void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			 if (etals[indiceEtal].isEtalOccupe()) {
				 System.out.println("L'etal est déja occupé");
			 } else {
				 etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			 }
		 }
		 
		 int trouverEtalLibre() {
			 int indice = 0;
			 while(indice<etals.length ) {
				 if(!etals[indice].isEtalOccupe()) {
					 return indice;
				 }
				 indice++;
			 }
			 return -1;
		 }
		 
		 Etal[] trouverEtals(String produit) {
			 int sizeTableau = 0;
			 for(int i = 0; i<etals.length; i++) {
				 if(etals[i].contientProduit(produit)) {
					 sizeTableau++;
				 }
			 }
			 if(sizeTableau == 0 ) {
				 return null;
			 }
			 Etal[] listeEtalProduit = new Etal[sizeTableau];
			 sizeTableau = 0;
			 for(int i = 0; i<etals.length; i++) {
				 if(etals[i].contientProduit(produit)) {
					 listeEtalProduit[sizeTableau] = etals[i];
					 sizeTableau++;
				 }
			 }
			 return listeEtalProduit;
		 }
		 
		 Etal trouverVendeur(Gaulois gaulois) {
			 for(int i = 0; i<etals.length; i++) {
				 if(etals[i].getVendeur() == gaulois) {
					 return etals[i];
				 }
			 }
			 return null;
		 }
		 
		 void afficherMarche() {
			 int nbOccupe = 0;

			 for(int i = 0; i<etals.length; i++) {
				 if(etals[i].isEtalOccupe()) {
					 etals[i].afficherEtal();
					 nbOccupe++;
				 }
			 }
			 nbOccupe = etals.length - nbOccupe;
			 if(nbOccupe != 0) {
				 System.out.println("Il reste " + nbOccupe + "nbEtalVide + \" étals non utilisés dans le marché.\\n");
			 }
			 
		 }
	}
}