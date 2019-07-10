package IO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class excelRead {
    

    public static void main(String[] args)throws IOException, InvalidFormatException {
    	readExcel();
    	return;
    }
    
    private static void readExcel()throws IOException, InvalidFormatException{
    	boolean idcoste = true;
    	boolean aux = true;
    	ArrayList<comuna>  comunas = new ArrayList<comuna>();
    	comuna comunaActual = new comuna();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
    	File data=new File("Comunas.xls");
        Workbook workbook = WorkbookFactory.create(data);

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

     // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            
            while (cellIterator.hasNext()) {
            	Cell cell = cellIterator.next();
            	if( cell.getCellTypeEnum() == CellType.STRING) {
            		if(aux == true) {
            			aux = false;
            			comunaActual.setNombre(cell.getStringCellValue());
                		
                		
            		}else {
            			aux = true;
            			String value=cell.getStringCellValue();
            			comunaActual.setVecinos(getVecinos(value));
            		}
            	}else {
            		if(idcoste == true) {
            			comunaActual.setId((int)cell.getNumericCellValue());
            			idcoste = false;
            			
            		}else {
            			comunaActual.setCoste((int)cell.getNumericCellValue());
            			idcoste = true;
            			
            		}
            	}
            	if((aux==true) && (idcoste==true)) {
            		comunas.add(comunaActual);
            		System.out.print("   |"+comunaActual.getId()+"|   			");
            		System.out.print("   *"+comunaActual.getNombre()+"*			   ");	
            		System.out.print("   $"+comunaActual.getCoste()+"    		 Id´s:");
            		
            		for(int i=0;i<comunaActual.getLength();i++){
            			System.out.print(comunaActual.getVecino(i));
            			System.out.print(",");
            		  }
            		  System.out.println();
            			
            	}
            	dataFormatter.formatCellValue(cell);
            }
        }
    }
    
    
    public static int[] getVecinos(String cell) {
    	String value=cell;
    	String[] valores=value.split(",");
    	int[] fin= new int[valores.length];
    	
    	for(int i=0;i<valores.length;i++) {
    		fin[i] = Integer.parseInt(valores[i]);
    	}
    	return fin;
    }
}