package EasyMemoryCleaner;

import Mono.Simd.*;
import SharpDX.*;
import SharpDX.Direct2D1.*;
import SharpDX.DirectWrite.*;
import SharpDX.Mathematics.Interop.*;
import DirectX_Renderer.*;

//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using FontFactory = SharpDX.DirectWrite.Factory;

public final class Program
{
	/** 
	 The main entry point for the application.
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [STAThread] static void Main()
	static void main()
	{
		Application.EnableVisualStyles();
		Application.SetCompatibleTextRenderingDefault(false);
		Application.Run(new Overlay());
	}
}