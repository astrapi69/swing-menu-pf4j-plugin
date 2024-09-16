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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.menu.pf4j.test.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;

/**
 * Test class for {@link TreeNodeMerger}
 */
public class TreeNodeMergerTest
{

	/**
	 * Test method for {@link TreeNodeMerger#mergeTreeNodes(List)}
	 */
	@Test
	public void testMergeTreeNodesList()
	{
		List<BaseTreeNode<String, Integer>> treeNodes = new ArrayList<>();
		// Add test cases here
		BaseTreeNode<String, Integer> result = TreeNodeMerger.mergeTreeNodes(treeNodes);
		assertNull(result);
	}

	/**
	 * Test method for {@link TreeNodeMerger#mergeTreeNodes(Optional, List)}
	 */
	@Test
	public void testMergeTreeNodesOptional()
	{
		Optional<BaseTreeNode<String, Integer>> firstNode = Optional.empty();
		List<BaseTreeNode<String, Integer>> treeNodes = new ArrayList<>();
		BaseTreeNode<String, Integer> result = TreeNodeMerger.mergeTreeNodes(firstNode, treeNodes);
		assertNull(result);
	}

	/**
	 * Test method for {@link TreeNodeMerger#getBaseTreeNode(List)}
	 */
	@Test
	public void testGetBaseTreeNode()
	{
		BaseTreeNode<MenuInfo, Long> testFileMenuWithMenubar = TestDataFactory
			.getTestFileMenuWithMenubar();
		List<BaseTreeNode<MenuInfo, Long>> treeNodes = new ArrayList<>(
			testFileMenuWithMenubar.traverse());
		BaseTreeNode<MenuInfo, Long> result = TreeNodeMerger.getBaseTreeNode(treeNodes);
		assertNotNull(result);
		assertEquals(0L, result.getId());
	}

}
