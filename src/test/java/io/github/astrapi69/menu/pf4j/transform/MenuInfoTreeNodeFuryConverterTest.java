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

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.fury.Fury;
import org.apache.fury.config.Language;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.file.write.StoreFileExtensions;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.gen.tree.TreeIdNode;
import io.github.astrapi69.gen.tree.convert.BaseTreeNodeTransformer;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapisixtynine.fury.BytesToObjectExtensions;
import io.github.astrapisixtynine.fury.ObjectToBytesExtensions;

/**
 * Test class for {@link MenuInfoTreeNodeFuryConverter}, verifying the conversion of
 * {@link BaseTreeNode} objects of type {@link MenuInfo} to bytes and back using Fury serialization
 */
class MenuInfoTreeNodeFuryConverterTest
{

	/**
	 * Test for converting a {@link BaseTreeNode} of {@link MenuInfo} to bytes and back using Fury
	 *
	 * @throws IOException
	 *             if an I/O error occurs during reading or writing the serialized data
	 */
	@Test
	void testWithFury() throws IOException
	{
		Fury fury = Fury.builder().withLanguage(Language.JAVA)
			// Allows deserializing objects with unknown types,
			// providing more flexibility but less security
			.requireClassRegistration(false).build();
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();

		Map<Long, TreeIdNode<MenuInfo, Long>> treeIdNodeMap = BaseTreeNodeTransformer
			.toKeyMap(menuBarTreeNode);
		byte[] bytes = ObjectToBytesExtensions.toBytes(fury, treeIdNodeMap);
		assertNotNull(bytes);

		File file = PathFinder.getRelativePath(PathFinder.getSrcTestResourcesDir(), "byte-object",
			"test-menu.bts");
		if (!file.exists())
		{
			FileFactory.newFile(file);
		}
		StoreFileExtensions.toFile(file, bytes);
		byte[] fileToBytearray = ReadFileExtensions.readFileToBytearray(file);
		Map<Long, TreeIdNode<MenuInfo, Long>> object = BytesToObjectExtensions.toObject(fury,
			fileToBytearray);
		assertNotNull(object);
		assertEquals(treeIdNodeMap, object);

		BaseTreeNode<MenuInfo, Long> root = BaseTreeNodeTransformer.getRoot(object);
		assertNotNull(root);
		assertEquals(menuBarTreeNode, root);
	}

	/**
	 * Test for converting bytes to {@link BaseTreeNode} of {@link MenuInfo} using the Fury
	 * converter
	 *
	 * @throws IOException
	 *             if an I/O error occurs during file reading
	 */
	@Test
	void toMenuInfoTreeNode() throws IOException
	{
		File file = PathFinder.getRelativePath(PathFinder.getSrcTestResourcesDir(), "byte-object",
			"test-menu.bts");
		byte[] fileToBytearray = ReadFileExtensions.readFileToBytearray(file);
		BaseTreeNode<MenuInfo, Long> menuInfoTreeNode = MenuInfoTreeNodeFuryConverter
			.toMenuInfoTreeNode(fileToBytearray);
		assertNotNull(menuInfoTreeNode);

		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		assertEquals(menuBarTreeNode, menuInfoTreeNode);
	}

	/**
	 * Test for converting {@link BaseTreeNode} of {@link MenuInfo} to bytes using the Fury
	 * converter
	 */
	@Test
	void toBytes()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		byte[] bytes = MenuInfoTreeNodeFuryConverter.toBytes(menuBarTreeNode);
		assertNotNull(bytes);
	}
}
