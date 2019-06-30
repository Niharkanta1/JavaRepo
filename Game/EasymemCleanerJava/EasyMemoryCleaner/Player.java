package EasyMemoryCleaner;

public class Player extends BaseClass
{
	public Player(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final IntPtr GetBoneMatrix()
	{
		IntPtr temp = mem.<IntPtr>Read(addr + 0x88);
		IntPtr temp1 = mem.<IntPtr>Read(temp + 0x28);
		IntPtr temp2 = mem.<IntPtr>Read(temp1 + 0x28);
		return mem.<IntPtr>Read(temp2 + 0x10);
	}

	public final PlayerProfile GetPlayerProfile()
	{
		return new PlayerProfile(mem.<Long>Read(addr + 0x490), mem);
		;
	}

	public final BodyController GetBodyController()
	{
		return new BodyController(mem.<Long>Read(mem.<Long>Read(mem.<Long>Read(addr + 0x4c0) + 0x18) + 0x28), mem);
	}

	public final boolean IsLocal()
	{
		return mem.<Boolean>Read(addr + 0x18);
	}

	public final MovementContext GetMovementContext()
	{
		return new MovementContext(mem.<Long>Read(addr + 0x38), mem);
	}
}