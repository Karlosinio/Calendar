package logicLayer;

import dataLayer.DataService;

public interface Importer
{
	public abstract DataService importData(String fileName) throws ImportException; 
}