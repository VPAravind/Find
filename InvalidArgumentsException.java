

/**
 * Class to catch the invalid command line arguments
 * 
 * */
public class InvalidArgumentsException extends Exception {
	public InvalidArgumentsException(String s) {
		super(s);
	}
}
