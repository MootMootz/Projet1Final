package fr.isika.cda24.TPAnnuaire.entitees;

import java.io.IOException;
import java.io.RandomAccessFile;


public class Noeud {

	protected static final int FILS_NUL = -1;
	private Stagiaire cle;
	private int filsGauche;
	private int filsDroit;
	private int doublon;
	
	public static final int TAILLE_TOTALE_NOEUD_OCTETS= Stagiaire.TAILLE_Stagiaires_Octets + 12;

	// constructeur d'un noeud
	

	public Noeud(Stagiaire cle, int filsGauche, int filsDroit, int doublon) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.doublon= doublon; 
	}

	public Noeud() {
		
	}

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	public void ecrireStagiaire(Stagiaire stagiaire, RandomAccessFile raf) {

		try {
			raf = new RandomAccessFile("src/main/java/fr/isika/cd24/annuaire/annuaire/test.bin", "rw");
			
			raf.seek(raf.length());

			raf.writeChars(stagiaire.getNomLong());

			raf.writeChars(stagiaire.getPrenomLong());

			raf.writeChars(stagiaire.getDptLong());
			raf.writeChars(stagiaire.getPromoLong());
			raf.writeChars(stagiaire.getAnneeLong());

			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);
			raf.writeInt(Noeud.FILS_NUL);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Noeud lireStagiaire(RandomAccessFile raf) {

		String nom = "";
		String prenom = "";
		String departement = "";
		String promo = "";
		String annee = "";
		int filsGauche = -1;
		int filsDroit = -1;
		int doublon=-1;

		try {
			// Lit bes variables de l'objet stagiaire

			for (int i = 0; i < Stagiaire.TAILLE_NOM_Caracteres; i++) {
				nom += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_Prenom_Caracteres; i++) {
				prenom += raf.readChar();

			}

			for (int i = 0; i < Stagiaire.TAILLE_Dpt_Caracteres; i++) {

				departement += raf.readChar();

			}
			for (int i = 0; i < Stagiaire.TAILLE_promo_Caracteres; i++) {
				promo += raf.readChar();
			}
			
			for (int i = 0; i < Stagiaire.TAILLE_annee_Caracteres; i++) {
				annee += raf.readChar();
			}
			
			filsGauche= raf.readInt(); 
			filsDroit= raf.readInt();
			doublon= raf.readInt();

			Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, promo, annee);

			return new Noeud(stagiaire, filsGauche, filsDroit,doublon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public int getDoublon() {
		return doublon;
	}

	public void setDoublon(int doublon) {
		this.doublon = doublon;
	}

	public void affichageInfixeNoeud(RandomAccessFile raf) throws IOException {

		if (this.getFilsGauche() != -1) {   // si fils gauche n'est pas vide 

			raf.seek(this.getFilsGauche() * (TAILLE_TOTALE_NOEUD_OCTETS)); // déplacer le curseur au niveau du fils gauche 

			Noeud noeudCourant = lireStagiaire(raf); // lire le fils gauche du noeud 

			noeudCourant.affichageInfixeNoeud(raf); //appel récursif de la méthode 
		}
		System.out.println(this.cle);

		if (this.getFilsDroit() != -1) {

			raf.seek(this.getFilsDroit() * (TAILLE_TOTALE_NOEUD_OCTETS));

			Noeud noeudCourant = lireStagiaire(raf);

			noeudCourant.affichageInfixeNoeud(raf);
		}
		
	}

	public Noeud rechercherStagiaire(String stagiaireRecherche , RandomAccessFile raf) throws IOException {  
		
		if (this.getCle().getNom().equalsIgnoreCase (stagiaireRecherche)) { // lorsque la valeur du nom recherché correspond au premier nom on l'affiche avec ses doublons 
			
			return this; 
			
		} else if (stagiaireRecherche.compareToIgnoreCase(this.getCle().getNom()) < 0) {  // si la valeur est inférieure on va au niveau du fils gauche 
			
			if (this.filsGauche == FILS_NUL) { // si la valeur du fils gauche est nulle ça signifie que le nom n'est pas disponible
				
				System.out .println("Ce stagiaire n'est pas présent dans l'annuaire");
				
				return null;
				
			} else { // sinon 
				
				raf.seek(this.filsGauche * Noeud.TAILLE_TOTALE_NOEUD_OCTETS) ; // on se positionne au niveau du fils gauche 
				
				Noeud filsGauche = lireStagiaire(raf); // on le lit 
				
				return filsGauche.rechercherStagiaire(stagiaireRecherche,raf); // et on applique de façon récursive la méthode de recheche 
			}
			} else {
			
				if (this.filsDroit== FILS_NUL) { // même principe avec le fils droit 
					
					System.out .println("Ce stagiaire n'est pas présent dans l'annuaire");
					
					return null;
				} else {
					
					System.out.println("droite");
					
					raf. seek(this.filsDroit * Noeud.TAILLE_TOTALE_NOEUD_OCTETS);
					
					Noeud filsDroit = lireStagiaire(raf);
					
					return filsDroit.rechercherStagiaire(stagiaireRecherche, raf);
					
				}
					
				}
			}
		
		

	
	
@Override
	public String toString() {
		return "Noeud [cle=" + cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + ", doublon=" + doublon
				+ "]";
	}

	public void ajouterNoeud (Stagiaire stagiaireNouveau, RandomAccessFile raf) throws IOException {
		
		int comparaison = this.getCle().getNom().compareTo(stagiaireNouveau.getNom()); //comparer le statgiaire à ajouter avec les stagiaires déjà présents 
		
		if (comparaison>0) { 
		
			
			if (this.getFilsGauche() == FILS_NUL) {
				
				raf.seek(raf.getFilePointer()-12); // déplace le curseur de 12 octets et se positionne sur la case du fils gauche 
				
				raf.writeInt((int) (raf.length() / (TAILLE_TOTALE_NOEUD_OCTETS))); // calcul de l'index du nouveau noeud et l'écrit 
		
				raf.seek(raf.length());
				
				ecrireStagiaire (stagiaireNouveau, raf);  // écriture du nouveau noeud dans le fichier 
	}  else {
		
		raf.seek(this.getFilsGauche() * (TAILLE_TOTALE_NOEUD_OCTETS));// déplace le curseur au niveau du fils gauche
		Noeud noeudFilsGauche = lireStagiaire (raf);// lit le fils gauche 
		
		 noeudFilsGauche.ajouterNoeud(stagiaireNouveau, raf); //appel récursif de la méthode afin de trouver un moyen d'insérer le noeud
		
	}
		} else if (comparaison < 0) {
			
		if (this.getFilsDroit() == FILS_NUL) {
		
		raf.seek(raf.getFilePointer()-8); // se déplace de 8 octets pour aller au niveau du fils droit 
			
			raf.writeInt((int) (raf.length() / (TAILLE_TOTALE_NOEUD_OCTETS)));
			
			raf.seek(raf.length());
			
			ecrireStagiaire (stagiaireNouveau, raf);
			
		} else {
			
			raf.seek(this.getFilsDroit() * (TAILLE_TOTALE_NOEUD_OCTETS));
			
			Noeud noeudFilsDroit = lireStagiaire(raf);
			
			 noeudFilsDroit.ajouterNoeud(stagiaireNouveau, raf);
			
		} 
		} else if (comparaison==0) {
			
			if (this.getDoublon() == FILS_NUL);{
				
				
				raf.seek(raf.getFilePointer()-4);
				
				raf.writeInt((int) (raf.length() / (TAILLE_TOTALE_NOEUD_OCTETS)));
				
				raf.seek(raf.length());
				
				ecrireStagiaire (stagiaireNouveau, raf);
				
			}
		} else {
					
					raf.seek(this.getDoublon() * (TAILLE_TOTALE_NOEUD_OCTETS));
					
					Noeud noeudDoublon = lireStagiaire(raf);
				
				 noeudDoublon.ajouterNoeud(stagiaireNouveau, raf);
				
			}
	
	}

		
	

	

	

	public Noeud supprimerNoeud(Noeud stagiaireSuppr, RandomAccessFile raf, int indexParent) throws IOException {

		try {
			if (stagiaireSuppr.getFilsGauche() == FILS_NUL && stagiaireSuppr.getFilsDroit()== FILS_NUL) { // s'il s'agit d'une feuille 
				
				 raf.seek(indexParent*TAILLE_TOTALE_NOEUD_OCTETS); // chercher l'index du parent ????
				
				Noeud noeudParent = lireStagiaire (raf); // on lit le noeud parent après s'être déplacé au niveau de son index
				
				if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim())>0){  // on compare les valeurs des noms 
					
					raf.seek(raf.getFilePointer()-12); // se déplacer au niveau du fils gauche 
					
					raf.writeInt (-1);// déclarer le fils gauche comme nul 
					
				} else if (noeudParent.getCle().getNom().trim().compareTo(stagiaireSuppr.getCle().getNom().trim()) < 0){
					
					raf.seek(raf.getFilePointer()-8); // se déplacer au niveau du fils droit 
					
					raf.writeInt (-1);// le déclarer nul
					
				} 
					
				
			} else if ((stagiaireSuppr.getFilsGauche () == FILS_NUL) && (stagiaireSuppr.getFilsDroit() != FILS_NUL)){
				
			
					
				}
		
	

	

	
		}
	
	


