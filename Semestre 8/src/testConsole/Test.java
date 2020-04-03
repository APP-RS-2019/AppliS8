package testConsole;

import java.util.Scanner;
import action.Action;
import action.ActionToDo;
import action.Scenario;
import robot.*;
import sensor.Sensor;
import upperClass.*;

public class Test {

	private static Scanner sc;

	public static void main(String[] args) throws Exception {
		Fleet defaultfleet = new Fleet("0");
		defaultfleet.setName("Flotte par default");
		Syst syst = new Syst();
		syst.update();
		syst.addFleet(defaultfleet);
		Scenario scenario = new Scenario();
		System.out.println("Ensemble des elements connus :\n");

		for (Fleet fleet : Syst.getFleets() ) {
			for(Robot robot : fleet.getRobots()) {
				for(Action action : robot.Capacity()) {
					System.out.println(action.getNom());
				}
				System.out.println(robot.getName());
			}
			System.out.print(fleet.toString()+"\n");
		}

		sc = new Scanner(System.in);
		boolean menuPrincipal = true;
		boolean menuModeManuel = true;
		boolean menuModeScenario = true;

		System.out.println("Bienvenu dans le mode console du programme APP-RS-2022");

		while(menuPrincipal){
			//Menu Principal
			System.out.println("Quelle Action souhaitez-vous faire ?\n");
			System.out.println("1 - Charger un système");
			System.out.println("2 - Sauvegarder le système");
			System.out.println("3 - Mode manuel");
			System.out.println("4 - Gérer les flottes");
			System.out.println("5 - Mode scénario");
			System.out.println("6 - Quitter le logiciel\n");

			String demandeMenuPrincipal = sc.nextLine();

			switch(demandeMenuPrincipal){

			case "1":
				System.out.println("A partir de quel chemin de fichier souhaitez-vous charger les données ?");
				System.out.println("Exemple : C:\\Users\\jacqu/Desktop/APP/save/save.txt\n");
				String cheminFichierChargement = sc.nextLine();
				try {
					syst.update(cheminFichierChargement);
					System.out.println("Fichier chargé avec succès\n");
				}
				catch(Exception FileNotFoundException) {
					System.err.println("Le fichier demandé est introuvable");
				}
				break;

			case "2":
				System.out.println("Vers quel chemin de fichier souhaitez-vous enregistrer ?\n");
				System.out.println("Exemple : C:\\Users\\jacqu/Desktop/APP/save/save.txt\n");
				String cheminFichierEnregistrement = sc.nextLine();
				try {
					syst.save(cheminFichierEnregistrement);
					System.out.println("Fichier enregistré avec succès\n");
				}
				catch(Exception FileNotFoundException) {
					System.err.println("Accès refusé");
				}
				break;

			case "3":
				menuModeManuel = true;
				while(menuModeManuel){
					System.out.println("Mode manuel");
					System.out.println("Que souhaitez-vous faire ?\n");
					System.out.println("1 - Test de communication avec le serveur");
					System.out.println("2 - Prendre controle d'un robot");
					System.out.println("3 - Revenir au menu principal\n");
					String demandeModeManuel = sc.nextLine();

					switch(demandeModeManuel){
					case "1":
						System.out.println("IP du serveur\n");
						String iPserveur = sc.nextLine();
						System.out.println("Numéro de port\n");
						String nbPort = sc.nextLine();
						System.out.println("Tentative de connexion...\n");
						try{
							ClientSocket server = new ClientSocket(iPserveur,Integer.parseInt(nbPort),"Test");
							System.out.println("Etat du serveur : + " + server.isOpen() + "\n");
						}
						catch(Exception ConnectException) {
							System.err.println("Connection au serveur échouée\n");
						}
						break;

					case "2":
						System.out.println("IP du serveur\n");
						iPserveur = sc.nextLine();
						System.out.println("Numéro de port\n");
						nbPort = sc.nextLine();

						System.out.println("Quel est IP du robot dont vous souhaitez prendre le controle ?\n");
						String iPRobotModeManuel = sc.nextLine();
						System.out.println("Voici les actions disponibles pour le robot : \n");
						System.out.println("Quelle actions souhaitez-vous lui faire faire ?\n");
						String actionModeManuel = sc.nextLine();
						System.out.println("Tentative de connexion...\n");
						try{
							ClientSocket server = new ClientSocket(iPserveur,Integer.parseInt(nbPort),"Mode manuel");
							System.out.println("Etat du serveur : + " + server.isOpen() + "\n");
							System.out.println("Envoi de l'ordre\n");
							server.sendOrder(iPRobotModeManuel, actionModeManuel);
						}
						catch(Exception ConnectException) {
							System.err.println("Connection au serveur échouée\n");
						}

						break;

					case "3":
						menuModeManuel = false;
						break;

					default:
						System.out.println("La demande est incohérente, veuillez respecter les régles de demande\n");
						break;
					};
				}
				break;

			case "4":
				System.out.println("Que souhaitez-vous faire ?\n");
				System.out.println("1 - Créer une flotte");
				System.out.println("2 - Supprimer une flotte");
				System.out.println("3 - Afficher les flottes existantes");
				System.out.println("4 - Sélectionner une flotte\n");
				String demandeFlotte = sc.nextLine();

				switch(demandeFlotte){
				case "1":
					System.out.println("Nom de la flotte à créer?\n");
					String nomAjoutFlotte = sc.nextLine();
					int max = 0;
					for (Fleet fleetAjout : Syst.getFleets() ) {
						if(max<Integer.parseInt(fleetAjout.getId())){
							max = Integer.parseInt(fleetAjout.getId());
						}
					}
					Fleet fleet = new Fleet(Integer.toString(max));
					fleet.setName(nomAjoutFlotte);
					syst.addFleet(fleet);
					break;

				case "2":
					System.out.println("Nom de la flotte à supprimer?\n");
					System.out.println("Flottes existantes :\n");
					for (Fleet FleetAffiche : Syst.getFleets() ) {
						System.out.println(FleetAffiche.getName());
					}
					String nomSupprimerFlotte = sc.nextLine();

					for (Fleet fleetS : Syst.getFleets() ) {
						if(fleetS.getName().equals(nomSupprimerFlotte)) {
							syst.deleteFleet(fleetS);
						}
					}
					break;

				case "3":
					System.out.println("Flottes existantes :\n");
					for (Fleet FleetAffiche : Syst.getFleets() ) {
						System.out.println(FleetAffiche.getName());
					}
					break;

				case "4":
					System.out.println("Quelle flotte souhaitez-vous selectionner ?\n");
					System.out.println("Flottes existantes :\n");
					for (Fleet FleetAffiche : Syst.getFleets() ) {
						System.out.println(FleetAffiche.getName());
					}
					System.out.println("\n");
					String nomSelectionFlotte = sc.nextLine();
					boolean fleetExist = false;
					String fleetID = null;
					for (Fleet FleetTest : Syst.getFleets() ) {
						if(FleetTest.getName().contentEquals(nomSelectionFlotte)) {
							fleetExist = true;
							fleetID = FleetTest.getId();
						}
					}
					if(fleetExist) {
						System.out.println("Que souhaitez-vous faire ?\n");
						System.out.println("1 - Ajouter un robot dans la flotte");
						System.out.println("2 - Supprimer un robot de la flotte");
						System.out.println("3 - Afficher les robots de la flotte");
						System.out.println("4 - Sélectionner un robot\n");
						String selectionFlotte = sc.nextLine();

						switch(selectionFlotte){
						case "1":
							System.out.println("Quelle robot voulez-vous ajouter ?\n");
							System.out.println("Type ? (Nao/Pepper/Robotino/Peekee1R)\n");
							String typeRobot = sc.nextLine();
							System.out.println("IP du robot ?\n");
							String iPRobot = sc.nextLine();
							System.out.println("Nom ?\n");
							String nomRobot = sc.nextLine();

							switch (typeRobot) {
							case "Nao":
								for (Fleet FleetRecherche : Syst.getFleets() ) {
									if(FleetRecherche.getName().contentEquals(nomSelectionFlotte)) {
										Nao Nao = new Nao(iPRobot, nomRobot);
										FleetRecherche.addRobot(Nao);
									}
								}
								break;
							case "Pepper":
								for (Fleet FleetRecherche : Syst.getFleets() ) {
									if(FleetRecherche.getName().contentEquals(nomSelectionFlotte)) {
										Pepper Pepper = new Pepper(iPRobot, nomRobot);
										FleetRecherche.addRobot(Pepper);
									}
								}
								break;
							case "Robotino":
								for (Fleet FleetRecherche : Syst.getFleets() ) {
									if(FleetRecherche.getName().contentEquals(nomSelectionFlotte)) {
										Robotino Robotino = new Robotino(iPRobot, nomRobot);
										FleetRecherche.addRobot(Robotino);
									}
								}
								break;
							case "Peekee1R":
								for (Fleet FleetRecherche : Syst.getFleets() ) {
									if(FleetRecherche.getName().contentEquals(nomSelectionFlotte)) {
										Peekee1R Peekee1R = new Peekee1R(iPRobot, nomRobot);
										FleetRecherche.addRobot(Peekee1R);
									}
								}
								break;
							}
							break;

						case "2":
							System.out.println("Nom du robot à supprimer?\n");
							System.out.println("Robots de la flotte :\n");
							for (Fleet fleetAffiche : Syst.getFleets()) {
								if(fleetAffiche.getName().equals(nomSelectionFlotte)){
									for(Robot robot : fleetAffiche.getRobots()) {
										System.out.println(robot.getName());
									}
								}
							}
							String nomSupprimerRobot = sc.nextLine();

							for (Fleet fleetS : Syst.getFleets() ) {
								if(fleetS.getId().equals(fleetID)) {
									for(Robot robot : fleetS.getRobots()) {
										if(robot.getName().equals(nomSupprimerRobot)) {
											fleetS.deleteRobot(robot);
										}
									}
								}
							}
							break;

						case "3":
							System.out.println("Robots de la flotte :\n");
							for (Fleet fleetAffiche : Syst.getFleets()) {
								if(fleetAffiche.getName().equals(nomSelectionFlotte)){
									for(Robot robot : fleetAffiche.getRobots()) {
										System.out.println(robot.getName());
									}
								}
							}
							break;

						case "4":
							System.out.println("Quel robot souhaitez-vous selectionner ?\n");
							System.out.println("Robots de la flotte :\n");
							for (Fleet fleetAffiche : Syst.getFleets()) {
								if(fleetAffiche.getName().equals(nomSelectionFlotte)){
									for(Robot robot : fleetAffiche.getRobots()) {
										System.out.println(robot.getName());
									}
								}
							}

							String nomSelectionRobot = sc.nextLine();
							boolean robotExist = false;
							String robotID = null;
							for (Fleet FleetTest : Syst.getFleets() ) {
								for(Robot robot : FleetTest.getRobots()) {
									if(robot.getName().contentEquals(nomSelectionRobot)) {
										robotExist = true;
										robotID = robot.getId();
									}
								}
							}

							if(robotExist) {
								System.out.println("Que souhaitez-vous faire ?\n");
								System.out.println("1 - Ajouter une action au robot");
								System.out.println("2 - Supprimer une action au robot");
								System.out.println("3 - Ajouter un capteur au robot");
								System.out.println("4 - Supprimer un capteur au robot");
								System.out.println("5 - Sélectionner une action");
								System.out.println("6 - Sélectionner un capteur");
								System.out.println("7 - Afficher l'ensemble des capteurs du robot");
								System.out.println("8 - Afficher l'ensemble des action du robot\n");
								String selectionRobot = sc.nextLine();

								switch(selectionRobot){
								case "1":
									System.out.println("Nom de l'action à ajouter?\n");
									String nomAjoutAction = sc.nextLine();
									for (Fleet fleetR : Syst.getFleets() ) {
										if(fleetR.equals(nomSelectionFlotte)){
											for(Robot robot : fleetR.getRobots()) {
												if(robot.equals(nomSelectionRobot)){
													Action actionAjoutee = new Action(robot.generatIDAction(),nomAjoutAction,"");
													robot.addAction(actionAjoutee);
												}
											}
										}
									}
									break;

								case "2":
									System.out.println("Nom de l'action à supprimer?\n");
									System.out.println("Ensemble des actions :\n");
									for (Fleet fleetAffiche : Syst.getFleets()) {
										if(fleetAffiche.getName().equals(nomSelectionFlotte)){
											for(Robot robot : fleetAffiche.getRobots()) {
												if(robot.getName().equals(nomSelectionRobot)) {
													for(Action action : robot.getAction()) {
														System.out.println(action.getNom());
													}
												}
											}
										}
									}
									String nomSupprimerAction = sc.nextLine();

									for (Fleet fleetS : Syst.getFleets() ) {
										if(fleetS.getId().equals(fleetID)) {
											for(Robot robot : fleetS.getRobots()) {
												if(robot.getId().equals(robotID)) {
													for(Action action : robot.getAction()) {
														if(action.getNom().equals(nomSupprimerAction)) {
															robot.getAction().remove(action);
														}
													}
												}
											}
										}
									}
									break;

								case "3":
									System.out.println("Nom du capteur à ajouter?\n");
									String nomAjoutCapteur = sc.nextLine();
									for (Fleet fleetR : Syst.getFleets() ) {
										if(fleetR.equals(nomSelectionFlotte)){
											for(Robot robot : fleetR.getRobots()) {
												if(robot.equals(nomSelectionRobot)){
													Sensor sensorAjoutee = new Sensor(robot.generatIDSensor(),nomAjoutCapteur,"");
													robot.addSensor(sensorAjoutee);
												}
											}
										}
									}
									break;

								case "4":
									System.out.println("Nom du capteur à supprimer?\n");
									System.out.println("Ensemble des capteurs :\n");
									for (Fleet fleetAffiche : Syst.getFleets()) {
										if(fleetAffiche.getName().equals(nomSelectionFlotte)){
											for(Robot robot : fleetAffiche.getRobots()) {
												if(robot.getName().equals(nomSelectionRobot)) {
													for(Sensor sensor : robot.getSensor()) {
														System.out.println(sensor.getName());
													}
												}
											}
										}
									}
									String nomSupprimerCapteur = sc.nextLine();
									for (Fleet fleetS : Syst.getFleets() ) {
										if(fleetS.getId().equals(fleetID)) {
											for(Robot robot : fleetS.getRobots()) {
												if(robot.getId().equals(robotID)) {
													for(Sensor sensor : robot.getSensor()) {
														if(sensor.getName().equals(nomSupprimerCapteur)) {
															robot.getSensor().remove(sensor);
														}
													}
												}
											}
										}
									}
									break;

								case "5":
									System.out.println("Quelle action souhaitez-vous selectionner ?\n");
									System.out.println("Ensemble des actions :\n");
									for (Fleet fleetAffiche : Syst.getFleets()) {
										if(fleetAffiche.getName().equals(nomSelectionFlotte)){
											for(Robot robot : fleetAffiche.getRobots()) {
												if(robot.getName().equals(nomSelectionRobot)) {
													for(Action action : robot.getAction()) {
														System.out.println(action.getNom());
													}
												}
											}
										}
									}
									String nomSelectionAction = sc.nextLine();
									boolean existAction = false;
									System.out.println("Description de l'action");
									for (Fleet fleetS : Syst.getFleets() ) {
										if(fleetS.getId().equals(fleetID)) {
											for(Robot robot : fleetS.getRobots()) {
												if(robot.getId().equals(robotID)) {
													for(Action action : robot.getAction()) {
														if(action.getNom().equals(nomSelectionAction)){
															System.out.println(action.getDescription());
															existAction = true;
														}
													}
												}
											}
										}
									}

									if (!existAction) {
										System.err.println("Action introuvable");
									}
									break;

								case "6":
									System.out.println("Quelle capteur souhaitez-vous selectionner ?\n");
									System.out.println("Ensemble des capteurs :\n");
									for (Fleet fleetAffiche : Syst.getFleets()) {
										if(fleetAffiche.getName().equals(nomSelectionFlotte)){
											for(Robot robot : fleetAffiche.getRobots()) {
												if(robot.getName().equals(nomSelectionRobot)) {
													for(Sensor sensor : robot.getSensor()) {
														System.out.println(sensor.getName());
													}
												}
											}
										}
									}
									String nomSelectionCapteur = sc.nextLine();
									boolean existSensor = false;
									System.out.println("Description du capteur");
									for (Fleet fleetS : Syst.getFleets() ) {
										if(fleetS.getId().equals(fleetID)) {
											for(Robot robot : fleetS.getRobots()) {
												if(robot.getId().equals(robotID)) {
													for(Sensor sensor : robot.getSensor()) {
														if(sensor.getName().equals(nomSelectionCapteur)){
															System.out.println(sensor.getDescription());
															existSensor = true;
														}
													}
												}
											}
										}
									}
									if(!existSensor) {
										System.err.println("Capteur introuvable");
									}
									break;

								case "7":
									System.out.println("Ensemble des capteurs :\n");
									for (Fleet fleetAffiche : Syst.getFleets()) {
										if(fleetAffiche.getName().equals(nomSelectionFlotte)){
											for(Robot robot : fleetAffiche.getRobots()) {
												if(robot.getName().equals(nomSelectionRobot)) {
													for(Sensor sensor : robot.getSensor()) {
														System.out.println(sensor.getName());
													}
												}
											}
										}
									}
									break;

								case "8":
									System.out.println("Ensemble des actions :\n");
									for (Fleet fleetAffiche : Syst.getFleets()) {
										if(fleetAffiche.getName().equals(nomSelectionFlotte)){
											for(Robot robot : fleetAffiche.getRobots()) {
												if(robot.getName().equals(nomSelectionRobot)) {
													for(Action action : robot.getAction()) {
														System.out.println(action.getNom());
													}
												}
											}
										}
									}
									break;

								default:
									System.out.println("La demande est incohérente, veuillez respecter les régles de demande\n");
									break;
								}}else {System.err.println("Ce robot n'existe pas");}
							break;

						default:
							System.out.println("La demande est incohérente, veuillez respecter les régles de demande\n");
							break;
						}}else {System.err.println("Cette flotte n'existe pas");}
					break;

				default:
					System.out.println("La demande est incohérente, veuillez respecter les régles de demande\n");
					break;
				}
				break;

			case "5":
				menuModeScenario = true;
				while(menuModeScenario){
					System.out.println("Mode scénario");
					System.out.println("Que souhaitez-vous faire ?\n");
					System.out.println("1 - Ajouter une action au scénario");
					System.out.println("2 - Effectuer le scénario en mode simulation");
					System.out.println("3 - Effectuer le scénario en mode déploiment");
					System.out.println("4 - Revenir au menu principal\n");
					String demandeModeScenario = sc.nextLine();

					switch(demandeModeScenario){
					case "1":
						System.out.println("Selectionner une flotte\n");
						System.out.println("Flottes existantes :\n");
						for (Fleet FleetAffiche : Syst.getFleets() ) {
							System.out.println(FleetAffiche.getName());
						}
						String flotteScenario = sc.nextLine();

						System.out.println("Selectionner un robot\n");
						System.out.println("Robots de la flotte :\n");
						for (Fleet fleetAffiche : Syst.getFleets()) {
							if(fleetAffiche.getName().equals(flotteScenario)){
								for(Robot robot : fleetAffiche.getRobots()) {
									System.out.println(robot.getName());
								}
							}
						}
						String robotScenario = sc.nextLine();

						System.out.println("Selectionner une action\n");
						System.out.println("Ensemble des actions :\n");
						for (Fleet fleetAffiche : Syst.getFleets()) {
							if(fleetAffiche.getName().equals(flotteScenario)){
								for(Robot robot : fleetAffiche.getRobots()) {
									if(robot.getName().equals(robotScenario)) {
										for(Action action : robot.getAction()) {
											System.out.println(action.getNom());
										}
									}
								}
							}
						}
						String actionScenario = sc.nextLine();

						System.out.println("Temps auquel l'action doit s'executer (en seconde)");
						System.out.println("Exemple : 3");
						String tempsScenario = sc.nextLine();

						ActionToDo actionToDo = new ActionToDo(robotScenario,actionScenario,Long.parseLong(tempsScenario));
						scenario.addAction(actionToDo);
						break;

					case "2":
						Scenario.connexion = false;
						scenario.execute();
						break;

					case "3":
						Scenario.connexion = true;
						scenario.execute();
						break;

					case "4":
						menuModeScenario = false;
						break;

					default:
						System.out.println("La demande est incohérente, veuillez respecter les régles de demande\n");
						break;
					}
				}
				break;

			case "6":
				menuPrincipal = false;
				break;

			default:
				System.out.println("La demande est incohérente, veuillez respecter les régles de demande\n");
				break;
			}
		}
	}
}
