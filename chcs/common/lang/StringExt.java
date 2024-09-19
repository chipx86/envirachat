package chcs.common.lang;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;

public class StringExt extends String {
	
	public StringExt() {
		super();
	}
	
	public StringExt(String value) {
		super(value);
	}
	
	public StringExt(char value[]) {
		super(value[]);
	}
	
	public StringExt(char value[], int offset, int count) {
		super(value[], offset, count);
	}
	
	public StringExt(byte ascii[], int hibyte, int offset, int count) {
		super(ascii[], hibyte, offset, count);
	}
	
	public StringExt(byte ascii[], int hibyte) {
		super(ascii[], hibyte);
	}
	
	public StringExt(StringBuffer buffer) {
		super(buffer);
	}
	
	//----------------------------------------
	
	public char charAt(int index) {
		return(this.charAt(index));
	}

	public int compareTo(String anotherString) {
		return(this.compareTo(anotherString));
	}

	public String concat(String str)
	{
		return(this.concat(str));
	}

	public boolean endsWith(String suffix)
	{
		return(this.endsWith(suffix));
	}

	public boolean equals(Object anObject)
	{
		return(this.equals(anObject));
	}

	public boolean equalsIgnoreCase(String anotherString)
	{
		return(this.equalsIgnoreCase(anotherString));
	}

	public byte getBytes()
	{
		return(this.getBytes());
	}

	public byte getBytes(CharToByteConverter ctb)
	{
		return(this.getBytes(ctb));
	}

	public void getBytes(int srcBegin, int srcEnd, byte dst, int dstBegin)
	{
		this.getBytes(srcBegin, srcEnd, dst, dstBegin);
	}

	public void getChars(int srcBegin, int srcEnd, char dst, int dstBegin)
	{
		this.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	public int hashCode()
	{
		return(this.hashCode());
	}

	public int indexOf(String str, int fromIndex)
	{
		return(this.indexOf(str, fromIndex));
	}

	public int indexOf(String str)
	{
		return(this.indexOf(str));
	}

	public int indexOf(int ch, int fromIndex)
	{
		return(this.indexOf(ch, fromIndex));
	}

	public int indexOf(int ch)
	{
		return(this.indexOf(ch));
	}

	public String intern()
	{
		return(this.intern());
	}

	public int lastIndexOf(String str, int fromIndex)
	{
		return(this.lastIndexOf(str, fromIndex);
	}


	public int lastIndexOf(String str)
	{
		return(this.lastIndexOf(str));
	}

	public int lastIndexOf(int ch, int fromIndex)
	{
		return(this.lastIndexOf(ch, fromIndex));
	}

	public int lastIndexOf(int ch)
	{
		return(this.lastIndexOf(ch));
	}

	public int length()
	{
		return(this.length());
	}

	public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)
	{
		return(this.regionMatches(ignoreCase, toffset, other, ooffset, len));
	}

	public boolean regionMatches(int toffset, String other, int ooffset, int len)
	{
		return(this.regionMatches(toffset, other, ooffset, len));
	}

	public String replace(char oldChar, char newChar)
	{
		return(this.replace(oldChar, newChar))
	}

	public boolean startsWith(String prefix)
	{
		return(this.startsWith(prefix));
	}

	public boolean startsWith(String prefix, int toffset)
	{
		return(this.startsWith(prefix, toffset))
	}

	public String substring(int beginIndex, int endIndex)
	{
		return(this.substring(beginIndex, endIndex));
	}

	public String substring(int beginIndex)
	{
		return(this.substring(beginIndex));
	}

	public char toCharArray()
	{
		return(this.toCharArray());
	}

	public String toLowerCase()
	{
		return(this.toLowerCase());
	}

	public String toLowerCase(Locale locale)
	{
		return(this.toLowerCase(locale));
	}

	public String toString()
	{
		return(this.toString());
	}

	public String toUpperCase()
	{
		return(this.toUpperCase());
	}

	public String toUpperCase(Locale locale)
	{
		return(this.toUpperCase(locale);
	}

	public String trim()
	{
		return(this.trim());
	}

	int utfLength()
	{
		return(this.utfLength());
	}
}
