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

import javax.swing.KeyStroke;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.file.write.StoreFileExtensions;
import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.id.generate.LongIdGenerator;
import io.github.astrapi69.menu.pf4j.transform.MenuInfoTreeNodeConverter;
import io.github.astrapi69.swing.menu.MenuExtensions;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.swing.menu.enumeration.MenuType;
import io.github.astrapi69.swing.menu.model.KeyStrokeInfo;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.swing.menu.model.transform.MenuItemInfoConverter;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

public class JMenuTestFileFactoryTest
{
	@Test
	public void testBuildRootTreeNodeFromXmlForFileMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode;
		BaseTreeNode<MenuInfo, Long> fileTreeNode;
		BaseTreeNode<MenuInfo, Long> toggleFullscreenTreeNode;
		BaseTreeNode<MenuInfo, Long> exitTreeNode;
		MenuInfo menuBarInfo;
		MenuInfo fileMenuInfo;
		MenuInfo toggleFullscreenMenuInfo;
		MenuInfo exitMenuInfo;
		LongIdGenerator idGenerator;
		String treeNodeAsXml;

		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;

		idGenerator = LongIdGenerator.of(0L);

		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		fileMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('F')).ordinal(1100)
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F")))
			.text("File").name(BaseMenuId.FILE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();
		fileTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(fileMenuInfo).build();

		toggleFullscreenMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11100)
			.mnemonic(MenuExtensions.toMnemonic('T'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F11")))
			.text("Toggle Fullscreen").name(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey())
			.actionCommand("io.github.astrapi69.swing.action.ToggleFullScreenAction").build();
		toggleFullscreenTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).parent(fileTreeNode).value(toggleFullscreenMenuInfo)
			.leaf(true).build();

		exitMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11200)
			.mnemonic(MenuExtensions.toMnemonic('E'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F4")))
			.text("Exit").name(BaseMenuId.EXIT.propertiesKey())
			.actionCommand("io.github.astrapi69.swing.action.ExitApplicationAction").build();
		exitTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).parent(fileTreeNode).value(exitMenuInfo).build();

		menuBarTreeNode.addChild(fileTreeNode);
		fileTreeNode.addChild(toggleFullscreenTreeNode);
		fileTreeNode.addChild(exitTreeNode);

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);

		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-file-menu.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

	@Test
	public void testBuildRootTreeNodeFromXmlForEditMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode;
		BaseTreeNode<MenuInfo, Long> editTreeNode;
		MenuInfo menuBarInfo;
		MenuInfo editMenuInfo;
		LongIdGenerator idGenerator;
		String treeNodeAsXml;

		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;

		idGenerator = LongIdGenerator.of(0L);

		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		editMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('E')).ordinal(1200)
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed E")))
			.text("Edit").name(BaseMenuId.EDIT.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();


		editTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(editMenuInfo).build();

		menuBarTreeNode.addChild(editTreeNode);

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);

		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-edit-menu.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

	@Test
	public void testBuildRootTreeNodeFromXmlForHelpMenu()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode;
		BaseTreeNode<MenuInfo, Long> helpTreeNode;
		BaseTreeNode<MenuInfo, Long> helpContentTreeNode;
		BaseTreeNode<MenuInfo, Long> donateTreeNode;
		BaseTreeNode<MenuInfo, Long> licenseTreeNode;
		BaseTreeNode<MenuInfo, Long> infoTreeNode;
		MenuInfo menuBarInfo;
		MenuInfo helpMenuInfo;
		MenuInfo helpContentMenuInfo;
		MenuInfo donateMenuInfo;
		MenuInfo licenseMenuInfo;
		MenuInfo infoMenuInfo;
		LongIdGenerator idGenerator;
		String treeNodeAsXml;

		BaseTreeNode<MenuInfo, Long> menuInfoLongBaseTreeNode;

		idGenerator = LongIdGenerator.of(0L);

		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		helpMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('H')).text("Help").ordinal(1300)
			.name(BaseMenuId.HELP.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();
		helpTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(helpMenuInfo).build();

		helpContentMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(13100)
			.mnemonic(MenuExtensions.toMnemonic('c'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed H")))
			.text("Content").name(BaseMenuId.HELP_CONTENT.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();
		helpContentTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(helpContentMenuInfo).leaf(true).build();

		donateMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).text("Donate").ordinal(13200)
			.name(BaseMenuId.HELP_DONATE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();
		donateTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(donateMenuInfo).build();

		licenseMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).text("Licence").ordinal(13300)
			.name(BaseMenuId.HELP_LICENSE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();
		licenseTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(licenseMenuInfo).build();

		infoMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).text("Info").ordinal(13400)
			.name(BaseMenuId.HELP_INFO.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction").build();
		infoTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(infoMenuInfo).build();

		menuBarTreeNode.addChild(helpTreeNode);
		helpTreeNode.addChild(helpContentTreeNode);
		helpTreeNode.addChild(donateTreeNode);
		helpTreeNode.addChild(licenseTreeNode);
		helpTreeNode.addChild(infoTreeNode);

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);
		RuntimeExceptionDecorator.decorate(() -> StoreFileExtensions.toFile(
			FileFactory.newFileQuietly(PathFinder.getSrcTestResourcesDir(), "app-help-menu.xml"),
			treeNodeAsXml, "UTF-8"));

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

}
