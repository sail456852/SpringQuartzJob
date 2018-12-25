package tij.annotation.sqlanno;

import org.springframework.util.StringUtils;

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
        if (args.length < 1) {
            System.err.println("TableCreator.main");
            System.exit(0);
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.err.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            // If the name is empty, use the Class name;
            if (tableName.length() < 1)
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<String>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1)
                    continue;
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    // Use field name if name not specified
                    if (sString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = field.getName().toUpperCase();
                    }
                    columnDefs.add(columnName + "  VARCHAR(" + sString.value() + ")" + getConstraints(sString.constrains()));
                }
                StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
                for (String columnDef : columnDefs) {
                    createCommand.append("\n   " + columnDef + ","  );
                    String tableCreate = createCommand.substring(0, createCommand.length() -1) + ");";
                    // Remove trailing coma
                    System.err.println("Table creation SQL for " + className + " is :\n " + tableCreate);
                }

            }

        }
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += "  UNIQUE";
        return constraints;
    }
}
