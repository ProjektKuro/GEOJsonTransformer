import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Timo 'eXodiquas' Netzer
 *
 */
public class Runner {

	/**
	 * @param args 1. Argument ist der Inputpath, 2. Argument der Outputpath. 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Converter c = new Converter(args[0], args[1]);
		c.run();
	}

}
