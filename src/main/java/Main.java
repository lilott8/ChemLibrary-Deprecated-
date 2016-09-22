import executable.Executable;
import executable.instructions.Combine;

/**
 * Created by jason on 2016/09/21.
 */
public class Main {

	public static void main(String... args) {
		Executable c = new Combine("name", Combine.class, -1);
	}
}
