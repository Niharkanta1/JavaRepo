package EasyMemoryCleaner;

import Mono.Simd.*;
import SharpDX.*;
import SharpDX.Direct2D1.*;
import SharpDX.DirectWrite.*;
import SharpDX.Mathematics.Interop.*;
import DirectX_Renderer.*;

public class EasyMemoryCleaner
{
	public Memory mem = new Memory();
	public UnityEngine.Vector2 windowSize = new UnityEngine.Vector2(0, 0);

	public enum eHumanBones
	{
		HumanBase(0),
		HumanPelvis(14),
		HumanLThigh1(15),
		HumanLThigh2(16),
		HumanLCalf(17),
		HumanLFoot(18),
		HumanLToe(19),
		HumanRThigh1(20),
		HumanRThigh2(21),
		HumanRCalf(22),
		HumanRFoot(23),
		HumanRToe(24),
		HumanSpine1(29),
		HumanSpine2(36),
		HumanSpine3(37),
		HumanLCollarbone(89),
		HumanLUpperarm(90),
		HumanLForearm1(91),
		HumanLForearm2(92),
		HumanLForearm3(93),
		HumanLPalm(94),
		HumanRCollarbone(110),
		HumanRUpperarm(111),
		HumanRForearm1(112),
		HumanRForearm2(113),
		HumanRForearm3(114),
		HumanRPalm(115),
		HumanNeck(132),
		HumanHead(133);

		private int intValue;
		private static java.util.HashMap<Integer, eHumanBones> mappings;
		private static java.util.HashMap<Integer, eHumanBones> getMappings()
		{
			if (mappings == null)
			{
				synchronized (eHumanBones.class)
				{
					if (mappings == null)
					{
						mappings = new java.util.HashMap<Integer, eHumanBones>();
					}
				}
			}
			return mappings;
		}

		private eHumanBones(int value)
		{
			intValue = value;
			getMappings().put(value, this);
		}

		public int getValue()
		{
			return intValue;
		}

		public static eHumanBones forValue(int value)
		{
			return getMappings().get(value);
		}
	}

//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: private struct Matrix34
	private final static class Matrix34
	{
		public Vector4f vec0;
		public Vector4f vec1;
		public Vector4f vec2;

		public Matrix34 clone()
		{
			Matrix34 varCopy = new Matrix34();

			varCopy.vec0 = this.vec0;
			varCopy.vec1 = this.vec1;
			varCopy.vec2 = this.vec2;

			return varCopy;
		}
	}
	private IntPtr GetPtr(IntPtr addr, int offset, Memory mem)
	{
		return IntPtr.Add((IntPtr)(mem.<Long>Read((long)addr)), offset);
	}
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to the 'unsafe' modifier in Java:
//ORIGINAL LINE: [HandleProcessCorruptedStateExceptions][SecurityCritical] public unsafe UnityEngine.Vector3 INTERNAL__getPosition(IntPtr addr, Memory mem)
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
	public final UnityEngine.Vector3 INTERNAL__getPosition(IntPtr addr, Memory mem)
	{
		try
		{
			IntPtr transform_internal = IntPtr.Add(addr, 0x10);
			if (transform_internal.ToInt64() == 0x0)
			{
				return new UnityEngine.Vector3();
			}

			IntPtr pMatrix = GetPtr(transform_internal, 0x38, mem);
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: int index = mem.Read<int>(GetPtr(transform_internal, 0x38 + sizeof(UInt64), mem).ToInt64());
			int index = mem.<Integer>Read(GetPtr(transform_internal, 0x38 + Long.SIZE, mem).ToInt64());
			if (pMatrix.ToInt64() == 0x0)
			{
				return new UnityEngine.Vector3();
			}

			IntPtr matrix_list_base = GetPtr(pMatrix, 0x8, mem);
			if (matrix_list_base.ToInt64() == 0x0)
			{
				return new UnityEngine.Vector3();
			}

			IntPtr dependency_index_table_base = GetPtr(pMatrix, 0x10, mem);
			if (dependency_index_table_base.ToInt64() == 0x0)
			{
				return new UnityEngine.Vector3();
			}

//C# TO JAVA CONVERTER TODO TASK: There is no Java equivalent to 'sizeof':
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] pMatricesBuff = mem.ReadByteArray(GetPtr(matrix_list_base, 0, mem).ToInt64(), (sizeof(Matrix34) * index) + sizeof(Matrix34));
			byte[] pMatricesBuff = mem.ReadByteArray(GetPtr(matrix_list_base, 0, mem).ToInt64(), (sizeof(Matrix34) * index) + sizeof(Matrix34));
			GCHandle pMatricesBufff = GCHandle.Alloc(pMatricesBuff, GCHandleType.Pinned);
			void * pMatricesBuf = pMatricesBufff.AddrOfPinnedObject().ToPointer();

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: byte[] pIndicesBuff = mem.ReadByteArray(GetPtr(dependency_index_table_base, 0, mem).ToInt64(), sizeof(int) * index + sizeof(int));
			byte[] pIndicesBuff = mem.ReadByteArray(GetPtr(dependency_index_table_base, 0, mem).ToInt64(), Integer.SIZE * index + Integer.SIZE);
			GCHandle pIndicesBufff = GCHandle.Alloc(pIndicesBuff, GCHandleType.Pinned);
			void * pIndicesBuf = pIndicesBufff.AddrOfPinnedObject().ToPointer();

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: Vector4f result = *(Vector4f*)((UInt64)pMatricesBuf + 0x30 * (UInt64)index);
			Vector4f result = *(Vector4f*)((long)pMatricesBuf + 0x30 * (long)index);

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: int index_relation = *(int*)((UInt64)pIndicesBuf + 0x4 * (UInt64)index);
			int index_relation = *(int*)((long)pIndicesBuf + 0x4 * (long)index);

			Vector4f xmmword_1410D1340 = new Vector4f(-2.0f, 2.0f, -2.0f, 0.0f);
			Vector4f xmmword_1410D1350 = new Vector4f(2.0f, -2.0f, -2.0f, 0.0f);
			Vector4f xmmword_1410D1360 = new Vector4f(-2.0f, -2.0f, 2.0f, 0.0f);

			int tries = 10000;
			int tried = 0;

			try
			{
				while (index_relation >= 0)
				{
					if (tried++ > tries)
					{
						break;
					}
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: Matrix34 matrix34 = *(Matrix34*)((UInt64)pMatricesBuf + (0x30 * (UInt64)index_relation));
					Matrix34 matrix34 = *(Matrix34*)((long)pMatricesBuf + (0x30 * (long)index_relation));

					Vector4f v10 = matrix34.vec2 * result;
					Vector4f v11 = (Vector4f)(VectorOperations.Shuffle(matrix34.vec1, (ShuffleSel)(0)));
					Vector4f v12 = (Vector4f)(VectorOperations.Shuffle(matrix34.vec1, (ShuffleSel)(85)));
					Vector4f v13 = (Vector4f)(VectorOperations.Shuffle(matrix34.vec1, (ShuffleSel)(-114)));
					Vector4f v14 = (Vector4f)(VectorOperations.Shuffle(matrix34.vec1, (ShuffleSel)(-37)));
					Vector4f v15 = (Vector4f)(VectorOperations.Shuffle(matrix34.vec1, (ShuffleSel)(-86)));
					Vector4f v16 = (Vector4f)(VectorOperations.Shuffle(matrix34.vec1, (ShuffleSel)(113)));
					result = (((((((v11 * xmmword_1410D1350) * v13) - ((v12 * xmmword_1410D1360) * v14)) * VectorOperations.Shuffle(v10, (ShuffleSel)(-86))) + ((((v15 * xmmword_1410D1360) * v14) - ((v11 * xmmword_1410D1340) * v16)) * VectorOperations.Shuffle(v10, (ShuffleSel)(85)))) + (((((v12 * xmmword_1410D1340) * v16) - (v15 * xmmword_1410D1350 * v13)) * VectorOperations.Shuffle(v10, (ShuffleSel)(0))) + v10)) + matrix34.vec0);

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: index_relation = *(int*)((UInt64)pIndicesBuf + 0x4 * (UInt64)index_relation);
					index_relation = *(int*)((long)pIndicesBuf + 0x4 * (long)index_relation);
				} // Not the cause of the memory leak
			}
			catch (RuntimeException e)
			{
				System.out.println("Caught INTERNAL__getPosition exception - " + e.toString());
				pIndicesBufff.Free();
				pMatricesBufff.Free();
				return new UnityEngine.Vector3();
			}

			pIndicesBufff.Free();
			pMatricesBufff.Free();

			return new UnityEngine.Vector3(result.X, result.Y, result.Z);
		}
		catch (RuntimeException e)
		{
			System.out.println("GetBonePosition Crashed");
			return new UnityEngine.Vector3();
		}
	}

	public EasyMemoryCleaner(UnityEngine.Vector2 win)
	{
		windowSize = win;
	}

	private int paintCalled = 0;
	private Camera FPSCamera = null;
	private GameObject GW = null;
	private LocalGameWorld LGW = null;
	private Player[] players = null;

	private Player local;

	private Renderer rend = new Renderer();

	public final void ReadWorlds()
	{
		while (true)
		{
			Thread.sleep(5);

			paintCalled++;

			try
			{
				GW = mem.FindActiveObject("GameWorld");
				LGW = GW.GetLocalGameWorldObject();

				players = LGW.GetPlayerArrayObject().GetPlayers();
				if (paintCalled >= 500 || FPSCamera == null)
				{
					FPSCamera = new Camera(mem.FindTaggedObject("fps camera").addr, mem);
					paintCalled = 0;
				}
				FPSCamera.GetViewmatrix();
			}
			catch (RuntimeException e)
			{
				System.out.println("ReadWorlds crashed - " + e.toString());
				continue;
			}
		}
	}

	public final void Draw(WindowRenderTarget device)
	{
		rend.device = device;

		SolidColorBrush solidColorBrush = new SolidColorBrush(device, SharpDX.Color.Red);
		TextFormat espFont = new TextFormat(new SharpDX.DirectWrite.Factory(), "Tahoma Bold", 10f);

		Thread.sleep(5);

		if (LGW == null || GW == null || players == null || FPSCamera == null)
		{
			return;
		}

		UnityEngine.Vector3 LocalLocation = new UnityEngine.Vector3();

		int Players = 0, Scavs = 0, PScavs = 0;

		for (Player p : players)
		{
			boolean Local = p.IsLocal();

			if (Local)
			{
				local = p;
				LocalLocation = local.GetMovementContext().GetLocation();
			}

			if (p == null || LocalLocation == new UnityEngine.Vector3() || Local)
			{
				continue;
			}

			IntPtr BoneMatrix = p.GetBoneMatrix();
			UnityEngine.Vector3 Location = INTERNAL__getPosition(mem.<IntPtr>Read(BoneMatrix + (0x20 + (eHumanBones.HumanBase.getValue() * 8))), mem);

			if (Location == new UnityEngine.Vector3(0, 0, Location.z))
			{
				continue;
			}

			PlayerProfile pProfile = p.GetPlayerProfile();
			boolean IsPlayer = pProfile.IsPlayer();
			PlayerInfo pInfo = pProfile.GetPlayerInfo();

			int RegisterDate = pInfo.GetRegistrationDate();

			if (IsPlayer == false)
			{
				if (RegisterDate != 0)
				{
					PScavs++;
				}
				else
				{
					Scavs++;
				}
			}
			else
			{
				Players++;
			}

			UnityEngine.Vector3 baseW2S, headW2S = null;

			tangible.RefObject<UnityEngine.Vector3> tempRef_baseW2S = new tangible.RefObject<UnityEngine.Vector3>(baseW2S);
			boolean tempVar = !FPSCamera.WorldToScreen(Location, tempRef_baseW2S, windowSize);
				baseW2S = tempRef_baseW2S.argValue;
			if (tempVar)
			{
				continue;
			}

			float _Distance = UnityEngine.Vector3.Distance(LocalLocation, Location);

			if (baseW2S == new UnityEngine.Vector3(0, 0, baseW2S.z) || _Distance >= 200 || baseW2S.x > windowSize.x + 150 || baseW2S.x < -150 || baseW2S.y < -150)
			{
				continue;
			}

			UnityEngine.Vector3 headPos = INTERNAL__getPosition(mem.<IntPtr>Read(BoneMatrix + (0x20 + (eHumanBones.HumanHead.getValue() * 8))), mem);

			tangible.RefObject<UnityEngine.Vector3> tempRef_headW2S = new tangible.RefObject<UnityEngine.Vector3>(headW2S);
			boolean tempVar2 = !FPSCamera.WorldToScreen(headPos, tempRef_headW2S, windowSize);
				headW2S = tempRef_headW2S.argValue;
			if (tempVar2)
			{
				continue;
			}

			UnityEngine.Vector2 wSize;
			wSize.y = Math.abs(headW2S.y - baseW2S.y) * 1.3f;
			wSize.x = (wSize.y / 1.7f);

			System.Drawing.Rectangle boxRect = new System.Drawing.Rectangle((int)(baseW2S.x - (wSize.x / 2)), (int)(baseW2S.y - wSize.y), (int)wSize.x, (int)wSize.y);

			rend.DrawBoxESP(solidColorBrush, boxRect);

			String pName = "N/A";

			if (IsPlayer == false)
			{
				if (RegisterDate != 0)
				{
					pName = "Player Scav";
				}
				else
				{
					pName = "Scav";
				}
			}
			else
			{
				Players++;
				pName = pInfo.GetName();
			}

			solidColorBrush.Color = Color.White;

			if (_Distance <= 100f)
			{
				rend.RectHealthBar(baseW2S.x - ((wSize.x + 12) / 2), (baseW2S.y - wSize.y - 1), 3.5f, wSize.y, (int)p.GetBodyController().GetHealthPercentage());
			}

			float headSZ;
			if (_Distance == 0)
			{
				headSZ = 20;
			}
			headSZ = 2 + (20 / _Distance);
			rend.DrawCircle(new Ellipse(new RawVector2(headW2S.x, headW2S.y - headSZ), headSZ, headSZ), solidColorBrush, false);

			rend.DrawText(pName + " (" + (int)_Distance + "m)", new RawVector2(boxRect.Left, boxRect.Bottom + 1), solidColorBrush, espFont);
			solidColorBrush.Color = Color.Red;
		}

		solidColorBrush.Color = Color.White;
		TextFormat watermarkFont = new TextFormat(new SharpDX.DirectWrite.Factory(), "Tahoma", 15f);
		rend.DrawText("Players: " + Players, new RawVector2(5, 5), solidColorBrush, watermarkFont);
		rend.DrawText("Player Scavs: " + PScavs, new RawVector2(5, 20), solidColorBrush, watermarkFont);
		rend.DrawText("Scavs: " + Scavs, new RawVector2(5, 35), solidColorBrush, watermarkFont);

		watermarkFont.Dispose();
		solidColorBrush.Dispose();
		espFont.Dispose();
	}
}