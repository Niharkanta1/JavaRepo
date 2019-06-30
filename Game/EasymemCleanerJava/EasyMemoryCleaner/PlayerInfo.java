package EasyMemoryCleaner;

public class PlayerInfo extends BaseClass
{
	public PlayerInfo(long addr, Memory mem)
	{
		super(addr, mem);
	}

	public final String GetName()
	{
		return mem.UReadString(addr + 0x10);
	}

	public final int GetRegistrationDate()
	{
		return mem.<Integer>Read(addr + 0x54);
	}

	private static String[] CyrilicToLatinL = "a,b,v,g,d,e,zh,z,i,j,k,l,m,n,o,p,r,s,t,u,f,kh,c,ch,sh,sch,j,y,j,e,yu,ya".split("[,]", -1);
	private static String[] CyrilicToLatinU = "A,B,V,G,D,E,Zh,Z,I,J,K,L,M,N,O,P,R,S,T,U,F,Kh,C,Ch,Sh,Sch,J,Y,J,E,Yu,Ya".split("[,]", -1);

	public static String CyrilicToLatin(String s)
	{
		StringBuilder sb = new StringBuilder((int)(s.length() * 1.5));
		for (char c : s)
		{
			if (c >= '\u0430' && c <= '\u044f')
			{
				sb.append(CyrilicToLatinL[c - '\u0430']);
			}
			else if (c >= '\u0410' && c <= '\u042f')
			{
				sb.append(CyrilicToLatinU[c - '\u0410']);
			}
			else if (c == '\u0401')
			{
				sb.append("Yo");
			}
			else if (c == '\u0451')
			{
				sb.append("yo");
			}
			else
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public final boolean IsScavBoss(String name)
	{
		String converted = CyrilicToLatin(name);
		return (converted.equals("Reshala") || converted.equals("Killa"));
	}
}