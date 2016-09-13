package rs.edu.vtsnis.dmitrovic.train_track;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Created by danijel on 9/13/16.
 */
public class ParamConverterProviderImpl implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if (LocalDateTime.class.equals(aClass)) {
            return (ParamConverter<T>) new LocalDateTimeParamConverter();
        }
        return null;
    }

    public class LocalDateTimeParamConverter implements ParamConverter<LocalDateTime> {

        @Override
        public LocalDateTime fromString(String s) {
            if (s != null)
                return LocalDateTime.parse(s);
            else return null;
        }

        @Override
        public String toString(LocalDateTime localDateTime) {
            return localDateTime.toString();
        }
    }
}

