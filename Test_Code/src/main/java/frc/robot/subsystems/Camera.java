package frc.robot.subsystems;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

public class Camera {
    public UsbCamera frontCamera;

    public Camera()
    {
        // // start capture from the camera at the front plugged into USB slot 0
        // frontCamera = CameraServer.startAutomaticCapture(0);
        // // set the stream's resolution to 320x240
        // frontCamera.setResolution(320, 240);
        // // set the stream's frames per second to 15
        // frontCamera.setFPS(24);
    }

    // public void initDefaultCommand()
    // {
    // }
}
