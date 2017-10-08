package com.example;

/**
 * Created by allen on 10/8/2017.
 */
//#define CHAR_NUM (100

class HPCharNode{
    private String name;
    private String[][] qn;
    private boolean flag;
    private int q_idx;
    private boolean isQuesEnd(){
        if(this.q_idx < this.qn.length)
            return false;
        else
            return true;
    };
    public boolean isFinalQues(){
        if(this.q_idx == this.qn.length -1)
            return true;
        else
            return false;
    };
    public HPCharNode(String name,String[][] qList){
        this.name = name;
        this.qn = qList;
        this.flag = true;
    };
    public void qIdxInc(){
        this.q_idx++;
    }
    public void qIdxDec(){
        this.q_idx--;
    }
    public int getQIdx(){
        return this.q_idx;
    }
    public void resetQIdx(){this.q_idx = 0; }

    public String curQuesGet(){
        if(isQuesEnd() == false)
            return this.qn[this.q_idx][0];
        else
            return null;
    }
    public String curAnsGet(){
        if(isQuesEnd() == false)
            return this.qn[this.q_idx][1];
        else
            return null;
    }
}

public class HPChar{
    private HPCharNode[] charPool;
    Constants CONSTANTS = new Constants();
    public String[][] questionList= new String[][]{
            {"Hey this is Neville Longbottom, Harry gave me your number. He said that\n" +
                    "you could help me. I lost my rememberall again! Oh...wait I forgot to ask, what is your name?", "Yes"},
            {"Oh nice to meet you "+CONSTANTS.DEF_NAME+", well do you think you could help me find my rememberall?", "Yes"},
            {"Oh that is great, really great, I don't even know where to start looking... I don't even know who to ask... if only I could think of someone smart... do you know anyone who is really smart?","Hermione"},
            {"Oh "+CONSTANTS.DEF_NAME+", that is bloody brilliant idea! I have her number can you talk to her for me? She makes me nervous... She is always correcting my spells... Here is her contact info",CONSTANTS.DEF_ANS}
    };
    public HPChar(){
        charPool = new HPCharNode[3];
        charPool[0] = new HPCharNode("Naville",questionList);
        charPool[1] = new HPCharNode("Hermione",questionList);
        //charPool[1] = new HPCharNode("Hermione",questionList1);
        //charPool[2] = new HPCharNode("Dumbledore",questionList2);
    }
    public HPCharNode HPCharGet(int idx){
        return charPool[idx];
    }
}

