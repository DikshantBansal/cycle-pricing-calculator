package parent.cycle.parts.wheels;

import java.util.Date;

public class CycleWheelsFactory {

	public static IWheels getCycleWheel(String type, Date pricingDate) {

		switch (type) {
		case "tubeWheel":
			return new TubeWheels(pricingDate);
		case "tubelessWheel":
			return new TubelessWheels(pricingDate);
		default:
			return new TubeWheels(pricingDate);
		}

	}

	public static IWheels getDefaultCycleWheel(Date pricingDate) {
		return getCycleWheel("tubeWheel", pricingDate);
	}

}
