package io.github.astrapi69.menu.pf4j.extension;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.pf4j.Extension;

@Extension
public class DesktopMenuExtension implements DesktopMenuExtensionPoint {

    public void buildMenuBar(JMenuBar menuBar) {
        JMenu exampleMenu = new JMenu("Example");
        exampleMenu.add(new JMenuItem("Hello World"));
        menuBar.add(exampleMenu);
    }

}
