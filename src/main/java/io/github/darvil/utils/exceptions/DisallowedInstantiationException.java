package io.github.darvil.utils.exceptions;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an exception thrown when an attempt is made to instantiate a class that should
 * not be instantiated.
 */
public class DisallowedInstantiationException extends AssertionError {
	public DisallowedInstantiationException(@NotNull Class<?> clazz) {
		super("The class " + clazz.getName() + " should not be instantiated");
	}
}