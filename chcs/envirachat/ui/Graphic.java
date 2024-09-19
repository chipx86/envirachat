package chcs.envirachat;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.image.ImageObserver;

import java.awt.Graphics;


public abstract class Graphic extends Graphics
{
	protected Graphic()
	{
	}
	
	public abstract Graphic create()
	{
		super.create();
	}
	

}
