package corba_iiop.HelloApp;

/**
* HelloApp/HelloHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Hello.idl
* 2020��3��31�� ���ڶ� ����12ʱ41��27�� GMT+08:00
*/

public final class HelloHolder implements org.omg.CORBA.portable.Streamable
{
  public Hello value = null;

  public HelloHolder ()
  {
  }

  public HelloHolder (Hello initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HelloHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HelloHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HelloHelper.type ();
  }

}
