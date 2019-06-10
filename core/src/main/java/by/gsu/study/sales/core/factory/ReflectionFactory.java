package by.gsu.study.sales.core.factory;

import by.gsu.study.sales.core.entity.IEntity;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// just for fun
public class ReflectionFactory<E extends IEntity>
    implements IFactory<E> {

    private final Class<E> clz;
    private final Map<Field, Method> map;
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> dateFormats = Arrays.asList(
            "yyyy-MM-dd",
            "yyyy:MM:dd",
            "yyyy-MM-dd hh:mm:ss",
            "yyyy:MM:dd hh:mm:ss"
    );

    public ReflectionFactory(Class<E> clz) {
        this.clz = clz;
        map = buildMap(clz);
    }

    @Override
    @SneakyThrows
    public E create() {
        Constructor<E> constructor = clz.getConstructor();
        E result = constructor.newInstance();
        for (Map.Entry<Field, Method> entry : map.entrySet()) {
            Field field = entry.getKey();
            Method method = entry.getValue();
            Class<?> type = field.getType();
            Object arg;

            if (isSimpleType(type)) {
                System.out.println("Input " + field.getName() + ":");
                String line = scanner.nextLine();
                arg = convertInput(type, line);
            } else if (IEntity.class.isAssignableFrom(type)) {
                System.out.println("Construct "+ field.getName() + " object :");
                ReflectionFactory<?> factory = new ReflectionFactory(type);
                arg = factory.create();
                System.out.println(field.getName() + " created successful");
            } else {
                throw new IllegalArgumentException("Can't create value for " + field.getName());
            }

            method.invoke(result, arg);

        }
        return result;
    }

    private Object convertInput(Class<?> type, String input) {
        if (String.class.equals(type)) {
            return input;
        }
        if (Character.class.equals(type) || char.class.equals(type)) {
            if (input.length() != 1) {
                throw new IllegalArgumentException("Can't get single char from: " + input);
            }
            return input.toCharArray()[0];
        }
        if (Byte.class.equals(type) || byte.class.equals(type)) {
            return Byte.valueOf(input);
        }
        if (Short.class.equals(type) || short.class.equals(type)) {
            return Byte.valueOf(input);
        }
        if (Integer.class.equals(type) || int.class.equals(type)) {
            return Integer.valueOf(input);
        }
        if (Long.class.equals(type) || long.class.equals(type)) {
            return Long.valueOf(input);
        }
        if (Float.class.equals(type) || float.class.equals(type)) {
            return Float.valueOf(input);
        }
        if (Double.class.equals(type) || double.class.equals(type)) {
            return Double.valueOf(input);
        }
        if (Boolean.class.equals(type) || boolean.class.equals(type)) {
            return Boolean.valueOf(input);
        }
        if (Date.class.equals(type)) {
            for (String format : dateFormats) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                try {
                    return sdf.parse(input);
                } catch (ParseException e) {
                    //ignore
                }
            }
            throw new IllegalArgumentException("Can't parse date  from input: " + input);
        }
        throw new IllegalArgumentException("Can't parse input to " + type.getCanonicalName());

    }

    private List<Field> getFields(Class<?> cls) {
        List<Field> fields = new ArrayList<>(
                Arrays.asList(clz.getDeclaredFields())
        );
        if (cls.getSuperclass() != null) {
            List<Field> superFields = getFields(cls.getSuperclass());
            fields.addAll(superFields);
        }
        return fields;
    }

    private Map<Field, Method> buildMap(Class<?> cls) {
        List<Field> fields = getFields(cls);
        Map<Field, Method> map = new HashMap<>();

        for (Field field : fields) {
            String name = field.getName();
            if ("id".equals(name)) {
                continue;
            }
            String setterName = getSetterName(name);
            try {
                Method method = cls.getMethod(setterName, field.getType());
                map.put(field, method);
            } catch (NoSuchMethodException e) {
                //ignore
            }
        }
        return map;
    }

    private String getSetterName(String input) {
        return "set" +
                input.substring(0, 1).toUpperCase() +
                input.substring(1);
    }

    private boolean isSimpleType(Class<?> type) {
        return
                Integer.class.equals(type) ||
                int.class.equals(type) ||
                Long.class.equals(type) ||
                long.class.equals(type) ||
                Byte.class.equals(type) ||
                byte.class.equals(type) ||
                Short.class.equals(type) ||
                short.class.equals(type) ||
                Character.class.equals(type) ||
                char.class.equals(type) ||
                String.class.equals(type) ||
                Double.class.equals(type) ||
                double.class.equals(type) ||
                Float.class.equals(type) ||
                float.class.equals(type) ||
                Date.class.equals(type);
    }

}
