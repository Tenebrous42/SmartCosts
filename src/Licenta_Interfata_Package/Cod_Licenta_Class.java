package Licenta_Interfata_Package;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Cod_Licenta_Class {
	static boolean depozit_fictiv = false, centru_fictiv = false, problema_echilibrata = false, ciclu_complet = false;
	static int nr_schimbari_baza = 1;

	public static void Afisare_matrice(double[][] a, int nr_linii, int nr_coloane, int sol_optim)
	{
		if(sol_optim == 1)
		{
			for(int i = 0; i < nr_linii; i++)
			{
				for(int j = 0; j < nr_coloane; j++)
					System.out.printf("%.0f ", a[i][j]);

				System.out.println("");
			}
		}
		else
		{	
			for(int i = 0; i < nr_linii; i++)
			{
				for(int j = 0; j < nr_coloane; j++)
					System.out.printf("%.1f ", a[i][j]);

				System.out.println("");
			}			

		}

	}

	public static double Coloana_goala(double[][] matrice_finala, double[][] matrice_finala_costuri, int indice_linie_poz_init, int indice_coloana_poz_init)
	{
		for(int i = 0; i < matrice_finala_costuri.length; i++)
		{
			if(matrice_finala[i][indice_coloana_poz_init] != -1 && i != indice_linie_poz_init)
			{
				return 0;
			}
		}

		return 1;
	}

	public static double Linie_goala(double[][] matrice_finala, double[][] matrice_finala_costuri, int indice_linie_poz_init, int indice_coloana_poz_init)
	{
		for(int j = 0; j < matrice_finala_costuri[0].length; j++)
		{
			if(matrice_finala[indice_linie_poz_init][j] != -1 && j != indice_coloana_poz_init)
			{
				return 0;
			}
		}

		return 1;
	}

	public static double Uita_te_in_stanga(double[][] matrice_finala, double[][] matrice_finala_costuri, int indice_linie_poz_init, int indice_coloana_poz_init,
			int indice_linie_delta, int indice_coloana_delta, double[][] matrice_ciclu, double[][] matrice_pozitie_in_ciclu, int pozitie_in_ciclu)
	{	
		//System.out.println("Intram in functia de uita-te in stanga");
		double rezultat_functie = -1;

		if(ciclu_complet == false)
		{
			int[] vector_indici_coloana_gasiti = new int[matrice_finala_costuri[0].length];

			///Setam vectorul cu -1 pentru a nu avea din indexul 0 din greseala atunci cand nu e cazul
			for(int k = 0; k < vector_indici_coloana_gasiti.length; k++)
				vector_indici_coloana_gasiti[k] = -1;


			int nr_index_gasit = 0;
			///Mai intai gasesc toti indecsii de coloana la stanga de pozitia initiala primita care sunt bazice
			for(int j = indice_coloana_poz_init - 1; j >= 0; j--)
			{
				if(matrice_finala[indice_linie_poz_init][j] != -1)
				{
					vector_indici_coloana_gasiti[nr_index_gasit] = j;
					nr_index_gasit++;
				}
			}

			if(nr_index_gasit == 0)
			{
				rezultat_functie = -3;
				//System.out.println("Nu am gasit nimic in stanga");

			}
			else
			{
				///Verific daca am gasit indexul de coloana final ale ciclului
				for(int k = 0; k < vector_indici_coloana_gasiti.length; k++)
					if(vector_indici_coloana_gasiti[k] == indice_coloana_delta)
					{
						matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = matrice_finala_costuri[indice_linie_poz_init][vector_indici_coloana_gasiti[k]];
						matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = pozitie_in_ciclu;
						ciclu_complet = true;
						break;
					}


				///La indecsii de coloana pe care i-am gasit (daca nu sunt ultimii din ciclu), ma uit in sus si in jos sa vad daca au o componenta bazica
				if(ciclu_complet == false)
				{
					boolean solutie_viabila = true; 

					for(int k = 0; k < vector_indici_coloana_gasiti.length; k++)
						if(vector_indici_coloana_gasiti[k] != -1 && ciclu_complet == false)
						{
							solutie_viabila = true; 
							matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = matrice_finala_costuri[indice_linie_poz_init][vector_indici_coloana_gasiti[k]];
							matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = pozitie_in_ciclu;
							pozitie_in_ciclu++;

							///Incepem verificarile sa vedem daca am obtinut o pozitie viabila de unde sa cautam mai departe
							///Verificarea 1: daca a ajuns pe o coloana goala care nu este ultima a ciclului
							if(Coloana_goala(matrice_finala, matrice_finala_costuri, indice_linie_poz_init, vector_indici_coloana_gasiti[k]) == 1)
							{
								if(vector_indici_coloana_gasiti[k] != indice_coloana_delta)
								{
									solutie_viabila = false;
									matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = -1;
									matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = 0;
									pozitie_in_ciclu--;

								}
								else
								{
									break;
								}

							}

							if(solutie_viabila == true && ciclu_complet == false)
							{
								Uita_te_in_sus(matrice_finala, matrice_finala_costuri, indice_linie_poz_init, vector_indici_coloana_gasiti[k],
										indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

								if(ciclu_complet == false)
								{
									Uita_te_in_jos(matrice_finala, matrice_finala_costuri, indice_linie_poz_init, vector_indici_coloana_gasiti[k],
											indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

									if(ciclu_complet == false)
									{
										solutie_viabila = false;
										matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = -1;
										matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = 0;
										pozitie_in_ciclu--;

									}
								}

							}


						}

				}
			}


		}

		return rezultat_functie;
	}


	public static double Uita_te_in_sus(double[][] matrice_finala, double[][] matrice_finala_costuri, int indice_linie_poz_init, int indice_coloana_poz_init,
			int indice_linie_delta, int indice_coloana_delta, double[][] matrice_ciclu, double[][] matrice_pozitie_in_ciclu, int pozitie_in_ciclu)
	{
		//System.out.println("Intram in functia de uita-te in sus");

		double rezultat_functie = -1;

		if(ciclu_complet == false)
		{
			int[] vector_indici_linie_gasiti = new int[matrice_finala_costuri.length];

			///Setam vectorul cu -1 pentru a nu avea din indexul 0 din greseala atunci cand nu e cazul
			for(int k = 0; k < vector_indici_linie_gasiti.length; k++)
				vector_indici_linie_gasiti[k] = -1;


			int nr_index_gasit = 0;
			///Mai intai gasesc toti indecsii de linie de pe coloana in sus de pozitia initiala primita care sunt bazice
			for(int i = indice_linie_poz_init - 1; i >= 0; i--)
			{
				if(matrice_finala[i][indice_coloana_poz_init] != -1)
				{
					vector_indici_linie_gasiti[nr_index_gasit] = i;
					nr_index_gasit++;
				}
			}

			if(nr_index_gasit == 0)
			{
				rezultat_functie = -3;
				//System.out.println("Nu am gasit nimic in sus");

			}
			else
			{
				///Verific daca am gasit indexul de linie final al ciclului

				for(int k = 0; k < vector_indici_linie_gasiti.length; k++)
					if(vector_indici_linie_gasiti[k] == indice_linie_delta)
					{
						matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = matrice_finala_costuri[vector_indici_linie_gasiti[k]][indice_coloana_poz_init];
						matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = pozitie_in_ciclu;
						ciclu_complet = true;
						break;
					}


				///La indecsii de linie pe care i-am gasit (daca nu sunt ultimii din ciclu), ma uit in stanga si in dreapta sa vad daca au o componenta bazica
				if(ciclu_complet == false)
				{
					boolean solutie_viabila = true;

					for(int k = 0; k < vector_indici_linie_gasiti.length; k++)
						if(vector_indici_linie_gasiti[k] != -1 && ciclu_complet == false)
						{
							solutie_viabila = true;
							matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = matrice_finala_costuri[vector_indici_linie_gasiti[k]][indice_coloana_poz_init];
							matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = pozitie_in_ciclu;
							pozitie_in_ciclu++;

							///Incepem verificarile sa vedem daca am obtinut o pozitie viabila de unde sa cautam mai departe
							///Verificarea 1: daca a ajuns pe o linie goala care nu este ultima a ciclului

							if(Linie_goala(matrice_finala, matrice_finala_costuri, vector_indici_linie_gasiti[k], indice_coloana_poz_init) == 1)
							{
								if(vector_indici_linie_gasiti[k] != indice_linie_delta)
								{
									solutie_viabila = false;
									matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = -1;
									matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = 0;
									pozitie_in_ciclu--;
								}
								else
								{
									break;
								}
							}

							if(solutie_viabila == true && ciclu_complet == false)
							{
								Uita_te_in_stanga(matrice_finala, matrice_finala_costuri, vector_indici_linie_gasiti[k], indice_coloana_poz_init, 
										indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

								if(ciclu_complet == false)
								{
									Uita_te_in_dreapta(matrice_finala, matrice_finala_costuri, vector_indici_linie_gasiti[k], indice_coloana_poz_init, 
											indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

									if(ciclu_complet == false)
									{
										solutie_viabila = false;
										matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = -1;
										matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = 0;
										pozitie_in_ciclu--;
									}
								}

							}


						}

				}

			}


		}
		return rezultat_functie;
	}

	public static double Uita_te_in_dreapta(double[][] matrice_finala, double[][] matrice_finala_costuri, int indice_linie_poz_init, int indice_coloana_poz_init,
			int indice_linie_delta, int indice_coloana_delta, double[][] matrice_ciclu, double[][] matrice_pozitie_in_ciclu, int pozitie_in_ciclu)
	{
		//System.out.println("Intram in functia de uita-te in dreapta");

		double rezultat_functie = -1;

		if(ciclu_complet == false)
		{

			int[] vector_indici_coloana_gasiti = new int[matrice_finala_costuri[0].length];
			///Setam vectorul cu -1 pentru a nu avea din indexul 0 din greseala atunci cand nu e cazul
			for(int k = 0; k < vector_indici_coloana_gasiti.length; k++)
				vector_indici_coloana_gasiti[k] = -1;


			int nr_index_gasit = 0;
			///Mai intai gasesc toti indecsii de coloana la dreapta de pozitia initiala primita care sunt bazice
			for(int j = indice_coloana_poz_init + 1; j < matrice_finala_costuri[0].length; j++)
			{
				if(matrice_finala[indice_linie_poz_init][j] != -1)
				{
					vector_indici_coloana_gasiti[nr_index_gasit] = j;
					nr_index_gasit++;
				}
			}


			if(nr_index_gasit == 0)
			{
				rezultat_functie = -3;
				//System.out.println("Nu am gasit nimic in dreapta");

			}
			else
			{
				///Verific daca am gasit indexul de coloana final ale ciclului
				for(int k = 0; k < vector_indici_coloana_gasiti.length; k++)
					if(vector_indici_coloana_gasiti[k] == indice_coloana_delta)
					{
						matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = matrice_finala_costuri[indice_linie_poz_init][vector_indici_coloana_gasiti[k]];
						matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = pozitie_in_ciclu;
						ciclu_complet = true;
						break;
					}


				///La indecsii de coloana pe care i-am gasit (daca nu sunt ultimii din ciclu), ma uit in sus si in jos sa vad daca au o componenta bazica

				if(ciclu_complet == false)
				{
					boolean solutie_viabila = true;

					for(int k = 0; k < vector_indici_coloana_gasiti.length; k++)
						if(vector_indici_coloana_gasiti[k] != -1 && ciclu_complet == false)
						{
							solutie_viabila = true;
							matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = matrice_finala_costuri[indice_linie_poz_init][vector_indici_coloana_gasiti[k]];
							matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = pozitie_in_ciclu;
							pozitie_in_ciclu++;

							///Incepem verificarile sa vedem daca am obtinut o pozitie viabila de unde sa cautam mai departe
							///Verificarea 1: daca a ajuns pe o coloana goala care nu este ultima a ciclului
							if(Coloana_goala(matrice_finala, matrice_finala_costuri, indice_linie_poz_init, vector_indici_coloana_gasiti[k]) == 1)
							{
								if(vector_indici_coloana_gasiti[k] != indice_coloana_delta)
								{
									solutie_viabila = false;
									matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = -1;
									matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = 0;
									pozitie_in_ciclu--;
								}
								else
								{
									break;
								}
							}

							if(solutie_viabila == true && ciclu_complet == false)
							{

								Uita_te_in_sus(matrice_finala, matrice_finala_costuri, indice_linie_poz_init, vector_indici_coloana_gasiti[k],
										indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

								if(ciclu_complet == false)
								{
									Uita_te_in_jos(matrice_finala, matrice_finala_costuri, indice_linie_poz_init, vector_indici_coloana_gasiti[k],
											indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

									if(ciclu_complet == false)
									{
										solutie_viabila = false;
										matrice_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = -1;
										matrice_pozitie_in_ciclu[indice_linie_poz_init][vector_indici_coloana_gasiti[k]] = 0;
										pozitie_in_ciclu--;
									}
								}
							}



						}

				}

			}


		}
		return rezultat_functie;
	}


	public static double Uita_te_in_jos(double[][] matrice_finala, double[][] matrice_finala_costuri, int indice_linie_poz_init, int indice_coloana_poz_init,
			int indice_linie_delta, int indice_coloana_delta, double[][] matrice_ciclu, double[][] matrice_pozitie_in_ciclu, int pozitie_in_ciclu)
	{
		//System.out.println("Intram in functia de uita-te in jos");

		double rezultat_functie = -1;

		if(ciclu_complet == false)
		{
			int[] vector_indici_linie_gasiti = new int[matrice_finala_costuri.length];

			///Setam vectorul cu -1 pentru a nu avea din indexul 0 din greseala atunci cand nu e cazul
			for(int k = 0; k < vector_indici_linie_gasiti.length; k++)
				vector_indici_linie_gasiti[k] = -1;


			int nr_index_gasit = 0;
			///Mai intai gasesc toti indecsii de linie de pe coloana in jos de pozitia initiala primita care sunt bazice
			for(int i = indice_linie_poz_init + 1; i < matrice_finala_costuri.length; i++)
			{
				if(matrice_finala[i][indice_coloana_poz_init] != -1)
				{
					vector_indici_linie_gasiti[nr_index_gasit] = i;
					nr_index_gasit++;
				}
			}

			if(nr_index_gasit == 0)
			{
				rezultat_functie = -3;
				//System.out.println("Nu am gasit nimic in jos");

			}
			else
			{
				///Verific daca am gasit indexul de linie final al ciclului

				for(int k = 0; k < vector_indici_linie_gasiti.length; k++)
					if(vector_indici_linie_gasiti[k] == indice_linie_delta)
					{
						matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = matrice_finala_costuri[vector_indici_linie_gasiti[k]][indice_coloana_poz_init];
						matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = pozitie_in_ciclu;
						ciclu_complet = true;
						break;
					}


				///La indecsii de linie pe care i-am gasit (daca nu sunt ultimii din ciclu), ma uit in stanga si in dreapta sa vad daca au o componenta bazica

				if(ciclu_complet == false)
				{
					boolean solutie_viabila = true;

					for(int k = 0; k < vector_indici_linie_gasiti.length; k++)
						if(vector_indici_linie_gasiti[k] != -1 && ciclu_complet == false)
						{
							solutie_viabila = true;
							matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = matrice_finala_costuri[vector_indici_linie_gasiti[k]][indice_coloana_poz_init];
							matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = pozitie_in_ciclu;
							pozitie_in_ciclu++;

							///Incepem verificarile sa vedem daca am obtinut o pozitie viabila de unde sa cautam mai departe
							///Verificarea 1: daca a ajuns pe o linie goala care nu este ultima a ciclului			

							if(Linie_goala(matrice_finala, matrice_finala_costuri, vector_indici_linie_gasiti[k], indice_coloana_poz_init) == 1)
							{
								if(vector_indici_linie_gasiti[k] != indice_linie_delta) 
								{
									solutie_viabila = false;
									matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = -1;	
									matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = 0;
									pozitie_in_ciclu--;
								}
								else
								{
									break;
								}
							}


							if(solutie_viabila == true && ciclu_complet == false)
							{
								Uita_te_in_stanga(matrice_finala, matrice_finala_costuri, vector_indici_linie_gasiti[k], indice_coloana_poz_init, 
										indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

								if(ciclu_complet == false)
								{
									Uita_te_in_dreapta(matrice_finala, matrice_finala_costuri, vector_indici_linie_gasiti[k], indice_coloana_poz_init, 
											indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

									if(ciclu_complet == false)
									{
										solutie_viabila = false;
										matrice_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = -1;	
										matrice_pozitie_in_ciclu[vector_indici_linie_gasiti[k]][indice_coloana_poz_init] = 0;
										pozitie_in_ciclu--;
									}
								}
							}


						}

				}

			}



		}
		return rezultat_functie;
	}

	public static double Calculare_delta(double[][] matrice_finala, double[][] matrice_finala_costuri, 
			int indice_linie_delta, int indice_coloana_delta)
	{
		///A) Parcurg matricea: gasesc o var secundara DONE
		///B) Intr-o noua matrice ii desenez ciclul, umpland matricea_ciclu cu valoarea costului acelei celule
		///C) Pe masura ce umplu matrice_ciclu, umplu matrice_pozitie_in_ciclu cu pozitia in ciclu a celulei gasite
		///D) Parcurgand in ordine celulele ciclului si * -1 si adunandu-le in delta[k_Delta]
		///E) Golesc matrice_ciclu si matrice_pozitie_in_ciclu pt a fi gata pt urmatoarea
		///F) In final, verific vectorul delta pt a vedea daca e optima solutia

		double valoare_delta = 0;
		double[][] matrice_ciclu = new double[matrice_finala_costuri.length][matrice_finala_costuri[0].length];
		double[][] matrice_pozitie_in_ciclu = new double[matrice_finala_costuri.length][matrice_finala_costuri[0].length];
		int pozitie_in_ciclu = 1;

		//Setam matricea_ciclu cu -1 ca sa putem acoperi si cazul cand costul gasit este == 0
		for(int i = 0 ; i < matrice_ciclu.length; i++)
			for(int j = 0; j < matrice_ciclu[0].length; j++)
				matrice_ciclu[i][j] = -1;

		matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
		matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
		pozitie_in_ciclu++;

		//matrice
		///ma uit in stanga
		///daca am gasit o pozitie bazica, ma uit in sus si in jos



		///ma uit in sus
		///daca am gasit o pozitie bazica, ma uit in stanga si in dreapta



		///ma uit in dreapta
		///daca am gasit o pozitie bazica, ma uit in sus si in jos



		///ma uit in jos
		///daca am gasit o pozitie bazica, ma uit in stanga si in dreapta



		///cand linia sau coloana rezultata din functii este egala cu linia sau coloana pozitiei mele delta, am completat circuitul curent


		Uita_te_in_stanga(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta,
				indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

		///TEST
		System.out.println("am terminat functia de uita-te in stanga initial");

		if(ciclu_complet == false)
		{
			///Daca am ajuns aici, inseamna ca nu am gasit ciclul incepand sa ma uit in stanga,
			///asa ca golim matricea ciclu, il punem pe delta in matrice_ciclu si incep sa ma uit in sus

			for(int i = 0 ; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
				{
					matrice_ciclu[i][j] = -1;
					matrice_pozitie_in_ciclu[i][j] = 0;
				}

			pozitie_in_ciclu = 1;
			matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
			matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
			pozitie_in_ciclu++;



			Uita_te_in_sus(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta,
					indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);
			///TEST
			System.out.println("am terminat functia de uita-te in sus initial");
		}



		if(ciclu_complet == false)
		{
			///Daca am ajuns aici, inseamna ca nu am gasit ciclul incepand sa ma uit in stanga si nici in sus,
			///asa ca golim matricea ciclu, il punem pe delta in matrice_ciclu si incep sa ma uit in dreapta

			for(int i = 0 ; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
				{
					matrice_ciclu[i][j] = -1;
					matrice_pozitie_in_ciclu[i][j] = 0;
				}

			pozitie_in_ciclu = 1;
			matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
			matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
			pozitie_in_ciclu++;


			Uita_te_in_dreapta(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta,
					indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);
			///TEST
			System.out.println("am terminat functia de uita-te in dreapta initial");
		}


		if(ciclu_complet == false)
		{
			///Daca am ajuns aici, inseamna ca nu am gasit ciclul incepand sa ma uit in stanga, in sus si nici in dreapta
			///asa ca golim matricea ciclu, il punem pe delta in matrice_ciclu si incep sa ma uit in jos

			for(int i = 0 ; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
				{
					matrice_ciclu[i][j] = -1;
					matrice_pozitie_in_ciclu[i][j] = 0;
				}

			pozitie_in_ciclu = 1;
			matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
			matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
			pozitie_in_ciclu++;


			Uita_te_in_jos(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta, 
					indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);
			///TEST
			System.out.println("am terminat functia de uita-te in jos initial");
		}


		Afisare_matrice(matrice_ciclu, matrice_ciclu.length, matrice_ciclu[0].length, 0);
		System.out.println("S-au gasit " + pozitie_in_ciclu + " celule in ciclu.");
		Afisare_matrice(matrice_pozitie_in_ciclu, matrice_pozitie_in_ciclu.length, matrice_pozitie_in_ciclu[0].length, 0);

		///In punctul asta avem matrice_ciclu si matrice_pozitie_in_ciclu pentru delta-ul nostru. De verificat in detaliu
		///Calculam valoarea deltei folosind simbolurile alternante

		//Aflam nr de celule din ciclu
		int nr_celule = 1;
		for(int i = 0 ; i < matrice_pozitie_in_ciclu.length; i++)
			for(int j = 0; j < matrice_pozitie_in_ciclu[0].length; j++)
			{
				if(matrice_pozitie_in_ciclu[i][j] > nr_celule)
				{
					nr_celule =(int) matrice_pozitie_in_ciclu[i][j];
				}
			}


		int index = 1;
		boolean am_gasit_indexul = false;
		//System.out.println("S-au gasit " + nr_celule + " celule in ciclu.");

		while(index <= nr_celule)
		{
			//	System.out.println("Val index: " + index);
			am_gasit_indexul = false;


			for(int i = 0; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
					if(matrice_pozitie_in_ciclu[i][j] == index && am_gasit_indexul == false)
					{
						if(index%2!=0)
						{
							valoare_delta = valoare_delta + (matrice_ciclu[i][j] * (-1));
							am_gasit_indexul = true;
						}
						else
						{
							valoare_delta = valoare_delta + matrice_ciclu[i][j];
							am_gasit_indexul = true;
						}
					}

			index++;
		}


		return valoare_delta;	
	}


	public static void Matricea_noua(double[][] matrice_finala, double[][] matrice_finala_costuri, 
			int indice_linie_delta, int indice_coloana_delta)
	{
		double[][] matrice_ciclu = new double[matrice_finala_costuri.length][matrice_finala_costuri[0].length];
		double[][] matrice_pozitie_in_ciclu = new double[matrice_finala_costuri.length][matrice_finala_costuri[0].length];
		int pozitie_in_ciclu = 1;

		//Setam matricea_ciclu cu -1 ca sa putem acoperi si cazul cand costul gasit este == 0
		for(int i = 0 ; i < matrice_ciclu.length; i++)
			for(int j = 0; j < matrice_ciclu[0].length; j++)
				matrice_ciclu[i][j] = -1;

		matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
		matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
		pozitie_in_ciclu++;


		Uita_te_in_stanga(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta,
				indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);

		///TEST
		System.out.println("am terminat functia de uita-te in stanga initial");

		if(ciclu_complet == false)
		{
			///Daca am ajuns aici, inseamna ca nu am gasit ciclul incepand sa ma uit in stanga,
			///asa ca golim matricea ciclu, il punem pe delta in matrice_ciclu si incep sa ma uit in sus

			for(int i = 0 ; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
				{
					matrice_ciclu[i][j] = -1;
					matrice_pozitie_in_ciclu[i][j] = 0;
				}

			pozitie_in_ciclu = 1;
			matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
			matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
			pozitie_in_ciclu++;



			Uita_te_in_sus(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta,
					indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);
			///TEST
			System.out.println("am terminat functia de uita-te in sus initial");
		}


		if(ciclu_complet == false)
		{
			///Daca am ajuns aici, inseamna ca nu am gasit ciclul incepand sa ma uit in stanga si nici in sus,
			///asa ca golim matricea ciclu, il punem pe delta in matrice_ciclu si incep sa ma uit in dreapta

			for(int i = 0 ; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
				{
					matrice_ciclu[i][j] = -1;
					matrice_pozitie_in_ciclu[i][j] = 0;
				}

			pozitie_in_ciclu = 1;
			matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
			matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
			pozitie_in_ciclu++;


			Uita_te_in_dreapta(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta,
					indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);
			///TEST
			System.out.println("am terminat functia de uita-te in dreapta initial");
		}


		if(ciclu_complet == false)
		{
			///Daca am ajuns aici, inseamna ca nu am gasit ciclul incepand sa ma uit in stanga, in sus si nici in dreapta
			///asa ca golim matricea ciclu, il punem pe delta in matrice_ciclu si incep sa ma uit in jos

			for(int i = 0 ; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
				{
					matrice_ciclu[i][j] = -1;
					matrice_pozitie_in_ciclu[i][j] = 0;
				}

			pozitie_in_ciclu = 1;
			matrice_ciclu[indice_linie_delta][indice_coloana_delta] = matrice_finala_costuri[indice_linie_delta][indice_coloana_delta];
			matrice_pozitie_in_ciclu[indice_linie_delta][indice_coloana_delta] = pozitie_in_ciclu;
			pozitie_in_ciclu++;


			Uita_te_in_jos(matrice_finala, matrice_finala_costuri, indice_linie_delta, indice_coloana_delta, 
					indice_linie_delta, indice_coloana_delta, matrice_ciclu, matrice_pozitie_in_ciclu, pozitie_in_ciclu);
			///TEST
			System.out.println("am terminat functia de uita-te in jos initial");
		}


		//Aflam nr de celule din ciclu
		int nr_celule = 1;
		for(int i = 0 ; i < matrice_pozitie_in_ciclu.length; i++)
			for(int j = 0; j < matrice_pozitie_in_ciclu[0].length; j++)
			{
				if(matrice_pozitie_in_ciclu[i][j] > nr_celule)
				{
					nr_celule =(int) matrice_pozitie_in_ciclu[i][j];
				}
			}



		System.out.println("Nr de celule din ciclu al deltei maxime:" + nr_celule);
		System.out.println("Matricea ciclu a deltei maxime:");
		Afisare_matrice(matrice_ciclu, matrice_ciclu.length, matrice_ciclu[0].length, 0);
		System.out.println("Matricea pozitie in ciclu a deltei maxime:");
		Afisare_matrice(matrice_pozitie_in_ciclu, matrice_pozitie_in_ciclu.length, matrice_pozitie_in_ciclu[0].length, 0);



		///Mai intai aflu valoarea minima din celulele pare ale ciclului
		int min_indice_linie = 0, min_indice_coloana = 0;
		double teta = 999999999;
		int index = 2;
		boolean am_gasit_indexul = false;

		while(index <= nr_celule)
		{
			System.out.println("Val index: " + index);
			am_gasit_indexul = false;

			for(int i = 0; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
					if(matrice_pozitie_in_ciclu[i][j] == index && am_gasit_indexul == false)
					{
						if(index%2==0)
						{
							if(matrice_finala[i][j] < teta)
							{
								teta = matrice_finala[i][j];
								min_indice_linie = i;
								min_indice_coloana = j;
								am_gasit_indexul = true;

							}

						}

					}
			index = index + 2;
		}


		///calculez noul tabel folosid acea minima valoare

		index = 1;	

		while(index <= nr_celule)
		{
			System.out.println("Val index: " + index);
			am_gasit_indexul = false;

			for(int i = 0; i < matrice_ciclu.length; i++)
				for(int j = 0; j < matrice_ciclu[0].length; j++)
					if(matrice_pozitie_in_ciclu[i][j] == index && am_gasit_indexul == false)
					{
						if(index%2!=0)
						{
							if(index == 1)
							{
								matrice_finala[i][j] = teta;
							}
							else
							{
								matrice_finala[i][j] = matrice_finala[i][j] + teta;
							}

							am_gasit_indexul = true;
						}
						else
						{
							if( i == min_indice_linie && j == min_indice_coloana)
							{
								matrice_finala[i][j] = -1;
							}
							else
							{
								matrice_finala[i][j] = matrice_finala[i][j] - teta;
							}
							am_gasit_indexul = true;
						}

					}

			index++;
		}


		System.out.println("Matricea noua construita:");
		Afisare_matrice(matrice_finala, matrice_finala.length, matrice_finala[0].length, 0);
		
		//am ramas aici
		//Cost_total_solutie_curenta(matrice_finala_costuri, matrice_finala_costuri);
	}



	public static double[][] Obtinere_matrice_finala(double[][]a, double cererea, double oferta, int m, int n)
	{
		double[][] matrice_gunoi_nereturnabila = {{1},{2}};
		double epsilon = 0.1;

		if(cererea > oferta)
		{
			System.out.println("Cererea: " + cererea + " este mai mare decat oferta: " + oferta + ". Introducem un depozit fictiv si perturbam problema.");

			depozit_fictiv = true;
			double[][] matrice_transport_principala_depozit_fictiv = new double[m+2][n+1];
			matrice_transport_principala_depozit_fictiv[m][n] = cererea - oferta;

			///Umplu valorile ofertei in matricea finala si dupa le perturb
			for(int i = 0; i < m; i++)
				matrice_transport_principala_depozit_fictiv[i][n] = a[i][n];
			for(int i = 0; i < m + 1; i++)
				matrice_transport_principala_depozit_fictiv[i][n] = matrice_transport_principala_depozit_fictiv[i][n] + epsilon;


			///Umplu valorile cererii in matricea finala si dupa perturb ultimul centru
			for(int j = 0; j < n; j++)
				matrice_transport_principala_depozit_fictiv[m+1][j] = a[m][j];

			matrice_transport_principala_depozit_fictiv[m+1][n-1] = matrice_transport_principala_depozit_fictiv[m+1][n-1] + ((m + 1)*epsilon);

			return matrice_transport_principala_depozit_fictiv;

		}

		if(cererea < oferta)
		{
			System.out.println("Cererea: " + cererea + " este mai mica decat oferta: " + oferta + ". Introducem un centru fictiv si perturbam problema.");

			centru_fictiv = true;
			double[][] matrice_transport_principala_centru_fictiv = new double[m+1][n+2];
			matrice_transport_principala_centru_fictiv[m][n] = oferta - cererea;


			///Umplu valorile ofertei in matricea finala si dupa le perturb
			for(int i = 0; i < m; i++)
				matrice_transport_principala_centru_fictiv[i][n+1] = a[i][n];

			for(int i = 0; i < m; i++)
				matrice_transport_principala_centru_fictiv[i][n+1] = matrice_transport_principala_centru_fictiv[i][n+1] + epsilon;




			///Umplu valorile cererii in matricea finala si dupa perturb ultimul centru
			for(int j = 0; j < n; j++)
				matrice_transport_principala_centru_fictiv[m][j] = a[m][j];

			matrice_transport_principala_centru_fictiv[m][n] = matrice_transport_principala_centru_fictiv[m][n] + (m *epsilon);

			return matrice_transport_principala_centru_fictiv;
		}

		if(cererea == oferta)
		{
			System.out.println("Cererea: " + cererea + " este = cu oferta: " + oferta + ". Problema echilibrata. O perturbam.");

			problema_echilibrata = true;

			///Perturb oferta problemei si pe urma cererea ultimului centru
			for(int i = 0; i < m; i++)
				a[i][n] = a[i][n] + epsilon;

			a[m][n-1] = a[m][n-1] + m*epsilon;


			return a;
		}

		return matrice_gunoi_nereturnabila;
	}



	public static double[][] Obtinere_matrice_finala_costuri(double[][]a, double cererea, double oferta, int m, int n)
	{
		double[][] matrice_gunoi_nereturnabila = {{1},{2}};

		if(cererea > oferta)
		{
			double[][] matrice_costuri_transport_principala_depozit_fictiv = new double[m+1][n];

			for(int i = 0; i < m; i++)
				for(int j = 0; j < n; j++)
					matrice_costuri_transport_principala_depozit_fictiv[i][j] = a[i][j];

			return matrice_costuri_transport_principala_depozit_fictiv;
		}

		if(cererea < oferta)
		{
			double[][] matrice_costuri_transport_principala_centru_fictiv = new double[m][n+1];
			for(int i = 0; i < m; i++)
				for(int j = 0; j < n; j++)
					matrice_costuri_transport_principala_centru_fictiv[i][j] = a[i][j];

			return matrice_costuri_transport_principala_centru_fictiv;
		}

		if(cererea == oferta)
		{
			return a;
		}

		return matrice_gunoi_nereturnabila;
	}



	public static boolean Validare_depozite_magazine(int m, int n)
	{
		if(m <= 0 || n <= 0)
		{
			System.out.println("Date negative sau 0 pt depozite sau magazine!");
			return false;
		}
		///if(n sau m nu sunt nr naturale avem eroare)

		return true;

	}

	public static boolean Validare_costuri_initiale(double[][] v, int nr_linii, int nr_coloane)
	{
		for (int i = 0; i < nr_linii; i++)
			for (int j = 0; j < nr_coloane; j++)
				if(v[i][j] <= 0)
					return false;

		return true;
	}

	
	public static double Cost_total_solutie_curenta(double[][] matrice_finala_costuri, double[][] matrice_finala)
	{
		double cost_total = 0;
		for(int i = 0; i < matrice_finala_costuri.length; i++)
			for(int j = 0; j < matrice_finala_costuri[0].length; j++)
				if(matrice_finala[i][j] != -1 && matrice_finala[i][j] != -2)
				{
					cost_total = cost_total + matrice_finala[i][j] * matrice_finala_costuri[i][j];
				}
		
		return cost_total;
	}
	
	public static void main() 
	{
		int nr_depozite = Frame_dos.nr_depozite, nr_magazine = Frame_dos.nr_magazine;

		double[][] matrice_initiala = new double[nr_depozite+1][nr_magazine+1];
		double[][] matrice_initiala_costuri = new double[nr_depozite][nr_magazine];

		String forma_costuri_unitare = "";
		//System.out.println("Introduceti unitatea monetara a costurilor (in ce sunt ele exprimate): ");
		//forma_costuri_unitare = sc.next();
		forma_costuri_unitare = Frame_dos.unitate_monetara_costuri;

		//System.out.println("Introduceti costurile unitare:");
		for(int i = 0; i < nr_depozite; i++)
			for(int j = 0; j < nr_magazine; j++)
			{
				//to remove
				matrice_initiala_costuri[i][j] = Double.parseDouble((String) Frame_tres.table_costs.getValueAt(i, j));
				System.out.println("Cod licenta valoare cost: " + matrice_initiala_costuri[i][j]);
			}

		if(Validare_costuri_initiale(matrice_initiala_costuri, nr_depozite, nr_magazine) == false)
		{
			System.out.println("Costurile initiale nu pot fi <= 0 !");
		//	sc.close();
			return ;
		}


		double oferta = 0;
	//	System.out.println("Introduceti cantitatile din depozite:");
		for(int i = 0; i < nr_depozite; i++)
		{
			
			matrice_initiala[i][nr_magazine] = Double.parseDouble((String) Frame_patru.table_depozite_nou.getValueAt(i, 1));
			if(matrice_initiala[i][nr_magazine] <= 0)
			{
				System.out.println("Valori incorecte pentru cantitatile din depozite !");
		//		sc.close();
				return ;
			}
			oferta = oferta + matrice_initiala[i][nr_magazine];
		}


		double cererea = 0;
		System.out.println("Introduceti cantitatile cerute de magazine:");
		for(int j = 0; j < nr_magazine; j++)
		{
			matrice_initiala[nr_depozite][j] = Double.parseDouble((String) Frame_5.table_magazine.getValueAt(j, 1));
			if(matrice_initiala[nr_depozite][j] <= 0)
			{
				System.out.println("Valori incorecte pentru cantitatile cerute de magazine!");
		//		sc.close();
				return ;
			}
			cererea = cererea + matrice_initiala[nr_depozite][j];
		}
		//sc.close();


		///PAS 0: VERIFIC DACA C = O SI PERTURB PROBLEMA

		double[][] matrice_finala =  Obtinere_matrice_finala(matrice_initiala, cererea, oferta, nr_depozite, nr_magazine);
		double[][] matrice_finala_costuri =  Obtinere_matrice_finala_costuri(matrice_initiala_costuri, cererea, oferta, nr_depozite ,nr_magazine);

		System.out.println("Matricea finala:");
		Afisare_matrice(matrice_finala, matrice_finala.length, matrice_finala[0].length, 0);
		System.out.println("Matricea finala a costurilor:");
		Afisare_matrice(matrice_finala_costuri, matrice_finala_costuri.length, matrice_finala_costuri[0].length, 0);


		///PAS 1: METODA COSTURILOR MINIME: AFLU SOLUTIA INITIALA SBAi

		double cost_minim = matrice_finala_costuri[0][0];
		boolean matrice_plina = false;

		while(matrice_plina == false) 
		{
			cost_minim = 9999999;

			for(int i = 0; i < matrice_finala_costuri.length; i++)
				for(int j = 0; j < matrice_finala_costuri[0].length; j++)
					if(cost_minim > matrice_finala_costuri[i][j] && matrice_finala[i][j] == 0)
						cost_minim = matrice_finala_costuri[i][j];
			System.out.println("Cost minim gasit "+ cost_minim);


			for(int i = 0; i < matrice_finala_costuri.length; i++)
				for(int j = 0; j < matrice_finala_costuri[0].length; j++)
					if(cost_minim == matrice_finala_costuri[i][j] && matrice_finala[i][j] == 0)
					{
						if(matrice_finala[i][matrice_finala[0].length - 1] < matrice_finala[matrice_finala.length - 1][j])
						{
							matrice_finala[i][j] = matrice_finala[i][matrice_finala[0].length - 1];
							matrice_finala[i][matrice_finala[0].length - 1] = matrice_finala[i][matrice_finala[0].length - 1] - matrice_finala[i][j];
							matrice_finala[matrice_finala.length - 1][j] = matrice_finala[matrice_finala.length - 1][j] - matrice_finala[i][j];

							///Fac linia * (-1)
							for(int k = 0; k < matrice_finala_costuri[0].length; k++)
								if(matrice_finala[i][k] == 0)
									matrice_finala[i][k] = -1;
						}

						if(matrice_finala[i][matrice_finala[0].length - 1] > matrice_finala[matrice_finala.length - 1][j])
						{
							matrice_finala[i][j] = matrice_finala[matrice_finala.length - 1][j];
							matrice_finala[i][matrice_finala[0].length - 1] = matrice_finala[i][matrice_finala[0].length - 1] - matrice_finala[i][j];
							matrice_finala[matrice_finala.length - 1][j] = matrice_finala[matrice_finala.length - 1][j] - matrice_finala[i][j];

							///Fac coloana * (-1)
							for(int k = 0; k < matrice_finala_costuri.length; k++)
								if(matrice_finala[k][j] == 0)
									matrice_finala[k][j] = -1;
						}

						if(matrice_finala[i][matrice_finala[0].length - 1] == matrice_finala[matrice_finala.length - 1][j])
						{
							matrice_finala[i][j] = matrice_finala[i][matrice_finala[0].length - 1];
							matrice_finala[i][matrice_finala[0].length - 1] = matrice_finala[i][matrice_finala[0].length - 1] - matrice_finala[i][j];
							matrice_finala[matrice_finala.length - 1][j] = matrice_finala[matrice_finala.length - 1][j] - matrice_finala[i][j];

							if(j + 1 < matrice_finala_costuri[0].length && matrice_finala_costuri[i][j+1] == 0)
							{
								matrice_finala[i][j+1] = -2;

								///Fac linia * (-1)
								for(int k = 0; k < matrice_finala_costuri[0].length; k++)
									if(matrice_finala[i][k] == 0)
										matrice_finala[i][k] = -1;

								///Fac coloana * (-1)
								for(int k = 0; k < matrice_finala_costuri.length; k++)
									if(matrice_finala[k][j] == 0)
										matrice_finala[k][j] = -1;

							}
							else if(i + 1 < matrice_finala_costuri.length && matrice_finala_costuri[i+1][j] == 0)
							{
								matrice_finala[i+1][j] = -2;
								///Fac linia * (-1)
								for(int k = 0; k < matrice_finala_costuri[0].length; k++)
									if(matrice_finala[i][k] == 0)
										matrice_finala[i][k] = -1;

								///Fac coloana * (-1)
								for(int k = 0; k < matrice_finala_costuri.length; k++)
									if(matrice_finala[k][j] == 0)
										matrice_finala[k][j] = -1;
							}
						}
					}


			///Verificam daca matricea e plina. Daca e, iesim din bucla, avem SBAi
			matrice_plina = true;
			for(int i = 0; i < matrice_finala_costuri.length; i++)
				for(int j = 0; j < matrice_finala_costuri[0].length; j++)
					if(matrice_finala[i][j] == 0)
						matrice_plina = false;

			System.out.println("Matrice noua construita:");
			Afisare_matrice(matrice_finala, matrice_finala.length, matrice_finala[0].length, 0);
		}

		
		System.out.println("Cost total pentru solutia initiala: " + Cost_total_solutie_curenta(matrice_finala_costuri, matrice_finala) + " u.m.");
		///Pana aici pare sa fie corect


		///PAS 2: VERIFIC DACA E OPTIMA SOLUTIA (DACA E, PROGRAMUL SE INCHEIE)
		boolean solutie_optima = false;
		int nr_delta = 0;
		double[] delta_var_secundare = new double[matrice_finala_costuri.length * matrice_finala_costuri[0].length 
		                                          - matrice_finala_costuri.length - matrice_finala_costuri[0].length + 1];


		int max_indice_linie_delta = 0, max_indice_coloana_delta = 0;
		double max_val_delta = 0;
		boolean nu_sunt_pozitive_in_delta = true, solutie_unica = true;


		while(solutie_optima == false)
		{
			max_indice_linie_delta = 0;
			max_indice_coloana_delta = 0;
			max_val_delta = 0;
			nu_sunt_pozitive_in_delta = true;
			solutie_unica = true;
			nr_delta = 0;

			for(int i = 0; i < matrice_finala_costuri.length; i++)
				for(int j = 0; j < matrice_finala_costuri[0].length; j++)
					if(matrice_finala[i][j] == -1)
					{
						ciclu_complet = false;
						delta_var_secundare[nr_delta] = Calculare_delta(matrice_finala, matrice_finala_costuri, i, j);

						if(delta_var_secundare[nr_delta] == 0)
						{
							solutie_unica = false;
						}

						if(delta_var_secundare[nr_delta] > 0 && delta_var_secundare[nr_delta] > max_val_delta)
						{
							max_val_delta = delta_var_secundare[nr_delta];
							max_indice_linie_delta = i;
							max_indice_coloana_delta = j;
							nu_sunt_pozitive_in_delta = false;
						}

						System.out.println("Val delta: " + delta_var_secundare[nr_delta]);

						nr_delta++;
					}

			if(nu_sunt_pozitive_in_delta == true)
			{
				solutie_optima = true;
			}
			else
			{
				///PAS 3: DACA NU E, VAD CINE INTRA IN BAZA: max_indice_linie_delta si max_indice_coloana_delta
				///PAS 4: VAD CINE IESE DIN BAZA
				///PAS 5: CREEZ NOUL TABEL

				ciclu_complet = false;
				Matricea_noua(matrice_finala, matrice_finala_costuri, max_indice_linie_delta, max_indice_coloana_delta);
			
				System.out.println("Cost intermediar pentru solutia obtinuta: " + Cost_total_solutie_curenta(matrice_finala_costuri, matrice_finala) + " " + forma_costuri_unitare);
				
			}
			

		}

		if(solutie_unica == true)
		{
			Frame_sase.textField_1Unica.setText("optima si unica.");
			
			System.out.println("Solutia finala este optima si unica.");
			System.out.println("Matricea finala este: ");
			Afisare_matrice(matrice_finala, matrice_initiala_costuri.length, matrice_initiala_costuri[0].length, 1);

			//PROBLEM
			for(int i = 1; i < matrice_initiala_costuri.length + 1; i++)
				for(int j = 1; j < matrice_initiala_costuri[0].length + 1; j++)
				{
					if(matrice_finala[i - 1][j - 1] == -1)
					{
						Frame_sase.table_final.setValueAt("", i, j);
					}
					else
					{
						Frame_sase.table_final.setValueAt(Math.round( matrice_finala[i - 1][j - 1] ), i, j);

					}

				}
			
			
			double cost_final = 0;
			for(int i = 0; i < matrice_finala_costuri.length; i++)
				for(int j = 0; j < matrice_finala_costuri[0].length; j++)
					if(matrice_finala[i][j] != -1 && matrice_finala[i][j] != -2)
						cost_final = cost_final + (Math.round(matrice_finala[i][j]) * matrice_finala_costuri[i][j]);

			
			Frame_sase.textFieldCostMinim.setText(String.valueOf(cost_final) + " " + forma_costuri_unitare);
			System.out.printf("Costul final este: %.0f %s.", cost_final, forma_costuri_unitare);
		}
		else
		{
			
			Frame_sase.textField_1Unica.setText("optima, dar nu unica.");

			System.out.println("Solutia finala este optima, dar nu unica.");
			System.out.println("Matricea finala este: ");
			Afisare_matrice(matrice_finala, matrice_initiala_costuri.length, matrice_initiala_costuri[0].length, 1);

			
			for(int i = 1; i < matrice_initiala_costuri.length + 1; i++)
				for(int j = 1; j < matrice_initiala_costuri[0].length + 1; j++)
				{
					if(matrice_finala[i - 1][j - 1] == -1)
					{
						Frame_sase.table_final.setValueAt("", i, j);
					}
					else
					{
						Frame_sase.table_final.setValueAt(Math.round( matrice_finala[i - 1][j - 1] ), i, j);

					}

				}
			
			
			double cost_final = 0;
			for(int i = 0; i < matrice_finala_costuri.length; i++)
				for(int j = 0; j < matrice_finala_costuri[0].length; j++)
					if(matrice_finala[i][j] != -1 && matrice_finala[i][j] != -2)
						cost_final = cost_final + (Math.round(matrice_finala[i][j]) * matrice_finala_costuri[i][j]);

			
			Frame_sase.textFieldCostMinim.setText(String.valueOf(cost_final) + " " + forma_costuri_unitare);
			System.out.printf("Costul final este: %.0f %s.", cost_final, forma_costuri_unitare);

		}

	}
}
