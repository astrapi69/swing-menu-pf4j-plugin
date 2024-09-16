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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.swing.menu.model.MenuInfo;

/**
 * Test class for {@link TreeNodeMerger}
 */
public class TreeNodeMergerParameterizedTest
{

	/**
	 * Parameterized test for {@link TreeNodeMerger#mergeTreeNodes(List)} using a CSV file
	 *
	 * @param firstNodePresent
	 *            indicates if the first node is present
	 * @param numberOfNodes
	 *            the number of nodes in the list
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/treeNodeMergerTestData.csv", numLinesToSkip = 1)
	@Disabled("test data have to be validated")
	public void testMergeTreeNodesParameterized(boolean firstNodePresent, int numberOfNodes)
	{
		Optional<BaseTreeNode<String, Integer>> firstNode = firstNodePresent
			? Optional.of(new BaseTreeNode<>())
			: Optional.empty();
		List<BaseTreeNode<String, Integer>> treeNodes = new ArrayList<>();
		for (int i = 0; i < numberOfNodes; i++)
		{
			treeNodes.add(new BaseTreeNode<>());
		}
		BaseTreeNode<String, Integer> result = TreeNodeMerger.mergeTreeNodes(firstNode, treeNodes);
		assertNotNull(result);
	}

	/**
	 * Parameterized test for {@link TreeNodeMerger#getBaseTreeNode(List)} using a CSV file
	 *
	 * @param numberOfNodes
	 *            the number of nodes in the list
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/treeNodeMergerGetBaseTreeNodeTestData.csv", numLinesToSkip = 1)
	@Disabled("test data have to be validated")
	public void testGetBaseTreeNodeParameterized(int numberOfNodes)
	{
		List<BaseTreeNode<MenuInfo, Long>> treeNodes = new ArrayList<>();
		for (int i = 0; i < numberOfNodes; i++)
		{
			BaseTreeNode<MenuInfo, Long> treeNode = new BaseTreeNode<>();
			treeNode.setValue(new MenuInfo()); // You can customize the value of MenuInfo here
			treeNodes.add(treeNode);
		}
		BaseTreeNode<MenuInfo, Long> result = TreeNodeMerger.getBaseTreeNode(treeNodes);
		assertNotNull(result);
		assertEquals(numberOfNodes, result.traverse().size());
	}
}
