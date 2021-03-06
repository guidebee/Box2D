package com.guidebee.game.tutorial.box2d.stage;


import android.util.Log;

import com.guidebee.game.Collidable;
import com.guidebee.game.graphics.TextureAtlas;
import com.guidebee.game.scene.collision.Collision;
import com.guidebee.game.scene.collision.CollisionListener;
import com.guidebee.game.tutorial.box2d.actor.AnimatedFaceGroup;
import com.guidebee.game.tutorial.box2d.actor.Face;
import com.guidebee.game.tutorial.box2d.actor.Ground;
import com.guidebee.game.tutorial.box2d.actor.Player;
import com.guidebee.game.ui.GameController;
import com.guidebee.game.ui.drawable.TextureRegionDrawable;

import static com.guidebee.game.GameEngine.assetManager;

public class SelfControlStage extends Box2DGameStage implements CollisionListener{


    private final Ground ground;
    private final Ground secondLevelGround;
    private final Ground thirdLevelGround;
    private final Face face;
    //private final AnimatedFaceGroup animatedFaceGroup;
    private final Player player;

    public SelfControlStage(){
        super();
        TextureAtlas textureAtlas=assetManager.get("box2d.atlas",TextureAtlas.class);
        GameController gameController
                = new GameController(new TextureRegionDrawable(textureAtlas.findRegion("Back")),
                new TextureRegionDrawable(textureAtlas.findRegion("Joystick")),
                new TextureRegionDrawable(textureAtlas.findRegion("Button_08_Normal_Shoot")),
                new TextureRegionDrawable(textureAtlas.findRegion("Button_08_Pressed_Shoot")),
                new TextureRegionDrawable(textureAtlas.findRegion("Button_08_Normal_Virgin")),
                new TextureRegionDrawable(textureAtlas.findRegion("Button_08_Pressed_Virgin"))
        );
        setGameController(gameController);

        ground=new Ground();
        addActor(ground);
        secondLevelGround=new Ground(0,80,500);
        addActor(secondLevelGround);
        thirdLevelGround=new Ground(0,160,200);
        addActor(thirdLevelGround);
        face=new Face();
        addActor(face);
        //animatedFaceGroup=new AnimatedFaceGroup();
        //addActor(animatedFaceGroup);

        player=new Player();
        addActor(player);
        gameController.addGameControllerListener(player);

        setCollisionListener(this, Collidable.BOX2D_CONTACT);
    }

    @Override
    public void collisionDetected(Collision collision) {
        Collidable objectA=collision.getObjectA();
        Collidable objectB=collision.getObjectB();


        if(!(objectA.getName()=="Ground" && objectB.getName()=="Ground")) {
            Log.d("Collide", collision.getObjectA().getName() + ":" +
                    collision.getObjectB().getName());
        }
    }
}
