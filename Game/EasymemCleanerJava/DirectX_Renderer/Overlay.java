package DirectX_Renderer;

import SharpDX.Direct2D1.*;
import SharpDX.*;
import SharpDX.DirectWrite.*;
import SharpDX.Mathematics.Interop.*;

//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using Factory = SharpDX.Direct2D1.Factory;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using FontFactory = SharpDX.DirectWrite.Factory;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using Format = SharpDX.DXGI.Format;

public class Overlay extends Form
{
	private WindowRenderTarget device;
	private HwndRenderTargetProperties renderProperties;
	private SharpDX.Direct2D1.Factory factory;

	private static final String WINDOW_NAME = "EscapeFromTarkov";

	private IntPtr handle = new IntPtr();
	private IntPtr gameHandle = FindWindow(null, WINDOW_NAME);
	private Thread threadDX = null;

//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: public struct RECT
	public final static class RECT
	{
		public int left, top, right, bottom;

		public RECT clone()
		{
			RECT varCopy = new RECT();

			varCopy.left = this.left;
			varCopy.top = this.top;
			varCopy.right = this.right;
			varCopy.bottom = this.bottom;

			return varCopy;
		}
	}

	//DllImports
	private static native IntPtr FindWindow(String lpClassName, String lpWindowName);
	static
	{
		System.loadLibrary("user32.dll");
	}

	public static native boolean GetWindowRect(IntPtr hwnd, tangible.RefObject<RECT> lpRect);

	public static native int SetWindowLong(IntPtr hWnd, int nIndex, int dwNewLong);

	public static native int GetWindowLong(IntPtr hWnd, int nIndex);

	private static native IntPtr SetActiveWindow(IntPtr handle);

	//Styles
	private static final int WS_EX_NOACTIVATE = 0x08000000;
	private static final int WS_EX_TOPMOST = 0x00000008;
	private static final int WM_ACTIVATE = 6;
	private static final int WA_INACTIVE = 0;
	private static final int WM_MOUSEACTIVATE = 0x0021;
	private static final int MA_NOACTIVATEANDEAT = 0x0004;

	private EasyMemoryCleaner.EasyMemoryCleaner cheat = null;

	public Overlay()
	{
		this.SetStyle(ControlStyles.SupportsTransparentBackColor, true);

		InitializeComponent();

		this.handle = Handle;
		int initialStyle = GetWindowLong(this.Handle, -20);
		SetWindowLong(this.Handle, -20, initialStyle | 0x80000 | 0x20);
		RECT rect = new RECT();
		tangible.RefObject<RECT> tempRef_rect = new tangible.RefObject<RECT>(rect);
		GetWindowRect(gameHandle, tempRef_rect);
		rect = tempRef_rect.argValue;
		this.Size = new Size(rect.right - rect.left, rect.bottom - rect.top);
		this.Top = rect.top;
		this.Left = rect.left;
	}

	private void Overlay_SharpDX_Load(Object sender, EventArgs e)
	{
		this.DoubleBuffered = true;
		this.TopMost = true;
		this.Visible = true;

		factory = new SharpDX.Direct2D1.Factory();

		this.SetStyle(ControlStyles.OptimizedDoubleBuffer | ControlStyles.AllPaintingInWmPaint | ControlStyles.DoubleBuffer | ControlStyles.UserPaint | ControlStyles.Opaque | ControlStyles.ResizeRedraw | ControlStyles.SupportsTransparentBackColor, true); // reduce the flicker too

		HwndRenderTargetProperties tempVar = new HwndRenderTargetProperties();
		tempVar.Hwnd = this.Handle;
		tempVar.PixelSize = new Size2(this.Width, this.Height);
		tempVar.PresentOptions = PresentOptions.None;
		renderProperties = tempVar;

		//Init DirectX
		device = new WindowRenderTarget(factory, new RenderTargetProperties(new PixelFormat(SharpDX.DXGI.Format.B8G8R8A8_UNorm, AlphaMode.Premultiplied)), renderProperties);

		cheat = new EasyMemoryCleaner.EasyMemoryCleaner(new UnityEngine.Vector2(this.Size.Width, this.Size.Height));

		Thread refreshSetupThread = new Thread(new ThreadStart(cheat.ReadWorlds));
		refreshSetupThread.IsBackground = true;
		refreshSetupThread.start();

		threadDX = new Thread(new ParameterizedThreadStart(_loop_DXThread));
		threadDX.Priority = ThreadPriority.Highest;
		threadDX.IsBackground = true;
		threadDX.start();
	}

	private void _loop_DXThread(Object sender)
	{
		while (true)
		{
			device.BeginDraw();
			device.Clear(SharpDX.Color.Transparent);
			device.TextAntialiasMode = SharpDX.Direct2D1.TextAntialiasMode.Default;

			cheat.Draw(device);

			device.EndDraw();
		}
	}

	/** 
	 Used to not show up the form in alt-tab window. 
	 Tested on Windows 7 - 64bit and Windows 10 64bit
	*/
	@Override
	protected CreateParams getCreateParams()
	{
		CreateParams pm = super.getCreateParams();
		pm.ExStyle |= 0x80;
		pm.ExStyle |= WS_EX_TOPMOST; // make the form topmost
		pm.ExStyle |= WS_EX_NOACTIVATE; // prevent the form from being activated
		return pm;
	}

	/** 
	 Makes the form unable to gain focus at all time, 
	 which should prevent lose focus
	*/
	@Override
	protected void WndProc(tangible.RefObject<Message> m)
	{
		if (m.argValue.Msg == WM_MOUSEACTIVATE)
		{
			m.argValue.Result = (IntPtr)MA_NOACTIVATEANDEAT;
			return;
		}
		if (m.argValue.Msg == WM_ACTIVATE)
		{
			if (((int)m.argValue.WParam & 0xFFFF) != WA_INACTIVE)
			{
				if (m.argValue.LParam != IntPtr.Zero)
				{
					SetActiveWindow(m.argValue.LParam);
				}
				else
				{
					SetActiveWindow(IntPtr.Zero);
				}
			}
		}
		else
		{
			super.WndProc(m);
		}
	}


	/** 
	 Required designer variable.
	*/
	private System.ComponentModel.IContainer components = null;

	/** 
	 Clean up any resources being used.
	 
	 @param disposing true if managed resources should be disposed; otherwise, false.
	*/
	@Override
	protected void Dispose(boolean disposing)
	{
		if (disposing && (components != null))
		{
			components.Dispose();
		}
		super.Dispose(disposing);
	}

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#region Windows Form Designer generated code

	/** 
	 Required method for Designer support - do not modify
	 the contents of this method with the code editor.
	*/
	private void InitializeComponent()
	{
		this.SuspendLayout();
		// 
		// Overlay_SharpDX
		// 
		this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
		this.BackColor = System.Drawing.Color.Black;
		this.ClientSize = new System.Drawing.Size(820, 425);
		this.DoubleBuffered = true;
		this.ForeColor = System.Drawing.SystemColors.ControlText;
		this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
		this.Name = "Overlay_SharpDX";
		this.ShowIcon = false;
		this.ShowInTaskbar = false;
		this.Text = "Overlay";
		this.TopMost = true;
		this.TransparencyKey = System.Drawing.Color.Black;
//C# TO JAVA CONVERTER TODO TASK: Java has no equivalent to C#-style event wireups:
		this.Load += new System.EventHandler(this.Overlay_SharpDX_Load);
		this.ResumeLayout(false);
	}

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
		///#endregion
}