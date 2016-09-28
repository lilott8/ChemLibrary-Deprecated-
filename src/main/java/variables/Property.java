package variables;

/**
 * Created by jason on 2016/08/18.
 */
public class Property<V> {
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
		return String.format("%.2f %s", this.quantity, this.unit.toString());
	}
}
