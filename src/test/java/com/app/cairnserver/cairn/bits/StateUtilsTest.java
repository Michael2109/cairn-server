package com.app.cairnserver.cairn.bits;

import com.app.cairnserver.cairn.bits.positions.StatePositions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StateUtilsTest {

    @Test
    public void testCurrentPlayer(){
        Assertions.assertTrue(StateUtils.getCurrentPlayer(1));
        Assertions.assertFalse(StateUtils.getCurrentPlayer(0));
    }

    @Test
    public void testAddShaman(){
        Assertions.assertTrue(StateUtils.getAddShaman(StatePositions.ADD_SHAMAN));
        Assertions.assertFalse(StateUtils.getAddShaman(0));
    }

    @Test
    public void testJumpShaman(){
        Assertions.assertTrue(StateUtils.getJumpShaman(StatePositions.JUMP_SHAMAN));
        Assertions.assertFalse(StateUtils.getJumpShaman(0));
    }

    @Test
    public void testMoveShaman(){
        Assertions.assertTrue(StateUtils.getMoveShaman(StatePositions.MOVE_SHAMAN));
        Assertions.assertFalse(StateUtils.getMoveShaman(0));
    }

    @Test
    public void testTransformation(){
        Assertions.assertTrue(StateUtils.getTransformation(StatePositions.TRANSFORMATION));
        Assertions.assertFalse(StateUtils.getTransformation(0));
    }

    @Test
    public void testChangePlayer(){
        Assertions.assertEquals(StatePositions.CURRENT_PLAYER, StateUtils.changePlayer(0) & StatePositions.CURRENT_PLAYER);
        Assertions.assertEquals(0, StateUtils.changePlayer(StateUtils.changePlayer(0)));
    }
    @Test
    public void testChangeAddShaman(){
        Assertions.assertEquals(StatePositions.ADD_SHAMAN, StateUtils.changeAddShaman(0) & StatePositions.ADD_SHAMAN);
        Assertions.assertEquals(0, StateUtils.changeAddShaman(StateUtils.changeAddShaman(0)));
    }
    @Test
    public void testChangeJumpShaman(){
        Assertions.assertEquals(StatePositions.JUMP_SHAMAN, StateUtils.changeJumpShaman(0) & StatePositions.JUMP_SHAMAN);
        Assertions.assertEquals(0, StateUtils.changeJumpShaman(StateUtils.changeJumpShaman(0)));
    }
    @Test
    public void testChangeMoveShaman(){
        Assertions.assertEquals(StatePositions.MOVE_SHAMAN, StateUtils.changeMoveShaman(0) & StatePositions.MOVE_SHAMAN);
        Assertions.assertEquals(0, StateUtils.changeMoveShaman(StateUtils.changeMoveShaman(0)));
    }
    @Test
    public void testChangeTransformation(){
        Assertions.assertEquals(StatePositions.TRANSFORMATION, StateUtils.changeTransformation(0) & StatePositions.TRANSFORMATION);
        Assertions.assertEquals(0, StateUtils.changeTransformation(StateUtils.changeTransformation(0)));
    }
}
