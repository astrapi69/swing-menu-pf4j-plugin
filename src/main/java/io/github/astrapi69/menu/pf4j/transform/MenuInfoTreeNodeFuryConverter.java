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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.fury.Fury;
import org.apache.fury.config.Language;

import io.github.astrapi69.collection.list.ListExtensions;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.gen.tree.TreeIdNode;
import io.github.astrapi69.gen.tree.convert.BaseTreeNodeTransformer;
import io.github.astrapi69.gen.tree.handler.IBaseTreeNodeHandlerExtensions;
import io.github.astrapi69.id.generate.LongIdGenerator;
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
public class MenuInfoTreeNodeFuryConverter
{
	private static final Fury FURY;

	static
	{
		FURY = newFury();
	}

	private static Fury newFury()
	{

		Fury fury = Fury.builder().withLanguage(Language.JAVA)
			// Allow to deserialize objects unknown types,
			// more flexible but less secure.
			.requireClassRegistration(false).build();
		return fury;
	}

	private static Fury getFury()
	{
		return FURY;
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
		Fury fury = getFury();
		Map<Long, TreeIdNode<MenuInfo, Long>> treeIdNodeMap = RuntimeExceptionDecorator
			.decorate(() -> BytesToObjectExtensions.toObject(fury, bytes));
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
		Fury fury = getFury();
		Map<Long, TreeIdNode<MenuInfo, Long>> treeIdNodeMap = BaseTreeNodeTransformer
			.toKeyMap(root);
		return ObjectToBytesExtensions.toBytes(fury, treeIdNodeMap);
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

		BaseTreeNode<MenuInfo, Long> root = mergeTreeNodes(treeNodes);

		List<BaseTreeNode<MenuInfo, Long>> orderedList = new ArrayList<>(root.traverse());
		orderedList.sort(new BaseTreeNodeByMenuInfoOrdinalComparator());
		LongIdGenerator idGenerator = LongIdGenerator.of(0L);
		for (BaseTreeNode<MenuInfo, Long> treeNode : orderedList)
		{
			treeNode.setId(idGenerator.getNextId());
		}
		return root;
	}

	private static List<BaseTreeNode<MenuInfo, Long>> toBaseTreeNodes(
		final byte[]... arrayOfByteArrays)
	{
		return Arrays.stream(arrayOfByteArrays)
			.map(MenuInfoTreeNodeFuryConverter::toMenuInfoTreeNode)
			.sorted(new BaseTreeNodeByMenuInfoOrdinalComparator()).collect(Collectors.toList());
	}

	private static <T, K> BaseTreeNode<T, K> mergeTreeNodes(
		final @NonNull List<BaseTreeNode<T, K>> treeNodes)
	{
		return mergeTreeNodes(ListExtensions.removeFirstElement(treeNodes), treeNodes);
	}

	private static <T, K> BaseTreeNode<T, K> mergeTreeNodes(
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
}
