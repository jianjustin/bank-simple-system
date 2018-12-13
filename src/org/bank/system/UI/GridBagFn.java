package org.bank.system.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import java.awt.Component;


/**
 * 布局操作类
 * @author chenjianrui
 *
 */
public class GridBagFn {
	
	/**
	 * 添加GridBag布局组件
	 * @param gridwidth
	 * @param weightx
	 * @param weighty
	 * @param insets
	 * @param gridBagLayout
	 * @param component
	 */
	public static void addCompoment(JPanel jPanel,int gridwidth,double weightx,double weighty,Insets insets,GridBagLayout gridBagLayout,GridBagConstraints gridBagConstraints,Component component) {
		//gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty=weighty;
		gridBagConstraints.insets = insets;
		gridBagLayout.setConstraints(component, gridBagConstraints);
		jPanel.add(component);
	}

}
