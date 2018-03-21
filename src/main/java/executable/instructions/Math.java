package executable.instructions;

import substance.Substance;

/**
 * @created: 3/21/18
 * @since: 0.1
 * @project: chemlibrary
 */
public class Math extends Instruction {

    public Math(long id, String name) {
        super(id, name, Math.class);
    }

    public Math(long id) {
        super(id, Math.class);
    }

    public Math(String name) {
        super(name, Math.class);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        return sb.toString();
    }

    public void execute(Substance... variables) {

    }

    public void execute() {

    }
}
