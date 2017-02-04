package org.usfirst.frc.team5964.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive chassis = new RobotDrive(1, 2);
	Victor climbChain = new Victor(3);
	Servo smallServo = new Servo(0);
	Servo bigServo = new Servo(1);
	Joystick joystick1 = new Joystick(0);
	Joystick joystick2 = new Joystick(1);
	CameraServer server;
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	
         CameraServer server = CameraServer.getInstance();
         server.startAutomaticCapture();

     // code for camera set up and use
     chassis.setExpiration(1);
 }

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
//Drive Robot
		chassis.arcadeDrive(joystick1.getY() * 0.75, -joystick1.getX() * 0.80);
//Move Gear Holding Arm
		bigServo.set(joystick2.getX()  * 0.2);
//Close gear holder
		if (joystick1.getRawButton(5)) {
			smallServo.set(0.2); }
		//drop gear
		if (joystick1.getRawButton(6)) {
			smallServo.set(0.7); }
//Left Position
		if (joystick1.getRawButton(3)) {
			bigServo.set(0.2); }
//Middle Position
		if (joystick1.getRawButton(2)) {
			bigServo.set(0.55);
		}
//Right Position		
		if(joystick1.getRawButton(4)) {
			bigServo.set(0.9);
		}
//Move Climbing Mechanism		
		if (joystick1.getRawButton(7)) {
			climbChain.set(.1);
		}
//Bring Back climbing mechanism		
		if (joystick1.getRawButton(8)) {
			climbChain.set(0);
		}
	}


	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}
