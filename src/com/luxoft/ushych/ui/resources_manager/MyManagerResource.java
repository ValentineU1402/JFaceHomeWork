package com.luxoft.ushych.ui.resources_manager;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class MyManagerResource {

    private ResourceManager resourceManager;

    public MyManagerResource() {
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources());
    }

    public Image getCheckImage(boolean element) {
        Image image = element ? getImage(Icons.CHECK_IN) : getImage(Icons.CHECK_OFF);
        return image;
    }

    public boolean checkStatus(Image image) {
        Image imageCheck = getImage(Icons.CHECK_IN);
        if (imageCheck.equals(image)) {
            return true;
        } else {
            return false;
        }
    }

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
        ImageData imageDate = image.getImageData();
        imageDate.width = 10;
        imageDate.height = 10;

        return image;
    }
}