package Model.Entity;
public class  Message {
    private int id;
    private  String  content;

    public Message(){};

    public  Message(int id, String content) {
        this.id = id;
        this.content = content;
    }

    private Message[] messages= new Message[4];
    public void initial(){
        messages[0]= new Message(1,"大家好，很高兴见到各位！");
        messages[1]= new Message(2,"你是GG还是MM");
        messages[2]= new Message(3,"快点吧， 我等的花都谢了");
        messages[3]= new Message(4,"真牛");
    }

    public Message idToMessage(int Id){
        for (Message m: messages){
               if(m.id==Id){
                   return m;
               }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;

    }

 public void senMessage(int id){
     System.out.println(idToMessage(id).content);
 }


}
