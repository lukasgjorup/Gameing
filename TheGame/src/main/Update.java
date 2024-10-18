package main;

import entity.DamageNumberManeger;
import entity.EnemyManeger;
import entity.EnemySlime;
import entity.Player;

import java.io.IOException;

public class Update {



    public void updateGame(Player player, Camera camera, Map map,EnemyManeger enemyManeger, DamageNumberManeger damageNumberManeger) throws IOException {
        camera.centerOnPlayer(player,map);
        player.update();
        enemyManeger.update(player);
        EnemyManeger.deadUpdate();
        damageNumberManeger.update();
        enemyManeger.newEnemy(enemyManeger.tileSize,player);


    }

}
