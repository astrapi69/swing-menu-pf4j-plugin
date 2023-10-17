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

import javax.swing.KeyStroke;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.id.generate.LongIdGenerator;
import io.github.astrapi69.swing.menu.MenuExtensions;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.swing.menu.enumeration.MenuType;
import io.github.astrapi69.swing.menu.model.KeyStrokeInfo;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.swing.menu.model.transform.MenuItemInfoConverter;

class MenuInfoJaxbTreeNodeConverterTest
{
	@Test
	@Disabled
	public void testBuildRootTreeNodeFromJaxbXmlForFileMenu()
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

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		fileMenuInfo = MenuInfo.builder().mnemonic(MenuExtensions.toMnemonic('F')).ordinal(1100)
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F")))
			.text("File").name(BaseMenuId.FILE.propertiesKey()).build();
		fileTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(fileMenuInfo).build();

		toggleFullscreenMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11100)
			.mnemonic(MenuExtensions.toMnemonic('T'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F11")))
			.text("Toggle Fullscreen").name(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey()).build();
		toggleFullscreenTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).parent(fileTreeNode).value(toggleFullscreenMenuInfo)
			.leaf(true).build();

		exitMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11200)
			.mnemonic(MenuExtensions.toMnemonic('E'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F4")))
			.text("Exit").name(BaseMenuId.EXIT.propertiesKey()).build();
		exitTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).parent(fileTreeNode).value(exitMenuInfo).build();

		menuBarTreeNode.addChild(fileTreeNode);
		fileTreeNode.addChild(toggleFullscreenTreeNode);
		fileTreeNode.addChild(exitTreeNode);

		treeNodeAsXml = MenuInfoJaxbTreeNodeConverter.toXml(menuBarTreeNode);

		menuInfoLongBaseTreeNode = MenuInfoJaxbTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

}