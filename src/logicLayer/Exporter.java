package logicLayer;

import dataLayer.DataService;


public interface Exporter
{
	public void save(String fileName, DataService data) throws ExportException;
}
