package EasyMemoryCleaner;

public class GameObject extends BaseClass
{
	public GameObject()
	{
		super();
	}
	public GameObject(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final long GetGameObjectNameAddr()
	{
		return mem.<Long>Read(addr + 0x60);
	}

	public final String GetObjectName()
	{
		long objNameAddr = GetGameObjectNameAddr();
		int i = 0;
		for (; i < 256; i++)
		{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: if (mem.Read<byte>(objNameAddr + i) == 0)
			if (mem.<Byte>Read(objNameAddr + i) == 0)
			{
				break;
			}
		}
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] b = mem.ReadByteArray(objNameAddr, i);
		byte[] b = mem.ReadByteArray(objNameAddr, i);

		return System.Text.Encoding.UTF8.GetString(b);
	}

	public final long GetN65Addr()
	{
		return mem.<Long>Read(addr + 0x30);
	}

	public final long GetLocalGameWorldObjectAddr()
	{
		long n65a = mem.<Long>Read(addr + 0x30);
		long n70a = mem.<Long>Read(n65a + 0x18);
		long lgwa = mem.<Long>Read(n70a + 0x28);
		return lgwa;
	}

	public final LocalGameWorld GetLocalGameWorldObject()
	{
		return new LocalGameWorld(GetLocalGameWorldObjectAddr(), mem);
	}

}