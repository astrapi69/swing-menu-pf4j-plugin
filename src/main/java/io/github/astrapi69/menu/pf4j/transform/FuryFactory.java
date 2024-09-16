/**
 * The MIT License
 *
 * Copyright (C) 2023 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.menu.pf4j.transform;

import org.apache.fury.Fury;
import org.apache.fury.config.Language;

/**
 * Factory class for creating and providing a singleton instance of {@link Fury}
 */
public final class FuryFactory
{

	/**
	 * Private constructor to prevent instantiation
	 */
	private FuryFactory()
	{
	}

	/**
	 * Singleton instance of {@link Fury}
	 */
	private static final Fury FURY;

	static
	{
		FURY = newFury();
	}

	/**
	 * Creates a new instance of {@link Fury} with the necessary configuration
	 *
	 * @return the configured {@link Fury} instance
	 */
	private static Fury newFury()
	{
		Fury fury = Fury.builder().withLanguage(Language.JAVA)
			// Allows deserializing objects with unknown types,
			// providing more flexibility but less security
			.requireClassRegistration(false).build();
		return fury;
	}

	/**
	 * Returns the singleton instance of {@link Fury}
	 *
	 * @return the singleton {@link Fury} instance
	 */
	static Fury getFury()
	{
		return FURY;
	}
}
