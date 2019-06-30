package EasyMemoryCleaner;

public class BodyController extends BaseClass
{
	public BodyController(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public enum BodyParts
	{
		Head(0x20),
		Thorax(0x28),
		Stomach(0x30),
		LeftArm(0x38),
		RightArm(0x40),
		LeftLeg(0x48),
		RightLeg(0x50);

		private int intValue;
		private static java.util.HashMap<Integer, BodyParts> mappings;
		private static java.util.HashMap<Integer, BodyParts> getMappings()
		{
			if (mappings == null)
			{
				synchronized (BodyParts.class)
				{
					if (mappings == null)
					{
						mappings = new java.util.HashMap<Integer, BodyParts>();
					}
				}
			}
			return mappings;
		}

		private BodyParts(int value)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static BodyParts forValue(int value)
		{
			return getMappings().get(value);
		}
	}

	public final float GetBodyPartHP(BodyParts part)
	{
		return mem.<Float>Read(mem.<Long>Read(mem.<Long>Read(addr + part.getValue()) + 0x10) + 0x10);
	}

	public final float GetMaxBodyPartHP(BodyParts part)
	{
		return mem.<Float>Read(mem.<Long>Read(mem.<Long>Read(addr + part.getValue()) + 0x10) + 0x14);
	}

	public final float GetMaxSumOfAllBodyParts()
	{
		if (GetMaxBodyPartHP(BodyParts.Thorax) == 80)
		{
			return 435;
		}
		else
		{
			return 725;
		}
	}

	public final float GetSumOfAllBodyParts()
	{
		float ret = 0f;
		for (BodyParts part : BodyParts.values())
		{
			ret += GetBodyPartHP(part);
		}
		return ret;
	}

	public final float GetHealthPercentage()
	{
		return (GetSumOfAllBodyParts() / GetMaxSumOfAllBodyParts()) * 100;
	}
}