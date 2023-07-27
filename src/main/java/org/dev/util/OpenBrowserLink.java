package org.dev.util;

import java.awt.*;
import java.net.URI;

public class OpenBrowserLink {

    public static boolean acessarLink(String link){
        try{
            Desktop.getDesktop().browse(new URI(link));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
