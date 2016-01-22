import java.util.Scanner;
import java.io.*;

/**
 * @author Joseph Burt
 *
 * Recursively searches directories given a user prompted beginning
 * path and prints the path from it to a targeted directory or file.
 */
public class Search 
{	
	private String decision;
	private Scanner scan;
	private boolean pass;
	private boolean found;
	
	/**
	 * Constructs an object of the Search class.
	 */
	public Search()
	{
		decision = "";
		scan = new Scanner(System.in);
		pass = true;
		found = false;
	}
	
	/**
	 * Performs the primary functions of the Search class and
	 * allows a user to search indefinitely.
	 */
	private void recursiveSearch()
	{
		pass = false;
		found = false;
		String startingString = promptStartingDir();
		
		if(pass)
		{
			File startingDirectory = new File (startingString);
			String targetString = promptTargetDir();
			File targetDirectory = new File(targetString);
			System.out.println("Searching... ");
			recFindTarget(startingDirectory, targetDirectory);
			
			if(!found)
				System.out.println("File not found.");
		}
		continueSearch();
		
		if(decision == "y")
			recursiveSearch();
		if(decision == "n");
			System.exit(0);
	}
	
	/**
	 * Prompts the user for a starting directory.
	 * @return result  the user selected starting directory or a blank
	 * 				   string if the directory is invalid
	 */
	private String promptStartingDir()
	{
		System.out.print("Please enter a starting directory: ");
		String result = scan.next();
		
		if(new File(result).exists())
		{
			pass = true;
			return result;
		}
		
		else
		{
			System.out.println("Directory not found.");
			pass = false;
			return "";
		}
	}
	
	/**
	 * Prompts the user for a target directory.
	 * @return result  the target file to be found
	 */
	private String promptTargetDir()
	{
		System.out.print("Please enter a target file or directory to be found: ");
		String result = scan.next();
		return result;
	}
	
	/**
	 * Recursively searches the system's directories in search for 
	 * the target directory or file. 
	 * @param inputDir  the directory to begin at
	 * @param target  the target file or directory
	 */
	private void recFindTarget(File inputDir, File target)
	{
		File[] fArr = inputDir.listFiles();
		// If the target file is the beginning directory
		if(inputDir.getName().equals(target.getName()))
		{
			System.out.println("Target absolute path: " + 
					inputDir.getAbsolutePath());
			found = true;
		}
		// Search through every file in the directory
		if(fArr != null)
		{
			// For every file in the directory
			for(File fs : fArr)
			{
				// If the file is a directory
				if(fs.isDirectory())
					recFindTarget(fs, target);
				
				// If the file is the target
				else if(target.getName().equals(fs.getName()))
				{
					System.out.println("Target absolute path: " + 
							fs.getAbsolutePath());
					found = true;
				}
			}
		}
	}
	
	/**
	 * Prompts the user whether to search for another
	 * directory or exit the program.
	 */
	private void continueSearch()
	{
		System.out.print("Search again? y/n: ");
		decision = scan.next();
		
		if(decision.equals("y"))
			recursiveSearch();
		
		else if(decision.equals("n"))
		{
		
		}
			
		else
		{
			System.out.println("Please enter either \"y\" for yes or \"n\" for no.");
			continueSearch();
		}
	}
	
	/**
	 * Launches the program by instantiating the Search class and
	 * running its functions.
	 * @param args  not used
	 */
	public static void main(String[] args) 
	{
		Search s = new Search();
		s.recursiveSearch();
	}
}
