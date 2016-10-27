package substance;

import java.io.Serializable;

/**
 * Created by jason on 2016/08/18.
 */
public class Property<V> implements Serializable {
	private float quantity;
	private V unit;

	public Property(float quan, V unit) {
		this.quantity = quan;
		this.unit = unit;
	}

	public float getQuantity() {
		return quantity;
	}

	public V getUnit() {
		return unit;
	}

	public String toString() {
		return this.toString("");
	}

	public String toString(String indentBuffer) {
		return indentBuffer + String.format("%.2f %s", this.quantity, this.unit.toString());
	}
}
