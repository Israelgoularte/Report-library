package org.dev.control.service.boxCreators;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.dev.control.service.LinksService;
import org.dev.model.LinksModel;

import java.util.List;

public class MenuNavegacaoFactory {

    public void createMenu(Menu navegacao){
        List<MenuItem> menuTipo = navegacao.getItems().stream().toList();
            for (LinksModel link: LinksService.getInstance().getLista()) {
//                String tipoTemp = link.getTipo();
//                String categoriaTemp = link.getcategoria();
//                if(!tipo.contains(tipoTemp)){
//                    tipo.add(tipoTemp);
//                    Menu menuTemp = new Menu(tipoTemp);
//                    menuTipo.add(menuTemp);
//                }
//                if(!categoria.contains(categoriaTemp)){
//                    categoria.add(categoriaTemp);
//                    Menu menuTemp = new Menu(categoriaTemp);
//                    menuCategoria.add(menuTemp);
//                }
//                MenuItem linkItem = new MenuItem(link.getNome());
//                linkItem.setOnAction(e->{
//                    //hyperlink
//                });
//                menuItems.add(linkItem);
            }
//            for (Menu t :
//                    tipo) {
//
//            }
    }
}
