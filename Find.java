


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Find.java
 * 
 * Version:
 * $1.0$
 */

/**
 * To implements the Find functionalty for finding files or directories.
 * 
 * @author Aravind Vicinthangal Prathivaathi
 *
 */

public class Find {
	static String[] type;

	static void checkFile(File f, String dirOrFile) {
		if (dirOrFile == null) {
			System.out.print(f.getParentFile() + "  ");
			System.out.print(f.getName() + "   ");
			for (int i = 0; i < type.length; i++) {
				if (type[i] != null) {
					if (type[i].equals("date")) {
						long epoch = f.lastModified();
						Date date = new Date(epoch * 1000);
						System.out.print(date + "   ");
					}

					if (type[i].equals("length")) {
						System.out.print(f.length() + "   ");
					}
				}
			}

		}

		else if (dirOrFile.equals("f")) {
			System.out.print(f.getParentFile() + "  ");
			System.out.print(f.getName());
			for (int i = 0; i < type.length; i++) {
				if (type[i] != null) {
					if (type[i].equals("date")) {
						long epoch = f.lastModified();
						Date date = new Date(epoch * 1000);
						System.out.print(date);
					}

					if (type[i].equals("length")) {
						System.out.print(f.length());
					}
				}
			}
		}
	}

	static void CheckForDir(File f, String dirOrFile) {
		if (dirOrFile == null) {
			System.out.print(f.getParentFile() + "  ");
			System.out.print(f.getName() + "   ");
			for (int i = 0; i < type.length; i++) {
				if (type[i] != null) {
					if (type[i].equals("date")) {
						long epoch = f.lastModified();
						Date date = new Date(epoch * 1000);
						System.out.print(date + "   ");
					}

					if (type[i].equals("length")) {
						System.out.print(f.length() + "   ");
					}
				}
			}
		}

		else if (dirOrFile.equals("d")) {
			System.out.print(f.getParentFile() + "  ");
			System.out.println(f.getName() + "   ");
			for (int i = 0; i < type.length; i++) {
				if (type[i] != null) {
					if (type[i].equals("date")) {
						long epoch = f.lastModified();
						Date date = new Date(epoch * 1000);
						System.out.print(date + "   ");
					}

					if (type[i].equals("length")) {
						System.out.print(f.length() + "   ");
					}
				}
			}
		}
	}

	/**
	 * Used to recursively print the directories or files
	 */
	static void RecursivePrint(File[] arr, int level, String dirOrFile) {
		for (File f : arr) {
			for (int i = 0; i < level; i++)
				System.out.print("\t");
			if (f.isFile()) {
				checkFile(f, dirOrFile);
			}

			else if (f.isDirectory()) {
				CheckForDir(f, dirOrFile);
				System.out.println();
				RecursivePrint(f.listFiles(), level + 1, dirOrFile);
			}
			System.out.println();
		}
	}
	/**
	 * Used to search for a file and then print it along with other functions
	 * if mentioned in the command
	*/
	static void SearchPrintFile(File[] arr, int level, String name, String dirOrFile) {
		for (File f : arr) {
			for (int i = 0; i < level; i++)
				System.out.print("\t");

			if (f.isFile()) {
				if (f.getName().equals(name)) {
					checkFile(f, dirOrFile);
				}

			} else if (f.isDirectory()) {
				if (f.getName().equals(name)) {
					CheckForDir(f, dirOrFile);
				}
				// recursion for sub-directories
				System.out.println();

				SearchPrintFile(f.listFiles(), level + 1, name, dirOrFile);
			}
			System.out.println();

		}
	}

	/**
	 * Main method : Checks for the command used to implement the find
	 * functionality
	 * 
	 * @throws IOException InvalidArgumentException
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws InvalidArgumentsException, IOException {
		// Provide full path for directory(change accordingly)
		if (args.length < 2 || args[1].equals("-name") || !args[0].equals("find")) {
			throw new InvalidArgumentsException("Invalid Syntax for Find");
		}

		type = new String[2];
		String searchFile = new String();
		searchFile = null;
		String dirOrFile = new String();
		dirOrFile = null;
		String rootDirName = args[1];
		File maindir = new File(rootDirName);
		if (!maindir.isAbsolute()) {
			rootDirName = maindir.getAbsolutePath();
		}
		File rootDir = new File(rootDirName);
		int typeIndex = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-name")) {
				searchFile = args[i + 1];
			}

			if (args[i].equals("-type")) {
				if (args[i + 1].equals("f")) {
					dirOrFile = "f";
				} else if (args[i + 1].equals("d")) {
					dirOrFile = "d";
				}
				else
					{System.out.println("Invalid");
					System.exit(2);
					}
			}

			if (args[i].equals("-date")) {
				type[typeIndex] = "date";
				typeIndex++;
			}

			if (args[i].equals("-length")) {
				type[typeIndex] = "length";
				typeIndex++;
			}
		}
		
		if (rootDir.exists() && rootDir.isDirectory()) {
			// array for files and sub-directories
			// of directory pointed by maindir

			File arr[] = rootDir.listFiles();
			System.out.println("**********************************************");
			System.out.println("Files from main directory : " + maindir);
			System.out.println("**********************************************");
			if (searchFile != null) {
				SearchPrintFile(arr, 0, searchFile, dirOrFile);
			}
			// Calling recursive method
			else
				RecursivePrint(arr, 0, dirOrFile);
		}
	}
}
