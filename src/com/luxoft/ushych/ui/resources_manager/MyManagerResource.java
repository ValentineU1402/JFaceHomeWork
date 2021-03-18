package com.luxoft.ushych.ui.resources_manager;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Image;

public class MyManagerResource {

    private ResourceManager resourceManager;

    public MyManagerResource() {
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources());
    }

    public Image getCheckImage(boolean element) {
        URL url = element ? ClassLoader.getSystemResource(Icons.CHECK_IN.getPath())
                : ClassLoader.getSystemResource(Icons.CHECK_OFF.getPath());
        ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
        Image image = resourceManager.createImage(descriptor);
        return image;
    }

    public boolean checkStatus(Image image) {
        URL url = ClassLoader.getSystemResource(Icons.CHECK_IN.getPath());
        ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
        Image imageCheckIn = resourceManager.createImage(descriptor);
        if (image.equals(imageCheckIn)) {
            return true;
        } else {
            return false;
        }
    }
}