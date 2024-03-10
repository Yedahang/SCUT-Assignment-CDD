package CDD;

import Model.Entity.Message;

public class Test {
   public static void main(String []args){

       Message message =new Message(); //new Message(5," ");
       message.initial();
       message.senMessage(2);

   }
}



