package org.dev.control.service.boxCreators;

import javafx.scene.layout.HBox;
import org.dev.model.LinksModel;

// LinksViewFactory.java
public interface LinksViewFactory {
    HBox createLinkBox(LinksModel link);
}

