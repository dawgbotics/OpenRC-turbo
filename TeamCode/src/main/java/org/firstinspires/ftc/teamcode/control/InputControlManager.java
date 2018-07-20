package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Oversees both auto and teleop.
 * Manages transitions between the two of them, and calls the requisite update() functions
 * on the AutoInputManager and TeleOpInputManager.
 *
 * OpModes should interface solely with this class.
 *
 * Singleton
 */
public class InputControlManager {

    private static InputControlManager self = null;

    private AutoInputManager auto;
    private TeleOpInputManager teleop;

    private static boolean RUN_AUTO;

    private ElapsedTime timer;
    private static final double AUTO_PERIOD = 30.0;
    private static final double TRANSITION_PERIOD = 8.0;
    private static final double TELE_OP_PERIOD = 90.0;

    private InputControlManager() {
        timer = new ElapsedTime();
        teleop = TeleOpInputManager.get();
    }

    /**
     * TELE-OP GET: sets this to teleop
     * Singleton functionality
     * Gets the instance of this class
     * @return The only extant instance of the class
     */
    public static InputControlManager get() {
        if (self == null) {
            self = new InputControlManager();
        }
        InputControlManager.RUN_AUTO = false;
        return self;
    }

    /**
     * AUTO GET: sets this to auto
     * Singleton functionality
     * Gets the instance of this class
     * @param autoFileName What auto file to run
     * @return The only extant instance of the class
     */
    public static InputControlManager get(String autoFileName) {
        InputControlManager selfTemp = InputControlManager.get();

        selfTemp.auto = AutoInputManager.get(autoFileName);
        InputControlManager.RUN_AUTO = true;

        return selfTemp;
    }

    public void update() {
        if (RUN_AUTO && timer.seconds() < AUTO_PERIOD) {
            auto.update();
        }
        // Run teleop if:
        // You're running teleop and not at the end of teleop OR
        // You're running auto and not at the end of teleop and not in the auto period
        else if ((!RUN_AUTO   && timer.seconds() < TELE_OP_PERIOD) ||
                  (RUN_AUTO   && timer.seconds() < (TELE_OP_PERIOD + TRANSITION_PERIOD + AUTO_PERIOD)
                              && timer.seconds() > AUTO_PERIOD + TRANSITION_PERIOD)) {
            teleop.update();
        }
    }
}
