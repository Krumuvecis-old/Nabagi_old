package server.userInterface;


class UserInterface extends GraphicsParts {

    private static Ekrans ekrans;

    private static int[] windowSize = {400, 300},
            windowLocation = {500, 150};


    static void start(String threadName){
        ekrans = new Ekrans(threadName, windowSize, windowLocation);
    }

    static void refresh(){
        ekrans.refresh();
    }

}
