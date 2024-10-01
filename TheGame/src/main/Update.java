package main;

import entity.EnemyManeger;
import entity.EnemySlime;
import entity.Player;

public class Update {



    public void updateGame(Player player, Camera camera, Map map,EnemyManeger enemyManeger){
        camera.centerOnPlayer(player,map);
        player.update();
        enemyManeger.update(player);


    }

}
