package parent.cycle.parts.chainassembly;

import java.util.Date;

public class ChainAssemblyFactory {

	public static IChainAssembly getCycleChainAssembly(String type, Date pricingDate) {

		switch (type) {
		case "normalChainAssembly":
			return new NormalCycleChainAssembly(pricingDate);
		case "sportsChainAssembly":
			return new SportsCycleChainAssembly(pricingDate);
		default:
			return new NormalCycleChainAssembly(pricingDate);
		}

	}

	public static IChainAssembly getDefaultCycleChainAssembly(Date pricingDate) {
		return getCycleChainAssembly("normalChainAssembly", pricingDate);
	}

}
