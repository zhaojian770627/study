package com.zj.study.rtext.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.fife.rtext.AWTExceptionHandler;
import org.fife.rtext.RText;
import org.fife.rtext.RTextPreferences;
import org.fife.rtext.StoreKeeper;
import org.fife.ui.SubstanceUtils;
import org.fife.ui.UIUtil;
import org.fife.ui.WebLookAndFeelUtils;
import org.fife.ui.app.AbstractGUIApplication;
import org.fife.ui.app.ThirdPartyLookAndFeelManager;

public class MyText {
	public static final int MDI_VIEW = 2;

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		AWTExceptionHandler.register();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// 1.5.2004/pwy: Setting this property makes the menu appear on top
				// of the screen on Apple Mac OS X systems. It is ignored by all other
				// other Java implementations.
				System.setProperty("apple.laf.useScreenMenuBar", "true");

				// Catch any uncaught Throwables on the EDT and log them.
				AWTExceptionHandler.register();

				// 1.5.2004/pwy: Setting this property defines the standard
				// Application menu name on Apple Mac OS X systems. It is ignored by
				// all other Java implementations.
				// NOTE: Although you can set the useScreenMenuBar property above at
				// runtime, it appears that for this one, you must set it before
				// (such as in your *.app definition).
				// System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				// "RText");

				// Swing stuff should always be done on the EDT...
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						String lafName = RTextPreferences.getLookAndFeelToLoad();

						// Allow Substance to paint window titles, etc. We don't allow
						// Metal (for example) to do this, because setting these
						// properties to "true", then toggling to a LAF that doesn't
						// support this property, such as Windows, causes the
						// OS-supplied frame to not appear (as of 6u20).
						if (SubstanceUtils.isASubstanceLookAndFeel(lafName)) {
							JFrame.setDefaultLookAndFeelDecorated(true);
							JDialog.setDefaultLookAndFeelDecorated(true);
						}

						String rootDir = AbstractGUIApplication.getLocationOfJar("RText.jar");
						ThirdPartyLookAndFeelManager lafManager = new ThirdPartyLookAndFeelManager(rootDir);

						try {
							ClassLoader cl = lafManager.getLAFClassLoader();
							// Set these properties before instantiating WebLookAndFeel
							if (WebLookAndFeelUtils.isWebLookAndFeel(lafName)) {
								WebLookAndFeelUtils.installWebLookAndFeelProperties(cl);
							}
							// Must set UIManager's ClassLoader before instantiating
							// the LAF. Substance is so high-maintenance!
							UIManager.getLookAndFeelDefaults().put("ClassLoader", cl);
							Class clazz = null;
							try {
								clazz = cl.loadClass(lafName);
							} catch (UnsupportedClassVersionError ucve) {
								// Previously opened with e.g. Java 6/Substance, now
								// restarting with Java 1.4 or 1.5.
								lafName = UIManager.getSystemLookAndFeelClassName();
								clazz = cl.loadClass(lafName);
							}
							LookAndFeel laf = (LookAndFeel) clazz.newInstance();
							UIManager.setLookAndFeel(laf);
							UIManager.getLookAndFeelDefaults().put("ClassLoader", cl);
							UIUtil.installOsSpecificLafTweaks();
						} catch (RuntimeException re) { // FindBugs
							throw re;
						} catch (Exception e) {
							e.printStackTrace();
						}

						// The default speed of Substance animations is too slow
						// (200ms), looks bad moving through JMenuItems quickly.
						if (SubstanceUtils.isSubstanceInstalled()) {
							try {
								SubstanceUtils.setAnimationSpeed(100);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						RText rtext = new RText(args);
						rtext.setLookAndFeelManager(lafManager);

						// For some reason, when using MDI_VIEW, the first window
						// isn't selected (although it is activated)...
						// INVESTIGATE ME!!
						if (rtext.getMainViewStyle() == MDI_VIEW) {
							rtext.getMainView().setSelectedIndex(0);
						}

						// We currently have one RText instance running.
						StoreKeeper.addRTextInstance(rtext);

					}
				});
			}
		});
	}

}
