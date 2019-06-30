package EasyMemoryCleaner;

public class LocalGameWorld extends BaseClass
{
	public LocalGameWorld(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final long GetPlayerArrayObjectAddr()
	{
		return mem.<Long>Read(addr + 0x60);
	}

	public final PlayerList GetPlayerArrayObject()
	{
		return new PlayerList(GetPlayerArrayObjectAddr(), mem);
	}
}