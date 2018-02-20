package reservar.vuelos.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utilidades {

    private static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public static void addValue(JsonObject jsonObject, String columnLabel, Object fieldValue) {
        if (fieldValue == null) {
            jsonObject.add(columnLabel, JsonNull.INSTANCE);
        } else if (fieldValue instanceof Integer) {
            jsonObject.addProperty(columnLabel, (Integer) fieldValue);
        } else if (fieldValue instanceof Double) {
            jsonObject.addProperty(columnLabel, (Double) fieldValue);
        } else if (fieldValue instanceof Float) {
            jsonObject.addProperty(columnLabel, (Float) fieldValue);
        } else if (fieldValue instanceof java.sql.Date) {
            jsonObject.addProperty(columnLabel, formater.format((Date) fieldValue));
        } else if (fieldValue instanceof String) {
            jsonObject.addProperty(columnLabel, (String) fieldValue);
        } else if (fieldValue instanceof BigDecimal) {
            jsonObject.addProperty(columnLabel, (BigDecimal) fieldValue);
        } else if (fieldValue instanceof Boolean) {
            jsonObject.addProperty(columnLabel, (Boolean) fieldValue);
        } else if (fieldValue instanceof Timestamp) {
            jsonObject.addProperty(columnLabel, fieldValue.toString());
        } else if (fieldValue instanceof byte[]) {
            jsonObject.addProperty(columnLabel, new String((byte[]) fieldValue));
        } else if (fieldValue instanceof Short) {
            jsonObject.addProperty(columnLabel, (Short) fieldValue);
        } else {
            System.err.println(fieldValue.getClass());
        }
    }

    public static void required(HttpServletRequest request, HttpServletResponse response, String params) throws IOException {
        String[] names = params.split(",");
        List<String> notFound = new ArrayList<String>();
        for (String name : names) {
            if (request.getParameter(name.trim()) == null) {
                notFound.add(name);
            }
        }
        if (notFound.size() > 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameters not found: " + String.join(",", notFound));
        }
    }
}
