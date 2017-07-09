/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.leapmotion.leap.*;
import com.leapmotion.leap.Bone.Type;
/**
 *
 * @author dogdays'
 */
class Brain extends Listener {
    private int loveCount = 0;
    private int yeahCount = 0;
    long beginFrameID;
    boolean emptyFrame = true;
    public boolean reactionCatched = false;
    private GUI gui;
    
    public void setGUI(GUI g) {
        gui = g;
    }
    
    Translation[] trans = {new Translation(
            "I-love-you-sign-american-sign-language-deaf-culture-32053964-773-1024.gif"
            , "我爱你"), new Translation("hello.gif", "你好"), 
            new Translation("拳头.jpg", "零"),
            new Translation("ye.jpg", "耶")};
    
    private TranslationDictionary dic = new TranslationDictionary(trans);
   
    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }


    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        

       Hand hand = frame.hands().get(0);
        if(hand.grabAngle()>3&&loveCount<1){
            gui.response(dic.getPictureAddress("零"), "零");
            reactionCatched=true;
        }
        
        if(emptyFrame){
            beginFrameID = frame.id();
            emptyFrame = false;
        } //end if
        
        //love sign
        Type[] boneTypes = Bone.Type.values();
        FingerList fingers = hand.fingers();
        double angle1 = getAngleOfFinger(fingers.get(1),boneTypes);
        double angle4 = getAngleOfFinger(fingers.get(4),boneTypes);
        if (angle1<0.35&&angle4<0.35
            &&angle1!=0&&angle4!=0
                &&hand.grabAngle()>1.75&&hand.grabAngle()<2.0){
            loveCount++;
        } //end if
 
        if(frame.id()-beginFrameID>=50){
            if(loveCount>30){
                gui.response("I-love-you-sign-american-sign-language-deaf-culture-32053964-773-1024.gif", "我爱你");
                loveCount = 0;
                reactionCatched=true;
            }else if(yeahCount>30){
                gui.response(dic.getPictureAddress("耶"), "耶");
                yeahCount = 0;
                reactionCatched=true;
            }
            else{
                beginFrameID = frame.id();
                loveCount = 0;
                yeahCount = 0;
            } //end if
        } //end if*/
        
        //hello
        GestureList gestureList = frame.gestures();
        double yValue = hand.palmPosition().getY();
        for(Gesture gesture : gestureList){
            if(gesture.type()== Gesture.Type.TYPE_SWIPE){
                SwipeGesture sg = new SwipeGesture(gesture);
                System.out.println("start"+sg.startPosition());
                System.out.println("end"+sg.position());
                if(yValue>370 && hand.grabAngle()<2.0){
                    gui.response(dic.getPictureAddress("你好"), "你好");
                    reactionCatched=true;
                } //end if
            } //end if
        } //end for loop
        
        //yeah
        double angle2 = getAngleOfFinger(fingers.get(2),boneTypes);
        if (angle1<0.6&&angle2<0.6
            &&angle1!=0&&angle4!=0
                &&hand.grabAngle()>1.60&&hand.grabAngle()<2.2){
            yeahCount++;
        } //end if
    }
    
    double getAngleOfFinger(Finger finger, Type[] boneTypes){
        Bone bone = finger.bone(boneTypes[2]);
        Vector vector1 = bone.direction();
        Bone bone1 = finger.bone(boneTypes[3]);
        Vector vector2 = bone1.direction();
        double angle = vector1.angleTo(vector2);
        return angle;
    }
}
