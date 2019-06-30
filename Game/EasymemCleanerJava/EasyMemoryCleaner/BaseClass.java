package EasyMemoryCleaner;

public class BaseClass
{
	public long addr = 0;
	public Memory mem = null;

	public BaseClass()
	{
	}
	public BaseClass(long addr, Memory mem)
	{
		this.addr = addr;
		this.mem = mem;
	}

	public final long GetAddr()
	{
		return addr;
	}
}