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

public class Renderer
{
	public WindowRenderTarget device;
	public Renderer()
	{
	}
	protected void finalize() throws Throwable
	{
	}
	public Renderer(WindowRenderTarget renderTarget)
	{
		device = renderTarget;
	}
	public final void DrawBoxESP(SolidColorBrush brush, System.Drawing.Rectangle boxRect)
	{
		DrawRectangle(brush, false, boxRect.X, boxRect.Y, boxRect.Width, boxRect.Height); // Middle
	}
	public final void DrawRectangle(SolidColorBrush brush, boolean Filled, float x, float y, float width, float height)
	{
		if (!Filled)
		{
			device.DrawRectangle(new RawRectangleF(x, y, x + width, y + height), brush);
		}
		else
		{
			device.FillRectangle(new RawRectangleF(x, y, x + width, y + height), brush);
		}
	}
	public final void DrawText(String text, SharpDX.Mathematics.Interop.RawVector2 pos, SolidColorBrush brush, TextFormat font)
	{
		device.DrawText(text, font, new RawRectangleF(pos.X, pos.Y, pos.X + (font.FontSize * text.length()), pos.Y + font.FontSize), brush);
	}
	public final void DrawLine(float x1, float y1, float x2, float y2, SolidColorBrush solidColorBrush)
	{
		device.DrawLine(new RawVector2(x1, y1), new RawVector2(x2, y2), solidColorBrush);
	}
	public final void DrawCircle(Ellipse circle, SolidColorBrush brush, boolean Filled)
	{
		if (!Filled)
		{
			device.DrawEllipse(circle, brush);
		}
		else
		{
			device.FillEllipse(circle, brush);
		}
	}
	public final void RectHealthBar(float x, float y, float w, float h, int HP)
	{
		SolidColorBrush healthBrush = new SolidColorBrush(device, SharpDX.Color.Green);

		if (HP > 100)
		{
			HP = 100;
		}
		int size = (int)((h * HP) / 100);

		if (size == h)
		{
			size -= 2;
		}

		if (HP != 100)
		{
			SolidColorBrush lackOfHealthBrush = new SolidColorBrush(device, SharpDX.Color.Red);
			DrawRectangle(lackOfHealthBrush, true, x + 1, y + 1, w - 2, h - size);
			lackOfHealthBrush.Dispose();
		}

		DrawRectangle(healthBrush, true, x + 1, y + 1 + h - size, w - 2, size);

		healthBrush.Dispose();
	}
}