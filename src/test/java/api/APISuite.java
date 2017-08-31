package api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by jason on 2016/08/26.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BenchtopTest.class,
		BenchtopDeserializerTest.class,
		SubstanceDeserializerTest.class,
		ExperimentDeserializerTest.class,
		SubroutineDeserializerTest.class,
        ChemicalDeserializerTest.class,
        SubroutineDeserializerTest.class
})
public class APISuite {}
