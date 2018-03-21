package executable.instructions;

import substance.Substance;

/**
 * @created: 3/21/18
 * @since: 0.1
 * @project: chemlibrary
 */
public class Return extends Instruction {

    public Return(long id, String name) {
        super(id, name, Return.class);
    }

    public Return(long id) {
        super(id, Output.class);
    }

    public Return(String name) {
        super(name, Return.class);
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