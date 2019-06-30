package EasyMemoryCleaner;

public class BaseObject extends BaseClass
{
	public BaseObject(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final long GetGameObjectAddr()
	{
		return mem.<Long>Read(addr + 0x10);
	}

	public final GameObject GetGameObject()
	{
		return new GameObject(GetGameObjectAddr(), mem);
	}

	public final long GetNextBaseObjectAddr()
	{
		return mem.<Long>Read(addr + 0x8);
	}

	public final BaseObject GetNextBaseObject()
	{
		return new BaseObject(GetNextBaseObjectAddr(), mem);
	}
}