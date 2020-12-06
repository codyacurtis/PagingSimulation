import java.util.Scanner;

/**
 * virtualPaging takes in a page-reference string and count of frames to
 * simulate a virtual paging memory manager. The program presents each iteration
 * of accessing a page with what each queue looks like and the count of page
 * faults. The program will end after 9 pages have been ran, or when the user
 * ends a page-reference string with -99 indicating it is the last page to be
 * ran.
 * 
 * @author Cody Curtis
 *
 */
public class virtualPaging {

	private final static int LASTPAGE = -99;
	private final static int MAXPAGES = 9;

	/**
	 * Converts the entered string of pages to an array of integers.
	 * 
	 * @param pages
	 * @return integer array of pages
	 */
	private static int[] makeIntArray(String pages) {
		String[] splitPages = pages.split(" ");
		int[] emptyPages = new int[splitPages.length];
		for (int i = 0; i < emptyPages.length; i++)
			emptyPages[i] = Integer.parseInt(splitPages[i]);
		return emptyPages;
	}

	/**
	 * Checks the last element of the array to determine if this is the last page to
	 * be entered.
	 * 
	 * @param pageArray
	 * @return true if LASTPAGE else false
	 */
	private static boolean lastPage(int[] pageArray) {
		boolean lastPage = false;
		if (pageArray[pageArray.length - 1] == LASTPAGE)
			lastPage = true;
		return lastPage;
	}

	/**
	 * This codes checks for boundary cases. If the initial check is true an error
	 * was found and returns true preventing additional checking. The second check
	 * will flip the flag to show no errors were found and returns this statement.
	 * 
	 * @param pageArray
	 * @param frames
	 * @return true if errors are found else false
	 */
	private static boolean errors(int[] pageArray, int frames) {
		boolean errors = true;
		if (pageArray.length > 20 || frames > 5 || frames < 3)
			return errors;
		if (pageArray[pageArray.length - 1] == -1 || pageArray[pageArray.length - 1] == LASTPAGE)
			errors = false;
		return errors;
	}

	/**
	 * This method runs the simulation using the Least Recently Used algorithm. This
	 * algorithm will either update a page already in memory with its most recent
	 * use, or will replace an old page, if there is no empty space in memory, with
	 * the new page and add its memory to the queue.
	 * 
	 * @param frames
	 * @param pageArray
	 */
	private static void runSimulation(int frames, int[] pageArray) {
		int pageFaults = 0;
		int contents = 0;

		int[] activePages = new int[frames];
		int[] pagesLRU = new int[frames];

		for (int i = 0; i < pageArray.length - 1; i++) {
			boolean replaced = false;
			for (int j = 0; j < activePages.length; j++) {
				if (activePages[j] == pageArray[i]) {
					pagesLRU[j] = i + 1;
					printStatus(pageArray[i], activePages, pagesLRU, pageFaults);
					replaced = true;
				}
			}
			if (replaced) {
				continue;
			} else if (contents < frames) {
				activePages[contents] = pageArray[i];
				pagesLRU[contents] = i + 1;
				pageFaults++;
				contents++;
			} else {
				int minValue = 25;
				int minIndex = 0;
				for (int j = 0; j < pagesLRU.length; j++) {
					if (pagesLRU[j] < minValue) {
						minIndex = j;
						minValue = pagesLRU[j];
					}
				}
				activePages[minIndex] = pageArray[i];
				pagesLRU[minIndex] = i + 1;
				pageFaults++;
			}
			printStatus(pageArray[i], activePages, pagesLRU, pageFaults);
		}
	}

	/**
	 * Prints an organized header for the program.
	 * 
	 * @param frames
	 */
	private static void printHeader(int frames) {
		System.out.print("ReferencePage\t");
		for (int i = 1; i <= frames; i++) {
			System.out.print("Frame" + i + "\t");
		}
		for (int i = 1; i <= frames; i++) {
			System.out.print("LRU-Q" + i + "\t");
		}
		System.out.print("PF-count\n");
		System.out.println("=".repeat(20 * frames + 60 / frames - 7));

	}

	/**
	 * Prints the current page being accessed, the pages in memory, their most
	 * recent use, and the count of page faults.
	 * 
	 * @param pageRef
	 * @param activePages
	 * @param pagesLRU
	 * @param pageFaults
	 */
	private static void printStatus(int pageRef, int[] activePages, int[] pagesLRU, int pageFaults) {
		System.out.print(pageRef + "\t\t|");
		for (int page : activePages)
			System.out.print(page + "\t|");
		for (int value : pagesLRU)
			System.out.print(value + "\t|");
		System.out.print(pageFaults + "\t|\n");
	}

	/**
	 * Takes in user input and calls runSimulation with these inputs.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		var lastPage = false;
		var pageCount = 0;
		System.out.println("The simulation has now started:");
		while (!lastPage && pageCount < MAXPAGES) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print("Please enter a page reference string:\n");
			var pages = scan.nextLine();
			System.out.print("Please enter the number of frames: ");
			var frames = scan.nextInt();
			System.out.println();

			int[] pageArray = makeIntArray(pages);

			if (errors(pageArray, frames)) {
				System.out.println("Progam only allows up to 20 pages and frames must be 3, 4, or 5.");
				System.out.println("The last value for the page-reference string must be either -1 or -99.");
				continue;
			}

			printHeader(frames);
			runSimulation(frames, pageArray);
			System.out.println("=".repeat(20 * frames + 60 / frames - 7));
			lastPage = lastPage(pageArray);
			pageCount++;

		}
		System.out.println("See you next time!");
	}

}
