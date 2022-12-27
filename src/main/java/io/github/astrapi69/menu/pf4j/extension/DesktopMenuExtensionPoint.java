package io.github.astrapi69.menu.pf4j.extension;

import javax.swing.JMenuBar;
import org.pf4j.ExtensionPoint;

public interface DesktopMenuExtensionPoint extends ExtensionPoint {

    void buildMenuBar(JMenuBar menuBar);

}
