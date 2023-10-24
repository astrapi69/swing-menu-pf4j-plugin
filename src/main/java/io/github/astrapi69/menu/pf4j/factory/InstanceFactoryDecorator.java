package io.github.astrapi69.menu.pf4j.factory;

import io.github.astrapi69.reflection.InstanceFactory;
import lombok.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class InstanceFactoryDecorator {


    public static <T> Optional<T> newInstance(@NonNull String fullyQualifiedClassName, Object... initArgs)
    {
        try {
           return InstanceFactory.newInstance(fullyQualifiedClassName, initArgs);
        } catch (InvocationTargetException e) {
            return Optional.empty();
        } catch (InstantiationException e) {
            return Optional.empty();
        } catch (IllegalAccessException e) {
            return Optional.empty();
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }
}
