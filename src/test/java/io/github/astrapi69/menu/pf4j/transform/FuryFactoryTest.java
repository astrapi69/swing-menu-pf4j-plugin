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

import static org.junit.jupiter.api.Assertions.*;

import org.apache.fury.Fury;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for {@link FuryFactory}
 */
public class FuryFactoryTest
{

	/**
	 * Test method for {@link FuryFactory#getFury()}
	 */
	@Test
	public void testGetFury()
	{
		Fury furyInstance = FuryFactory.getFury();
		assertNotNull(furyInstance);
		assertEquals(FuryFactory.getFury(), furyInstance); // Singleton check
	}

	/**
	 * Parameterized test for validating different language configurations (although
	 * {@link FuryFactory} always uses Java, this test checks the singleton)
	 *
	 * @param language
	 *            the language to simulate (can be expanded for future flexibility)
	 */
	@ParameterizedTest
	@ValueSource(strings = { "JAVA", "PYTHON" })
	public void testFurySingletonForDifferentLanguages(String language)
	{
		Fury furyInstance = FuryFactory.getFury();
		assertNotNull(furyInstance);
		assertEquals(FuryFactory.getFury(), furyInstance); // Singleton check
	}
}
