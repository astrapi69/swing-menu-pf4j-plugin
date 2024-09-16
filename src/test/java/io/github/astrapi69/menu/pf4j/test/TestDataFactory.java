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
package io.github.astrapi69.menu.pf4j.test;

import javax.swing.KeyStroke;

import io.github.astrapi69.gen.tree.BaseTreeNode;
import io.github.astrapi69.id.generate.LongIdGenerator;
import io.github.astrapi69.swing.menu.MenuExtensions;
import io.github.astrapi69.swing.menu.enumeration.BaseMenuId;
import io.github.astrapi69.swing.menu.enumeration.MenuType;
import io.github.astrapi69.swing.menu.model.KeyStrokeInfo;
import io.github.astrapi69.swing.menu.model.MenuInfo;
import io.github.astrapi69.swing.menu.model.transform.MenuItemInfoConverter;

/**
 * The class {@link TestDataFactory} provides factory methods for generating test data for menu
 * structures, specifically {@link BaseTreeNode} objects containing {@link MenuInfo} for testing.
 */
public class TestDataFactory
{

	/**
	 * Creates a test menu bar containing the "File" and "Help" menus, each with sub-items.
	 *
	 * @return the root {@link BaseTreeNode} of the menu structure
	 */
	public static BaseTreeNode<MenuInfo, Long> getTestFileAndHelpMenuWithMenubar()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode;
		BaseTreeNode<MenuInfo, Long> fileTreeNode;
		BaseTreeNode<MenuInfo, Long> toggleFullscreenTreeNode;
		BaseTreeNode<MenuInfo, Long> exitTreeNode;
		BaseTreeNode<MenuInfo, Long> helpTreeNode;
		BaseTreeNode<MenuInfo, Long> helpContentTreeNode;
		BaseTreeNode<MenuInfo, Long> donateTreeNode;
		BaseTreeNode<MenuInfo, Long> diagnosticTreeNode;
		BaseTreeNode<MenuInfo, Long> diagnosticActivityTreeNode;
		BaseTreeNode<MenuInfo, Long> diagnosticProfileTreeNode;
		BaseTreeNode<MenuInfo, Long> diagnosticUsageTreeNode;
		BaseTreeNode<MenuInfo, Long> licenseTreeNode;
		BaseTreeNode<MenuInfo, Long> infoTreeNode;
		MenuInfo helpMenuInfo;
		MenuInfo helpContentMenuInfo;
		MenuInfo donateMenuInfo;
		MenuInfo diagnosticMenuInfo;
		MenuInfo diagnosticActivityMenuInfo;
		MenuInfo diagnosticProfileMenuInfo;
		MenuInfo diagnosticUsageMenuInfo;
		MenuInfo licenseMenuInfo;
		MenuInfo infoMenuInfo;
		MenuInfo menuBarInfo;
		MenuInfo fileMenuInfo;
		MenuInfo toggleFullscreenMenuInfo;
		MenuInfo exitMenuInfo;
		LongIdGenerator idGenerator;

		idGenerator = LongIdGenerator.of(0L);

		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");
		menuBarInfo.setActionClass("io.github.astrapi69.awt.action.NoAction");

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		fileMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('F')).ordinal(1100)
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F")))
			.text("File").name(BaseMenuId.FILE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		fileTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(fileMenuInfo).build();

		toggleFullscreenMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11100)
			.mnemonic(MenuExtensions.toMnemonic('T'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F11")))
			.text("Toggle Fullscreen").name(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey())
			.actionCommand("io.github.astrapi69.swing.action.ToggleFullScreenAction")
			.actionClass("io.github.astrapi69.swing.action.ToggleFullScreenAction").build();
		toggleFullscreenTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).parent(fileTreeNode).value(toggleFullscreenMenuInfo)
			.leaf(true).build();

		exitMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11200)
			.mnemonic(MenuExtensions.toMnemonic('E'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F4")))
			.text("Exit").name(BaseMenuId.EXIT.propertiesKey())
			.actionCommand("io.github.astrapi69.swing.action.ExitApplicationAction")
			.actionClass("io.github.astrapi69.swing.action.ExitApplicationAction").build();
		exitTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).parent(fileTreeNode).value(exitMenuInfo).build();

		helpMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('H')).ordinal(13000)
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed H")))
			.text("Help").name(BaseMenuId.HELP.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		helpTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(helpMenuInfo).build();
		helpContentMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(13100)
			.mnemonic(MenuExtensions.toMnemonic('C'))
			.keyStrokeInfo(
				KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl alt pressed H")))
			.text("Help Content").name(BaseMenuId.HELP_CONTENT.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		helpContentTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(helpContentMenuInfo).build();

		donateMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(13200)
			.mnemonic(MenuExtensions.toMnemonic('L'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl pressed L")))
			.text("Donate").name(BaseMenuId.HELP_DONATE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		donateTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(donateMenuInfo).build();

		diagnosticMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('G')).ordinal(13300).text("Diagnostic >")
			.name(TestMenuId.HELP_DIAGNOSTIC.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		diagnosticTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(diagnosticMenuInfo).build();

		diagnosticActivityMenuInfo = MenuInfo.builder().type(MenuType.CHECK_BOX_MENU_ITEM)
			.ordinal(133100).mnemonic(MenuExtensions.toMnemonic('A'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl pressed A")))
			.text("Activity").name(TestMenuId.HELP_DIAGNOSTIC_ACTIVITY.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		diagnosticActivityTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).leaf(true).value(diagnosticActivityMenuInfo).build();

		diagnosticProfileMenuInfo = MenuInfo.builder().type(MenuType.RADIO_BUTTON_MENU_ITEM)
			.ordinal(133200).mnemonic(MenuExtensions.toMnemonic('P'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl pressed P")))
			.text("Profile").name(TestMenuId.HELP_DIAGNOSTIC_PROFILE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		diagnosticProfileTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).leaf(true).value(diagnosticProfileMenuInfo).build();

		diagnosticUsageMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(133300)
			.mnemonic(MenuExtensions.toMnemonic('U'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl pressed U")))
			.text("Usage").name(TestMenuId.HELP_DIAGNOSTIC_USAGE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		diagnosticUsageTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).leaf(true).value(diagnosticUsageMenuInfo).build();

		licenseMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(13400)
			.mnemonic(MenuExtensions.toMnemonic('L'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl pressed L")))
			.text("Licence").name(BaseMenuId.HELP_LICENSE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		licenseTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(licenseMenuInfo).build();

		infoMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(13500)
			.mnemonic(MenuExtensions.toMnemonic('I'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("ctrl pressed I")))
			.text("Info").name(BaseMenuId.HELP_INFO.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		infoTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(infoMenuInfo).build();

		menuBarTreeNode.addChild(fileTreeNode);
		menuBarTreeNode.addChild(helpTreeNode);
		fileTreeNode.addChild(toggleFullscreenTreeNode);
		fileTreeNode.addChild(exitTreeNode);
		helpTreeNode.addChild(helpContentTreeNode);
		helpTreeNode.addChild(donateTreeNode);
		helpTreeNode.addChild(diagnosticTreeNode);
		helpTreeNode.addChild(licenseTreeNode);
		helpTreeNode.addChild(infoTreeNode);
		diagnosticTreeNode.addChild(diagnosticActivityTreeNode);
		diagnosticTreeNode.addChild(diagnosticProfileTreeNode);
		diagnosticTreeNode.addChild(diagnosticUsageTreeNode);
		return menuBarTreeNode;
	}

	/**
	 * Creates a test menu bar containing the "Help" menus, each with sub-items.
	 *
	 * @return the root {@link BaseTreeNode} of the menu structure
	 */
	public static BaseTreeNode<MenuInfo, Long> getTestHelpMenuWithMenubar()
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

		idGenerator = LongIdGenerator.of(0L);

		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");
		menuBarInfo.setActionClass("io.github.astrapi69.awt.action.NoAction");

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		helpMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('H')).text("Help").ordinal(1300)
			.name(BaseMenuId.HELP.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		helpTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(helpMenuInfo).build();

		helpContentMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(13100)
			.mnemonic(MenuExtensions.toMnemonic('c'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed H")))
			.text("Content").name(BaseMenuId.HELP_CONTENT.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		helpContentTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(helpContentMenuInfo).leaf(true).build();

		donateMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).text("Donate").ordinal(13200)
			.name(BaseMenuId.HELP_DONATE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		donateTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(donateMenuInfo).build();

		licenseMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).text("Licence").ordinal(13300)
			.name(BaseMenuId.HELP_LICENSE.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		licenseTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(licenseMenuInfo).build();

		infoMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).text("Info").ordinal(13400)
			.name(BaseMenuId.HELP_INFO.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();
		infoTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).value(infoMenuInfo).build();

		menuBarTreeNode.addChild(helpTreeNode);
		helpTreeNode.addChild(helpContentTreeNode);
		helpTreeNode.addChild(donateTreeNode);
		helpTreeNode.addChild(licenseTreeNode);
		helpTreeNode.addChild(infoTreeNode);
		return menuBarTreeNode;
	}

	/**
	 * Creates a test menu bar containing the "File" menus, each with sub-items.
	 *
	 * @return the root {@link BaseTreeNode} of the menu structure
	 */
	public static BaseTreeNode<MenuInfo, Long> getTestFileMenuWithMenubar()
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

		// Create a LongIdGenerator starting from 0
		idGenerator = LongIdGenerator.of(0L);

		// Create MenuInfo for the root menu bar
		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");
		menuBarInfo.setActionClass("io.github.astrapi69.awt.action.NoAction");

		// Create the root tree node for the menu bar
		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		// Create MenuInfo for the File menu with a mnemonic and shortcut
		fileMenuInfo = MenuInfo.builder().mnemonic(MenuExtensions.toMnemonic('F')).ordinal(1100)
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction")
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F")))
			.text("File").name(BaseMenuId.FILE.propertiesKey()).build();
		fileTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(fileMenuInfo).build();

		// Create MenuInfo for Toggle Fullscreen menu item with a mnemonic and shortcut
		toggleFullscreenMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11100)
			.mnemonic(MenuExtensions.toMnemonic('T'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F11")))
			.text("Toggle Fullscreen").name(BaseMenuId.TOGGLE_FULLSCREEN.propertiesKey())
			.actionCommand("io.github.astrapi69.swing.action.ToggleFullScreenAction")
			.actionClass("io.github.astrapi69.swing.action.ToggleFullScreenAction").build();
		toggleFullscreenTreeNode = BaseTreeNode.<MenuInfo, Long> builder()
			.id(idGenerator.getNextId()).parent(fileTreeNode).value(toggleFullscreenMenuInfo)
			.leaf(true).build();

		// Create MenuInfo for Exit menu item with a mnemonic and shortcut
		exitMenuInfo = MenuInfo.builder().type(MenuType.MENU_ITEM).ordinal(11200)
			.mnemonic(MenuExtensions.toMnemonic('E'))
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed F4")))
			.text("Exit").name(BaseMenuId.EXIT.propertiesKey())
			.actionCommand("io.github.astrapi69.swing.action.ExitApplicationAction")
			.actionClass("io.github.astrapi69.swing.action.ExitApplicationAction").build();
		exitTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.leaf(true).parent(fileTreeNode).value(exitMenuInfo).build();

		// Add File menu node to the menu bar and add the sub-menu items to the File menu
		menuBarTreeNode.addChild(fileTreeNode);
		fileTreeNode.addChild(toggleFullscreenTreeNode);
		fileTreeNode.addChild(exitTreeNode);
		return menuBarTreeNode;
	}

	/**
	 * Creates a test menu bar containing the "Edit" menus, each with sub-items.
	 *
	 * @return the root {@link BaseTreeNode} of the menu structure
	 */
	public static BaseTreeNode<MenuInfo, Long> getTestEditMenuWithMenubar()
	{
		BaseTreeNode<MenuInfo, Long> menuBarTreeNode;
		BaseTreeNode<MenuInfo, Long> editTreeNode;
		MenuInfo menuBarInfo;
		MenuInfo editMenuInfo;
		LongIdGenerator idGenerator;

		idGenerator = LongIdGenerator.of(0L);

		menuBarInfo = MenuItemInfoConverter.fromJMenuBar();
		menuBarInfo.setActionCommand("io.github.astrapi69.awt.action.NoAction");
		menuBarInfo.setActionClass("io.github.astrapi69.awt.action.NoAction");

		menuBarTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(menuBarInfo).build();

		editMenuInfo = MenuInfo.builder().type(MenuType.MENU)
			.mnemonic(MenuExtensions.toMnemonic('E')).ordinal(1200)
			.keyStrokeInfo(KeyStrokeInfo.toKeyStrokeInfo(KeyStroke.getKeyStroke("alt pressed E")))
			.text("Edit").name(BaseMenuId.EDIT.propertiesKey())
			.actionCommand("io.github.astrapi69.awt.action.NoAction")
			.actionClass("io.github.astrapi69.awt.action.NoAction").build();


		editTreeNode = BaseTreeNode.<MenuInfo, Long> builder().id(idGenerator.getNextId())
			.value(editMenuInfo).build();

		menuBarTreeNode.addChild(editTreeNode);
		return menuBarTreeNode;
	}

	public static BaseTreeNode<MenuInfo, Long> newTestMenuWithSize(int size)
	{
		if (size <= 1)
		{
			return getTestEditMenuWithMenubar();
		}
		if (size <= 5)
		{
			return getTestFileMenuWithMenubar();
		}
		else
		{
			return getTestFileAndHelpMenuWithMenubar();
		}
	}
}
