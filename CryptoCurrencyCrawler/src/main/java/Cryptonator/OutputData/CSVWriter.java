package Cryptonator.OutputData;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by Lennard on 25-7-2017.
 */
public class CSVWriter {

    private static final char DEFAULT_SEPERATORS=',';

    public static void writeLine(Writer writer, List<String> values) throws IOException
    {
        writeLine(writer, values, DEFAULT_SEPERATORS);
    }

    public static void writeLine(Writer writer, List<String> values, char seperators) throws IOException
    {
       writer.append(createWriteLineString(values, seperators));
    }

    private static String createWriteLineString(List<String> values, char seperators)
    {
        Boolean first = true;
        StringBuilder stringBuilder = new StringBuilder();
        for(String value : values)
        {
            if(!first)
            {
                stringBuilder.append(seperators);
            }
            stringBuilder.append(value);
            first = false;
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
