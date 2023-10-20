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

import java.io.File;

import javax.swing.JMenu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.junit.jupiter.callback.before.test.IgnoreHeadlessExceptionExtension;
import io.github.astrapi69.menu.pf4j.transform.MenuInfoTreeNodeConverter;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

public class JMenuFactoryTest
{
	File xmlFile;
	String fileMenuXml;
	String editMenuXml;
	String helpMenuXml;

	@BeforeEach
	public void beforeEach()
	{
		String filename;
		filename = "app-file-menu.xml";
		xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), filename);
		fileMenuXml = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.fromFile(xmlFile));

		filename = "app-edit-menu.xml";
		xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), filename);
		editMenuXml = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.fromFile(xmlFile));

		filename = "app-help-menu.xml";
		xmlFile = FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), filename);
		helpMenuXml = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.fromFile(xmlFile));
	}

	@ExtendWith(IgnoreHeadlessExceptionExtension.class)
	@Test
	public void testBuildFileMenuFromXml()
	{
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		JMenu menu;

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(fileMenuXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode.getId(), 0);

		menu = JMenuFactory.buildMenu(BaseMenuId.FILE.propertiesKey(), menuInfoLongBaseTreeNode);
		assertNotNull(menu);
	}

	@ExtendWith(IgnoreHeadlessExceptionExtension.class)
	@Test
	public void testBuildEditMenuFromXml()
	{
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		JMenu menu;

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(editMenuXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode.getId(), 0);

		menu = JMenuFactory.buildMenu(BaseMenuId.EDIT.propertiesKey(), menuInfoLongBaseTreeNode);
		assertNotNull(menu);
	}

	@ExtendWith(IgnoreHeadlessExceptionExtension.class)
	@Test
	public void testBuildHelpMenuFromXml()
	{
		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;
		JMenu menu;

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(helpMenuXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode.getId(), 0);

		menu = JMenuFactory.buildMenu(BaseMenuId.HELP.propertiesKey(), menuInfoLongBaseTreeNode);
		assertNotNull(menu);
	}

}
