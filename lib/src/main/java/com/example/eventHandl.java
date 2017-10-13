package com.example;
import java.util.Scanner;

public class eventHandl {
    public static int rndIdx = 0;
    //new comment riah
    private static boolean containsString(String original, String tobeChecked, boolean caseSensitive)
    {
        if (caseSensitive)
        {
            return original.contains(tobeChecked);

        }
        else
        {
            return original.toLowerCase().contains(tobeChecked.toLowerCase());
        }

    }
    public void eventHandler(msgHandler ms){
        Constants CONSTANTS = new Constants();
        String printData;
        HPChar hpChar = new HPChar();
        user userNode = new user();
        Scanner input = new Scanner(System.in);
        while(true){
            HPCharNode node = hpChar.HPCharGet(0);
            String tmpStr = node.curQuesGet();
            /*First question or the end of question pool*/
            if((node.getQIdx() == CONSTANTS.GREETING) || (tmpStr == null)){
                System.out.println("==Round "+Integer.toString(++rndIdx)+"==");
                node.resetQIdx();
                System.out.println(node.curQuesGet());
                userNode.name = input.nextLine();
                node.qIdxInc();
            }
            else{
                String tmpAns = node.curAnsGet();
                System.out.println(tmpStr.replaceFirst(CONSTANTS.DEF_NAME,userNode.name));
                userNode.ans = input.nextLine();
                /* debug purpose: Allen
                try {
                    ms.SendMsg(userNode.ans);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                //System.out.println(userNode.ans);
                /*
                * If matched, move on to next question.
                * If not, stay and loop the question again.
                * */
                /*debug purpose: Allen
                try {
                    String recvMsg = ms.RecvMsg();
                    if(containsString(recvMsg,tmpAns,false) == true){
                        //System.out.println("Match !");
                        node.qIdxInc();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                if(containsString(userNode.ans,tmpAns,false) == true){
                    //System.out.println("Match !");
                    node.qIdxInc();
                }
                if(node.isFinalQues() == true){
                    tmpStr = node.curQuesGet();
                    System.out.println(tmpStr.replaceFirst(CONSTANTS.DEF_NAME,userNode.name));
                    node.resetQIdx();
                }
            }
        }
    }
}
