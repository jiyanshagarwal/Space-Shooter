package game;

/**
 *
 * @author Jiyansh
 */
public enum ControlType {
    ARROW_KEYS {
        @Override
        public boolean upPressed() {
            return Game.KEY_INPUT.upPressed();
        }

        @Override
        public boolean downPressed() {
            return Game.KEY_INPUT.downPressed();
        }

        @Override
        public boolean leftPressed() {
            return Game.KEY_INPUT.leftPressed();
        }

        @Override
        public boolean rightPressed() {
            return Game.KEY_INPUT.rightPressed();
        }

        @Override
        public boolean spacePressed() {
            return Game.KEY_INPUT.mPressed();
        }
    },
    WASD {
        @Override
        public boolean upPressed() {
            return Game.KEY_INPUT.wPressed();
        }

        @Override
        public boolean downPressed() {
            return Game.KEY_INPUT.sPressed();
        }

        @Override
        public boolean leftPressed() {
            return Game.KEY_INPUT.aPressed();
        }

        @Override
        public boolean rightPressed() {
            return Game.KEY_INPUT.dPressed();
        }

        @Override
        public boolean spacePressed() {
            return Game.KEY_INPUT.onePressed();
        }
    };

    public abstract boolean upPressed();

    public abstract boolean downPressed();

    public abstract boolean leftPressed();

    public abstract boolean rightPressed();

    public abstract boolean spacePressed();
}
