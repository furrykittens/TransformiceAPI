package ee.ignorance.transformiceapi.protocol.server.mouse;

import ee.ignorance.transformiceapi.processors.AbstractProcessor;
import ee.ignorance.transformiceapi.processors.mouse.MouseMoveProcessor;
import ee.ignorance.transformiceapi.protocol.server.AbstractResponse;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class MouseMoveResponse extends AbstractResponse {

        private String gameCode;
        private int posX;
        private int posY;
        private int movX;
        private int movY;
        private boolean goingLeft;
        private boolean goingRight;
        private boolean jumping;
        private byte jumpingImage;
        private byte unk; //unknown
        private int playerID;

        public MouseMoveResponse(byte[] rawMessage) {
                super(rawMessage);
        }

        @Override
        public void parse(byte[] rawMessage) {
                try {
                        DataInputStream in = new DataInputStream(new ByteArrayInputStream(rawMessage));
                        gameCode = Integer.toString(in.readInt());
                        goingRight = in.readBoolean();
                        goingLeft = in.readBoolean();
                        posX = in.readShort();
                        posY = in.readShort();
                        movX = in.readShort();
                        movY = in.readShort();
                        jumping = in.readBoolean();
                        jumpingImage = in.readByte();
                        unk = in.readByte(); //no clue what this is
                        playerID = in.readInt();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public String getGameCode() {
                return gameCode;
        }

        public int getPosX() {
                return posX;
        }

        public int getPosY() {
                return posY;
        }

        public int getMovX() {
                return movX;
        }

        public int getMovY() {
                return movY;
        }

        public boolean isJumping() {
                return jumping;
        }

        public boolean isGoingRight() {
                return goingRight;
        }

        public boolean isGoingLeft() {
                return goingLeft;
        }

        public int getPlayerID() {
                return playerID;
        }

        public byte getJumpingImage() {
                return jumpingImage;
        }

        public byte getUnk() {
                return unk;
        }

        @Override
        public AbstractProcessor getProcessor() {
                return new MouseMoveProcessor();
        }
}
