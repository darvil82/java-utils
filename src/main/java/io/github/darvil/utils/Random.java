package io.github.darvil.utils;

public final class Random {
	private Random() {}

	/**
	 * Returns a random integer between min and max. First inclusive, last exclusive. {@code [min, max)}
	 */
	public static int randInt(int min, int max) {
		return (int)(Math.random() * (max - min)) + min;
	}

	/**
	 * Returns a random integer between 0 and max. First inclusive, last exclusive. {@code [0, max)}
	 */
	public static int randInt(int max) {
		return (int)(Math.random() * max);
	}
}