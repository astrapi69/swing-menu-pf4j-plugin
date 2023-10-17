package io.github.astrapi69.menu.pf4j.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.KeyStroke;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.id.generate.LongIdGenerator;
import io.github.astrapi69.swing.menu.MenuExtensions;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.swing.menu.enumeration.MenuType;
import io.github.astrapi69.swing.menu.model.KeyStrokeInfo;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.swing.menu.model.transform.MenuItemInfoConverter;

class MenuInfoTreeNodeConverterTest
{
	@Test
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

		treeNodeAsXml = MenuInfoTreeNodeConverter.toXml(menuBarTreeNode);

		menuInfoLongBaseTreeNode = MenuInfoTreeNodeConverter.toMenuInfoTreeNode(treeNodeAsXml);
		assertNotNull(menuInfoLongBaseTreeNode);
		assertEquals(menuInfoLongBaseTreeNode, menuBarTreeNode);
	}

}