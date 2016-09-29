package substance;

/**
 * Created by jason on 2016/08/18.
 */
public class Units {

	public enum Volume {
		nL, uL, mL, cL, dL, L, daL, hL, kL
	}

	public enum Mass {
		nG, uG, mG, cG, dG, G, daG, hG, kG
	}

	public enum Length {

	}

	public enum Time {
		MICROSECOND, MILLISECOND, SECOND, MINUTE, HOUR, DAY
	}

	public enum Temperature {
		K, C, F;

		int value;

		public double convert(Temperature input, Temperature output) {
			switch(input) {
				case K:
					switch(output) {
						case C:
							break;
						case F:
							break;
					}
					break;
				case C:
					switch(output) {
						case F:
							break;
						case K:
							break;
					}
					break;
				case F:
					switch(output) {
						case C:
							break;
						case K:
							break;
					}
					break;
			}
			return 0.0;
		}
	}
}
