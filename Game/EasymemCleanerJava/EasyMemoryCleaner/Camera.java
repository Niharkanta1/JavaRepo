package EasyMemoryCleaner;

public class Camera extends GameObject
{
	private long matrixLeadup;
	private Matrix4x4 _viewMatrix;

	public Camera(long addr, Memory mem)
	{
		super(addr, mem);
		matrixLeadup = mem.<Long>Read(mem.<Long>Read(addr + 0x30) + 0x18);
	}

	public final void GetViewmatrix()
	{
		_viewMatrix = mem.<Matrix4x4>Read(matrixLeadup + 0xC0);
	}

	public final boolean WorldToScreen(UnityEngine.Vector3 _Enemy, tangible.RefObject<UnityEngine.Vector3> _Screen, UnityEngine.Vector2 windowSize)
	{
		_Screen.argValue = new UnityEngine.Vector3(0, 0, 0);

		//Getting viewmatrix from fpsCamera
		//fpsCamera, new int[] { 0x30, 0x18, 0xC0 }

		Matrix4x4 temp = Matrix4x4.Transpose(_viewMatrix);

		UnityEngine.Vector3 translationVector = new UnityEngine.Vector3(temp.M41, temp.M42, temp.M43);
		UnityEngine.Vector3 up = new UnityEngine.Vector3(temp.M21, temp.M22, temp.M23);
		UnityEngine.Vector3 right = new UnityEngine.Vector3(temp.M11, temp.M12, temp.M13);

		float w = D3DXVec3Dot(translationVector, _Enemy) + temp.M44;

		if (w < 0.098f)
		{
			return false;
		}

		float y = D3DXVec3Dot(up, _Enemy) + temp.M24;
		float x = D3DXVec3Dot(right, _Enemy) + temp.M14;

		_Screen.argValue.x = (windowSize.x / 2) * (1f + x / w);
		_Screen.argValue.y = (windowSize.y / 2) * (1f - y / w);
		_Screen.argValue.z = w;

		return true;

	}

	private float D3DXVec3Dot(UnityEngine.Vector3 a, UnityEngine.Vector3 b)
	{
		return (a.x * b.x + a.y * b.y + a.z * b.z);
	}
}