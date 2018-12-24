package tij.annotation.sqlanno;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 12/24/18<br/>
 * Time: 11:18 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        if(args.length < 1){
            System.err.println("TableCreator.main");
            System.exit(0);
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.err.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            // If the name is empty, use the Class name;
            if(tableName.length() < 1)
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<String>();
            for(Field field : cl.getDeclaredFields()){
               String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length < 1)
                    continue;
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                }
                //
            }

        }
    }
}
