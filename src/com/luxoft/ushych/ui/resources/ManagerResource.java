package com.luxoft.ushych.ui.resources;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Image;

/**
 * Constructs a new instance of this class you can control icons resources
 *
 * @author vushych@luxoft.com
 * 
 * @see Icons
 * @see Image
 * @see ImageDescriptor
 * @see ClassLoader
 * @see URL
 */
public class ManagerResource {

    private ResourceManager resourceManager;

    /**
     * Application`s icons manager configures necessary icons for this application
     *
     * @see Icons
     * @see ResourceManager
     */
    public ManagerResource() {
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources());
    }

    /**
     * Get equivalent Image by boolean for TableList
     *
     * @param check is boolean element that configured correct image element
     *
     * @see TableList
     * @see Icons
     */
    public Image getCheckImage(boolean check) {
        Image image = check ? getImage(Icons.CHECK_IN) : getImage(Icons.CHECK_OFF);
        return image;
    }

    /**
     * Check boolean status image from cell of TableList
     *
     * @param image is image element that configured correct boolean element
     *
     * @see TableList
     * @see Icons
     */
    public boolean checkStatus(Image image) {
        Image imageCheck = getImage(Icons.CHECK_IN);
        if (imageCheck.equals(image)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get arrow image for column header
     *
     * @param arrow from column header
     *
     * @see Image
     * @see Icons
     */
    public Image getArrow(Image arrow) {
        Image imageCheck = getArrowUp();
        if (arrow == null || !imageCheck.equals(arrow)) {
            return imageCheck;
        } else {
            return getArrowDown();
        }
    }

    private Image getArrowUp() {
        Image image = getImage(Icons.ARROW_UP);
        return image;
    }

    private Image getArrowDown() {
        Image image = getImage(Icons.ARROW_DOWN);
        return image;
    }

    private Image getImage(Icons name) {
        URL url = ClassLoader.getSystemResource(name.getPath());
        ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
        Image image = resourceManager.createImage(descriptor);
        return image;
    }
}