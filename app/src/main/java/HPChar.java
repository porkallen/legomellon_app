/**
 * Created by allen on 10/2/2017.
 */
//#define CHAR_NUM (100

class HPCharNode{
    private String name;
    private String[][] qn;
    private boolean flag;
    private int q_idx;
    public HPCharNode(String name,String[][] qList){
        this.name = name;
        this.qn = new qNode(qList);
        this.flag = true;
    }
    public void qIdxInc(){
        this.q_idx++;
    }
    public void qIdxDec(){
        this.q_idx--;
    }
    public int getQIdx(){
        return this.q_idx;
    }
}
public class HPChar{
    private HPCharNode[] charPool;
    public String[][] questionList= new String[][]{
            {"Hey this is Neville Longbottom, Harry gave me your number. He said that\n" +
                    "you could help me. I lost my rememberall again…Oh,wait I forgot to ask, what is your name?", "Yes"},
            {"YOYO", "HIHI"}
    };
    public HPChar(){
        charPool[0] = new HPCharNode("Naville",questionList);
        //charPool[1] = new HPCharNode("Hermione",questionList1);
        //charPool[2] = new HPCharNode("Dumbledore",questionList2);
    }
}
