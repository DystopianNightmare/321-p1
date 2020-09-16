import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Main method used to test cache. Can create 1 or 2 caches of whatever size supplied by user in arguements.
 * @instructor Minlong
 * @class CS321 for project 1
 * @author josh gandolfo
 *
 */
public class Test {
	private static boolean single = false;						// Returns true if single cache is used or false if not
	private static Cache<Object> c1;									// 1st level cache
	private static Cache<Object> c2;									//2nd level cache
	private static int firstHit = 0;							// 1st level cache hit
	private static int secondHit = 0;							// 2nd level cache hit
	private static int total = 0;								// total cache references							
	private static int seconCheck = 0;							// total second cache references made

	public static void printUsage() {
		System.out.println("Usage: Test 1 <cache size> <Filename to be test>");
		System.out.println("Usage: Test 2 <1st cache size> <2nd cache size> <Filename to be test>");
	}
	public static void main(String[] args) {
		try {
			Integer numCache = Integer.parseInt(args[0]);
			if(numCache == 1 && args.length != 3) {
				printUsage();
				return;
			}
			if(numCache == 2 && args.length != 4) {
				printUsage();
				return;
			}
			if(numCache != 1 && numCache != 2) {
				printUsage();
				return;
			}
		}catch(Exception e) {
			printUsage();
			return;
		}
		if(args.length == 3) {
			try {
			Integer size = Integer.parseInt(args[1]);
			c1 = new Cache<Object>(size);
			}catch(Exception e) {
				printUsage();
				return;
			}
			
			single = true;
		}
		else if(args.length == 4) {
			try {
			Integer size = Integer.parseInt(args[1]);
			c1 = new Cache<Object>(size);
			Integer size2 = Integer.parseInt(args[2]);
			c2 = new Cache<Object>(size2);
			}catch(Exception e) {
				printUsage();
				return;
			}
		}else {
			printUsage();
			return;
		}

		try {
			Scanner fileScan = new Scanner(new File(args[args.length-1]));

			while(fileScan.hasNext()) {

				String next = fileScan.next();

				if(single == true) {	
					if(c1.search( next)) {					// checks if next is in cache 1 then, increments firstHit if found
						firstHit++;
					}
				}
				if(single == false) {

					if(c1.search(next)) {					//checks if next is in cache 1 and if found add its to cache 2, then increments firstHit if found
						firstHit++;
						c2.search(next);
					}else {
						seconCheck++;				
						if( c2.search(next)) {				//checks if next is in cache 2, then increments secondHit if found
							secondHit++;
						}
					}
				}
				total++;
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound in Test");
			return;
		}
		if(single) {
			double globalRatio =  ((firstHit + secondHit)/(double)(total));
			System.out.println("One cache with "+ args[0] + " entries created");
			System.out.println("Number of global references: " + total);
			System.out.println("Number of global hits: " + firstHit);
			System.out.println("Number of 1st level cache hits: " + firstHit);
			System.out.println("The global hit ratio: "+ globalRatio);
		}
		if(!single) {
			double globalRatio =  ((firstHit + secondHit)/(double)(total));
			double ratio1 =  ((firstHit )/(double)(total));
			double ratio2 =  ((secondHit)/(double)(seconCheck));
			System.out.println("First cache with "+ args[0] + " entries created");
			System.out.println("Second cache with "+ args[1] + " entries created");
			System.out.println("");
			System.out.println("Number of global references: " + total);
			System.out.println("Number of global hits: " + (firstHit+secondHit));
			System.out.println("Global hit ratio: " + globalRatio);
			System.out.println("");
			System.out.println("Number of 1st level references: " + total);
			System.out.println("Number of 1st level cache hits: " + firstHit);
			System.out.println("1st level cache hit ratio: " + ratio1);
			System.out.println("");
			System.out.println("Number of 2nd level references: " + seconCheck);
			System.out.println("2nd level cache hits: " + secondHit);
			System.out.println("2nd level cache hit ratio: "+ ratio2);
		}
	}
}
