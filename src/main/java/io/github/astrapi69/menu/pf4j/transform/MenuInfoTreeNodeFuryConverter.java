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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.gen.tree.TreeIdNode;
import io.github.astrapi69.gen.tree.convert.BaseTreeNodeTransformer;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import io.github.astrapisixtynine.fury.BytesToObjectExtensions;
import io.github.astrapisixtynine.fury.ObjectToBytesExtensions;
import lombok.NonNull;

/**
 * The class {@link MenuInfoTreeNodeFuryConverter} provides utility methods for converting byte
 * arrays representations of {@link MenuInfo} objects to a single root {@link BaseTreeNode} and vice
 * versa with the Fury Serialization Framework
 */
public final class MenuInfoTreeNodeFuryConverter
{

	/**
	 * Private constructor to prevent instantiation
	 */
	private MenuInfoTreeNodeFuryConverter()
	{
	}

	/**
	 * Factory method that creates a {@link BaseTreeNode} object from the given XML {@link String}
	 * which represents a {@link BaseTreeNode} of {@link MenuInfo} objects
	 *
	 * @param bytes
	 *            the bytes representation of {@link MenuInfo} objects
	 * @return the newly created {@link BaseTreeNode} object
	 */
	public static BaseTreeNode<MenuInfo, Long> toMenuInfoTreeNode(final byte[] bytes)
	{
		Map<Long, TreeIdNode<MenuInfo, Long>> treeIdNodeMap = RuntimeExceptionDecorator
			.decorate(() -> BytesToObjectExtensions.toObject(FuryFactory.getFury(), bytes));
		return BaseTreeNodeTransformer.getRoot(treeIdNodeMap);
	}

	/**
	 * Converts the given {@link BaseTreeNode} object into a {@link byte} array that represents a
	 * {@link BaseTreeNode} of {@link MenuInfo} objects
	 *
	 * @param root
	 *            the {@link BaseTreeNode} object
	 * @return the {@link byte} array
	 */
	public static byte[] toBytes(final @NonNull BaseTreeNode<MenuInfo, Long> root)
	{
		Map<Long, TreeIdNode<MenuInfo, Long>> treeIdNodeMap = BaseTreeNodeTransformer
			.toKeyMap(root);
		return ObjectToBytesExtensions.toBytes(FuryFactory.getFury(), treeIdNodeMap);
	}

	/**
	 * Merges several byte array objects, representing individual menu items, into a single
	 * {@link BaseTreeNode} of {@link MenuInfo} objects
	 *
	 * @param arrayOfByteArrays
	 *            the XML {@link String} objects
	 * @return the newly created {@link BaseTreeNode} object
	 */
	public static BaseTreeNode<MenuInfo, Long> mergeMenuInfoTreeNode(
		final byte[]... arrayOfByteArrays)
	{
		List<BaseTreeNode<MenuInfo, Long>> treeNodes = toBaseTreeNodes(arrayOfByteArrays);
		return TreeNodeMerger.getBaseTreeNode(treeNodes);
	}

	private static List<BaseTreeNode<MenuInfo, Long>> toBaseTreeNodes(
		final byte[]... arrayOfByteArrays)
	{
		return Arrays.stream(arrayOfByteArrays)
			.map(MenuInfoTreeNodeFuryConverter::toMenuInfoTreeNode)
			.sorted(new BaseTreeNodeByMenuInfoOrdinalComparator()).collect(Collectors.toList());
	}

}
