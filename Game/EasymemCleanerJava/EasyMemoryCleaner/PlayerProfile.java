package EasyMemoryCleaner;

public class PlayerProfile extends BaseClass
{
	public PlayerProfile(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final boolean IsPlayer()
	{
		return mem.<Boolean>Read(addr + 0x20);
	}

	public final PlayerInfo GetPlayerInfo()
	{
		return new PlayerInfo(mem.<Long>Read(addr + 0x28), mem);
	}
}