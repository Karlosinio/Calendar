package guiLayer;

import logicLayer.LogicLayer;
import logicLayer.LogicLayerException;
import logicLayer.XMLSettingsSerializer;

public class Main
{
	public static LogicLayer ll;
	
	public static void main(String[] args)
	{
		ll = new LogicLayer();
		
		try
		{
			ll.setSettings(XMLSettingsSerializer.importData());
		}
		catch (LogicLayerException e)
		{
			ll.setDefaultSettings();
			ExceptionWindow.openWindow(e.getMessage());
		}
		
		try
		{
			ll.setDataService(ll.getSerializer().importData(ll.getFinalFileName()));
		}
		catch (LogicLayerException e)
		{
			ExceptionWindow.openWindow(e.getMessage());
		}
		finally
		{			
			MainWindow.openWindow(null);
		}
		
	}

}
