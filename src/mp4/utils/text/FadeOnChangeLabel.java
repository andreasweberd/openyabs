/*
 *  This file is part of MP by anti43 /GPL.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mp4.utils.text;


import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import java.awt.Insets;

       

        import javax.swing.JLabel;

/**
 * <p>Title: FadeOnChangeLabel </p>
 *
 * <p>Description: A label that animates when it's text value change.</p>
 *
 * <p>Copyright: Copyright (c) 2006 - 2007</p>
 *
 * @author Chris Fragoulides
 * @version 1.0
 */
public class FadeOnChangeLabel
extends JLabel implements Runnable{
    private static final long serialVersionUID = 1L;
	/** 
	 * The last value of the label.
	 */
	private String lastValue = "";
    /** 
     * True when an animation is in progress. 
     */
    private boolean animating = false;
    /** 
     * Fade value.
     */
    private float fade = 0;
    /**
     * Initial fade value.
     */
    private float initFade = .2f;
    /** 
     * This variable controls how match we 
     * will fade in each animation loop. 
     */
    private float fadeStep = -0.1f;
    /**
     * Red component of the fade color.
     */
    private float red = 0.4f;
    /**
     * Green component of the fade color.
     */
    private float green = 0.8f;
    /**
     * Blue component of the fade color.
     */
    private float blue = 0f;
    /**
     * When a fade is initiated, the label will have this color.
     */
    private Color fadeColor = new Color(red, green, blue, fade);
    /**
     * Flag to control the fade process.
     */
    private boolean paintCalled = false;
    /**
     * If this label doesn't control it's painting, we must set
     * the Container responsible for it. 
     */
    private Container repaintCont = null;

    /**
     * Constructor. It contains only a call to setOpaque(),
     * because we want the color changes to be visible.
     */
    public FadeOnChangeLabel() {
        setOpaque(true);
    }

    /**
     * Sets animation parameters. Will only accept values in the
     * range [0,1] for color components and initial fade,
     * and in (-1,0) for fade step.
     * @param r - float value of the red color component.
     * @param g	- float value of the green color component.
     * @param b	- float value of the blue color component.
     * @param fStep	- float value of the fade step.
     * @param iFade	- float value of the initial fade.
     */
    public void setParams(float r, float g, float b,
            float fStep, float iFade) {
        if (r >= 0 && r <= 1) {
            red = r;
        }
        if (g >= 0 && g <= 1) {
            green = g;
        }
        if (b >= 0 && b <= 1) {
            blue = b;
        }
        if (fStep > 0 && fStep < 1) {
            fadeStep = fStep;
        }
        if (iFade >= 0 && iFade <= 1) {
            initFade = iFade;
        }
    }

    /**
     * Sets the Container that controls this label's painting.
     * Call this method if the label is a child component of a container
     * that does not control the painting process.
     * @param c - Container.
     */
    public void setRepaintContainer(Container c) {
        repaintCont = c;
    }

    /**
     * Sets the text, and initiates the animation if the 
     * new value is different that the old one.
     * @param text 
     */
    @Override
    public void setText(String text) {
        super.setText(text);
//        if (!text.equals(lastValue)) {
//            lastValue = text;
            animate();
//        }
    }

    /**
     * Override paintComponent to make it fulfil our needs.
     */
    @Override
    public void paintComponent(Graphics g) {
        // Let the Label perform its normal painting.
        super.paintComponent(g);
        // Now make the fade effect.
        if (fade != 0) {
            Insets i = this.getInsets();
            g.setColor(fadeColor);
            g.fillRect(i.left, i.top,
                    getWidth() - i.left - i.right,
                    getHeight() - i.top - i.bottom);
        }
        // paintComponent() called, we can continue to the next
        // animation frame.
        paintCalled = true;
    }

    /**
     * Initiates the animation Thread.
     */
    private void animate() {
        // Only animate if there's not another animation in progress
        // and this Label is actually added to a parent Container, i.e.
        // if it is visible.
        if (!animating && this.getParent() != null) {
            Thread t = new Thread(this);
            t.start();
        }
    }

    /**
     * The core of the animation.
     */
    @Override
    public void run() {
        animating = true;
        fade = initFade;
        try {
            while (fade != 0) {
                fadeColor = new Color(red, green, blue, fade);
                fade += fadeStep;
                if (fade < 0) {
                    fade = 0;
                }
                paintCalled = false;
                if (repaintCont == null) {
                    repaint(); // This label controls it's painting.
                } // This label controls it's painting.
                else {
                    repaintCont.repaint(); // Ask the container to repaint.
                } // Ask the container to repaint.
                // Now wait until paintComponent() gets called.
                while (!paintCalled && fade != 0) {
                    Thread.sleep(500);
                }
            }
            animating = false;
        } catch (Exception e) {
            animating = false;
            System.out.println("FadeOnChangeLabel encountered an error: " + e.getMessage());
        }
    }
}



