package EasyMemoryCleaner;

public class MovementContext extends BaseClass
{
	public MovementContext(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final UnityEngine.Vector3 GetLocation()
	{
		return mem.<UnityEngine.Vector3>Read(addr + 0x68);
	} // WILL ONLY UPDATE WHEN TARGET IS WITHIN FOV, USE BASE BONE POSITION | SHOULD ONLY BE USED FOR LOCAL
}