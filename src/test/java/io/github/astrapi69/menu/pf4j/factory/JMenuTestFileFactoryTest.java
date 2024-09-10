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
package io.github.astrapi69.menu.pf4j.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.file.write.StoreFileExtensions;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.menu.pf4j.transform.MenuInfoTreeNodeConverter;
import io.github.astrapi69.menu.pf4j.transform.TestDataFactory;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

/**
 * The test class {@link JMenuTestFileFactoryTest} provides unit tests for verifying the XML
 * conversions of menu structures and tree nodes in the application.
 */
public class JMenuTestFileFactoryTest
{

	/**
	 * Test for building a root tree node from XML for the file menu.
	 */
	@Test
	public void testBuildRootTreeNodeFromXmlForFileMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestFileMenuWithMenubar();
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		String treeNodeAsXml;

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);

		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-file-menu.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

	/**
	 * Test for building a root tree node from XML for the edit menu.
	 */
	@Test
	public void testBuildRootTreeNodeFromXmlForEditMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestEditMenuWithMenubar();
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		String treeNodeAsXml;

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);

		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-edit-menu.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

	/**
	 * Test for building a root tree node from XML for the help menu.
	 */
	@Test
	public void testBuildRootTreeNodeFromXmlForHelpMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode = TestDataFactory.getTestHelpMenuWithMenubar();
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		String treeNodeAsXml;

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);
		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-help-menu.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}
}
