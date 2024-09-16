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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.github.astrapi69.collection.list.ListExtensions;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.gen.tree.handler.IBaseTreeNodeHandlerExtensions;
import io.github.astrapi69.id.generate.LongIdGenerator;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import lombok.NonNull;

/**
 * Utility class for merging tree nodes
 */
public final class TreeNodeMerger
{

	/**
	 * Private constructor to prevent instantiation
	 */
	private TreeNodeMerger()
	{
	}


	/**
	 * Merges a list of tree nodes by removing the first element from the list and merging the rest
	 *
	 * @param treeNodes
	 *            the list of tree nodes to be merged
	 * @param <T>
	 *            the type of the value in the tree node
	 * @param <K>
	 *            the type of the key in the tree node
	 * @return the merged root tree node
	 */
	public static <T, K> BaseTreeNode<T, K> mergeTreeNodes(
		final @NonNull List<BaseTreeNode<T, K>> treeNodes)
	{
		return mergeTreeNodes(ListExtensions.removeFirstElement(treeNodes), treeNodes);
	}

	/**
	 * Merges the given tree nodes into the first tree node if present
	 *
	 * @param firstTreeNode
	 *            the optional first tree node that will be merged with the rest
	 * @param treeNodes
	 *            the list of tree nodes to be merged
	 * @param <T>
	 *            the type of the value in the tree node
	 * @param <K>
	 *            the type of the key in the tree node
	 * @return the merged root tree node or null if the first tree node is not present
	 */
	public static <T, K> BaseTreeNode<T, K> mergeTreeNodes(
		final @NonNull Optional<BaseTreeNode<T, K>> firstTreeNode,
		final @NonNull List<BaseTreeNode<T, K>> treeNodes)
	{
		BaseTreeNode<T, K> root = null;
		if (firstTreeNode.isPresent())
		{
			root = IBaseTreeNodeHandlerExtensions.mergeTreeNodes(firstTreeNode.get(), treeNodes);
		}
		return root;
	}

	/**
	 * Utility method for retrieving and processing a root tree node from a list of tree nodes
	 * 
	 * @param treeNodes
	 *            The list of tree nodes
	 * @return the root with all tree nodes
	 */
	public static BaseTreeNode<MenuInfo, Long> getBaseTreeNode(
		List<BaseTreeNode<MenuInfo, Long>> treeNodes)
	{
		BaseTreeNode<MenuInfo, Long> root = TreeNodeMerger.mergeTreeNodes(treeNodes);

		List<BaseTreeNode<MenuInfo, Long>> orderedList = new ArrayList<>(root.traverse());
		orderedList.sort(new BaseTreeNodeByMenuInfoOrdinalComparator());
		LongIdGenerator idGenerator = LongIdGenerator.of(0L);
		for (BaseTreeNode<MenuInfo, Long> treeNode : orderedList)
		{
			treeNode.setId(idGenerator.getNextId());
		}
		return root;
	}
}
