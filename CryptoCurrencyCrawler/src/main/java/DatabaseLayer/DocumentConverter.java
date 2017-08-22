package DatabaseLayer;

import Reflection.TypeChecker;
import com.google.gson.annotations.SerializedName;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Lennard on 22-8-2017.
 */
public class DocumentConverter {

    public Document convertToDocument(Object object)
    {
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        Method[] methods = objectClass.getMethods();

        Document document = new Document();
        for(Field field : fields)
        {
            if(field.isAnnotationPresent(SerializedName.class))
            {
                String fieldJsonValue = field.getAnnotation(SerializedName.class).value();
                Object value = runGetter(field, object, methods);
                //TODO null might be a valid empty value for some json...
                if(value != null)
                {
                    if(TypeChecker.isNotWrapperType(value.getClass()))
                    {
                        Document classDocument = convertToDocument(value);
                        document.append(fieldJsonValue, classDocument);
                    } else
                    {
                        document.append(fieldJsonValue, value);
                    }
                }
            }
        }
        return document;
    }

    public static Object runGetter(Field field, Object object, Method[] methods)
    {
        for (Method method : methods)
        {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
            {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
                    // MZ: Method found, run it
                    try
                    {
                        return method.invoke(object);
                    }
                    catch (IllegalAccessException e)
                    {
                       e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                       e.printStackTrace();
                    }

                }
            }
        }
        return null;
    }
}
