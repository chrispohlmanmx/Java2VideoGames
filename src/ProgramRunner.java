import java.util.List;
import java.util.Scanner;

import controller.VideoGameHelper;
import model.VideoGame;

/**
 * @author Christopher Pohlman - cmpolhman
 * CIS175 - Spring 2022
 * Feb 2, 2022
 */

/**
 * 
 */
public class ProgramRunner {
	static Scanner in = new Scanner(System.in);
	static VideoGameHelper vgh = new VideoGameHelper();
	
	private static void addAGame() {
		System.out.print("Enter a title: ");
		String title = in.nextLine();
		System.out.print("Enter a system: ");
		String system = in.nextLine();
		
		VideoGame toAdd = new VideoGame(title, system);
		vgh.insertGame(toAdd);
	}
	
	private static void viewGames() {
		List<VideoGame> allGames = vgh.showAllGames();
		for(VideoGame singleGame : allGames) {
			System.out.println(singleGame.returnGameDetails());
		}
	}
	
	private static void deleteAGame() {
		System.out.println("Enter the title to delete: ");
		String title = in.nextLine();
		System.out.println("Enter the system to delete: ");
		String system = in.nextLine();
		
		VideoGame toDelete = new VideoGame(title,system);
		vgh.deleteGame(toDelete);
	}
	
	private static void editAGame() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Title");
		System.out.println("2 : Search by System");
		int search = in .nextInt();
		in.nextLine();
		List<VideoGame> foundGames;
		if(search == 1) {
			System.out.print("Enter the title: ");
			String title = in.nextLine();
			foundGames = vgh.searchForGameByTitle(title);
		}else {
			System.out.print("Enter the system: ");
			String system = in.nextLine();
			foundGames = vgh.searchForGameBySystem(system);
		}
		
		if(!foundGames.isEmpty()) {
			System.out.println("Found Games.");
			for(VideoGame vg : foundGames) {
				System.out.println(vg.getId() + " : " + vg.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			
			VideoGame toEdit = vgh.searchForGameById(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle() + " for " + toEdit.getSystem());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update System");
			int update = in.nextInt();
			in.nextLine();
			
			if(update == 1) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setTitle(newTitle);
			}else if(update == 2) {
				System.out.print("New System: ");
				String newSystem = in.nextLine();
				toEdit.setSystem(newSystem);
			}
			
			vgh.updateGame(toEdit);
		}else {
			System.out.println("No results found");
		}
	}
	
	public static void menu() {
		boolean goAgain = true;
		System.out.println("Video Game List");
		while(goAgain) {
			System.out.println("Select an option:");
			System.out.println("1 : Add a Game");
			System.out.println("2 : Edit a Game");
			System.out.println("3 : Delete a Game");
			System.out.println("4 : View all Games");
			System.out.println("5 : Exit");
			System.out.print("Type your selection: ");
			int option = in.nextInt();
			in.nextLine();
			
			if(option == 1) {
				addAGame();
			}else if(option == 2) {
				editAGame();
			}else if(option == 3) {
				deleteAGame();
			}else if(option == 4) {
				viewGames();
			}else {
				vgh.cleanUp();
				System.out.println("Goodbye!");
				goAgain = false;
			}
			
		}
	}
	
	public static void main(String[] args) {
		menu();
	}
	
	
}
