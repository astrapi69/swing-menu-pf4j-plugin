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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;

/**
 * Parameterized test for {@link MenuInfoTreeNodeFuryConverter} to test serialization and
 * deserialization
 */
public class MenuInfoTreeNodeFuryConverterParameterizedTest
{

	/**
	 * Parameterized test method for verifying conversions for different menu tree node sizes
	 *
	 * @param size
	 *            the number of nodes in the tree
	 */
	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 8 })
	public void testSerializationForDifferentTreeSizes(int size)
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.newTestMenuWithSize(size);

		byte[] bytes = MenuInfoTreeNodeFuryConverter.toBytes(menuBarTreeNode);
		assertNotNull(bytes);

		BaseTreeNode<MenuInfo, Long> deserializedTreeNode = MenuInfoTreeNodeFuryConverter
			.toMenuInfoTreeNode(bytes);
		assertNotNull(deserializedTreeNode);
		assertEquals(menuBarTreeNode, deserializedTreeNode);
	}
}
