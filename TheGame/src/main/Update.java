package main;

public class Update {



    public void updateGame(Player player, Camera camera,Map map){
        camera.centerOnPlayer(player,map);
        player.update();


    }

}
