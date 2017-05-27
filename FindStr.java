import java.util.Scanner;

class FindStr
{
  public static void main(String[] args)
  {
    Str a=new Str();
	a.Str();
    System.out.println("一共出现了"+a.CountFind()+"次匹配");
  }
}

class Str extends Record
{
  String putstr,findstr;
  void Str()
  {
    Scanner sc = new Scanner(System.in);
    System.out.print("请输入一个待查找字符串的字符串：");
    putstr = sc.next();
//	System.out.println(putstr);
    System.out.print("请输入一个匹配字符串：");
    findstr = sc.next();
//	System.out.println(findstr);
  }
  int CountFind()
  {
	Record L;
	L=null;
    char a[] =this.putstr.toCharArray();
    char b[] =this.findstr.toCharArray();
    int i,j,count;
    count=0;
    for (i=0;i<putstr.length();i++)
    {
      if (a[i]==b[0] & putstr.length()-i>=findstr.length())
      {
//		System.out.println("进入后续字符比较的循环a[i]="+a[i]+" b[0]="+b[0]+" i="+i);
		L=PushRecord(i,L);
		count++;
        for(j=0;j<findstr.length() & i<putstr.length();j++)
        {
          if (a[i]!=b[j])
			{
//			  System.out.println("oh!this is no the need one!--------------------");
			  count--;
			  PopRecord(L);
//			  System.out.println("oh!I have Pop it!------------------------------");
			  break;
			}
          else 
			{
			  i++;
			}
        }
      }
    }
	PrintRecords(L);
    return count;
  }
}

class Record
{
	int records;
	Record LeftRecord;
	Record PushRecord(int i,Record M)
	{
		if (M==null)
		{
			M=new Record();
			M.LeftRecord=null;
			M.records=i;
//			System.out.println("第一次入栈M.records:"+M.records);
		}
		else 
		{
			Record p=new Record();
			p.records=i;
			p.LeftRecord=M;
			M=p;
//			System.out.println("入栈M.records:"+M.records);
		}
		return M;
	}
	int PrintRecord(Record M)
	{
		return M.records;
	}
	Record PopRecord(Record M)
	{
		if (M==null || M.LeftRecord==null)
		{
			return null;
		}
		else
		{
			Record p;
			p=M;
			M=p.LeftRecord;
			p.LeftRecord=null;
			return M;
		}
	}
	void PrintRecords(Record M)
	{
		
		System.out.print("\n所需要匹配的字符分别出现在如下位置：");
		for(;M!=null;)
		{
			System.out.print(PrintRecord(M));
			M=PopRecord(M);
			if (M==null)
			{
				System.out.print("。");
				break;
			}
			System.out.print("、");
		}
		System.out.println();
	}
}
