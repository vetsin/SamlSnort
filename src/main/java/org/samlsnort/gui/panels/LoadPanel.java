/*******************************************************************************
 * Copyright (C) 2011 by Michael Gerber
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 ******************************************************************************/
package org.samlsnort.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.samlsnort.gui.listener.TestListener;
import org.samlsnort.vo.TestCase;

public class LoadPanel extends JPanel implements TestListener {

	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public LoadPanel() {
		setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		add(progressBar, BorderLayout.SOUTH);

		textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);

	}

	public void clear() {
		textArea.setText("");
	}

	@Override
	public void endTestCase(final int currentTestCase,final  int maxTestCase,
			final TestCase testCase) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setValue(((currentTestCase - 1) * 2) + 1);
				textArea.append(testCase.getTestSuccessful() ? "Successful"
						: "Failed");
				textArea.append("\n\n");
				textArea.setCaretPosition(textArea.getText().length());
			}
		});
	}

	@Override
	public void startTestCase(final int currentTestCase, final int maxTestCase,
			final TestCase testCase) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setMaximum(maxTestCase * 2);
				progressBar.setValue(((currentTestCase - 1) * 2) + 1);
				textArea.append(testCase.getName() + " -> ");
				textArea.setCaretPosition(textArea.getText().length());		}
		});
		
	}

}