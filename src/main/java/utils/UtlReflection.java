package utils;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public final class UtlReflection {
	private UtlReflection() {}

	/**
	 * Returns the simple name of the given class. If the class is an anonymous class, then the simple name
	 * of the superclass is returned.
	 *
	 * @param clazz The class to get the simple name of.
	 * @return The simple name of the given class.
	 */
	public static @NotNull String getSimpleName(@NotNull Class<?> clazz) {
		String name;

		do {
			name = clazz.getSimpleName();
		} while (name.isEmpty() && ((clazz = clazz.getSuperclass()) != null));

		return name;
	}

	/**
	 * Returns {@code true} if the given parameter types are assignable to the parameter types of the given method.
	 * @param method The method to check.
	 * @param paramTypes The parameter types to check.
	 * @return {@code true} if the given parameter types are assignable to the parameter types of the given method.
	 */
	public static boolean hasParameters(Method method, Class<?>... paramTypes) {
		var parameterTypes = method.getParameterTypes();
		if (parameterTypes.length != paramTypes.length) return false;

		for (int i = 0; i < parameterTypes.length; i++) {
			if (!paramTypes[i].isAssignableFrom(parameterTypes[i])) return false;
		}

		return true;
	}

	/**
	 * Instantiates the given class with a no-argument constructor.
	 * @param clazz The class to instantiate.
	 * @param <T> The type of the class.
	 * @return The instantiated class. If the class could not be instantiated, a {@link RuntimeException} is thrown.
	 */
	public static <T> T instantiate(Class<T> clazz) {
		return UtlReflection.instantiate(clazz, List.of(), List.of());
	}

	/**
	 * Instantiates the given class with the given arguments.
	 * @param clazz The class to instantiate.
	 * @param args The arguments to pass to the constructor.
	 * @param <T> The type of the class.
	 * @return The instantiated class. If the class could not be instantiated, a {@link RuntimeException} is thrown.
	 */
	public static <T> T instantiate(Class<T> clazz, @NotNull List<@NotNull Object> args) {
		return UtlReflection.instantiate(
			clazz,
			args.stream().map(Object::getClass).toList(),
			args
		);
	}

	/**
	 * Instantiates the given class with the given arguments.
	 * @param clazz The class to instantiate.
	 * @param argTypes The types of the arguments to pass to the constructor.
	 * @param args The arguments to pass to the constructor.
	 * @param <T> The type of the class.
	 * @return The instantiated class. If the class could not be instantiated, a {@link RuntimeException} is thrown.
	 */
	public static <T> T instantiate(
		Class<T> clazz,
		@NotNull List<? extends Class<?>> argTypes,
		@NotNull List<Object> args
	) {
		try {
			return clazz.getConstructor(argTypes.toArray(Class[]::new)).newInstance(args.toArray());
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Unable to find a public constructor for the class '" + clazz.getName()
				+ """
			'. Please make sure:
			  - This class has a public constructor with the parameters: %s
			  - This is a static class. (Not an inner class)
			  - This class is not defined in a method.
			  - This class is visible to external dependencies.""".formatted(argTypes),
				e
			);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
				"Unable to gain access to the class '" + clazz.getName()
					+ "'. Please make sure this class is visible to external dependencies.",
				e
			);
		} catch (InstantiationException e) {
			throw new RuntimeException(
				"Unable to instantiate the class '" + clazz.getName()
					+ "'. Please make sure this class is not abstract.",
				e
			);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}