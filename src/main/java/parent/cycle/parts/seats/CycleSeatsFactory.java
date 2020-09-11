package parent.cycle.parts.seats;

import java.util.Date;

public class CycleSeatsFactory {

	public static ISeatAssembly getCycleSeat(String type, Date pricingDate) {

		switch (type) {
		case "normalCycleSeat":
			return new NormalCycleSeat(pricingDate);
		case "airSuspensionCycleSeat":
			return new AirSuspensionCycleSeat(pricingDate);
		default:
			return new NormalCycleSeat(pricingDate);
		}

	}

	public static ISeatAssembly getDefaultCycleSeat(Date pricingDate) {
		return getCycleSeat("normalCycleSeat", pricingDate);
	}

}
