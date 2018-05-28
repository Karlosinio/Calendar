package logicLayer;

import dataLayer.DataService;


public interface Saver
{
	public void save(String fileName, DataService data) throws SaveException;
}
