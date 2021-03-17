package com.luxoft.ushych.ui.resources_manager;

import java.net.MalformedURLException;
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
        URL url = null;
        try {
            if (element) {
                url = new URL("file://C:/Users/valen/eclipse-workspace/workspaceRCP/JFaceHomework/"
                        + Icons.CHECK_IN.getPath());
            } else {
                url = new URL("file://C:/Users/valen/eclipse-workspace/workspaceRCP/JFaceHomework/"
                        + Icons.CHECK_OFF.getPath());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
        Image image = resourceManager.createImage(descriptor);
        return image;

    }
}