package EasyMemoryCleaner;

public class PlayerList extends BaseClass
{
	public PlayerList(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final int GetCount()
	{
		return mem.<Integer>Read(addr + 0x18);
	}

	public final Player[] GetPlayers()
	{
		int playerCount = GetCount();

		long fpAddr = mem.<Long>Read(addr + 0x10);
		Player[] p = new Player[playerCount];
		for (int i = 0; i < playerCount; i++)
		{
			p[i] = new Player(mem.<Long>Read(fpAddr + 0x20 + (i * 0x8)), mem);
		}
		return p;
	}
}