package EasyMemoryCleaner;

public class Memory
{
	public static native IntPtr OpenProcess(ProcessAccessFlags dwDesiredAccess, boolean bInheritHandle, int dwProcessId);
	static
	{
		System.loadLibrary("Kernel32.dll");
	}

	public static native int CloseHandle(IntPtr handle);

	public static native boolean ReadProcessMemory(int hProcess, long lpBaseAddress, byte[] lpBuffer, int dwSize, tangible.RefObject<Integer> lpNumberOfBytesRead);
	static
	{
		System.loadLibrary("kernel32.dll");
	}

	private String processName = "EscapeFromTarkov";
	private IntPtr processHandle = IntPtr.Zero;
	private IntPtr baseAddr = IntPtr.Zero;
	private Process targetProcess = null;
	private GameObjectManager GOM = null;

	public Memory()
	{
		Process[] procID = Process.GetProcessesByName(processName);
		if (procID.length > 0)
		{
			targetProcess = procID[0];

			targetProcess.PriorityClass = ProcessPriorityClass.High;
			System.out.println("Setting " + processName + " priority to high");

			processHandle = OpenProcess(ProcessAccessFlags.VMRead, false, targetProcess.Id);

			baseAddr = targetProcess.MainModule.BaseAddress;

			GOM = new GameObjectManager(this.<Long>Read(baseAddr.ToInt64() + 0x1432840), this);
		}
		else
		{
			System.out.println("ERROR: Could not find " + processName + " process");
			Application.Exit();
		}

		Process[] dwmID = Process.GetProcessesByName("dwm");
		if (dwmID.length > 0)
		{
			dwmID[0].PriorityClass = ProcessPriorityClass.Idle; // Overlay makes DWM CPU usage skyrocket
			System.out.println("Setting dwm.exe priority to low");
		}
	}

	private int Exit()
	{
		return CloseHandle(processHandle);
	}

	public final GameObject FindActiveObject(String objName)
	{
		BaseObject activeObject = GOM.GetFirstActiveObject();
		do
		{
			if (activeObject.GetGameObject().GetObjectName().toLowerCase().equals(objName.toLowerCase()))
			{
				return activeObject.GetGameObject();
			}
			activeObject = activeObject.GetNextBaseObject();
		} while (activeObject.GetAddr() != GOM.GetFirstActiveObject().GetAddr());

		return null;
	}

	public final GameObject FindTaggedObject(String objName)
	{
		BaseObject taggedObject = GOM.GetFirstTaggedObject();
		int MAX_CT = 2000;
		int i = 0;
		do
		{
			i++;
			if (taggedObject.GetGameObject().GetObjectName().toLowerCase().equals(objName.toLowerCase()))
			{
				return taggedObject.GetGameObject();
			}
			if (i >= MAX_CT)
			{
				System.out.println("Overflow when finding tagged object: " + objName);
				return new GameObject();
			}
			taggedObject = taggedObject.GetNextBaseObject();
		} while (taggedObject.GetAddr() != GOM.GetFirstActiveObject().GetAddr());

		return new GameObject();
	}

	public final GameObject FindObject(BaseObject b, String objName)
	{
		BaseObject activeObject = b;
		do
		{
			if (activeObject.GetGameObject().GetObjectName().toLowerCase().equals(objName.toLowerCase()))
			{
				return activeObject.GetGameObject();
			}
			activeObject = activeObject.GetNextBaseObject();
		} while (activeObject.GetAddr() != b.GetAddr());
		return null;
	}

	public final String UReadString(long ptr)
	{
		long unicodeStringObjAddr = this.<Long>Read(ptr);
		int length = this.<Integer>Read(unicodeStringObjAddr + 0x10);
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] b = ReadByteArray(unicodeStringObjAddr + 0x14, length * 2);
		byte[] b = ReadByteArray(unicodeStringObjAddr + 0x14, length * 2);
		return System.Text.Encoding.Unicode.GetString(b);
	}

	public final <T> T Read(IntPtr ptr)
	{
		int size = System.Runtime.InteropServices.Marshal.SizeOf(T.class);
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] buf = new byte[size];
		byte[] buf = new byte[size];
		int read = 0;
		tangible.RefObject<Integer> tempRef_read = new tangible.RefObject<Integer>(read);
		ReadProcessMemory((int)processHandle, ptr.ToInt64(), buf, size, tempRef_read);
		read = tempRef_read.argValue;

		GCHandle handle = GCHandle.Alloc(buf, GCHandleType.Pinned);
		T data = (T)Marshal.PtrToStructure(handle.AddrOfPinnedObject(), T.class);
		handle.Free();
		return data;
	}
	public final <T> T Read(long ptr)
	{
		int size = System.Runtime.InteropServices.Marshal.SizeOf(T.class);
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] buf = new byte[size];
		byte[] buf = new byte[size];
		int read = 0;
		tangible.RefObject<Integer> tempRef_read = new tangible.RefObject<Integer>(read);
		ReadProcessMemory((int)processHandle, (long)ptr, buf, size, tempRef_read);
		read = tempRef_read.argValue;

		GCHandle handle = GCHandle.Alloc(buf, GCHandleType.Pinned);
		T data = (T)Marshal.PtrToStructure(handle.AddrOfPinnedObject(), T.class);
		handle.Free();
		return data;
	}

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: public byte[] ReadByteArray(long ptr, int sz)
	public final byte[] ReadByteArray(long ptr, int sz)
	{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] buf = new byte[sz];
		byte[] buf = new byte[sz];
		int read = 0;
		tangible.RefObject<Integer> tempRef_read = new tangible.RefObject<Integer>(read);
		ReadProcessMemory((int)processHandle, (long)ptr, buf, sz, tempRef_read);
		read = tempRef_read.argValue;
		return buf;
	}
}