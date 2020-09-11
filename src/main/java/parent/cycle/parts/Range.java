package parent.cycle.parts;

import java.util.Date;

public class Range {

	Date upperLimit;
	Integer price;

	public Range(Date upperLimit, Integer price) {
		this.upperLimit = upperLimit;
		this.price = price;
	}

	public Date getUpperLimit() {
		return upperLimit;
	}

	public Integer getPrice() {
		return price;
	}

}
