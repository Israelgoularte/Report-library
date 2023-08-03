package org.dev.service.fxelement;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.dev.model.ReportModel;
import org.dev.service.MyServicesDAO;
import org.dev.util.OpenBrowserLink;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

import java.util.HashMap;

public class MenuReportOptionsMyServices extends MyServicesDAO {
    private final Menu menu_report;

    public MenuReportOptionsMyServices(Menu menuReport) {
        menu_report = menuReport;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        HashMap<String, Menu> tipo_Menu_Map = new HashMap<>();
        HashMap<String,HashMap<String, Menu>> tipo_Categoria_Menu_Map = new HashMap<>();

        //remove todos os itens adicionados a navegacao
        menu_report.getItems().clear();
        try{

            for (ReportModel link : UsuarioContexto.getInstance().getContexto().getReportsByIdUsuario())
            {
                String tipo = link.getTipo();
                String categoria = link.getCategoria();

                //Cria o menu item e adiciona uma ação a ele
                MenuItem menuItemLink = new MenuItem(link.getNome());
                menuItemLink.setOnAction(e -> {
                    OpenBrowserLink.acessarLink(link.getLink());
                });

                Menu menuTipo = tipo_Menu_Map.getOrDefault(tipo,null);
                if (menuTipo==null)
                { // caso o menu tipo ainda não exista
                    //cria o menu tipo
                    menuTipo = new Menu(tipo);

                    //adiciona o novo menu de tipo a o Menu Principal @navegacao
                    menu_report.getItems().add(menuTipo);

                    //cria o menu de categoria
                    Menu menuCategoria = new Menu(categoria);

                    //adiciona o menu categoria a lista de item do menu de tipo
                    menuTipo.getItems().add(menuCategoria);

                    //adiciona o Menu item ao menu da categoria
                    menuCategoria.getItems().add(menuItemLink);

                    //mapeia o menu tipo
                    tipo_Menu_Map.put(tipo,menuTipo);

                    //cria um mapa para categoria
                    HashMap<String, Menu> categoria_Menu_Map = new HashMap<>();

                    //adiciona o menu categoria ao novo mapa
                    categoria_Menu_Map.put(categoria,menuCategoria);

                    //adiciona o novo mapa a o mapa tipoCategoria
                    tipo_Categoria_Menu_Map.put(tipo,categoria_Menu_Map);
                }else{// caso o menu tipo exista
                    //verifica se ja esta mapeado a categoria para o tipo
                    HashMap<String, Menu> categoriaMap = tipo_Categoria_Menu_Map.getOrDefault(tipo,null); //retorna o Mapa de categoria
                    Menu menuCategoria = categoriaMap.get(categoria);
                    if (menuCategoria == null)
                    {

                        //cria o Menu da categoria
                        menuCategoria = new Menu(categoria);

                        //adiciona o menuItem a lista de itens da categoria
                        menuCategoria.getItems().add(menuItemLink);

                        //adiciona o novo menu a lista de item do menu de tipo
                        menuTipo.getItems().add(menuCategoria);

                        // cria um novo mapa para a categoria
                        categoriaMap = new HashMap<>();

                        //adiciona o novo menu de categoria ao mapa criado
                        categoriaMap.put(categoria,menuCategoria);

                        //adiciona o novo mapa a lista de mapas de categoria.
                        tipo_Categoria_Menu_Map.put(tipo,categoriaMap);
                    }else{ // caso o a categoria desse tipo esteja mapeada.

                        //adiciona o item a lista de items da categoria
                        menuCategoria.getItems().add(menuItemLink);
                    }
                }

            }
            return new GenericMenssage<>(true,"Menu atualizado com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
