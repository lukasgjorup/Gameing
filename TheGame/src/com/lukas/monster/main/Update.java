package com.lukas.monster.main;

import com.lukas.monster.entity.DamageNumberManeger;
import com.lukas.monster.entity.EnemyManeger;
import com.lukas.monster.entity.Player;

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
