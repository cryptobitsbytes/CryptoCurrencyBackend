package Reflection;

import com.google.gson.annotations.SerializedName;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;

/**

 */
//TODO make beautiful, this class looks ugly.
public class DocumentConverter {

    public static Document convertObjectToDocument(Object object)
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
                if(value != null)
                {
                    if(TypeChecker.isNotWrapperType(value.getClass()))
                    {
                        Document classDocument = convertObjectToDocument(value);
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

    public static Object convertDocumentToObject(Document document,Class<?> objectClass)
    {
        Object object = null;
        try{
            object = objectClass.newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }

        Method[] methods = objectClass.getDeclaredMethods();
        Field[] fields = objectClass.getDeclaredFields();

        for(Field field : fields) {
            if (field.isAnnotationPresent(SerializedName.class)) {
                String fieldJsonValue = field.getAnnotation(SerializedName.class).value();
                Class<?> fieldClass = field.getType();
                Object documentValue = document.get(fieldJsonValue);
                if(documentValue != null)
                {
                    if(TypeChecker.isNotWrapperType(fieldClass))
                    {
                        if(TypeChecker.isNotCollection(fieldClass)) {
                            try
                            {
                                Document document1 = (Document)documentValue;
                                Object fieldObject = convertDocumentToObject((Document)documentValue, fieldClass);
                                runSetter(field, object, methods, fieldObject);
                            }
                            catch(ClassCastException e)
                            {
                                System.out.printf("JsonValue: %s; Class: %s", field.getName(), field.getClass().getName());
                            }
                        } else {
                            Collection<Object> collection =  document.values();
                            Collection<Object> returnCollection = new LinkedList<>();
                            for(Object documentObject : collection)
                            {
                                Object fieldObject = convertDocumentToObject((Document)documentObject, documentObject.getClass());
                                returnCollection.add(fieldObject);
                            }
                            runSetter(field, object, methods, returnCollection);
                        }

                    } else
                    {
                        runSetter(field, object, methods, documentValue);
                    }
                }
            }
        }
        return object;
    }

    public static Object runGetter(Field field, Object object, Method[] methods)
    {
        for (Method method : methods)
        {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
            {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
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

    public static Object runSetter(Field field, Object object, Method[] methods, Object parameter)
    {
        for (Method method : methods)
        {
            if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3)))
            {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
                    try
                    {
                        return method.invoke(object, parameter);
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
