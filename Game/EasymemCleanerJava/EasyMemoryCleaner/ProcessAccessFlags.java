package EasyMemoryCleaner;

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: [Flags] public enum ProcessAccessFlags : uint
public class ProcessAccessFlags 
{
	public static final ProcessAccessFlags All = new ProcessAccessFlags(0x001F0FFF);
	public static final ProcessAccessFlags Terminate = new ProcessAccessFlags(0x00000001);
	public static final ProcessAccessFlags CreateThread = new ProcessAccessFlags(0x00000002);
	public static final ProcessAccessFlags VMOperation = new ProcessAccessFlags(0x00000008);
	public static final ProcessAccessFlags VMRead = new ProcessAccessFlags(0x00000010);
	public static final ProcessAccessFlags VMWrite = new ProcessAccessFlags(0x00000020);
	public static final ProcessAccessFlags DupHandle = new ProcessAccessFlags(0x00000040);
	public static final ProcessAccessFlags SetInformation = new ProcessAccessFlags(0x00000200);
	public static final ProcessAccessFlags QueryInformation = new ProcessAccessFlags(0x00000400);
	public static final ProcessAccessFlags Synchronize = new ProcessAccessFlags(0x00100000);

	private int intValue;
	private static java.util.HashMap<Integer, ProcessAccessFlags> mappings;
	private static java.util.HashMap<Integer, ProcessAccessFlags> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ProcessAccessFlags.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ProcessAccessFlags>();
				}
			}
		}
		return mappings;
	}

	private ProcessAccessFlags(int value)
	{
		intValue = value;
		synchronized (ProcessAccessFlags.class)
		{
			getMappings().put(value, this);
		}
	}

	public int getValue()
	{
		return intValue;
	}

	public static ProcessAccessFlags forValue(int value)
	{
		synchronized (ProcessAccessFlags.class)
		{
			ProcessAccessFlags enumObj = getMappings().get(value);
			if (enumObj == null)
			{
				return new ProcessAccessFlags(value);
			}
			else
			{
				return enumObj;
			}
		}
	}
}