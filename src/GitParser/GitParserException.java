/*************************************************************************************
 Programmer(s): Jenzel Arevalo

 Class Description:
 Custom exception class that insulates and identifies errors that may occur from
 the GitParser class
 ************************************************************************************/

package GitParser;

//TODO: MARKED FOR DELETION - In interest of time, not necessary for implementation
public class GitParserException extends Exception
{
  public GitParserException(String s)
  {
      super(s);
  }
}
