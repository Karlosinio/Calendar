package guiLayer;

import logicLayer.LogicLayer;

public class Main
{
	public static LogicLayer ll;

	public static void main(String[] args)
	{
		ll = new LogicLayer();
		MainWindow.OpenWindow();

	}

}
