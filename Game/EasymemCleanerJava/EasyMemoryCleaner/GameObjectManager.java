package EasyMemoryCleaner;

public class GameObjectManager extends BaseClass
{
	public GameObjectManager(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final long GetFirstActiveObjectAddr()
	{
		return mem.<Long>Read(addr + 0x18);
	}

	public final BaseObject GetFirstActiveObject()
	{
		return new BaseObject(GetFirstActiveObjectAddr(), mem);
	}
	//not tested
	public final long GetLastActiveObjectAddr()
	{
		return mem.<Long>Read(addr + 0x10);
	}
	//not tested
	public final BaseObject GetLastActiveObject()
	{
		return new BaseObject(GetLastActiveObjectAddr(), mem);
	}


	public final long GetFirstTaggedObjectAddr()
	{
		return mem.<Long>Read(addr + 0x8);
	}

	public final BaseObject GetFirstTaggedObject()
	{
		return new BaseObject(GetFirstTaggedObjectAddr(), mem);
	}
	//not tested
	public final long GetLastTaggedObjectAddr()
	{
		return mem.<Long>Read(addr + 0x0);
	}
	//not tested
	public final BaseObject GetLastTaggedObject()
	{
		return new BaseObject(GetLastTaggedObjectAddr(), mem);
	}
}